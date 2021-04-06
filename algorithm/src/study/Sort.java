package study;

import java.util.Arrays;

/**
 * @Author luozhengchao
 * @Date 2021/3/31 下午5:49
 */
public class Sort {

    public static void main(String[] args) {
        int[] a = {4, 5, 6, 8, 1, 0, 67};
//        mpsort(a);
        crsort(a);
        System.out.println(Arrays.toString(a));
    }

    private static void crsort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    swap(a, j, j - 1);
                }
            }

        }
    }

    private static void mpsort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }


    private static void swap(int[] a, int j, int i) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
