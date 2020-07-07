package com.dmsoft.fire.openapi.v1;

import com.dmsoft.fire.dto.CommodityDto;
import com.dmsoft.fire.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhixin.huang
 * @date 2020/7/7 10:34 上午
 */
@RestController
@RequestMapping("api/v1/redis")
@Api(value = "redis", tags = "redis缓存控制器")
public class RedisFacade {
    @Resource
    private RedisUtil redisUtil;

    @PostMapping("/save")
    @ApiOperation(httpMethod = "POST", value = "测试redis保存")
    public void save(@RequestBody CommodityDto commodityDto) {
        redisUtil.set(commodityDto.getCommodityNo(), commodityDto);
    }

    @DeleteMapping("/delete/{commodityNo}")
    @ApiOperation(httpMethod = "DELETE", value = "测试redis删除")
    public void delete(@PathVariable("commodityNo") String commodityNo) {
        redisUtil.del(commodityNo);
    }
}
