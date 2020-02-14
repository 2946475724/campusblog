package com.zs.campusblog.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zs
 * @date 2020/2/13
 */
public class CompareUtil {
    /**
     * 找出在数组t1但不在数组t2中的元素
     * @param t1
     * @param t2
     * @param <T>
     * @return
     */
    public static <T> List<T> compare(T[] t1, T[] t2) {
        List<T> list1 = Arrays.asList(t1);
        List<T> list2 = Arrays.asList(t2);
        List<T> lisDiff = new ArrayList<T>();
        for(T t : t1) {
            if(!list2.contains(t)) {
                lisDiff.add(t);
            }
        }
        return lisDiff;
    }
}
