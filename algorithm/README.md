# **第一章 、常见运算**

```java
//取模运算:余数,可以用作循环
5%2 = 1
5/2 = 2
i++ //输出后再加
++i //加完再输出  
```



# 第二章、常用数据结构与算法



## 一、**数组、链表、跳表**

* 原始数组

  ```java
  //1、 数据类型 [] 数组名=new 数据类型[ length];
  int[] ary = new int[4];//初始化值为0
  //2、初始化
  int[] ary2 = {0, 1, 2};
  //数组扩容,拷贝
  int[] newInts = Arrays.copyOf(ary2, 5);//表面上对数组长度进行扩容，实际新开辟一个空间
  System.out.println(Arrays.toString(newInts));//[0, 1, 0, 0, 0]
  //数组复制
  System.arraycopy(ary2, 1, ary, 1, 2);//把ary2中的内容从下标1开始复制到ary中，复制长度2
  System.out.println(Arrays.toString(ary));//[0, 1, 2, 0]
  ```

* ArrayList

  ```java
  public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;//学习写法：添加到size位置后，size++
    return true;
  }
  ```

* 链表

  ```java
  //双向链表
  class Node {
      public int key, val;
      public Node next, prev;
      public Node(int k, int v) {
          this.key = k;
          this.val = v;
      }
  }
  ```

  

* 跳表

  * 升维：增加多级索引Olog(n)；随着增加删除索引索引可能需要重建，空间复杂度O(n)

###1、解题模板

* 遍历

  ```java
  /* 基本的单链表节点 */
  class ListNode {
      int val;
      ListNode next;
  }
  
  void traverse(ListNode head) {
      for (ListNode p = head; p != null; p = p.next) {
          // 迭代访问 p.val
      }
  }
  
  void traverse(ListNode head) {
      // 递归访问 head.val
      traverse(head.next);
  }
  ```

  



### 2、力扣刷题

#### [146. LRU 缓存机制](https://leetcode-cn.com/problems/lru-cache/)

* **题解**：LRU 缓存算法的核心数据结构就是哈希链表，双向链表和哈希表的结合体。这个数据结构长这样：

<img src="https://pic.leetcode-cn.com/b84cf65debb43b28bd212787ca63d34c9962696ed427f638763be71a3cb8f89d.jpg" alt="HashLinkedList" style="zoom:50%;" />



* **答案**

  ```java
  class LRUCache { 
      private HashMap<Integer, Node> map;
      private DoubleList cache;
      // 最大容量
      private int cap;
  
      public LRUCache(int capacity) {
          this.cap = capacity;
          map = new HashMap<>();
          cache = new DoubleList();
      }
  
      public int get(int key) {
          if (!map.containsKey(key)) return -1;
          int val = map.get(key).val;
          // 利用 put 方法把该数据提前
          put(key, val);
          return val;
      }
  
      public void put(int key, int val) {
          // 先把新节点 x 做出来
          Node x = new Node(key, val);
          if (map.containsKey(key)) {
              // 删除旧的节点，新的插到头部
              cache.remove(map.get(key));
              cache.addFirst(x);
              // 更新 map 中对应的数据
              map.put(key, x);
          } else {
            	//满了则删除链表最后一个数据
              if (cap == cache.size()) {
                  Node last = cache.removeLast();
                  map.remove(last.key);
              }
              // 直接添加到头部
              cache.addFirst(x);
              map.put(key, x);
          }
      }
  
      static class DoubleList {
        	//确保链表不为空，头结点为first.next
          private Node first = new Node(0, 0);
          private Node end = new Node(0, 0);
          private int size;
  
          public DoubleList() {
              first.next = end;
              end.prev = first;
              size = 0;
          }
  
          // 在链表头部添加节点 x，时间 O(1)
          public void addFirst(Node x) {
              Node temp = first.next;
              first.next = x;//第二个才是插入的头结点
              x.next = temp;
              temp.prev = x;
              x.prev = first;
              size++;
          }
          // 删除链表中的 x 节点（x 一定存在）
          // 由于是双链表且给的是目标 Node 节点，时间 O(1)
          public void remove(Node x) {
              x.next.prev = x.prev;
              x.prev.next = x.next;
              size--;
          }
          // 删除链表中最后一个节点，并返回该节点，时间 O(1)
          public Node removeLast() {
              Node last = end.prev;
              remove(last);
              return last;
          }
  
          // 返回链表长度，时间 O(1)
          public int size() {
              return size;
          }
      }
  
      static class Node {
          public int key, val;
          public Node next, prev;
  
          public Node(int k, int v) {
              this.key = k;
              this.val = v;
          }
      }
  }
  ```

  作者：labuladong
  链接：https://leetcode-cn.com/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/

  

#### [142. 环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

*给定一个链表*，返回链表开始**入环的第一个节点**。 如果链表无环，则返回 `null`

* 解题思路
  - 这类链表题目一般都是使用双指针法解决的，例如寻找距离尾部第K个节点、寻找环入口、寻找公共尾部入口等。
  - slow再走a = 入口 = head走到入口 = a

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        //快慢指针
        //每次移动两步,有环则一定会在环的 某一个位置 超越慢指针
        ListNode fast = head, slow = head;
        while(true){
            if(fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) break;
        }
        // 假如fast == head --> 有环
        fast = head;
        while(fast != slow){
           fast = fast.next; 
           slow = slow.next;
        }
        return fast;
    }
}
```

#### [面试题 02.07. 链表相交](https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/)

*给定两个（单向）链表，判定它们是否相交并返回交点*

* **解题思路**

  * 双指针(快慢指针，前后指针)

    ```java
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
      //双指针:a和b以相同的速度走过相同的路程，如果最后一段路程相同则他们必然在共同路程的入口相遇；
      ListNode a = headA;
      ListNode b = headB;
      while(a != b){
        a = a == null ? headB : a.next;
        b = b == null ? headA : b.next;
      }
      return a;
    }
    ```

#### [206. 反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)

* **解题思路**

  定义两个指针： pre（前） 和 cur(后) ；每次让 cur 的 next 指向 per ，实现一次局部反转，局部反转完成之后，pre 和 cur 同时往前移动一个位置，循环上述过程，直至 pre到达链表尾部

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        //双指针实现
        ListNode per = null;
        ListNode cur = head;
        ListNode temp = null;
        while(cur != null){
            temp = cur.next;
            //改变指向，局部反转
            cur.next = per;
            //指针向前移动
            per = cur;
            cur = temp;
        }
        return per;
    }
}
```

#### [141. 环形链表](https://leetcode-cn.com/problems/linked-list-cycle/)

*给定一个链表，判断链表中是否有环*

* **解题思路**

  1、快慢指针，有环则一定追上；

  2、Set集合或者数组：存在则一定有环

```java
public boolean hasCycle(ListNode head) {
  //快慢指针
  ListNode f = head, s = head;
  while(true){
    if(f == null || f.next == null) return false;
    f = f.next.next;
    s = s.next;
    if(f == s) return true;
  }
}
```

#### [21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

*将两个升序链表合并为一个新的 **升序** 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。*

*  **解题思路**
  * 迭代法：因为有序可以一次遍历两个链表，通过比较，改变指向，其中一个遍历完毕，把另一个指向最后即可

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //迭代法
        ListNode head = new ListNode(-1);
        //维护一个指针，一直指向链表的最后一个位置
        ListNode per = head;
        while(l1 != null && l2 != null){
            if(l1.val >= l2.val){
                per.next = l2;
                l2 = l2.next;
            }else{
                per.next = l1;
                l1 = l1.next;
            }
            //指针后移一位
            per = per.next;
        }
        //链表末尾指向不为空的链表
        per.next = l1 == null ? l2 : l1;
        return head.next;
}
```

* 递归解法

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //递归解法，较小的节点指向其余待合并元素
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        if(l1.val > l2.val){
           l2.next = mergeTwoLists(l1, l2.next);
           return l2;
        }else{
           l1.next =  mergeTwoLists(l1.next, l2);
           return l1;
        }
}
```



## 三、树、二叉树、二叉搜索树

### 1、 解题模板

*  遍历

```java
/* 基本的二叉树节点 */
class TreeNode {
    int val;
    TreeNode left, right;
}
void traverse(TreeNode root) {
    traverse(root.left);
    traverse(root.right);
}
```

* N叉树遍历

```java
/* 基本的 N 叉树节点 */
class TreeNode {
    int val;
    TreeNode[] children;
}
void traverse(TreeNode root) {
    for (TreeNode child : root.children)
        traverse(child);
}
```

### 2、力扣刷题

#### [94. 二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

*给定一个二叉树的根节点 `root` ，返回它的 **中序** 遍历。*

* 解题思路:递归求解

  ```java
  class Solution {
      public List<Integer> inorderTraversal(TreeNode root) {
         List<Integer> res = new ArrayList();
         order(root, res);
         return res;
      }
  
      private void order(TreeNode root, List<Integer> res){
          //中序遍历 做根右
          if(null == root) return;
          order(root.left, res);
          res.add(root.val);
          order(root.right, res);
      }
  }
  ```

  

#### [590. N 叉树的后序遍历](https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/)

*给定一个 N 叉树，返回其节点值的 **后序遍历** 。*

*N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 `null` 分隔（请参见示例）。*

* **解题思路**

  ```java
  class Solution {
      public List<Integer> postorder(Node root) {
         List<Integer> res = new ArrayList();
         inOrder(root, res);
         return res;
      }
  		
      private void inOrder(Node node, List<Integer> res){
          if(null == node) return;
        	//遍历孩子节点
          for(Node n : node.children){
              inOrder(n, res);
          }
          res.add(node.val);
      }
  }
  ```

  

#### [429. N 叉树的层序遍历](https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/)

*给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）*

* 解题思路

  ```java
  class Solution {
      public List<List<Integer>> levelOrder(Node root) {
          List<List<Integer>> res = new ArrayList();
          if(root == null) return res;
          inOrder(root, res, 0);
          return res;
      }
      
      private void inOrder(Node root,List<List<Integer>> res, int level){
          if(root == null) return;
          //遍历当前层
          if(res.size() < level + 1){
              res.add(new ArrayList()); //防止下标越界
          }
          res.get(level).add(root.val);       
          for(Node n : root.children){
              inOrder(n, res, level + 1);//注意不能是level++
          }      
      }
  }
  ```

  

#### [226. 翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)

* 解题思路

  ```java
  class Solution {
      public TreeNode invertTree(TreeNode root) {
        	//递归终止条件  
        	if(null == root) return root;
        	//当前层逻辑：位置交换  
        	TreeNode temp = root.left;
          root.left = root.right;
          root.right = temp;
        	//下探一层
          invertTree(root.left);
          invertTree(root.right);
          return root;
      }
  }
  ```

  

## 四、**栈、队列**

### 1、解题模板

### 2、力扣刷题



## 五、**哈希表、映射、集合**



# 第三章、其他相关知识

## 一、多线程

### 1、常用思路

* Volatile 可见性，自旋锁等待；（类似于等待通知）

* 锁资源:synchronized + wait/notify; lock + condition + await/singal
* CountDownLatch/CyclicBarrier(循环场景)/Semahphore
* 阻塞队列BlockingQueue: put/take

### 2、力扣刷题

#### [1114. 按序打印](https://leetcode-cn.com/problems/print-in-order/)

*我们使用线程等待的方式实现执行屏障，使用释放线程等待的方式实现屏障消除*

* 使用synchronized + wait/notify 实现

  * 锁升级：偏向锁 -> 轻量级锁（自旋锁）->重量级锁 （MarkWord锁标志位）

     * 偏向锁：只有一个线程，无资源竞争，打上线程标签（可重入，偏向于这一个线程）

     * 轻量级锁：大于2个线程产生资源竞争，CAS机制，自旋等待，用户态层面，消耗CPU；

       （自适应自旋，自旋次数大于10或者竞争线程大于多少，JVM优化选择）

    * 重量级锁：需要向操作系统内核申请，开销大，Lock机制，等待，但不浪费CPU

```java
class Foo {
    private boolean firstFinish = false;
    private boolean secondFinish = false;
    Object lock = new Object();
  
    public Foo() {       
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized(lock){
                printFirst.run();
                firstFinish = true;
                //通知所有在等待lock的线程
                lock.notifyAll(); 
        }      
    }

    public void second(Runnable printSecond) throws InterruptedException {   
           synchronized(lock){
               //当1还未执行结束，则自旋等待,防止出现中途跳出
               while(!firstFinish){
                   lock.wait();
               }
             while()
                printSecond.run();
                secondFinish = true;
                lock.notifyAll(); 
            } 
    }

    public void third(Runnable printThird) throws InterruptedException {  
           synchronized(lock){
               //当2还未执行结束则自旋等待
               while(!secondFinish){
                   lock.wait();
               }
               printThird.run(); 
            } 
    }
}
```

* volatile实现
  * 保证可见性，通知其他线程重新从内存中加载缓存数据
  * 禁止指令重排：字节码层面加标识，jvm层面内存屏障，操作系统Lock

```java
class Foo {
  	//保证可见性及禁止指令重排序
  	volatile int count=1;
    public Foo() {    
    }
    
    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        count++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
      //自旋，volatile更新数据后会通知到其他线程获取最新的值 
      while (count!=2);
        printSecond.run();
        count++;
    }

    public void third(Runnable printThird) throws InterruptedException {
      //自旋，volatile更新数据后会通知到其他线程获取最新的值    
      while (count!=3);
        printThird.run();
    }
}
```

* Lock+Condition+await()/signall()实现

```java
class Foo {
    int num;
    Lock lock;
    //精确的通知和唤醒线程
    Condition condition1, condition2, condition3;

    public Foo() {
        num = 1;
        lock = new ReentrantLock();
        condition1 = lock.newCondition();
        condition2 = lock.newCondition();
        condition3 = lock.newCondition();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        try {
          	//自旋等待
            while (num != 1) {
                condition1.await();
            }
            printFirst.run();
            num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            printSecond.run();
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            printThird.run();
            num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
```

* CountDownLatch(减计数器)实现
  * 一个线程等待一组线程执行完再执行，等待线程调用await()方法如果计数器未到达0则一直等待，其余线程执行完后调用countDown()方法会把计数器减一；所有需要等待的线程执行完则计数器为0，等待的线程开始执行；

```java
lass Foo {
    CountDownLatch a;
    CountDownLatch b;

    public Foo() {
        //等待一个线程执行完
        a = new CountDownLatch(1);
        b = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        a.countDown();//执行完-1
    }

    public void second(Runnable printSecond) throws InterruptedException {
        a.await();
        printSecond.run();
        b.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        b.await();
        printThird.run();
    }
}
```

* Semaphore(信号量)
  * Semaphore与CountDownLatch相似，不同的地方在于Semaphore的值被获取到后是可以释放的，并不像CountDownLatch那样一直减到底
  * 获得Semaphore的线程处理完它的逻辑之后，你就可以调用它的Release()函数将它的**计数器重新加1**，这样其它被阻塞的线程就可以得到调用了

```java
class Foo {
    private Semaphore sa;
    private Semaphore sb;
    public Foo() {
        sa = new Semaphore(0);//等待first执行完后再+许可
        sb = new Semaphore(0);  
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        sa.release();//给second加许可，释放一个sa的信号量
    }

    public void second(Runnable printSecond) throws InterruptedException {
        sa.acquire();
        printSecond.run();
        sb.release();//给third加许可
    }

    public void third(Runnable printThird) throws InterruptedException {
        sb.acquire();
        printThird.run();
    }
}
```

* 阻塞队列

  ```java
  class Foo {
      BlockingQueue<String> blockingQueue12, blockingQueue23;
  
      public Foo() {
          //同步队列,没有容量，进去一个元素，必须等待取出来以后，才能再往里面放一个元素
          blockingQueue12 = new SynchronousQueue<>();
          blockingQueue23 = new SynchronousQueue<>();
      }
  
      public void first(Runnable printFirst) throws InterruptedException {
          printFirst.run();
          blockingQueue12.put("stop");
      }
  
      public void second(Runnable printSecond) throws InterruptedException {
          blockingQueue12.take();
          printSecond.run();
          blockingQueue23.put("stop");
      }
  
      public void third(Runnable printThird) throws InterruptedException {
          blockingQueue23.take();
          printThird.run();
      }
  }
  ```

  

#### [1115. 交替打印FooBar](https://leetcode-cn.com/problems/print-foobar-alternately/)

* Semaphore
  在该场景下有点类似红绿灯交替变换的情境，因此信号量成了首选思路：

```java
class FooBar {
    private int n;
    public FooBar(int n) {
        this.n = n;
    }
    Semaphore foo = new Semaphore(1);
    Semaphore bar = new Semaphore(0);

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();
            printBar.run();
            foo.release();
        }
    }
}
```


* Lock（公平锁）
  公平锁也是实现交替执行一个不错的选择：

```java
class FooBar {
    private int n;
    public FooBar(int n) {
        this.n = n;
    }

    Lock lock = new ReentrantLock(true);
    volatile boolean permitFoo = true;

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; ) {
            lock.lock();
            try {
              if(permitFoo) {
                  printFoo.run();
                    i++;
                    permitFoo = false;
              }
            }finally {
              lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; ) {
            lock.lock();
            try {
              if(!permitFoo) {
                  printBar.run();
                  i++;
                  permitFoo = true;
              }
            }finally {
              lock.unlock();
            }
        }
    }
 }
```


* 无锁，volatile
  以上的公平锁方案完全可以改造成无锁方案：

```java
class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    volatile boolean permitFoo = true;

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; ) {
            if (permitFoo) {
                printFoo.run();
                i++;
                permitFoo = false;//下一次一定是要等待其他线程完成修改
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; ) {
            if (!permitFoo) {
                printBar.run();
                i++;
                permitFoo = true;
            }
        }
    }
}
```


* CyclicBarrier

  * CyclicBarrier 可以有不止一个栅栏，因为它的栅栏（Barrier）可以重复使用（Cyclic）

    ![img](https://img-blog.csdnimg.cn/img_convert/0dde2d343b11b15140fcfec3ef247eba.png)

  * 在CyclicBarrier类的内部有一个计数器，每个线程在到达屏障点的时候都会**调用await方法将自己阻塞**，此时计数器会减1，当计数器减为0的时候所有因调用await方法而被阻塞的线程将被唤醒。这就是实现**一组线程相互等待的原理**
  * 在场景一中提过，CyclicBarrier更适合用在循环场景中，那么我们来试一下：

```java
class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    CyclicBarrier cb = new CyclicBarrier(2);
    volatile boolean fin = true;

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!fin) ;//自旋等待，必须要加锁否则下一次任然可能是自己抢占了时间片
            printFoo.run();
            fin = false;
            try {
                cb.await();//阻塞自己，等待其他线程到达屏障点-->到达后进入下一次循环；
            } catch (BrokenBarrierException e) {
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                cb.await();//阻塞自己，等待其他线程到达屏障点-->到达后执行打印逻辑
            } catch (BrokenBarrierException e) {
            }
            printBar.run();
            fin = true;//类似于唤醒在自旋的线程
        }
    }
}
```



* 阻塞队列BlockingQueue

```java
public class FooBar {
    private int n;
    private BlockingQueue<Integer> bar = new LinkedBlockingQueue<>(1);
    private BlockingQueue<Integer> foo = new LinkedBlockingQueue<>(1);
    public FooBar(int n) {
        this.n = n;
    }
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.put(i);//在take前都只能阻塞
            printFoo.run();
            bar.put(i);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.take();
            printBar.run();
            foo.take();//执行完释放，类似于通知
        }
    }
}
```



#### [1188. 设计有限阻塞队列](https://leetcode-cn.com/problems/design-bounded-blocking-queue/)

* synchronized + wait/notify  + 链表

```java
class BoundedBlockingQueue {
    //通过链表实现，可以从头结点添加，尾结点删除
    private LinkedList<Integer> list;
    private int capacity;
    private volatile int size;

    Object lock = new Object();

    public BoundedBlockingQueue(int capacity) {
        this.list = new LinkedList();
        this.capacity = capacity;
        this.size = 0;
    }
    
    public void enqueue(int element) throws InterruptedException {
        synchronized(lock){
            while(size + 1 > capacity) lock.wait();//自旋等待
            size++;
            list.addFirst(element);
            lock.notify();
        }
    }
    
    public int dequeue() throws InterruptedException {
        synchronized(lock){
           while(size <= 0) lock.wait();//自旋等待 
           int res = list.removeLast();
           size--;
           lock.notify();
           return res;
        }
    }
    
    public int size() {
        return size;
    }
}
```

