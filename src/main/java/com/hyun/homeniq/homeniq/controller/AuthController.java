package com.hyun.homeniq.homeniq.controller;

import com.hyun.homeniq.homeniq.model.dto.request.LoginRequest;
import com.hyun.homeniq.homeniq.model.dto.request.RegisterRequest;
import com.hyun.homeniq.homeniq.model.dto.response.AuthResponse;
import com.hyun.homeniq.homeniq.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 인증 컨트롤러
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        log.info("로그인 요청: {}", request.getEmail());
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 회원가입
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        log.info("회원가입 요청: {}", request.getEmail());
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        log.info("로그아웃 요청");
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    /**
     * 현재 사용자 정보 조회
     */
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser() {
        // TODO: JWT 토큰에서 사용자 ID 추출하여 조회
        log.info("현재 사용자 조회 요청");
        Map<String, Object> user = new HashMap<>();
        user.put("id", 1);
        user.put("email", "test@example.com");
        user.put("name", "테스트 사용자");

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);

        return ResponseEntity.ok(response);
    }
}
