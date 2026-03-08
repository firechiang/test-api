package com.paipai.core.service.impl;

import com.paipai.core.service.PUserService;
import com.paipai.data.entity.PUser;
import com.paipai.data.mapper.PUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PUserServiceImpl implements PUserService {

    @Autowired
    private PUserMapper puserMapper;

    @Override
    public PUser queryByPhone(String phone) {
        return puserMapper.selectByPhone(phone);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyByPrimaryKeySelective(PUser puser) {
        int res = puserMapper.updateByPrimaryKeySelective(puser);
        return res == 0 ? false : true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyUserByPrimaryKey(PUser puser) {
        int res = puserMapper.updateUserByPrimaryKey(puser);
        return res == 0 ? false : true;
    }

}
