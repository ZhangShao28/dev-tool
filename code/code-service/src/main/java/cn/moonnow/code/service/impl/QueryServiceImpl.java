package cn.moonnow.code.service.impl;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.moonnow.code.entity.Query;
import cn.moonnow.code.persistent.IQueryPersistent;
import cn.moonnow.code.query.QueryQuery;
import cn.moonnow.code.service.IQueryService;
import cn.moonnow.code.vo.QueryVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 查询 服务实现
 */
@Log4j2
@Service("cn.moonnow.code.QueryService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class QueryServiceImpl implements IQueryService {

  public static final String LOG = "QueryService";

  @Resource(name = "cn.moonnow.code.QueryPersistent")
  private IQueryPersistent queryPersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Query saveQuery(Query query) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + query);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(query)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      query.setQueryId(ToolUtil.getUUID());
      QueryQuery queryQuery = new QueryQuery();
      queryQuery.setQueryTypeAndeq(query.getQueryType());
      queryQuery.setDtIdAndeq(query.getDtId());
      queryQuery.setColumnsIdAndeq(query.getColumnsId());
      if (queryPersistent.getCountQuery(queryQuery) > 0) {
        throw new ToolException("查询条件：" + query.getQueryType() + " 不能重复");
      }
      return queryPersistent.saveQuery(query);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Query> batchSaveQuery(Collection<Query> querys) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + querys);
    }
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Query query : querys) {
        if (ToolUtil.isNullEntityAllFieldValue(query)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        query.setQueryId(ToolUtil.getUUID());
        QueryQuery queryQuery = new QueryQuery();
        queryQuery.setQueryTypeAndeq(query.getQueryType());
        queryQuery.setDtIdAndeq(query.getDtId());
        queryQuery.setColumnsIdAndeq(query.getColumnsId());
        if (queryPersistent.getCountQuery(queryQuery) > 0) {
          throw new ToolException("查询条件：" + query.getQueryType() + " 不能重复");
        }
      }
      return queryPersistent.batchSaveQuery(querys);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Query updateQuery(Query query) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + query);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(query)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(query.getQueryId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Query oldQuery = queryPersistent.getQueryByPk(query.getQueryId());
      if (ToolUtil.isNullEntityAllFieldValue(oldQuery)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      QueryQuery queryQuery = new QueryQuery();
      queryQuery.setQueryTypeAndeq(query.getQueryType());
      queryQuery.setDtIdAndeq(query.getDtId());
      queryQuery.setColumnsIdAndeq(query.getColumnsId());
      if (!(query.getQueryType().equals(oldQuery.getQueryType()) && query.getDtId().equals(oldQuery.getDtId()) && query.getColumnsId().equals(oldQuery.getColumnsId()))) {
        if (queryPersistent.getCountQuery(queryQuery) > 0) {
          throw new ToolException("查询条件：" + query.getQueryType() + " 不能重复");
        }
      }
      return queryPersistent.updateQuery(query);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Query> batchUpdateQuery(Collection<Query> querys) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + querys);
    }
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Query query : querys) {
        if (ToolUtil.isNullEntityAllFieldValue(query)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(query.getQueryId())) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        Query oldQuery = queryPersistent.getQueryByPk(query.getQueryId());
        if (ToolUtil.isNullEntityAllFieldValue(oldQuery)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        QueryQuery queryQuery = new QueryQuery();
        queryQuery.setQueryTypeAndeq(query.getQueryType());
        queryQuery.setDtIdAndeq(query.getDtId());
        queryQuery.setColumnsIdAndeq(query.getColumnsId());
        if (!(query.getQueryType().equals(oldQuery.getQueryType()) && query.getDtId().equals(oldQuery.getDtId()) && query.getColumnsId().equals(oldQuery.getColumnsId()))) {
          if (queryPersistent.getCountQuery(queryQuery) > 0) {
            throw new ToolException("查询条件：" + query.getQueryType() + " 不能重复");
          }
        }
      }
      return queryPersistent.batchUpdateQuery(querys);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void removeQuery(Query query) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + query);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(query)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Query> querySet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(query.getQueryId())) {
        QueryQuery queryQuery = new QueryQuery();
        BeanUtils.copyProperties(query, queryQuery);
        querySet.addAll(queryPersistent.queryQuery(queryQuery));
      } else {
        Query oldQuery = queryPersistent.getQueryByPk(query.getQueryId());
        if (ToolUtil.isNullEntityAllFieldValue(oldQuery)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        querySet.add(oldQuery);
      }
      if (ToolUtil.isNotEmpty(querySet)) {
        queryPersistent.batchRemoveQuery(querySet);
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
  public void batchRemoveQuery(Collection<Query> querys) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + querys);
    }
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Query> querySet = new LinkedHashSet<>();
      for (Query query : querys) {
        if (ToolUtil.isNullEntityAllFieldValue(query)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(query.getQueryId())) {
          QueryQuery queryQuery = new QueryQuery();
          BeanUtils.copyProperties(query, queryQuery);
          querySet.addAll(queryPersistent.queryQuery(queryQuery));
        } else {
          Query oldQuery = queryPersistent.getQueryByPk(query.getQueryId());
          if (ToolUtil.isNullEntityAllFieldValue(oldQuery)) {
            throw new ToolException(ToolException.E_PARAM_ERR);
          }
          querySet.add(oldQuery);
        }
      }
      if (ToolUtil.isNotEmpty(querySet)) {
        queryPersistent.batchRemoveQuery(querySet);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Long getCountQuery(QueryQuery queryQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + queryQuery);
    }
    try {
      return queryPersistent.getCountQuery(queryQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Query getQueryByPk(String queryId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + queryId);
    }
    try {
      if (ToolUtil.isNullStr(queryId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return queryPersistent.getQueryByPk(queryId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Query> getAllQuery() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return queryPersistent.getAllQuery();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Query> queryQuery(QueryQuery queryQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + queryQuery);
    }
    try {
      return queryPersistent.queryQuery(queryQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<Query> pagingQueryQuery(Param param, QueryQuery queryQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + queryQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return queryPersistent.pagingQueryQuery(param, queryQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public QueryVO getQueryVOByPk(String queryId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + queryId);
    }
    try {
      if (ToolUtil.isNullStr(queryId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return queryPersistent.getQueryVOByPk(queryId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<QueryVO> getAllQueryVO() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return queryPersistent.getAllQueryVO();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<QueryVO> queryQueryVO(QueryQuery queryQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + queryQuery);
    }
    try {
      return queryPersistent.queryQueryVO(queryQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<QueryVO> pagingQueryQueryVO(Param param, QueryQuery queryQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + queryQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return queryPersistent.pagingQueryQueryVO(param, queryQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
