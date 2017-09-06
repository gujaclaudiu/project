package edu.msg.ro.business.DTO.mapper;

import javax.enterprise.context.Dependent;

import edu.msg.ro.business.DTO.UserDTO;
import edu.msg.ro.persistence.entity.User;

/**
 * Mapper for {@link User} and {@link UserDTO}.
 * 
 * @author Andrei Floricel, msg systems ag
 *
 */
@Dependent
public class UserDTOMapper extends AbstractDTOMapper<User, UserDTO> {

	@Override
	public UserDTO getDTOInstance() {
		return new UserDTO();
	}

	@Override
	protected void mapEntityToDTOFields(User entity, UserDTO dto) {
		dto.setEmail(entity.getEmail());
		dto.setFirstname(entity.getFirstname());
		dto.setLastname(entity.getLastname());
		dto.setPassword(entity.getPassword());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setUsername(entity.getUsername());
		dto.setActive(entity.isActive());
	}

	@Override
	protected void mapDTOToEntityFields(UserDTO dto, User entity) {
		entity.setEmail(dto.getEmail());
		entity.setFirstname(dto.getFirstname());
		entity.setLastname(dto.getLastname());
		entity.setPassword(dto.getPassword());
		entity.setLockVersion(dto.getLockVersion());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setUsername(dto.getUsername());
		entity.setActive(dto.isActive());
	}

}
