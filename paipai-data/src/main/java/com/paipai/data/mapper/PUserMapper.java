package com.paipai.data.mapper;


import com.paipai.data.entity.PUser;

public interface PUserMapper {

    int insert(PUser puser);

    int updateByPrimaryKeySelective(PUser pUser);

    int updateUserByPrimaryKey(PUser puser);

    PUser selectByPhone(String phone);
}