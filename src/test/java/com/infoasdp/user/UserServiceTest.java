package com.infoasdp.user;

import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.infoasdp.auth.AuthenticationEngine;
import com.infoasdp.auth.PasswordUtil;
import com.infoasdp.login.entity.UserEntity;
import com.infoasdp.repository.UserRepository;
import com.infoasdp.repository.UserRoleRepository;
import com.infoasdp.service.UserDaoService;
import com.infoasdp.test.ApplicationMock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserDaoService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired
	private AuthenticationEngine authEngine;
	
	@Before
	public void before(){}
	
	@After
	public void after(){
		//userRepo.deleteAll();
	}
	
	@Test
	public void saveUserTest() throws Exception{
		UserEntity user = value();
		userService.saveUser(user);
		Assert.assertEquals(2, userRepo.count());
	}
	
	public UserEntity value(){
		UserEntity user = new UserEntity();
		user.setUserId("septa.uddin15@gmail.com");
		user.setName("SEPTA");
		user.setAlamat("JAKARTA");
		user.setPassword("password");
		user.setNoHp("081809844015");
		user.setUserRole(userRoleRepo.findOne("1"));
		return user;
	}
	
	@Test
	public void getPasswordTest(){
		try {
			PasswordUtil.getPasswordHash("creator", "saltpass");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void authenticationTest() throws Exception{
		authEngine.authenticate("superuser", "creator");
	}
}
