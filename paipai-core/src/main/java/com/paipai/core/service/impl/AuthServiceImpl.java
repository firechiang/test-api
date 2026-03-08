package com.paipai.core.service.impl;

import com.paipai.core.service.AuthService;
import com.paipai.data.entity.PUser;
import com.paipai.data.mapper.PUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PUserMapper puserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(String phone, String pwd) {
        PUser puser = PUser.builder().phone(phone).pwd(pwd).createTime(Instant.now().getEpochSecond()).build();
        int res = puserMapper.insert(puser);
        return res > 0 ? true : false;
    }
}
