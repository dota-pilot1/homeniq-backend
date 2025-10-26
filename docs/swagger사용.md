# Swagger 사용 가이드

## 1. Swagger란?

**REST API 자동 문서화 도구**
- Controller 코드만으로 API 문서 자동 생성
- 웹 브라우저에서 API 테스트 가능
- Postman 없이도 API 호출 가능
- 프론트엔드와 백엔드 협업 용이

**SpringDoc OpenAPI 사용**
- Spring Boot 3.x 지원
- 기존 Springfox 대체 (더 이상 업데이트 안 됨)
- OpenAPI 3.0 스펙 준수

---

## 2. Swagger 적용 완료!

### (1) 의존성 추가
```gradle
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0'
```

### (2) Spring Security 설정
```java
// SecurityConfig.java에 추가됨
.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
```

### (3) Swagger 설정 클래스
```java
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("HomeniQ API")
                .version("1.0.0")
                .description("HomeniQ 쇼핑몰 API 문서"));
    }
}
```

### (4) Controller 애노테이션
```java
@Tag(name = "인증 API", description = "로그인, 회원가입 관련 API")
@RestController
public class AuthController {

    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(...) { }
}
```

---

## 3. Swagger UI 접속

### URL
```
http://localhost:9090/swagger-ui/index.html
```

또는 짧게:
```
http://localhost:9090/swagger-ui.html
```

### API Docs (JSON)
```
http://localhost:9090/v3/api-docs
```

---

## 4. Swagger UI 사용법

### (1) API 목록 확인
1. Swagger UI 접속
2. **인증 API** 섹션 확장
3. 4개의 API 확인:
   - `POST /api/auth/login` - 로그인
   - `POST /api/auth/register` - 회원가입
   - `POST /api/auth/logout` - 로그아웃
   - `GET /api/auth/me` - 현재 사용자 조회

### (2) API 테스트 (회원가입 예제)
1. **POST /api/auth/register** 클릭
2. **Try it out** 버튼 클릭
3. Request body에 JSON 입력:
```json
{
  "email": "test@daum.net",
  "password": "1234",
  "name": "홍길동"
}
```
4. **Execute** 버튼 클릭
5. **Response** 섹션에서 결과 확인:
```json
{
  "status": "success",
  "user": {
    "id": 1,
    "email": "test@daum.net",
    "name": "홍길동"
  },
  "token": "temp-token-1-1234567890"
}
```

### (3) API 테스트 (로그인 예제)
1. **POST /api/auth/login** 클릭
2. **Try it out** 버튼 클릭
3. Request body:
```json
{
  "email": "test@daum.net",
  "password": "1234"
}
```
4. **Execute** 클릭
5. 응답 확인

---

## 5. 주요 Swagger 애노테이션

### (1) 컨트롤러 레벨
```java
@Tag(name = "태그명", description = "태그 설명")
@RestController
public class MyController { }
```

### (2) 메서드 레벨
```java
@Operation(summary = "API 요약", description = "API 상세 설명")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "성공"),
    @ApiResponse(responseCode = "400", description = "실패")
})
@PostMapping("/endpoint")
public ResponseEntity<?> method() { }
```

### (3) 파라미터 설명
```java
@Operation(summary = "사용자 조회")
@GetMapping("/users/{id}")
public User getUser(
    @Parameter(description = "사용자 ID") @PathVariable Long id
) { }
```

### (4) 요청/응답 예제
```java
@Schema(description = "로그인 요청")
public class LoginRequest {
    @Schema(description = "이메일", example = "test@example.com")
    private String email;

    @Schema(description = "비밀번호", example = "password123")
    private String password;
}
```

---

## 6. DTO에 Swagger 적용 (선택)

### LoginRequest.java (예제)
```java
@Schema(description = "로그인 요청 DTO")
public class LoginRequest {

    @Schema(description = "이메일 주소", example = "test@daum.net", required = true)
    private String email;

    @Schema(description = "비밀번호", example = "1234", required = true)
    private String password;

    // getter, setter...
}
```

### AuthResponse.java (예제)
```java
@Schema(description = "인증 응답 DTO")
public class AuthResponse {

    @Schema(description = "응답 상태", example = "success")
    private String status;

    @Schema(description = "사용자 정보")
    private UserResponse user;

    @Schema(description = "JWT 토큰", example = "eyJhbGciOiJIUzI1...")
    private String token;

    // getter, setter...
}
```

---

## 7. Swagger vs Postman

| 기능 | Swagger | Postman |
|------|---------|---------|
| **문서 자동 생성** | ✅ 자동 | ❌ 수동 |
| **API 테스트** | ✅ 가능 | ✅ 가능 |
| **요청/응답 예제** | ✅ 자동 | ❌ 수동 |
| **팀 협업** | ✅ URL 공유만 | △ 계정 필요 |
| **복잡한 테스트** | △ 단순 | ✅ 강력 |
| **설정** | ✅ 코드에 애노테이션 | ❌ 별도 작성 |

**결론:**
- **개발 중**: Swagger (빠른 테스트, 문서 자동 생성)
- **복잡한 시나리오**: Postman (환경변수, 스크립트 등)

---

## 8. 프론트엔드와 협업

### Before Swagger
```
프론트: "로그인 API 어떻게 호출하나요?"
백엔드: "POST /api/auth/login에 이메일, 비밀번호 보내세요"
프론트: "응답은요?"
백엔드: "status, user, token 들어있어요"
프론트: "user 안에 뭐 들어있어요?"
백엔드: "id, email, name이요..."
```

### After Swagger
```
백엔드: "http://localhost:9090/swagger-ui.html 보세요"
프론트: (접속) → 모든 API 확인 → 바로 테스트 가능
```

---

## 9. 실전 팁

### (1) 백엔드 재시작 후 확인
```bash
cd homeniq-backend
./gradlew bootRun
```

브라우저에서:
```
http://localhost:9090/swagger-ui.html
```

### (2) API 그룹화
```java
@Tag(name = "인증 API")  // 인증 관련
@Tag(name = "상품 API")  // 상품 관련
@Tag(name = "주문 API")  // 주문 관련
```

### (3) 민감 정보 숨기기
```java
@Schema(description = "비밀번호", accessMode = AccessMode.WRITE_ONLY)
private String password;  // 응답에는 노출 안 됨
```

### (4) Deprecated API 표시
```java
@Deprecated
@Operation(summary = "구버전 로그인 (사용 중단)")
@PostMapping("/old-login")
public void oldLogin() { }
```

---

## 10. 트러블슈팅

### 문제 1: Swagger UI 접속 안 됨
**원인:** Spring Security가 막고 있음

**해결:** SecurityConfig에 추가 (이미 완료됨)
```java
.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
```

### 문제 2: API가 안 보임
**원인:** Controller 경로 인식 못함

**해결:** SwaggerConfig에서 경로 지정
```java
@Bean
public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
        .group("public")
        .pathsToMatch("/api/**")
        .build();
}
```

### 문제 3: 한글 깨짐
**원인:** 인코딩 문제

**해결:** application.properties 추가
```properties
server.servlet.encoding.charset=UTF-8
server.servlet.encoding.force=true
```

---

## 11. 운영 환경 설정

### 운영에서 Swagger 비활성화
```properties
# application-prod.properties
springdoc.swagger-ui.enabled=false
springdoc.api-docs.enabled=false
```

또는 SecurityConfig에서:
```java
if (!"prod".equals(environment)) {
    .requestMatchers("/swagger-ui/**").permitAll()
}
```

---

## 12. 현재 프로젝트 API 문서

### 인증 API

#### 1. POST /api/auth/register
**설명:** 회원가입
**요청:**
```json
{
  "email": "test@daum.net",
  "password": "1234",
  "name": "홍길동"
}
```
**응답:**
```json
{
  "status": "success",
  "user": {
    "id": 1,
    "email": "test@daum.net",
    "name": "홍길동"
  },
  "token": "temp-token-1-..."
}
```

#### 2. POST /api/auth/login
**설명:** 로그인
**요청:**
```json
{
  "email": "test@daum.net",
  "password": "1234"
}
```
**응답:** 회원가입과 동일

#### 3. POST /api/auth/logout
**설명:** 로그아웃
**요청:** 없음
**응답:**
```json
{
  "status": "success"
}
```

#### 4. GET /api/auth/me
**설명:** 현재 사용자 조회
**요청:** 없음
**응답:**
```json
{
  "user": {
    "id": 1,
    "email": "test@example.com",
    "name": "테스트 사용자"
  }
}
```

---

## 13. 다음 단계

### (1) DTO에 @Schema 추가 (선택)
- 더 상세한 API 문서 제공
- 예제 값 자동 생성

### (2) API 그룹화
- 인증, 상품, 주문 등으로 분리

### (3) JWT 인증 추가 시
```java
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
```

---

## 14. 결론

**Swagger 적용 완료!**

✅ 설정 완료
✅ 보안 설정 완료
✅ Controller 애노테이션 추가
✅ API 문서 자동 생성

**접속 URL:**
```
http://localhost:9090/swagger-ui.html
```

**장점:**
- API 문서 자동 생성
- 웹에서 바로 테스트 가능
- 프론트엔드와 협업 편리
- Postman 불필요

**다음 작업:**
- 백엔드 재시작
- Swagger UI 확인
- API 테스트 진행
