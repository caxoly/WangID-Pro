package po;

import java.util.Date;

public class Shop {
    private Integer shop_id;
    private Integer p_id;
    private Integer user_id;
    private Integer c_id;
    private Integer d_id;
    private String shop_name;
    private String shop_phone;
    private String province;
    private String city;
    private String district;
    private String address;
    private Date create_time;

    /**
     * 获取收货信息编号
     * @return
     */
    public Integer getShop_id() {
        return shop_id;
    }

    /**
     * 设置收货信息编号
     * @param shop_id
     */
    public void setShop_id(Integer shop_id) {
        this.shop_id = shop_id;
    }

    /**
     * 获取地址(省)编号，外键，地址表
     * @return
     */
    public Integer getP_id() {
        return p_id;
    }

    /**
     * 设置地址(省)编号，外键，地址表
     * @param p_id
     */
    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    /**
     * 获取用户编号，外键，用户表
     * @return
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * 设置用户编号，外键，用户表
     * @param user_id
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取地址(市)编号，外键，地址表
     * @return
     */
    public Integer getC_id() {
        return c_id;
    }

    /**
     * 设置地址(市)编号，外键，地址表
     * @param c_id
     */
    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    /**
     * 获取地址(区)编号，外键，地址表
     * @return
     */
    public Integer getD_id() {
        return d_id;
    }

    /**
     * 设置地址(区)编号，外键，地址表
     * @param d_id
     */
    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    /**
     * 获取收货人姓名
     * @return
     */
    public String getShop_name() {
        return shop_name;
    }

    /**
     * 设置收货人姓名
     * @param shop_name
     */
    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    /**
     * 获取收货人电话
     * @return
     */
    public String getShop_phone() {
        return shop_phone;
    }

    /**
     * 设置收货人电话
     * @param shop_phone
     */
    public void setShop_phone(String shop_phone) {
        this.shop_phone = shop_phone;
    }

    /**
     * 获取省
     * @return
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区/县
     * @return
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置区/县
     * @param district
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取详细地址
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取创建时间
     * @return
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 设置创建时间
     * @param create_time
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
