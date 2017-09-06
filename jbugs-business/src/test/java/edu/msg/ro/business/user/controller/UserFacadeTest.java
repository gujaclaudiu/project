package edu.msg.ro.business.user.controller;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.ro.AbstractIntegrationTest;
import edu.msg.ro.business.DTO.UserDTO;
import edu.msg.ro.business.controller.UserFacade;
import edu.msg.ro.business.exception.BusinessException;

public class UserFacadeTest extends AbstractIntegrationTest {

	@EJB
	private UserFacade sut;

	@Test
	public void createUser_succesfull() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setLastname("Doe");
		testUser.setEmail("mail1@msggroup.com");
		testUser.setPhoneNumber("0040111111111");

		UserDTO createdUser = sut.createUser(testUser);

		Assert.assertNotNull("The newly persisted user should have an id!", createdUser.getId());
	}

	@Test
	public void createUser_ActiveByDefault() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John2");
		testUser.setLastname("Doe2");
		testUser.setEmail("mail2@msggroup.com");
		testUser.setPhoneNumber("0049111111112");

		UserDTO createdUser = sut.createUser(testUser);

		Assert.assertTrue("The newly persisted user should be active!", createdUser.isActive());
	}

	@Test
	public void updateUser_succesfull() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("Michael");
		testUser.setLastname("Bolton");
		testUser.setEmail("boltoooon@msggroup.com");
		testUser.setPhoneNumber("0040111475111");

		UserDTO createdUser = sut.createUser(testUser);

		UserDTO user2 = new UserDTO();
		user2.setUsername(createdUser.getUsername());
		user2.setId(createdUser.getId());
		user2.setFirstname("Jamal");
		user2.setLastname("Michaels");
		user2.setEmail("boltoooon@msggroup.com");
		user2.setPhoneNumber("0040111475111");
		user2.setLockVersion(createdUser.getLockVersion());

		UserDTO updatedUser = sut.updateUser(user2);

		Assert.assertNotEquals(createdUser.getFirstname(), updatedUser.getFirstname());
	}

	public void deleteUser_succesfull() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("Michael");
		testUser.setLastname("Phelps");
		testUser.setEmail("pleeeeelps@msggroup.com");
		testUser.setPhoneNumber("0040231475111");

		UserDTO createdUser = sut.createUser(testUser);

		UserDTO deletedUser = sut.deleteUser(createdUser);

		Assert.assertFalse(deletedUser.isActive());
	}

}
