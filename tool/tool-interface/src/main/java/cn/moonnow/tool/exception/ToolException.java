package cn.moonnow.tool.exception;

public class ToolException extends Exception {

  private static final long serialVersionUID = 1L;

  public static final String E_ERR = "服务器内部错误，无法完成请求。";

  public static final String E_PARAM_ERR = "参数错误。";

  protected String errMsg;

  public ToolException(String errMsg) {
    super(errMsg);
    this.errMsg = errMsg;
  }

  public String getErrMsg() {
    return errMsg;
  }

}
