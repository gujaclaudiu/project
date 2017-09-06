package edu.msg.ro.business.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.msg.ro.business.DAO.UserDAO;
import edu.msg.ro.business.DTO.UserDTO;
import edu.msg.ro.business.DTO.mapper.UserDTOMapper;
import edu.msg.ro.business.exception.BusinessException;
import edu.msg.ro.persistence.entity.User;

/**
 * Controller for User component.
 * 
 * @author Andrei Floricel, msg systems ag
 *
 */
@Stateless
public class UserService {

	@Inject
	private UserDTOMapper userDTOMapper;

	@EJB
	private UserDAO userDAO;

	public UserDTO createUser(UserDTO user) throws BusinessException {
		validateUserCreationData(user);

		user.setUsername(generateUsername(user));

		User userEntity = new User();
		userDTOMapper.mapToEntity(user, userEntity);

		userEntity.setActive(true);

		userDAO.persistEntity(userEntity);
		User persistedUser = userDAO.findEntity(userEntity.getId());
		return userDTOMapper.mapToDTO(persistedUser);
	}

	public UserDTO updateUser(UserDTO user) throws BusinessException {
		validateUserUpdateData(user);

		User initialUser = userDAO.findUserByUsername(user.getUsername());
		userDTOMapper.mapToEntity(user, initialUser);
		return userDTOMapper.mapToDTO(initialUser);
	}

	public UserDTO deleteUser(UserDTO user) throws BusinessException {

		user.setActive(false);

		return updateUser(user);
	}

	private void validateUserCreationData(UserDTO user) throws BusinessException {

		String phoneNumber = user.getPhoneNumber();

		if (user.getFirstname() == null)
			throw new BusinessException("Firstname cannot be null");

		if (user.getLastname() == null)
			throw new BusinessException("Lastname cannot be null");

		if (phoneNumber == null)
			throw new BusinessException("Phone number cannot be null");

		if (user.getEmail() == null)
			throw new BusinessException("Email cannot be null");

		if (!phoneNumber.matches("[0-9]+"))
			throw new BusinessException("Phone number can only contain digits from 0 to 9");

		if (phoneNumber.length() != 13)
			throw new BusinessException("Phone number has incorrect number of characters");

		if (phoneNumber.startsWith("0040") == false && phoneNumber.startsWith("0049") == false)
			throw new BusinessException("Phone number must be from Romania or Germany");

		if (user.getEmail().endsWith("@msggroup.com") == false)
			throw new BusinessException("Email must fit the format [address]@msggroup.com");

		User existingUserWithSameEmail = userDAO.findUserByEmail(user.getEmail());
		if (existingUserWithSameEmail != null) {
			throw new BusinessException("User already exists with given email");
		}

		User existingUserWithSamePhoneNumber = userDAO.findUserByPhoneNumber(user.getPhoneNumber());
		if (existingUserWithSamePhoneNumber != null) {
			throw new BusinessException("User already exists with given phone number");
		}
	}

	private void validateUserUpdateData(UserDTO user) throws BusinessException {

		String phoneNumber = user.getPhoneNumber();

		if (user.getFirstname() == null)
			throw new BusinessException("Firstname cannot be null");

		if (user.getLastname() == null)
			throw new BusinessException("Lastname cannot be null");

		if (phoneNumber == null)
			throw new BusinessException("Phone number cannot be null");

		if (user.getEmail() == null)
			throw new BusinessException("Email cannot be null");

		if (!phoneNumber.matches("[0-9]+"))
			throw new BusinessException("Phone number can only contain digits from 0 to 9");

		if (phoneNumber.length() != 13)
			throw new BusinessException("Phone number has incorrect number of characters");

		if (phoneNumber.startsWith("0040") == false && phoneNumber.startsWith("0049") == false)
			throw new BusinessException("Phone number must be from Romania or Germany");

		if (user.getEmail().endsWith("@msggroup.com") == false)
			throw new BusinessException("Email must fit the format [address]@msggroup.com");

		User existingUserWithSameEmail = userDAO.findUserByEmail(user.getEmail());
		if (existingUserWithSameEmail != null && !existingUserWithSameEmail.getUsername().equals(user.getUsername())) {
			throw new BusinessException("User already exists with given email");
		}

		User existingUserWithSamePhoneNumber = userDAO.findUserByPhoneNumber(user.getPhoneNumber());
		if (existingUserWithSamePhoneNumber != null
				&& !existingUserWithSameEmail.getUsername().equals(user.getUsername())) {
			throw new BusinessException("User already exists with given phone number");
		}
	}

	private String generateUsername(UserDTO user) throws BusinessException {

		StringBuilder firstname = new StringBuilder(user.getFirstname());
		StringBuilder lastname = new StringBuilder(user.getLastname());

		if (firstname.length() + lastname.length() < 6)
			throw new BusinessException("Cannot create username, firstname and lastname are too short");

		int lnameCount = 5;

		if (lastname.length() < 5)
			lnameCount = lastname.length();

		User existingUserWithSameUsername = new User();
		StringBuilder username = new StringBuilder();

		do {
			username = new StringBuilder(lastname.subSequence(0, lnameCount));
			username.append(firstname.subSequence(0, User.USERNAME_LENGTH - lnameCount));

			existingUserWithSameUsername = userDAO.findUserByUsername(username.toString());

			lnameCount--;
		} while (existingUserWithSameUsername != null && lnameCount >= 0
				&& User.USERNAME_LENGTH - lnameCount <= firstname.length());

		if (existingUserWithSameUsername != null)
			throw new BusinessException("Cannot generate username, all possible usernames are taken");

		return username.toString();
	}
}
