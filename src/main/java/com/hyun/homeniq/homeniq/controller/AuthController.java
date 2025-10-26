package com.hyun.homeniq.homeniq.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        // TODO: 실제 인증 로직 구현
        // 현재는 테스트용으로 항상 성공 응답

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> user = new HashMap<>();
        user.put("id", 1);
        user.put("email", email);
        user.put("name", "테스트 사용자");

        response.put("user", user);
        response.put("token", "test-token-12345");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> registerRequest) {
        String email = registerRequest.get("email");
        String name = registerRequest.get("name");
        String password = registerRequest.get("password");

        // TODO: 실제 회원가입 로직 구현

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> user = new HashMap<>();
        user.put("id", 1);
        user.put("email", email);
        user.put("name", name);

        response.put("user", user);
        response.put("token", "test-token-12345");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser() {
        // TODO: 실제 사용자 정보 조회 로직 구현
        Map<String, Object> user = new HashMap<>();
        user.put("id", 1);
        user.put("email", "test@example.com");
        user.put("name", "테스트 사용자");

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);

        return ResponseEntity.ok(response);
    }
}
