package com.yc.demo.stickyindexview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yc.stickyindexview.StickyIndexView;
import com.yc.stickyindexview.ilstener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener<StarBean> {

    private List<StarBean> mList;
    private StickyIndexView mStickyIndexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        TestData();
        initView();
    }

    private void TestData() {
        mList = new ArrayList<>();
        StarBean dehua = new StarBean("刘德华", "关注");
        mList.add(dehua);
        StarBean lihong = new StarBean("王力宏", "未关注");
        mList.add(lihong);
        StarBean zhao = new StarBean("赵子龙", "关注");
        mList.add(zhao);
        StarBean teshu = new StarBean("@#((**", "关注");
        mList.add(teshu);
    }

    private void initView() {
        mStickyIndexView = findViewById(R.id.stickyIndexView);
        mStickyIndexView.addItemDecoration(new StickyIndexViewDecoration(this));
        StarAdapter mAdapter = new StarAdapter(this, mList);
        mAdapter.setOnItemClickListener(this);
        mStickyIndexView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View childView, int position, StarBean item) {
        Toast.makeText(this, item.getName(), Toast.LENGTH_SHORT).show();
    }
}
