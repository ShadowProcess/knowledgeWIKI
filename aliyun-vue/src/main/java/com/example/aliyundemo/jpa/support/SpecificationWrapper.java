package com.example.aliyundemo.jpa.support;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SpecificationWrapper<T extends Serializable> {

    @Getter
    private final JpaSpecificationExecutor<T> standardRepository;

    @Getter
    private Sort sort;

    private Specification<T> specification;
    public Specification<T> toSpecification() {
        return specification;
    }


    public SpecificationWrapper<T> sort(Sort sort) {
        this.sort = sort;
        return this;
    }

    public SpecificationWrapper<T> and(Specification<T> value) {
        specification = specification == null ? value : Specification.where(specification).and(value);
        return this;
    }

    public SpecificationWrapper<T> or() {
        SpecificationWrapper that = this;
        SpecificationWrapper exe = new SpecificationWrapper<T>(getStandardRepository()) {
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

    //---------------------------------------------------------------------------------
    static <P> Path<P> str2Path(Path<?> path, String string) {
        @SuppressWarnings("unchecked")
        Path<P> castPath = (Path<P>) path;
        return Arrays.stream(string.split("\\."))
                .reduce(castPath, Path::get, (a, b) -> a);
    }

    public SpecificationWrapper<T> eq(String path, Object value) {
        return value == null
                ? and((root, query, criteriaBuilder) ->
                criteriaBuilder.isNull(str2Path(root, path)))
                : and((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(str2Path(root, path), value));
    }

    public SpecificationWrapper<T> example(T probe) {
        return and((root, query, criteriaBuilder) ->
                QueryByExamplePredicateBuilder.getPredicate(root, criteriaBuilder, Example.of(probe)));
    }

    public SpecificationWrapper<T> cond(boolean condition,
                                        Function<SpecificationWrapper<T>,
                                                SpecificationWrapper<T>> functor) {
        return condition ? functor.apply(this) : this;
    }

    public SpecificationWrapper<T> ne(String path, Object value) {
        return value == null
                ? and((root, query, criteriaBuilder) ->
                criteriaBuilder.isNotNull(root.get(path)))
                : and((root, query, criteriaBuilder) ->
                criteriaBuilder.notEqual(root.get(path), value));
    }


    public <P extends Comparable<P>> SpecificationWrapper<T> le(String path, P value) {
        return cond(value != null,
                x -> x.and((r, q, cb) ->
                        cb.lessThanOrEqualTo(str2Path(r, path), value))
        );
    }

    public <P extends Comparable<P>> SpecificationWrapper<T> ge(String path, P value) {
        return cond(value != null,
                x -> x.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.greaterThanOrEqualTo(str2Path(root, path), value))
        );
    }


    public <P extends Comparable<P>> SpecificationWrapper<T> lt(String path, P value) {
        return cond(value != null,
                x -> x.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.lessThan(str2Path(root, path), value))
        );
    }

    public <P extends Comparable<P>> SpecificationWrapper<T> gt(String path, P value) {
        return cond(value != null,
                x -> x.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.greaterThan(str2Path(root, path), value))
        );
    }

    // 闭区间
    public <P extends Comparable<P>> SpecificationWrapper<T> between(
            String path, P lowerBound, P upperBound) {
        return ge(path, lowerBound).le(path, upperBound);
    }

    public SpecificationWrapper<T> like(String path, String value) {
        return and((root, query, criteriaBuilder) ->
                criteriaBuilder.like(str2Path(root, path), value));

    }


    public SpecificationWrapper<T> like(boolean condition, String path, String value) {
        return cond(condition, spec -> spec.like(path, value));
    }


    public SpecificationWrapper<T> in(String path, Collection<?> value) {
        return and((root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Object> p = criteriaBuilder.in(str2Path(root, path));
            value.forEach(p::value);
            return p;
        });
    }

    public SpecificationWrapper<T> in(String path, Object... value) {
        return and((root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Object> p = criteriaBuilder.in(str2Path(root, path));
            Arrays.stream(value).forEach(p::value);
            return p;
        });
    }


    public SpecificationWrapper<T> notIn(String path, Collection<?> value) {
        return and(Specification.not((root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Object> p = criteriaBuilder.in(str2Path(root, path));
            value.forEach(p::value);
            return p;
        }));
    }

    public SpecificationWrapper<T> notIn(String path, Object... value) {
        return and(Specification.not((root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Object> p = criteriaBuilder.in(str2Path(root, path));
            Arrays.stream(value).forEach(p::value);
            return p;
        }));
    }
    //-----------------------------------------------------------------------------------------

    public Optional<T> findOne() {
        Optional<T> result = getStandardRepository().findOne(toSpecification());
        return result;
    }

    public T getOne() {
        return findOne().orElse(null);
    }

    public List<T> findAll() {
        return getSort() == null
                ? getStandardRepository().findAll(toSpecification())
                : getStandardRepository().findAll(toSpecification(), getSort());
    }

    public Page<T> findAll(int page, int size) { // zero based page
        PageRequest pageReq = getSort() == null ? PageRequest.of(page, size) : PageRequest.of(page, size, getSort());
        return getStandardRepository().findAll(toSpecification(), pageReq);
    }

    public long count() {
        return getStandardRepository().count(toSpecification());
    }

    //-----------------------------------------------------------------------------------------
    public SpecificationWrapper<T> sort(Sort.Order... orders) {
        return sort(Sort.by(orders));
    }

    public SpecificationWrapper<T> sort(String... properties) {
        return sort(Sort.by(properties));
    }

    public SpecificationWrapper<T> sort(Sort.Direction direction, String... properties) {
        return sort(Sort.by(direction, properties));
    }

    public SpecificationWrapper<T> sort(List<Sort.Order> orders) {
        return sort(Sort.by(orders));
    }
}