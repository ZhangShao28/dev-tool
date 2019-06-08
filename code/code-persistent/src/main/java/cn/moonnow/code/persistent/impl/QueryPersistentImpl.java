package cn.moonnow.code.persistent.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.moonnow.code.entity.Query;
import cn.moonnow.code.persistent.IQueryPersistent;
import cn.moonnow.code.query.QueryQuery;
import cn.moonnow.code.vo.QueryVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.persistent.impl.ToolPersistent;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 查询 持久化实现
 */
@Log4j2
@Repository("cn.moonnow.code.QueryPersistent")
public class QueryPersistentImpl extends ToolPersistent implements IQueryPersistent {

  public static final String LOG = "QueryPersistent";

  public static final String TABLE_ALIAS = "query";

  public static final LinkedHashSet<String> PRIMARY_KEY = new LinkedHashSet<>();
  public static final LinkedHashSet<String> COLUMNS = new LinkedHashSet<>();
  public static final LinkedHashMap<String, String> COLUMNS_PARAMETER = new LinkedHashMap<>();
  public static final LinkedHashSet<String> VIRTUAL_COLUMNS = new LinkedHashSet<>();
  public static final LinkedHashMap<String, String> SORT = new LinkedHashMap<>();

  private static StringBuilder INSERT_SQL = new StringBuilder();
  private static StringBuilder UPDATE_SQL = new StringBuilder();
  private static StringBuilder DEL_SQL_BY_PK = new StringBuilder();
  public static StringBuilder SELECT_SQL = new StringBuilder();
  public static StringBuilder SELECT_VO_SQL = new StringBuilder();
  public static StringBuilder COUNT_SQL = new StringBuilder();

  public static final String COLUMN_QUERY_ID = "queryId";
  public static final String COLUMN_QUERY_TYPE = "queryType";
  public static final String COLUMN_DT_ID = "dtId";
  public static final String COLUMN_COLUMNS_ID = "columnsId";
  public static final String COLUMN_WEIGHT_ORDER = "weightOrder";

  public static final String VIRTUAL_COLUMN_COLUMN_NAME = "( SELECT columns.COLUMN_NAME FROM PRO_COLUMNS columns WHERE columns.COLUMNS_ID IS NOT NULL AND columns.COLUMNS_ID = query.COLUMNS_ID ) AS COLUMN_NAME";
  public static final String VIRTUAL_COLUMN_COLUMN_NAME_ANNOTATION = "( SELECT columns.COLUMN_NAME_ANNOTATION FROM PRO_COLUMNS columns WHERE columns.COLUMNS_ID IS NOT NULL AND columns.COLUMNS_ID = query.COLUMNS_ID ) AS COLUMN_NAME_ANNOTATION";

  static {
    PRIMARY_KEY.add(COLUMN_QUERY_ID);

    COLUMNS.add(COLUMN_QUERY_ID);
    COLUMNS.add(COLUMN_QUERY_TYPE);
    COLUMNS.add(COLUMN_DT_ID);
    COLUMNS.add(COLUMN_COLUMNS_ID);
    COLUMNS.add(COLUMN_WEIGHT_ORDER);

    COLUMNS_PARAMETER.put(COLUMN_QUERY_ID, "QUERY_ID");
    COLUMNS_PARAMETER.put(COLUMN_QUERY_TYPE, "QUERY_TYPE");
    COLUMNS_PARAMETER.put(COLUMN_DT_ID, "DT_ID");
    COLUMNS_PARAMETER.put(COLUMN_COLUMNS_ID, "COLUMNS_ID");
    COLUMNS_PARAMETER.put(COLUMN_WEIGHT_ORDER, "WEIGHT_ORDER");

    VIRTUAL_COLUMNS.add(VIRTUAL_COLUMN_COLUMN_NAME);
    VIRTUAL_COLUMNS.add(VIRTUAL_COLUMN_COLUMN_NAME_ANNOTATION);

    SORT.put(COLUMN_WEIGHT_ORDER, ASC);

    INSERT_SQL = getInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
    UPDATE_SQL = getUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY);
    DEL_SQL_BY_PK = getDelSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
    SELECT_VO_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, VIRTUAL_COLUMNS, PRIMARY_KEY, TABLE_ALIAS);
    COUNT_SQL = getCountSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
  }

  @Override
  public Query saveQuery(Query query) throws Exception {
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
      this.insert(INSERT_SQL, query);
      return query;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Query> batchSaveQuery(Collection<Query> querys) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + querys);
    }
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.insert(INSERT_SQL, querys);
      return querys;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
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
      this.update(UPDATE_SQL, query);
      return query;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Query> batchUpdateQuery(Collection<Query> querys) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + querys);
    }
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.update(UPDATE_SQL, querys);
      return querys;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void removeQuery(Query query) throws Exception {
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
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, query.getQueryId());
        }
      }
      this.del(DEL_SQL_BY_PK, mapSqlParameterSource);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void batchRemoveQuery(Collection<Query> querys) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + querys);
    }
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.del(DEL_SQL_BY_PK, querys);
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
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, queryQuery));
      }
      if (ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        return this.queryCount(countSql, Long.class);
      } else {
        return this.queryCount(countSql, queryQuery, Long.class);
      }
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
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, queryId);
        }
      }
      Collection<Query> querySet = this.query(querySql, mapSqlParameterSource, Query.class);
      return ToolUtil.isNotEmpty(querySet) ? querySet.iterator().next() : null;
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
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, Query.class);
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
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, queryQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      if (ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        return this.query(querySql, Query.class);
      } else {
        return this.query(querySql, queryQuery, Query.class);
      }
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
      if (param.getRows() < 0) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (param.getPage() < 1) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Paging<Query> paging = new Paging<>(param);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, queryQuery));
      }
      Long count = null;
      if (ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        count = this.queryCount(countSql, Long.class);
      } else {
        count = this.queryCount(countSql, queryQuery, Long.class);
      }
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        if (!ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
          querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, queryQuery));
        }
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Query> querySet = null;
        if (ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
          querySet = this.query(querySql, Query.class);
        } else {
          querySet = this.query(querySql, queryQuery, Query.class);
        }
        if (ToolUtil.isNotEmpty(querySet)) {
          Set<String> inQueryId = new LinkedHashSet<>();
          for (Query query : querySet) {
            inQueryId.add(query.getQueryId());
          }
          QueryQuery rQueryQuery = new QueryQuery();
          rQueryQuery.setQueryIdAndin(new ArrayList<>(inQueryId));
          Collection<Query> rQuerySet = this.queryQuery(rQueryQuery);
          if (ToolUtil.isNotEmpty(rQuerySet)) {
            paging.setData(rQuerySet);
          }
        }
      }
      return paging;
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
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, queryId);
        }
      }
      Collection<QueryVO> queryVOSet = this.query(querySql, mapSqlParameterSource, QueryVO.class);
      return ToolUtil.isNotEmpty(queryVOSet) ? queryVOSet.iterator().next() : null;
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
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, QueryVO.class);
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
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, queryQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      if (ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        return this.query(querySql, QueryVO.class);
      } else {
        return this.query(querySql, queryQuery, QueryVO.class);
      }
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
      if (param.getRows() < 0) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (param.getPage() < 1) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Paging<QueryVO> paging = new Paging<>(param);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, queryQuery));
      }
      Long count = null;
      if (ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        count = this.queryCount(countSql, Long.class);
      } else {
        count = this.queryCount(countSql, queryQuery, Long.class);
      }
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        if (!ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
          querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, queryQuery));
        }
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Query> querySet = null;
        if (ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
          querySet = this.query(querySql, Query.class);
        } else {
          querySet = this.query(querySql, queryQuery, Query.class);
        }
        if (ToolUtil.isNotEmpty(querySet)) {
          Set<String> inQueryId = new LinkedHashSet<>();
          for (Query query : querySet) {
            inQueryId.add(query.getQueryId());
          }
          QueryQuery rQueryQuery = new QueryQuery();
          rQueryQuery.setQueryIdAndin(new ArrayList<>(inQueryId));
          Collection<QueryVO> rQueryVOSet = this.queryQueryVO(rQueryQuery);
          if (ToolUtil.isNotEmpty(rQueryVOSet)) {
            paging.setData(rQueryVOSet);
          }
        }
      }
      return paging;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
