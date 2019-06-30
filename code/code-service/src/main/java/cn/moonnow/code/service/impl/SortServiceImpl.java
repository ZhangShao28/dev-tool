package cn.moonnow.code.service.impl;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.moonnow.code.entity.Sort;
import cn.moonnow.code.persistent.ISortPersistent;
import cn.moonnow.code.query.SortQuery;
import cn.moonnow.code.service.ISortService;
import cn.moonnow.code.vo.SortVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 排序 服务实现
 */
@Log4j2
@Service("cn.moonnow.code.SortService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class SortServiceImpl implements ISortService {

  public static final String LOG = "SortService";

  @Resource(name = "cn.moonnow.code.SortPersistent")
  private ISortPersistent sortPersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Sort saveSort(Sort sort) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sort);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      sort.setSortId(ToolUtil.getUUID());
      SortQuery sortQuery = new SortQuery();
      sortQuery.setDtIdAndeq(sort.getDtId());
      sortQuery.setColumnsIdAndeq(sort.getColumnsId());
      if (sortPersistent.getCountSort(sortQuery) > 0) {
        throw new ToolException("同一列不能重复添加排序。");
      }
      return sortPersistent.saveSort(sort);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Sort> batchSaveSort(Collection<Sort> sorts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sorts);
    }
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Sort sort : sorts) {
        if (ToolUtil.isNullEntityAllFieldValue(sort)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        sort.setSortId(ToolUtil.getUUID());
        SortQuery sortQuery = new SortQuery();
        sortQuery.setDtIdAndeq(sort.getDtId());
        sortQuery.setColumnsIdAndeq(sort.getColumnsId());
        if (sortPersistent.getCountSort(sortQuery) > 0) {
          throw new ToolException("同一列不能重复添加排序。");
        }
      }
      return sortPersistent.batchSaveSort(sorts);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
      Sort oldSort = sortPersistent.getSortByPk(sort.getSortId());
      if (ToolUtil.isNullEntityAllFieldValue(oldSort)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (!sort.getDtId().equals(oldSort.getDtId()) || !sort.getColumnsId().equals(oldSort.getColumnsId())) {
        SortQuery sortQuery = new SortQuery();
        sortQuery.setDtIdAndeq(sort.getDtId());
        sortQuery.setColumnsIdAndeq(sort.getColumnsId());
        if (sortPersistent.getCountSort(sortQuery) > 0) {
          throw new ToolException("同一列不能重复添加排序。");
        }
      }
      return sortPersistent.updateSort(sort);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Sort> batchUpdateSort(Collection<Sort> sorts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sorts);
    }
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Sort sort : sorts) {
        if (ToolUtil.isNullEntityAllFieldValue(sort)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(sort.getSortId())) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        Sort oldSort = sortPersistent.getSortByPk(sort.getSortId());
        if (ToolUtil.isNullEntityAllFieldValue(oldSort)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (!sort.getDtId().equals(oldSort.getDtId()) || !sort.getColumnsId().equals(oldSort.getColumnsId())) {
          SortQuery sortQuery = new SortQuery();
          sortQuery.setDtIdAndeq(sort.getDtId());
          sortQuery.setColumnsIdAndeq(sort.getColumnsId());
          if (sortPersistent.getCountSort(sortQuery) > 0) {
            throw new ToolException("同一列不能重复添加排序。");
          }
        }
      }
      return sortPersistent.batchUpdateSort(sorts);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void removeSort(Sort sort) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sort);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Sort> sortSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(sort.getSortId())) {
        SortQuery sortQuery = new SortQuery();
        BeanUtils.copyProperties(sort, sortQuery);
        sortSet.addAll(sortPersistent.querySort(sortQuery));
      } else {
        Sort oldSort = sortPersistent.getSortByPk(sort.getSortId());
        if (ToolUtil.isNullEntityAllFieldValue(oldSort)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        sortSet.add(oldSort);
      }
      if (ToolUtil.isNotEmpty(sortSet)) {
        sortPersistent.batchRemoveSort(sortSet);
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
  public void batchRemoveSort(Collection<Sort> sorts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + sorts);
    }
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Sort> sortSet = new LinkedHashSet<>();
      for (Sort sort : sorts) {
        if (ToolUtil.isNullEntityAllFieldValue(sort)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(sort.getSortId())) {
          SortQuery sortQuery = new SortQuery();
          BeanUtils.copyProperties(sort, sortQuery);
          sortSet.addAll(sortPersistent.querySort(sortQuery));
        } else {
          Sort oldSort = sortPersistent.getSortByPk(sort.getSortId());
          if (ToolUtil.isNullEntityAllFieldValue(oldSort)) {
            throw new ToolException(ToolException.E_PARAM_ERR);
          }
          sortSet.add(oldSort);
        }
      }
      if (ToolUtil.isNotEmpty(sortSet)) {
        sortPersistent.batchRemoveSort(sortSet);
      }
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
      return sortPersistent.getCountSort(sortQuery);
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
      return sortPersistent.getSortByPk(sortId);
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
      return sortPersistent.getAllSort();
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
      return sortPersistent.querySort(sortQuery);
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
      return sortPersistent.pagingQuerySort(param, sortQuery);
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
      return sortPersistent.getSortVOByPk(sortId);
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
      return sortPersistent.getAllSortVO();
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
      return sortPersistent.querySortVO(sortQuery);
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
      return sortPersistent.pagingQuerySortVO(param, sortQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
