package com.lyyco.rays.service.concurrent.CompletableFuture_java8;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                apply(quote.getPrice(), quote.getDiscountCode());
    }

    public List<String> findPrices(String product) {
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("four"),
                new Shop("five"));
        shops
                .stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());

        shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(
                          () -> shop.getPrice(product)))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                          CompletableFuture.supplyAsync(
                              ()-> applyDiscount(quote)))
                  .collect(toList());


        return null;

    }


    private static String apply(double price, Code discountCode) {
        delay();//模拟延迟
        return format(price * (100 - discountCode.percentage) / 100);
    }

    private static String format(double v) {

        return null;
    }

    private static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
