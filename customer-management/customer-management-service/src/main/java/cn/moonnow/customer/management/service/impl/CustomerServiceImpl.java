package cn.moonnow.customer.management.service.impl;

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
import cn.moonnow.customer.management.entity.Customer;
import cn.moonnow.customer.management.persistent.ICustomerPersistent;
import cn.moonnow.customer.management.query.CustomerQuery;
import cn.moonnow.customer.management.service.ICustomerService;
import cn.moonnow.customer.management.vo.CustomerVO;
import lombok.extern.log4j.Log4j2;

/**
 * 客户 服务实现
 */
@Log4j2
@Service("cn.moonnow.customer.management.CustomerService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = Exception.class)
public class CustomerServiceImpl implements ICustomerService {

  public static final String LOG = "CustomerService";

  @Resource(name = "cn.moonnow.customer.management.CustomerPersistent")
  private ICustomerPersistent customerPersistent;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Customer saveCustomer(Customer customer) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + customer);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(customer)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      customer.setCustomerId(ToolUtil.getUUID());
      return customerPersistent.saveCustomer(customer);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Customer> batchSaveCustomer(Collection<Customer> customers) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + customers);
    }
    try {
      if (ToolUtil.isEmpty(customers)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Customer customer : customers) {
        if (ToolUtil.isNullEntityAllFieldValue(customer)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        customer.setCustomerId(ToolUtil.getUUID());
      }
      return customerPersistent.batchSaveCustomer(customers);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Customer updateCustomer(Customer customer) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + customer);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(customer)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(customer.getCustomerId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Customer oldCustomer = customerPersistent.getCustomerByPk(customer.getCustomerId());
      if (ToolUtil.isNullEntityAllFieldValue(oldCustomer)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return customerPersistent.updateCustomer(customer);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public Collection<Customer> batchUpdateCustomer(Collection<Customer> customers) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + customers);
    }
    try {
      if (ToolUtil.isEmpty(customers)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      for (Customer customer : customers) {
        if (ToolUtil.isNullEntityAllFieldValue(customer)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(customer.getCustomerId())) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        Customer oldCustomer = customerPersistent.getCustomerByPk(customer.getCustomerId());
        if (ToolUtil.isNullEntityAllFieldValue(oldCustomer)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
      }
      return customerPersistent.batchUpdateCustomer(customers);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void removeCustomer(Customer customer) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + customer);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(customer)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Customer> customerSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(customer.getCustomerId())) {
        CustomerQuery customerQuery = new CustomerQuery();
        BeanUtils.copyProperties(customer, customerQuery);
        customerSet.addAll(customerPersistent.queryCustomer(customerQuery));
      } else {
        Customer oldCustomer = customerPersistent.getCustomerByPk(customer.getCustomerId());
        if (ToolUtil.isNullEntityAllFieldValue(oldCustomer)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        customerSet.add(oldCustomer);
      }
      if (ToolUtil.isNotEmpty(customerSet)) {
        customerPersistent.batchRemoveCustomer(customerSet);
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
  public void batchRemoveCustomer(Collection<Customer> customers) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + customers);
    }
    try {
      if (ToolUtil.isEmpty(customers)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      Set<Customer> customerSet = new LinkedHashSet<>();
      for (Customer customer : customers) {
        if (ToolUtil.isNullEntityAllFieldValue(customer)) {
          throw new ToolException(ToolException.E_PARAM_ERR);
        }
        if (ToolUtil.isNullStr(customer.getCustomerId())) {
          CustomerQuery customerQuery = new CustomerQuery();
          BeanUtils.copyProperties(customer, customerQuery);
          customerSet.addAll(customerPersistent.queryCustomer(customerQuery));
        } else {
          Customer oldCustomer = customerPersistent.getCustomerByPk(customer.getCustomerId());
          if (ToolUtil.isNullEntityAllFieldValue(oldCustomer)) {
            throw new ToolException(ToolException.E_PARAM_ERR);
          }
          customerSet.add(oldCustomer);
        }
      }
      if (ToolUtil.isNotEmpty(customerSet)) {
        customerPersistent.batchRemoveCustomer(customerSet);
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Long getCountCustomer(CustomerQuery customerQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + customerQuery);
    }
    try {
      return customerPersistent.getCountCustomer(customerQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Customer getCustomerByPk(String customerId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + customerId);
    }
    try {
      if (ToolUtil.isNullStr(customerId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return customerPersistent.getCustomerByPk(customerId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Customer> getAllCustomer() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return customerPersistent.getAllCustomer();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<Customer> queryCustomer(CustomerQuery customerQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + customerQuery);
    }
    try {
      return customerPersistent.queryCustomer(customerQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<Customer> pagingQueryCustomer(Param param, CustomerQuery customerQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customerQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return customerPersistent.pagingQueryCustomer(param, customerQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public CustomerVO getCustomerVOByPk(String customerId) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + customerId);
    }
    try {
      if (ToolUtil.isNullStr(customerId)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return customerPersistent.getCustomerVOByPk(customerId);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<CustomerVO> getAllCustomerVO() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
    }
    try {
      return customerPersistent.getAllCustomerVO();
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Collection<CustomerVO> queryCustomerVO(CustomerQuery customerQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + customerQuery);
    }
    try {
      return customerPersistent.queryCustomerVO(customerQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public Paging<CustomerVO> pagingQueryCustomerVO(Param param, CustomerQuery customerQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customerQuery);
    }
    try {
      if (ToolUtil.isNullEntityAllFieldValue(param)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      return customerPersistent.pagingQueryCustomerVO(param, customerQuery);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
