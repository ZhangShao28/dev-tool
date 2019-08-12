package cn.moonnow.customer.management.service;

import java.util.Collection;

import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.customer.management.entity.Customer;
import cn.moonnow.customer.management.query.CustomerQuery;
import cn.moonnow.customer.management.vo.CustomerVO;

/**
 * 客户 服务接口
 */
public interface ICustomerService {

  /**
   * 保存 客户 信息
   */
  public Customer saveCustomer(Customer customer) throws Exception;

  /**
   * 批量保存 客户 信息
   */
  public Collection<Customer> batchSaveCustomer(Collection<Customer> customers) throws Exception;

  /**
   * 修改 客户 信息
   */
  public Customer updateCustomer(Customer customer) throws Exception;

  /**
   * 批量修改 客户 信息
   */
  public Collection<Customer> batchUpdateCustomer(Collection<Customer> customers) throws Exception;

  /**
   * 删除 客户 信息
   */
  public void removeCustomer(Customer customer) throws Exception;

  /**
   * 批量删除 客户 信息
   */
  public void batchRemoveCustomer(Collection<Customer> customers) throws Exception;

  /**
   * 获取 客户 数据量
   */
  public Long getCountCustomer(CustomerQuery customerQuery) throws Exception;

  /**
   * 通过主键获取 客户 数据
   */
  public Customer getCustomerByPk(String customerId) throws Exception;

  /**
   * 获取 客户 所有数据
   */
  public Collection<Customer> getAllCustomer() throws Exception;

  /**
   * 查询 客户 数据
   */
  public Collection<Customer> queryCustomer(CustomerQuery customerQuery) throws Exception;

  /**
   * 分页查询 客户 数据
   */
  public Paging<Customer> pagingQueryCustomer(Param param, CustomerQuery customerQuery) throws Exception;

  /**
   * 通过主键获取 客户 展示数据
   */
  public CustomerVO getCustomerVOByPk(String customerId) throws Exception;

  /**
   * 获取 客户 所有展示数据
   */
  public Collection<CustomerVO> getAllCustomerVO() throws Exception;

  /**
   * 查询 客户 展示数据
   */
  public Collection<CustomerVO> queryCustomerVO(CustomerQuery customerQuery) throws Exception;

  /**
   * 分页查询 客户 展示数据
   */
  public Paging<CustomerVO> pagingQueryCustomerVO(Param param, CustomerQuery customerQuery) throws Exception;

}
