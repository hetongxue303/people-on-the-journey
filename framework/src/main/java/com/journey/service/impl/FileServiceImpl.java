package com.journey.service.impl;

import com.journey.domain.common.Result;
import com.journey.enums.QINiuPathEnum;
import com.journey.service.FileService;
import com.journey.utils.QINiuUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件业务实现
 *
 * @author hy
 * @version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private QINiuUtil qiNiuUtil;

    @Override
    public Result uploadImage(MultipartFile multipartFile, QINiuPathEnum qiNiuPathEnum) {
        return Result.success("上传成功", qiNiuUtil.upload(multipartFile, qiNiuPathEnum));
    }
}