# DBeaver 사용 가이드

## 1. DBeaver란?

**무료 오픈소스 데이터베이스 관리 도구**
- 모든 주요 DB 지원 (MySQL, PostgreSQL, Oracle, SQL Server 등)
- GUI 기반으로 쉽게 DB 조회/수정
- SQL 실행, 테이블 생성, 데이터 조작 가능
- ER 다이어그램 자동 생성
- 데이터 Import/Export 기능

**대안 도구:**
- MySQL Workbench (MySQL 전용)
- DataGrip (JetBrains 유료)
- TablePlus (macOS, 유료)
- HeidiSQL (Windows 무료)

---

## 2. DBeaver 설치

### macOS
```bash
brew install --cask dbeaver-community
```

### Windows
https://dbeaver.io/download/ 에서 다운로드

### Linux
```bash
sudo snap install dbeaver-ce
```

---

## 3. 데이터베이스 연결 (MySQL)

### (1) 새 연결 생성

1. **DBeaver 실행**
2. **메뉴**: Database → New Database Connection
3. **DB 선택**: MySQL 클릭 → Next
4. **연결 정보 입력**:

```
Server Host:   localhost
Port:          3306
Database:      homeniq
Username:      homeniq
Password:      homeniq1234
```

5. **Test Connection** 클릭
   - MySQL Driver 자동 다운로드
   - "Connected" 메시지 확인

6. **Finish** 클릭

### (2) Docker MySQL 연결 시

Docker 컨테이너 포트를 호스트에 바인딩했다면:
```
Host: localhost
Port: 3306
```

별도 설정 불필요 (Docker가 이미 3306 포트 노출)

---

## 4. 기본 사용법

### (1) 데이터베이스 탐색

**좌측 패널:**
```
homeniq
├── Tables               # 테이블 목록
│   └── users           # users 테이블
├── Views               # 뷰
├── Stored Procedures   # 저장 프로시저
└── Functions           # 함수
```

**테이블 더블클릭:**
- 데이터 조회 화면 열림
- 데이터 직접 수정 가능

### (2) SQL 쿼리 실행

**방법 1: SQL 에디터 사용**
1. 데이터베이스 우클릭 → SQL Editor → New SQL Script
2. 쿼리 작성:
```sql
SELECT * FROM users;
```
3. 실행:
   - `Ctrl+Enter` (Windows/Linux)
   - `Cmd+Enter` (macOS)
   - 또는 실행 버튼 클릭

**방법 2: SQL Console**
1. 데이터베이스 우클릭 → SQL Editor → Open SQL Console
2. 쿼리 작성 및 실행

### (3) 데이터 조회

```sql
-- 전체 데이터 조회
SELECT * FROM users;

-- 조건 조회
SELECT * FROM users WHERE email = 'test@example.com';

-- 정렬
SELECT * FROM users ORDER BY created_at DESC;

-- 개수 확인
SELECT COUNT(*) FROM users;
```

### (4) 데이터 추가

```sql
INSERT INTO users (email, password, name)
VALUES ('test@daum.net', 'encrypted_password', '홍길동');
```

### (5) 데이터 수정

```sql
UPDATE users
SET name = '김철수'
WHERE email = 'test@daum.net';
```

### (6) 데이터 삭제

```sql
DELETE FROM users
WHERE email = 'test@daum.net';
```

---

## 5. 테이블 생성 (GUI)

### 방법 1: SQL 스크립트
```sql
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### 방법 2: GUI 사용
1. Tables 우클릭 → Create New Table
2. 테이블명 입력: `products`
3. 컬럼 추가:
   - `id` / BIGINT / Primary Key / Auto Increment
   - `name` / VARCHAR(100) / Not Null
   - `price` / INT / Not Null
   - `created_at` / TIMESTAMP / Default: CURRENT_TIMESTAMP
4. Save 또는 Persist 클릭

---

## 6. 테이블 구조 확인

### SQL로 확인
```sql
DESC users;
-- 또는
SHOW CREATE TABLE users;
```

### GUI로 확인
1. 테이블 우클릭 → View Table
2. 탭 선택:
   - **Data**: 데이터 조회/수정
   - **Properties**: 테이블 정보
   - **Columns**: 컬럼 목록 및 타입
   - **Constraints**: Primary Key, Foreign Key
   - **Indexes**: 인덱스 목록
   - **ER Diagram**: 테이블 관계도

---

## 7. ER 다이어그램 생성

**전체 DB ER 다이어그램:**
1. 데이터베이스 우클릭 → View Diagram
2. 모든 테이블 관계가 시각화됨

**특정 테이블만:**
1. 테이블 선택 (Ctrl+클릭으로 다중 선택)
2. 우클릭 → View Diagram
3. 선택한 테이블만 표시

**다이어그램 저장:**
- File → Save Diagram
- 이미지로 Export 가능 (PNG, SVG)

---

## 8. 데이터 Import/Export

### (1) CSV로 Export
1. 테이블 우클릭 → Export Data
2. CSV 선택 → Next
3. 저장 위치 선택 → Finish

### (2) CSV로 Import
1. 테이블 우클릭 → Import Data
2. CSV 선택 → Next
3. 파일 선택 → 컬럼 매핑 확인 → Finish

### (3) SQL 스크립트로 Export
1. 테이블 우클릭 → Generate SQL → DDL
2. CREATE TABLE 문이 생성됨
3. 복사하여 사용

---

## 9. 유용한 기능

### (1) 자동완성
- SQL 작성 중 `Ctrl+Space`로 테이블명/컬럼명 자동완성

### (2) 결과 데이터 직접 수정
- SELECT 결과에서 셀 더블클릭 → 값 수정 → Save

### (3) 쿼리 히스토리
- 좌측 패널: SQL History
- 이전에 실행한 모든 쿼리 확인 가능

### (4) 북마크
- 자주 사용하는 쿼리를 북마크로 저장
- 우클릭 → Add to Bookmarks

### (5) 데이터 필터링
- 데이터 조회 화면에서 필터 아이콘 클릭
- GUI로 WHERE 조건 생성

---

## 10. Docker MySQL 연결 트러블슈팅

### 문제 1: Connection refused
**원인:** Docker 컨테이너가 실행되지 않음

**해결:**
```bash
docker ps | grep mysql
# 실행 중이 아니면:
docker start homeniq-mysql
```

### 문제 2: Access denied
**원인:** 비밀번호 또는 사용자명 오류

**해결:**
- application.properties 확인
```properties
spring.datasource.username=homeniq
spring.datasource.password=homeniq1234
```

### 문제 3: Database 'homeniq' doesn't exist
**원인:** DB가 생성되지 않음

**해결:**
```bash
docker exec -it homeniq-mysql mysql -u root -p
CREATE DATABASE homeniq;
GRANT ALL PRIVILEGES ON homeniq.* TO 'homeniq'@'%';
FLUSH PRIVILEGES;
```

---

## 11. 프로젝트에서 자주 사용하는 쿼리

### (1) users 테이블 데이터 확인
```sql
SELECT id, email, name, created_at
FROM users
ORDER BY created_at DESC;
```

### (2) 최근 가입자 확인
```sql
SELECT *
FROM users
WHERE created_at >= DATE_SUB(NOW(), INTERVAL 7 DAY)
ORDER BY created_at DESC;
```

### (3) 이메일 중복 체크
```sql
SELECT email, COUNT(*) as count
FROM users
GROUP BY email
HAVING count > 1;
```

### (4) 테이블 용량 확인
```sql
SELECT
    table_name AS '테이블명',
    ROUND(((data_length + index_length) / 1024 / 1024), 2) AS 'Size (MB)'
FROM information_schema.TABLES
WHERE table_schema = 'homeniq'
ORDER BY (data_length + index_length) DESC;
```

### (5) 전체 테이블 목록
```sql
SHOW TABLES;
```

### (6) 테이블 구조 확인
```sql
DESC users;
```

---

## 12. DBeaver vs 다른 도구

| 도구 | 장점 | 단점 | 추천 대상 |
|------|------|------|-----------|
| **DBeaver** | 무료, 모든 DB 지원, 기능 풍부 | 무거움 | 모든 개발자 |
| **MySQL Workbench** | MySQL 전용 최적화 | MySQL만 지원 | MySQL 개발자 |
| **DataGrip** | 강력한 기능, IDE 통합 | 유료 | IntelliJ 사용자 |
| **TablePlus** | 가볍고 빠름, 깔끔한 UI | 유료, macOS 중심 | macOS 사용자 |
| **HeidiSQL** | 가볍고 빠름 | Windows만 지원 | Windows 개발자 |

---

## 13. 단축키 (유용한 것만)

| 기능 | Windows/Linux | macOS |
|------|---------------|-------|
| SQL 실행 | `Ctrl+Enter` | `Cmd+Enter` |
| 현재 라인만 실행 | `Ctrl+\` | `Cmd+\` |
| 자동완성 | `Ctrl+Space` | `Ctrl+Space` |
| 포맷팅 (정렬) | `Ctrl+Shift+F` | `Cmd+Shift+F` |
| 주석 토글 | `Ctrl+/` | `Cmd+/` |
| 새 SQL 에디터 | `Ctrl+]` | `Cmd+]` |

---

## 14. 실전 팁

### (1) 쿼리 작성 시
- 항상 세미콜론(`;`)으로 끝내기
- 대문자/소문자 구분 없음 (관례상 SQL 키워드는 대문자)
- 가독성을 위해 포맷팅 사용 (`Ctrl+Shift+F`)

### (2) 데이터 수정 시
- SELECT로 먼저 확인
- WHERE 조건 반드시 확인
- 운영 DB는 절대 직접 수정 금지

### (3) 백업
- 중요한 작업 전 Export로 백업
- 또는 mysqldump 사용

### (4) 성능 최적화
- 인덱스 확인: EXPLAIN SELECT ...
- 느린 쿼리 찾기: DBeaver의 실행 시간 확인

---

## 15. MyBatis와 함께 사용하기

### 개발 프로세스:
1. **DBeaver에서 SQL 작성 및 테스트**
   ```sql
   SELECT * FROM users WHERE email = 'test@example.com';
   ```

2. **정상 작동 확인 후 MyBatis XML에 복사**
   ```xml
   <select id="findByEmail" resultMap="UserResultMap">
       SELECT * FROM users WHERE email = #{email}
   </select>
   ```

3. **파라미터 바인딩 변경**
   - DBeaver: `'test@example.com'` (하드코딩)
   - MyBatis: `#{email}` (파라미터 바인딩)

---

## 16. 결론

**DBeaver를 사용해야 하는 이유:**
- ✅ 무료 오픈소스
- ✅ 모든 DB 지원
- ✅ 직관적인 GUI
- ✅ SQL 작성/테스트 편리
- ✅ ER 다이어그램 자동 생성
- ✅ 데이터 Import/Export 쉬움

**추천 사용법:**
- 개발 환경: DBeaver로 편하게 작업
- SQL 검증: DBeaver에서 먼저 테스트
- MyBatis 작성: 검증된 SQL을 XML에 복사
- ER 다이어그램: DB 구조 파악 및 문서화

**주의사항:**
- 운영 DB는 읽기 전용으로만 연결
- UPDATE/DELETE 전에 반드시 SELECT로 확인
- WHERE 조건 없는 UPDATE/DELETE 절대 금지
