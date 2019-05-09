package cn.demo.chapter12.two;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import cn.demo.chapter12.one.Demo01_Activity;
import cn.demo.chapter12.one.Demo02_Activity;

/**
 * 项目名称：Demo03_Activity.java
 * 类描述： NavigationView, FloatingActionButton, Snackbar, CoordinatorLayout 的具体使用方式
 * FloatingActionButtom 的具体用法
 * 创建人：ZJ
 * 创建时间：2019/4/25 15:21
 * 修改时间：ZJ
 * 修改备注：
 */
public class Demo03_Activity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar c12d3_toolbar;
    private DrawerLayout drawer_layout3;
    private NavigationView c12d3_nav_view;
    private FloatingActionButton c12d3_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo03_activity);
        initView();
//       @@@ 1.0 首先使用这个方法将 Toolbar设置到 ActionBar标题栏
//            上，因为要替换原来的actionBar
        setSupportActionBar(c12d3_toolbar);
//       @@@ 2.0 使用 getSupportActionBar()方法获取到 ActionBar的实例。
        ActionBar actionBar = getSupportActionBar();
//        @@@ 3.0 如果这个actionBar 不为空
        if (actionBar != null) {
//           @@@ 3.1 让导航 设置出来。
            actionBar.setDisplayHomeAsUpEnabled(true);
//           @@@ 3.2 在设置一个 导航按钮图标。
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

//        $$$ 1.0 设置首选项为默认选中
        c12d3_nav_view.setCheckedItem(R.id.c12d3_menu_nav_call);
//        $$$ 2.0 设置菜单项选中事件的监听器，当用户点击了任意菜单项就会
//                回调 OnNavigationItemSelectedListener()方法中
        c12d3_nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.c12d3_menu_nav_call:
//                        Toast.makeText(Demo03_Activity.this, "Call", Toast.LENGTH_SHORT).show();
 /**                    点击某个侧面某个菜单项，可随意调整到一个活动中*/
                        Intent intent = new Intent(Demo03_Activity.this, Demo02_Activity.class);
                        startActivity(intent);
                        break;
                    case R.id.c12d3_menu_nav_friends:
/**                     点击某个侧面某个菜单项，可随意调整到一个活动中*/
                        Intent intent2 = new Intent(Demo03_Activity.this, Demo01_Activity.class);
                        startActivity(intent2);
                        break;
                    case R.id.c12d3_menu_nav_location:
                        Toast.makeText(Demo03_Activity.this, "Location", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.c12d3_menu_nav_mail:
                        Toast.makeText(Demo03_Activity.this, "Mail", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.c12d3_menu_nav_task:
                        Toast.makeText(Demo03_Activity.this, "Task", Toast.LENGTH_SHORT).show();
                        break;
                }
//                $$$ 2.1 选择某一个菜单项后，调用该方法 再将滑动菜单关闭。
                drawer_layout3.closeDrawers();
                return true;
            }
        });
    }

    private void initView() {
        c12d3_toolbar = (Toolbar) findViewById(R.id.c12d3_toolbar);
        drawer_layout3 = (DrawerLayout) findViewById(R.id.drawer_layout3);
        c12d3_nav_view = (NavigationView) findViewById(R.id.c12d3_nav_view);

        c12d3_fab = (FloatingActionButton) findViewById(R.id.c12d3_fab);
        c12d3_fab.setOnClickListener(this);
    }

    /***
     * ~~~~~ 1.0 使用该方法完成对 toolbar.xml文件的加载（主要是将 toolbar.xml中的布局加载到Toolbar上）
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /***
     * ~~~~~ 2.0 对 toobar.xml文件加载出的标题按钮 进行点击操作
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
//               @@@ 3.4 对 HomeAsUp按钮的点击事件进行处理，也就是导航按钮。
//                     注意：HomeAsUp按钮的点击事件 永远是 android.R.id.home
            case android.R.id.home:
//               @@@ 3.5 使用 openDrawer()方法将滑动菜单展现出来。
//                            该方法中需要传入一个参数，为了保证此时的行为和 布局文件中的行为一致，这
//                            里建议传入：GravityCompat.START，随系统设置的语言而变化。
                drawer_layout3.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.c12d3_fab:
//                Toast.makeText(this, "FloatingActionButton 被点击", Toast.LENGTH_SHORT).show();
                /***
                 * Snackbar 的使用方式
                 * 代码说明
                 * 调用 Snackbar的 .make()方法来创建一个 Snackbar对象，make()方法的第一个参数需要传入一个View对象，这个View对象可以是当前界面布局的任意一个View，Snackbar会使用这个 View来自动查找最外层的布局，用来展示 Snackbar控件。Snackbar的 make()的第二个参数用于显示内容， 表示该控件要做的事情，第三个参数用于显示该 Snackbar控件显示的时长。
                 * 这里有调用了 .setAction()方法来设置一个动作，从而让 Snackbar不仅仅是一个指示，而是可以和用户进行交互，让用户有一个可以 ‘误操作回滚’ 返回的 动作【按钮】。如果用户没有点击这个【动作按钮】，就执行 Snackbar本来要做的事情，如果用户点击了这个【动作按钮】，就能取消这个 Snackbar要本来要打算做的事情，也就是 ‘误操作回滚’ 功能。
                 * 在这个【动作按钮】的点击事件里再弹出一个 Toast吐司操作，最后同样调用 .show()方法让 Snackbar显示出来，提示用户已经做了 ‘误操作回滚’ 功能，即没有删除数据的功能。
                 */
                Snackbar.make(v, "删除数据", Snackbar.LENGTH_LONG)
                        .setAction("Undo按钮（取消删除操作）", new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(Demo03_Activity.this, "数据恢复，这条信息没被删除",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;
        }
    }
}
