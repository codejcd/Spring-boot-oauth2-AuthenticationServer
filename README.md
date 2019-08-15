# Spring-boot-oauth2-AuthenticationServer
spring-boot-oauth2 password credential

# 내용
스프링 부트 기반 Oauth2 인증 서버

# 개발 환경
Spring Boot 2.1.6 / Maven 4.0.0 / Mybatis 2.1.0 / MySQL 5.6

# Oauth2 인증 방식 
Authorization Code, Implicit, password credential, client credential 중 __password credential__ 사용

# 테스트
##### POSTMAN 같은 API TEST 가능한 툴로 아래의 요건으로 호출.
1. token 발급
* URI : http://localhost:8093/oauth/token 
* HTTP METHOD : POST
* HTTP HEADER : Authorization Basic
  * username : Client ID
  * password : Client Secret key
* BODY PARAM  : 
  * client_id : Client ID
  * grant_type : Password
  * username : User ID
  * password : Password
 
2. refresh token 발급
* URI : http://localhost:8093/oauth/token 
* HTTP METHOD : POST 
* HTTP HEADER : Authorization Basic
** username : Client ID
** password : Client Secret key
* BODY PARAM  : 
** grant_type : Password
** refresh_token : token
  
# DB 스키마
아래는 Spring boot Oauth2 DB Scheme 입니다.
그대로 MySQL 로 생성시에는 몇가지의 문제점이 있습니다.
일단 PK 가되는 컬럼의 길이 문제 때문에 그대로 생성할수 없습니다.\
그리고 기본적으로 MySQL은 RDBMS 입니다. 상황에 따라 grant_type 의 경우 
DB 테이블에 passwod,refresh_token 값이 원자값이 들어가지 않을수도 있습니다.
적절하게 테이블 구조를 고칠 필요가 있습니다.
이 프로그램에 사용된 DB 스키마 파일은 mysql_db_scheme.sql을 참고해주세요.
 
 https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
 
 
 
 
  
