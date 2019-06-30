package cn.moonnow.sign.persistent;

import java.util.Collection;

import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.sign.entity.AccountGrpNexus;
import cn.moonnow.sign.query.AccountGrpNexusQuery;
import cn.moonnow.sign.vo.AccountGrpNexusVO;

/**
 * 账号分组 持久化接口
 */
public interface IAccountGrpNexusPersistent {

  public static final String TABLE_NAME = "DEV_ACCOUNT_GRP_NEXUS";

  /**
   * 保存 账号分组 信息
   */
  public AccountGrpNexus saveAccountGrpNexus(AccountGrpNexus accountGrpNexus) throws Exception;

  /**
   * 批量保存 账号分组 信息
   */
  public Collection<AccountGrpNexus> batchSaveAccountGrpNexus(Collection<AccountGrpNexus> accountGrpNexuss) throws Exception;

  /**
   * 修改 账号分组 信息
   */
  public AccountGrpNexus updateAccountGrpNexus(AccountGrpNexus accountGrpNexus) throws Exception;

  /**
   * 批量修改 账号分组 信息
   */
  public Collection<AccountGrpNexus> batchUpdateAccountGrpNexus(Collection<AccountGrpNexus> accountGrpNexuss) throws Exception;

  /**
   * 删除 账号分组 信息
   */
  public void removeAccountGrpNexus(AccountGrpNexus accountGrpNexus) throws Exception;

  /**
   * 批量删除 账号分组 信息
   */
  public void batchRemoveAccountGrpNexus(Collection<AccountGrpNexus> accountGrpNexuss) throws Exception;

  /**
   * 获取 账号分组 数据量
   */
  public Long getCountAccountGrpNexus(AccountGrpNexusQuery accountGrpNexusQuery) throws Exception;

  /**
   * 通过主键获取 账号分组 数据
   */
  public AccountGrpNexus getAccountGrpNexusByPk(String nexusId) throws Exception;

  /**
   * 获取 账号分组 所有数据
   */
  public Collection<AccountGrpNexus> getAllAccountGrpNexus() throws Exception;

  /**
   * 查询 账号分组 数据
   */
  public Collection<AccountGrpNexus> queryAccountGrpNexus(AccountGrpNexusQuery accountGrpNexusQuery) throws Exception;

  /**
   * 分页查询 账号分组 数据
   */
  public Paging<AccountGrpNexus> pagingQueryAccountGrpNexus(Param param, AccountGrpNexusQuery accountGrpNexusQuery) throws Exception;

  /**
   * 通过主键获取 账号分组 展示数据
   */
  public AccountGrpNexusVO getAccountGrpNexusVOByPk(String nexusId) throws Exception;

  /**
   * 获取 账号分组 所有展示数据
   */
  public Collection<AccountGrpNexusVO> getAllAccountGrpNexusVO() throws Exception;

  /**
   * 查询 账号分组 展示数据
   */
  public Collection<AccountGrpNexusVO> queryAccountGrpNexusVO(AccountGrpNexusQuery accountGrpNexusQuery) throws Exception;

  /**
   * 分页查询 账号分组 展示数据
   */
  public Paging<AccountGrpNexusVO> pagingQueryAccountGrpNexusVO(Param param, AccountGrpNexusQuery accountGrpNexusQuery) throws Exception;

}
