package com.arithmetic.stack_queue;

//用栈实现队列

import java.util.Stack;

/**
 * 如何用栈实现队列？
 * 提示下：用一个栈肯定是没办法实现队列，但如果我们有两个栈呢?
 *
 *  让一个栈作为队列的入口，负责插入新元素；另外一个栈作为队列的出口，负责移除老元素。
 *
 */

public class StackImplQueue {

    /**
     * 定义两个栈来实现队列
     * 栈A 负责插入新元素
     * 栈B 负责移除老元素
     */
    private Stack<Integer> stackA = new Stack<>();
    private Stack<Integer> stackB = new Stack<>();

    /**
     * 入队操作
     * @Param element
     */
    public void enQueue(int element){
        stackA.push(element);
    }

    /**
     *
     * 出队操作
     */
    public Integer deQueue(){
        if (stackB.isEmpty()){
            if (stackA.isEmpty()){
                return null;
            }
            fetchFormStackA();
        }

        return stackB.pop();
    }

    /**
     * 从stackA栈中拿到出栈元素压入栈B
     */
    private void fetchFormStackA() {
        while (!stackA.isEmpty()){
            stackB.push(stackA.pop());
        }
    }

    public static void main(String[] args) {
        StackImplQueue stackQueue = new StackImplQueue();
        stackQueue.enQueue(1);
        stackQueue.enQueue(2);
        stackQueue.enQueue(3);

        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());

        stackQueue.enQueue(4);
        System.out.println(stackQueue.deQueue());
    }
}
