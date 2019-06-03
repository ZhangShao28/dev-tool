//package cn.moonnow.dict.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import cn.moonnow.dict.entity.Dict;
//import cn.moonnow.dict.mapper.DictMapper;
//import cn.moonnow.dict.service.IDictService;
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
//@Service("cn.moonnow.dict.DictService")
//@Transactional(propagation = org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = java.lang.Exception.class)
//public class DictServiceImpl implements IDictService {
//
//  @Autowired
//  private DictMapper dictMapper;
//
//  @Override
//  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
//  public void saveDict(Dict dict) throws Exception {
//    if (log.isDebugEnabled()) {
//      log.debug("Staring call DictService.saveUsr ");
//      log.debug("parameter dict is : " + dict);
//    }
//    try {
////      usrMapper.insert(usr);
////      152f36c8-5fdd-4ab5-96ba-b6e6dad8728c
////      Usr aa = usrMapper.selectById("da8d6bca-6ac0-4278-8de3-844ac8be19f8");
////      System.out.println();
////      throw new ToolException(ToolException.E_PARAM_ERR);
////      throw new ToolException("sadasdasd");
//    } catch (Exception e) {
//      if (log.isErrorEnabled()) {
//        log.error(e.getMessage(), e);
//      }
//      throw e;
//    }
//  }
//
//}
