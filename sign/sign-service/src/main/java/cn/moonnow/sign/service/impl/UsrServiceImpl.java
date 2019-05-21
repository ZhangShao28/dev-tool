package cn.moonnow.sign.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.moonnow.sign.entity.Usr;
import cn.moonnow.sign.mapper.UsrMapper;
import cn.moonnow.sign.service.IUsrService;

@Service("cn.moonnow.sign.UsrService")
@Transactional(propagation = org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = java.lang.Exception.class)
public class UsrServiceImpl implements IUsrService {

  private static final Logger logger = LoggerFactory.getLogger(UsrServiceImpl.class);

  @Autowired
  private UsrMapper usrMapper;

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void saveUsr(Usr usr) throws Exception {
    if (logger.isDebugEnabled()) {
      logger.debug("Staring call SignService.saveUsr ");
      logger.debug("parameter usr is : " + usr);
    }
    try {
      usrMapper.insert(usr);
//      152f36c8-5fdd-4ab5-96ba-b6e6dad8728c
//      Usr aa = usrMapper.selectById("da8d6bca-6ac0-4278-8de3-844ac8be19f8");
//      System.out.println(aa);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error(e.getMessage(), e);
      }
    }
  }

}
