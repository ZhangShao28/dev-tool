package cn.moonnow.sign.service;

import java.util.Collection;

import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.sign.entity.Account;
import cn.moonnow.sign.query.AccountQuery;
import cn.moonnow.sign.vo.AccountVO;

/**
 * 账号 服务接口
 */
public interface IAccountService {

  /**
   * 保存 账号 信息
   */
  public Account saveAccount(Account account) throws Exception;

  /**
   * 批量保存 账号 信息
   */
  public Collection<Account> batchSaveAccount(Collection<Account> accounts) throws Exception;

  /**
   * 修改 账号 信息
   */
  public Account updateAccount(Account account) throws Exception;

  /**
   * 批量修改 账号 信息
   */
  public Collection<Account> batchUpdateAccount(Collection<Account> accounts) throws Exception;

  /**
   * 删除 账号 信息
   */
  public void removeAccount(Account account) throws Exception;

  /**
   * 批量删除 账号 信息
   */
  public void batchRemoveAccount(Collection<Account> accounts) throws Exception;

  /**
   * 获取 账号 数据量
   */
  public Long getCountAccount(AccountQuery accountQuery) throws Exception;

  /**
   * 通过主键获取 账号 数据
   */
  public Account getAccountByPk(String accountId) throws Exception;

  /**
   * 获取 账号 所有数据
   */
  public Collection<Account> getAllAccount() throws Exception;

  /**
   * 查询 账号 数据
   */
  public Collection<Account> queryAccount(AccountQuery accountQuery) throws Exception;

  /**
   * 分页查询 账号 数据
   */
  public Paging<Account> pagingQueryAccount(Param param, AccountQuery accountQuery) throws Exception;

  /**
   * 通过主键获取 账号 展示数据
   */
  public AccountVO getAccountVOByPk(String accountId) throws Exception;

  /**
   * 获取 账号 所有展示数据
   */
  public Collection<AccountVO> getAllAccountVO() throws Exception;

  /**
   * 查询 账号 展示数据
   */
  public Collection<AccountVO> queryAccountVO(AccountQuery accountQuery) throws Exception;

  /**
   * 分页查询 账号 展示数据
   */
  public Paging<AccountVO> pagingQueryAccountVO(Param param, AccountQuery accountQuery) throws Exception;

}
