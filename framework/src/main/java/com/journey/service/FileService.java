package com.journey.service;

import com.journey.domain.common.Result;
import com.journey.enums.QINiuPathEnum;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件业务
 *
 * @author hy
 * @version 1.0
 */
public interface FileService {

    Result uploadImage(MultipartFile multipartFile, QINiuPathEnum qiNiuPathEnum);

}