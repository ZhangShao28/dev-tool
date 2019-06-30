package cn.moonnow.sign.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.rest.Responses;
import cn.moonnow.tool.rest.ToolController;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import cn.moonnow.sign.entity.LoginLog;
import cn.moonnow.sign.query.LoginLogQuery;
import cn.moonnow.sign.service.ILoginLogService;
import cn.moonnow.sign.vo.LoginLogVO;
import lombok.extern.log4j.Log4j2;

/**
 * 登录日志 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "sign/loginLog" })
public class LoginLogController extends ToolController {

  public static final String LOG = "LoginLogController";

  @Resource(name = "cn.moonnow.sign.LoginLogService")
  private ILoginLogService loginLogService;

  /**
   * 保存 登录日志 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<LoginLog> save(Param param, @RequestBody LoginLog loginLog) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLog);
    }
    Responses<LoginLog> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(loginLog)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(loginLogService.saveLoginLog(loginLog));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 登录日志 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<LoginLog> batchSave(Param param, @RequestBody List<LoginLog> loginLogs) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLogs);
    }
    Responses<LoginLog> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(loginLogs)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(loginLogService.batchSaveLoginLog(loginLogs));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 登录日志 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<LoginLog> update(Param param, @RequestBody LoginLog loginLog) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLog);
    }
    Responses<LoginLog> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(loginLog)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(loginLogService.updateLoginLog(loginLog));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 登录日志 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<LoginLog> batchUpdate(Param param, @RequestBody List<LoginLog> loginLogs) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLogs);
    }
    Responses<LoginLog> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(loginLogs)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(loginLogService.batchUpdateLoginLog(loginLogs));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 登录日志 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<LoginLog> remove(Param param, @RequestBody LoginLog loginLog) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLog);
    }
    Responses<LoginLog> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(loginLog)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      loginLogService.removeLoginLog(loginLog);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 登录日志 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<LoginLog> batchRemove(Param param, @RequestBody List<LoginLog> loginLogs) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLogs);
    }
    Responses<LoginLog> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(loginLogs)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      loginLogService.batchRemoveLoginLog(loginLogs);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 登录日志 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<LoginLog> getByPk(Param param, @RequestBody LoginLog loginLog) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLog);
    }
    Responses<LoginLog> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(loginLog)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(loginLog.getLogId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(loginLogService.getLoginLogByPk(loginLog.getLogId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 登录日志 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<LoginLog> getAll(Param param, @RequestBody LoginLogQuery loginLogQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLogQuery);
    }
    Responses<LoginLog> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(loginLogQuery)) {
        responses.setData(loginLogService.getAllLoginLog());
      } else {
        responses.setData(loginLogService.queryLoginLog(loginLogQuery));
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
   * 分页获取 登录日志 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<LoginLog> paging(Param param, @RequestBody LoginLogQuery loginLogQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLogQuery);
    }
    Responses<LoginLog> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(loginLogQuery)) {
        responses.setData(loginLogService.pagingQueryLoginLog(param, null));
      } else {
        responses.setData(loginLogService.pagingQueryLoginLog(param, loginLogQuery));
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
   * 通过主键获取 登录日志 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<LoginLogVO> getVOByPk(Param param, @RequestBody LoginLog loginLog) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLog);
    }
    Responses<LoginLogVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(loginLog)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(loginLog.getLogId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(loginLogService.getLoginLogVOByPk(loginLog.getLogId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 登录日志 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<LoginLogVO> getAllVO(Param param, @RequestBody LoginLogQuery loginLogQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLogQuery);
    }
    Responses<LoginLogVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(loginLogQuery)) {
        responses.setData(loginLogService.getAllLoginLogVO());
      } else {
        responses.setData(loginLogService.queryLoginLogVO(loginLogQuery));
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
   * 分页获取 登录日志 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<LoginLogVO> pagingVO(Param param, @RequestBody LoginLogQuery loginLogQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLogQuery);
    }
    Responses<LoginLogVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(loginLogQuery)) {
        responses.setData(loginLogService.pagingQueryLoginLogVO(param, null));
      } else {
        responses.setData(loginLogService.pagingQueryLoginLogVO(param, loginLogQuery));
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
