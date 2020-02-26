package com.example.aliyundemo.jpa.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;


@NoRepositoryBean
public interface StandardRepository<T extends Serializable, P>
        extends JpaRepository<T, P>, JpaSpecificationExecutor {

    default SpecificationWrapper<T> spec() {
        return new SpecificationWrapper<>(this);
    }
}
