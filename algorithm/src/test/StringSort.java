package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author luozhengchao
 * @Date 2021/3/17 上午11:00
 */
public class StringSort {

    public static void main(String[] args) throws IOException {
        String res1 = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = br.readLine()) != null){
            char[] ch = s.toCharArray();
            char[] res = new char[ch.length];
            int flag = 65, j=0;
            while(flag<=90){
                for(int i=0; i<ch.length; i++){
                    if((ch[i]>=65&&ch[i]<=90) || (ch[i]>=97&&ch[i]<=122)){
                        if(ch[i]==flag || ch[i]== flag+32){
                            res[j] = ch[i];
                            j++;
                        }
                    }
                }
                flag++;
            }

            j=0;
            for(int i=0; i<ch.length; i++){
                if((ch[i]>=65&&ch[i]<=90) || (ch[i]>=97&&ch[i]<=122)){
                    ch[i] = res[j];
                    j++;
                }
            }
            res1 = ch.toString();
        }
        System.out.println(res1);

    }


    /**
     *
     * 题目描述
     * 编写一个程序，将输入字符串中的字符按如下规则排序。
     *
     * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
     *
     * 如，输入： Type 输出： epTy
     *
     * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
     *
     * 如，输入： BabA 输出： aABb
     *
     * 规则 3 ：非英文字母的其它字符保持原来的位置。
     *
     *
     * 如，输入： By?e 输出： Be?y
     *
     * @param str
     * @return
     */


}
