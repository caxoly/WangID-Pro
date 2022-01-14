package po;

import java.util.List;

public class GoodsType {
    private Integer type_id;
    private String type_name;
    private Integer f_id;
    List<GoodsType> list;

    public List<GoodsType> getList() {
        return list;
    }

    public void setList(List<GoodsType> list) {
        this.list = list;
    }

    /**
     * 获取商品类别编号
     * @return
     */
    public Integer getType_id() {
        return type_id;
    }

    /**
     * 设置商品类别编号
     * @param type_id
     */
    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    /**
     * 获取商品类型名称
     * @return
     */
    public String getType_name() {
        return type_name;
    }

    /**
     * 设置商品类型名称
     * @param type_name
     */
    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    /**
     * 获取商品父类id
     * @return
     */
    public Integer getF_id() {
        return f_id;
    }

    /**
     * 设置商品父类id
     * @param f_id
     */
    public void setF_id(Integer f_id) {
        this.f_id = f_id;
    }
}
