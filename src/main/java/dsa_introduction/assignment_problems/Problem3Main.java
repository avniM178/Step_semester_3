import java.util.HashMap;
import java.util.Map;

public class Problem3Main {
    public static Object[] mostPopular(String[] orders) {
        Map<String, Integer> counts = new HashMap<>();

        for (String item : orders) {
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }

        String bestItem = orders[0];
        int bestCount = counts.get(bestItem);

        for (String item : orders) {
            int count = counts.get(item);

            if (count > bestCount) {
                bestItem = item;
                bestCount = count;
            }
        }

        return new Object[]{bestItem, bestCount};
    }

    public static void main(String[] args) {
        String[] orders = {
            "dosa", "idli", "vada", "dosa",
            "idli", "dosa", "tea"
        };

        Object[] result = mostPopular(orders);
        System.out.println("(" + result[0] + ", " + result[1] + ")");
    }
}
