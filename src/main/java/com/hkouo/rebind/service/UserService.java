package com.hkouo.rebind.service;

import com.hkouo.rebind.mapper.UserMapper;
import com.hkouo.rebind.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean isUserIdDuplicate(String userId) {
        return userMapper.isUserIdDuplicate(userId) > 0;
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUniqueName(generateUniqueName());

        try {
            userMapper.insertUser(user);

            Map<String, Object> roleMap = new HashMap<>();
            roleMap.put("userIdx", user.getIdx());
            roleMap.put("role", "USER");
            userMapper.insertUserRole(roleMap);

        } catch (DataIntegrityViolationException e) {
            String cause = e.getRootCause() != null ? e.getRootCause().getMessage() : "";

            if (cause.contains("uq_users_userid")) {
                throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
            } else if (cause.contains("uq_users_email")) {
                throw new IllegalArgumentException("이미 등록된 이메일입니다.");
            } else {
                throw new IllegalArgumentException("회원가입에 실패했습니다.");
            }
        }
    }



    private String generateUniqueName() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        return "" + letters.charAt(rand.nextInt(26)) + letters.charAt(rand.nextInt(26)) +
                String.format("%05d", rand.nextInt(100000));
    }
}


