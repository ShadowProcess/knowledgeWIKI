package com.example.multithread.jpa.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface StandardRepository<T extends Serializable> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    public default SpecificationWrapper<T> spec() {
        return new SpecificationWrapper<>(this);
    }

}
