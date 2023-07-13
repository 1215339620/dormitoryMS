package com.dormitoryms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dormitoryms.entity.DManager;
import com.dormitoryms.form.ChangeForm;
import com.dormitoryms.form.RuleForm;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.mapper.DManagerMapper;
import com.dormitoryms.service.DManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dormitoryms.vo.PageVO;
import com.dormitoryms.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-04-04
 */
@Service
public class DManagerServiceImpl extends ServiceImpl<DManagerMapper, DManager> implements DManagerService {
    @Autowired
    private DManagerMapper dManagerMapper;

    @Override
    public ResultVO login(RuleForm ruleForm) {
//        判断用户名是否存在
        QueryWrapper<DManager> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", ruleForm.getUsername());
        DManager dManager = this.dManagerMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        if (dManager == null) {
            resultVO.setCode(-1);
        } else {
            //2.判断密码是否正确
            if (!dManager.getPassword().equals(ruleForm.getPassword())) {
                resultVO.setCode(-2);
            } else {
                resultVO.setCode(0);
                resultVO.setData(dManager);
            }
        }
        return resultVO;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<DManager> dManagerPage=new Page<>(page,size);
        Page<DManager> managerPage = this.dManagerMapper.selectPage(dManagerPage, null);
        PageVO pageVO=new PageVO();
        pageVO.setTotal(managerPage.getTotal());
        pageVO.setData(managerPage.getRecords());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        //模糊查询+分页
        Page<DManager> dormitoryAdminPage = new Page<>(searchForm.getPage(),searchForm.getSize());
        Page<DManager> resultPage = null;
        if(searchForm.getValue().equals("")){
            resultPage = this.dManagerMapper.selectPage(dormitoryAdminPage, null);
        } else {
//            数据库查询
            QueryWrapper<DManager> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = this.dManagerMapper.selectPage(dormitoryAdminPage,queryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public boolean changePassword(ChangeForm changeForm) {
        DManager dManager = this.dManagerMapper.selectById(changeForm.getId());
        dManager.setPassword(changeForm.getNewPass());
        int update = this.dManagerMapper.updateById(dManager);
        return update==1;
    }

}
