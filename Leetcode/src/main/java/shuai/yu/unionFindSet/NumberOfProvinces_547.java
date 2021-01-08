package shuai.yu.unionFindSet;

import shuai.yu.common.UnionFindSet;

/**
 * 省份数量，并查集
 * 有 n个城市，其中一些彼此相连，另一些没有相连。如果城市 a与城市 b直接相连，且城市 b与城市 c直接相连，那么城市 a与城市 c间接相连。
 * 省份是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1表示第 i个城市和第 j个城市直接相连，
 * isConnected[i][j] = 0表示二者不直接相连。
 *
 * @author shuai.yu
 * @version 2021/01/07
 */
public class NumberOfProvinces_547
{
    public static void main(String[] args)
    {
        /**
         * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
         * 输出：2
         * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
         * 输出：3
         */
        int[][] isConnected = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(isConnected));
        isConnected = new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(isConnected));
    }

    public static int findCircleNum(int[][] isConnected)
    {
        // TODO: 2021/1/8 还没提交 
        if (isConnected == null || isConnected.length == 0 || isConnected[0].length == 0)
            return 0;
        UnionFindSet unionFindSet = new UnionFindSet();
        for (int i = 0; i < isConnected.length; i++)
        {
            unionFindSet.add(i);
            for (int j = i + 1; j < isConnected[0].length; j++)
            {
                if (isConnected[i][j] == 1)
                {
                    unionFindSet.merge(i, j);
                }
            }
        }
        return unionFindSet.getNumOfTrees();
    }
}
