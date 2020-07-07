package com.dmsoft.fire.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dmsoft.fire.dto.CommodityDto;
import com.dmsoft.fire.entity.Commodity;
import com.dmsoft.fire.mapper.CommodityMapper;
import com.dmsoft.fire.openapi.exception.BusinessException;
import com.dmsoft.fire.service.CommodityService;
import com.dmsoft.fire.util.SnowflakeComponent;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author zhixin.huang
 * @date 2019/4/8 18:33
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityMapper commodityMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Override
    public CommodityDto saveCommodity(CommodityDto commodityDto) {
        //向数据库持久化
        Commodity commodity = new Commodity()
                .setCommodityName(commodityDto.getCommodityName())
                .setCommodityType(commodityDto.getCommodityType())
                .setCommodityPrice(commodityDto.getCommodityPrice())
                .setCommodityDesc(commodityDto.getCommodityDesc())
                .setCommodityNo(snowflakeComponent.getSnowflakeIdWorker().nextId());
        commodityMapper.insert(commodity);
        commodityDto.setCommodityNo(commodity.getCommodityNo());
        return commodityDto;
    }

    @Override
    @CachePut(value = "commodityDto", key = "#commodityDto.commodityNo")
    public CommodityDto updateCommodity(CommodityDto commodityDto) {
        Commodity commodity = commodityMapper.selectOne(new QueryWrapper<Commodity>()
                .eq("commodity_no", commodityDto.getCommodityNo()));
        if (Objects.isNull(commodity)) {
            throw new BusinessException(500, "该记录不存在！");
        }
        // 使用beanUtils（慎用）
        BeanUtils.copyProperties(commodityDto, commodity);
        commodityMapper.update(commodity, new QueryWrapper<Commodity>()
                .eq("commodity_no", commodityDto.getCommodityNo()));
        return commodityDto;
    }

    @Override
    @CacheEvict(value = "commodityDto", key = "#commodityNo")
    public void deleteCommodityByCommodityNo(String commodityNo) {
        commodityMapper.delete(new QueryWrapper<Commodity>()
                .eq("commodity_no", commodityNo));
    }

    @Override
    @Cacheable(value = "commodityDto", key = "#commodityNo")
    public CommodityDto findCommodityByCommodityNo(String commodityNo) {
        CommodityDto commodityDto = new CommodityDto();
        Commodity commodity = commodityMapper.selectOne(new QueryWrapper<Commodity>()
                .eq("commodity_no", commodityNo));
        if (Objects.isNull(commodity)) {
            throw new BusinessException(500, "该记录不存在！");
        }
        BeanUtils.copyProperties(commodity, commodityDto);
        return commodityDto;
    }
}
