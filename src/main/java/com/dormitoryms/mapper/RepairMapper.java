package com.dormitoryms.mapper;

import com.dormitoryms.entity.Repair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2023-04-04
 */
@Repository
public interface RepairMapper extends BaseMapper<Repair> {

    void upStatus1(Integer id);

    void upStatus2(Integer id);
}
