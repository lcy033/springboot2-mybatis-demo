package com.example.arithmetic;

/**
 * @author : LCY
 * @date : create in 2021/2/18 7:10 下午
 */
public class Arrays {

    // 二维数组 稀疏数组
    private static void getArrays() {
        //创建一个原始的二维数11*11
        //0表示没有旗子，1表示黑子，2表示蓝子
        //这里在程序中手动输入棋子...low一点.
        int chessArr1[][] = new int[11][11];
        chessArr1[1][4] = 1;
        chessArr1[2][5] = 2;
        chessArr1[3][1] = 2;
        //输出原始的二维数组
        for (int[] row : chessArr1) {
            for (int data : row) {
//                System.out.println("原始的二维数组");
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //1.先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //将得到的值放入稀疏数组
        int spareseArr[][] = new int[sum + 1][3];//sum是一共有多少个棋
        //给稀数组赋值
        //这是稀疏数组的第一行，也就是存放标准二维数组（棋盘）信息的地方
        spareseArr[0][0] = 11;//有多少行
        spareseArr[0][1] = 11;//有多少列
        spareseArr[0][2] = sum;//必须拿到sum才能创建数组（sum在棋盘上有几个棋子）


        //稀疏数组已经创建过了.spareseArr[][]
        //int chessArr1[][] = new int[11][11];//二维数组(棋盘)
        //遍历二维数组，将非0的值放到sparseArr中
        int count = 0;//count 用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    //count是从1开始的,并不存在第0个棋子,但是却存在数组中的第0个元素,正好和第一行错开.
                    count++;//代表的是第几个棋子。  spareseArr[count]中存放的一维数组中存放的内容就是这个棋子的信息。
                    spareseArr[count][0] = i;//第一列，存放（非零数据）棋子在普通数组（棋盘）中的位置（行数）
                    spareseArr[count][1] = j;//第二列，存放（非零数据）棋子在普通数组（棋盘）中的位置（列数）
                    spareseArr[count][2] = chessArr1[i][j];//第三列，存放（非零数据）数据。（是黑棋蓝棋）
                }
            }
        }


        //将稀疏数组--》恢复成二维数组
        /**
         * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的chressArray
         * 2.在读取稀疏数组后几行的数据，并赋给原始的二维数组即可。
         */
        //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        //稀疏数组第一列是普通二维数组的行，第二列是普通数组的列
        int chessArr2[][] = new int[spareseArr[0][0]][spareseArr[0][1]];//普通数组
        //2.在读取稀疏数组后几行的数据（从第二行开始），并付给原始的二维数组即可

        //之所以从1开始，是因为稀疏矩阵第一行存的是普通数组的信息
        for (int i = 1; i < spareseArr.length; i++) {
            chessArr2[spareseArr[i][0]][spareseArr[i][1]] = spareseArr[i][2];
            //chessArr2[spareseArr[i][0]非零数据所在行][spareseArr[i][1]非零数据所在列]=spareseArr[i][2]非零数据的值。
        }
        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {

                //关于printf相关知识：https://blog.csdn.net/qq_39017218/article/details/80042287
                System.out.printf("%d\t", data);

            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Arrays.getArrays();
    }

}
