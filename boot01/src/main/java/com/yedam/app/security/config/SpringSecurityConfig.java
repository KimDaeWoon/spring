package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	@Bean // 비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 인증 및 인가
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http // Security가 적용될 URI ,, 버전에 따른 표현식 확인
				.authorizeHttpRequests((authrize) -> authrize // requestMatchers 경로에 대해 등록 가능
						.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()// FORWARD 호출 할때 특별 권한 없이 통신 이루어 지게 함
						.requestMatchers("/", "/all").permitAll()//

						.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // hasRole 는 매개값으로 USER이렇게 쓴다
						.requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN") //

						.anyRequest().authenticated()) // authenticated 인가 받은 사람은 누구나 접근 가능
				.formLogin(formlogin -> formlogin.defaultSuccessUrl("/all"))
				.logout(logout -> logout.logoutSuccessUrl("/all").invalidateHttpSession(true));

		return http.build();
	}

//	@Bean // 메모리상 인증 정보 등록 -> test 전용 방식
//	InMemoryUserDetailsManager inMemoryUserDetailsService() {
//		UserDetails user = User.builder()//
//				.username("user1")//
//				.password(passwordEncoder().encode("1234"))//
//				.roles("USER") // ROLE_USER == USER 같은 의미, roles에는 USER 이것만 사용 가능
//				// .authorities("ROLE_USER")
//				.build();
//
//		UserDetails admin = User.builder()// 인스턴스 생성 하면 name, encode, authorities 초기값 설정
//				.username("admin1")//
//				.password(passwordEncoder().encode("1234"))//
//				// .roles("ADMIN") // ROLE_ADMIN == ADMIN 같은 의미
//				.authorities("ROLE_ADMIN", "ROLE_USER")// 권한 부여 ROLE_ADMIN 1개 만 일 수 있고 ROLE_USER 추가 가능
//				.build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}
}
