package cn.moonnow.code.persistent.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.moonnow.code.entity.Sort;
import cn.moonnow.code.persistent.ISortPersistent;
import cn.moonnow.code.query.SortQuery;
import cn.moonnow.code.vo.SortVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.persistent.impl.ToolPersistent;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 排序 持久化实现
 */
@Log4j2
@Repository("cn.moonnow.code.SortPersistent")
public class SortPersistentImpl extends ToolPersistent implements ISortPersistent {

  public static final String LOG = "SortPersistent";

  public static final String TABLE_ALIAS = "sort";

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

  public static final String COLUMN_SORT_ID = "sortId";
  public static final String COLUMN_SORT_RULE = "sortRule";
  public static final String COLUMN_DT_ID = "dtId";
  public static final String COLUMN_COLUMNS_ID = "columnsId";

  public static final String VIRTUAL_COLUMN_COLUMN_NAME = "( SELECT columns.COLUMN_NAME FROM PRO_COLUMNS columns WHERE columns.COLUMNS_ID IS NOT NULL AND columns.COLUMNS_ID = sort.COLUMNS_ID ) AS COLUMN_NAME";
  public static final String VIRTUAL_COLUMN_COLUMN_NAME_ANNOTATION = "( SELECT columns.COLUMN_NAME_ANNOTATION FROM PRO_COLUMNS columns WHERE columns.COLUMNS_ID IS NOT NULL AND columns.COLUMNS_ID = sort.COLUMNS_ID ) AS COLUMN_NAME_ANNOTATION";

  static {
    PRIMARY_KEY.add(COLUMN_SORT_ID);

    COLUMNS.add(COLUMN_SORT_ID);
    COLUMNS.add(COLUMN_SORT_RULE);
    COLUMNS.add(COLUMN_DT_ID);
    COLUMNS.add(COLUMN_COLUMNS_ID);

    COLUMNS_PARAMETER.put(COLUMN_SORT_ID, "SORT_ID");
    COLUMNS_PARAMETER.put(COLUMN_SORT_RULE, "SORT_RULE");
    COLUMNS_PARAMETER.put(COLUMN_DT_ID, "DT_ID");
    COLUMNS_PARAMETER.put(COLUMN_COLUMNS_ID, "COLUMNS_ID");

    VIRTUAL_COLUMNS.add(VIRTUAL_COLUMN_COLUMN_NAME);
    VIRTUAL_COLUMNS.add(VIRTUAL_COLUMN_COLUMN_NAME_ANNOTATION);

    INSERT_SQL = getInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
    UPDATE_SQL = getUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY);
    DEL_SQL_BY_PK = getDelSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
    SELECT_VO_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, VIRTUAL_COLUMNS, PRIMARY_KEY, TABLE_ALIAS);
    COUNT_SQL = getCountSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
  }

  @Override
  public Sort saveSort(Sort sort) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sort);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(sort.getSortId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.insert(INSERT_SQL, sort);
      return sort;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Sort> batchSaveSort(Collection<Sort> sorts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sorts);
    }
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.insert(INSERT_SQL, sorts);
      return sorts;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Sort updateSort(Sort sort) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sort);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(sort.getSortId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.update(UPDATE_SQL, sort);
      return sort;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Sort> batchUpdateSort(Collection<Sort> sorts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sorts);
    }
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.update(UPDATE_SQL, sorts);
      return sorts;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void removeSort(Sort sort) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sort);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(sort.getSortId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, sort.getSortId());
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
  public void batchRemoveSort(Collection<Sort> sorts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sorts);
    }
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.del(DEL_SQL_BY_PK, sorts);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Long getCountSort(SortQuery sortQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sortQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, sortQuery));
      }
      if (ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        return this.queryCount(countSql, Long.class);
      } else {
        return this.queryCount(countSql, sortQuery, Long.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Sort getSortByPk(String sortId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sortId);
    }
    try {
      if (ToolUtil.isNullStr(sortId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, sortId);
        }
      }
      Collection<Sort> sortSet = this.query(querySql, mapSqlParameterSource, Sort.class);
      return ToolUtil.isNotEmpty(sortSet) ? sortSet.iterator().next() : null;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Sort> getAllSort() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, Sort.class);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Sort> querySort(SortQuery sortQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sortQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, sortQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      if (ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        return this.query(querySql, Sort.class);
      } else {
        return this.query(querySql, sortQuery, Sort.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<Sort> pagingQuerySort(Param param, SortQuery sortQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sortQuery);
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
      Paging<Sort> paging = new Paging<>(param);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, sortQuery));
      }
      Long count = null;
      if (ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        count = this.queryCount(countSql, Long.class);
      } else {
        count = this.queryCount(countSql, sortQuery, Long.class);
      }
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        if (!ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
          querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, sortQuery));
        }
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Sort> sortSet = null;
        if (ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
          sortSet = this.query(querySql, Sort.class);
        } else {
          sortSet = this.query(querySql, sortQuery, Sort.class);
        }
        if (ToolUtil.isNotEmpty(sortSet)) {
          Set<String> inSortId = new LinkedHashSet<>();
          for (Sort sort : sortSet) {
            inSortId.add(sort.getSortId());
          }
          SortQuery rSortQuery = new SortQuery();
          rSortQuery.setSortIdAndin(new ArrayList<>(inSortId));
          Collection<Sort> rSortSet = this.querySort(rSortQuery);
          if (ToolUtil.isNotEmpty(rSortSet)) {
            paging.setData(rSortSet);
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
  public SortVO getSortVOByPk(String sortId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sortId);
    }
    try {
      if (ToolUtil.isNullStr(sortId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, sortId);
        }
      }
      Collection<SortVO> sortVOSet = this.query(querySql, mapSqlParameterSource, SortVO.class);
      return ToolUtil.isNotEmpty(sortVOSet) ? sortVOSet.iterator().next() : null;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<SortVO> getAllSortVO() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, SortVO.class);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<SortVO> querySortVO(SortQuery sortQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sortQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, sortQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      if (ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        return this.query(querySql, SortVO.class);
      } else {
        return this.query(querySql, sortQuery, SortVO.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<SortVO> pagingQuerySortVO(Param param, SortQuery sortQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sortQuery);
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
      Paging<SortVO> paging = new Paging<>(param);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, sortQuery));
      }
      Long count = null;
      if (ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        count = this.queryCount(countSql, Long.class);
      } else {
        count = this.queryCount(countSql, sortQuery, Long.class);
      }
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        if (!ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
          querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, sortQuery));
        }
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Sort> sortSet = null;
        if (ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
          sortSet = this.query(querySql, Sort.class);
        } else {
          sortSet = this.query(querySql, sortQuery, Sort.class);
        }
        if (ToolUtil.isNotEmpty(sortSet)) {
          Set<String> inSortId = new LinkedHashSet<>();
          for (Sort sort : sortSet) {
            inSortId.add(sort.getSortId());
          }
          SortQuery rSortQuery = new SortQuery();
          rSortQuery.setSortIdAndin(new ArrayList<>(inSortId));
          Collection<SortVO> rSortVOSet = this.querySortVO(rSortQuery);
          if (ToolUtil.isNotEmpty(rSortVOSet)) {
            paging.setData(rSortVOSet);
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
