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
import cn.moonnow.code.persistent.IColumnsPersistent;
import cn.moonnow.code.query.ColumnsQuery;
import cn.moonnow.code.service.IColumnsService;
import cn.moonnow.code.vo.ColumnsVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 列 服务实现
 */
@Log4j2
@Service("cn.moonnow.code.ColumnsService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class ColumnsServiceImpl implements IColumnsService {

  public static final String LOG = "ColumnsService";

  @Resource(name = "cn.moonnow.code.ColumnsPersistent")
  private IColumnsPersistent columnsPersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Columns saveColumns(Columns columns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columns);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      columns.setDtId(ToolUtil.getUUID());
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setColumnNameAndeq(columns.getColumnName());
      columnsQuery.setDtIdAndeq(columns.getDtId());
      if (columnsPersistent.getCountColumns(columnsQuery) > 0) {
        throw new ToolException("列名：" + columns.getColumnName() + " 不能重复");
      }
      return columnsPersistent.saveColumns(columns);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Columns> batchSaveColumns(Collection<Columns> columnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columnss);
    }
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Columns columns : columnss) {
        if (ToolUtil.isNullEntityAllFieldValue(columns)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        columns.setDtId(ToolUtil.getUUID());
        ColumnsQuery columnsQuery = new ColumnsQuery();
        columnsQuery.setColumnNameAndeq(columns.getColumnName());
        columnsQuery.setDtIdAndeq(columns.getDtId());
        if (columnsPersistent.getCountColumns(columnsQuery) > 0) {
          throw new ToolException("列名：" + columns.getColumnName() + " 不能重复");
        }
      }
      return columnsPersistent.batchSaveColumns(columnss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
      Columns oldColumns = columnsPersistent.getColumnsByPk(columns.getColumnsId());
      if (ToolUtil.isNullEntityAllFieldValue(oldColumns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setColumnNameAndeq(columns.getColumnName());
      columnsQuery.setDtIdAndeq(columns.getDtId());
      if (!(columns.getColumnName().equals(oldColumns.getColumnName()) && columns.getDtId().equals(oldColumns.getDtId()))) {
        if (columnsPersistent.getCountColumns(columnsQuery) > 0) {
          throw new ToolException("列名：" + columns.getColumnName() + " 不能重复");
        }
      }
      return columnsPersistent.updateColumns(columns);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Columns> batchUpdateColumns(Collection<Columns> columnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columnss);
    }
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Columns columns : columnss) {
        if (ToolUtil.isNullEntityAllFieldValue(columns)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(columns.getColumnsId())) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        Columns oldColumns = columnsPersistent.getColumnsByPk(columns.getColumnsId());
        if (ToolUtil.isNullEntityAllFieldValue(oldColumns)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        ColumnsQuery columnsQuery = new ColumnsQuery();
        columnsQuery.setColumnNameAndeq(columns.getColumnName());
        columnsQuery.setDtIdAndeq(columns.getDtId());
        if (!(columns.getColumnName().equals(oldColumns.getColumnName()) && columns.getDtId().equals(oldColumns.getDtId()))) {
          if (columnsPersistent.getCountColumns(columnsQuery) > 0) {
            throw new ToolException("列名：" + columns.getColumnName() + " 不能重复");
          }
        }
      }
      return columnsPersistent.batchUpdateColumns(columnss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void removeColumns(Columns columns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columns);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Columns> columnsSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(columns.getColumnsId())) {
        ColumnsQuery columnsQuery = new ColumnsQuery();
        BeanUtils.copyProperties(columns, columnsQuery);
        columnsSet.addAll(columnsPersistent.queryColumns(columnsQuery));
      } else {
        Columns oldColumns = columnsPersistent.getColumnsByPk(columns.getColumnsId());
        if (ToolUtil.isNullEntityAllFieldValue(oldColumns)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        columnsSet.add(oldColumns);
      }
      if (ToolUtil.isNotEmpty(columnsSet)) {
        columnsPersistent.batchRemoveColumns(columnsSet);
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
  public void batchRemoveColumns(Collection<Columns> columnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + columnss);
    }
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Columns> columnsSet = new LinkedHashSet<>();
      for (Columns columns : columnss) {
        if (ToolUtil.isNullEntityAllFieldValue(columns)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(columns.getColumnsId())) {
          ColumnsQuery columnsQuery = new ColumnsQuery();
          BeanUtils.copyProperties(columns, columnsQuery);
          columnsSet.addAll(columnsPersistent.queryColumns(columnsQuery));
        } else {
          Columns oldColumns = columnsPersistent.getColumnsByPk(columns.getColumnsId());
          if (ToolUtil.isNullEntityAllFieldValue(oldColumns)) {
            throw new ToolException(ToolException.E_PARAM_ERR);
          }
          columnsSet.add(oldColumns);
        }
      }
      if (ToolUtil.isNotEmpty(columnsSet)) {
        columnsPersistent.batchRemoveColumns(columnsSet);
      }
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
      return columnsPersistent.getCountColumns(columnsQuery);
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
      return columnsPersistent.getColumnsByPk(columnsId);
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
      return columnsPersistent.getAllColumns();
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
      return columnsPersistent.queryColumns(columnsQuery);
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
      return columnsPersistent.pagingQueryColumns(param, columnsQuery);
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
      return columnsPersistent.getColumnsVOByPk(columnsId);
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
      return columnsPersistent.getAllColumnsVO();
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
      return columnsPersistent.queryColumnsVO(columnsQuery);
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
      return columnsPersistent.pagingQueryColumnsVO(param, columnsQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
