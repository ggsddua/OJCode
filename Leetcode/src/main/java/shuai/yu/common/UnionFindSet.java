package shuai.yu.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 并查集
 * 并查集是一种数据结构。在并查集里，每个节点会记录它的父节点
 * 典型应用是解决连通分量的问题
 *
 * @author shuai.yu
 * @version 2021/01/08
 */
public class UnionFindSet
{
    private Map<Integer, Integer> setMap;

    // 并查集中树的数目，即连通分量的数量
    private int numOfTrees;

    public UnionFindSet()
    {
        setMap = new HashMap<>();
        numOfTrees = 0;
    }

    /**
     * 插入元素
     */
    public void add(int x)
    {
        if (!setMap.containsKey(x))
        {
            setMap.put(x, null);
            numOfTrees++;
        }
    }

    /**
     * 插入元素
     */
    public void add(int x, int father)
    {
        if (!setMap.containsKey(x) && setMap.containsKey(father))
        {
            setMap.put(x, father);
        }
    }

    /**
     * 合并元素
     */
    public void merge(int x, int y)
    {
        int xRoot = findRoot(x);
        int yRoot = findRoot(y);
        if (xRoot != yRoot)
        {
            if (!setMap.containsKey(xRoot))
            {
                add(xRoot);
            }
            if (!setMap.containsKey(yRoot))
            {
                add(yRoot);
            }
            setMap.put(xRoot, yRoot);
            numOfTrees--;
        }
    }

    /**
     * 找根节点
     */
    public int findRoot(int x)
    {
        int root = x;
        // 找到根节点
        while (setMap.get(root) != null)
        {
            root = setMap.get(root);
        }

        // 将路径中的所有节点的父节点设置为根节点，提高查找效率
        while (root != x)
        {
            int oldFather = setMap.get(x);
            setMap.put(x, root);
            x = oldFather;
        }
        return root;
    }

    public boolean isConnected(int x, int y)
    {
        return findRoot(x) == findRoot(y);
    }

    public int getNumOfTrees()
    {
        return numOfTrees;
    }
}
