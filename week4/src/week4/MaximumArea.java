package week4;

public class MaximumArea {
    static int matrix[][] = { 
        {1, 0, 1, 0, 0},
        {0, 1, 1, 1, 1},
        {0 ,0, 0, 0, 1},
        {0, 0, 0, 1, 1},
        {0, 1, 0, 1, 1}
    };

    public static void main(String[] args) {
 
        System.out.println("Max square size : " + findMaxSquareSize(matrix));
 
    }
 
    public static int findMaxSquareSize(int[][] matrix) {
        int maxSize = 0;
 
        int[][] temp = new int[matrix.length][matrix.length];
 
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[0][i] == 0) {
                temp[0][i] = 1;
            }
 
            if (matrix[i][0] == 0) {
                temp[i][0] = 1;
            }
        }
 
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    int size = 1 + Math.min(temp[i - 1][j], Math.min(temp[i - 1][j - 1], temp[i][j - 1]));
                    temp[i][j] = size;
                    if (maxSize < size) {
                        maxSize = size;
                    }
                }
            }
        }
 
        return (maxSize > 1 ? maxSize*2 : 0);
    }
 
}