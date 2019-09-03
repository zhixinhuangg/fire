package com.dmsoft.fire.dao;

import com.dmsoft.fire.entity.SysLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author zhixin.huang
 * @date 2019/6/20 9:17
 */
public interface SysLogDao extends PagingAndSortingRepository<SysLog, String>, JpaSpecificationExecutor<SysLog> {

    @Override
    SysLog save(SysLog sysLog);
}
