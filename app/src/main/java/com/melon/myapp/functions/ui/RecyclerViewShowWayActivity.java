package com.melon.myapp.functions.ui;

import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.melon.myapp.BaseActivity;
import com.melon.myapp.R;
import com.melon.myapp.adapter.ItemAdapter;
import com.melon.myapp.bean.Item;

import java.util.ArrayList;
import java.util.List;

import static com.melon.myapp.adapter.ItemAdapter.SPAN_COUNT_ONE;
import static com.melon.myapp.adapter.ItemAdapter.SPAN_COUNT_THREE;

/**
 * 网格切换
 */
public class RecyclerViewShowWayActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<Item> items;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_recycler_view_show_way);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        initItemsData();

        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT_ONE);
        itemAdapter = new ItemAdapter(items, gridLayoutManager);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
    private void initItemsData() {
        items = new ArrayList<>(4);
        items.add(new Item(R.drawable.img1, "Image 1", 20, 33));
        items.add(new Item(R.drawable.img2, "Image 2", 10, 54));
        items.add(new Item(R.drawable.img3, "Image 3", 27, 20));
        items.add(new Item(R.drawable.img4, "Image 4", 45, 67));
    }
    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meun_main_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_switch_layout) {
            switchLayout();
            switchIcon(item);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void switchLayout() {
        if (gridLayoutManager.getSpanCount() == SPAN_COUNT_ONE) {
            gridLayoutManager.setSpanCount(SPAN_COUNT_THREE);
        } else {
            gridLayoutManager.setSpanCount(SPAN_COUNT_ONE);
        }
        itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
    }

    private void switchIcon(MenuItem item) {
        if (gridLayoutManager.getSpanCount() == SPAN_COUNT_THREE) {
            item.setIcon(getResources().getDrawable(R.drawable.ic_span_3));
        } else {
            item.setIcon(getResources().getDrawable(R.drawable.ic_span_1));
        }
    }
}