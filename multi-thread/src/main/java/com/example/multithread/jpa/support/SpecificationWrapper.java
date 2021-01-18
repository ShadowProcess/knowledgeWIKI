package com.example.multithread.jpa.support;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SpecificationWrapper<T extends Serializable> implements
        LambdaSpecification<T, SpecificationWrapper<T>>,
        Sortable<SpecificationWrapper<T>>,
        SpecExecutor<T> {

    @Getter
    private final JpaSpecificationExecutor<T> jpaSpecificationExecutor;
    private Specification<T> specification;
    @Getter
    private Sort sort;

    @Override
    public Specification<T> toSpecification() {
        return specification;
    }

    @Override
    public SpecificationWrapper<T> sort(Sort sort) {
        this.sort = sort;
        return this;
    }

    @Override

    public SpecificationWrapper<T> and(Specification<T> value) {
        specification = specification == null ? value : Specification.where(specification).and(value);
        return this;
    }


    public SpecificationWrapper<T> or() {
        var that = this;
        var exe = new SpecificationWrapper<T>(getJpaSpecificationExecutor()) {
            @Override
            public Specification<T> toSpecification() {
                return that.toSpecification();
            }

            @Override
            public SpecificationWrapper<T> sort(Sort sort) {
                return that.sort(sort);
            }
        };
        specification = Specification.where(specification).or(exe::toPredicate);
        return exe;
    }


    public Predicate toPredicate(Root<T> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {
        return specification.toPredicate(root, query, criteriaBuilder);
    }
}