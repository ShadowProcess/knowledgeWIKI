package com.example.jpademo.jpa.support.original;

import com.example.jpademo.jpa.entity.integer_type.IntegerDemo;
import com.example.jpademo.jpa.repo.IntRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.*;

/**
 * 单向映射关系和双向映射关系的区别？
 *
 * 总结可见：
 * 双向关联和单向关联在数据表层是没有区别的，只是在业务上单向关联只可以通过ProductInfo对象找到对应的ProductType对象；而双向
 * 关联关系不仅可以通过ProductInfo对象找到对应的ProductType对象，还可以通过ProductType对象找到对应的ProductInfo对象
 */


/* //构造查询条件
 * root    ：Root接口，代表查询的根对象，可以通过root获取实体中的属性
 * query   ：代表一个顶层查询对象，用来自定义查询
 * cb      ：用来构建查询，此对象里有很多条件方法
 *
 * public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);
 **/
public class JpaSpecificationExecutorCollege {

    @Autowired
    private IntRepository intRepository;

    //匿名内部类
    /*
     * 自定义查询条件
     * 1.实现Specification接口（提供泛型：查询的对象类型）
     * 2.实现toPredicate方法（构造查询条件）
     * 3.需要借助方法参数中的两个参数（
     *      root：获取需要查询的对象属性
     *      CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配））
     * 案例：根据客户名称查询，查询客户名为传智播客的客户
     * 查询条件
     * 1.查询方式
     *      cb对象
     * 2.比较的属性名称
     *      root对象
     */
    @Test
    public void testEqual() {
        Specification<IntegerDemo> spec = new Specification<IntegerDemo>() {
            @Override
            public Predicate toPredicate(Root<IntegerDemo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //1.获取比较的属性
                Path<Object> id = root.get("id"); //查询的是属性名，不是表的字段名
                //2.构造查询条件: select * from jpa_int where id = 3
                /**
                 * 第一个参数：需要比较的属性（path对象）
                 * 第二个参数：当前需要比较的取值
                 */
                Predicate predicate = criteriaBuilder.equal(id, 3); //进行精准的匹配  （比较的属性，比较的属性的取值）
                return predicate;
            }
        };
        Optional<IntegerDemo> one = intRepository.findOne(spec);
        one.ifPresent(System.out::println);
    }


    //多条件拼接
    @Test
    public void testAnd() {
        Specification<IntegerDemo> spec = new Specification<IntegerDemo>() {
            @Override
            public Predicate toPredicate(Root<IntegerDemo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> cusName = root.get("cusName");//客户名    //查询的是属性名，不是表的字段名
                Path<Object> address = root.get("address");//客户地址   //查询的是属性名，不是表的字段名

                //构造查询条件
                //1.构造客户名的精准匹配查询
                Predicate p1 = criteriaBuilder.equal(cusName, "黑马程序"); //第一个参数，path（属性），第二个参数，属性的取值
                //2.构造所属行业的精准匹配查询
                Predicate p2 = criteriaBuilder.equal(address, "虹桥");
                //3.将多个查询条件组合到一起：组合（满足条件一并且满足条件二：与关系，     满足条件一或满足条件二即可：或关系）
                Predicate and = criteriaBuilder.and(p1, p2); //以与的形式拼接多个查询条件
                // cb.or();//以或的形式拼接多个查询条件
                return and;
            }
        };
        Optional<IntegerDemo> one = intRepository.findOne(spec);
        one.ifPresent(System.out::println);
    }


    @Test
    public void testLike() {
        //构造查询条件
        Specification<IntegerDemo> spec = (Specification<IntegerDemo>) (root, query, criteriaBuilder) -> {
            //查询属性：客户名
            Path<Object> custName = root.get("custName");
            //查询方式：模糊匹配  d开头
            Predicate like = criteriaBuilder.like(custName.as(String.class), "d%");
            return like;
        };
        List<IntegerDemo> list = intRepository.findAll(spec);
        list.forEach(System.out::println);
    }

    @Test
    public void testSort() {
        //构造查询条件
        Specification<IntegerDemo> spec = new Specification<IntegerDemo>() {
            @Override
            public Predicate toPredicate(Root<IntegerDemo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //查询属性：客户名
                Path<Object> custName = root.get("custName");
                //查询方式：模糊查询
                Predicate like = criteriaBuilder.like(custName.as(String.class), "d%");
                return like;
            }
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "custId");
        List<IntegerDemo> list = intRepository.findAll(spec, sort);
        list.forEach(System.out::println);
    }

    @Test
    public void testPage() {
        Specification<IntegerDemo> spec = new Specification<IntegerDemo>() {
            @Override
            public Predicate toPredicate(Root<IntegerDemo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查询属性：客户名
                Path<Object> custName = root.get("custName");
                //查询方式：模糊匹配
                Predicate like = cb.like(custName.as(String.class), "d%");
                return like;
            }
        };
        //PageRequest对象是Pageable接口的实现类
//        创建PageRequest的过程中，需要调用他的构造方法传入两个参数
//
//        第一个参数：当前查询的页数（从0开始）
//        第二个参数：每页查询的数量
        Pageable pageable = PageRequest.of(0, 1);
        ;
        //分页查询
        Page<IntegerDemo> page = intRepository.findAll(spec, pageable);
        System.out.println(page.getContent());      //得到数据集合列表
        System.out.println(page.getTotalElements());//得到总条数
        System.out.println(page.getTotalPages());   //得到总页数
    }


    @Test
    public void testIn() {
        Specification<IntegerDemo> spec = new Specification<IntegerDemo>() {
            @Override
            public Predicate toPredicate(Root<IntegerDemo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> rootOfferId = root.get("rootOfferId");
                CriteriaBuilder.In<Object> in = cb.in(rootOfferId);
                for (String offerId : Arrays.asList("1", "2", "3")) {
                    in.value(offerId);
                }
                return in;
            }
        };
        List<IntegerDemo> all = intRepository.findAll(spec);
        all.forEach(System.out::println);
    }


    @Test
    public void testCriteriaQuery() {
        Specification<IntegerDemo> spec = new Specification<IntegerDemo>() {
            @Override
            public Predicate toPredicate(Root<IntegerDemo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> id = root.get("id");
                Path<Object> name = root.get("name");
                Predicate p1 = cb.equal(id, "1");
                Predicate p2 = cb.equal(name, "na");

                //return cb.and(p1,p2);
                return query.where(p1, p2).getRestriction();
            }
        };
        List<IntegerDemo> all = intRepository.findAll(spec);
        all.forEach(System.out::println);
    }



    @Test
    public void testCriteriaQueryDD() {
        Specification<IntegerDemo> spec = new Specification<IntegerDemo>() {
            @Override
            public Predicate toPredicate(Root<IntegerDemo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Path<Object> id = root.get("id");
                Path<Object> name = root.get("name");
                Path<Object> age = root.get("age");
                Path<Object> gender = root.get("gender");
                Predicate p1 = cb.equal(id, "1");
                Predicate p2 = cb.equal(name, "na");
                Predicate p3 = cb.equal(age, "12");
                Predicate p4 = cb.equal(gender, "male");
                predicates.add(p1);
                predicates.add(p2);
                predicates.add(p3);
                predicates.add(p4);

                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        List<IntegerDemo> all = intRepository.findAll(spec);
        all.forEach(System.out::println);
    }


    @Test
    public void testMultiSpec() {
        //select * from t_student where (id=2 or id=3) and (address like 'zt%' and name like 'foo%')

//第一个Specification定义了两个or的组合
        Specification<IntegerDemo> s1 = new Specification<IntegerDemo>() {
            @Override
            public Predicate toPredicate(Root<IntegerDemo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = criteriaBuilder.equal(root.get("id"), "2");
                Predicate p2 = criteriaBuilder.equal(root.get("id"), "3");
                return criteriaBuilder.or(p1, p2);
            }
        };
//第二个Specification定义了两个or的组合
        Specification<IntegerDemo> s2 = new Specification<IntegerDemo>() {
            @Override
            public Predicate toPredicate(Root<IntegerDemo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = criteriaBuilder.like(root.get("address"), "zt%");
                Predicate p2 = criteriaBuilder.like(root.get("name"), "foo%");
                return criteriaBuilder.or(p1, p2);
            }
        };
//通过Specifications将两个Specification连接起来，第一个条件加where，第二个是and
        List<IntegerDemo> stus = intRepository.findAll(Specification.where(s1).and(s2));
    }


}
