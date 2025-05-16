package com.example.banking.validator;

public class Validator {

    private Validator() {
    }

    /**
     * id는 숫자를 입력받아야 하며, owner의 경우 null이 아니고 1~40자를 입력 받아야 한다
     * @param id
     * @param owner
     */
    public static boolean validateCreateAccount(String id, String owner) {
        if (!id.matches("\\d+")) {
            throw new IllegalArgumentException("id는 숫자로만 구성되어야 합니다.");
        }

        if (owner.isEmpty() || owner.length() > 40) {
            throw new IllegalArgumentException("owner는 1자 이상 40자 이하의 문자열이어야 하며 null일 수 없습니다.");
        }

        return true;
    }
}
