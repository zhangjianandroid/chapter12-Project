package cn.demo.chapter12.one;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import cn.demo.chapter12.R;

/**
 *项目名称：Demo01_Activity.java
 *类描述： 对使用 Material Design 的一些控件和效果的实现
 *          Toolbar控件的实现，该控件可替换每个活动默认的最顶部的ActionBar标题栏
 *创建人：ZJ
 *创建时间：2019/4/15 18:24
 *修改时间：ZJ
 *修改备注：
 *@version
 */
public class Demo01_Activity extends AppCompatActivity {

    private Toolbar c12d1_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo01_activity);
//        初始化 Toolbar控件
        initView();
//        调用该方法将 Toolbar实例传入进去，就能使用Toolbar
        setSupportActionBar(c12d1_toolbar);
    }

    /***
     * 该方法 完成对 toolbar.xml文件的加载。
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /***
     * 该方法 用于对各种点击按钮的 处理。
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(this, "backUp 被点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete 被点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "setting 被点击", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void initView() {
        c12d1_toolbar = (Toolbar) findViewById(R.id.c12d1_toolbar);
    }
}
