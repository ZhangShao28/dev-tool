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
import cn.moonnow.code.entity.Query;
import cn.moonnow.code.entity.Sort;
import cn.moonnow.code.entity.VirtualColumns;
import cn.moonnow.code.persistent.IColumnsPersistent;
import cn.moonnow.code.persistent.IDtPersistent;
import cn.moonnow.code.persistent.IPkPersistent;
import cn.moonnow.code.persistent.IQueryPersistent;
import cn.moonnow.code.persistent.ISortPersistent;
import cn.moonnow.code.persistent.IVirtualColumnsPersistent;
import cn.moonnow.code.query.ColumnsQuery;
import cn.moonnow.code.query.DtQuery;
import cn.moonnow.code.query.PkQuery;
import cn.moonnow.code.query.QueryQuery;
import cn.moonnow.code.query.SortQuery;
import cn.moonnow.code.query.VirtualColumnsQuery;
import cn.moonnow.code.service.IDtService;
import cn.moonnow.code.vo.DtVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 数据库表 服务实现
 */
@Log4j2
@Service("cn.moonnow.code.DtService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class DtServiceImpl implements IDtService {

  public static final String LOG = "DtService";

  @Resource(name = "cn.moonnow.code.DtPersistent")
  private IDtPersistent dtPersistent;

  @Resource(name = "cn.moonnow.code.ColumnsPersistent")
  private IColumnsPersistent columnsPersistent;

  @Resource(name = "cn.moonnow.code.PkPersistent")
  private IPkPersistent pkPersistent;

  @Resource(name = "cn.moonnow.code.QueryPersistent")
  private IQueryPersistent queryPersistent;

  @Resource(name = "cn.moonnow.code.SortPersistent")
  private ISortPersistent sortPersistent;

  @Resource(name = "cn.moonnow.code.VirtualColumnsPersistent")
  private IVirtualColumnsPersistent virtualColumnsPersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Dt saveDt(Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dt);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      dt.setDtId(ToolUtil.getUUID());
      dt.setDtName(ToolUtil.getDtNameFromDtStr(dt.getDtSql()));
      DtQuery dtQuery = new DtQuery();
      dtQuery.setDtNameAndeq(dt.getDtName());
      if (dtPersistent.getCountDt(dtQuery) > 0) {
        throw new ToolException("表名：" + dt.getDtName() + " 不能重复");
      }
      return dtPersistent.saveDt(dt);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Dt> batchSaveDt(Collection<Dt> dts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dts);
    }
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Dt dt : dts) {
        if (ToolUtil.isNullEntityAllFieldValue(dt)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        dt.setDtId(ToolUtil.getUUID());
        dt.setDtName(ToolUtil.getDtNameFromDtStr(dt.getDtSql()));
        DtQuery dtQuery = new DtQuery();
        dtQuery.setDtNameAndeq(dt.getDtName());
        if (dtPersistent.getCountDt(dtQuery) > 0) {
          throw new ToolException("表名：" + dt.getDtName() + " 不能重复");
        }
      }
      return dtPersistent.batchSaveDt(dts);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
      Dt oldDt = dtPersistent.getDtByPk(dt.getDtId());
      if (ToolUtil.isNullEntityAllFieldValue(oldDt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      dt.setDtName(ToolUtil.getDtNameFromDtStr(dt.getDtSql()));
      DtQuery dtQuery = new DtQuery();
      dtQuery.setDtNameAndeq(dt.getDtName());
      if (!dtQuery.getDtNameAndeq().equals(oldDt.getDtName())) {
        if (dtPersistent.getCountDt(dtQuery) > 0) {
          throw new ToolException("表名：" + dt.getDtName() + " 不能重复");
        }
      }
      return dtPersistent.updateDt(dt);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Dt> batchUpdateDt(Collection<Dt> dts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dts);
    }
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Dt dt : dts) {
        if (ToolUtil.isNullEntityAllFieldValue(dt)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(dt.getDtId())) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        Dt oldDt = dtPersistent.getDtByPk(dt.getDtId());
        if (ToolUtil.isNullEntityAllFieldValue(oldDt)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        dt.setDtName(ToolUtil.getDtNameFromDtStr(dt.getDtSql()));
        DtQuery dtQuery = new DtQuery();
        dtQuery.setDtNameAndeq(dt.getDtName());
        if (!dtQuery.getDtNameAndeq().equals(oldDt.getDtName())) {
          if (dtPersistent.getCountDt(dtQuery) > 0) {
            throw new ToolException("表名：" + dt.getDtName() + " 不能重复");
          }
        }
      }
      return dtPersistent.batchUpdateDt(dts);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void removeDt(Dt dt) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dt);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Dt> dtSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(dt.getDtId())) {
        DtQuery dtQuery = new DtQuery();
        BeanUtils.copyProperties(dt, dtQuery);
        dtSet.addAll(dtPersistent.queryDt(dtQuery));
      } else {
        Dt oldDt = dtPersistent.getDtByPk(dt.getDtId());
        if (ToolUtil.isNullEntityAllFieldValue(oldDt)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        dtSet.add(oldDt);
      }
      if (ToolUtil.isNotEmpty(dtSet)) {
        Set<VirtualColumns> delVirtualColumnsSet = new LinkedHashSet<>();
        Set<Query> delQuerySet = new LinkedHashSet<>();
        Set<Sort> delSortSet = new LinkedHashSet<>();
        Set<Pk> delPkSet = new LinkedHashSet<>();
        Set<Columns> delColumnsSet = new LinkedHashSet<>();
        for (Dt eachDt : dtSet) {
          VirtualColumnsQuery virtualColumnsSourceQuery = new VirtualColumnsQuery();
          virtualColumnsSourceQuery.setSourceDtId(eachDt.getDtId());
          Collection<VirtualColumns> delVirtualColumnsSource = virtualColumnsPersistent.queryVirtualColumns(virtualColumnsSourceQuery);
          if (ToolUtil.isNotEmpty(delVirtualColumnsSource)) {
            delVirtualColumnsSet.addAll(delVirtualColumnsSource);
          }
          VirtualColumnsQuery virtualColumnsTargetQuery = new VirtualColumnsQuery();
          virtualColumnsTargetQuery.setTargetDtId(eachDt.getDtId());
          Collection<VirtualColumns> delVirtualColumnsTarget = virtualColumnsPersistent.queryVirtualColumns(virtualColumnsTargetQuery);
          if (ToolUtil.isNotEmpty(delVirtualColumnsTarget)) {
            delVirtualColumnsSet.addAll(delVirtualColumnsTarget);
          }
          QueryQuery queryQuery = new QueryQuery();
          queryQuery.setDtId(eachDt.getDtId());
          Collection<Query> delQuery = queryPersistent.queryQuery(queryQuery);
          if (ToolUtil.isNotEmpty(delQuery)) {
            delQuerySet.addAll(delQuery);
          }
          SortQuery sortQuery = new SortQuery();
          sortQuery.setDtId(eachDt.getDtId());
          Collection<Sort> delSort = sortPersistent.querySort(sortQuery);
          if (ToolUtil.isNotEmpty(delSort)) {
            delSortSet.addAll(delSort);
          }
          PkQuery pkQuery = new PkQuery();
          pkQuery.setDtId(eachDt.getDtId());
          Collection<Pk> delPk = pkPersistent.queryPk(pkQuery);
          if (ToolUtil.isNotEmpty(delPk)) {
            delPkSet.addAll(delPk);
          }
          ColumnsQuery columnsQuery = new ColumnsQuery();
          columnsQuery.setDtId(eachDt.getDtId());
          Collection<Columns> delColumns = columnsPersistent.queryColumns(columnsQuery);
          if (ToolUtil.isNotEmpty(delColumns)) {
            delColumnsSet.addAll(delColumns);
          }
        }
        if (ToolUtil.isNotEmpty(delVirtualColumnsSet)) {
          virtualColumnsPersistent.batchRemoveVirtualColumns(delVirtualColumnsSet);
        }
        if (ToolUtil.isNotEmpty(delQuerySet)) {
          queryPersistent.batchRemoveQuery(delQuerySet);
        }
        if (ToolUtil.isNotEmpty(delSortSet)) {
          sortPersistent.batchRemoveSort(delSortSet);
        }
        if (ToolUtil.isNotEmpty(delPkSet)) {
          pkPersistent.batchRemovePk(delPkSet);
        }
        if (ToolUtil.isNotEmpty(delColumnsSet)) {
          columnsPersistent.batchRemoveColumns(delColumnsSet);
        }
        dtPersistent.batchRemoveDt(dtSet);
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
  public void batchRemoveDt(Collection<Dt> dts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dts);
    }
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Dt> dtSet = new LinkedHashSet<>();
      for (Dt dt : dts) {
        if (ToolUtil.isNullEntityAllFieldValue(dt)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(dt.getDtId())) {
          DtQuery dtQuery = new DtQuery();
          BeanUtils.copyProperties(dt, dtQuery);
          dtSet.addAll(dtPersistent.queryDt(dtQuery));
        } else {
          Dt oldDt = dtPersistent.getDtByPk(dt.getDtId());
          if (ToolUtil.isNullEntityAllFieldValue(oldDt)) {
            throw new ToolException(ToolException.E_PARAM_ERR);
          }
          dtSet.add(oldDt);
        }
      }
      if (ToolUtil.isNotEmpty(dtSet)) {
        Set<VirtualColumns> delVirtualColumnsSet = new LinkedHashSet<>();
        Set<Query> delQuerySet = new LinkedHashSet<>();
        Set<Sort> delSortSet = new LinkedHashSet<>();
        Set<Pk> delPkSet = new LinkedHashSet<>();
        Set<Columns> delColumnsSet = new LinkedHashSet<>();
        for (Dt dt : dtSet) {
          VirtualColumnsQuery virtualColumnsSourceQuery = new VirtualColumnsQuery();
          virtualColumnsSourceQuery.setSourceDtId(dt.getDtId());
          Collection<VirtualColumns> delVirtualColumnsSource = virtualColumnsPersistent.queryVirtualColumns(virtualColumnsSourceQuery);
          if (ToolUtil.isNotEmpty(delVirtualColumnsSource)) {
            delVirtualColumnsSet.addAll(delVirtualColumnsSource);
          }
          VirtualColumnsQuery virtualColumnsTargetQuery = new VirtualColumnsQuery();
          virtualColumnsTargetQuery.setTargetDtId(dt.getDtId());
          Collection<VirtualColumns> delVirtualColumnsTarget = virtualColumnsPersistent.queryVirtualColumns(virtualColumnsTargetQuery);
          if (ToolUtil.isNotEmpty(delVirtualColumnsTarget)) {
            delVirtualColumnsSet.addAll(delVirtualColumnsTarget);
          }
          QueryQuery queryQuery = new QueryQuery();
          queryQuery.setDtId(dt.getDtId());
          Collection<Query> delQuery = queryPersistent.queryQuery(queryQuery);
          if (ToolUtil.isNotEmpty(delQuery)) {
            delQuerySet.addAll(delQuery);
          }
          SortQuery sortQuery = new SortQuery();
          sortQuery.setDtId(dt.getDtId());
          Collection<Sort> delSort = sortPersistent.querySort(sortQuery);
          if (ToolUtil.isNotEmpty(delSort)) {
            delSortSet.addAll(delSort);
          }
          PkQuery pkQuery = new PkQuery();
          pkQuery.setDtId(dt.getDtId());
          Collection<Pk> delPk = pkPersistent.queryPk(pkQuery);
          if (ToolUtil.isNotEmpty(delPk)) {
            delPkSet.addAll(delPk);
          }
          ColumnsQuery columnsQuery = new ColumnsQuery();
          columnsQuery.setDtId(dt.getDtId());
          Collection<Columns> delColumns = columnsPersistent.queryColumns(columnsQuery);
          if (ToolUtil.isNotEmpty(delColumns)) {
            delColumnsSet.addAll(delColumns);
          }
        }
        if (ToolUtil.isNotEmpty(delVirtualColumnsSet)) {
          virtualColumnsPersistent.batchRemoveVirtualColumns(delVirtualColumnsSet);
        }
        if (ToolUtil.isNotEmpty(delQuerySet)) {
          queryPersistent.batchRemoveQuery(delQuerySet);
        }
        if (ToolUtil.isNotEmpty(delSortSet)) {
          sortPersistent.batchRemoveSort(delSortSet);
        }
        if (ToolUtil.isNotEmpty(delPkSet)) {
          pkPersistent.batchRemovePk(delPkSet);
        }
        if (ToolUtil.isNotEmpty(delColumnsSet)) {
          columnsPersistent.batchRemoveColumns(delColumnsSet);
        }
        dtPersistent.batchRemoveDt(dtSet);
      }
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
      return dtPersistent.getCountDt(dtQuery);
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
      return dtPersistent.getDtByPk(dtId);
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
      return dtPersistent.getAllDt();
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
      return dtPersistent.queryDt(dtQuery);
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
      return dtPersistent.pagingQueryDt(param, dtQuery);
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
      return dtPersistent.getDtVOByPk(dtId);
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
      return dtPersistent.getAllDtVO();
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
      return dtPersistent.queryDtVO(dtQuery);
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
      return dtPersistent.pagingQueryDtVO(param, dtQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
