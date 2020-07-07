package com.dmsoft.fire.service;

import com.dmsoft.fire.dto.CommodityDto;

/**
 * @author zhixin.huang
 * @date 2019/4/8 18:31
 */
public interface CommodityService {

    /**
     * 保存商品
     *
     * @param commodityDto 商品信息
     */
    CommodityDto saveCommodity(CommodityDto commodityDto);

    /**
     * 更新商品
     *
     * @param commodityDto 商品信息
     */
    CommodityDto updateCommodity(CommodityDto commodityDto);

    /**
     * 删除商品
     *
     * @param commodityNo 商品编号
     */
    void deleteCommodityByCommodityNo(String commodityNo);


    /**
     * 根据商品编号查找商品
     *
     * @param commodityNo 商品编号
     * @return 商品信息
     */
    CommodityDto findCommodityByCommodityNo(String commodityNo);
}
