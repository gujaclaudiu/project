package edu.msg.ro.business.DAO;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import edu.msg.ro.persistence.entity.User;

/**
 * DAO for {@link User} entity.
 * 
 * @author Andrei Floricel, msg systems ag
 *
 */
@Stateless
public class UserDAO extends AbstractDao<User> {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	public User findUserByEmail(String email) {
		TypedQuery<User> query = this.em.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class);
		query.setParameter("email", email);

		return getSingleResult(query);
	}

	public User findUserByPhoneNumber(String phoneNumber) {
		TypedQuery<User> query = this.em.createNamedQuery(User.FIND_USER_BY_PHONENUMBER, User.class);
		query.setParameter("phoneNumber", phoneNumber);

		return getSingleResult(query);
	}

	public User findUserByUsername(String username) {
		TypedQuery<User> query = this.em.createNamedQuery(User.FIND_USER_BY_USERNAME, User.class);
		query.setParameter("username", username);

		return getSingleResult(query);
	}

}
