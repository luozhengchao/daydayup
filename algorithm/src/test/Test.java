package test;

import java.util.Scanner;

/**
 * @Author luozhengchao
 * @Date 2021/3/21 下午12:10
 */
public class Test {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别

        String test = test(in.nextLine());
        System.out.println(test);
    }

    private static String test(String str) {
        StringBuffer buffer = new StringBuffer();
        if (null == str) {
            return buffer.toString();
        }
        String[] s = str.split(" ");
        for (int i = s.length - 1; i >= 0; i--) {
            if (null != s[i] && s[i].replace(" ", "") != "") {
                buffer.append(s[i].trim()).append(" ");
            }
        }
        return buffer.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}