package cn.moonnow.code.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.code.entity.Columns;
import cn.moonnow.code.query.ColumnsQuery;
import cn.moonnow.code.service.IColumnsService;
import cn.moonnow.code.vo.ColumnsVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.rest.Responses;
import cn.moonnow.tool.rest.ToolController;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 列 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "code/columns" })
public class ColumnsController extends ToolController {

  public static final String LOG = "ColumnsController";

  @Resource(name = "cn.moonnow.code.ColumnsService")
  private IColumnsService columnsService;

  /**
   * 保存 列 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<Columns> save(Param param, @RequestBody Columns columns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columns);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(columnsService.saveColumns(columns));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 列 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<Columns> batchSave(Param param, @RequestBody List<Columns> columnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columnss);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(columnsService.batchSaveColumns(columnss));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 列 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<Columns> update(Param param, @RequestBody Columns columns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columns);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(columnsService.updateColumns(columns));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 列 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<Columns> batchUpdate(Param param, @RequestBody List<Columns> columnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columnss);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(columnsService.batchUpdateColumns(columnss));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 列 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<Columns> remove(Param param, @RequestBody Columns columns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columns);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      columnsService.removeColumns(columns);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 列 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<Columns> batchRemove(Param param, @RequestBody List<Columns> columnss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columnss);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      columnsService.batchRemoveColumns(columnss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 列 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<Columns> getByPk(Param param, @RequestBody Columns columns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columns);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(columns.getColumnsId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(columnsService.getColumnsByPk(columns.getColumnsId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 列 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<Columns> getAll(Param param, @RequestBody ColumnsQuery columnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columnsQuery);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        responses.setData(columnsService.getAllColumns());
      } else {
        responses.setData(columnsService.queryColumns(columnsQuery));
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
   * 分页获取 列 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<Columns> paging(Param param, @RequestBody ColumnsQuery columnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columnsQuery);
    }
    Responses<Columns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        responses.setData(columnsService.pagingQueryColumns(param, null));
      } else {
        responses.setData(columnsService.pagingQueryColumns(param, columnsQuery));
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
   * 通过主键获取 列 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<ColumnsVO> getVOByPk(Param param, @RequestBody Columns columns) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columns);
    }
    Responses<ColumnsVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(columns.getColumnsId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(columnsService.getColumnsVOByPk(columns.getColumnsId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 列 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<ColumnsVO> getAllVO(Param param, @RequestBody ColumnsQuery columnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columnsQuery);
    }
    Responses<ColumnsVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        responses.setData(columnsService.getAllColumnsVO());
      } else {
        responses.setData(columnsService.queryColumnsVO(columnsQuery));
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
   * 分页获取 列 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<ColumnsVO> pagingVO(Param param, @RequestBody ColumnsQuery columnsQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + columnsQuery);
    }
    Responses<ColumnsVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        responses.setData(columnsService.pagingQueryColumnsVO(param, null));
      } else {
        responses.setData(columnsService.pagingQueryColumnsVO(param, columnsQuery));
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
