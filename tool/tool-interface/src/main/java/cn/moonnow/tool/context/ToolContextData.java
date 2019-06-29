package cn.moonnow.tool.context;

/**
 * 全局共享变量</br>
 * 传统 http 请求</br>
 * 一次请求会创建一个线程</br>
 * 利用线程本地变量 做全局共享变量</br>
 * 支持并发 且不会造成共享变量冲突
 */
public final class ToolContextData {

  private static final ThreadLocal<TokenTimesEffect> tokenTimesEffectThreadLocal = new ThreadLocal<>();

  private static final ThreadLocal<CurrentLoginAccountInfo> currentLoginAccountInfoThreadLocal = new ThreadLocal<>();

  public static final TokenTimesEffect getTokenTimesEffect() {
    return (TokenTimesEffect) tokenTimesEffectThreadLocal.get();
  }

  public static final void setTokenTimesEffect(TokenTimesEffect tokenTimesEffect) {
    tokenTimesEffectThreadLocal.set(tokenTimesEffect);
  }

  public static final void removeTokenTimesEffect() {
    tokenTimesEffectThreadLocal.remove();
  }

  public static final CurrentLoginAccountInfo getCurrentLoginAccountInfo() {
    return (CurrentLoginAccountInfo) currentLoginAccountInfoThreadLocal.get();
  }

  public static final void setCurrentLoginAccountInfo(CurrentLoginAccountInfo currentLoginAccountInfo) {
    currentLoginAccountInfoThreadLocal.set(currentLoginAccountInfo);
  }

  public static final void removeCurrentLoginAccountInfo() {
    currentLoginAccountInfoThreadLocal.remove();
  }

}
