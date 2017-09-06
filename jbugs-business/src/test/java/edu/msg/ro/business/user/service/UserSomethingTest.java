package edu.msg.ro.business.user.service;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import edu.msg.ro.AbstractIntegrationTest;
import edu.msg.ro.business.DTO.UserDTO;
import edu.msg.ro.business.exception.BusinessException;
import edu.msg.ro.business.service.UserService;

public class UserSomethingTest extends AbstractIntegrationTest {

	@EJB
	private UserService sut;

	@Test
	public void createUser_FirstnameValidationFail() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setLastname("Doe");
		testUser.setEmail("mail3@msggroup.com");
		testUser.setPhoneNumber("0040111111113");

		try {
			sut.createUser(testUser);
		} catch (BusinessException e) {
			Assert.assertEquals("Firstname cannot be null", e.getMessage());
			return;
		}
		Assert.fail("Firstname validation should fail!");
	}

	@Test
	public void createUser_LastnameValidationFail() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setEmail("mail4@msggroup.com");
		testUser.setPhoneNumber("0040111111114");

		try {
			sut.createUser(testUser);
		} catch (BusinessException e) {
			Assert.assertEquals("Lastname cannot be null", e.getMessage());
			return;
		}
		Assert.fail("Lastname validation should fail!");
	}

	@Test
	public void createUser_PhoneNullValidationFail() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setLastname("Doe");
		testUser.setEmail("mail5@msggroup.com");

		try {
			sut.createUser(testUser);
		} catch (BusinessException e) {
			Assert.assertEquals("Phone number cannot be null", e.getMessage());
			return;
		}
		Assert.fail("Phone number null validation should fail!");
	}

	@Test
	public void createUser_EmailNullValidationFail() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setLastname("Doe");
		testUser.setPhoneNumber("0040111111115");

		try {
			sut.createUser(testUser);
		} catch (BusinessException e) {
			Assert.assertEquals("Email cannot be null", e.getMessage());
			return;
		}
		Assert.fail("Email null validation should fail!");
	}

	@Test
	public void createUser_PhoneNumberCharactersValidationFail() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setLastname("Doe");
		testUser.setEmail("mail4@msggroup.com");
		testUser.setPhoneNumber("0040aaaaaaaaa");

		try {
			sut.createUser(testUser);
		} catch (BusinessException e) {
			Assert.assertEquals("Phone number can only contain digits from 0 to 9", e.getMessage());
			return;
		}
		Assert.fail("Phone number characters validation should fail!");
	}

	@Test
	public void createUser_PhoneNumberRegionValidationFail() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setLastname("Doe");
		testUser.setEmail("mail5@msggroup.com");
		testUser.setPhoneNumber("0041111111111");

		try {
			sut.createUser(testUser);
		} catch (BusinessException e) {
			Assert.assertEquals("Phone number must be from Romania or Germany", e.getMessage());
			return;
		}
		Assert.fail("Phone number region validation should fail!");
	}

	@Test
	public void createUser_EmailFormatValidationFail() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setLastname("Doe");
		testUser.setEmail("mail5@msggoup.com");
		testUser.setPhoneNumber("0040111111111");

		try {
			sut.createUser(testUser);
		} catch (BusinessException e) {
			Assert.assertEquals("Email must fit the format [address]@msggroup.com", e.getMessage());
			return;
		}
		Assert.fail("Email format validation should fail!");
	}

	@Test
	public void createUser_UniqueEmailValidationFail() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John");
		testUser.setLastname("Doe");
		testUser.setEmail("unique@msggroup.com");
		testUser.setPhoneNumber("0040111111115");

		sut.createUser(testUser);

		UserDTO testUser2 = new UserDTO();
		testUser2.setFirstname("Mary");
		testUser2.setLastname("Jane");
		testUser2.setEmail("unique@msggroup.com");
		testUser2.setPhoneNumber("0040111111116");

		try {
			sut.createUser(testUser2);
		} catch (BusinessException e) {
			Assert.assertEquals("User already exists with given email", e.getMessage());
			return;
		}
		Assert.fail("Unique email validation should fail!");
	}

	@Test
	public void createUser_UniquePhoneNumberValidationFail() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("John2");
		testUser.setLastname("Doe2");
		testUser.setPhoneNumber("0049123456777");
		testUser.setEmail("unique2@msggroup.com");

		sut.createUser(testUser);

		UserDTO testUser2 = new UserDTO();
		testUser2.setFirstname("Mary2");
		testUser2.setLastname("Jane2");
		testUser2.setPhoneNumber("0049123456777");
		testUser2.setEmail("unique22@msggroup.com");

		try {
			sut.createUser(testUser2);
		} catch (BusinessException e) {
			Assert.assertEquals("User already exists with given phone number", e.getMessage());
			return;
		}
		Assert.fail("Unique phone Number validation should fail!");
	}

	@Test
	public void createUser_ShortNameValidationFail() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("Jo");
		testUser.setLastname("Do");
		testUser.setPhoneNumber("0040123123124");
		testUser.setEmail("unique3@msggroup.com");

		try {
			sut.createUser(testUser);
		} catch (BusinessException e) {
			Assert.assertEquals("Cannot create username, firstname and lastname are too short", e.getMessage());
			return;
		}
		Assert.fail("Short name validation should fail!");
	}

	@Test
	public void createUser_AvailableUsernameValidationFail() throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setFirstname("Ion");
		testUser.setLastname("Pop");
		testUser.setPhoneNumber("0040123123123");
		testUser.setEmail("unique4@msggroup.com");

		UserDTO u1 = sut.createUser(testUser);

		UserDTO testUser2 = new UserDTO();
		testUser2.setFirstname("Ion");
		testUser2.setLastname("Pop");
		testUser2.setPhoneNumber("0049123456456");
		testUser2.setEmail("unique42@msggroup.com");

		UserDTO u2 = new UserDTO();

		try {
			u2 = sut.createUser(testUser2);
		} catch (BusinessException e) {
			Assert.assertEquals("Cannot generate username, all possible usernames are taken", e.getMessage());
			return;
		}

		System.out.println("u1:" + u1.getUsername());
		System.out.println("u2:" + u2.getUsername());
		Assert.fail("Unique username validation should fail!");
	}

}
