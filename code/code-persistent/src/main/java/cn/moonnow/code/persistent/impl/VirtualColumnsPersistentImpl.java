package cn.moonnow.code.persistent.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.moonnow.code.entity.VirtualColumns;
import cn.moonnow.code.persistent.IVirtualColumnsPersistent;
import cn.moonnow.code.query.VirtualColumnsQuery;
import cn.moonnow.code.vo.VirtualColumnsVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.persistent.impl.ToolPersistent;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 虚拟列 持久化实现
 */
@Log4j2
@Repository("cn.moonnow.code.VirtualColumnsPersistent")
public class VirtualColumnsPersistentImpl extends ToolPersistent implements IVirtualColumnsPersistent {

  public static final String LOG = "VirtualColumnsPersistent";

  public static final String TABLE_ALIAS = "virtualColumns";

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

  public static final String COLUMN_VIRTUAL_COLUMNS_ID = "virtualColumnsId";
  public static final String COLUMN_SOURCE_DT_ID = "sourceDtId";
  public static final String COLUMN_TARGET_DT_ID = "targetDtId";
  public static final String COLUMN_SOURCE_COLUMNS_ID = "sourceColumnsId";
  public static final String COLUMN_TARGET_COLUMNS_ID = "targetColumnsId";
  public static final String COLUMN_TARGET_DISPLAY_COLUMNS_ID = "targetDisplayColumnsId";
  public static final String COLUMN_DISPLAY_COLUMNS_ALIAS = "displayColumnsAlias";
  public static final String COLUMN_VIRTUAL_COLUMNS_SQL = "virtualColumnsSql";
  public static final String COLUMN_WEIGHT_ORDER = "weightOrder";

  public static final String VIRTUAL_COLUMN_DT_NAME = "( SELECT dt.DT_NAME FROM PRO_DT dt WHERE dt.DT_ID IS NOT NULL AND dt.DT_ID = virtualColumns.TARGET_DT_ID ) AS DT_NAME";
  public static final String VIRTUAL_COLUMN_COLUMN_NAME = "( SELECT columns.COLUMN_NAME FROM PRO_COLUMNS columns WHERE columns.COLUMNS_ID IS NOT NULL AND columns.COLUMNS_ID = virtualColumns.TARGET_DISPLAY_COLUMNS_ID ) AS COLUMN_NAME";

  static {
    PRIMARY_KEY.add(COLUMN_VIRTUAL_COLUMNS_ID);

    COLUMNS.add(COLUMN_VIRTUAL_COLUMNS_ID);
    COLUMNS.add(COLUMN_SOURCE_DT_ID);
    COLUMNS.add(COLUMN_TARGET_DT_ID);
    COLUMNS.add(COLUMN_SOURCE_COLUMNS_ID);
    COLUMNS.add(COLUMN_TARGET_COLUMNS_ID);
    COLUMNS.add(COLUMN_TARGET_DISPLAY_COLUMNS_ID);
    COLUMNS.add(COLUMN_DISPLAY_COLUMNS_ALIAS);
    COLUMNS.add(COLUMN_VIRTUAL_COLUMNS_SQL);
    COLUMNS.add(COLUMN_WEIGHT_ORDER);

    COLUMNS_PARAMETER.put(COLUMN_VIRTUAL_COLUMNS_ID, "VIRTUAL_COLUMNS_ID");
    COLUMNS_PARAMETER.put(COLUMN_SOURCE_DT_ID, "SOURCE_DT_ID");
    COLUMNS_PARAMETER.put(COLUMN_TARGET_DT_ID, "TARGET_DT_ID");
    COLUMNS_PARAMETER.put(COLUMN_SOURCE_COLUMNS_ID, "SOURCE_COLUMNS_ID");
    COLUMNS_PARAMETER.put(COLUMN_TARGET_COLUMNS_ID, "TARGET_COLUMNS_ID");
    COLUMNS_PARAMETER.put(COLUMN_TARGET_DISPLAY_COLUMNS_ID, "TARGET_DISPLAY_COLUMNS_ID");
    COLUMNS_PARAMETER.put(COLUMN_DISPLAY_COLUMNS_ALIAS, "DISPLAY_COLUMNS_ALIAS");
    COLUMNS_PARAMETER.put(COLUMN_VIRTUAL_COLUMNS_SQL, "VIRTUAL_COLUMNS_SQL");
    COLUMNS_PARAMETER.put(COLUMN_WEIGHT_ORDER, "WEIGHT_ORDER");

    VIRTUAL_COLUMNS.add(VIRTUAL_COLUMN_DT_NAME);
    VIRTUAL_COLUMNS.add(VIRTUAL_COLUMN_COLUMN_NAME);

    SORT.put(COLUMN_WEIGHT_ORDER, ASC);

    INSERT_SQL = getInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
    UPDATE_SQL = getUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY);
    DEL_SQL_BY_PK = getDelSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
    SELECT_VO_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, VIRTUAL_COLUMNS, PRIMARY_KEY, TABLE_ALIAS);
    COUNT_SQL = getCountSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
  }

  @Override
  public VirtualColumns saveVirtualColumns(VirtualColumns virtualColumns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumns);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(virtualColumns.getVirtualColumnsId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.insert(INSERT_SQL, virtualColumns);
      return virtualColumns;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<VirtualColumns> batchSaveVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumnss);
    }
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.insert(INSERT_SQL, virtualColumnss);
      return virtualColumnss;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public VirtualColumns updateVirtualColumns(VirtualColumns virtualColumns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumns);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(virtualColumns.getVirtualColumnsId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.update(UPDATE_SQL, virtualColumns);
      return virtualColumns;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<VirtualColumns> batchUpdateVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumnss);
    }
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.update(UPDATE_SQL, virtualColumnss);
      return virtualColumnss;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void removeVirtualColumns(VirtualColumns virtualColumns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumns);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(virtualColumns.getVirtualColumnsId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, virtualColumns.getVirtualColumnsId());
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
  public void batchRemoveVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumnss);
    }
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.del(DEL_SQL_BY_PK, virtualColumnss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Long getCountVirtualColumns(VirtualColumnsQuery virtualColumnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumnsQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
      }
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        return this.queryCount(countSql, Long.class);
      } else {
        return this.queryCount(countSql, virtualColumnsQuery, Long.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public VirtualColumns getVirtualColumnsByPk(String virtualColumnsId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumnsId);
    }
    try {
      if (ToolUtil.isNullStr(virtualColumnsId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, virtualColumnsId);
        }
      }
      Collection<VirtualColumns> virtualColumnsSet = this.query(querySql, mapSqlParameterSource, VirtualColumns.class);
      return ToolUtil.isNotEmpty(virtualColumnsSet) ? virtualColumnsSet.iterator().next() : null;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<VirtualColumns> getAllVirtualColumns() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, VirtualColumns.class);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<VirtualColumns> queryVirtualColumns(VirtualColumnsQuery virtualColumnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumnsQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        return this.query(querySql, VirtualColumns.class);
      } else {
        return this.query(querySql, virtualColumnsQuery, VirtualColumns.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<VirtualColumns> pagingQueryVirtualColumns(Param param, VirtualColumnsQuery virtualColumnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumnsQuery);
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
      Paging<VirtualColumns> paging = new Paging<>(param);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
      }
      Long count = null;
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        count = this.queryCount(countSql, Long.class);
      } else {
        count = this.queryCount(countSql, virtualColumnsQuery, Long.class);
      }
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        if (!ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
          querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
        }
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<VirtualColumns> virtualColumnsSet = null;
        if (ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
          virtualColumnsSet = this.query(querySql, VirtualColumns.class);
        } else {
          virtualColumnsSet = this.query(querySql, virtualColumnsQuery, VirtualColumns.class);
        }
        if (ToolUtil.isNotEmpty(virtualColumnsSet)) {
          Set<String> inVirtualColumnsId = new LinkedHashSet<>();
          for (VirtualColumns virtualColumns : virtualColumnsSet) {
            inVirtualColumnsId.add(virtualColumns.getVirtualColumnsId());
          }
          VirtualColumnsQuery rVirtualColumnsQuery = new VirtualColumnsQuery();
          rVirtualColumnsQuery.setVirtualColumnsIdAndin(new ArrayList<>(inVirtualColumnsId));
          Collection<VirtualColumns> rVirtualColumnsSet = this.queryVirtualColumns(rVirtualColumnsQuery);
          if (ToolUtil.isNotEmpty(rVirtualColumnsSet)) {
            paging.setData(rVirtualColumnsSet);
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
  public VirtualColumnsVO getVirtualColumnsVOByPk(String virtualColumnsId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumnsId);
    }
    try {
      if (ToolUtil.isNullStr(virtualColumnsId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, virtualColumnsId);
        }
      }
      Collection<VirtualColumnsVO> virtualColumnsVOSet = this.query(querySql, mapSqlParameterSource, VirtualColumnsVO.class);
      return ToolUtil.isNotEmpty(virtualColumnsVOSet) ? virtualColumnsVOSet.iterator().next() : null;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<VirtualColumnsVO> getAllVirtualColumnsVO() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, VirtualColumnsVO.class);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<VirtualColumnsVO> queryVirtualColumnsVO(VirtualColumnsQuery virtualColumnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumnsQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        return this.query(querySql, VirtualColumnsVO.class);
      } else {
        return this.query(querySql, virtualColumnsQuery, VirtualColumnsVO.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<VirtualColumnsVO> pagingQueryVirtualColumnsVO(Param param, VirtualColumnsQuery virtualColumnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumnsQuery);
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
      Paging<VirtualColumnsVO> paging = new Paging<>(param);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
      }
      Long count = null;
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        count = this.queryCount(countSql, Long.class);
      } else {
        count = this.queryCount(countSql, virtualColumnsQuery, Long.class);
      }
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        if (!ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
          querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
        }
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<VirtualColumns> virtualColumnsSet = null;
        if (ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
          virtualColumnsSet = this.query(querySql, VirtualColumns.class);
        } else {
          virtualColumnsSet = this.query(querySql, virtualColumnsQuery, VirtualColumns.class);
        }
        if (ToolUtil.isNotEmpty(virtualColumnsSet)) {
          Set<String> inVirtualColumnsId = new LinkedHashSet<>();
          for (VirtualColumns virtualColumns : virtualColumnsSet) {
            inVirtualColumnsId.add(virtualColumns.getVirtualColumnsId());
          }
          VirtualColumnsQuery rVirtualColumnsQuery = new VirtualColumnsQuery();
          rVirtualColumnsQuery.setVirtualColumnsIdAndin(new ArrayList<>(inVirtualColumnsId));
          Collection<VirtualColumnsVO> rVirtualColumnsVOSet = this.queryVirtualColumnsVO(rVirtualColumnsQuery);
          if (ToolUtil.isNotEmpty(rVirtualColumnsVOSet)) {
            paging.setData(rVirtualColumnsVOSet);
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
