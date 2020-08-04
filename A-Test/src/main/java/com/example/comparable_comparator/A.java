package com.example.comparable_comparator;


import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Comparable是排序接口。若一个类实现了Comparable接口，就意味着该类支持排序。
 * 实现了Comparable接口的类的对象的列表或数组可以通过Collections.sort或Arrays.sort进行自动排序。
 *
 * 　此外，实现此接口的对象可以用作有序映射中的键或有序集合中的集合，无需指定比较器
 *
 * 两种接口的最主要区别还是一个对象自己实现和后期实现的区别。
 */
public class A implements Comparable {
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}


/**
 * Comparator是比较接口，我们如果需要控制某个类的次序，而该类本身不支持排序(即没有实现Comparable接口)，
 * 那么我们就可以建立一个“该类的比较器”来进行排序，这个“比较器”只需要实现Comparator接口即可。也就是说，
 * 我们可以通过实现Comparator来新建一个比较器，然后通过这个比较器对类进行排序。
 */
class B implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}


////////////////////////////////////////////////////////////////////////
    // 状态：0，待完成；1，已完成；2，已领取；3，已失效
    // 目标排序结果：1 0 2 3
//    List<UserTaskItem> sortedUserTasks = userTaskItems.stream()
//            .sorted(Comparator.<UserTaskItem, Integer>comparing(x -> x.getUserTask().getState(), (a, b) -> {
//                if (Objects.equals(a, b)) return 0;
//                if (a == 3) return 1;  //表示a > b
//                if (b == 3) return -1; //表示a < b
//                if (a == 2) return 1;  //表示a > b
//                if (b == 2) return -1; //表示a < b
//                if (a == 0) return 1;  //表示a > b
//                if (b == 0) return -1; //表示a < b
//                if (a == 1) return 1;  //表示a > b
//                if (b == 1) return -1; //表示a < b
//                return 0;
//            }).thenComparing(y -> y.getUserTask().getDateCreated()))
//            .collect(Collectors.toList());
////////////////////////////////////////////////////////////////////////////////////

class AndDDD {
    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
            Function<? super T, ? extends U> keyExtractor)
    {
        Objects.requireNonNull(keyExtractor);
        return (Comparator<T> & Serializable)
                (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
    }

    ////// (Comparator<T> & Serializable) 是什么意思？
    /***
     * This means that the resulting value will be cast to Comparator and Serializable (i.e. a serializable comparator)
     * 意思是：将返回值转换为可序列化的比较器
     */

}

