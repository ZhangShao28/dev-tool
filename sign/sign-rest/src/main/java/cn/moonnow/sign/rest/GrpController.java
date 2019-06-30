package cn.moonnow.sign.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.rest.Responses;
import cn.moonnow.tool.rest.ToolController;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import cn.moonnow.sign.entity.Grp;
import cn.moonnow.sign.query.GrpQuery;
import cn.moonnow.sign.service.IGrpService;
import cn.moonnow.sign.vo.GrpVO;
import lombok.extern.log4j.Log4j2;

/**
 * 组 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "sign/grp" })
public class GrpController extends ToolController {

  public static final String LOG = "GrpController";

  @Resource(name = "cn.moonnow.sign.GrpService")
  private IGrpService grpService;

  /**
   * 保存 组 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<Grp> save(Param param, @RequestBody Grp grp) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grp);
    }
    Responses<Grp> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(grp)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(grpService.saveGrp(grp));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 组 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<Grp> batchSave(Param param, @RequestBody List<Grp> grps) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grps);
    }
    Responses<Grp> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(grps)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(grpService.batchSaveGrp(grps));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 组 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<Grp> update(Param param, @RequestBody Grp grp) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grp);
    }
    Responses<Grp> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(grp)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(grpService.updateGrp(grp));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 组 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<Grp> batchUpdate(Param param, @RequestBody List<Grp> grps) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grps);
    }
    Responses<Grp> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(grps)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(grpService.batchUpdateGrp(grps));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 组 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<Grp> remove(Param param, @RequestBody Grp grp) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grp);
    }
    Responses<Grp> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(grp)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      grpService.removeGrp(grp);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 组 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<Grp> batchRemove(Param param, @RequestBody List<Grp> grps) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grps);
    }
    Responses<Grp> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(grps)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      grpService.batchRemoveGrp(grps);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 组 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<Grp> getByPk(Param param, @RequestBody Grp grp) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grp);
    }
    Responses<Grp> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(grp)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(grp.getGrpId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(grpService.getGrpByPk(grp.getGrpId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 组 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<Grp> getAll(Param param, @RequestBody GrpQuery grpQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grpQuery);
    }
    Responses<Grp> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(grpQuery)) {
        responses.setData(grpService.getAllGrp());
      } else {
        responses.setData(grpService.queryGrp(grpQuery));
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
   * 分页获取 组 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<Grp> paging(Param param, @RequestBody GrpQuery grpQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grpQuery);
    }
    Responses<Grp> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(grpQuery)) {
        responses.setData(grpService.pagingQueryGrp(param, null));
      } else {
        responses.setData(grpService.pagingQueryGrp(param, grpQuery));
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
   * 通过主键获取 组 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<GrpVO> getVOByPk(Param param, @RequestBody Grp grp) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grp);
    }
    Responses<GrpVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(grp)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(grp.getGrpId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(grpService.getGrpVOByPk(grp.getGrpId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 组 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<GrpVO> getAllVO(Param param, @RequestBody GrpQuery grpQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grpQuery);
    }
    Responses<GrpVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(grpQuery)) {
        responses.setData(grpService.getAllGrpVO());
      } else {
        responses.setData(grpService.queryGrpVO(grpQuery));
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
   * 分页获取 组 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<GrpVO> pagingVO(Param param, @RequestBody GrpQuery grpQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + grpQuery);
    }
    Responses<GrpVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(grpQuery)) {
        responses.setData(grpService.pagingQueryGrpVO(param, null));
      } else {
        responses.setData(grpService.pagingQueryGrpVO(param, grpQuery));
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
