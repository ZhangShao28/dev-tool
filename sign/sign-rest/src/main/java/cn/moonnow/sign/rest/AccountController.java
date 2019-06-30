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
import cn.moonnow.sign.entity.Account;
import cn.moonnow.sign.query.AccountQuery;
import cn.moonnow.sign.service.IAccountService;
import cn.moonnow.sign.vo.AccountVO;
import lombok.extern.log4j.Log4j2;

/**
 * 账号 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "sign/account" })
public class AccountController extends ToolController {

  public static final String LOG = "AccountController";

  @Resource(name = "cn.moonnow.sign.AccountService")
  private IAccountService accountService;

  /**
   * 保存 账号 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<Account> save(Param param, @RequestBody Account account) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + account);
    }
    Responses<Account> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(account)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(accountService.saveAccount(account));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 账号 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<Account> batchSave(Param param, @RequestBody List<Account> accounts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accounts);
    }
    Responses<Account> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(accounts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(accountService.batchSaveAccount(accounts));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 账号 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<Account> update(Param param, @RequestBody Account account) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + account);
    }
    Responses<Account> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(account)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(accountService.updateAccount(account));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 账号 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<Account> batchUpdate(Param param, @RequestBody List<Account> accounts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accounts);
    }
    Responses<Account> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(accounts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(accountService.batchUpdateAccount(accounts));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 账号 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<Account> remove(Param param, @RequestBody Account account) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + account);
    }
    Responses<Account> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(account)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      accountService.removeAccount(account);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 账号 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<Account> batchRemove(Param param, @RequestBody List<Account> accounts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accounts);
    }
    Responses<Account> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(accounts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      accountService.batchRemoveAccount(accounts);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 账号 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<Account> getByPk(Param param, @RequestBody Account account) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + account);
    }
    Responses<Account> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(account)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(account.getAccountId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(accountService.getAccountByPk(account.getAccountId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 账号 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<Account> getAll(Param param, @RequestBody AccountQuery accountQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountQuery);
    }
    Responses<Account> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountQuery)) {
        responses.setData(accountService.getAllAccount());
      } else {
        responses.setData(accountService.queryAccount(accountQuery));
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
   * 分页获取 账号 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<Account> paging(Param param, @RequestBody AccountQuery accountQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountQuery);
    }
    Responses<Account> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountQuery)) {
        responses.setData(accountService.pagingQueryAccount(param, null));
      } else {
        responses.setData(accountService.pagingQueryAccount(param, accountQuery));
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
   * 通过主键获取 账号 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<AccountVO> getVOByPk(Param param, @RequestBody Account account) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + account);
    }
    Responses<AccountVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(account)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(account.getAccountId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(accountService.getAccountVOByPk(account.getAccountId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 账号 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<AccountVO> getAllVO(Param param, @RequestBody AccountQuery accountQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountQuery);
    }
    Responses<AccountVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountQuery)) {
        responses.setData(accountService.getAllAccountVO());
      } else {
        responses.setData(accountService.queryAccountVO(accountQuery));
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
   * 分页获取 账号 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<AccountVO> pagingVO(Param param, @RequestBody AccountQuery accountQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + accountQuery);
    }
    Responses<AccountVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(accountQuery)) {
        responses.setData(accountService.pagingQueryAccountVO(param, null));
      } else {
        responses.setData(accountService.pagingQueryAccountVO(param, accountQuery));
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
