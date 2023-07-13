package com.dormitoryms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dormitoryms.entity.Admin;
import com.dormitoryms.entity.DManager;
import com.dormitoryms.form.ChangeForm;
import com.dormitoryms.form.RuleForm;
import com.dormitoryms.mapper.AdminMapper;
import com.dormitoryms.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dormitoryms.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-04-04
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public ResultVO login(RuleForm ruleForm) {
        //判断用户名是否存在
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", ruleForm.getUsername());
        Admin admin = this.adminMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        if (admin == null) {
            resultVO.setCode(-1);
        } else {
            //2.判断密码是否正确
            if (!admin.getPassword().equals(ruleForm.getPassword())) {
                resultVO.setCode(-2);
            } else {
                resultVO.setCode(0);
                resultVO.setData(admin);
            }
        }
        return resultVO;
    }

    @Override
    public boolean changePassword(ChangeForm changeForm) {
        Admin admin = this.adminMapper.selectById(changeForm.getId());
        admin.setPassword(changeForm.getNewPass());
        int update = this.adminMapper.updateById(admin);
        return update==1;
    }
}
