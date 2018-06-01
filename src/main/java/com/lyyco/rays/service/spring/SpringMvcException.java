package com.lyyco.rays.service.spring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 将自定义的异常映射到状态码
 * Author liyangyang
 * 2018/6/1
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "bad")
public class SpringMvcException extends RuntimeException{

}
