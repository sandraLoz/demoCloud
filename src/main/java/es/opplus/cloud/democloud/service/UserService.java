package es.opplus.cloud.democloud.service;

import java.util.List;

import es.opplus.cloud.democloud.dto.UserDto;



public interface UserService {

	List<UserDto> findAll();

	UserDto findUserById(Long userId);

	UserDto createUser(UserDto userDto);

	Long deleteUserById(Long userId);

	UserDto updateUserById(Long userId, UserDto user);
}
