package com.example.graduatestudent.controller;


import com.example.graduatestudent.entity.Province;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.service.IProvinceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author muxin
 * @since 2023-04-14
 */
@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Resource
    IProvinceService provinceService;
    @GetMapping("/getProvinceList")
    public BaseResult getProvinceList() {
        List<Province> provinceList = provinceService.list();
        return new OkResult(provinceList);
    }
}
