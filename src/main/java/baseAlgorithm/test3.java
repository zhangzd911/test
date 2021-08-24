package baseAlgorithm;


import java.util.Arrays;

/**
 * 插入排序 O(n2)
 */
public class test3 {

    public static void main(String[] args) {

        Integer[] arrays = {6, 3, 2, 7, 1, 3, 5, 4};

        for (int i =  1; i <  arrays.length;i++) {
            int temp = arrays[i];
            int j;
            for (j = i; j > 0 && arrays[ j - 1] > temp ;j--) {
                arrays[j] = arrays[j - 1];
            }
            arrays[j] = temp;
        }


        printArrays(arrays);
    }

    private static void printArrays(Integer[] arrays) {
        System.out.println(Arrays.toString(arrays));
    }
}
