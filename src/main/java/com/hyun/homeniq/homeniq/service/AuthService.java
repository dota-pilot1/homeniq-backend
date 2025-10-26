package com.hyun.homeniq.homeniq.service;

import com.hyun.homeniq.homeniq.model.dto.request.LoginRequest;
import com.hyun.homeniq.homeniq.model.dto.request.RegisterRequest;
import com.hyun.homeniq.homeniq.model.dto.response.AuthResponse;

/**
 * 인증 서비스 인터페이스
 */
public interface AuthService {

    /**
     * 회원가입
     */
    AuthResponse register(RegisterRequest request);

    /**
     * 로그인
     */
    AuthResponse login(LoginRequest request);

    /**
     * 이메일 중복 체크
     */
    boolean isEmailExists(String email);
}
