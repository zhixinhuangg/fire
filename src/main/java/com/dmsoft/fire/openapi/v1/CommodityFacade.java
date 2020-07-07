package com.dmsoft.fire.openapi.v1;

import com.dmsoft.fire.annotation.MyLog;
import com.dmsoft.fire.dto.CommodityDto;
import com.dmsoft.fire.openapi.ResponseEntity;
import com.dmsoft.fire.openapi.exception.BusinessException;
import com.dmsoft.fire.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * todo 需要去做统一异常处理
 *
 * @author zhixin.huang
 * @date 2019/4/9 11:56
 */
@RestController
@RequestMapping("api/v1/commodity")
@Api(value = "commodity", tags = "商品控制器")
public class CommodityFacade {
    @Resource
    private CommodityService commodityService;

    @PostMapping("/saveCommodity")
    @MyLog(value = "保存", write = true)
    @ApiOperation(httpMethod = "POST", value = "保存商品")
    public ResponseEntity<CommodityDto> saveCommodity(@RequestBody CommodityDto commodityDto) {
        ResponseEntity<CommodityDto> responseEntity;
        try {
            CommodityDto result = commodityService.saveCommodity(commodityDto);
            responseEntity = new ResponseEntity<>(result, HttpStatus.OK, true, "保存成功！");
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(null, HttpStatus.OK, true, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY, false, e.getMessage());
        }
        return responseEntity;
    }

    @PostMapping("/updateCommodity")
    @MyLog(value = "更新")
    @ApiOperation(httpMethod = "POST", value = "更新商品")
    public ResponseEntity<CommodityDto> updateCommodity(@RequestBody CommodityDto commodityDto) {
        ResponseEntity<CommodityDto> responseEntity;
        try {
            CommodityDto result = commodityService.updateCommodity(commodityDto);
            responseEntity = new ResponseEntity<>(result, HttpStatus.OK, true, "保存成功！");
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(null, HttpStatus.OK, true, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY, false, e.getMessage());
        }
        return responseEntity;
    }

    @DeleteMapping("/deleteCommodityByCommodityNo/{commodityNo}")
    @MyLog(value = "删除")
    @ApiOperation(httpMethod = "DELETE", value = "删除商品")
    public ResponseEntity<Boolean> deleteCommodityByCommodityNo(@PathVariable("commodityNo") String commodityNo) {
        ResponseEntity<Boolean> responseEntity;
        try {
            commodityService.deleteCommodityByCommodityNo(commodityNo);
            responseEntity = new ResponseEntity<>(true, HttpStatus.OK, true, "删除成功！");
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(false, HttpStatus.OK, true, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY, false, e.getMessage());
        }
        return responseEntity;
    }

    @GetMapping("/findCommodityByCommodityNo/{commodityNo}")
    @MyLog("查询")
    @ApiOperation(httpMethod = "GET", value = "查询商品")
    public ResponseEntity<CommodityDto> findCommodityByCommodityNo(@PathVariable("commodityNo") String commodityNo) {
        ResponseEntity<CommodityDto> responseEntity;
        try {
            CommodityDto commodityDto = commodityService.findCommodityByCommodityNo(commodityNo);
            responseEntity = new ResponseEntity<>(commodityDto, HttpStatus.OK, true, "查询成功！");
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.OK, true, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY, false, e.getMessage());
        }
        return responseEntity;
    }
}
