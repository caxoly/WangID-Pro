package dao;

import po.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends BaseDao {
    /**
     * 查找省
     * @return
     */
    public List<China> findProvince(){
        String sql = "SELECT * FROM china WHERE Pid=0";
        List<China> list = new ArrayList<>();
        China china = null;
        JDBC jdbc = executeQuery(sql);
        if (jdbc.rs!=null){
            try {
                while (jdbc.rs.next()) {
                    china = new China();
                    china.setId(jdbc.rs.getInt("Id"));
                    china.setName(jdbc.rs.getString("Name"));
                    china.setPid(jdbc.rs.getInt("Pid"));
                    china.setLevel(jdbc.rs.getInt("level"));
                    list.add(china);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return list;
    }

    /**
     * 通过省份id查找相应的城市
     * @return
     */
    public List<China> getCityByPid(int id){
        String sql = "SELECT * FROM china WHERE Pid=?";
        List<China> list = new ArrayList<>();
        China china = null;
        JDBC jdbc = executeQuery(sql,id);
        if (jdbc.rs!=null){
            try {
                while (jdbc.rs.next()) {
                    china = new China();
                    china.setId(jdbc.rs.getInt("Id"));
                    china.setName(jdbc.rs.getString("Name"));
                    china.setLevel(jdbc.rs.getInt("level"));
                    list.add(china);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return list;
    }

    /**
     * 添加收货地址
     * @param shop
     * @return
     */
    public int address(Shop shop){
        String sql = "INSERT INTO tbl_shop(user_id,p_id,shop_name,shop_phone,province,city,district,address,create_time,c_id,d_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        return executeUpdate(sql,shop.getUser_id(),shop.getP_id(),shop.getShop_name(),shop.getShop_phone(),shop.getProvince(),shop.getCity(),shop.getDistrict(),shop.getAddress(),shop.getCreate_time(),shop.getC_id(),shop.getD_id());
    }

    /**
     * 用过用户id获得所有的地址信息
     * @param user_id
     * @return
     */
    public List<Shop> getSiteById(int user_id){
        String sql = "SELECT * FROM tbl_shop Where user_id=?";
        List<Shop> list = new ArrayList<>();
        Shop shop = null;
        JDBC jdbc = executeQuery(sql,user_id);
        if (jdbc.rs!=null){
            try {
                while (jdbc.rs.next()) {
                    shop = new Shop();
                    shop.setShop_id(jdbc.rs.getInt("shop_id"));
                    shop.setShop_name(jdbc.rs.getString("shop_name"));
                    shop.setP_id(jdbc.rs.getInt("p_id"));
                    shop.setUser_id(jdbc.rs.getInt("user_id"));
                    shop.setShop_phone(jdbc.rs.getString("shop_phone"));
                    shop.setProvince(jdbc.rs.getString("province"));
                    shop.setCity(jdbc.rs.getString("city"));
                    shop.setDistrict(jdbc.rs.getString("district"));
                    shop.setAddress(jdbc.rs.getString("address"));
                    shop.setCreate_time(jdbc.rs.getDate("create_time"));
                    shop.setC_id(jdbc.rs.getInt("c_id"));
                    shop.setD_id(jdbc.rs.getInt("d_id"));
                    list.add(shop);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return list;
    }

    /**
     * 通过商品id获取相关数据
     * @param ids
     * @return
     */
    public List<Goods> getMyCart(String ids){
        String sql = "select * from tbl_goods g join tbl_cart c on g.goods_id=c.goods_id where c.cart_id in("+ids+")";
        List<Goods> list=new ArrayList<>();
        Goods goods=null;
        JDBC jdbc=executeQuery(sql);
        if(jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    goods=new Goods();
                    goods.setCart_id(jdbc.rs.getInt("cart_id"));
                    goods.setNum(jdbc.rs.getInt("num"));
                    goods.setGoods_id(jdbc.rs.getInt("goods_id"));
                    goods.setGoods_name(jdbc.rs.getString("goods_name"));
                    goods.setUrl(jdbc.rs.getString("url"));
                    goods.setPrice(jdbc.rs.getDouble("price"));
                    goods.setSell_num(jdbc.rs.getInt("sell_num"));
                    goods.setStore_num(jdbc.rs.getInt("store_num"));
                    goods.setType_id(jdbc.rs.getInt("type_id"));
                    goods.setRemake(jdbc.rs.getString("remake"));
                    goods.setKey(jdbc.rs.getString("key"));
                    goods.setIndex(jdbc.rs.getInt("index"));
                    list.add(goods);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return list;
    }

    /**
     * 写入订单表并获取它的订单id
     * @param order
     * @return
     */
    public int createOrder(Order order){
        String sql = "insert into tbl_order(shop_id,user_id,state,sum_money,pay_way,give_way,create_time) values(?,?,?,?,?,?,?)";
        return executeUpdateGetId(sql,order.getShop_id(),order.getUser_id(),order.getState(),order.getSum_money(),order.getPay_way(),order.getGive_way(),order.getCreate_time());
    }


    /**
     * 写入订单详情表
     * @return
     */
    public int createOrderDetail(int user_id,int order_id,int goods_id,int goods_num,double goods_price){
        String sql = "insert into tbl_orderdetail(user_id,order_id,goods_id,goods_num,goods_price) values(?,?,?,?,?)";
        return executeUpdate(sql,user_id,order_id,goods_id,goods_num,goods_price);
    }

    /**
     * 写入订单表后删除购物车数据
     * @param ids
     * @return
     */
    public int deleteCart(String ids){
        String sql = "delete from tbl_cart where cart_id in("+ids+")";
        return executeUpdate(sql);
    }

    /**
     * 支付金额修改用户余额
     * @param user_id
     * @param sum_money
     * @return
     */
    public int setUserMoney(int user_id,double sum_money){
        String sql = "update tbl_user set user_money=user_money-? where user_id=?";
        return executeUpdate(sql,sum_money,user_id);
    }

    /**
     * 支付成功修改订单信息
     * @param order_id
     * @return
     */
    public int setState(int order_id){
        String sql = "update tbl_order set state=1 where order_id=?";
        return executeUpdate(sql,order_id);
    }

    /**
     * 根据订单编号查询商品编号
     * @param order_id
     * @return
     */
    public int getGoodsByOrderId(int order_id){
        String sql = "SELECT goods_id FROM tbl_orderdetail WHERE order_id=?";
        JDBC jdbc = executeQuery(sql,order_id);
        int goods_id = 0;
        if (jdbc.rs!=null){
            try {
                if (jdbc.rs.next()){
                    goods_id =jdbc.rs.getInt("goods_id");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return goods_id;
    }

    /**
     * 根据商品id查询商品信息
     * @param goods_id
     * @return
     */
    public int getGoodsById(int goods_id){
        String sql = "select * from tbl_goods where goods_id=?";
        JDBC jdbc = executeQuery(sql,goods_id);
        int store_num = 0;
        if (jdbc.rs!=null){
            try {
                if (jdbc.rs.next()){
                    store_num = jdbc.rs.getInt("store_num");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return store_num;
    }

    /**
     * 修改库存
     * @param goods_id
     * @return
     */
    public int updateStoreNum(int goods_id){
        String sql = "update tbl_goods set store_num=store_num-1 where goods_id=?";
        return executeUpdate(sql,goods_id);
    }

    /**
     * 没有库存了删除商品
     * @param goods_id
     * @return
     */
    public int deleteGoods(int goods_id){
        String sql = "delete from tbl_goods where goods_id=?";
        return executeUpdate(sql,goods_id);
    }

    /**
     * 查询用户所有的订单信息
     * @param user_id
     * @return
     */
    public List<MyOrder> showOrder(int user_id){
        String sql = "select o.order_id,o.sum_money,o.pay_way,o.state,o.create_time,g.url,g.goods_name,od.goods_num,s.shop_name from " +
                " tbl_goods g right join tbl_orderdetail od on g.goods_id=od.goods_id " +
                " join tbl_order o on o.order_id=od.order_id left join tbl_shop s on s.shop_id=o.shop_id " +
                " where o.user_id=? and od.`show`=0";
        List<MyOrder> list=new ArrayList<>();
        MyOrder myOrder=null;
        JDBC jdbc=executeQuery(sql,user_id);
        if(jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    myOrder=new MyOrder();
                    myOrder.setOrder_id(jdbc.rs.getInt("o.order_id"));
                    myOrder.setPay_way(jdbc.rs.getInt("o.pay_way"));
                    myOrder.setState(jdbc.rs.getInt("o.state"));
                    myOrder.setGoods_url(jdbc.rs.getString("g.url"));
                    myOrder.setGoods_name(jdbc.rs.getString("g.goods_name"));
                    myOrder.setSum_money(jdbc.rs.getDouble("o.sum_money"));
                    myOrder.setGoods_num(jdbc.rs.getInt("od.goods_num"));
                    myOrder.setShop_name(jdbc.rs.getString("s.shop_name"));
                    myOrder.setCreate_time(jdbc.rs.getDate("o.create_time"));
                    list.add(myOrder);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return list;

    }

    /**
     * 显示不同状态的订单
     * @param user_id
     * @param state
     * @return
     */
    public List<MyOrder> showStateOrder(int user_id,int state){
        String sql = "select o.order_id,o.sum_money,o.pay_way,o.state,o.create_time,g.url,g.goods_name,od.goods_num,s.shop_name from " +
                " tbl_goods g right join tbl_orderdetail od on g.goods_id=od.goods_id " +
                " join tbl_order o on o.order_id=od.order_id left join tbl_shop s on s.shop_id=o.shop_id " +
                " where o.user_id=? and od.`show`=0 and o.state=?";
        List<MyOrder> list=new ArrayList<>();
        MyOrder myOrder=null;
        JDBC jdbc=executeQuery(sql,user_id,state);
        if(jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    myOrder=new MyOrder();
                    myOrder.setOrder_id(jdbc.rs.getInt("o.order_id"));
                    myOrder.setPay_way(jdbc.rs.getInt("o.pay_way"));
                    myOrder.setState(jdbc.rs.getInt("o.state"));
                    myOrder.setGoods_url(jdbc.rs.getString("g.url"));
                    myOrder.setGoods_name(jdbc.rs.getString("g.goods_name"));
                    myOrder.setSum_money(jdbc.rs.getDouble("o.sum_money"));
                    myOrder.setGoods_num(jdbc.rs.getInt("od.goods_num"));
                    myOrder.setShop_name(jdbc.rs.getString("s.shop_name"));
                    myOrder.setCreate_time(jdbc.rs.getDate("o.create_time"));
                    list.add(myOrder);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return list;

    }

    /**
     * 删除订单时修改订单详情表的状态
     * @param order_id
     * @return
     */
    public int setShow(int order_id){
        String sql = "update tbl_orderdetail set `show`=1 where order_id=?";
        return executeUpdate(sql,order_id);
    }

    /**
     * 删除用户地址
     * @param order
     * @return
     */
    public int delete(Order order){
        String sql = "DELETE FROM tbl_shop WHERE shop_id=?";
        return executeUpdate(sql,order.getShop_id());
    }

    /**
     * 修改用户地址
     * @param shop
     * @return
     */
    public int update(Shop shop){
        String sql = "update tbl_shop set shop_name=?,shop_phone=?,province=?,city=?,district=?,address=? where shop_id=?";
        return executeUpdate(sql,shop.getShop_name(),shop.getShop_phone(),shop.getProvince(),shop.getCity(),shop.getDistrict(),shop.getAddress(),shop.getShop_id());
    }

}
