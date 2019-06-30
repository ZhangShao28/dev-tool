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
import cn.moonnow.sign.entity.AccountGrpNexus;
import cn.moonnow.sign.query.AccountGrpNexusQuery;
import cn.moonnow.sign.service.IAccountGrpNexusService;
import cn.moonnow.sign.vo.AccountGrpNexusVO;
import lombok.extern.log4j.Log4j2;

/**
 * 账号分组 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "sign/accountGrpNexus" })
public class AccountGrpNexusController extends ToolController {

  public static final String LOG = "AccountGrpNexusController";

  @Resource(name = "cn.moonnow.sign.AccountGrpNexusService")
  private IAccountGrpNexusService accountGrpNexusService;

  /**
   * 保存 账号分组 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<AccountGrpNexus> save(Param param, @RequestBody AccountGrpNexus accountGrpNexus) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexus);
    }
    Responses<AccountGrpNexus> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(accountGrpNexusService.saveAccountGrpNexus(accountGrpNexus));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 账号分组 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<AccountGrpNexus> batchSave(Param param, @RequestBody List<AccountGrpNexus> accountGrpNexuss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexuss);
    }
    Responses<AccountGrpNexus> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(accountGrpNexuss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(accountGrpNexusService.batchSaveAccountGrpNexus(accountGrpNexuss));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 账号分组 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<AccountGrpNexus> update(Param param, @RequestBody AccountGrpNexus accountGrpNexus) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexus);
    }
    Responses<AccountGrpNexus> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(accountGrpNexusService.updateAccountGrpNexus(accountGrpNexus));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 账号分组 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<AccountGrpNexus> batchUpdate(Param param, @RequestBody List<AccountGrpNexus> accountGrpNexuss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexuss);
    }
    Responses<AccountGrpNexus> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(accountGrpNexuss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(accountGrpNexusService.batchUpdateAccountGrpNexus(accountGrpNexuss));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 账号分组 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<AccountGrpNexus> remove(Param param, @RequestBody AccountGrpNexus accountGrpNexus) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexus);
    }
    Responses<AccountGrpNexus> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      accountGrpNexusService.removeAccountGrpNexus(accountGrpNexus);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 账号分组 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<AccountGrpNexus> batchRemove(Param param, @RequestBody List<AccountGrpNexus> accountGrpNexuss) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexuss);
    }
    Responses<AccountGrpNexus> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(accountGrpNexuss)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      accountGrpNexusService.batchRemoveAccountGrpNexus(accountGrpNexuss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 账号分组 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<AccountGrpNexus> getByPk(Param param, @RequestBody AccountGrpNexus accountGrpNexus) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexus);
    }
    Responses<AccountGrpNexus> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(accountGrpNexus.getNexusId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(accountGrpNexusService.getAccountGrpNexusByPk(accountGrpNexus.getNexusId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 账号分组 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<AccountGrpNexus> getAll(Param param, @RequestBody AccountGrpNexusQuery accountGrpNexusQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexusQuery);
    }
    Responses<AccountGrpNexus> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        responses.setData(accountGrpNexusService.getAllAccountGrpNexus());
      } else {
        responses.setData(accountGrpNexusService.queryAccountGrpNexus(accountGrpNexusQuery));
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
   * 分页获取 账号分组 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<AccountGrpNexus> paging(Param param, @RequestBody AccountGrpNexusQuery accountGrpNexusQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexusQuery);
    }
    Responses<AccountGrpNexus> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        responses.setData(accountGrpNexusService.pagingQueryAccountGrpNexus(param, null));
      } else {
        responses.setData(accountGrpNexusService.pagingQueryAccountGrpNexus(param, accountGrpNexusQuery));
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
   * 通过主键获取 账号分组 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<AccountGrpNexusVO> getVOByPk(Param param, @RequestBody AccountGrpNexus accountGrpNexus) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexus);
    }
    Responses<AccountGrpNexusVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexus)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(accountGrpNexus.getNexusId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(accountGrpNexusService.getAccountGrpNexusVOByPk(accountGrpNexus.getNexusId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 账号分组 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<AccountGrpNexusVO> getAllVO(Param param, @RequestBody AccountGrpNexusQuery accountGrpNexusQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexusQuery);
    }
    Responses<AccountGrpNexusVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        responses.setData(accountGrpNexusService.getAllAccountGrpNexusVO());
      } else {
        responses.setData(accountGrpNexusService.queryAccountGrpNexusVO(accountGrpNexusQuery));
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
   * 分页获取 账号分组 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<AccountGrpNexusVO> pagingVO(Param param, @RequestBody AccountGrpNexusQuery accountGrpNexusQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountGrpNexusQuery);
    }
    Responses<AccountGrpNexusVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountGrpNexusQuery)) {
        responses.setData(accountGrpNexusService.pagingQueryAccountGrpNexusVO(param, null));
      } else {
        responses.setData(accountGrpNexusService.pagingQueryAccountGrpNexusVO(param, accountGrpNexusQuery));
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
