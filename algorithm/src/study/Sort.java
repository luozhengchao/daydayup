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
//        crsort(a);
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 插入排序
     * 最小的往前移动，如果有序跳过
     *
     * @param a
     */
    private static void crsort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    swap(a, j, j - 1);
                }
            }

        }
    }

    /**
     * 冒泡排序
     * 大的往后移动
     *
     * @param a
     */
    private static void mpsort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }


    /**
     * 归并排序
     * 分治思想
     *
     * @param arr
     */
    private static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        subSort(arr, temp, 0,arr.length -1);
    }

    private static void subSort(int[] arr, int[] temp, int left, int right) {
        if (left < right){
            int mid = (left + right) / 2;
            //左边排序
            subSort(arr, temp, left, mid);
            //右边排序
            subSort(arr, temp, mid + 1, right);
            //合并
            merge(arr, temp, left, mid, right);
        }

    }

    /**
     * 合并两个有序数组
     * @param arr
     * @param temp
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        //左序列指针
        int i = left;
        //右序列指针
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right){
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        //把左边填入
        while (i <= mid){
            temp[k++] = arr[i++];
        }
        //把右边填入
        while (j <= right){
            temp[k++] = arr[j++];
        }
        k = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[k++];
        }
    }


    private static void swap(int[] a, int j, int i) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
