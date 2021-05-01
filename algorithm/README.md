# daydayup

#### 1、递归

```java
   public void recur(int level, int param){
        //跳出判断
        if(level > MAX){
            return ;
        }
        //执行本层逻辑
        proce（level,param）;
        //递归
        recur(level + 1, newParam);
    }
```
#### 2、分治

