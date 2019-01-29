package com.lyyco.rays.service.algorithm.toolbox;

import java.util.*;
import java.io.*;

/**
 * Author liyangyang
 * 2019/1/28
 */
public class MaxPairwiseProduct {
    static long getMaxPairwiseProduct(int[] numbers) {
        long max_product = 0;
        int n = numbers.length;
        int max_index = 0;
        for (int i = 0; i < n; i++) {
            if (numbers[max_index] < numbers[i]) {
                max_index = i;
            }
        }
        int second_max_index = 0;
        for (int i = 0; i < n; i++) {
            if (numbers[second_max_index] < numbers[i] && numbers[i] != numbers[max_index]) {
                second_max_index = i;
            }
        }
        max_product = (long) numbers[max_index] * (long) numbers[second_max_index];
        return max_product;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                        InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
