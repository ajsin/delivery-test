package com.delivery.api.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

@Slf4j
public class CommonUtil {


    /**
     * 유저 idx 생성
     * @return 생성된 유저idx
     */
    public static String createUid() {
        return "UID"+System.currentTimeMillis() + RandomStringUtils.randomAlphanumeric(5);
    }

    /**
     * 비밀번호 조건 validate (3가지 조건 이상)
     * @param pw 비밀번호
     * @return 조건 통과 여부
     */
    public static boolean validatePw(String pw) {

        // 길이 12자 이상 30자 미만
        {
            if(!Pattern.compile(".{12,30}").matcher(pw).find()) {
                log.info("12자 미만");
                return false;
            }
        }

        int found = 0;

        // 대문자 포함여부
        {
            if(Pattern.compile("(?=.*?[A-Z])").matcher(pw).find()) {
                found += 1;
                log.info("대문자 포함");
            }
        }
        // 소문자 포함여부
        {
            if(Pattern.compile("(?=.*?[a-z])").matcher(pw).find()) {
                found += 1;
                log.info("소문자 포함");
            }
        }
        // 숫자 포함여부
        {
            if(Pattern.compile("(?=.*?[0-9])").matcher(pw).find()) {
                found += 1;
                log.info("숫자 포함");
            }
        }
        // 특수문자 포함여부
        {
            if(Pattern.compile("(?=.*?[#?!@$%^&*-])").matcher(pw).find()) {
                found += 1;
                log.info("특수문자 포함");
            }
        }

        return found >= 3;
    }

    public static String encryptPw(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
