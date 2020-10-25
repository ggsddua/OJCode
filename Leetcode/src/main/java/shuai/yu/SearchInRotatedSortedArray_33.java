package shuai.yu;

/**
 * 搜索旋转排序数组
 * 给你一个升序排列的整数数组 nums ，和一个整数 target 。
 * <p>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。（例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] ）。
 * <p>
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * @author shuai.yu
 * @version 2020/10/25
 */
public class SearchInRotatedSortedArray_33 {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        search1(nums, 0);
    }

    /**
     * 遍历，直接超过100%
     */
    public static int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     */
    public static int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid])
                return mid;
            // left~mid 递增
            if (nums[left] <= nums[mid]) {
                if(target >= nums[left] && target < nums[mid]) {
                    // left~mid-1
                    right = mid - 1;
                }else {
                    // mid+1~right
                    left = mid + 1;
                }
            } else{
                // left~mid 中间有转折点
                if (target > nums[mid] && target <= nums[right]){
                    // mid+1~right
                    left = mid + 1;
                }else {
                    // left~mid-1
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
