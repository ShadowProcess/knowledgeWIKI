package com.example.multithread.jpa.support;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

/**
 * 查询Specification对象的组装
 *
 * @param <T>
 * @param <Self>
 */

public interface LambdaSpecification<T, Self extends LambdaSpecification<T, Self>> {
    static <P> Path<P> str2Path(Path<?> path, String string) {
        @SuppressWarnings("unchecked")
        Path<P> castPath = (Path<P>) path;
        return Arrays.stream(string.split("\\."))
                .reduce(castPath, Path::get, (a, b) -> null);
    }

    Self and(Specification<T> specification);

    default Self eq(String path, Object value) {
        return value == null
                ? and((root, query, criteriaBuilder) ->
                criteriaBuilder.isNull(str2Path(root, path)))
                : and((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(str2Path(root, path), value));
    }

    default Self example(T probe) {
        return and((root, query, criteriaBuilder) ->
                QueryByExamplePredicateBuilder.getPredicate(root, criteriaBuilder, Example.of(probe)));
    }

    @SuppressWarnings("unchecked")
    default Self cond(boolean condition,
                      Function<Self, Self> functor) {
        return condition
                ? functor.apply((Self) this)
                : (Self) this;
    }

    default Self ne(String path, Object value) {
        return value == null
                ? and((root, query, criteriaBuilder) ->
                criteriaBuilder.isNotNull(root.get(path)))
                : and((root, query, criteriaBuilder) ->
                criteriaBuilder.notEqual(root.get(path), value));
    }

    default <P extends Comparable<P>> Self le(String path, P value) {
        return cond(value != null,
                x -> x.and((r, q, cb) ->
                        cb.lessThanOrEqualTo(str2Path(r, path), value))
        );
    }

    default <P extends Comparable<P>> Self ge(String path, P value) {
        return cond(value != null,
                x -> x.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.greaterThanOrEqualTo(str2Path(root, path), value))
        );
    }

    default <P extends Comparable<P>> Self lt(String path, P value) {
        return cond(value != null,
                x -> x.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.lessThan(str2Path(root, path), value))
        );
    }

    default <P extends Comparable<P>> Self gt(String path, P value) {
        return cond(value != null,
                x -> x.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.greaterThan(str2Path(root, path), value))
        );
    }

    // 闭区间
    default <P extends Comparable<P>> Self between(
            String path, P lowerBound, P upperBound) {
        return ge(path, lowerBound).le(path, upperBound);
    }

    default Self like(String path, String value) {
        return and((root, query, criteriaBuilder) ->
                criteriaBuilder.like(str2Path(root, path), value));
    }

    default Self like(boolean condition, String path, String value) {
        return cond(condition, spec -> spec.like(path, value));
    }

    default Self in(String path, Collection<?> value) {
        if (value.isEmpty()) return eq("id",-1L);// must false
        return and((root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Object> p = criteriaBuilder.in(str2Path(root, path));
            value.forEach(p::value);
            return p;
        });
    }

    default Self in(String path, Object... value) {
        return and((root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Object> p = criteriaBuilder.in(str2Path(root, path));
            Arrays.stream(value).forEach(p::value);
            return p;
        });
    }

    @SuppressWarnings("unchecked")
    default Self notIn(String path, Collection<?> value) {
        if (value.isEmpty()) return (Self) this;
        return and(Specification.not((root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Object> p = criteriaBuilder.in(str2Path(root, path));
            value.forEach(p::value);
            return p;
        }));
    }

    default Self notIn(String path, Object... value) {
        return and(Specification.not((root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Object> p = criteriaBuilder.in(str2Path(root, path));
            Arrays.stream(value).forEach(p::value);
            return p;
        }));
    }
}
