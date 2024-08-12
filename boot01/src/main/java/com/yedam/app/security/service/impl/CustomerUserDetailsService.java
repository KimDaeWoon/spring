package com.yedam.app.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	private UserMapper userMapper;

	@Autowired
	CustomerUserDetailsService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Mapper를 활용 하여 DB 에 접근
		UserVO userVO = userMapper.getUserInfo(username);

		if (userVO == null) {
			throw new UsernameNotFoundException(username);
		}
		return new LoginUserVO(userVO); // userVO 를 감싸는 형태로 보낸다
	}

}
