package com.dormitoryms.controller;


import com.dormitoryms.entity.Building;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.service.BuildingService;
import com.dormitoryms.util.ResultVOUtil;
import com.dormitoryms.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-04-04
 */
@RestController
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @PostMapping("/save")
    public ResultVO save(@RequestBody Building building){
        boolean save = this.buildingService.save(building);
        if(!save) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return ResultVOUtil.success(this.buildingService.list(page, size));
    }

    @GetMapping("/list")
    public ResultVO list(){
        return ResultVOUtil.success(this.buildingService.list());
    }
    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        return ResultVOUtil.success(this.buildingService.search(searchForm));
    }
    @GetMapping("/findById/{id}")
    public ResultVO findById(@PathVariable("id") Integer id){
        return ResultVOUtil.success(this.buildingService.getById(id));
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody Building building){
        boolean update = this.buildingService.updateById(building);
        if(!update) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id){
        Boolean delete = this.buildingService.deleteById(id);
        if(!delete) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

