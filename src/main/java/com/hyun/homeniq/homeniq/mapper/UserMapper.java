package com.hyun.homeniq.homeniq.mapper;

import com.hyun.homeniq.homeniq.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 사용자 MyBatis Mapper 인터페이스
 */
@Mapper
public interface UserMapper {

    /**
     * 이메일로 사용자 조회
     */
    User findByEmail(@Param("email") String email);

    /**
     * ID로 사용자 조회
     */
    User findById(@Param("id") Long id);

    /**
     * 사용자 등록
     */
    int insert(User user);

    /**
     * 사용자 정보 수정
     */
    int update(User user);

    /**
     * 사용자 삭제
     */
    int delete(@Param("id") Long id);

    /**
     * 이메일 중복 체크
     */
    int countByEmail(@Param("email") String email);
}
