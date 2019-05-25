package cn.moonnow.sign.rest;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.sign.entity.Usr;
import cn.moonnow.sign.service.IUsrService;
import cn.moonnow.tool.rest.Responses;
import cn.moonnow.tool.rest.ToolController;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "sign/usr" })
public class UsrController extends ToolController {

  @Resource(name = "cn.moonnow.sign.UsrService")
  private IUsrService usrService;

  @PostMapping(value = { "cs" })
  public Responses<?> cs(@RequestBody Usr usr) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("Staring call UsrController.cs ");
      log.debug("parameter usr is : " + usr);
    }
    Responses<?> responses = new Responses<>();
    try {
      usr.setUsrId(ToolUtil.getUUID());
      usr.setUsrNo("cs用户002");
      usr.setUsrType("A");
      usr.setUsrName("姓名002");
      usr.setVersion(1);
      usr.setCreateId("1");
      usr.setCreateAcc("1");
      usr.setCreateName("1");
      usrService.saveUsr(usr);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

}
