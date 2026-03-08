package com.paipai.core.service.impl;

import com.paipai.core.service.PCompanyService;
import com.paipai.data.entity.PCompany;
import com.paipai.data.mapper.PCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PCompanyServiceImpl implements PCompanyService {

    @Autowired
    private PCompanyMapper pcompanyMapper;

    @Override
    public PCompany queryById(Long id) {
        return pcompanyMapper.selectByPrimaryKey(id);
    }
}
