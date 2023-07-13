package com.dormitoryms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dormitoryms.entity.DManager;
import com.dormitoryms.entity.Notice;
import com.dormitoryms.form.SearchForm;
import com.dormitoryms.mapper.NoticeMapper;
import com.dormitoryms.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dormitoryms.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-04-04
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Notice> noticePage=new Page<>(page,size);
        Page<Notice> noticePage1= this.noticeMapper.selectPage(noticePage, null);
        PageVO pageVO=new PageVO();
        pageVO.setTotal(noticePage1.getTotal());
        pageVO.setData(noticePage1.getRecords());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        //模糊查询+分页
        Page<Notice> noticePage = new Page<>(searchForm.getPage(),searchForm.getSize());
        Page<Notice> resultPage = null;
        if(searchForm.getValue().equals("")){
            resultPage = this.noticeMapper.selectPage(noticePage, null);
        } else {
//            数据库查询
            QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = this.noticeMapper.selectPage(noticePage,queryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }
}
