import java.util.Arrays;

public class Eyebrow {
    private static final int[][] EYEBROW_X = new int[][]{{0, 35, 53, 84, 112, 131, 131, 124, 119, 112, 57, 20, 27, 3, 0},
            {0, 35, 53, 59, 76, 103, 122, 130, 130, 127, 118, 111, 54, 44, 33, 2, 0},
            {0, 2, 6, 10, 17, 22, 40, 48, 57, 76, 99, 110, 115, 123, 123, 121, 109, 92, 78, 63, 56, 47, 35, 22, 9, 0},
            {0, 10, 23, 37, 41, 46, 55, 77, 86, 96, 109, 114, 120, 125, 133, 134, 134, 131, 128, 117, 111, 108, 99, 87, 81, 73, 58, 49, 32, 22, 2, 0},
            {0, 9, 24, 32, 38, 71, 85, 100, 114, 121, 122, 122, 118, 115, 105, 68, 51, 40, 15, 2}};
    private static final int[][] EYEBROW_Y = new int[][]{{18, 0, 0, 6, 14, 25, 29, 34, 34, 33, 15, 13, 13, 19, 19},
            {25, 6, 0, 0, 3, 11, 18, 24, 26, 30, 35, 35, 14, 14, 17, 27, 27},
            {23, 20, 17, 14, 10, 7, 1, 0, 0, 2, 7, 11, 13, 20, 27, 27, 27, 23, 18, 14, 13, 12, 14, 17, 21, 24},
            {27, 16, 8, 3, 2, 1, 0, 0, 1, 3, 7, 9, 12, 15, 21, 23, 25, 28, 29, 29, 27, 24, 19, 15, 14, 13, 12, 13, 17, 20, 29, 29},
            {20, 12, 4, 1, 0, 0, 2, 5, 9, 13, 15, 17, 21, 22, 22, 14, 12, 12, 17, 22}};

    public static int[] getEyebrowX(int num) {
        return Arrays.copyOf(EYEBROW_X[num - 1], EYEBROW_X[num - 1].length);
    }

    public static int[] getEyebrowY(int num) {
        return Arrays.copyOf(EYEBROW_Y[num - 1], EYEBROW_Y[num - 1].length);
    }

    public static int[] getEyebrowXM(int num) {
        int[] xm = new int[EYEBROW_X[num - 1].length];
        for (int i = 0; i < EYEBROW_X[num - 1].length; i++) {
            xm[i] = -EYEBROW_X[num - 1][i];
        }
        return xm;
    }
}
