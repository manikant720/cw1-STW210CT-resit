package week7;
import java.util.*;
 
class MaximumIndex {
    static int R = 4, C = 4;
    static int rowWithMax1s(int mat[][])
    {
        ArrayList<int[]> newArray = new ArrayList<int[]>();
        int j,max_row_index = 0;
            j = C - 1;
 
        for (int i = 0; i < R; i++) {
            // Move left until a 0 is found
            while (j >= 0 && mat[i][j] == 1) {
                j = j - 1;
                max_row_index = i;
            }
        }
        for (int i = 0; i < mat.length; i++) {
            if (i != max_row_index){
                newArray.add(mat[i]);
            }
        }
        R = 3;
        C = 3;
        j = C -1; 
        max_row_index = 0;
        
        for (int i = 0; i < R; i++) {
            System.out.println();
            while (j >= 0 && newArray.get(i)[1] == 1) {
                j = j - 1; 
                max_row_index = i; 
            }
        }

          if(max_row_index==0&&mat[0][C-1]==0)
              return -1;
              // +1 for 1st maximum row index which was removed
        return max_row_index + 1;
    }
    public static void main(String[] args)
    {
        int mat[][] = {{0, 0, 0, 1},
                        {1, 1, 1, 1},
                       { 0, 0, 0 ,1},
                        {0, 1, 0, 1}};
        
        System.out.println("Index of row with 2nd maximum 1s is "+ rowWithMax1s(mat));
    }
}