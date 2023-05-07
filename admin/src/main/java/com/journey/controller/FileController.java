package com.journey.controller;

import com.journey.domain.common.Result;
import com.journey.enums.QINiuPathEnum;
import com.journey.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@RequestMapping("file")
@Schema(name = "文件模块")
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("upload/foods")
    @Operation(summary = "上传食物图片")
    public Result uploadFoods(MultipartFile file) {
        return fileService.uploadImage(file, QINiuPathEnum.FOOD);
    }

    @PostMapping("upload/travel-agency")
    @Operation(summary = "上传旅游社图片")
    public Result uploadTravelAgency(MultipartFile file) {
        return fileService.uploadImage(file, QINiuPathEnum.TRAVEL_AGENCY);
    }

    @PostMapping("upload/scenic-spots")
    @Operation(summary = "上传景区图片")
    public Result uploadScenicSpots(MultipartFile file) {
        return fileService.uploadImage(file, QINiuPathEnum.SCENIC_SPOTS);
    }

}