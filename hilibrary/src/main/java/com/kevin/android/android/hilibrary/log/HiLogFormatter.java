package com.kevin.android.android.hilibrary.log;

/**
 * Author：kevin
 * Time：2021/2/14 1:53
 * Description：
 */
public interface HiLogFormatter<T> {
    String format(T data);
}
