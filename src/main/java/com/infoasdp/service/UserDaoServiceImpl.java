package com.infoasdp.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.infoasdp.login.entity.UserEntity;
import com.infoasdp.repository.UserRepository;
import com.infoasdp.util.CommonPageUtil;
import com.infoasdp.util.CommonPaging;
import com.infoasdp.util.JsonUtil;
import com.infoasdp.util.StringFunction;
import com.infoasdp.util.SystemConstant;
import com.infoasdp.util.UserException;

public class UserDaoServiceImpl implements UserDaoService{
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	CommonPageUtil pageUtil;

	@Override
	public synchronized String saveUser(UserEntity request) throws Exception {
		UserEntity toUpdate = request;
		if (isExistUserByUserId(request.getUserId(), request.getId())) {
			throw new UserException("ALREADY_EXISTS", "User with that User ID already exists !");
		}
		if (request == null || StringFunction.isEmpty(request.getUserId())) {
			throw new UserException("MANDATORY", "User ID is mandatory !");
		}
		if (StringFunction.isNotEmpty(request.getId())) {
			toUpdate = userRepo.findOne(request.getId());
			if (toUpdate == null) {
				throw new UserException("NOT_FOUND", "User not found !");
			}
			BeanUtils.copyProperties(request, toUpdate);
		}else{
			sendmail(request);
		}
		userRepo.save(toUpdate);
		
		//CommonResponse<String> response = comGen.generateCommonResponse(SystemConstant.SUCCESS);
		return JsonUtil.generateDefaultJsonWriter().writeValueAsString("SUCCESS");
	}
	
	private boolean isExistUserByUserId(String userId, String id) {
		CriteriaBuilder critBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = critBuilder.createQuery(Long.class);
		countQuery.select(critBuilder.count(countQuery.from(UserEntity.class)));
		Root<UserEntity> root = countQuery.from(UserEntity.class);
		Predicate predicateValid = critBuilder.equal(root.get(UserEntity.Constant.VALID_FIELD),
				SystemConstant.ValidFlag.VALID);
		Predicate predicateUserId = critBuilder.equal(root.get(UserEntity.Constant.USER_ID_FIELD), userId);
		Predicate validAndUserId = critBuilder.and(predicateValid, predicateUserId);
		em.createQuery(countQuery);
		if (id != null) {
			Predicate predicateId = critBuilder.notEqual(root.get(UserEntity.Constant.ID_FIELD), id);
			countQuery.where(critBuilder.and(validAndUserId, predicateId));
		} else {
			countQuery.where(critBuilder.and(validAndUserId));
		}
		Long rowCount = em.createQuery(countQuery).getSingleResult();
		return (rowCount != null && rowCount > 0 ? true : false);
	}

	@Override
	public UserEntity findOneUser(UserEntity request) throws Exception {
		return userRepo.findOne(request.getId());
	}

	@Override
	public String deleteUser(UserEntity request) throws Exception {
		if (request == null || StringFunction.isEmpty(request.getId())) {
			throw new UserException("MANDATORY", "ID is mandatory !");
		}
		UserEntity user = userRepo.findOne(request.getId());
		if (user != null) {
			user.setValid(SystemConstant.ValidFlag.INVALID);
		}
		//CommonResponse<String> toSerialize = comGen.generateCommonResponse("DELETE_SUCCESS");
		ObjectWriter writer = JsonUtil.generateDefaultJsonWriter();
		return writer.writeValueAsString("Delete Success");
	}

	@Override
	public UserEntity getUsersByName(String userId) {
		return userRepo.findOneByUserId(userId);
	}

	@Override
	public CommonPaging<UserEntity> getUserWithPaging(String search, String user, int page) {
		Pageable pageable = pageUtil.generateDefaultPageRequest(page);
		Page<UserEntity> paging = userRepo.findByUserIdNotAndUserIdContaining(user, search, pageable);
		return new CommonPaging<>(paging);
	}
	
	private void sendmail(UserEntity request) throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("septa.rf2018@gmail.com", "SEPTA1509");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("septa.rf2018@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(request.getUserId()));
		   msg.setSubject("welcome to your new infoASDP Account");
		   String messageMail = "<html> "
		   		+ "<body><h3>Hi "+request.getName()+",</h3>"
		   		+ "<p>"
		   		+ "Your account already created!<br/>"
		   		+ "Username : "+request.getUserId()+"<br/>"
				+ "Password : "+request.getPassword()+"<br/>"
				+ "Please change your password for security privacy<br/>"
		   		+ "<h3>Thanks, <br/>Regards,<br/>Admin infoASDP</h3>"
		   		+ "</p>"
		   		+ "</body></html>";
		   msg.setContent(messageMail, "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent(messageMail, "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   /*MimeBodyPart attachPart = new MimeBodyPart();

		   attachPart.attachFile("/var/tmp/image19.png");
		   multipart.addBodyPart(attachPart);*/
		   msg.setContent(multipart);
		   Transport.send(msg);   
		}
	
}
