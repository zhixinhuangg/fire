package com.dmsoft.fire.dao;

import com.dmsoft.fire.entity.Commodity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhixin.huang
 * @date 2019/4/8 18:20
 */
@Repository
public interface CommodityDao extends PagingAndSortingRepository<Commodity, String>, JpaSpecificationExecutor<Commodity> {

    /**
     * 向数据库保存商品
     *
     * @param commodity 商品实体
     * @return Commodity
     */
    @Override
    Commodity save(Commodity commodity);

    /**
     * 向数据库删除商品
     *
     * @param commodityNo 商品编号
     */
    void deleteByCommodityNo(String commodityNo);


    /**
     * 通过商品编号向数据库查询单个商品
     *
     * @param commodityNo 商品编号
     * @return 商品实体类
     */
    Commodity findByCommodityNo(String commodityNo);
}
