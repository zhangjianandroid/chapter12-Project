package cn.demo.chapter12.two;

/**
 * 项目名称：2018_AndroidStudio_ FirstLineOfCode_No.2
 * 类描述：
 * 创建人：ZJ
 * 创建时间：2019/4/30 3:07
 * 修改时间：2019/4/30
 * 修改备注：
 *
 * @version 1.0
 */
public class Demo04_Fruit {
    public Demo04_Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    private int imageId;

}
