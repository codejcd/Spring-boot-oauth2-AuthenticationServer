# spring-security-oauth2-authentication-server

# 내용
스프링 부트 기반 Oauth2 인증 서버

상세 설명은 아래 블로그를 참고해주세요.

https://codejcd.tistory.com/11

# 개발 환경
Spring Boot 2.1.6 / Maven 4.0.0 / Mybatis 2.1.0 / MySQL 5.6

# Oauth2 인증 방식 
Authorization Code, Implicit, password credential, client credential 중 __password credential__ 사용

# 테스트
### POSTMAN 같은 API TEST 가능한 툴로 아래의 요건으로 호출.
1. token 발급
* URI : http://localhost:8093/oauth/token 
* HTTP METHOD : POST
* HTTP HEADER : Authorization Basic
  * username : Client ID
  * password : Client Secret key
* BODY PARAM  : 
  * client_id : Client ID
  * grant_type : password
  * username : User ID
  * password : Password
 
2. refresh token 발급
* URI : http://localhost:8093/oauth/token 
* HTTP METHOD : POST 
* HTTP HEADER : Authorization Basic
  * username : Client ID
  * password : Client Secret key
* BODY PARAM  : 
  * grant_type : refresh_token
  * refresh_token : Refresh Token

3. token 체크
* URI : http://localhost:8093/oauth/check_token 
* HTTP METHOD : POST 
* HTTP HEADER : Authorization Basic
  * username : Client ID
  * password : Client Secret key
* BODY PARAM  : 
  * token : Token
  * refresh_token : Token
  
# DB 스키마
https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
<pre>
위 링크는 Spring boot Oauth2 DB Scheme 입니다.
그대로 MySQL 로 생성 시에는 몇 가지의 문제점이 있습니다.
일단 PK 가되는 컬럼의 길이 문제 때문에 그대로 생성할수 없습니다.
그리고 기본적으로 MySQL은 RDBMS 입니다. 상황에 따라 grant_type 의 경우 
DB 테이블에 passwod,refresh_token 값이 원자값이 들어가지 않을수도 있습니다.
적절하게 테이블 구조를 고칠 필요가 있습니다.
이 프로그램에 사용된 DB 스키마 파일은 src/resource/db/scheme.sql, test_data.sql 을 참고해주세요.
testData의 clientScret 의 값은 암호화 되어 있으며, 원문 값은 testSecret 입니다.
user password 값은 1234 입니다.
테스트 용도로 생성된 값이므로 반드시 해당 값만 사용할 필요는 없습니다.
</pre>
 
