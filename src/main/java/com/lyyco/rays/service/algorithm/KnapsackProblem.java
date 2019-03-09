package com.lyyco.rays.service.algorithm;

public class KnapsackProblem {
    public static void main(String[] args) {
        int maxCapacity = 10;
        int numOfProducts = 3;
        int weight[] = {3, 4, 5};
        int price[] = {4, 5, 6};
        int record[][] = solve(maxCapacity, numOfProducts, weight, price);
        for (int i = 1; i <=numOfProducts; i++) {
            for (int j = 1; j <=maxCapacity; j++) {
                System.out.print(record[i][j]+"\t");
                if(j==maxCapacity){
                    System.out.println();
                }
            }
        }
    }

    /**
     *
     * @param maxCapacity 表示背包的最大容量
     * @param numOfProducts 表示商品个数
     * @param weight 表示商品重量数组
     * @param price 表示商品价值数组
     * @return
     */
    public static int[][] solve(int maxCapacity,int numOfProducts,int[]weight,int[]price){
        //用于记录，record[i][j]表示前i件物品刚好放入maxCapacity重量的背包中，可以获得的最大价值
        int record[][] = new int[numOfProducts+1][maxCapacity+1];
        for(int i =0;i<numOfProducts+1;i++){
            record[i][0]= 0;
        }
        //0件物品价值为0
        for(int j=0;j<maxCapacity+1;j++){
            record[0][j] = 0;
        }
        for(int i =1;i<numOfProducts+1;i++){
            for(int j =1;j<maxCapacity+1;j++){
                /*
                当物品为i件重量为j时，如果第i件的重量(w[i-1])小于重量j时，c[i][j]为下列两种情况之一：
                (1)物品i不放入背包中，所以c[i][j]为c[i-1][j]的值
                (2)物品i放入背包中，则背包剩余重量为j-w[i-1],所以c[i][j]为c[i-1][j-w[i-1]]的值加上当前物品i的价值
                 */
                if(weight[i-1] <= j){
                    if(record[i -1][j] < (record[i-1][j-weight[i-1]] + price[i-1])){
                        record[i][j] = record[i-1][j-weight[i-1]] + price[i-1];
                    }else {
                        record[i][j] = record[i-1][j];
                    }
                }else {
                    record[i][j] = record[i-1][j];
                }
            }
        }
    return record;
    }
}
