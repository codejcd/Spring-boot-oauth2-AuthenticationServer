# Spring-boot-oauth2-AuthenticationServer
spring-boot-oauth2 password credential

# 내용
스프링 부트 기반 Oauth2 인증 서버

# 개발 환경
Spring Boot 2.1.6 / Maven 4.0.0 / Mybatis 2.1.0 / MySQL 5.6

# Oauth2 인증 방식 
Authorization Code, Implicit, password credential, client credential 중 __password credential__ 사용

# 테스트
POSTMAN 같은 API TEST 가능한 툴로 아래의 요건으로 호출.

1. token 발급
 1) URI : http://localhost:8093/oauth/token 
 2) HTTP METHOD : POST
 3) HTTP HEADER : Authorization Basic
  - username : Client ID
  - password : Client Secret key
 4) BODY PARAM  : 
  - client_id : Client ID
  - grant_type : Password
  - username : User ID
  - password : Password
  
2. refresh token 발급
 1) URI : http://localhost:8093/oauth/token 
 2) HTTP METHOD : POST 
 3) HTTP HEADER : Authorization Basic
  - username : Client ID
  - password : Client Secret key
 4) BODY PARAM  : 
  - grant_type : Password
  - refresh_token : token
  
  
