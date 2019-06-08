package cn.moonnow.code.persistent.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.moonnow.code.entity.Dt;
import cn.moonnow.code.persistent.IDtPersistent;
import cn.moonnow.code.query.DtQuery;
import cn.moonnow.code.vo.DtVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.persistent.impl.ToolPersistent;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 数据库表 持久化实现
 */
@Log4j2
@Repository("cn.moonnow.code.DtPersistent")
public class DtPersistentImpl extends ToolPersistent implements IDtPersistent {

  public static final String LOG = "DtPersistent";

  public static final String TABLE_ALIAS = "dt";

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

  public static final String COLUMN_DT_ID = "dtId";
  public static final String COLUMN_DT_SQL = "dtSql";
  public static final String COLUMN_DT_NAME = "dtName";
  public static final String COLUMN_DT_NAME_ANNOTATION = "dtNameAnnotation";
  public static final String COLUMN_DT_PREFIX = "dtPrefix";
  public static final String COLUMN_INITIAL_CASE_ENTITY_NAME = "initialCaseEntityName";
  public static final String COLUMN_INITIAL_LOWERCASE_ENTITY_NAME = "initialLowercaseEntityName";
  public static final String COLUMN_PRO_PATH = "proPath";
  public static final String COLUMN_PRO_ALL_NAME = "proAllName";

  static {
    PRIMARY_KEY.add(COLUMN_DT_ID);

    COLUMNS.add(COLUMN_DT_ID);
    COLUMNS.add(COLUMN_DT_SQL);
    COLUMNS.add(COLUMN_DT_NAME);
    COLUMNS.add(COLUMN_DT_NAME_ANNOTATION);
    COLUMNS.add(COLUMN_DT_PREFIX);
    COLUMNS.add(COLUMN_INITIAL_CASE_ENTITY_NAME);
    COLUMNS.add(COLUMN_INITIAL_LOWERCASE_ENTITY_NAME);
    COLUMNS.add(COLUMN_PRO_PATH);
    COLUMNS.add(COLUMN_PRO_ALL_NAME);

    COLUMNS_PARAMETER.put(COLUMN_DT_ID, "DT_ID");
    COLUMNS_PARAMETER.put(COLUMN_DT_SQL, "DT_SQL");
    COLUMNS_PARAMETER.put(COLUMN_DT_NAME, "DT_NAME");
    COLUMNS_PARAMETER.put(COLUMN_DT_NAME_ANNOTATION, "DT_NAME_ANNOTATION");
    COLUMNS_PARAMETER.put(COLUMN_DT_PREFIX, "DT_PREFIX");
    COLUMNS_PARAMETER.put(COLUMN_INITIAL_CASE_ENTITY_NAME, "INITIAL_CASE_ENTITY_NAME");
    COLUMNS_PARAMETER.put(COLUMN_INITIAL_LOWERCASE_ENTITY_NAME, "INITIAL_LOWERCASE_ENTITY_NAME");
    COLUMNS_PARAMETER.put(COLUMN_PRO_PATH, "PRO_PATH");
    COLUMNS_PARAMETER.put(COLUMN_PRO_ALL_NAME, "PRO_ALL_NAME");

    SORT.put(COLUMN_DT_NAME, ASC);

    INSERT_SQL = getInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
    UPDATE_SQL = getUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY);
    DEL_SQL_BY_PK = getDelSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
    SELECT_VO_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, VIRTUAL_COLUMNS, PRIMARY_KEY, TABLE_ALIAS);
    COUNT_SQL = getCountSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
  }

  @Override
  public Dt saveDt(Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dt);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dt.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.insert(INSERT_SQL, dt);
      return dt;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Dt> batchSaveDt(Collection<Dt> dts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dts);
    }
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.insert(INSERT_SQL, dts);
      return dts;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Dt updateDt(Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dt);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dt.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.update(UPDATE_SQL, dt);
      return dt;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Dt> batchUpdateDt(Collection<Dt> dts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dts);
    }
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.update(UPDATE_SQL, dts);
      return dts;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void removeDt(Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dt);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dt.getDtId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, dt.getDtId());
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
  public void batchRemoveDt(Collection<Dt> dts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dts);
    }
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.del(DEL_SQL_BY_PK, dts);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Long getCountDt(DtQuery dtQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dtQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, dtQuery));
      }
      if (ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        return this.queryCount(countSql, Long.class);
      } else {
        return this.queryCount(countSql, dtQuery, Long.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Dt getDtByPk(String dtId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dtId);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, dtId);
        }
      }
      Collection<Dt> dtSet = this.query(querySql, mapSqlParameterSource, Dt.class);
      return ToolUtil.isNotEmpty(dtSet) ? dtSet.iterator().next() : null;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Dt> getAllDt() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, Dt.class);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Dt> queryDt(DtQuery dtQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dtQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, dtQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      if (ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        return this.query(querySql, Dt.class);
      } else {
        return this.query(querySql, dtQuery, Dt.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<Dt> pagingQueryDt(Param param, DtQuery dtQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dtQuery);
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
      Paging<Dt> paging = new Paging<>(param);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, dtQuery));
      }
      Long count = null;
      if (ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        count = this.queryCount(countSql, Long.class);
      } else {
        count = this.queryCount(countSql, dtQuery, Long.class);
      }
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        if (!ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
          querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, dtQuery));
        }
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Dt> dtSet = null;
        if (ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
          dtSet = this.query(querySql, Dt.class);
        } else {
          dtSet = this.query(querySql, dtQuery, Dt.class);
        }
        if (ToolUtil.isNotEmpty(dtSet)) {
          Set<String> inDtId = new LinkedHashSet<>();
          for (Dt dt : dtSet) {
            inDtId.add(dt.getDtId());
          }
          DtQuery rDtQuery = new DtQuery();
          rDtQuery.setDtIdAndin(new ArrayList<>(inDtId));
          Collection<Dt> rDtSet = this.queryDt(rDtQuery);
          if (ToolUtil.isNotEmpty(rDtSet)) {
            paging.setData(rDtSet);
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
  public DtVO getDtVOByPk(String dtId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dtId);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, dtId);
        }
      }
      Collection<DtVO> dtVOSet = this.query(querySql, mapSqlParameterSource, DtVO.class);
      return ToolUtil.isNotEmpty(dtVOSet) ? dtVOSet.iterator().next() : null;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<DtVO> getAllDtVO() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, DtVO.class);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<DtVO> queryDtVO(DtQuery dtQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dtQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, dtQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      if (ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        return this.query(querySql, DtVO.class);
      } else {
        return this.query(querySql, dtQuery, DtVO.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<DtVO> pagingQueryDtVO(Param param, DtQuery dtQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dtQuery);
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
      Paging<DtVO> paging = new Paging<>(param);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, dtQuery));
      }
      Long count = null;
      if (ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
        count = this.queryCount(countSql, Long.class);
      } else {
        count = this.queryCount(countSql, dtQuery, Long.class);
      }
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        if (!ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
          querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, dtQuery));
        }
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Dt> dtSet = null;
        if (ToolUtil.isNullEntityAllFieldValue(dtQuery)) {
          dtSet = this.query(querySql, Dt.class);
        } else {
          dtSet = this.query(querySql, dtQuery, Dt.class);
        }
        if (ToolUtil.isNotEmpty(dtSet)) {
          Set<String> inDtId = new LinkedHashSet<>();
          for (Dt dt : dtSet) {
            inDtId.add(dt.getDtId());
          }
          DtQuery rDtQuery = new DtQuery();
          rDtQuery.setDtIdAndin(new ArrayList<>(inDtId));
          Collection<DtVO> rDtVOSet = this.queryDtVO(rDtQuery);
          if (ToolUtil.isNotEmpty(rDtVOSet)) {
            paging.setData(rDtVOSet);
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
