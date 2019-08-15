package com.codejcd.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.codejcd.service.CustumUserDetailService;

/**
 * Oauth2 Authorization Server Configuration
 * @author Jeon
 *
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
		@Autowired
		private CustumUserDetailService custumUserDetailService;  
		
		@Autowired
		private AuthenticationManager authenticationManager; 
		
		@Autowired
	    private DataSource dataSource;
		
		@Autowired
		private PasswordEncoder passwordEncoder; // DI가 없는 이유는 DB 저장시 Prefix를 사용하여 다양한 암호화 방식을 지원한다. ex) {bcrypt}
		
		@Value("${security.oauth2.signkey}")
		private String signKey;
	    
	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients.jdbc(dataSource) // Client 인증 시 사용할 datasoruce 설정
	        	   .passwordEncoder(passwordEncoder); // 패스워드 인코딩 섷정

	    }	
	    
	    /** 
	    * 인증과 토큰 Endpoint 설정
	    *  유저 토큰 인증 설정 및 토큰에 대해 DB 사용
	    */
	    /*@Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
	    	 endpoints.tokenStore(new JdbcTokenStore(dataSource)) // 토큰 DB 설정 
	    	 .userDetailsService(custumUserDetailService) // 유저 인증 관리자 및 서비스
	    	 .authenticationManager(authenticationManager);  
	    }*/
	    
	    /**
	     *  인증과 토큰 Endpoint 설정
	     *  JWT와 유저 토큰 인증 설정
	     */
	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	    	super.configure(endpoints);
	    	endpoints
	    	.userDetailsService(custumUserDetailService) // 유저 인증 관리자 및 서비스
	    	.authenticationManager(authenticationManager)
	    	.accessTokenConverter(jwtAccessTokenConverter()); // Json Web Token 변환 설정
	    }
	    
	    /**
	     * Json Web Token 설정
	     * @return
	     */
	    @Bean
	    public JwtAccessTokenConverter jwtAccessTokenConverter() {
	    	JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	    	converter.setSigningKey(signKey); // 사인 키 설정
	    	return converter;
	    }
	    
	    /**
	     * 토큰 보안 제약
	     */
	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	    	 security.tokenKeyAccess("permitAll()")
	            .checkTokenAccess("isAuthenticated()");
	           // .allowFormAuthenticationForClients();
	    }
	    

	}
