package com.lyyco.rays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RaysApplicationTests {

	@Test
	public void contextLoads() {

	}

	public static void main(String...args){
		List test = new ArrayList(4);
		ExecutorService executor = Executors.newFixedThreadPool(4);
		executor.execute(()->test.add(1));
	}



}
