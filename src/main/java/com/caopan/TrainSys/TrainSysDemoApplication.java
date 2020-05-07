package com.caopan.TrainSys;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan( "com.caopan.TrainSys.biz.dao")
public class TrainSysDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainSysDemoApplication.class, args);
    }

//    @Bean
//    public Redisson redisson() {
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
//        return (Redisson) Redisson.create(config);
//    }
}
