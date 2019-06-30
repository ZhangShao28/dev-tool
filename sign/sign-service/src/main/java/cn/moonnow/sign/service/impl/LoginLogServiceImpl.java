package cn.moonnow.sign.service.impl;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import cn.moonnow.sign.entity.LoginLog;
import cn.moonnow.sign.persistent.ILoginLogPersistent;
import cn.moonnow.sign.query.LoginLogQuery;
import cn.moonnow.sign.service.ILoginLogService;
import cn.moonnow.sign.vo.LoginLogVO;
import lombok.extern.log4j.Log4j2;

/**
 * 登录日志 服务实现
 */
@Log4j2
@Service("cn.moonnow.sign.LoginLogService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class LoginLogServiceImpl implements ILoginLogService {

  public static final String LOG = "LoginLogService";

  @Resource(name = "cn.moonnow.sign.LoginLogPersistent")
  private ILoginLogPersistent loginLogPersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public LoginLog saveLoginLog(LoginLog loginLog) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + loginLog);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(loginLog)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      loginLog.setLogId(ToolUtil.getUUID());
      return loginLogPersistent.saveLoginLog(loginLog);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<LoginLog> batchSaveLoginLog(Collection<LoginLog> loginLogs) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + loginLogs);
    }
    try {
      if (ToolUtil.isEmpty(loginLogs)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (LoginLog loginLog : loginLogs) {
        if (ToolUtil.isNullEntityAllFieldValue(loginLog)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        loginLog.setLogId(ToolUtil.getUUID());
      }
      return loginLogPersistent.batchSaveLoginLog(loginLogs);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public LoginLog updateLoginLog(LoginLog loginLog) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + loginLog);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(loginLog)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(loginLog.getLogId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      LoginLog oldLoginLog = loginLogPersistent.getLoginLogByPk(loginLog.getLogId());
      if (ToolUtil.isNullEntityAllFieldValue(oldLoginLog)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return loginLogPersistent.updateLoginLog(loginLog);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<LoginLog> batchUpdateLoginLog(Collection<LoginLog> loginLogs) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + loginLogs);
    }
    try {
      if (ToolUtil.isEmpty(loginLogs)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (LoginLog loginLog : loginLogs) {
        if (ToolUtil.isNullEntityAllFieldValue(loginLog)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(loginLog.getLogId())) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        LoginLog oldLoginLog = loginLogPersistent.getLoginLogByPk(loginLog.getLogId());
        if (ToolUtil.isNullEntityAllFieldValue(oldLoginLog)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
      }
      return loginLogPersistent.batchUpdateLoginLog(loginLogs);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void removeLoginLog(LoginLog loginLog) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + loginLog);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(loginLog)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<LoginLog> loginLogSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(loginLog.getLogId())) {
        LoginLogQuery loginLogQuery = new LoginLogQuery();
        BeanUtils.copyProperties(loginLog, loginLogQuery);
        loginLogSet.addAll(loginLogPersistent.queryLoginLog(loginLogQuery));
      } else {
        LoginLog oldLoginLog = loginLogPersistent.getLoginLogByPk(loginLog.getLogId());
        if (ToolUtil.isNullEntityAllFieldValue(oldLoginLog)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        loginLogSet.add(oldLoginLog);
      }
      if (ToolUtil.isNotEmpty(loginLogSet)) {
        loginLogPersistent.batchRemoveLoginLog(loginLogSet);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void batchRemoveLoginLog(Collection<LoginLog> loginLogs) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + loginLogs);
    }
    try {
      if (ToolUtil.isEmpty(loginLogs)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<LoginLog> loginLogSet = new LinkedHashSet<>();
      for (LoginLog loginLog : loginLogs) {
        if (ToolUtil.isNullEntityAllFieldValue(loginLog)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(loginLog.getLogId())) {
          LoginLogQuery loginLogQuery = new LoginLogQuery();
          BeanUtils.copyProperties(loginLog, loginLogQuery);
          loginLogSet.addAll(loginLogPersistent.queryLoginLog(loginLogQuery));
        } else {
          LoginLog oldLoginLog = loginLogPersistent.getLoginLogByPk(loginLog.getLogId());
          if (ToolUtil.isNullEntityAllFieldValue(oldLoginLog)) {
            throw new ToolException(ToolException.E_PARAM_ERR);
          }
          loginLogSet.add(oldLoginLog);
        }
      }
      if (ToolUtil.isNotEmpty(loginLogSet)) {
        loginLogPersistent.batchRemoveLoginLog(loginLogSet);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Long getCountLoginLog(LoginLogQuery loginLogQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + loginLogQuery);
    }
    try {
      return loginLogPersistent.getCountLoginLog(loginLogQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public LoginLog getLoginLogByPk(String logId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + logId);
    }
    try {
      if (ToolUtil.isNullStr(logId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return loginLogPersistent.getLoginLogByPk(logId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<LoginLog> getAllLoginLog() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return loginLogPersistent.getAllLoginLog();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<LoginLog> queryLoginLog(LoginLogQuery loginLogQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + loginLogQuery);
    }
    try {
      return loginLogPersistent.queryLoginLog(loginLogQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<LoginLog> pagingQueryLoginLog(Param param, LoginLogQuery loginLogQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLogQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return loginLogPersistent.pagingQueryLoginLog(param, loginLogQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public LoginLogVO getLoginLogVOByPk(String logId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + logId);
    }
    try {
      if (ToolUtil.isNullStr(logId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return loginLogPersistent.getLoginLogVOByPk(logId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<LoginLogVO> getAllLoginLogVO() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return loginLogPersistent.getAllLoginLogVO();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<LoginLogVO> queryLoginLogVO(LoginLogQuery loginLogQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + loginLogQuery);
    }
    try {
      return loginLogPersistent.queryLoginLogVO(loginLogQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<LoginLogVO> pagingQueryLoginLogVO(Param param, LoginLogQuery loginLogQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + loginLogQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return loginLogPersistent.pagingQueryLoginLogVO(param, loginLogQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
