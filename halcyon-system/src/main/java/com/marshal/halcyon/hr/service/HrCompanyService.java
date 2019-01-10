package com.marshal.halcyon.hr.service;

import com.marshal.halcyon.hr.entity.HrCompany;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/1/9
 * @desc:
 */
public interface HrCompanyService {

    List<HrCompany> queryCompanyInfo();

    HrCompany saveCompanyInfo(HrCompany hrCompany);
}
