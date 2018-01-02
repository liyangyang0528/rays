package com.lyyco.rays.service.spark;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
/**
 * Created by lyy on 2017/12/27.
 */
public class HelloSpark {
    org.apache.spark.SparkConf conf = new org.apache.spark.SparkConf().setAppName("lyy").setMaster(" http://192.168.137.1:4040");
    JavaSparkContext sc = new JavaSparkContext(conf);
    JavaRDD<String> distFile = sc.textFile("");
}
