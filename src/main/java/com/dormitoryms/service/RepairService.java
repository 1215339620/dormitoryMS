package com.dormitoryms.service;

import com.dormitoryms.entity.Repair;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-04-04
 */
public interface RepairService extends IService<Repair> {

    PageVO list(Integer page, Integer size);

    PageVO search(SearchForm searchForm);

    boolean updateStatus(Integer id);

    boolean updateStatus2(Integer id);

    PageVO listByDName(Integer page, Integer size, String dId);
}
