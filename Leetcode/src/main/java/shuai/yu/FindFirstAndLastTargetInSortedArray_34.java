package shuai.yu;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * @author shuai.yu
 * @version 2020/10/26
 */
public class FindFirstAndLastTargetInSortedArray_34 {
    public static void main(String[] args) {

    }

    public static int[] searchRange(int[] nums, int target) {
        // abcd 依次从左到右，拆分成两个二分查找
        int a = 0;
        int b = 0;
        int c = 0;
        int d = nums.length - 1;
        int ans1 = -1;
        int ans2 = -1;
        while (a <= d) {
            // 先二分查找找到一个目标的位置
            int mid = (a + d) / 2;
            if (target > nums[mid]) {
                a = mid + 1;
            } else if (target < nums[mid]) {
                d = mid - 1;
            } else {
                ans1 = mid;
                ans2 = mid;
                b = mid - 1;
                c = mid + 1;
                // 然后左右各自进行二分查找，找到起始和结束
                while (a <= b) {
                    mid = (a + b) / 2;
                    // 只可能小于或等于目标
                    if (target > nums[mid]) {
                        a = mid + 1;
                    } else {
                        ans1 = mid;
                        b = mid - 1;
                    }
                }

                while (c <= d) {
                    mid = (c + d) / 2;
                    // 只可能大于或等于目标
                    if (target < nums[mid]) {
                        d = mid - 1;
                    } else {
                        ans2 = mid;
                        c = mid + 1;
                    }
                }

                break;
            }
        }
        return new int[]{ans1, ans2};
    }
}
