package com.dmsoft.fire.util;

import com.dmsoft.fire.dto.CommodityDto;
import com.dmsoft.fire.entity.Commodity;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

/**
 * @author zhixin.huang
 * @date 2019/4/9 9:34
 */
public class BeanCopyTest {
    public static void main(String[] args) {
        CommodityDto commodityDto = new CommodityDto();
        commodityDto.setCommodityNo(UUID.randomUUID().toString());
        commodityDto.setCommodityName("a");
        commodityDto.setCommodityPrice("1.23");
        commodityDto.setCommodityType("日用品");
        commodityDto.setCommodityDesc("111");

        long l = System.currentTimeMillis();
        Commodity commodity = new Commodity();
        for (int i = 0; i < 100000000; i++) {
            BeanUtils.copyProperties(commodityDto, commodity);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("spring的beanUtil耗时：" + (l1 - l));


        long l2 = System.currentTimeMillis();
        Commodity commodity1 = new Commodity();
        for (int i = 0; i < 100000000; i++) {
            BeanCopier beanCopier = BeanCopier.create(CommodityDto.class, Commodity.class, false);
            beanCopier.copy(commodityDto, commodity1, null);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("cglib的BeanCopier耗时：" + (l3 - l2));


        long l4 = System.currentTimeMillis();
        Commodity commodity2 = new Commodity();
        for (int i = 0; i < 100000000; i++) {
            org.springframework.cglib.beans.BeanCopier beanCopier = org.springframework.cglib.beans.BeanCopier.create(CommodityDto.class, Commodity.class, false);
            beanCopier.copy(commodityDto, commodity2, null);
        }
        long l5 = System.currentTimeMillis();
        System.out.println("spring的BeanCopier耗时：" + (l5 - l4));

    }
}
