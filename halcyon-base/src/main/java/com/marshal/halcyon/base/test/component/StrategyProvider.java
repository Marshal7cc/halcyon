package com.marshal.halcyon.base.test.component;

import com.marshal.halcyon.core.util.ApplicationContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/6/1
 * @desc:
 */
@Component
public class StrategyProvider {

    Map<String, GenerateStrategy> strategyMap = new HashMap<>();

    GenerateStrategy getStrategy(String identity) {
        return strategyMap.get(identity);
    }


    @PostConstruct
    public void init() {
        Map<String, GenerateStrategy> beans = ApplicationContextHolder.getApplicationContext().getBeansOfType(GenerateStrategy.class);
        beans.forEach((k, v) -> {
            strategyMap.put(v.getIdentifier(), v);
        });
    }
}
