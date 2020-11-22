package shuai.yu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * O(1) 时间插入、删除和获取随机元素 - 允许重复
 *
 * @author shuai.yu
 * @version 2020/11/01
 */
public class CollectionO1_381 {
    public static void main(String[] args) {
    }
}

class RandomizedCollection {
    private List<Integer> list;
    private Map<Integer, Set<Integer>> map;

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<Integer, Set<Integer>>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        list.add(val);
        Set<Integer> set = map.computeIfAbsent(val, k -> new HashSet<>());
        set.add(list.size() - 1);
        return set.size() == 1;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        Set<Integer> set = map.get(val);
        if (set == null || set.size() == 0) {
            return false;
        }
        Iterator<Integer> iterator = set.iterator();
        int i = iterator.next();
        int lastNum = list.get(list.size() - 1);
        set.remove(i);
        map.get(lastNum).remove(list.size() - 1);
        if (list.size() - 1 > i) {
            map.get(lastNum).add(i);
        }

        list.set(i, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        if (list.isEmpty())
            return 0;
        return list.get((int) (Math.random() * list.size()));
    }
}