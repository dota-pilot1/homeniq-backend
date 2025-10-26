package com.hyun.homeniq.homeniq.service.impl;

import com.hyun.homeniq.homeniq.mapper.UserMapper;
import com.hyun.homeniq.homeniq.model.dto.request.LoginRequest;
import com.hyun.homeniq.homeniq.model.dto.request.RegisterRequest;
import com.hyun.homeniq.homeniq.model.dto.response.AuthResponse;
import com.hyun.homeniq.homeniq.model.dto.response.UserResponse;
import com.hyun.homeniq.homeniq.model.entity.User;
import com.hyun.homeniq.homeniq.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 인증 서비스 구현체
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입
     */
    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        log.info("회원가입 시도: {}", request.getEmail());

        // 이메일 중복 체크
        if (isEmailExists(request.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        // User 엔티티 생성
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // 비밀번호 암호화

        // DB 저장
        int result = userMapper.insert(user);
        if (result != 1) {
            throw new RuntimeException("회원가입에 실패했습니다.");
        }

        log.info("회원가입 성공: userId={}", user.getId());

        // 응답 생성
        UserResponse userResponse = new UserResponse(user.getId(), user.getEmail(), user.getName());
        return new AuthResponse("success", userResponse, generateToken(user));
    }

    /**
     * 로그인
     */
    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        log.info("로그인 시도: {}", request.getEmail());

        // 사용자 조회
        User user = userMapper.findByEmail(request.getEmail());
        if (user == null) {
            throw new RuntimeException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        log.info("로그인 성공: userId={}", user.getId());

        // 응답 생성
        UserResponse userResponse = new UserResponse(user.getId(), user.getEmail(), user.getName());
        return new AuthResponse("success", userResponse, generateToken(user));
    }

    /**
     * 이메일 중복 체크
     */
    @Override
    public boolean isEmailExists(String email) {
        return userMapper.countByEmail(email) > 0;
    }

    /**
     * JWT 토큰 생성 (임시 구현)
     * TODO: JWT 라이브러리 사용하여 실제 토큰 생성
     */
    private String generateToken(User user) {
        return "temp-token-" + user.getId() + "-" + System.currentTimeMillis();
    }
}
