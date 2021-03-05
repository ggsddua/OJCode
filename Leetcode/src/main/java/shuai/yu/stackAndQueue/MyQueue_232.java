package shuai.yu.stackAndQueue;

import java.util.Stack;

/**
 * 用栈实现队列
 *
 * @author shuai.yu
 * @version 2021/03/05
 */
public class MyQueue_232
{
    // 存
    Stack<Integer> stack1;
    // 取
    Stack<Integer> stack2;

    /**
     * Initialize your data structure here.
     */
    public MyQueue_232()
    {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x)
    {
        if (stack1.isEmpty())
        {
            while (!stack2.isEmpty())
            {
                stack1.push(stack2.pop());
            }
            stack1.push(x);
        }
        else
        {
            stack1.push(x);
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop()
    {
        if (stack2.isEmpty())
        {
            while (!stack1.isEmpty())
            {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
        else
        {
            return stack2.pop();
        }
    }

    /**
     * Get the front element.
     */
    public int peek()
    {
        if (stack2.isEmpty())
        {
            while (!stack1.isEmpty())
            {
                stack2.push(stack1.pop());
            }
            return stack2.peek();
        }
        else
        {
            return stack2.peek();
        }
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty()
    {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
