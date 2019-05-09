package cn.demo.chapter12.two;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.demo.chapter12.R;

/**
 * 项目名称：2018_AndroidStudio_ FirstLineOfCode_No.2
 * 类描述： RecyclerView 适配器类，让该类再继承 RecyclerView.Holder 类。
 * 创建人：ZJ
 * 创建时间：2019/4/30 13:57
 * 修改时间：2019/4/30
 * 修改备注：
 *
 * @version 1.0
 */
public class Demo04_FruitAdapter extends RecyclerView.Adapter<Demo04_FruitAdapter.ViewHolder> {
    private Context mContext;
    private List<Demo04_Fruit> demo04_fruitLists;

    /***
     * 【复用 findViewById()】
     * 创建 静态内部类，作用：避免多次创建 findViewById()
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

//        复用 findViewById()
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
//
            fruitImage = itemView.findViewById(R.id.c12d4_iv_iamge);
            fruitName = itemView.findViewById(R.id.c12d4_tv_name);
        }
    }


    public Demo04_FruitAdapter(List<Demo04_Fruit> d4_fruit){
        demo04_fruitLists = d4_fruit;
    }

    /***
     * 【首次加载布局，并复用converView对象】
     * 创建 ViewHolder的实例，将 fruit_item 的布局加载进来
     *  用于复用
     * @param parent 父布局文件
     * @param viewType view类型
     * @return
     */
    @NonNull
    @Override
    public Demo04_FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (mContext == null){
           mContext = parent.getContext();
       }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        /***
         * 处理 RecyclerView的点击事件
         */
//        首先 先获取 viewHolder为了复用缓存对象
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Demo04_Fruit demo04Fruit = demo04_fruitLists.get(position);
                Intent intent = new Intent(mContext, Demo05_FruitActivity.class);
                intent.putExtra(Demo05_FruitActivity.FRUIT_NAME, demo04Fruit.getName());
                intent.putExtra(Demo05_FruitActivity.IMAGE_ID, demo04Fruit.getImageId());
//               去启动 Demo05_FruitActivity
                mContext.startActivity(intent);
            }
        });
//        return new ViewHolder(view);
          return viewHolder;
    }

    /***
     * 【给每个子条目设置对应数据】
     * 用于将 RecyclerView子项的数据进行赋值，会在每个子项被滚动到屏幕内的时候执行。
     * 通过 get(position)来获取当前子项（每个子项）的实例。
     * @param holder 复用缓存对象
     * @param position 每个子项
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Demo04_Fruit demo04_fruit = demo04_fruitLists.get(position);
        holder.fruitName.setText(demo04_fruit.getName());
        /**
         *      Glide的.with()方法可以传入一个Context, Activity或者Fragment参数。然后
         * 调用.load()方法去加载图片，方法中可以是一个URL地址，也可以是一个本地路径，
         * 或一个资源id，最后调用.into()方法将图片设置到具体某一个ImageView中即可。
         *      之所以使用Gilde是因为这些图片的像素非常高，如果不进行压缩加载的话，容
         * 易导致内存溢出，而使用Gilde就不用担心这种情况，因为它内部做了很多复杂的逻辑
         * 操作，其中就包括图片的压缩。
         */
        Glide.with(mContext).load(demo04_fruit.getImageId()).into(holder.fruitImage);
    }

    /***
     * 【显示总条目数据】
     * 用于 获取一共有多少个子项，有list.size()来决定的。
     * @return
     */
    @Override
    public int getItemCount() {
        return demo04_fruitLists.size();
    }

}
