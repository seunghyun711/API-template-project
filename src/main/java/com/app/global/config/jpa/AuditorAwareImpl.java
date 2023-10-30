package com.app.global.config.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public Optional<String> getCurrentAuditor() { // 생성자나 수정자에 등록될 uri정보 리턴
        String modifiedBy = String.valueOf(httpServletRequest.getRequestURL()); // 요청의 대한 uri 정보
        if (!StringUtils.hasText(modifiedBy)) {
            modifiedBy = "unkonwn";
        }
        return Optional.of(modifiedBy);
    }
}
