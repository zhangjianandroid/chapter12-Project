package cn.demo.chapter12.one;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cn.demo.chapter12.R;

/**
 * 项目名称：Demo02_Activity.java
 * 类描述： Metarial Designs 控件效果下的 DrawerLayout 滑动菜单的用法
 * 创建人：ZJ
 * 创建时间：2019/4/15 18:48
 * 修改时间：ZJ
 * 修改备注：
 */
public class Demo02_Activity extends AppCompatActivity {

    private Toolbar c12d2_toolbar;
    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo02_activity);
        initView();
//        # 1.0 调用该方法将 Toolbar实例传入进去
        setSupportActionBar(c12d2_toolbar);
//        @@@ 1.0 首先调用 getSupportActionBar()方法获得ActionBar的实例，虽然整个 ActionBar的实例是通
//            过Toolbar实现的。
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
//            @@@ 2.0 让导航显示出来。
            actionBar.setDisplayHomeAsUpEnabled(true);
//            @@@ 3.0 再设置一个 导航按钮图标。
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    /***
     * ………… 1.0  该方法 完成对 toolbar.xml文件的加载。
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }


    /***
     *  ………… 2.0 该方法 用于对各种点击按钮的 处理。
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;
//            4.0 对 HomeAsUp按钮的点击事件进行处理，也就是导航按钮。
//                  HomeAsUp按钮的id永远是 android.R.id.home
//                  然后调用 DrawerLayout的 openDrawer()方法将滑动菜单展示出来。
//                  注意：openDrawer()需要传入一个 Gravity参数，为了保证这里的行为和活动布局xml中定义的一致，建议
//                  传入：GravityCompat.START
            case android.R.id.home:
                drawer_layout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    private void initView() {
        c12d2_toolbar = (Toolbar) findViewById(R.id.c12d2_toolbar);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }
}
