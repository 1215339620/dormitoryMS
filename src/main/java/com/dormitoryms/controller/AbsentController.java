package com.dormitoryms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dormitoryms.entity.Absent;
import com.dormitoryms.entity.Dormitory;
import com.dormitoryms.entity.Student;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.service.AbsentService;
import com.dormitoryms.service.BuildingService;
import com.dormitoryms.service.DormitoryService;
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
@RequestMapping("/absent")
public class AbsentController {
    @Autowired
    private AbsentService absentService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return ResultVOUtil.success(this.absentService.list(page, size));
    }
    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        return ResultVOUtil.success(this.absentService.search(searchForm));
    }
    @GetMapping("/buildingList")
    public ResultVO buildingList(){
        return ResultVOUtil.success(this.buildingService.list());
    }

    @GetMapping("/findDormitoryByBuildingId/{id}")
    public ResultVO findDormitoryByBuildingId(@PathVariable("id") Integer id){
        QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("b_id", id);
        List<Dormitory> dormitoryList = this.dormitoryService.list(queryWrapper);
        return ResultVOUtil.success(dormitoryList);
    }

    @GetMapping("/findStudentByDormitoryId/{id}")
    public ResultVO findStudentByDormitoryId(@PathVariable("id") Integer id){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("d_id", id);
        List<Student> studentList = this.studentService.list(queryWrapper);
        return ResultVOUtil.success(studentList);
    }
    @PostMapping("/save")
    public ResultVO save(@RequestBody Absent absent){
        boolean save = this.absentService.save(absent);
        if(!save) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

