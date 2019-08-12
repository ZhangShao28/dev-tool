package cn.moonnow.customer.management.rest;

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
import cn.moonnow.customer.management.entity.Customer;
import cn.moonnow.customer.management.query.CustomerQuery;
import cn.moonnow.customer.management.service.ICustomerService;
import cn.moonnow.customer.management.vo.CustomerVO;
import lombok.extern.log4j.Log4j2;

/**
 * 客户 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "customer/management/customer" })
public class CustomerController extends ToolController {

  public static final String LOG = "CustomerController";

  @Resource(name = "cn.moonnow.customer.management.CustomerService")
  private ICustomerService customerService;

  /**
   * 保存 客户 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<Customer> save(Param param, @RequestBody Customer customer) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customer);
    }
    Responses<Customer> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(customer)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(customerService.saveCustomer(customer));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 客户 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<Customer> batchSave(Param param, @RequestBody List<Customer> customers) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customers);
    }
    Responses<Customer> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(customers)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(customerService.batchSaveCustomer(customers));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 客户 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<Customer> update(Param param, @RequestBody Customer customer) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customer);
    }
    Responses<Customer> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(customer)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(customerService.updateCustomer(customer));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 客户 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<Customer> batchUpdate(Param param, @RequestBody List<Customer> customers) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customers);
    }
    Responses<Customer> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(customers)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(customerService.batchUpdateCustomer(customers));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 客户 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<Customer> remove(Param param, @RequestBody Customer customer) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customer);
    }
    Responses<Customer> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(customer)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      customerService.removeCustomer(customer);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 客户 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<Customer> batchRemove(Param param, @RequestBody List<Customer> customers) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customers);
    }
    Responses<Customer> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(customers)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      customerService.batchRemoveCustomer(customers);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 客户 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<Customer> getByPk(Param param, @RequestBody Customer customer) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customer);
    }
    Responses<Customer> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(customer)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(customer.getCustomerId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(customerService.getCustomerByPk(customer.getCustomerId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 客户 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<Customer> getAll(Param param, @RequestBody CustomerQuery customerQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customerQuery);
    }
    Responses<Customer> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(customerQuery)) {
        responses.setData(customerService.getAllCustomer());
      } else {
        responses.setData(customerService.queryCustomer(customerQuery));
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
   * 分页获取 客户 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<Customer> paging(Param param, @RequestBody CustomerQuery customerQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customerQuery);
    }
    Responses<Customer> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(customerQuery)) {
        responses.setData(customerService.pagingQueryCustomer(param, null));
      } else {
        responses.setData(customerService.pagingQueryCustomer(param, customerQuery));
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
   * 通过主键获取 客户 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<CustomerVO> getVOByPk(Param param, @RequestBody Customer customer) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customer);
    }
    Responses<CustomerVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(customer)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(customer.getCustomerId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(customerService.getCustomerVOByPk(customer.getCustomerId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 客户 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<CustomerVO> getAllVO(Param param, @RequestBody CustomerQuery customerQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customerQuery);
    }
    Responses<CustomerVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(customerQuery)) {
        responses.setData(customerService.getAllCustomerVO());
      } else {
        responses.setData(customerService.queryCustomerVO(customerQuery));
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
   * 分页获取 客户 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<CustomerVO> pagingVO(Param param, @RequestBody CustomerQuery customerQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + customerQuery);
    }
    Responses<CustomerVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(customerQuery)) {
        responses.setData(customerService.pagingQueryCustomerVO(param, null));
      } else {
        responses.setData(customerService.pagingQueryCustomerVO(param, customerQuery));
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
