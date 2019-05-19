package com.marshal.halcyon.core.generator.service;


import com.marshal.halcyon.core.generator.dto.GeneratorInfo;

import java.util.List;


public interface HalcyonGeneratorService {
    List<String> showTables();

    void generatorFile(GeneratorInfo info) throws Exception;

}
