package com.kevin.android.android.hilibrary.log;

/**
 * Author：kevin
 * Time：2021/2/14 1:55
 * Description：
 */
public class HiThreadFormatter implements HiLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread: " + data.getName();
    }
}
