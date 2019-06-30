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
import cn.moonnow.sign.entity.Grp;
import cn.moonnow.sign.persistent.IGrpPersistent;
import cn.moonnow.sign.query.GrpQuery;
import cn.moonnow.sign.service.IGrpService;
import cn.moonnow.sign.vo.GrpVO;
import lombok.extern.log4j.Log4j2;

/**
 * 组 服务实现
 */
@Log4j2
@Service("cn.moonnow.sign.GrpService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class GrpServiceImpl implements IGrpService {

  public static final String LOG = "GrpService";

  @Resource(name = "cn.moonnow.sign.GrpPersistent")
  private IGrpPersistent grpPersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Grp saveGrp(Grp grp) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + grp);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(grp)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      grp.setGrpId(ToolUtil.getUUID());
      return grpPersistent.saveGrp(grp);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Grp> batchSaveGrp(Collection<Grp> grps) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + grps);
    }
    try {
      if (ToolUtil.isEmpty(grps)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Grp grp : grps) {
        if (ToolUtil.isNullEntityAllFieldValue(grp)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        grp.setGrpId(ToolUtil.getUUID());
      }
      return grpPersistent.batchSaveGrp(grps);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Grp updateGrp(Grp grp) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + grp);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(grp)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(grp.getGrpId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Grp oldGrp = grpPersistent.getGrpByPk(grp.getGrpId());
      if (ToolUtil.isNullEntityAllFieldValue(oldGrp)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return grpPersistent.updateGrp(grp);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Grp> batchUpdateGrp(Collection<Grp> grps) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + grps);
    }
    try {
      if (ToolUtil.isEmpty(grps)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Grp grp : grps) {
        if (ToolUtil.isNullEntityAllFieldValue(grp)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(grp.getGrpId())) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        Grp oldGrp = grpPersistent.getGrpByPk(grp.getGrpId());
        if (ToolUtil.isNullEntityAllFieldValue(oldGrp)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
      }
      return grpPersistent.batchUpdateGrp(grps);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void removeGrp(Grp grp) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + grp);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(grp)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Grp> grpSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(grp.getGrpId())) {
        GrpQuery grpQuery = new GrpQuery();
        BeanUtils.copyProperties(grp, grpQuery);
        grpSet.addAll(grpPersistent.queryGrp(grpQuery));
      } else {
        Grp oldGrp = grpPersistent.getGrpByPk(grp.getGrpId());
        if (ToolUtil.isNullEntityAllFieldValue(oldGrp)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        grpSet.add(oldGrp);
      }
      if (ToolUtil.isNotEmpty(grpSet)) {
        grpPersistent.batchRemoveGrp(grpSet);
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
  public void batchRemoveGrp(Collection<Grp> grps) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + grps);
    }
    try {
      if (ToolUtil.isEmpty(grps)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Grp> grpSet = new LinkedHashSet<>();
      for (Grp grp : grps) {
        if (ToolUtil.isNullEntityAllFieldValue(grp)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(grp.getGrpId())) {
          GrpQuery grpQuery = new GrpQuery();
          BeanUtils.copyProperties(grp, grpQuery);
          grpSet.addAll(grpPersistent.queryGrp(grpQuery));
        } else {
          Grp oldGrp = grpPersistent.getGrpByPk(grp.getGrpId());
          if (ToolUtil.isNullEntityAllFieldValue(oldGrp)) {
            throw new ToolException(ToolException.E_PARAM_ERR);
          }
          grpSet.add(oldGrp);
        }
      }
      if (ToolUtil.isNotEmpty(grpSet)) {
        grpPersistent.batchRemoveGrp(grpSet);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Long getCountGrp(GrpQuery grpQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + grpQuery);
    }
    try {
      return grpPersistent.getCountGrp(grpQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Grp getGrpByPk(String grpId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + grpId);
    }
    try {
      if (ToolUtil.isNullStr(grpId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return grpPersistent.getGrpByPk(grpId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Grp> getAllGrp() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return grpPersistent.getAllGrp();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Grp> queryGrp(GrpQuery grpQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + grpQuery);
    }
    try {
      return grpPersistent.queryGrp(grpQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<Grp> pagingQueryGrp(Param param, GrpQuery grpQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grpQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return grpPersistent.pagingQueryGrp(param, grpQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public GrpVO getGrpVOByPk(String grpId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + grpId);
    }
    try {
      if (ToolUtil.isNullStr(grpId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return grpPersistent.getGrpVOByPk(grpId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<GrpVO> getAllGrpVO() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return grpPersistent.getAllGrpVO();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<GrpVO> queryGrpVO(GrpQuery grpQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + grpQuery);
    }
    try {
      return grpPersistent.queryGrpVO(grpQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<GrpVO> pagingQueryGrpVO(Param param, GrpQuery grpQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grpQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return grpPersistent.pagingQueryGrpVO(param, grpQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
