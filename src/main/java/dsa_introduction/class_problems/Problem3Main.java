import java.util.HashSet;
import java.util.Set;

public class Problem3Main {

    public static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            int complement = target - num;

            if (seen.contains(complement)) {
                return true;
            }

            seen.add(num);
        }

        return false;
    }

    public static boolean hasPairWithSumBruteForce(
            int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};

        System.out.println(hasPairWithSum(nums, 9));
        System.out.println(hasPairWithSumBruteForce(nums, 20));
    }
}