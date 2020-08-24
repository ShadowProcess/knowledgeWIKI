package com.arithmetic.ladder;


import java.util.Scanner;

/*
一个台阶总共有n级，如果一次可以跳1级，也可以跳2级，求总共有多少种跳法。

如果整个台阶只有1级，则显然只有一种跳法。如果台阶有2级，则有两种跳法：一种是分两次跳，每次跳1级；另一种是一次跳2级。

推广到一般情况。则可以把n级台阶时的跳法看成是n的函数，记为f(n)。当n > 2时，第一次跳一级还是两级，决定了后面剩下的台阶的跳法数目的不同：
***如果第一次只跳一级，则后面剩下的n-1级台阶的跳法数目为f(n-1)；
***如果第一次跳两级，则后面剩下的n-2级台阶的跳法数目为f(n-2)。
因此，当n > 2时，n级台阶的不同跳法的总数f(n) = f(n-1) + f(n-2)。其中f(1) = 1,f(2) = 2。

追本溯源，上述问题的本质就是斐波那契数问题。
 */
public class LadderDemo {

    //解法1：递归
    public static int jumpFloor(int target) {
        if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            int sum = jumpFloor(target - 1) + jumpFloor(target - 2);
            return sum;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int target = scan.nextInt();
        System.out.println(jumpFloor(target));
    }


    //解法2：迭代
    public int getRecursion(int n){
        if(n < 0)
            return -1;
        if(n <= 2)
            return n;
        int temp1 = 1;
        int temp2 = 2;
        int result = 0;
        for(int i = 3;i <= n;i++){
            result = temp1 + temp2;
            temp1 = temp2;
            temp2 = result;
        }
        return result;
    }

}
