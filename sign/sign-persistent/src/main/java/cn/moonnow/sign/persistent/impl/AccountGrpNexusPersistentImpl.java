package cn.moonnow.sign.persistent.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.persistent.impl.ToolPersistent;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import cn.moonnow.sign.entity.AccountGrpNexus;
import cn.moonnow.sign.persistent.IAccountGrpNexusPersistent;
import cn.moonnow.sign.query.AccountGrpNexusQuery;
import cn.moonnow.sign.vo.AccountGrpNexusVO;
import lombok.extern.log4j.Log4j2;

/**
 * 账号分组 持久化实现
 */
@Log4j2
@Repository("cn.moonnow.sign.AccountGrpNexusPersistent")
public class AccountGrpNexusPersistentImpl extends ToolPersistent implements IAccountGrpNexusPersistent {

  public static final String LOG = "AccountGrpNexusPersistent";

  public static final String TABLE_ALIAS = "accountGrpNexus";

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

  @PostConstruct
  private void init() {
    super.init(TABLE_NAME, PRIMARY_KEY, COLUMNS, COLUMNS_PARAMETER, VIRTUAL_COLUMNS, SORT);
    INSERT_SQL = getInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
    UPDATE_SQL = getUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY);
    DEL_SQL_BY_PK = getDelSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
    SELECT_VO_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, VIRTUAL_COLUMNS, PRIMARY_KEY, TABLE_ALIAS);
    COUNT_SQL = getCountSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
  }

  @Override
  public AccountGrpNexus saveAccountGrpNexus(AccountGrpNexus accountGrpNexus) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexus);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(accountGrpNexus.getNexusId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.insert(INSERT_SQL, accountGrpNexus);
      return accountGrpNexus;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<AccountGrpNexus> batchSaveAccountGrpNexus(Collection<AccountGrpNexus> accountGrpNexuss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexuss);
    }
    try {
      if (ToolUtil.isEmpty(accountGrpNexuss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.insert(INSERT_SQL, accountGrpNexuss);
      return accountGrpNexuss;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public AccountGrpNexus updateAccountGrpNexus(AccountGrpNexus accountGrpNexus) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexus);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(accountGrpNexus.getNexusId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.update(UPDATE_SQL, accountGrpNexus);
      return accountGrpNexus;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<AccountGrpNexus> batchUpdateAccountGrpNexus(Collection<AccountGrpNexus> accountGrpNexuss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexuss);
    }
    try {
      if (ToolUtil.isEmpty(accountGrpNexuss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.update(UPDATE_SQL, accountGrpNexuss);
      return accountGrpNexuss;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void removeAccountGrpNexus(AccountGrpNexus accountGrpNexus) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexus);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(accountGrpNexus.getNexusId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, accountGrpNexus.getNexusId());
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
  public void batchRemoveAccountGrpNexus(Collection<AccountGrpNexus> accountGrpNexuss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexuss);
    }
    try {
      if (ToolUtil.isEmpty(accountGrpNexuss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      this.del(DEL_SQL_BY_PK, accountGrpNexuss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Long getCountAccountGrpNexus(AccountGrpNexusQuery accountGrpNexusQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexusQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, accountGrpNexusQuery));
      }
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        return this.queryCount(countSql, Long.class);
      } else {
        return this.queryCount(countSql, accountGrpNexusQuery, Long.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public AccountGrpNexus getAccountGrpNexusByPk(String nexusId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + nexusId);
    }
    try {
      if (ToolUtil.isNullStr(nexusId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, nexusId);
        }
      }
      Collection<AccountGrpNexus> accountGrpNexusSet = this.query(querySql, mapSqlParameterSource, AccountGrpNexus.class);
      return ToolUtil.isNotEmpty(accountGrpNexusSet) ? accountGrpNexusSet.iterator().next() : null;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<AccountGrpNexus> getAllAccountGrpNexus() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, AccountGrpNexus.class);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<AccountGrpNexus> queryAccountGrpNexus(AccountGrpNexusQuery accountGrpNexusQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexusQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, accountGrpNexusQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        return this.query(querySql, AccountGrpNexus.class);
      } else {
        return this.query(querySql, accountGrpNexusQuery, AccountGrpNexus.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<AccountGrpNexus> pagingQueryAccountGrpNexus(Param param, AccountGrpNexusQuery accountGrpNexusQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexusQuery);
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
      Paging<AccountGrpNexus> paging = new Paging<>(param);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, accountGrpNexusQuery));
      }
      Long count = null;
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        count = this.queryCount(countSql, Long.class);
      } else {
        count = this.queryCount(countSql, accountGrpNexusQuery, Long.class);
      }
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        if (!ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
          querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, accountGrpNexusQuery));
        }
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<AccountGrpNexus> accountGrpNexusSet = null;
        if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
          accountGrpNexusSet = this.query(querySql, AccountGrpNexus.class);
        } else {
          accountGrpNexusSet = this.query(querySql, accountGrpNexusQuery, AccountGrpNexus.class);
        }
        if (ToolUtil.isNotEmpty(accountGrpNexusSet)) {
          Set<String> inNexusId = new LinkedHashSet<>();
          for (AccountGrpNexus accountGrpNexus : accountGrpNexusSet) {
            inNexusId.add(accountGrpNexus.getNexusId());
          }
          AccountGrpNexusQuery rAccountGrpNexusQuery = new AccountGrpNexusQuery();
          rAccountGrpNexusQuery.setNexusIdAndin(new ArrayList<>(inNexusId));
          Collection<AccountGrpNexus> rAccountGrpNexusSet = this.queryAccountGrpNexus(rAccountGrpNexusQuery);
          if (ToolUtil.isNotEmpty(rAccountGrpNexusSet)) {
            paging.setData(rAccountGrpNexusSet);
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
  public AccountGrpNexusVO getAccountGrpNexusVOByPk(String nexusId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + nexusId);
    }
    try {
      if (ToolUtil.isNullStr(nexusId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, nexusId);
        }
      }
      Collection<AccountGrpNexusVO> accountGrpNexusVOSet = this.query(querySql, mapSqlParameterSource, AccountGrpNexusVO.class);
      return ToolUtil.isNotEmpty(accountGrpNexusVOSet) ? accountGrpNexusVOSet.iterator().next() : null;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<AccountGrpNexusVO> getAllAccountGrpNexusVO() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, AccountGrpNexusVO.class);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<AccountGrpNexusVO> queryAccountGrpNexusVO(AccountGrpNexusQuery accountGrpNexusQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexusQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, accountGrpNexusQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        return this.query(querySql, AccountGrpNexusVO.class);
      } else {
        return this.query(querySql, accountGrpNexusQuery, AccountGrpNexusVO.class);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<AccountGrpNexusVO> pagingQueryAccountGrpNexusVO(Param param, AccountGrpNexusQuery accountGrpNexusQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexusQuery);
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
      Paging<AccountGrpNexusVO> paging = new Paging<>(param);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, accountGrpNexusQuery));
      }
      Long count = null;
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        count = this.queryCount(countSql, Long.class);
      } else {
        count = this.queryCount(countSql, accountGrpNexusQuery, Long.class);
      }
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        if (!ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
          querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, accountGrpNexusQuery));
        }
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<AccountGrpNexus> accountGrpNexusSet = null;
        if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
          accountGrpNexusSet = this.query(querySql, AccountGrpNexus.class);
        } else {
          accountGrpNexusSet = this.query(querySql, accountGrpNexusQuery, AccountGrpNexus.class);
        }
        if (ToolUtil.isNotEmpty(accountGrpNexusSet)) {
          Set<String> inNexusId = new LinkedHashSet<>();
          for (AccountGrpNexus accountGrpNexus : accountGrpNexusSet) {
            inNexusId.add(accountGrpNexus.getNexusId());
          }
          AccountGrpNexusQuery rAccountGrpNexusQuery = new AccountGrpNexusQuery();
          rAccountGrpNexusQuery.setNexusIdAndin(new ArrayList<>(inNexusId));
          Collection<AccountGrpNexusVO> rAccountGrpNexusVOSet = this.queryAccountGrpNexusVO(rAccountGrpNexusQuery);
          if (ToolUtil.isNotEmpty(rAccountGrpNexusVOSet)) {
            paging.setData(rAccountGrpNexusVOSet);
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
