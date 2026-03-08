package com.paipai.core.service.impl;

import com.paipai.core.service.PProjectNewsService;
import com.paipai.data.dto.ProjectNewsListDto;
import com.paipai.data.entity.PProjectNews;
import com.paipai.data.mapper.PProjectNewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PProjectNewsServiceImpl implements PProjectNewsService {

    @Autowired
    private PProjectNewsMapper pprojectNewsMapper;

    @Override
    public PProjectNews queryById(Long id) {
        return pprojectNewsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PProjectNews> queryByPageable(ProjectNewsListDto dto) {

        return pprojectNewsMapper.selectByPageable(dto);
    }
}
