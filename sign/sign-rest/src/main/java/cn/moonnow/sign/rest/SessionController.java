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
import cn.moonnow.sign.entity.Session;
import cn.moonnow.sign.query.SessionQuery;
import cn.moonnow.sign.service.ISessionService;
import cn.moonnow.sign.vo.SessionVO;
import lombok.extern.log4j.Log4j2;

/**
 * 会话 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "sign/session" })
public class SessionController extends ToolController {

  public static final String LOG = "SessionController";

  @Resource(name = "cn.moonnow.sign.SessionService")
  private ISessionService sessionService;

  /**
   * 保存 会话 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<Session> save(Param param, @RequestBody Session session) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + session);
    }
    Responses<Session> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(session)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(sessionService.saveSession(session));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 会话 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<Session> batchSave(Param param, @RequestBody List<Session> sessions) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sessions);
    }
    Responses<Session> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(sessions)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(sessionService.batchSaveSession(sessions));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 会话 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<Session> update(Param param, @RequestBody Session session) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + session);
    }
    Responses<Session> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(session)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(sessionService.updateSession(session));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 会话 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<Session> batchUpdate(Param param, @RequestBody List<Session> sessions) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sessions);
    }
    Responses<Session> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(sessions)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(sessionService.batchUpdateSession(sessions));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 会话 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<Session> remove(Param param, @RequestBody Session session) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + session);
    }
    Responses<Session> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(session)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      sessionService.removeSession(session);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 会话 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<Session> batchRemove(Param param, @RequestBody List<Session> sessions) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sessions);
    }
    Responses<Session> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(sessions)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      sessionService.batchRemoveSession(sessions);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 会话 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<Session> getByPk(Param param, @RequestBody Session session) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + session);
    }
    Responses<Session> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(session)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(session.getSessionId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(sessionService.getSessionByPk(session.getSessionId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 会话 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<Session> getAll(Param param, @RequestBody SessionQuery sessionQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sessionQuery);
    }
    Responses<Session> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sessionQuery)) {
        responses.setData(sessionService.getAllSession());
      } else {
        responses.setData(sessionService.querySession(sessionQuery));
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
   * 分页获取 会话 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<Session> paging(Param param, @RequestBody SessionQuery sessionQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sessionQuery);
    }
    Responses<Session> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sessionQuery)) {
        responses.setData(sessionService.pagingQuerySession(param, null));
      } else {
        responses.setData(sessionService.pagingQuerySession(param, sessionQuery));
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
   * 通过主键获取 会话 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<SessionVO> getVOByPk(Param param, @RequestBody Session session) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + session);
    }
    Responses<SessionVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(session)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(session.getSessionId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(sessionService.getSessionVOByPk(session.getSessionId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 会话 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<SessionVO> getAllVO(Param param, @RequestBody SessionQuery sessionQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sessionQuery);
    }
    Responses<SessionVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sessionQuery)) {
        responses.setData(sessionService.getAllSessionVO());
      } else {
        responses.setData(sessionService.querySessionVO(sessionQuery));
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
   * 分页获取 会话 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<SessionVO> pagingVO(Param param, @RequestBody SessionQuery sessionQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sessionQuery);
    }
    Responses<SessionVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sessionQuery)) {
        responses.setData(sessionService.pagingQuerySessionVO(param, null));
      } else {
        responses.setData(sessionService.pagingQuerySessionVO(param, sessionQuery));
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
