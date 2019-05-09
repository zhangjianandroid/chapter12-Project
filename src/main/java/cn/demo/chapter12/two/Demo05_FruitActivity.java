package cn.demo.chapter12.two;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.demo.chapter12.R;
/**
 *项目名称：Demo05_FruitActivity.java
 *类描述： FruitActivity 界面的处理
 *创建人：ZJ
 *创建时间：2019/5/4 22:09
 *修改时间：ZJ
 *修改备注：
 *@version
 */
public class Demo05_FruitActivity extends AppCompatActivity {

    private ImageView c12d5_fruit_image_view;
    private CollapsingToolbarLayout c12d5_collapsing_toolbar;
    private AppBarLayout c12d5_appBar;
    private TextView c12d5_fruit_content_text;
    public static final String FRUIT_NAME = "fruit_name";
    public static final String IMAGE_ID = "fruit_image_id";
    private Toolbar c12d5_tololbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo05_activity_fruit);
//        1.0 获取 Demo04_FruitAdapter传递过来的 意图数据
        Intent intent = getIntent();
//        2.0 获取从 Demo04_FruitAdapter传递过来的数据，即：水果名称，水果图片ID
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(IMAGE_ID, 0);
//        3.0 初始化控件
        initView();
//        4.0 设置活动的 Toolbar
        setSupportActionBar(c12d5_tololbar);
//        5.0 先获取到这个 Toolbar，为了给他设置 导航图标
        ActionBar actionBar = getSupportActionBar();
//        5.1 如果 Toolbar不为空，就设置一个导航
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
//        6.0 设置 actionBar的标题
        c12d5_collapsing_toolbar.setTitle(fruitName);
//        7.0 显示图片：使用Glide加载传入的水果图片，并设置到标题栏的 ImageView上
        Glide.with(this).load(fruitImageId).into(c12d5_fruit_image_view);
//        8.0 显示文本内容：创建一个例子方法，用于循环显示水果的名字
        String fruitContent = generateFruitContent(fruitName);
//        8.1 再将水果的名字显示到 TextView中。
        c12d5_fruit_content_text.setText(fruitContent);
    }

    private void initView() {
        c12d5_fruit_image_view = (ImageView) findViewById(R.id.c12d5_fruit_image_view);
        c12d5_tololbar = (Toolbar) findViewById(R.id.c12d5_tololbar);
        c12d5_collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.c12d5_collapsing_toolbar);
        c12d5_appBar = (AppBarLayout) findViewById(R.id.c12d5_appBar);
        c12d5_fruit_content_text = (TextView) findViewById(R.id.c12d5_fruit_content_text);
    }

    /***
     * 随便一个例子方法，用于在 TextView中显示的内容
     * @param fruitName
     * @return
     */
    private String generateFruitContent(String fruitName){
        StringBuilder fruitContent = new StringBuilder();
        for (int i=0; i< 500; i++){
            fruitContent.append(fruitName+"   ");
        }
        return fruitContent.toString();
    }

    /***
     *  对 导航 进行处理，点击导航销毁（关闭）当前活动。
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
