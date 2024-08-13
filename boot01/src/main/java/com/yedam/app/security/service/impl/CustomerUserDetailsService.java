package com.yedam.app.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yedam.app.security.mapper.UserMapper;
import com.yedam.app.security.service.LoginUserVO;
import com.yedam.app.security.service.UserVO;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
	// UserDetailsService 유저가 있는지 없는지 확인 하는 첫번 째 단계

	private UserMapper userMapper;

	@Autowired
	CustomerUserDetailsService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loadUserByUsername 실제로 존재하는지 확인 해준다
		// Mapper를 활용 하여 DB 에 접근
		UserVO userVO = userMapper.getUserInfo(username);

		if (userVO == null) {
			throw new UsernameNotFoundException(username);
		}
		return new LoginUserVO(userVO); // userVO 를 감싸는 형태로 보낸다
	}

}
