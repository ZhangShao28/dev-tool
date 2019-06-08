package cn.moonnow.code.service.impl;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.moonnow.code.entity.Pk;
import cn.moonnow.code.persistent.IPkPersistent;
import cn.moonnow.code.query.PkQuery;
import cn.moonnow.code.service.IPkService;
import cn.moonnow.code.vo.PkVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 主键 服务实现
 */
@Log4j2
@Service("cn.moonnow.code.PkService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class PkServiceImpl implements IPkService {

  public static final String LOG = "PkService";

  @Resource(name = "cn.moonnow.code.PkPersistent")
  private IPkPersistent pkPersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Pk savePk(Pk pk) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + pk);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      pk.setPkId(ToolUtil.getUUID());
      return pkPersistent.savePk(pk);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Pk> batchSavePk(Collection<Pk> pks) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + pks);
    }
    try {
      if (ToolUtil.isEmpty(pks)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Pk pk : pks) {
        if (ToolUtil.isNullEntityAllFieldValue(pk)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        pk.setPkId(ToolUtil.getUUID());
      }
      return pkPersistent.batchSavePk(pks);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Pk updatePk(Pk pk) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + pk);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(pk.getPkId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Pk oldPk = pkPersistent.getPkByPk(pk.getPkId());
      if (ToolUtil.isNullEntityAllFieldValue(oldPk)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return pkPersistent.updatePk(pk);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Pk> batchUpdatePk(Collection<Pk> pks) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + pks);
    }
    try {
      if (ToolUtil.isEmpty(pks)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Pk pk : pks) {
        if (ToolUtil.isNullEntityAllFieldValue(pk)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(pk.getPkId())) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        Pk oldPk = pkPersistent.getPkByPk(pk.getPkId());
        if (ToolUtil.isNullEntityAllFieldValue(oldPk)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
      }
      return pkPersistent.batchUpdatePk(pks);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void removePk(Pk pk) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + pk);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Pk> pkSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(pk.getPkId())) {
        PkQuery pkQuery = new PkQuery();
        BeanUtils.copyProperties(pk, pkQuery);
        pkSet.addAll(pkPersistent.queryPk(pkQuery));
      } else {
        Pk oldPk = pkPersistent.getPkByPk(pk.getPkId());
        if (ToolUtil.isNullEntityAllFieldValue(oldPk)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        pkSet.add(oldPk);
      }
      if (ToolUtil.isNotEmpty(pkSet)) {
        pkPersistent.batchRemovePk(pkSet);
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
  public void batchRemovePk(Collection<Pk> pks) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + pks);
    }
    try {
      if (ToolUtil.isEmpty(pks)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Pk> pkSet = new LinkedHashSet<>();
      for (Pk pk : pks) {
        if (ToolUtil.isNullEntityAllFieldValue(pk)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(pk.getPkId())) {
          PkQuery pkQuery = new PkQuery();
          BeanUtils.copyProperties(pk, pkQuery);
          pkSet.addAll(pkPersistent.queryPk(pkQuery));
        } else {
          Pk oldPk = pkPersistent.getPkByPk(pk.getPkId());
          if (ToolUtil.isNullEntityAllFieldValue(oldPk)) {
            throw new ToolException(ToolException.E_PARAM_ERR);
          }
          pkSet.add(oldPk);
        }
      }
      if (ToolUtil.isNotEmpty(pkSet)) {
        pkPersistent.batchRemovePk(pkSet);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Long getCountPk(PkQuery pkQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + pkQuery);
    }
    try {
      return pkPersistent.getCountPk(pkQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Pk getPkByPk(String pkId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + pkId);
    }
    try {
      if (ToolUtil.isNullStr(pkId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return pkPersistent.getPkByPk(pkId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Pk> getAllPk() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return pkPersistent.getAllPk();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Pk> queryPk(PkQuery pkQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + pkQuery);
    }
    try {
      return pkPersistent.queryPk(pkQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<Pk> pagingQueryPk(Param param, PkQuery pkQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pkQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return pkPersistent.pagingQueryPk(param, pkQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public PkVO getPkVOByPk(String pkId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + pkId);
    }
    try {
      if (ToolUtil.isNullStr(pkId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return pkPersistent.getPkVOByPk(pkId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<PkVO> getAllPkVO() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return pkPersistent.getAllPkVO();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<PkVO> queryPkVO(PkQuery pkQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + pkQuery);
    }
    try {
      return pkPersistent.queryPkVO(pkQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<PkVO> pagingQueryPkVO(Param param, PkQuery pkQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pkQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return pkPersistent.pagingQueryPkVO(param, pkQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
