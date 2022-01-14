package service;

import dao.OrderDao;
import po.*;

import java.util.Date;
import java.util.List;

public class OrderService {
    OrderDao dao = new OrderDao();

    /**
     * 查找省
     * @return
     */
    public List<China> findProvince(){
        return dao.findProvince();
    }

    /**
     * 通过省份id查找相应的城市
     * @return
     */
    public List<China> getCityByPid(int id){
        return dao.getCityByPid(id);
    }

    /**
     * 添加收货地址
     * @param shop
     * @return
     */
    public int address(Shop shop){
        return dao.address(shop);
    }

    /**
     * 用过用户id获得所有的地址信息
     * @param user_id
     * @return
     */
    public List<Shop> getSiteById(int user_id){
        return dao.getSiteById(user_id);
    }

    /**
     * 通过商品id获取相关数据
     * @param ids
     * @return
     */
    public List<Goods> getMyCart(String ids){
        List<Goods> list = dao.getMyCart(ids);
        String[] showUrl = null;
        for (Goods goods : list) {
            String url = goods.getUrl();
            showUrl = url.split(",");
            goods.setUrl(showUrl[0]);
        }
        return list;
    }

    /**
     * 立即购买创建订单
     * @param order
     * @return
     */
    public Order rightBuy(Order order,int goods_id) {
        order.setState(0);
        order.setCreate_time(new Date());
//        创建订单并返回订单id
        int order_id = dao.createOrder(order);
        //订单详情信息
        dao.createOrderDetail(order.getUser_id(),order_id,goods_id,1,order.getSum_money());
        //把订单信息返回给客户端
        order.setOrder_id(order_id);
        return order;
    }

    /**
     * 创建订单 并写入订单详情
     * @param order
     * @param ids
     * @return
     */
    public Order createOrder(Order order,String ids) {
        double sum = 0;
        List<Goods> list=dao.getMyCart(ids);
        for(Goods goods :list){
            sum += (goods.getPrice()*goods.getNum());
        }
        order.setState(0);
        order.setCreate_time(new Date());
        order.setSum_money(sum);
        int order_id = dao.createOrder(order);
        //订单详情信息
        for (Goods goods : list) {
            dao.createOrderDetail(order.getUser_id(),order_id,goods.getGoods_id(),goods.getNum(),sum);
        }
        //删除购物车数据
        dao.deleteCart(ids);
        //把订单信息返回给客户端
        order.setOrder_id(order_id);
        return order;
    }

    /**
     * 支付金额修改用户余额
     * @param user_id
     * @param sum_money
     * @return
     */
    public int setUserMoney(int user_id,double sum_money){
        return dao.setUserMoney(user_id,sum_money);
    }

    /**
     * 支付成功修改订单信息并修改库存
     * @param order_id
     * @return
     */
    public int setState(int order_id){
//        修改订单状态
        dao.setState(order_id);
//        根据订单编号查询商品编号
        int goods_id = dao.getGoodsByOrderId(order_id);
//        根据商品id查询商品的库存
        int store_num = dao.getGoodsById(goods_id);
//        如果库存大于一则减一，等于一则删除
        if (store_num>1){
            return dao.updateStoreNum(goods_id);
        }else {
            return dao.deleteGoods(goods_id);
        }

    }

    /**
     * 查询用户所有的订单信息
     * @param user_id
     * @return
     */
    public List<MyOrder> showOrder(int user_id){
        List<MyOrder> list = dao.showOrder(user_id);
        String[] showUrl = null;
        for (MyOrder order : list) {
            String url = order.getGoods_url();
            showUrl = url.split(",");
            order.setGoods_url(showUrl[0]);
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
        List<MyOrder> list = dao.showStateOrder(user_id,state);
        String[] showUrl = null;
        for (MyOrder order : list) {
            String url = order.getGoods_url();
            showUrl = url.split(",");
            order.setGoods_url(showUrl[0]);
        }
        return list;
    }

    /**
     * 删除订单时修改订单详情表的状态
     * @param order_id
     * @return
     */
    public int setShow(int order_id){
        return dao.setShow(order_id);
    }

    /**
     * 删除用户地址
     * @param order
     * @return
     */
    public int delete(Order order){ return dao.delete(order); }

    /**
     * 修改用户地址
     * @param shop
     * @return
     */
    public int update(Shop shop){ return dao.update(shop); }
}

