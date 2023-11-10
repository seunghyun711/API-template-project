package com.app.domain.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass// 부모 클래스는 테이블이랑 매핑하지 않고 부모 클래스를 상속받는 자식 클래스에 매핑 정보를 제공할 때 사용 -> 자식 클래스에 createTime, updateTime을 매핑정보로 제공한다.
@Getter
public abstract class BaseEntity extends BaseTimeEntity {

    @CreatedBy
    @Column(updatable = false)
    private String createBy; // 생성자

    @LastModifiedBy
    private String modifiedBy; // 수정자

}
