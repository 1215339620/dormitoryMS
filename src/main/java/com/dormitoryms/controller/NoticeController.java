package com.dormitoryms.controller;


import com.dormitoryms.entity.DManager;
import com.dormitoryms.entity.Notice;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.service.NoticeService;
import com.dormitoryms.util.ResultVOUtil;
import com.dormitoryms.vo.PageVO;
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
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @PostMapping("/save")
    public ResultVO save(@RequestBody Notice notice){
        boolean save = this.noticeService.save(notice);
        if (!save){
            return ResultVOUtil.fail();
        }
        return ResultVOUtil.success(null);
    }
    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        PageVO pageVO = this.noticeService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }
    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVO pageVO = this.noticeService.search(searchForm);
        return ResultVOUtil.success(pageVO);
    }
}

