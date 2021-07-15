package com.example;

import java.util.*;
import java.util.stream.Collectors;

// 使用程序生成长度18的整数数组，形如
// [
//   16, 27, 61, 95, 8, 21, 86,
//   66, 81, 76, 81, 0, 36, 58,
//   22, 90, 96, 67
// ]

// 将生成的数组处理成一个新数组，要求新数组格式如下，形如
// [
//   [ 0, 8 ],
//   [ 16 ],
//   [ 21, 22, 27 ],
//   [ 36 ],
//   [ 58 ],
//   [ 61, 66, 67 ],
//   [ 76 ],
//   [ 81, 86 ],
//   [ 90, 95, 96 ]
// ]

//注：先说下思路，可以直接在下方写java代码，可以使用任何内置方法，点击左上角运行按钮随时调试

public class TwoDimensionalArray {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 18; i++) {
            list.add(random.nextInt(100));
        }

        //int [][] arr=new int[][]{{4,5,6,8},{2,3},{1,6,9}};
        String[][] arr = new String[10][18];

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            if (list.get(i) < 10) {
                arr[0][i] = list.get(i).toString();
                continue;
            }
            if (list.get(i) < 20) {
                arr[1][i] = list.get(i).toString();
                continue;
            }
            if (list.get(i) < 30) {
                arr[2][i] = list.get(i).toString();
                continue;
            }
            if (list.get(i) < 40) {
                arr[3][i] = list.get(i).toString();
                continue;
            }
            if (list.get(i) < 50) {
                arr[4][i] = list.get(i).toString();
                continue;
            }

            if (list.get(i) < 60) {
                arr[5][i] = list.get(i).toString();
                continue;
            }

            if (list.get(i) < 70) {
                arr[6][i] = list.get(i).toString();
                continue;
            }

            if (list.get(i) < 80) {
                arr[7][i] = list.get(i).toString();
                continue;
            }

            if (list.get(i) < 90) {
                arr[8][i] = list.get(i).toString();
                continue;
            }
            if (list.get(i) < 100) {
                arr[9][i] = list.get(i).toString();
                continue;
            }
        }

        System.out.println("[");
        for (String[] ints : arr) {
            List<String> collect = Arrays.stream(ints).filter(Objects::nonNull).collect(Collectors.toList());
            if (!collect.isEmpty()) {
                Collections.sort(collect);
                System.out.print("[");
                System.out.print(String.join(",", collect));
                System.out.println("]");
            }
        }
        System.out.println("]");
    }
}
