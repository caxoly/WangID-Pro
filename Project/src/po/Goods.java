package po;

import java.util.Date;

public class Goods {
    private Integer goods_id;
    private String goods_name;
    private String url;
    private String remake;
    private String key;
    private Integer sell_num;
    private Integer store_num;
    private Integer index;
    private Integer type_id;
    private Double price;
    private Date create_time;
    private String[] urls;

    private String type_name;

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    private Integer cart_id;
    private Integer num;

    private Integer love_id;
    private Integer love_state;
    private Date love_time;

    public Integer getLove_state() {
        return love_state;
    }

    public void setLove_state(Integer love_state) {
        this.love_state = love_state;
    }

    public Date getLove_time() {
        return love_time;
    }

    public void setLove_time(Date love_time) {
        this.love_time = love_time;
    }

    public Integer getLove_id() {
        return love_id;
    }

    public void setLove_id(Integer love_id) {
        this.love_id = love_id;
    }

    public Integer getCart_id() {
        return cart_id;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取商品编号
     * @return
     */
    public Integer getGoods_id() {
        return goods_id;
    }

    /**
     * 设置商品编号
     * @return goods_id
     */
    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    /**
     * 获取商品名称
     * @return
     */
    public String getGoods_name() {
        return goods_name;
    }

    /**
     * 设置商品名称
     * @return goods_name
     */
    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    /**
     * 获取商品图片
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置商品图片
     * @return url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取商品详情
     * @return
     */
    public String getRemake() {
        return remake;
    }

    /**
     * 设置商品详情
     * @return remake
     */
    public void setRemake(String remake) {
        this.remake = remake;
    }

    /**
     * 获取商品关键字
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置商品关键字
     * @return key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取商品销量
     * @return
     */
    public Integer getSell_num() {
        return sell_num;
    }

    /**
     * 设置商品销量
     * @return sell_num
     */
    public void setSell_num(Integer sell_num) {
        this.sell_num = sell_num;
    }

    /**
     * 获取商品库存
     * @return
     */
    public Integer getStore_num() {
        return store_num;
    }

    /**
     * 设置商品库存
     * @return store_num
     */
    public void setStore_num(Integer store_num) {
        this.store_num = store_num;
    }

    /**
     *  获取是否推荐到首页，1是 默认0
     *  @return
     */
    public Integer getIndex() {
        return index;
    }

    /**
     *  设置是否推荐到首页，1是 默认0
     *  @return index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 获取商品类型
     * @return
     */
    public Integer getType_id() {
        return type_id;
    }

    /**
     * 设置商品类型
     * @return type_id
     */
    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    /**
     * 获取商品价格
     * @return
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取创建时间
     * @return
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     *设置创建时间
     * @param create_time
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
