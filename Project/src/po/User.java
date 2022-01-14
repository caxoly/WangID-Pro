package po;

public class User {
    private Integer user_id;
    private Integer sex;
    private Integer age;
    private Integer user_type;
    private String user_name;
    private String pwd;
    private String like_name;
    private String image_url;
    private Double user_money;

    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取用户余额
     * @return
     */
    public Double getUser_money() {
        return user_money;
    }

    /**
     * 设置用户余额
     * @param user_money
     */
    public void setUser_money(Double user_money) {
        this.user_money = user_money;
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
     * @param user_id
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * 获取性别 0男 1女
     * @return
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别 0男 1女
     * @return sex
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     * @return
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     * @return age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取用户类型 0用户 1管理员
     * @return
     */
    public Integer getUser_type() {
        return user_type;
    }

    /**
     * 设置用户类型 0用户 1管理员
     * @return user_type
     */
    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    /**
     * 获取用户名
     * @return
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * 设置用户名
     * @return user_name
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * 获取登陆密码
     * @return
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置登陆密码
     * @return pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取昵称
     * @return
     */
    public String getLike_name() {
        return like_name;
    }

    /**
     * 设置昵称
     * @return like_name
     */
    public void setLike_name(String like_name) {
        this.like_name = like_name;
    }

    /**
     * 获取头像图片
     * @return
     */
    public String getImage_url() {
        return image_url;
    }

    /**
     * 设置头像图片
     * @return image_url
     */
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
