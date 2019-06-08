package cn.moonnow.code.persistent.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.moonnow.code.entity.Columns;
import cn.moonnow.code.persistent.IColumnsPersistent;
import cn.moonnow.code.query.ColumnsQuery;
import cn.moonnow.code.vo.ColumnsVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.persistent.impl.ToolPersistent;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 列 持久化实现
 */
@Log4j2
@Repository("cn.moonnow.code.ColumnsPersistent")
public class ColumnsPersistentImpl extends ToolPersistent implements IColumnsPersistent {

  public static final String LOG = "ColumnsPersistent";

  public static final String TABLE_ALIAS = "columns";

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

  public static final String COLUMN_COLUMNS_ID = "columnsId";
  public static final String COLUMN_COLUMN_NAME = "columnName";
  public static final String COLUMN_COLUMN_NAME_ANNOTATION = "columnNameAnnotation";
  public static final String COLUMN_DATA_TYPE = "dataType";
  public static final String COLUMN_IS_NULL = "isNull";
  public static final String COLUMN_INITIAL_CASE_COLUMN_NAME = "initialCaseColumnName";
  public static final String COLUMN_INITIAL_LOWERCASE_COLUMN_NAME = "initialLowercaseColumnName";
  public static final String COLUMN_WEIGHT_ORDER = "weightOrder";
  public static final String COLUMN_DT_ID = "dtId";

  static {
    PRIMARY_KEY.add(COLUMN_COLUMNS_ID);

    COLUMNS.add(COLUMN_COLUMNS_ID);
    COLUMNS.add(COLUMN_COLUMN_NAME);
    COLUMNS.add(COLUMN_COLUMN_NAME_ANNOTATION);
    COLUMNS.add(COLUMN_DATA_TYPE);
    COLUMNS.add(COLUMN_IS_NULL);
    COLUMNS.add(COLUMN_INITIAL_CASE_COLUMN_NAME);
    COLUMNS.add(COLUMN_INITIAL_LOWERCASE_COLUMN_NAME);
    COLUMNS.add(COLUMN_WEIGHT_ORDER);
    COLUMNS.add(COLUMN_DT_ID);

    COLUMNS_PARAMETER.put(COLUMN_COLUMNS_ID, "COLUMNS_ID");
    COLUMNS_PARAMETER.put(COLUMN_COLUMN_NAME, "COLUMN_NAME");
    COLUMNS_PARAMETER.put(COLUMN_COLUMN_NAME_ANNOTATION, "COLUMN_NAME_ANNOTATION");
    COLUMNS_PARAMETER.put(COLUMN_DATA_TYPE, "DATA_TYPE");
    COLUMNS_PARAMETER.put(COLUMN_IS_NULL, "IS_NULL");
    COLUMNS_PARAMETER.put(COLUMN_INITIAL_CASE_COLUMN_NAME, "INITIAL_CASE_COLUMN_NAME");
    COLUMNS_PARAMETER.put(COLUMN_INITIAL_LOWERCASE_COLUMN_NAME, "INITIAL_LOWERCASE_COLUMN_NAME");
    COLUMNS_PARAMETER.put(COLUMN_WEIGHT_ORDER, "WEIGHT_ORDER");
    COLUMNS_PARAMETER.put(COLUMN_DT_ID, "DT_ID");

    SORT.put(COLUMN_WEIGHT_ORDER, ASC);

    INSERT_SQL = getInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
    UPDATE_SQL = getUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY);
    DEL_SQL_BY_PK = getDelSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
    SELECT_VO_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, VIRTUAL_COLUMNS, PRIMARY_KEY, TABLE_ALIAS);
    COUNT_SQL = getCountSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
  }

  @Override
  public Columns saveColumns(Columns columns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columns);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(columns.getColumnsId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.insert(INSERT_SQL, columns);
      return columns;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Columns> batchSaveColumns(Collection<Columns> columnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columnss);
    }
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.insert(INSERT_SQL, columnss);
      return columnss;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Columns updateColumns(Columns columns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columns);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(columns.getColumnsId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.update(UPDATE_SQL, columns);
      return columns;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Columns> batchUpdateColumns(Collection<Columns> columnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columnss);
    }
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.update(UPDATE_SQL, columnss);
      return columnss;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void removeColumns(Columns columns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columns);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(columns.getColumnsId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, columns.getColumnsId());
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
  public void batchRemoveColumns(Collection<Columns> columnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columnss);
    }
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.del(DEL_SQL_BY_PK, columnss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Long getCountColumns(ColumnsQuery columnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columnsQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
      }
      if (ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        return this.queryCount(countSql, Long.class);
      } else {
        return this.queryCount(countSql, columnsQuery, Long.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Columns getColumnsByPk(String columnsId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columnsId);
    }
    try {
      if (ToolUtil.isNullStr(columnsId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, columnsId);
        }
      }
      Collection<Columns> columnsSet = this.query(querySql, mapSqlParameterSource, Columns.class);
      return ToolUtil.isNotEmpty(columnsSet) ? columnsSet.iterator().next() : null;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Columns> getAllColumns() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, Columns.class);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Columns> queryColumns(ColumnsQuery columnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columnsQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      if (ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        return this.query(querySql, Columns.class);
      } else {
        return this.query(querySql, columnsQuery, Columns.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<Columns> pagingQueryColumns(Param param, ColumnsQuery columnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columnsQuery);
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
      Paging<Columns> paging = new Paging<>(param);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
      }
      Long count = null;
      if (ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        count = this.queryCount(countSql, Long.class);
      } else {
        count = this.queryCount(countSql, columnsQuery, Long.class);
      }
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        if (!ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
          querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
        }
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Columns> columnsSet = null;
        if (ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
          columnsSet = this.query(querySql, Columns.class);
        } else {
          columnsSet = this.query(querySql, columnsQuery, Columns.class);
        }
        if (ToolUtil.isNotEmpty(columnsSet)) {
          Set<String> inColumnsId = new LinkedHashSet<>();
          for (Columns columns : columnsSet) {
            inColumnsId.add(columns.getColumnsId());
          }
          ColumnsQuery rColumnsQuery = new ColumnsQuery();
          rColumnsQuery.setColumnsIdAndin(new ArrayList<>(inColumnsId));
          Collection<Columns> rColumnsSet = this.queryColumns(rColumnsQuery);
          if (ToolUtil.isNotEmpty(rColumnsSet)) {
            paging.setData(rColumnsSet);
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
  public ColumnsVO getColumnsVOByPk(String columnsId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columnsId);
    }
    try {
      if (ToolUtil.isNullStr(columnsId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, columnsId);
        }
      }
      Collection<ColumnsVO> columnsVOSet = this.query(querySql, mapSqlParameterSource, ColumnsVO.class);
      return ToolUtil.isNotEmpty(columnsVOSet) ? columnsVOSet.iterator().next() : null;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<ColumnsVO> getAllColumnsVO() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, ColumnsVO.class);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<ColumnsVO> queryColumnsVO(ColumnsQuery columnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columnsQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      if (ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        return this.query(querySql, ColumnsVO.class);
      } else {
        return this.query(querySql, columnsQuery, ColumnsVO.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<ColumnsVO> pagingQueryColumnsVO(Param param, ColumnsQuery columnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columnsQuery);
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
      Paging<ColumnsVO> paging = new Paging<>(param);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
      }
      Long count = null;
      if (ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        count = this.queryCount(countSql, Long.class);
      } else {
        count = this.queryCount(countSql, columnsQuery, Long.class);
      }
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        if (!ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
          querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
        }
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Columns> columnsSet = null;
        if (ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
          columnsSet = this.query(querySql, Columns.class);
        } else {
          columnsSet = this.query(querySql, columnsQuery, Columns.class);
        }
        if (ToolUtil.isNotEmpty(columnsSet)) {
          Set<String> inColumnsId = new LinkedHashSet<>();
          for (Columns columns : columnsSet) {
            inColumnsId.add(columns.getColumnsId());
          }
          ColumnsQuery rColumnsQuery = new ColumnsQuery();
          rColumnsQuery.setColumnsIdAndin(new ArrayList<>(inColumnsId));
          Collection<ColumnsVO> rColumnsVOSet = this.queryColumnsVO(rColumnsQuery);
          if (ToolUtil.isNotEmpty(rColumnsVOSet)) {
            paging.setData(rColumnsVOSet);
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
