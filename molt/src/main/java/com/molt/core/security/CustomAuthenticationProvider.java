package com.molt.core.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {  

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) 
			throws AuthenticationException {
		
		
		Object salt = null;

		if (this.getSaltSource() != null) {
			salt = this.getSaltSource().getSalt(userDetails);
		}
		
		if (authentication.getCredentials() == null) {
			throw new BadCredentialsException("Bad credentials");
		}
		
		CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails)authentication.getDetails();
		String saveYn = details.getSaveYn();
		
		String presentedPassword = authentication.getCredentials().toString();
		
		// 비밀번호 체크
		if(!userDetails.getPassword().equals(presentedPassword)){
			// 패스워드가 틀렸다면
 			throw new BadCredentialsException("아이디나 비밀번호를 잘못 입력하였습니다.");
		
		}else {
			
			// 패스워드가 맞다면
			logger.debug("additionalAuthenticationChecks: 비밀번호가 일치함");
		}
	}
}

