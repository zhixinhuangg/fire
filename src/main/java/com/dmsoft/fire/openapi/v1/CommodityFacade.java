package com.dmsoft.fire.openapi.v1;

import com.dmsoft.fire.annotation.MyLog;
import com.dmsoft.fire.dto.CommodityDto;
import com.dmsoft.fire.openapi.ResponseEntity;
import com.dmsoft.fire.openapi.exception.BusinessException;
import com.dmsoft.fire.service.CommodityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhixin.huang
 * @date 2019/4/9 11:56
 */
@RestController
@RequestMapping("api/v1/commodity")
public class CommodityFacade {
    @Resource
    private CommodityService commodityService;

    @PostMapping("/saveCommodity")
    @MyLog(value = "保存")
    public ResponseEntity<Boolean> saveCommodity(@RequestBody CommodityDto commodityDto) {
        ResponseEntity<Boolean> responseEntity;
        try {
            commodityService.saveCommodity(commodityDto);
            responseEntity = new ResponseEntity<>(true, HttpStatus.OK, true, "保存成功！");
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(false, HttpStatus.OK, true, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY, false, e.getMessage());
        }
        return responseEntity;
    }

    @PostMapping("/updateCommodity")
    public ResponseEntity<Boolean> updateCommodity(@RequestBody CommodityDto commodityDto) {
        ResponseEntity<Boolean> responseEntity;
        try {
            commodityService.updateCommodity(commodityDto);
            responseEntity = new ResponseEntity<>(true, HttpStatus.OK, true, "保存成功！");
        } catch (BusinessException e) {
            responseEntity = new ResponseEntity<>(false, HttpStatus.OK, true, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY, false, e.getMessage());
        }
        return responseEntity;
    }

    @DeleteMapping("/deleteCommodityByCommodityNo")
    public ResponseEntity<Boolean> deleteCommodityByCommodityNo(@RequestParam("commodityNo") String commodityNo) {
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

    @GetMapping("/findCommodityByCommodityNo")
    @MyLog("保存")
    public ResponseEntity<CommodityDto> findCommodityByCommodityNo(@RequestParam("commodityNo") String commodityNo) {
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
