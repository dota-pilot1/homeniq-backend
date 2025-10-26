-- Users 테이블 (주민 정보 포함)
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 ID',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT '이메일',
    password VARCHAR(255) NOT NULL COMMENT '비밀번호 (암호화)',
    name VARCHAR(50) NOT NULL COMMENT '이름',
    dong VARCHAR(10) COMMENT '동',
    ho VARCHAR(10) COMMENT '호',
    phone VARCHAR(20) COMMENT '전화번호',
    move_in_date DATE COMMENT '입주일',
    family_count INT DEFAULT 1 COMMENT '가족 구성원 수',
    vehicle_count INT DEFAULT 0 COMMENT '차량 대수',
    status VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '상태: ACTIVE, MOVED_OUT',
    notes TEXT COMMENT '비고',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    INDEX idx_email (email),
    UNIQUE KEY unique_residence (dong, ho)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='사용자 (주민)';
