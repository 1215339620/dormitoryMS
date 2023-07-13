package com.dormitoryms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dormitoryms.entity.*;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.service.BuildingService;
import com.dormitoryms.service.DormitoryService;
import com.dormitoryms.service.RepairService;
import com.dormitoryms.service.StudentService;
import com.dormitoryms.util.ResultVOUtil;
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
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private RepairService repairService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return ResultVOUtil.success(this.repairService.list(page, size));
    }
    @GetMapping("/listByDName/{page}/{size}/{dId}")
    public ResultVO list(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("dId") String dId){
        return ResultVOUtil.success(this.repairService.listByDName(page, size,dId));
    }
    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        return ResultVOUtil.success(this.repairService.search(searchForm));
    }
    @GetMapping("/buildingList")
    public ResultVO buildingList(){
        return ResultVOUtil.success(this.buildingService.list());
    }
    @GetMapping("/findDormitoryById/{id}")
    public ResultVO findDormitoryById(@PathVariable("id") Integer id){
        Dormitory dormitory = this.dormitoryService.getById(id);
        return ResultVOUtil.success(dormitory);
    }
    @GetMapping("/findBuildingById/{id}")
    public ResultVO findBuildingById(@PathVariable("id") Integer id){
        Building building = this.buildingService.getById(id);
        return ResultVOUtil.success(building);
    }
    @PostMapping("/save")
    public ResultVO save(@RequestBody Repair repair){
        boolean save = this.repairService.save(repair);
        if(!save) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
    @PutMapping("/update1/{id}")
    public ResultVO update1(@PathVariable("id") Integer id){
        boolean update = this.repairService.updateStatus(id);
        if(!update) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
    @PutMapping("/update2/{id}")
    public ResultVO update2(@PathVariable("id") Integer id){
        boolean update = this.repairService.updateStatus2(id);
        if(!update) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

