package com.marshal.halcyon.base.hr.controller;

import com.marshal.halcyon.core.entity.ResponseData;
import com.marshal.halcyon.core.controller.BaseController;
import com.marshal.halcyon.base.hr.entity.HrCompany;
import com.marshal.halcyon.base.hr.service.HrCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/1/9
 * @desc:
 */
@RestController
@RequestMapping("/hr/company")
public class HrCompanyController extends BaseController {

    @Autowired
    HrCompanyService hrCompanyService;

    @RequestMapping("/query")
    public List<HrCompany> query() {
        return hrCompanyService.selectAll();
    }

    @RequestMapping("/save")
    public ResponseData save(@RequestBody HrCompany hrCompany) {
        hrCompanyService.save(hrCompany);
        return new ResponseData();
    }
}
