package com.codejcd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.codejcd.service.CustumUserDetailService;

/**
 * AuthenticationProvider Impl
 * @author Jeon
 *
 */
@Component
public class CustomAuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
	
	 @Autowired
	 private PasswordEncoder passwordEncoder; 
	 
	 @Autowired
	 private CustumUserDetailService custumUserDetailService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String userId = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserDetails userDetail = custumUserDetailService.loadUserByUsername(userId);
	
		if(null == userDetail) {
			throw new BadCredentialsException("user is null");
		}
		
		if (!passwordEncoder.matches(password, userDetail.getPassword())) {
			throw new BadCredentialsException("password is invalid");
		}
		
		return new UsernamePasswordAuthenticationToken(userId, password, userDetail.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
