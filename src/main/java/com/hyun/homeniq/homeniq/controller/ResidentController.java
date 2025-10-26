package com.hyun.homeniq.homeniq.controller;

import com.hyun.homeniq.homeniq.mapper.UserMapper;
import com.hyun.homeniq.homeniq.model.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "주민 관리 API", description = "아파트 주민 정보 조회 및 관리")
@RestController
@RequestMapping("/api/residents")
@RequiredArgsConstructor
public class ResidentController {

    private final UserMapper userMapper;

    @Operation(summary = "전체 주민 목록 조회", description = "등록된 모든 주민 정보를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<User>> getAllResidents() {
        List<User> residents = userMapper.findAll();
        return ResponseEntity.ok(residents);
    }

    @Operation(summary = "주민 상세 정보 조회", description = "ID로 특정 주민의 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<User> getResident(@PathVariable Long id) {
        User resident = userMapper.findById(id);
        if (resident == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resident);
    }

    @Operation(summary = "동/호로 주민 조회", description = "동과 호수로 주민을 조회합니다.")
    @GetMapping("/search")
    public ResponseEntity<User> getResidentByDongHo(
            @RequestParam String dong,
            @RequestParam String ho) {
        User resident = userMapper.findByDongAndHo(dong, ho);
        if (resident == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resident);
    }

    @Operation(summary = "주민 정보 수정", description = "주민의 상세 정보를 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateResident(
            @PathVariable Long id,
            @RequestBody User user) {
        user.setId(id);
        int updated = userMapper.update(user);

        Map<String, Object> response = new HashMap<>();
        if (updated > 0) {
            response.put("success", true);
            response.put("message", "주민 정보가 수정되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "주민 정보 수정에 실패했습니다.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(summary = "주민 삭제", description = "주민 정보를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteResident(@PathVariable Long id) {
        int deleted = userMapper.delete(id);

        Map<String, Object> response = new HashMap<>();
        if (deleted > 0) {
            response.put("success", true);
            response.put("message", "주민 정보가 삭제되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "주민 정보 삭제에 실패했습니다.");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
