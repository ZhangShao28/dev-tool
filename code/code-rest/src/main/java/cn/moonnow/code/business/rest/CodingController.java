package cn.moonnow.code.business.rest;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.code.business.param.DtParam;
import cn.moonnow.code.business.service.ICodingService;
import cn.moonnow.code.entity.Dt;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.rest.Responses;
import cn.moonnow.tool.rest.ToolController;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 生成代码 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "code/coding" })
public class CodingController extends ToolController {

  public static final String LOG = "CodingController";

  @Resource(name = "cn.moonnow.code.CodingService")
  private ICodingService codingService;

  /**
   * 测试专用</br>
   * 保存 数据库表 信息</br>
   * 从sql文件获取建表sql
   */
  @PostMapping(params = { ToolUtil.ACTION + "save_dt_info" })
  public Responses<Dt> saveDtInfo(Param param, @RequestBody DtParam dtParam) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dtParam);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dtParam)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(codingService.saveDtInfo(dtParam));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 测试专用</br>
   * 提取所有数据库表数据
   */
  @PostMapping(params = { ToolUtil.ACTION + "initdata" })
  public Responses<?> initData(Param param) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
    }
    Responses<?> responses = new Responses<>();
    try {
      codingService.initData();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 提取数据库表数据
   */
  @PostMapping(params = { ToolUtil.ACTION + "extract" })
  public Responses<?> extract(Param param, @RequestBody Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dt);
    }
    Responses<?> responses = new Responses<>();
    try {
      codingService.extract(dt);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

}
