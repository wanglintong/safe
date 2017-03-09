package cn.com.zlqf.safe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.zlqf.safe.R;

/**
 * Created by Administrator on 2017/3/9.
 */
public class HomeActivity extends AppCompatActivity{
    private GridView gv_home;
    private String[] mTitles;
    private int[] mImgs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initUI();
        initData();
    }

    private void initData() {
        mTitles = new String[]{
                "手机防盗","通信卫视","软件管理","进程管理","流量统计","手机杀毒","缓存清理","高级工具","设置中心"
        };
        mImgs = new int[] {
                R.mipmap.home_safe,R.mipmap.home_callmsgsafe,
                R.mipmap.home_apps,R.mipmap.home_taskmanager,
                R.mipmap.home_netmanager,R.mipmap.home_trojan,
                R.mipmap.home_sysoptimize,R.mipmap.home_tools,
                R.mipmap.home_settings
        };
        gv_home.setAdapter(new MyAdapter());
        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 8:
                        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void initUI() {
        gv_home = (GridView) findViewById(R.id.gv_home);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public Object getItem(int position) {
            return mTitles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getApplicationContext(), R.layout.gridview_item, null);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_title.setText(mTitles[position]);
            iv_icon.setImageResource(mImgs[position]);

            return view;
        }
    }
}
