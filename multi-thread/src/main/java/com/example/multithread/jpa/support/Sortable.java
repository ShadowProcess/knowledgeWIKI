package com.example.multithread.jpa.support;

import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 排序功能，模板模式
 *
 * @param <Self>
 */
public interface Sortable<Self> {

    public Self sort(Sort sort);

    public Sort getSort();

    public default Self sort(Sort.Order... orders) {
        return sort(Sort.by(orders));
    }

    public default Self sort(String... properties) {
        return sort(Sort.by(properties));
    }

    public default Self sort(Sort.Direction direction, String... properties) {
        return sort(Sort.by(direction, properties));
    }

    public default Self sort(List<Sort.Order> orders) {
        return sort(Sort.by(orders));
    }

    public default Self sortAsc(String... properties) {
        return sort(Sort.Direction.ASC,properties);
    }

    public default Self sortDesc(String... properties) {
        return sort(Sort.Direction.DESC,properties);
    }

//    class SortBuilder<Self> implements Sortable<Self>{
//        private List<Tuple2<String, Sort.Direction>> list = Lists.newArrayList();
//
//        @Override
//        public Self sort(Sort sort) {
//            return null;
//        }
//    }
}
