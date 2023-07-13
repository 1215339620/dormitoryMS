package com.dormitoryms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dormitoryms.entity.Absent;
import com.dormitoryms.entity.Building;
import com.dormitoryms.entity.Dormitory;
import com.dormitoryms.entity.Student;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.mapper.*;
import com.dormitoryms.service.AbsentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dormitoryms.vo.AbsentVO;
import com.dormitoryms.vo.DormitoryVO;
import com.dormitoryms.vo.PageVO;
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
public class AbsentServiceImpl extends ServiceImpl<AbsentMapper, Absent> implements AbsentService {
    @Autowired
    private AbsentMapper absentMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Absent> absentPage = new Page<>(page, size);
        Page<Absent> resultPage = this.absentMapper.selectPage(absentPage, null);
        List<AbsentVO> absentVOList = new ArrayList<>();
        for (Absent absent : resultPage.getRecords()) {
            AbsentVO absentVO = new AbsentVO();
            BeanUtils.copyProperties(absent, absentVO);
            absentVO.setBuildingName(this.buildingMapper.selectById(absent.getBId()).getName());
            absentVO.setDormitoryName(this.dormitoryMapper.selectById(absent.getDId()).getName());
            absentVO.setStudentName(this.studentMapper.selectById(absent.getStudentId()).getName());
            absentVOList.add(absentVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(absentVOList);
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<Absent> absentPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<Absent> resultPage = null;
        if(searchForm.getValue().equals("")){
            resultPage = this.absentMapper.selectPage(absentPage, null);
        } else {
            QueryWrapper<Absent> queryWrapper = new QueryWrapper<>();
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
            resultPage = this.absentMapper.selectPage(absentPage, queryWrapper);
        }
        List<AbsentVO> absentVOList = new ArrayList<>();
        for (Absent absent : resultPage.getRecords()) {
            AbsentVO absentVO = new AbsentVO();
            BeanUtils.copyProperties(absent, absentVO);
            absentVO.setBuildingName(this.buildingMapper.selectById(absent.getBId()).getName());
            absentVO.setDormitoryName(this.dormitoryMapper.selectById(absent.getDId()).getName());
            absentVO.setStudentName(this.studentMapper.selectById(absent.getStudentId()).getName());
            absentVOList.add(absentVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(absentVOList);
        return pageVO;
    }
}
