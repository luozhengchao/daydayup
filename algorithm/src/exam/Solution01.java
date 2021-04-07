package exam;

/**
 * @Author luozhengchao
 * @Date 2021/4/1 下午8:12
 */
public class Solution01 {


    public static void main(String[] args) {
        exam.Node node = new exam.Node(2);
        exam.Node node1 = new exam.Node(1);
        exam.Node node2 = new exam.Node(2);
        solution(node1, node2);

    }

    private static exam.Node solution(exam.Node node1, exam.Node node2) {
        if (node1 == null || node2 == null){
            return null;
        }

        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;

            node1 = node1 == null ? node2 : node1.next;
            node2 = node2 == null ? node1 : node2.next;
        }
        return node1;
    }


    private static exam.Node solution03(exam.Node node1, exam.Node node2) {
        if (node1 == null || node2 == null){
            return null;
        }

        int a = 0;
        int b = 0;
        exam.Node temp1 = null;
        exam.Node temp2 = null;
        while (node1 != null || node2 != null){
            if (node1 != null){
                node1 = node1.next;
                a++;
            }
            if (node2 != null){
                node2 = node2.next;
                b++;
            }

        }
        return null;

    }



}

class Node {
    int val;
    exam.Node next;
    public Node(int val) {
        this.val = val;
    }

}
