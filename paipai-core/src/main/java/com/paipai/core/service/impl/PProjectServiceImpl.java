package com.paipai.core.service.impl;

import com.paipai.core.service.PProjectService;
import com.paipai.data.dto.ProjectListDto;
import com.paipai.data.entity.PProject;
import com.paipai.data.entity.PProjectSub;
import com.paipai.data.mapper.PProjectMapper;
import com.paipai.data.mapper.PProjectSubMapper;
import com.paipai.data.vo.PProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class PProjectServiceImpl implements PProjectService {

    @Autowired
    private PProjectMapper pprojectMapper;

    @Autowired
    private PProjectSubMapper pprojectSubMapper;

    @Override
    public PProject queryById(Long id) {
        return pprojectMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PProjectVo> queryProjectVoByPageable(ProjectListDto dto) {
        if(!StringUtils.hasText(dto.getKeywords())) {
            dto.setKeywords(null);
        }
        return pprojectMapper.selectProjectVoByPageable(dto);
    }

    /**
     * 添加订阅
     *
     * @param projectId
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addSub(Long projectId, Long userId) {
        PProjectSub pprojectSub = PProjectSub.builder().projectId(projectId).userId(userId).createTime(Instant.now().getEpochSecond()).build();
        int res = pprojectSubMapper.insert(pprojectSub);
        if (res > 0) {
            pprojectMapper.updateAddSubCount(projectId);
        }
        return 1;
    }

    /**
     * 取消订阅
     *
     * @param projectId
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int unSub(Long projectId, Long userId) {
        PProjectSub pprojectSub = PProjectSub.builder().projectId(projectId).userId(userId).build();
        int res = pprojectSubMapper.deleteBy(pprojectSub);
        if (res > 0) {
            pprojectMapper.updateUnSubCount(projectId);
        }
        return 1;
    }

    @Override
    public boolean exists(Long id) {
        Long projectId = pprojectMapper.selectId(id);
        return Objects.nonNull(projectId) ? true : false;
    }

}



