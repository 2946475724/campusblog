package com.zs.campusblog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zs
 * @date 2020/4/14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MinioUploadDto {
    private String url;
    private String name;
}
