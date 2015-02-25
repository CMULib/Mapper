import java.io.Serializable;
/**
 * Created by yiranfei on 2/24/15.
 */
public class SlaveInfo implements Serializable {
  public long mUsedMemory;
  public long mFreeMemory;
  public long mTotalMemory;
  public long mMaxMemory;

  public SlaveInfo() {
    Runtime runtime = Runtime.getRuntime();

    mUsedMemory = runtime.totalMemory() - runtime.freeMemory();
    mFreeMemory = runtime.freeMemory();
    mTotalMemory = runtime.totalMemory();
    mMaxMemory = runtime.maxMemory();
  }
}
