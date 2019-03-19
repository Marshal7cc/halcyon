package com.marshal.halcyon.base.hr.service.impl;

import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
import com.marshal.halcyon.base.hr.entity.HrCompany;
import com.marshal.halcyon.base.hr.mapper.HrCompanyMapper;
import com.marshal.halcyon.base.hr.service.HrCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auth: Marshal
 * @date: 2019/1/9
 * @desc:
 */
@Service
public class HrCompanyServiceImpl extends BaseServiceImpl<HrCompany> implements HrCompanyService {

    @Autowired
    HrCompanyMapper hrCompanyMapper;

}
