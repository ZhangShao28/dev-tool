package cn.moonnow.sign.rest;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.sign.entity.Usr;
import cn.moonnow.sign.service.IUsrService;
import cn.moonnow.tool.rest.ToolController;
import cn.moonnow.tool.util.ToolUtil;

@RestController
@Scope("prototype")
@RequestMapping(value = { "sign/usr" })
public class UsrController extends ToolController {

  private static final Logger logger = LoggerFactory.getLogger(UsrController.class);

  @Resource(name = "cn.moonnow.sign.UsrService")
  private IUsrService usrService;

  @GetMapping(value = { "cs" })
  public Map<String, Object> cs() throws Exception {
    if (logger.isDebugEnabled()) {
      logger.debug("Staring call UsrController.cs ");
    }
    LinkedHashMap<String, Object> responses = new LinkedHashMap<String, Object>();
    try {
      Usr usr = new Usr();
      usr.setUsrId(ToolUtil.getUUID());
      usr.setUsrNo("cs用户002");
      usr.setUsrType("A");
      usr.setUsrName("姓名002");
      usr.setVersion(1);
      usr.setCreateId("1");
      usr.setCreateAcc("1");
      usr.setCreateName("1");
      usrService.saveUsr(usr);
      responses.put("msg", "成功");
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error(e.getMessage(), e);
      }
    }
    return responses;
  }

}
