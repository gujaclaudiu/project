package edu.msg.ro.business.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import edu.msg.ro.business.DTO.UserDTO;
import edu.msg.ro.business.exception.BusinessException;
import edu.msg.ro.business.service.UserService;

/**
 * Boundary for user component.
 * 
 * @author floricea
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserFacade {

	@EJB
	private UserService userSomething;

	public UserDTO createUser(UserDTO user) throws BusinessException {
		return userSomething.createUser(user);
	}

	public UserDTO updateUser(UserDTO user) throws BusinessException {
		return userSomething.updateUser(user);
	}

	public UserDTO deleteUser(UserDTO user) throws BusinessException {
		return userSomething.deleteUser(user);
	}

}
