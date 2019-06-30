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
import cn.moonnow.sign.entity.Session;
import cn.moonnow.sign.persistent.ISessionPersistent;
import cn.moonnow.sign.query.SessionQuery;
import cn.moonnow.sign.service.ISessionService;
import cn.moonnow.sign.vo.SessionVO;
import lombok.extern.log4j.Log4j2;

/**
 * 会话 服务实现
 */
@Log4j2
@Service("cn.moonnow.sign.SessionService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class SessionServiceImpl implements ISessionService {

  public static final String LOG = "SessionService";

  @Resource(name = "cn.moonnow.sign.SessionPersistent")
  private ISessionPersistent sessionPersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Session saveSession(Session session) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + session);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(session)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      session.setSessionId(ToolUtil.getUUID());
      return sessionPersistent.saveSession(session);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Session> batchSaveSession(Collection<Session> sessions) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sessions);
    }
    try {
      if (ToolUtil.isEmpty(sessions)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Session session : sessions) {
        if (ToolUtil.isNullEntityAllFieldValue(session)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        session.setSessionId(ToolUtil.getUUID());
      }
      return sessionPersistent.batchSaveSession(sessions);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Session updateSession(Session session) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + session);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(session)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(session.getSessionId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Session oldSession = sessionPersistent.getSessionByPk(session.getSessionId());
      if (ToolUtil.isNullEntityAllFieldValue(oldSession)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return sessionPersistent.updateSession(session);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Session> batchUpdateSession(Collection<Session> sessions) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sessions);
    }
    try {
      if (ToolUtil.isEmpty(sessions)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Session session : sessions) {
        if (ToolUtil.isNullEntityAllFieldValue(session)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(session.getSessionId())) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        Session oldSession = sessionPersistent.getSessionByPk(session.getSessionId());
        if (ToolUtil.isNullEntityAllFieldValue(oldSession)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
      }
      return sessionPersistent.batchUpdateSession(sessions);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void removeSession(Session session) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + session);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(session)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Session> sessionSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(session.getSessionId())) {
        SessionQuery sessionQuery = new SessionQuery();
        BeanUtils.copyProperties(session, sessionQuery);
        sessionSet.addAll(sessionPersistent.querySession(sessionQuery));
      } else {
        Session oldSession = sessionPersistent.getSessionByPk(session.getSessionId());
        if (ToolUtil.isNullEntityAllFieldValue(oldSession)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        sessionSet.add(oldSession);
      }
      if (ToolUtil.isNotEmpty(sessionSet)) {
        sessionPersistent.batchRemoveSession(sessionSet);
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
  public void batchRemoveSession(Collection<Session> sessions) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sessions);
    }
    try {
      if (ToolUtil.isEmpty(sessions)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Session> sessionSet = new LinkedHashSet<>();
      for (Session session : sessions) {
        if (ToolUtil.isNullEntityAllFieldValue(session)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(session.getSessionId())) {
          SessionQuery sessionQuery = new SessionQuery();
          BeanUtils.copyProperties(session, sessionQuery);
          sessionSet.addAll(sessionPersistent.querySession(sessionQuery));
        } else {
          Session oldSession = sessionPersistent.getSessionByPk(session.getSessionId());
          if (ToolUtil.isNullEntityAllFieldValue(oldSession)) {
            throw new ToolException(ToolException.E_PARAM_ERR);
          }
          sessionSet.add(oldSession);
        }
      }
      if (ToolUtil.isNotEmpty(sessionSet)) {
        sessionPersistent.batchRemoveSession(sessionSet);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Long getCountSession(SessionQuery sessionQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sessionQuery);
    }
    try {
      return sessionPersistent.getCountSession(sessionQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Session getSessionByPk(String sessionId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sessionId);
    }
    try {
      if (ToolUtil.isNullStr(sessionId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return sessionPersistent.getSessionByPk(sessionId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Session> getAllSession() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return sessionPersistent.getAllSession();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Session> querySession(SessionQuery sessionQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sessionQuery);
    }
    try {
      return sessionPersistent.querySession(sessionQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<Session> pagingQuerySession(Param param, SessionQuery sessionQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sessionQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return sessionPersistent.pagingQuerySession(param, sessionQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public SessionVO getSessionVOByPk(String sessionId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sessionId);
    }
    try {
      if (ToolUtil.isNullStr(sessionId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return sessionPersistent.getSessionVOByPk(sessionId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<SessionVO> getAllSessionVO() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return sessionPersistent.getAllSessionVO();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<SessionVO> querySessionVO(SessionQuery sessionQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sessionQuery);
    }
    try {
      return sessionPersistent.querySessionVO(sessionQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<SessionVO> pagingQuerySessionVO(Param param, SessionQuery sessionQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sessionQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return sessionPersistent.pagingQuerySessionVO(param, sessionQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
