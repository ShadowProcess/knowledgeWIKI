package com.example.multithread.jpa.support;

import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 查询功能，模板模式
 *
 * @param <T>
 */
public interface SpecExecutor<T extends Serializable> {

    JpaSpecificationExecutor<T> getJpaSpecificationExecutor();

    Sort getSort();

    Specification<T> toSpecification();

    default Optional<T> findOne() {
        return getJpaSpecificationExecutor().findOne(toSpecification());
    }

    default T getOne() {
        Optional<T> one = getJpaSpecificationExecutor().findOne(toSpecification());
        return one.orElse(null);
    }

    default List<T> findAll() {
        return getJpaSpecificationExecutor().findAll(toSpecification(), getSort());
    }

    default Page<T> findAll(int page, int size) { // zero based page
        var pageReq =  PageRequest.of(page, size, getSort());
        return getJpaSpecificationExecutor().findAll(toSpecification(), pageReq);
    }

    default Long count() {
        return getJpaSpecificationExecutor().count(toSpecification());
    }

    default Long countDistinct(Class<T> entityClass, String prop) {
        var em = BeanHook.self().getEntityManager();
        var specification = toSpecification();
        var cb = em.getCriteriaBuilder();
        var query = cb.createTupleQuery();
        var root = query.from(entityClass);
        var expression = cb.countDistinct(LambdaSpecification.str2Path(root, prop));
        query.multiselect(expression);
        if (specification!=null) query.where(specification.toPredicate(root, query, cb));
        return em.createQuery(query).getSingleResult().get(0, Long.class);
    }

    default Double sumFloat64(Class<T> entityClass, String prop) {
        var em = BeanHook.self().getEntityManager();
        var specification = toSpecification();
        var cb = em.getCriteriaBuilder();
        var query = cb.createTupleQuery();
        var root = query.from(entityClass);
        var expression = cb.sumAsDouble(LambdaSpecification.str2Path(root, prop));
        query.multiselect(expression);
        if (specification!=null) query.where(specification.toPredicate(root, query, cb));
        return em.createQuery(query).getSingleResult().get(0, Double.class);
    }

    default Long sumInt64(Class<T> entityClass, String prop) {
        var em = BeanHook.self().getEntityManager();
        var specification = toSpecification();
        var cb = em.getCriteriaBuilder();
        var query = cb.createTupleQuery();
        var root = query.from(entityClass);
        var exp = cb.sumAsLong(LambdaSpecification.str2Path(root, prop));
        query.multiselect(exp);
        if (specification!=null) query.where(specification.toPredicate(root, query, cb));
        return em.createQuery(query).getSingleResult().get(0, Long.class);
    }

    default Double avgFloat64(Class<T> entityClass, String prop) {
        var em = BeanHook.self().getEntityManager();
        var specification = toSpecification();
        var cb = em.getCriteriaBuilder();
        var query = cb.createTupleQuery();
        var root = query.from(entityClass);
        var exp = cb.avg(LambdaSpecification.str2Path(root, prop));
        query.multiselect(exp);
        if (specification!=null) query.where(specification.toPredicate(root, query, cb));
        return em.createQuery(query).getSingleResult().get(0, Double.class);
    }

    default <U> Stream<U> fetch(Class<T> entityClass, String prop, Class<U> uClass) {
        var em = BeanHook.self().getEntityManager();
        var specification = toSpecification();
        var cb = em.getCriteriaBuilder();
        var query = cb.createTupleQuery();
        var root = query.from(entityClass);
        var exp = LambdaSpecification.str2Path(root, prop);
        query.multiselect(exp);
        if (specification!=null) query.where(specification.toPredicate(root, query, cb));
        return em.createQuery(query).getResultList().stream().map(x -> x.get(0,uClass));
    }
}
