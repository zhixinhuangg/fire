package com.dmsoft.fire.service.impl;

import com.dmsoft.fire.dao.CommodityDao;
import com.dmsoft.fire.dto.CommodityDto;
import com.dmsoft.fire.entity.Commodity;
import com.dmsoft.fire.openapi.exception.BusinessException;
import com.dmsoft.fire.service.CommodityService;
import com.dmsoft.fire.util.SnowflakeComponent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhixin.huang
 * @date 2019/4/8 18:33
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityDao commodityDao;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    private BeanCopier toCommodity = BeanCopier.create(CommodityDto.class, Commodity.class, false);
    private BeanCopier toCommodityDto = BeanCopier.create(Commodity.class, CommodityDto.class, false);

    @Override
    @Cacheable(value = "commodity", key = "#commodityDto.commodityNo")
    public void saveCommodity(CommodityDto commodityDto) {
        //实例化实体类
        Commodity commodity = new Commodity();
        //使用spring自带的cglib的BeanCopier方法（效率更高，效率测试代码：com.dmsoft.fire.util.BeanCopyTest）
        toCommodity.copy(commodityDto, commodity, null);

        commodity.setCommodityNo(snowflakeComponent.getSnowflakeIdWorker().nextId());

        //向数据库持久化
        commodityDao.save(commodity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "commodity", key = "#commodityDto.commodityNo")
    public void updateCommodity(CommodityDto commodityDto) {
        try {
            Commodity commodity = commodityDao.findByCommodityNo(commodityDto.getCommodityNo());
            toCommodity.copy(commodityDto, commodity, null);
            commodityDao.save(commodity);
        } catch (NullPointerException e) {
            throw new BusinessException(500, "该记录不存在！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "commodity", key = "#commodityNo")
    public void deleteCommodityByCommodityNo(String commodityNo) {
        try {
            commodityDao.deleteByCommodityNo(commodityNo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(500, "删除操作失败！");
        }
    }

    @Override
    @Cacheable(keyGenerator = "wiselyKeyGenerator",value = "commodity")
    public CommodityDto findCommodityByCommodityNo(String commodityNo) {
        CommodityDto commodityDto = new CommodityDto();
        Commodity commodity = commodityDao.findByCommodityNo(commodityNo);
        if (commodity != null) {
            toCommodityDto.copy(commodity, commodityDto, null);
        }
        return commodityDto;
    }
}
