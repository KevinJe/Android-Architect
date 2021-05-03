package com.kevin.android.android.hilibrary.log;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kevin.android.android.hilibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：kevin
 * Time：2021/2/14 12:21
 * Description：将View显示出来。
 */
public class HiViewPrinter implements HiLogPrinter {
    private RecyclerView recyclerView;
    private LogAdapter adapter;
    private HiViewPrinterProvider viewPrinterProvider;

    public HiViewPrinter(Activity activity) {
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        recyclerView = new RecyclerView(activity);
        adapter = new LogAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        viewPrinterProvider = new HiViewPrinterProvider(rootView, recyclerView);
    }

    /**
     * 获取HiViewPrinterProvider，通过HiViewPrinterProvider可以控制log视图的展示和隐藏
     * @return
     */
    public HiViewPrinterProvider getViewPrinterProvider() {
        return viewPrinterProvider;
    }

    @Override
    public void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString) {
        // 将log展示添加到recyclerView中
        adapter.addItem(new HiLogMo(System.currentTimeMillis(), level, tag, printString));
        // recyclerView滚动
        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    private static class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {
        private List<HiLogMo> logs = new ArrayList<>();

        public void addItem(HiLogMo logItem) {
            logs.add(logItem);
            notifyItemInserted(logs.size() - 1);
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hilog_item, parent, false);
            return new LogViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            HiLogMo logItem = logs.get(position);

            int color = getHighlightColor(logItem.level);
            holder.tagView.setTextColor(color);
            holder.messageView.setTextColor(color);

            holder.tagView.setText(logItem.getFlattened());
            holder.messageView.setText(logItem.log);
        }

        @Override
        public int getItemCount() {
            return logs.size();
        }

        private int getHighlightColor(int logLevel) {
            int highlight;
            switch (logLevel) {
                case HiLogType.V:
                    highlight = 0XFFBBBBBB;
                    break;
                case HiLogType.D:
                    highlight = 0XFFFFFFFF;
                    break;
                case HiLogType.I:
                    highlight = 0XFF6A8759;
                    break;
                case HiLogType.W:
                    highlight = 0XFFBBB529;
                    break;
                case HiLogType.E:
                    highlight = 0XFFFF6B68;
                    break;
                default:
                    highlight = 0XFFBBBBBB;
                    break;
            }
            return highlight;
        }
    }

    private static class LogViewHolder extends RecyclerView.ViewHolder {
        TextView tagView;
        TextView messageView;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tagView = (TextView) itemView.findViewById(R.id.tv_tag);
            messageView = (TextView) itemView.findViewById(R.id.tv_message);
        }
    }
}
