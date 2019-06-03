package cn.moonnow.tool.util;

import java.io.Serializable;

import lombok.Data;

/**
 * 接受前台传递的通用参数
 */
@Data
public class Param implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long pro; // 防止请求缓存

  private String action; // 我要做什么

  private Long rows; // 多少条

  private Long page; // 第几页

  private String token; // 会话使用自己的封装 明文传递uuid

}
