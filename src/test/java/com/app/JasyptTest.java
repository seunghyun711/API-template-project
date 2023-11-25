package com.app;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptTest {
    @Test
    public void jasyptTest(){
        String password = ""; // EditConfiuration -> VM Option을 통해 설정한 Jasypt의 암호
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES"); // 암호화 알고리즘
        String content = ""; // 암호화 할 내용(ex. DB 암호, 각 api secret key...)
        String encryptedContent = encryptor.encrypt(content);// 암호화
        String decryptedContent = encryptor.decrypt(encryptedContent); // 복호화
        System.out.println("Enc : " + encryptedContent + ", Dec : " + decryptedContent);
    }
}
