package com.luo.space.service;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author luozhengchao
 * @date 2021/9/5 9:52 上午
 */
@Component
@Getter
public class SpaceConfig {

    private String name;

    public SpaceConfig(String name) {
        this.name = name;
        System.out.println("youcan");
    }

    public SpaceConfig() {
        System.out.println("wucan");
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
    }


}
