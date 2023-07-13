package com.dormitoryms.mapper;

import com.dormitoryms.entity.Dormitory;
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
public interface DormitoryMapper extends BaseMapper<Dormitory> {

    void addAvailable(Integer id);

    void subAvailable(Integer id);

    Integer findAvailableDormitoryId();
}
