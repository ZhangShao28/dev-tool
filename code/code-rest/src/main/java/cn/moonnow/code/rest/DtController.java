package cn.moonnow.code.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.code.entity.Dt;
import cn.moonnow.code.query.DtQuery;
import cn.moonnow.code.service.IDtService;
import cn.moonnow.code.vo.DtVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.rest.Responses;
import cn.moonnow.tool.rest.ToolController;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 数据库表 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "code/dt" })
public class DtController extends ToolController {

  public static final String LOG = "DtController";

  @Resource(name = "cn.moonnow.code.DtService")
  private IDtService dtService;

  /**
   * 保存 数据库表 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<Dt> save(Param param, @RequestBody Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dt);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dtService.saveDt(dt));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 数据库表 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<Dt> batchSave(Param param, @RequestBody List<Dt> dts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dts);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dtService.batchSaveDt(dts));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 数据库表 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<Dt> update(Param param, @RequestBody Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dt);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dtService.updateDt(dt));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 数据库表 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<Dt> batchUpdate(Param param, @RequestBody List<Dt> dts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dts);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dtService.batchUpdateDt(dts));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 数据库表 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<Dt> remove(Param param, @RequestBody Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dt);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      dtService.removeDt(dt);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 数据库表 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<Dt> batchRemove(Param param, @RequestBody List<Dt> dts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dts);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      dtService.batchRemoveDt(dts);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 数据库表 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<Dt> getByPk(Param param, @RequestBody Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dt);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dt.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dtService.getDtByPk(dt.getDtId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 数据库表 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<Dt> getAll(Param param, @RequestBody DtQuery dtQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dtQuery);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        responses.setData(dtService.getAllDt());
      } else {
        responses.setData(dtService.queryDt(dtQuery));
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 分页获取 数据库表 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<Dt> paging(Param param, @RequestBody DtQuery dtQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dtQuery);
    }
    Responses<Dt> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        responses.setData(dtService.pagingQueryDt(param, null));
      } else {
        responses.setData(dtService.pagingQueryDt(param, dtQuery));
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 数据库表 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<DtVO> getVOByPk(Param param, @RequestBody Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dt);
    }
    Responses<DtVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dt.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dtService.getDtVOByPk(dt.getDtId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 数据库表 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<DtVO> getAllVO(Param param, @RequestBody DtQuery dtQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dtQuery);
    }
    Responses<DtVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        responses.setData(dtService.getAllDtVO());
      } else {
        responses.setData(dtService.queryDtVO(dtQuery));
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 分页获取 数据库表 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<DtVO> pagingVO(Param param, @RequestBody DtQuery dtQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dtQuery);
    }
    Responses<DtVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        responses.setData(dtService.pagingQueryDtVO(param, null));
      } else {
        responses.setData(dtService.pagingQueryDtVO(param, dtQuery));
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

}
