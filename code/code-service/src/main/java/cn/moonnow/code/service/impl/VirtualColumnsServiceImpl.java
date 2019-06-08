package cn.moonnow.code.service.impl;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.moonnow.code.entity.Columns;
import cn.moonnow.code.entity.Dt;
import cn.moonnow.code.entity.Pk;
import cn.moonnow.code.entity.VirtualColumns;
import cn.moonnow.code.persistent.IColumnsPersistent;
import cn.moonnow.code.persistent.IDtPersistent;
import cn.moonnow.code.persistent.IPkPersistent;
import cn.moonnow.code.persistent.IVirtualColumnsPersistent;
import cn.moonnow.code.query.PkQuery;
import cn.moonnow.code.query.VirtualColumnsQuery;
import cn.moonnow.code.service.IVirtualColumnsService;
import cn.moonnow.code.vo.VirtualColumnsVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 虚拟列 服务实现
 */
@Log4j2
@Service("cn.moonnow.code.VirtualColumnsService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class VirtualColumnsServiceImpl implements IVirtualColumnsService {

  public static final String LOG = "VirtualColumnsService";

  @Resource(name = "cn.moonnow.code.VirtualColumnsPersistent")
  private IVirtualColumnsPersistent virtualColumnsPersistent;

  @Resource(name = "cn.moonnow.code.DtPersistent")
  private IDtPersistent dtPersistent;

  @Resource(name = "cn.moonnow.code.ColumnsPersistent")
  private IColumnsPersistent columnsPersistent;

  @Resource(name = "cn.moonnow.code.PkPersistent")
  private IPkPersistent pkPersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public VirtualColumns saveVirtualColumns(VirtualColumns virtualColumns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumns);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      virtualColumns.setVirtualColumnsId(ToolUtil.getUUID());
      Dt sourceDt = dtPersistent.getDtByPk(virtualColumns.getSourceDtId());
      Dt targetDt = dtPersistent.getDtByPk(virtualColumns.getTargetDtId());
      Columns sourceColumns = columnsPersistent.getColumnsByPk(virtualColumns.getSourceColumnsId());
      Columns targetColumns = columnsPersistent.getColumnsByPk(virtualColumns.getTargetColumnsId());
      Columns targetDisplayColumns = columnsPersistent.getColumnsByPk(virtualColumns.getTargetDisplayColumnsId());
      Columns targetPkColumns = null;
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(virtualColumns.getTargetDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        targetPkColumns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
      }
      StringBuilder virtualColumnsSql = new StringBuilder("( SELECT ");
      // ( SELECT dt.DT_NAME FROM PRO_DT dt WHERE dt.DT_ID IS NOT NULL AND dt.DT_ID = sort.DT_ID ) AS DT_NAME
      virtualColumnsSql.append(targetDt.getInitialLowercaseEntityName()).append(".").append(targetDisplayColumns.getColumnName())
          .append(" FROM ").append(targetDt.getDtName()).append(" ").append(targetDt.getInitialLowercaseEntityName())
          .append(" WHERE ").append(targetDt.getInitialLowercaseEntityName()).append(".").append(targetPkColumns.getColumnName())
          .append(" IS NOT NULL AND ").append(targetDt.getInitialLowercaseEntityName()).append(".").append(targetColumns.getColumnName())
          .append(" = ").append(sourceDt.getInitialLowercaseEntityName()).append(".").append(sourceColumns.getColumnName())
          .append(" ) AS ");
      if (ToolUtil.isNullStr(virtualColumns.getDisplayColumnsAlias())) {
        virtualColumnsSql.append(targetDisplayColumns.getColumnName());
      } else {
        virtualColumnsSql.append(virtualColumns.getDisplayColumnsAlias());
      }
      virtualColumns.setVirtualColumnsSql(virtualColumnsSql.toString());
      return virtualColumnsPersistent.saveVirtualColumns(virtualColumns);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<VirtualColumns> batchSaveVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumnss);
    }
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (VirtualColumns virtualColumns : virtualColumnss) {
        if (ToolUtil.isNullEntityAllFieldValue(virtualColumnss)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        virtualColumns.setVirtualColumnsId(ToolUtil.getUUID());
      }
      return virtualColumnsPersistent.batchSaveVirtualColumns(virtualColumnss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
      VirtualColumns oldVirtualColumns = virtualColumnsPersistent.getVirtualColumnsByPk(virtualColumns.getVirtualColumnsId());
      if (ToolUtil.isNullEntityAllFieldValue(oldVirtualColumns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return virtualColumnsPersistent.updateVirtualColumns(virtualColumns);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<VirtualColumns> batchUpdateVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumnss);
    }
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (VirtualColumns virtualColumns : virtualColumnss) {
        if (ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(virtualColumns.getVirtualColumnsId())) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        VirtualColumns oldVirtualColumns = virtualColumnsPersistent.getVirtualColumnsByPk(virtualColumns.getVirtualColumnsId());
        if (ToolUtil.isNullEntityAllFieldValue(oldVirtualColumns)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
      }
      return virtualColumnsPersistent.batchUpdateVirtualColumns(virtualColumnss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void removeVirtualColumns(VirtualColumns virtualColumns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumns);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<VirtualColumns> virtualColumnsSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(virtualColumns.getVirtualColumnsId())) {
        VirtualColumnsQuery virtualColumnsQuery = new VirtualColumnsQuery();
        BeanUtils.copyProperties(virtualColumns, virtualColumnsQuery);
        virtualColumnsSet.addAll(virtualColumnsPersistent.queryVirtualColumns(virtualColumnsQuery));
      } else {
        VirtualColumns oldVirtualColumns = virtualColumnsPersistent.getVirtualColumnsByPk(virtualColumns.getVirtualColumnsId());
        if (ToolUtil.isNullEntityAllFieldValue(oldVirtualColumns)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        virtualColumnsSet.add(oldVirtualColumns);
      }
      if (ToolUtil.isNotEmpty(virtualColumnsSet)) {
        virtualColumnsPersistent.batchRemoveVirtualColumns(virtualColumnsSet);
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
  public void batchRemoveVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + virtualColumnss);
    }
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<VirtualColumns> virtualColumnsSet = new LinkedHashSet<>();
      for (VirtualColumns virtualColumns : virtualColumnss) {
        if (ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(virtualColumns.getVirtualColumnsId())) {
          VirtualColumnsQuery virtualColumnsQuery = new VirtualColumnsQuery();
          BeanUtils.copyProperties(virtualColumns, virtualColumnsQuery);
          virtualColumnsSet.addAll(virtualColumnsPersistent.queryVirtualColumns(virtualColumnsQuery));
        } else {
          VirtualColumns oldVirtualColumns = virtualColumnsPersistent.getVirtualColumnsByPk(virtualColumns.getVirtualColumnsId());
          if (ToolUtil.isNullEntityAllFieldValue(oldVirtualColumns)) {
            throw new ToolException(ToolException.E_PARAM_ERR);
          }
          virtualColumnsSet.add(oldVirtualColumns);
        }
      }
      if (ToolUtil.isNotEmpty(virtualColumnsSet)) {
        virtualColumnsPersistent.batchRemoveVirtualColumns(virtualColumnsSet);
      }
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
      return virtualColumnsPersistent.getCountVirtualColumns(virtualColumnsQuery);
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
      return virtualColumnsPersistent.getVirtualColumnsByPk(virtualColumnsId);
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
      return virtualColumnsPersistent.getAllVirtualColumns();
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
      return virtualColumnsPersistent.queryVirtualColumns(virtualColumnsQuery);
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
      return virtualColumnsPersistent.pagingQueryVirtualColumns(param, virtualColumnsQuery);
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
      return virtualColumnsPersistent.getVirtualColumnsVOByPk(virtualColumnsId);
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
      return virtualColumnsPersistent.getAllVirtualColumnsVO();
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
      return virtualColumnsPersistent.queryVirtualColumnsVO(virtualColumnsQuery);
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
      return virtualColumnsPersistent.pagingQueryVirtualColumnsVO(param, virtualColumnsQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
