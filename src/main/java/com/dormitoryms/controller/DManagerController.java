package com.dormitoryms.controller;


import com.dormitoryms.entity.DManager;
import com.dormitoryms.entity.Dormitory;
import com.dormitoryms.form.ChangeForm;
import com.dormitoryms.form.RuleForm;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.service.DManagerService;
import com.dormitoryms.util.ResultVOUtil;
import com.dormitoryms.vo.PageVO;
import com.dormitoryms.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-04-04
 */
@RestController
@RequestMapping("/dManager")
public class DManagerController {
    @Autowired
    private DManagerService dManagerService;
    @GetMapping("/login")
    public ResultVO login(RuleForm ruleForm){
        ResultVO resultVO = this.dManagerService.login(ruleForm);
        return resultVO;
    }
    @PostMapping("/save")
    public ResultVO save(@RequestBody DManager dManager){
        boolean save = this.dManagerService.save(dManager);
        if (!save){
            return ResultVOUtil.fail();
        }
        return ResultVOUtil.success(null);
    }
    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        PageVO pageVO = this.dManagerService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }
    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVO pageVO = this.dManagerService.search(searchForm);
        return ResultVOUtil.success(pageVO);
    }
    @GetMapping("/findById/{id}")
    public ResultVO findById(@PathVariable("id") Integer id){
        DManager dManagerServiceById = this.dManagerService.getById(id);
        return ResultVOUtil.success(dManagerServiceById);
    }
    @PutMapping("/update")
    public ResultVO update(@RequestBody DManager dManager){
        boolean update = this.dManagerService.updateById(dManager);
        if(!update) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id){
        boolean remove = this.dManagerService.removeById(id);
        if(!remove) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
    @GetMapping("/list")
    public ResultVO list(){
        List<DManager> dormitoryAdminList = this.dManagerService.list();
        return ResultVOUtil.success(dormitoryAdminList);
    }
    @PutMapping("/changePassword")
    public ResultVO changePassword(@RequestBody ChangeForm changeForm){
        boolean update = this.dManagerService.changePassword(changeForm);
        if(!update) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

