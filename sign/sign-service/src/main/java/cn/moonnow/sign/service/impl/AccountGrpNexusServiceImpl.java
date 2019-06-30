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
import cn.moonnow.sign.entity.AccountGrpNexus;
import cn.moonnow.sign.persistent.IAccountGrpNexusPersistent;
import cn.moonnow.sign.query.AccountGrpNexusQuery;
import cn.moonnow.sign.service.IAccountGrpNexusService;
import cn.moonnow.sign.vo.AccountGrpNexusVO;
import lombok.extern.log4j.Log4j2;

/**
 * 账号分组 服务实现
 */
@Log4j2
@Service("cn.moonnow.sign.AccountGrpNexusService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class AccountGrpNexusServiceImpl implements IAccountGrpNexusService {

  public static final String LOG = "AccountGrpNexusService";

  @Resource(name = "cn.moonnow.sign.AccountGrpNexusPersistent")
  private IAccountGrpNexusPersistent accountGrpNexusPersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public AccountGrpNexus saveAccountGrpNexus(AccountGrpNexus accountGrpNexus) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexus);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      accountGrpNexus.setNexusId(ToolUtil.getUUID());
      return accountGrpNexusPersistent.saveAccountGrpNexus(accountGrpNexus);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<AccountGrpNexus> batchSaveAccountGrpNexus(Collection<AccountGrpNexus> accountGrpNexuss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexuss);
    }
    try {
      if (ToolUtil.isEmpty(accountGrpNexuss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (AccountGrpNexus accountGrpNexus : accountGrpNexuss) {
        if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        accountGrpNexus.setNexusId(ToolUtil.getUUID());
      }
      return accountGrpNexusPersistent.batchSaveAccountGrpNexus(accountGrpNexuss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
      AccountGrpNexus oldAccountGrpNexus = accountGrpNexusPersistent.getAccountGrpNexusByPk(accountGrpNexus.getNexusId());
      if (ToolUtil.isNullEntityAllFieldValue(oldAccountGrpNexus)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return accountGrpNexusPersistent.updateAccountGrpNexus(accountGrpNexus);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<AccountGrpNexus> batchUpdateAccountGrpNexus(Collection<AccountGrpNexus> accountGrpNexuss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexuss);
    }
    try {
      if (ToolUtil.isEmpty(accountGrpNexuss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (AccountGrpNexus accountGrpNexus : accountGrpNexuss) {
        if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(accountGrpNexus.getNexusId())) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        AccountGrpNexus oldAccountGrpNexus = accountGrpNexusPersistent.getAccountGrpNexusByPk(accountGrpNexus.getNexusId());
        if (ToolUtil.isNullEntityAllFieldValue(oldAccountGrpNexus)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
      }
      return accountGrpNexusPersistent.batchUpdateAccountGrpNexus(accountGrpNexuss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void removeAccountGrpNexus(AccountGrpNexus accountGrpNexus) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexus);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<AccountGrpNexus> accountGrpNexusSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(accountGrpNexus.getNexusId())) {
        AccountGrpNexusQuery accountGrpNexusQuery = new AccountGrpNexusQuery();
        BeanUtils.copyProperties(accountGrpNexus, accountGrpNexusQuery);
        accountGrpNexusSet.addAll(accountGrpNexusPersistent.queryAccountGrpNexus(accountGrpNexusQuery));
      } else {
        AccountGrpNexus oldAccountGrpNexus = accountGrpNexusPersistent.getAccountGrpNexusByPk(accountGrpNexus.getNexusId());
        if (ToolUtil.isNullEntityAllFieldValue(oldAccountGrpNexus)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        accountGrpNexusSet.add(oldAccountGrpNexus);
      }
      if (ToolUtil.isNotEmpty(accountGrpNexusSet)) {
        accountGrpNexusPersistent.batchRemoveAccountGrpNexus(accountGrpNexusSet);
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
  public void batchRemoveAccountGrpNexus(Collection<AccountGrpNexus> accountGrpNexuss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + accountGrpNexuss);
    }
    try {
      if (ToolUtil.isEmpty(accountGrpNexuss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<AccountGrpNexus> accountGrpNexusSet = new LinkedHashSet<>();
      for (AccountGrpNexus accountGrpNexus : accountGrpNexuss) {
        if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(accountGrpNexus.getNexusId())) {
          AccountGrpNexusQuery accountGrpNexusQuery = new AccountGrpNexusQuery();
          BeanUtils.copyProperties(accountGrpNexus, accountGrpNexusQuery);
          accountGrpNexusSet.addAll(accountGrpNexusPersistent.queryAccountGrpNexus(accountGrpNexusQuery));
        } else {
          AccountGrpNexus oldAccountGrpNexus = accountGrpNexusPersistent.getAccountGrpNexusByPk(accountGrpNexus.getNexusId());
          if (ToolUtil.isNullEntityAllFieldValue(oldAccountGrpNexus)) {
            throw new ToolException(ToolException.E_PARAM_ERR);
          }
          accountGrpNexusSet.add(oldAccountGrpNexus);
        }
      }
      if (ToolUtil.isNotEmpty(accountGrpNexusSet)) {
        accountGrpNexusPersistent.batchRemoveAccountGrpNexus(accountGrpNexusSet);
      }
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
      return accountGrpNexusPersistent.getCountAccountGrpNexus(accountGrpNexusQuery);
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
      return accountGrpNexusPersistent.getAccountGrpNexusByPk(nexusId);
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
      return accountGrpNexusPersistent.getAllAccountGrpNexus();
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
      return accountGrpNexusPersistent.queryAccountGrpNexus(accountGrpNexusQuery);
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
      return accountGrpNexusPersistent.pagingQueryAccountGrpNexus(param, accountGrpNexusQuery);
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
      return accountGrpNexusPersistent.getAccountGrpNexusVOByPk(nexusId);
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
      return accountGrpNexusPersistent.getAllAccountGrpNexusVO();
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
      return accountGrpNexusPersistent.queryAccountGrpNexusVO(accountGrpNexusQuery);
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
      return accountGrpNexusPersistent.pagingQueryAccountGrpNexusVO(param, accountGrpNexusQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
