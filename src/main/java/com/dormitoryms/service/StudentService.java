package com.dormitoryms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dormitoryms.entity.Dormitory;
import com.dormitoryms.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dormitoryms.form.ChangeForm;
import com.dormitoryms.form.RuleForm;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.form.StudentForm;
import com.dormitoryms.vo.PageVO;
import com.dormitoryms.vo.ResultVO;
import com.dormitoryms.vo.StudentVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-04-04
 */
public interface StudentService extends IService<Student> {

    ResultVO login(RuleForm ruleForm);

    Boolean saveStudent(Student student);

    PageVO list(Integer page, Integer size);

    PageVO search(SearchForm searchForm);

    Boolean update(StudentForm studentForm);

    Boolean deleteById(Integer id);

    boolean changePassword(ChangeForm changeForm);
}
