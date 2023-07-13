package com.dormitoryms.controller;


import com.dormitoryms.form.ChangeForm;
import com.dormitoryms.form.RuleForm;
import com.dormitoryms.service.AdminService;
import com.dormitoryms.service.DManagerService;
import com.dormitoryms.util.ResultVOUtil;
import com.dormitoryms.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-04-04
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/login")
    public ResultVO login(RuleForm ruleForm){
        ResultVO resultVO = this.adminService.login(ruleForm);
        return resultVO;
    }
    @PutMapping("/changePassword")
    public ResultVO changePassword(@RequestBody ChangeForm changeForm){
        boolean update = this.adminService.changePassword(changeForm);
        if(!update) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

