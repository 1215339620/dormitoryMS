package com.dormitoryms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dormitoryms.entity.*;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.mapper.*;
import com.dormitoryms.service.RepairService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dormitoryms.vo.AbsentVO;
import com.dormitoryms.vo.PageVO;
import com.dormitoryms.vo.RepairVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-04-04
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {
    @Autowired
    private RepairMapper repairMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Repair> repairPage = new Page<>(page, size);
        Page<Repair> resultPage = this.repairMapper.selectPage(repairPage, null);
        List<RepairVO> repairVOList = new ArrayList<>();
        for (Repair repair : resultPage.getRecords()) {
            RepairVO repairVO = new RepairVO();
            BeanUtils.copyProperties(repair, repairVO);
            repairVO.setBuildingName(this.buildingMapper.selectById(repair.getBId()).getName());
            repairVO.setDormitoryName(this.dormitoryMapper.selectById(repair.getDId()).getName());
            repairVO.setStudentName(this.studentMapper.selectById(repair.getStudentId()).getName());
            repairVOList.add(repairVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(repairVOList);
        return pageVO;
    }
    @Override
    public PageVO listByDName(Integer page, Integer size, String dId) {
        Page<Repair> repairPage = new Page<>(page, size);
        QueryWrapper<Repair> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("d_id",dId);
        Page<Repair> resultPage = this.repairMapper.selectPage(repairPage, queryWrapper);
        System.out.println("===============================================================================================");
        System.out.println(resultPage.getRecords());
        List<RepairVO> repairVOList = new ArrayList<>();
        for (Repair repair : resultPage.getRecords()) {
            RepairVO repairVO = new RepairVO();
            BeanUtils.copyProperties(repair, repairVO);
            repairVO.setBuildingName(this.buildingMapper.selectById(repair.getBId()).getName());
            repairVO.setDormitoryName(this.dormitoryMapper.selectById(repair.getDId()).getName());
            repairVO.setStudentName(this.studentMapper.selectById(repair.getStudentId()).getName());
            repairVOList.add(repairVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(repairVOList);
        return pageVO;
    }
    @Override
    public PageVO search(SearchForm searchForm) {
        Page<Repair> repairPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<Repair> resultPage = null;
        if(searchForm.getValue().equals("")){
            resultPage = this.repairMapper.selectPage(repairPage, null);
        } else {
            QueryWrapper<Repair> queryWrapper = new QueryWrapper<>();
            if(searchForm.getKey().equals("buildingName")){
                QueryWrapper<Building> buildingQueryWrapper = new QueryWrapper<>();
                buildingQueryWrapper.like("name", searchForm.getValue());
                List<Building> buildingList = this.buildingMapper.selectList(buildingQueryWrapper);
                List<Integer> idList = new ArrayList<>();
                for (Building building : buildingList) {
                    idList.add(building.getId());
                }
                queryWrapper.in("b_id", idList);
            }
            if(searchForm.getKey().equals("dormitoryName")){
                QueryWrapper<Dormitory> dormitoryQueryWrapper = new QueryWrapper<>();
                dormitoryQueryWrapper.like("name", searchForm.getValue());
                List<Dormitory> dormitoryList = this.dormitoryMapper.selectList(dormitoryQueryWrapper);
                List<Integer> idList = new ArrayList<>();
                for (Dormitory dormitory : dormitoryList) {
                    idList.add(dormitory.getId());
                }
                queryWrapper.in("d_id", idList);
            }
            if(searchForm.getKey().equals("studentName")){
                QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
                studentQueryWrapper.like("name", searchForm.getValue());
                List<Student> studentList = this.studentMapper.selectList(studentQueryWrapper);
                List<Integer> idList = new ArrayList<>();
                for (Student student: studentList) {
                    idList.add(student.getId());
                }
                queryWrapper.in("student_id", idList);
            }
            resultPage = this.repairMapper.selectPage(repairPage, queryWrapper);
        }
        List<RepairVO> repairVOList = new ArrayList<>();
        for (Repair repair : resultPage.getRecords()) {
            RepairVO repairVO = new RepairVO();
            BeanUtils.copyProperties(repair, repairVO);
            repairVO.setBuildingName(this.buildingMapper.selectById(repair.getBId()).getName());
            repairVO.setDormitoryName(this.dormitoryMapper.selectById(repair.getDId()).getName());
            repairVO.setStudentName(this.studentMapper.selectById(repair.getStudentId()).getName());
            repairVOList.add(repairVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(repairVOList);
        return pageVO;
    }

    @Override
    public boolean updateStatus(Integer id) {
        try {
            this.repairMapper.upStatus1(id);
        } catch (Exception e){
            return  false;
        }
        return true;
    }

    @Override
    public boolean updateStatus2(Integer id) {
        try {
            this.repairMapper.upStatus2(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }


}
