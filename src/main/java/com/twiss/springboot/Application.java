package com.twiss.springboot;

import com.twiss.springboot.config.DruidDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Twiss
 * @Date: 2021/3/3 11:12 下午
 * @RestController
 * 这是一个spring mvc的controller
 * 同时@RestController默认就是支持将返回值直接给浏览器的，而不是去渲染视图
 *
 * @EnableAutoConfiguration
 * auto configuration，是spring boot最最重要和核心的功能之一
 * spring boot的核心思想，就是不要去做太多的xml配置，全部基于约定的一些规则，自动完成一些配置
 * auto configuration，就会根据我们引入的一些依赖，比如引入spring-boot-starter-web，
 * 就会根据我们要开发web程序的特点，自动完成tomcat服务器等相关的web配置
 */

/**
 * 这个注解相当于@Configuration+@EnableAutoConfiguration+@ComponenScan的组合
 * 一般开发中，就是直接使用@SpringBootApplication
 */
@SpringBootApplication
/**
 * 将数据源配置类，导进来，相当于以前搞多个xml的时候，将多个xml导入一个总的xml中
 */
@Import(DruidDataSourceConfig.class)
public class Application {
    @RequestMapping("/hello/{name}")
    public String sayHello(@PathVariable String name){
        return "hello, "+name+", this is spring boot.";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
