package baseAlgorithm;

import scala.Int;

/**
 * 冒牌排序 O(n2)
 */
public class test1 {


    /**
     * 1、 {6, 3, 2, 7, 1, 3, 5, 4}
     * 2、需要将每个数字都进行排序
     * 3、找一个最大的
     */
    public static void main(String[] args) {
        Integer[] arrays = {6, 3, 2, 7, 1, 3, 5, 4};

        int temp;
        for (int i = 0;i <  arrays.length - 1; i++) {
            for (int j = 0; j < arrays.length -1 - i;j++) {

                if (arrays[j] > arrays[j + 1]) {
                    temp = arrays[j];
                    arrays[j] = arrays[j + 1];
                    arrays[j + 1] = temp;
                }

            }

        }

        for (Integer a : arrays) {
            System.out.println(a);
        }



    }
}
