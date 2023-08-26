package com.example.model;

import lombok.Data;

import java.util.List;

/**
 * 用来接收集合类型参数的对象
 *
 * @author minus
 * @since 2023/08/26 16:16
 */
@Data
public class CollectionVO {

    private List<String> ids;

}
