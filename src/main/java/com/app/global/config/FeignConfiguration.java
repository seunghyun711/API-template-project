package com.app.global.config;

import com.app.global.error.FeignClientExceptionErrorDecoder;
import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// 로깅 레벨, 구현한 error decoder 재시도를 위한 retryer를 빈으로 등록
@Configuration
@EnableFeignClients(basePackages = "com.app") // todo basepacakage 변경 시 패키지명 수정
//@Import(FeignConfiguration.class)
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignClientExceptionErrorDecoder();
    }

    @Bean
    public Retryer retryer(){
        return new Retryer.Default(1000, 2000, 3); // 실행주기, 최대 실행 주기 ,최대 재시도 횟수
    }
}
