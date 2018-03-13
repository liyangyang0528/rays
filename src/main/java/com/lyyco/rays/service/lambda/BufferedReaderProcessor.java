package com.lyyco.rays.service.lambda;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Description
 * @Author Created by lyy
 * @Date: Created in 15:02 2018/3/12
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
