package com.dormitoryms.service;

import com.dormitoryms.entity.Notice;
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
public interface NoticeService extends IService<Notice> {
    public PageVO list(Integer page, Integer size);

    PageVO search(SearchForm searchForm);
}
