package cn.moonnow.code.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.code.entity.Pk;
import cn.moonnow.code.query.PkQuery;
import cn.moonnow.code.service.IPkService;
import cn.moonnow.code.vo.PkVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.rest.Responses;
import cn.moonnow.tool.rest.ToolController;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 主键 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "code/pk" })
public class PkController extends ToolController {

  public static final String LOG = "PkController";

  @Resource(name = "cn.moonnow.code.PkService")
  private IPkService pkService;

  /**
   * 保存 主键 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<Pk> save(Param param, @RequestBody Pk pk) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pk);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(pkService.savePk(pk));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 主键 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<Pk> batchSave(Param param, @RequestBody List<Pk> pks) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pks);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(pks)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(pkService.batchSavePk(pks));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 主键 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<Pk> update(Param param, @RequestBody Pk pk) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pk);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(pkService.updatePk(pk));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 主键 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<Pk> batchUpdate(Param param, @RequestBody List<Pk> pks) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pks);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(pks)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(pkService.batchUpdatePk(pks));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 主键 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<Pk> remove(Param param, @RequestBody Pk pk) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pk);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      pkService.removePk(pk);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 主键 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<Pk> batchRemove(Param param, @RequestBody List<Pk> pks) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pks);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(pks)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      pkService.batchRemovePk(pks);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 主键 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<Pk> getByPk(Param param, @RequestBody Pk pk) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pk);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(pk.getPkId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(pkService.getPkByPk(pk.getPkId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 主键 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<Pk> getAll(Param param, @RequestBody PkQuery pkQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pkQuery);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(pkQuery)) {
        responses.setData(pkService.getAllPk());
      } else {
        responses.setData(pkService.queryPk(pkQuery));
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 分页获取 主键 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<Pk> paging(Param param, @RequestBody PkQuery pkQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pkQuery);
    }
    Responses<Pk> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(pkQuery)) {
        responses.setData(pkService.pagingQueryPk(param, null));
      } else {
        responses.setData(pkService.pagingQueryPk(param, pkQuery));
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 主键 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<PkVO> getVOByPk(Param param, @RequestBody Pk pk) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pk);
    }
    Responses<PkVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(pk.getPkId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(pkService.getPkVOByPk(pk.getPkId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 主键 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<PkVO> getAllVO(Param param, @RequestBody PkQuery pkQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pkQuery);
    }
    Responses<PkVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(pkQuery)) {
        responses.setData(pkService.getAllPkVO());
      } else {
        responses.setData(pkService.queryPkVO(pkQuery));
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 分页获取 主键 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<PkVO> pagingVO(Param param, @RequestBody PkQuery pkQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + pkQuery);
    }
    Responses<PkVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(pkQuery)) {
        responses.setData(pkService.pagingQueryPkVO(param, null));
      } else {
        responses.setData(pkService.pagingQueryPkVO(param, pkQuery));
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

}
