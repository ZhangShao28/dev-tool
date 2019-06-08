package cn.moonnow.code.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.code.entity.VirtualColumns;
import cn.moonnow.code.query.VirtualColumnsQuery;
import cn.moonnow.code.service.IVirtualColumnsService;
import cn.moonnow.code.vo.VirtualColumnsVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.rest.Responses;
import cn.moonnow.tool.rest.ToolController;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 虚拟列 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "code/virtualColumns" })
public class VirtualColumnsController extends ToolController {

  public static final String LOG = "VirtualColumnsController";

  @Resource(name = "cn.moonnow.code.VirtualColumnsService")
  private IVirtualColumnsService virtualColumnsService;

  /**
   * 保存 虚拟列 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<VirtualColumns> save(Param param, @RequestBody VirtualColumns virtualColumns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumns);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(virtualColumnsService.saveVirtualColumns(virtualColumns));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 虚拟列 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<VirtualColumns> batchSave(Param param, @RequestBody List<VirtualColumns> virtualColumnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumnss);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(virtualColumnsService.batchSaveVirtualColumns(virtualColumnss));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 虚拟列 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<VirtualColumns> update(Param param, @RequestBody VirtualColumns virtualColumns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumns);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(virtualColumnsService.updateVirtualColumns(virtualColumns));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 虚拟列 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<VirtualColumns> batchUpdate(Param param, @RequestBody List<VirtualColumns> virtualColumnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumnss);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(virtualColumnsService.batchUpdateVirtualColumns(virtualColumnss));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 虚拟列 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<VirtualColumns> remove(Param param, @RequestBody VirtualColumns virtualColumns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumns);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      virtualColumnsService.removeVirtualColumns(virtualColumns);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 虚拟列 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<VirtualColumns> batchRemove(Param param, @RequestBody List<VirtualColumns> virtualColumnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumnss);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      virtualColumnsService.batchRemoveVirtualColumns(virtualColumnss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 虚拟列 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<VirtualColumns> getByPk(Param param, @RequestBody VirtualColumns virtualColumns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumns);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(virtualColumns.getVirtualColumnsId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(virtualColumnsService.getVirtualColumnsByPk(virtualColumns.getVirtualColumnsId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 虚拟列 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<VirtualColumns> getAll(Param param, @RequestBody VirtualColumnsQuery virtualColumnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumnsQuery);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        responses.setData(virtualColumnsService.getAllVirtualColumns());
      } else {
        responses.setData(virtualColumnsService.queryVirtualColumns(virtualColumnsQuery));
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
   * 分页获取 虚拟列 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<VirtualColumns> paging(Param param, @RequestBody VirtualColumnsQuery virtualColumnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumnsQuery);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        responses.setData(virtualColumnsService.pagingQueryVirtualColumns(param, null));
      } else {
        responses.setData(virtualColumnsService.pagingQueryVirtualColumns(param, virtualColumnsQuery));
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
   * 通过主键获取 虚拟列 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<VirtualColumnsVO> getVOByPk(Param param, @RequestBody VirtualColumns virtualColumns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumns);
    }
    Responses<VirtualColumnsVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(virtualColumns.getVirtualColumnsId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(virtualColumnsService.getVirtualColumnsVOByPk(virtualColumns.getVirtualColumnsId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 虚拟列 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<VirtualColumnsVO> getAllVO(Param param, @RequestBody VirtualColumnsQuery virtualColumnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumnsQuery);
    }
    Responses<VirtualColumnsVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        responses.setData(virtualColumnsService.getAllVirtualColumnsVO());
      } else {
        responses.setData(virtualColumnsService.queryVirtualColumnsVO(virtualColumnsQuery));
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
   * 分页获取 虚拟列 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<VirtualColumnsVO> pagingVO(Param param, @RequestBody VirtualColumnsQuery virtualColumnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + virtualColumnsQuery);
    }
    Responses<VirtualColumnsVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        responses.setData(virtualColumnsService.pagingQueryVirtualColumnsVO(param, null));
      } else {
        responses.setData(virtualColumnsService.pagingQueryVirtualColumnsVO(param, virtualColumnsQuery));
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
