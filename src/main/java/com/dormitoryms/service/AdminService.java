package com.dormitoryms.service;

import com.dormitoryms.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dormitoryms.form.ChangeForm;
import com.dormitoryms.form.RuleForm;
import com.dormitoryms.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-04-04
 */
public interface AdminService extends IService<Admin> {

    public ResultVO login(RuleForm ruleForm);

    boolean changePassword(ChangeForm changeForm);
}
