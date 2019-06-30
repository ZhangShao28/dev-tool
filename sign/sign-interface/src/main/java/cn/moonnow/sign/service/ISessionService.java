package cn.moonnow.sign.service;

import java.util.Collection;

import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.sign.entity.Session;
import cn.moonnow.sign.query.SessionQuery;
import cn.moonnow.sign.vo.SessionVO;

/**
 * 会话 服务接口
 */
public interface ISessionService {

  /**
   * 保存 会话 信息
   */
  public Session saveSession(Session session) throws Exception;

  /**
   * 批量保存 会话 信息
   */
  public Collection<Session> batchSaveSession(Collection<Session> sessions) throws Exception;

  /**
   * 修改 会话 信息
   */
  public Session updateSession(Session session) throws Exception;

  /**
   * 批量修改 会话 信息
   */
  public Collection<Session> batchUpdateSession(Collection<Session> sessions) throws Exception;

  /**
   * 删除 会话 信息
   */
  public void removeSession(Session session) throws Exception;

  /**
   * 批量删除 会话 信息
   */
  public void batchRemoveSession(Collection<Session> sessions) throws Exception;

  /**
   * 获取 会话 数据量
   */
  public Long getCountSession(SessionQuery sessionQuery) throws Exception;

  /**
   * 通过主键获取 会话 数据
   */
  public Session getSessionByPk(String sessionId) throws Exception;

  /**
   * 获取 会话 所有数据
   */
  public Collection<Session> getAllSession() throws Exception;

  /**
   * 查询 会话 数据
   */
  public Collection<Session> querySession(SessionQuery sessionQuery) throws Exception;

  /**
   * 分页查询 会话 数据
   */
  public Paging<Session> pagingQuerySession(Param param, SessionQuery sessionQuery) throws Exception;

  /**
   * 通过主键获取 会话 展示数据
   */
  public SessionVO getSessionVOByPk(String sessionId) throws Exception;

  /**
   * 获取 会话 所有展示数据
   */
  public Collection<SessionVO> getAllSessionVO() throws Exception;

  /**
   * 查询 会话 展示数据
   */
  public Collection<SessionVO> querySessionVO(SessionQuery sessionQuery) throws Exception;

  /**
   * 分页查询 会话 展示数据
   */
  public Paging<SessionVO> pagingQuerySessionVO(Param param, SessionQuery sessionQuery) throws Exception;

}
