package po;

import java.util.Date;

public class Order {
    private Integer order_id;
    private Integer shop_id;
    private Integer user_id;
    private Integer state;
    private Double sum_money;
    private Integer pay_way;
    private String give_way;
    private Date create_time;

    /**
     * 获取支付方式  默认0在线支付  1货到付款
     * @return
     */
    public Integer getPay_way() {
        return pay_way;
    }

    /**
     * 设置支付方式  默认0在线支付  1货到付款
     * @return pay_way
     */
    public void setPay_way(Integer pay_way) {
        this.pay_way = pay_way;
    }

    /**
     * 获取配送方式
     * @return
     */
    public String getGive_way() {
        return give_way;
    }

    /**
     * 设置配送方式
     * @return give_way
     */
    public void setGive_way(String give_way) {
        this.give_way = give_way;
    }

    /**
     * 获取订单编号
     * @return
     */
    public Integer getOrder_id() {
        return order_id;
    }

    /**
     * 设置订单编号
     * @return order_id
     */
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    /**
     * 获取收货信息编号
     * @return
     */
    public Integer getShop_id() {
        return shop_id;
    }

    /**
     * 设置收货信息编号
     * @return shop_id
     */
    public void setShop_id(Integer shop_id) {
        this.shop_id = shop_id;
    }

    /**
     * 获取用户编号
     * @return
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * 设置用户编号
     * @return user_id
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取订单状态 0未付款 1已付款 2已发货 3已签收
     * @return
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置订单状态 0未付款 1已付款 2已发货 3已签收
     * @return state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取订单总金额
     * @return
     */
    public Double getSum_money() {
        return sum_money;
    }

    /**
     * 设置订单总金额
     * @return sum_money
     */
    public void setSum_money(Double sum_money) {
        this.sum_money = sum_money;
    }

    /**
     * 获取下单时间
     * @return
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 设置下单时间
     * @return create_time
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
