package cn.moonnow.tool.context;

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
