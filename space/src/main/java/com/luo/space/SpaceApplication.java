package com.luo.space;

import com.luo.space.service.SpaceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpaceApplication {

    public static void main(String[] args) {
        var run = SpringApplication.run(SpaceApplication.class, args);
        var bean = run.getBean(SpaceConfig.class);
    }

}
