package com.lyyco.rays.service.concurrent.CompletableFuture_java8;


import java.util.List;

/**
 * 这款代码
 * Author liyangyang
 * 2018/4/12
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote){
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(),quote.getDiscountCode());
    }


    private static String apply(double price, Code discountCode) {
        delay();//模拟延迟
        return format(price * (100 - discountCode.percentage)/100);
    }

    private static String format(double v) {

    }

    private static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
