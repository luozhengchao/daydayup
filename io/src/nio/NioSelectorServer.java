package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * @Author luozhengchao
 * @Date 2021/4/3 下午5:43
 */
public class NioSelectorServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        //绑定端口
        socketChannel.socket().bind(new InetSocketAddress(9000));
        //设置为非阻塞
        socketChannel.configureBlocking(false);


        //打开selector处理channel，即创建epoll(epoll_create)
        Selector selector = Selector.open();

        //socketChannel  注册到selector上，每个key对应一个channel
        /**
         * selector/epoll 包含两个集合
         * channel(fd?)集合
         * 就绪列表事件集合  操作系统触发放到
         */
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        while (true) {
            //阻塞等待事件发生（accept连接 读写等io事件）
            //不会遍历所有注册的channel,只会遍历有事件发生的channel
            /**
             * Epoll函数：
             * epoll_create创建
             * epoll_ctl
             * 1、注册事件，添加到事件表（只在 epoll_ctl 的时候把数据复制到内核空间，这保证了每个描述符和事件一定只会被复制到内核空间一次，相比之下，select 每次调用都会把三个 fd_set 复制一遍）
             * 2、调用 epoll_ctl 时，就是注册了一个事件：在集合中放入文件描述符以及事件数据，并且加上一个回调函数
             * 3、一旦文件描述符上的对应事件发生，就会调用回调函数，这个函数会把这个文件描述符加入到就绪队列上
             * epoll_wait
             * 1、等待事件（epoll_wait 返回 n ，那么只需要做 n 次循环，可以保证遍历的每一次都是有意义的）
             * 2、epoll_wait 时，它只是在查看就绪队列上是否有内容，有的话就返回给你的程序
             */
            selector.select();

            //获取selector中注册的全部时间的key实例
            selectionKeys.stream().forEach(
                    key -> {
                        //连接事件
                        if (key.isAcceptable()){

                        }
                    }
            );


        }

    }
}
