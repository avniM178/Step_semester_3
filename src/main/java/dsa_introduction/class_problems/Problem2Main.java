public class Problem2Main {

    public static int[] warehouseSummary(int[][] grid) {
        int totalItems = 0;
        int maxValue = grid[0][0];
        int maxRow = 0;
        int maxCol = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int value = grid[row][col];
                totalItems += value;

                if (value > maxValue) {
                    maxValue = value;
                    maxRow = row;
                    maxCol = col;
                }
            }
        }

        return new int[]{totalItems, maxRow, maxCol};
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        int[] result = warehouseSummary(grid);

        System.out.println("(" + result[0] + ", (" +
                result[1] + ", " + result[2] + "))");
    }
}