package test;

/**
 * @Author luozhengchao
 * @Date 2021/3/27 下午9:40
 */
public class Test03 {

    public static void main(String[] args) {
        int[] str = {1,0,0,2,1};
        str = sort(str);
        for (int i = 0; i< str.length; i++) {
            System.out.println(str[i]);
        }
    }

    public static int[] sort(int[] str){
       for (int i = 1; i < str.length; i++) {
           for (int j = 0; j < str.length - i; j++) {
               int temp = str[j];
               if (str[j] > str[j + 1]){
                   str[j] = str[j + 1];
                   str[j + 1] = temp;
               }
           }
       }
       return str;
    }

}
