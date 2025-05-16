package com.example.banking.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendWelcomeEmail(String owner) {
        // 실제 이메일을 외부 SMTP 서버로 전송
        // 실제 계정 정보 account에서 owner를 찾아 email을 얻어 로직을 실행함
        // 이 로직은 실제로는 느리고 실패할 수 있음
    }

}
