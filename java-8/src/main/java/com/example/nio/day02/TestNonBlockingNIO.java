package com.example.nio.day02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

/*
 * 一、使用 NIO 完成网络通信的三个核心：
 * 
 * 1. 通道（Channel）：负责连接
 * 		
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 * 
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 * 
 * 2. 缓冲区（Buffer）：负责数据的存取
 * 
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 */

/**
 *
 * NIO中的selector是阻塞的，为什么效率就更高了？
 * 对比传统的阻塞IO，从原来的读写阻塞，转换到了selector的阻塞，请问，这个效率不是应该更低了吗？
 * 假设有8个读数据请求过来，他不是要在这个地方阻塞8次吗？如果更大的并发的情况，岂不是很容易形成瓶颈？
 *
 *
 * selector.select（）可以不阻塞
 * selector.select（1000），不阻塞
 * selector.wakeup()也可以唤醒selector
 *
 * selector也可以设置成不阻塞的，大体原理上来说,他可以搞一个轮询机制去检查是否有可以满足事件的通道可以使用
 * 比如每隔1000ms，不满足期间你可以干别的事.个人认为:jvm调os做文件操作，os完成后返回jvm结果，
 * jvm去维护selector,用户线程只是去查询selector是否有可操作的channel而已，而这期间，
 * 用户线程永远可以不阻塞,当然你也可以自己一直阻塞在那，比如你没别的事干。。
 *
 * 8个连接只用了一个线程阻塞，假设其中五个连接可读，方法直接返回这5个连接，
 * 通过Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
 * 分别读取即可，而socketChannel.read方法不阻塞，通过一个线程可以处理多个连接，
 * 而传统阻塞IO需要8个线程。这就是多路复用解决连接数多的情况下线程太多线程切换导致性能低下，
 * 而且线程的数量是有限制的。
 *
 * Selector：NIO核心的东西，负责监听ServerSocketChannel、SocketChannel。NIO是可以实现单线程为多个客户端服务的。传统IO是做不到的， 传统IO要多线程才行。
 */
public class TestNonBlockingNIO {
	
	//客户端
	@Test
	public void client() throws IOException{
		//1. 获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		
		//TODO 2. 切换非阻塞模式
		sChannel.configureBlocking(false);
		
		//3. 分配指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		//4. 发送数据给服务端
		Scanner scan = new Scanner(System.in);
		
		while(scan.hasNext()){
			String str = scan.next();
			buf.put((new Date().toString() + "\n" + str).getBytes());
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}
		
		//5. 关闭通道
		sChannel.close();
	}

	//服务端
	@Test
	public void server() throws IOException{
		//1. 获取通道
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		
		//TODO 2. 切换非阻塞模式
		ssChannel.configureBlocking(false);
		
		//3. 绑定连接
		ssChannel.bind(new InetSocketAddress(9898));
		
		//4. 获取选择器
		Selector selector = Selector.open();
		
		//5. 将通道注册到选择器上, 并且指定“监听接收事件”
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		//6. 轮询式的获取选择器上已经“准备就绪”的事件  selector.select()返回准备就绪事件的个数
		while(selector.select() > 0) {

			//7. 获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();

			while (it.hasNext()) {
				//8. 获取准备“就绪”的事件
				SelectionKey sk = it.next();

				//9. 判断具体是什么事件准备就绪
				if (sk.isAcceptable()) {
					//10. 若“接收就绪”，获取客户端连接
					SocketChannel sChannel = ssChannel.accept();

					//11. 切换非阻塞模式
					sChannel.configureBlocking(false);

					//12. 将该通道注册到选择器上
					sChannel.register(selector, SelectionKey.OP_READ);
				} else if (sk.isReadable()) {
					//13. 获取当前选择器上“读就绪”状态的通道
					SocketChannel sChannel = (SocketChannel) sk.channel();

					//14. 读取数据
					ByteBuffer buf = ByteBuffer.allocate(1024);

					int len = 0;
					while ((len = sChannel.read(buf)) > 0) {
						buf.flip();
						System.out.println(new String(buf.array(), 0, len));
						buf.clear();
					}
				}

				//15. 取消选择键 SelectionKey
				it.remove();
			}
		}
	}
}
