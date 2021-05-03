package com.kevin.android.android.hilibrary.log;

import androidx.annotation.NonNull;

/**
 * Author：kevin
 * Time：2021/2/14 1:52
 * Description：
 */
public interface HiLogPrinter {
    void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString);
}
