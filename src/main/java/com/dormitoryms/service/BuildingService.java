package com.dormitoryms.service;

import com.dormitoryms.entity.Building;
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
public interface BuildingService extends IService<Building> {

    PageVO list(Integer page, Integer size);

    PageVO search(SearchForm searchForm);

    Boolean deleteById(Integer id);
}
