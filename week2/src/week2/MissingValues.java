package week2;
import java.util.ArrayList;
import java.util.List;


public class MissingValues {
    public static void main(String args[]) {

        int[] myArray = {2, 6, 18, 22};
        int k = 6;
        List<Integer> newArray = new ArrayList<Integer>();
        List<Integer> missingEvenList = new ArrayList<Integer>();
        int diff = myArray[0] - 0;

        for(int i = 0; i < myArray.length; i++)
        {

            if (myArray[i] - i != diff)
            {

                while (diff < myArray[i] - i)
                {
                    newArray.add(i + diff);
                    diff++;
                }
            }
        }

        for (int i=0; i< newArray.size(); i++) {
            if(newArray.get(i)%2 == 0) {
                missingEvenList.add(newArray.get(i));
            }
        }

        System.out.println("Missing array: " + missingEvenList);
        System.out.println("Output: " + missingEvenList.get(k - 1));
    }
}