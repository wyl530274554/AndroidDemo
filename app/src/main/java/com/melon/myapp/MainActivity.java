package com.melon.myapp;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.melon.myapp.functions.beacon.ShowBeaconsActivity;
import com.melon.myapp.functions.screen.PhoneDensityActivity;
import com.melon.myapp.functions.sensor.ShakeOneShakeActivity;
import com.melon.myapp.functions.ui.AppActionBarActivity;
import com.melon.myapp.functions.ui.DrawerLayoutActivity;
import com.melon.myapp.functions.ui.FlowLayoutActivity;
import com.melon.myapp.functions.ui.ProgressActivity;
import com.melon.myapp.functions.ui.StatusBarActivity;
import com.melon.myapp.functions.wifi.ShowWifiInfoActivity;
import com.melon.mylibrary.util.ToastUtil;
import com.melon.mylibrary.util.ViewHolder;
import com.melon.mylibrary.util.CommonUtil;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private String[] items = new String[]{"查看Wifi列表", "摇一摇", "Beacon",
            "屏幕分辨率", "进度条", "导航",
            "侧滑", "自动换行", "ActionBar",
            "沉浸式状态栏"};
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_main);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light);

        GridView gridView = (GridView) findViewById(R.id.gv_main);
        MyAdapter mAdapter = new MyAdapter();
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // wifi列表
                        CommonUtil.enterActivity(mContext, ShowWifiInfoActivity.class);
                        break;
                    case 1:
                        // 摇一摇
                        CommonUtil.enterActivity(mContext, ShakeOneShakeActivity.class);
                        break;
                    case 2:
                        //beacon
                        CommonUtil.enterActivity(mContext, ShowBeaconsActivity.class);
                        break;
                    case 3:
                        //屏幕分辨率
                        CommonUtil.enterActivity(mContext, PhoneDensityActivity.class);
                        break;
                    case 4:
                        //屏幕分辨率
                        CommonUtil.enterActivity(mContext, ProgressActivity.class);
                        break;
                    case 6:
                        //侧滑
                        CommonUtil.enterActivity(mContext, DrawerLayoutActivity.class);
                        break;
                    case 7:
                        //自动换行
                        CommonUtil.enterActivity(mContext, FlowLayoutActivity.class);
                        break;
                    case 8:
                        //ActionBar
                        CommonUtil.enterActivity(mContext, AppActionBarActivity.class);
                        break;
                    case 9:
                        //沉浸式状态栏
                        CommonUtil.enterActivity(mContext, StatusBarActivity.class);
                        break;
                }
            }

        });
    }

    @Override
    protected void initData() {
        showRefresh();
    }

    private void showRefresh() {
        //第一次显示下拉刷新
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        },200);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                ToastUtil.showShortToast(getApplicationContext(), "刷新完毕");
            }
        }, 2000);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_main, parent, false);
            }

            TextView tvName = ViewHolder.get(convertView, R.id.tv_item_main_name);
            tvName.setText(items[position]);
            if(position==5 || position==9)
                tvName.setTextColor(Color.RED);
            return convertView;
        }
    }
}
