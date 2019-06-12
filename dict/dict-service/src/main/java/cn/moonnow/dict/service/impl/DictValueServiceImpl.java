package cn.moonnow.dict.service.impl;

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
import cn.moonnow.dict.entity.DictValue;
import cn.moonnow.dict.persistent.IDictValuePersistent;
import cn.moonnow.dict.query.DictValueQuery;
import cn.moonnow.dict.service.IDictValueService;
import cn.moonnow.dict.vo.DictValueVO;
import lombok.extern.log4j.Log4j2;

/**
 * 数据字典值 服务实现
 */
@Log4j2
@Service("cn.moonnow.dict.DictValueService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class DictValueServiceImpl implements IDictValueService {

  public static final String LOG = "DictValueService";

  @Resource(name = "cn.moonnow.dict.DictValuePersistent")
  private IDictValuePersistent dictValuePersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public DictValue saveDictValue(DictValue dictValue) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dictValue);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictValue)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      dictValue.setDictValueId(ToolUtil.getUUID());
      return dictValuePersistent.saveDictValue(dictValue);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<DictValue> batchSaveDictValue(Collection<DictValue> dictValues) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dictValues);
    }
    try {
      if (ToolUtil.isEmpty(dictValues)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (DictValue dictValue : dictValues) {
        if (ToolUtil.isNullEntityAllFieldValue(dictValue)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        dictValue.setDictValueId(ToolUtil.getUUID());
      }
      return dictValuePersistent.batchSaveDictValue(dictValues);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public DictValue updateDictValue(DictValue dictValue) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dictValue);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictValue)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dictValue.getDictValueId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      DictValue oldDictValue = dictValuePersistent.getDictValueByPk(dictValue.getDictValueId());
      if (ToolUtil.isNullEntityAllFieldValue(oldDictValue)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return dictValuePersistent.updateDictValue(dictValue);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<DictValue> batchUpdateDictValue(Collection<DictValue> dictValues) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dictValues);
    }
    try {
      if (ToolUtil.isEmpty(dictValues)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (DictValue dictValue : dictValues) {
        if (ToolUtil.isNullEntityAllFieldValue(dictValue)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(dictValue.getDictValueId())) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        DictValue oldDictValue = dictValuePersistent.getDictValueByPk(dictValue.getDictValueId());
        if (ToolUtil.isNullEntityAllFieldValue(oldDictValue)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
      }
      return dictValuePersistent.batchUpdateDictValue(dictValues);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void removeDictValue(DictValue dictValue) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dictValue);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictValue)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<DictValue> dictValueSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(dictValue.getDictValueId())) {
        DictValueQuery dictValueQuery = new DictValueQuery();
        BeanUtils.copyProperties(dictValue, dictValueQuery);
        dictValueSet.addAll(dictValuePersistent.queryDictValue(dictValueQuery));
      } else {
        DictValue oldDictValue = dictValuePersistent.getDictValueByPk(dictValue.getDictValueId());
        if (ToolUtil.isNullEntityAllFieldValue(oldDictValue)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        dictValueSet.add(oldDictValue);
      }
      if (ToolUtil.isNotEmpty(dictValueSet)) {
        dictValuePersistent.batchRemoveDictValue(dictValueSet);
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
  public void batchRemoveDictValue(Collection<DictValue> dictValues) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dictValues);
    }
    try {
      if (ToolUtil.isEmpty(dictValues)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<DictValue> dictValueSet = new LinkedHashSet<>();
      for (DictValue dictValue : dictValues) {
        if (ToolUtil.isNullEntityAllFieldValue(dictValue)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(dictValue.getDictValueId())) {
          DictValueQuery dictValueQuery = new DictValueQuery();
          BeanUtils.copyProperties(dictValue, dictValueQuery);
          dictValueSet.addAll(dictValuePersistent.queryDictValue(dictValueQuery));
        } else {
          DictValue oldDictValue = dictValuePersistent.getDictValueByPk(dictValue.getDictValueId());
          if (ToolUtil.isNullEntityAllFieldValue(oldDictValue)) {
            throw new ToolException(ToolException.E_PARAM_ERR);
          }
          dictValueSet.add(oldDictValue);
        }
      }
      if (ToolUtil.isNotEmpty(dictValueSet)) {
        dictValuePersistent.batchRemoveDictValue(dictValueSet);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Long getCountDictValue(DictValueQuery dictValueQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dictValueQuery);
    }
    try {
      return dictValuePersistent.getCountDictValue(dictValueQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public DictValue getDictValueByPk(String dictValueId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dictValueId);
    }
    try {
      if (ToolUtil.isNullStr(dictValueId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return dictValuePersistent.getDictValueByPk(dictValueId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<DictValue> getAllDictValue() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return dictValuePersistent.getAllDictValue();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<DictValue> queryDictValue(DictValueQuery dictValueQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dictValueQuery);
    }
    try {
      return dictValuePersistent.queryDictValue(dictValueQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<DictValue> pagingQueryDictValue(Param param, DictValueQuery dictValueQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValueQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return dictValuePersistent.pagingQueryDictValue(param, dictValueQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public DictValueVO getDictValueVOByPk(String dictValueId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dictValueId);
    }
    try {
      if (ToolUtil.isNullStr(dictValueId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return dictValuePersistent.getDictValueVOByPk(dictValueId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<DictValueVO> getAllDictValueVO() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return dictValuePersistent.getAllDictValueVO();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<DictValueVO> queryDictValueVO(DictValueQuery dictValueQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + dictValueQuery);
    }
    try {
      return dictValuePersistent.queryDictValueVO(dictValueQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<DictValueVO> pagingQueryDictValueVO(Param param, DictValueQuery dictValueQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValueQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return dictValuePersistent.pagingQueryDictValueVO(param, dictValueQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
