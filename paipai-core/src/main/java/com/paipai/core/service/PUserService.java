package com.paipai.core.service;

import com.paipai.data.entity.PUser;

public interface PUserService {

    PUser queryByPhone(String phone);

    boolean modifyByPrimaryKeySelective(PUser puser);

    boolean modifyUserByPrimaryKey(PUser puser);
}
