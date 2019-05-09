package cn.demo.chapter12.two;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.demo.chapter12.R;

public class Demo04_Activity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar c12d4_toolbar;
    private DrawerLayout c12d4_drawer_layout4;
    private NavigationView c12d4_nav_view;
    private FloatingActionButton c12d4_fab;

    /***
     * 活动的 中间部分
     * @param savedInstanceState
     */
//    &&& 1.0 定义数组，用来存放多个 Fruit实例。
    private Demo04_Fruit[] fruits = {new Demo04_Fruit("apple", R.drawable.apple), new Demo04_Fruit("banana", R.drawable.banana),
            new Demo04_Fruit("orange", R.drawable.orange), new Demo04_Fruit("watermelon", R.drawable.watermelon),
            new Demo04_Fruit("pear", R.drawable.pear), new Demo04_Fruit("grape", R.drawable.grape),
            new Demo04_Fruit("pineapple", R.drawable.pineapple), new Demo04_Fruit("strawberry", R.drawable.strawberry),
            new Demo04_Fruit("cherry", R.drawable.cherry), new Demo04_Fruit("mango", R.drawable.mango)};

    private List<Demo04_Fruit> fruitList = new ArrayList<>();
    private Demo04_FruitAdapter demo04FruitAdapter;
    private RecyclerView c12d4_recycler_view;
    private SwipeRefreshLayout c12d4_srl_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo04_activity);
        initView();
        initFruit();
        /***
         * %%% 3.0 填充布局 和设置 适配器
         */
//        %%% 3.1 创建 GridLayoutManager的构造函数（参数1：context 参数2：列数）
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//        %%% 3.2 将 gridLayoutManager 设置到 RecyclerView 中，这里使用的是 LinearLayout线性布局
        c12d4_recycler_view.setLayoutManager(gridLayoutManager);
//        %%% 3.3 创建 FruitAdapter 的实例
        demo04FruitAdapter = new Demo04_FruitAdapter(fruitList);
//        %%% 3.4 将 demo04FruitAdapter适配器设置 到 c12d4_recycler_view中去
        c12d4_recycler_view.setAdapter(demo04FruitAdapter);


//        @@@ 1.0 设置该Activity活动的ActionBar【这里使用Toolbar替换原有的 Actionbar】，因为Toolbar有
//        更多的功能
        setSupportActionBar(c12d4_toolbar);

//        ### 1.0 获取当前的 Toolbar
        ActionBar actionBar = getSupportActionBar();
//        ### 2.0 如果当前的 actionBar 不为空，就在标题栏上将 导航按钮设置出来
        if (actionBar != null) {
//            @@@ 2.1 就将导航按钮设置出来，实际上Toolbar最左侧的按钮就叫 HomeAsUp按钮。
            actionBar.setDisplayHomeAsUpEnabled(true);
//            @@@ 2.2 再设置一个导航图标，如果不指定，就是一个默认的 返回箭头，这里将默认按钮给修改了。
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        /***
         * $$$ NavigationView的代码处理
         */
//        $$$ 1.0 NavigationView的用法，它可以很好的优化和优美侧面滑动菜单
//        $$$ 1.1 设置默认首选项
        c12d4_nav_view.setCheckedItem(R.id.c12d3_menu_nav_call);
//        $$$ 1.2 设置NavigationView 的点击事件
        c12d4_nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.c12d3_menu_nav_call:
                        Intent intent = new Intent(Demo04_Activity.this, Demo03_Activity.class);
                        startActivity(intent);
                        break;
                }
//                无论选择哪个后，都将该 滑动菜单关闭
                c12d4_drawer_layout4.closeDrawers();
                return true;
            }
        });

//        ^^^ 1.0 设置下拉刷新进度条的颜色，和主题的颜色一致
        c12d4_srl_refresh.setColorSchemeResources(R.color.colorPrimary);
//        ^^^ 2.0 设置刷新的回调监听器，刷新后要做的事情
        c12d4_srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    /***
     * ^^^ 3.0 刷新后要做的事情，就去将水果图片重新加载一番，这里
     * 实际的操作是从网络上当下最新的数据
     */
    private void refreshFruits(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                   ^^^ 3.1 刷新后先让沉睡2秒后，再去做事情
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                ^^^ 3.2 让子线程切换到主线程上
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        ^^^ 3.3 重新加载一边 水果图片
                        initFruit();
//                        ^^^ 3.4 通知数据发生了变化
                        demo04FruitAdapter.notifyDataSetChanged();
//                        ^^^ 3.5 传入false，表示刷新事件结束，并隐藏刷新进度条
                        c12d4_srl_refresh.setRefreshing(false);
                    }
                });
            }
        }).start();
//
    }

    private void initView() {
//        Toolbar标题栏
        c12d4_toolbar = (Toolbar) findViewById(R.id.c12d4_toolbar);
//        DrawerLayout 侧面菜单布局（即包裹整个活动布局）
        c12d4_drawer_layout4 = (DrawerLayout) findViewById(R.id.c12d4_drawer_layout4);
//        NavigationView 优化侧面菜单
        c12d4_nav_view = (NavigationView) findViewById(R.id.c12d4_nav_view);
//        FloatingActionButton 悬浮按钮
        c12d4_fab = (FloatingActionButton) findViewById(R.id.c12d4_fab);
        c12d4_fab.setOnClickListener(this);
        c12d4_recycler_view = (RecyclerView) findViewById(R.id.c12d4_recycler_view);
        c12d4_recycler_view.setOnClickListener(this);
        c12d4_srl_refresh = (SwipeRefreshLayout) findViewById(R.id.c12d4_srl_refresh);
        c12d4_srl_refresh.setOnClickListener(this);
    }

    /***
     * @@@ 4.0 加载 toolbar.xml这个菜单文件，将其显示在 Toolbar标题栏上，也就是3个按钮
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /***
     * @@@ 5.0 对 Toolbar标题栏上的按钮
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "backup按钮被点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete按钮被点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "setting按钮被点击", Toast.LENGTH_SHORT).show();
                break;
            /***
             * ### 3.0 针对 导航按钮给出点击事件，即 HomeAsUp按钮
             *      切记：HomeAsUp按钮的id永远都是 android.R.id.home
             */
            case android.R.id.home:
//                ### 3.1 这里给出侧面菜单的展出方式，一般行为都是和xml中定义的一致。即：Gravity.START
                c12d4_drawer_layout4.openDrawer(Gravity.START);
                break;
        }
        return true;
    }

    /***
     * …… 1.0 FloationActionButton 悬浮按钮的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.c12d4_fab:
//                Toast.makeText(this, "我是 悬浮按钮：FloatingActionButton", Toast.LENGTH_SHORT).show();
                /***
                 * ^^^ 1.0 Snackbar 交互式提示工具。
                 * .make(, , ,)参数说明：
                 *      参数1：传入一个View，该View可以是当前界面布局的任意一个View，Snackbar会使用这个View来
                 *              自动查找最外层的布局
                 *      参数2：Snackbar 要显示的内容
                 *      参数3：Snackbar 显示的时长
                 *  .setAction(, ,)参数说明：设置一个动作，Snackbar不仅仅是一个指示，还可以和用户进行交互
                 *      参数1：虽然只是文本文字，但其实是一个Undo按钮，可以点击，点击它就可以取消 Snackbar本来要做的事情
                 *      参数2：对取消后的操作给出一个监听事件，可以弹出一句话，也可以是另外的操作。
                 *  .show()：将该交互式提示工具显示出来。
                 */
                Snackbar.make(v, "确定要删除这条数据吗？", Snackbar.LENGTH_LONG)
                        .setAction("取消删除操作", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(Demo04_Activity.this, "已经取消删除操作", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;
        }
    }

    /***
     * %%% 2.0 初始化 水果数据
     */
    private void initFruit() {
//        %%% 2.1 首先先请看一下 fruitList集合中的数据。
        fruitList.clear();
//        %%% 2.2 使用循环的方式，随机的从刚才定义的 水果的数组中挑选一个水果放到fruitList中，这
//            样每次打开的界面就不同了，为了让界面显示更多的水果，循环放入50个水果。
        for (int i = 0; i <= 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }
}
