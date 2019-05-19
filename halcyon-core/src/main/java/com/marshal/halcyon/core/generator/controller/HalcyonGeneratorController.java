package com.marshal.halcyon.core.generator.controller;


import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.core.entity.ResponseData;
import com.marshal.halcyon.core.generator.dto.GeneratorInfo;
import com.marshal.halcyon.core.generator.service.HalcyonGeneratorService;
import com.marshal.halcyon.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * halcyon代码生成器
 */
@RestController
@RequestMapping(value = "/generator")
public class HalcyonGeneratorController extends BaseController {

    @Autowired
    HalcyonGeneratorService service;

    /**
     * 获取数据库中的所有表
     *
     * @return
     */
    @GetMapping(value = "/alltables")
    public ResponseData showTables() {
        List<String> list = service.showTables();
        List<Map<String, String>> resultMap = list.stream().map(item -> {
            Map<String, String> map = new HashMap<>();
            map.put("id", item);
            map.put("text", item);
            return map;
        }).collect(Collectors.toList());
        return new ResponseData(resultMap);
    }

    /**
     * 生成文件
     *
     * @param generatorInfo
     * @return
     */
    @RequestMapping(value = "/create")
    public ResponseData generatorTables(@RequestBody GeneratorInfo generatorInfo) throws Exception {
        service.generatorFile(generatorInfo);
        return ResponseUtil.responseOk("生成文件成功!");
    }

}
