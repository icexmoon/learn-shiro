package cn.icexmoon.bootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 关于 Spring Boot 整合 Shiro 的示例项目
 */
@SpringBootApplication
@MapperScan("cn.icexmoon.bootdemo.mapper")
public class BootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootDemoApplication.class, args);
    }

}
