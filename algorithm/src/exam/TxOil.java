package exam;

/**
 * @Author luozhengchao
 * @Date 2021/3/30 下午9:05
 */
public class TxOil {

    public static void main(String[] args) {
        //存的油量
        int[] a = {1, 2, 3, 4, 5};
        //消耗的油量
        int[] b = {5, 4, 3, 2, 1};

        System.out.println(run(a, b));
    }


    private static int run(int[] a, int[] b) {
        int minSpace = 0;
        int idx = 0;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] - b[i];
            if (sum < minSpace) {
                minSpace = sum;
                idx = i;
            }
        }
        return sum < 0 ? -1 : (idx + 1) % a.length;
    }


}
