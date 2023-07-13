package com.dormitoryms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dormitoryms.entity.DManager;
import com.dormitoryms.entity.Dormitory;
import com.dormitoryms.entity.Student;
import com.dormitoryms.form.ChangeForm;
import com.dormitoryms.form.RuleForm;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.form.StudentForm;
import com.dormitoryms.mapper.DormitoryMapper;
import com.dormitoryms.mapper.StudentMapper;
import com.dormitoryms.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dormitoryms.vo.PageVO;
import com.dormitoryms.vo.ResultVO;
import com.dormitoryms.vo.StudentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Style;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Override
    public ResultVO login(RuleForm ruleForm) {
//        判断用户名是否存在
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", ruleForm.getUsername());
        Student student = this.studentMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        if (student == null) {
            resultVO.setCode(-1);
        } else {
            //2.判断密码是否正确
            if (!student.getPassword().equals(ruleForm.getPassword())) {
                resultVO.setCode(-2);
            } else {
                resultVO.setCode(0);
                resultVO.setData(student);
            }
        }
        return resultVO;
    }

    @Override
    public Boolean saveStudent(Student student) {
        //添加学生数据
//        System.out.println(student);
        int insert = this.studentMapper.insert(student);
        if(insert != 1) return false;
        //修改宿舍数据
        Dormitory dormitory = this.dormitoryMapper.selectById(student.getDId());
        if (dormitory.getAvailable() == 0) {
            return false;
        }
        dormitory.setAvailable(dormitory.getAvailable() - 1);
        int update = this.dormitoryMapper.updateById(dormitory);
        if(update != 1) return false;
        return true;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Student> studentPage = new Page<>(page, size);
        Page<Student> resultPage = this.studentMapper.selectPage(studentPage, null);
        List<Student> studentList = resultPage.getRecords();
        //VO转换
        List<StudentVO> studentVOList = new ArrayList<>();
        for (Student student : studentList) {
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student, studentVO);
            Dormitory dormitory = this.dormitoryMapper.selectById(student.getDId());
            studentVO.setDName(dormitory.getName());
            studentVOList.add(studentVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setData(studentVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<Student> studentPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<Student> resultPage = null;
        if(searchForm.getValue().equals("")){
            resultPage = this.studentMapper.selectPage(studentPage, null);
        } else {
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = this.studentMapper.selectPage(studentPage, queryWrapper);
        }
        List<Student> studentList = resultPage.getRecords();
        //VO转换
        List<StudentVO> studentVOList = new ArrayList<>();
        for (Student student : studentList) {
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student, studentVO);
            Dormitory dormitory = this.dormitoryMapper.selectById(student.getDId());
            studentVO.setDName(dormitory.getName());
            studentVOList.add(studentVO);
        }
        PageVO pageVO = new PageVO();
        pageVO.setData(studentVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public Boolean update(StudentForm studentForm) {
        //更新学生信息
        Student student = new Student();
        BeanUtils.copyProperties(studentForm, student);
        int update = this.studentMapper.updateById(student);
        if(update != 1) return false;
        //更新宿舍数据
        if(!studentForm.getDId().equals(studentForm.getOldDId())){
            //old+1，new-1
            try {
                this.dormitoryMapper.addAvailable(studentForm.getOldDId());
                this.dormitoryMapper.subAvailable(studentForm.getDId());
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean deleteById(Integer id) {
        //修改宿舍数据
        Student student = this.studentMapper.selectById(id);
        try {
            Dormitory dormitory = this.dormitoryMapper.selectById(student.getDId());
            if(dormitory.getType() > dormitory.getAvailable()){
                this.dormitoryMapper.addAvailable(student.getDId());
            }
        } catch (Exception e) {
            return false;
        }
        //删除学生数据
        int delete = this.studentMapper.deleteById(id);
        if(delete != 1) return false;
        return true;
    }

    @Override
    public boolean changePassword(ChangeForm changeForm) {
        Student student = this.studentMapper.selectById(changeForm.getId());
        student.setPassword(changeForm.getNewPass());
        int update = this.studentMapper.updateById(student);
        return update==1;
    }
}
