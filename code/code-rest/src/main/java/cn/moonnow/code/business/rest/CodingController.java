package cn.moonnow.code.business.rest;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.code.business.param.DtParam;
import cn.moonnow.code.business.service.ICodingService;
import cn.moonnow.code.business.vo.ConfigVO;
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

  /**
   * 获取代码文件绝对路径配置信息
   */
  @PostMapping(params = { ToolUtil.ACTION + "get_config" })
  public Responses<ConfigVO> getConfig(Param param, @RequestBody Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dt);
    }
    Responses<ConfigVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dt.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(codingService.getConfig(dt));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 生成 java实体类 代码
   */
  @PostMapping(params = { ToolUtil.ACTION + "coding_jdbc_entity" })
  public Responses<?> codingJdbcEntity(Param param, @RequestBody ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + configVo);
    }
    Responses<?> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      codingService.codingJdbcEntity(configVo);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 生成 java查询类 代码
   */
  @PostMapping(params = { ToolUtil.ACTION + "coding_query" })
  public Responses<?> codingQuery(Param param, @RequestBody ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + configVo);
    }
    Responses<?> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      codingService.codingQuery(configVo);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 生成 java展示类 代码
   */
  @PostMapping(params = { ToolUtil.ACTION + "coding_vo" })
  public Responses<?> codingVo(Param param, @RequestBody ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + configVo);
    }
    Responses<?> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      codingService.codingVo(configVo);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 生成 java持久化接口 代码
   */
  @PostMapping(params = { ToolUtil.ACTION + "coding_i_persistent" })
  public Responses<?> codingIPersistent(Param param, @RequestBody ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + configVo);
    }
    Responses<?> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      codingService.codingIPersistent(configVo);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 生成 java持久化实现 代码
   */
  @PostMapping(params = { ToolUtil.ACTION + "coding_jdbc_persistent_impl" })
  public Responses<?> codingJdbcPersistentImpl(Param param, @RequestBody ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + configVo);
    }
    Responses<?> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      codingService.codingJdbcPersistentImpl(configVo);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 生成 java服务接口 代码
   */
  @PostMapping(params = { ToolUtil.ACTION + "coding_i_service" })
  public Responses<?> codingIService(Param param, @RequestBody ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + configVo);
    }
    Responses<?> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      codingService.codingIService(configVo);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 生成 java服务实现 代码
   */
  @PostMapping(params = { ToolUtil.ACTION + "coding_service_impl" })
  public Responses<?> codingServiceImpl(Param param, @RequestBody ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + configVo);
    }
    Responses<?> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      codingService.codingServiceImpl(configVo);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 生成 java控制器 代码
   */
  @PostMapping(params = { ToolUtil.ACTION + "coding_controller" })
  public Responses<?> codingController(Param param, @RequestBody ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + configVo);
    }
    Responses<?> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      codingService.codingController(configVo);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 生成所有代码
   */
  @PostMapping(params = { ToolUtil.ACTION + "coding_all" })
  public Responses<?> codingAll(Param param, @RequestBody ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + configVo);
    }
    Responses<?> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      codingService.codingJdbcEntity(configVo);
      codingService.codingQuery(configVo);
      codingService.codingVo(configVo);
      codingService.codingIPersistent(configVo);
      codingService.codingJdbcPersistentImpl(configVo);
      codingService.codingIService(configVo);
      codingService.codingServiceImpl(configVo);
      codingService.codingController(configVo);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 生成项目
   */
  @PostMapping(params = { ToolUtil.ACTION + "coding_project" })
  public Responses<?> codingProject(Param param, @RequestBody ConfigVO configVo) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + configVo);
    }
    Responses<?> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(configVo)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(configVo.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      codingService.codingProject(configVo);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

}
