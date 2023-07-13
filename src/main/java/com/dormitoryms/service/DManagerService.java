package com.dormitoryms.service;

import com.dormitoryms.entity.DManager;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dormitoryms.form.ChangeForm;
import com.dormitoryms.form.RuleForm;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.vo.PageVO;
import com.dormitoryms.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-04-04
 */
public interface DManagerService extends IService<DManager> {
    public ResultVO login(RuleForm ruleForm);
    public PageVO list(Integer page, Integer size);
//    public PageVO search(SearchForm searchForm);
    public PageVO search(SearchForm searchForm);

    boolean changePassword(ChangeForm changeForm);
}
