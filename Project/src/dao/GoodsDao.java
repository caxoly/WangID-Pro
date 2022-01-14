package dao;

import po.Goods;
import po.JDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao extends BaseDao {
    /**
     * 通过商品id查询到该商品
     * @param goods_id
     * @return
     */
    public Goods getGoodsById(int goods_id){
        String sql = "select * from tbl_goods where goods_id=?";
        JDBC jdbc = executeQuery(sql,goods_id);
        Goods goods = null;
        if (jdbc.rs!=null){
            try {
                if (jdbc.rs.next()){
                    goods = new Goods();
                    goods.setGoods_id(jdbc.rs.getInt("goods_id"));
                    goods.setGoods_name(jdbc.rs.getString("goods_name"));
                    goods.setUrl(jdbc.rs.getString("url"));
                    goods.setPrice(jdbc.rs.getDouble("price"));
                    goods.setSell_num(jdbc.rs.getInt("sell_num"));
                    goods.setStore_num(jdbc.rs.getInt("store_num"));
                    goods.setRemake(jdbc.rs.getString("remake"));
                    goods.setIndex(jdbc.rs.getInt("index"));
                    goods.setKey(jdbc.rs.getString("key"));
                    goods.setCreate_time(jdbc.rs.getDate("create_time"));
                    goods.setType_id(jdbc.rs.getInt("type_id"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return goods;
    }

    /**
     * 查找商品并排序
     * @return
     */
    public List<Goods> getGoodsBySell(){
        String sql = "select * from tbl_goods order by `index` desc, sell_num desc limit 30";
        List<Goods> list = new ArrayList<>();
        Goods goods=null;
        JDBC jdbc=executeQuery(sql);
        if(jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    goods=new Goods();
                    goods.setGoods_id(jdbc.rs.getInt("goods_id"));
                    goods.setGoods_name(jdbc.rs.getString("goods_name"));
                    goods.setUrl(jdbc.rs.getString("url"));
                    goods.setPrice(jdbc.rs.getDouble("price"));
                    goods.setSell_num(jdbc.rs.getInt("sell_num"));
                    goods.setStore_num(jdbc.rs.getInt("store_num"));
                    goods.setIndex(jdbc.rs.getInt("index"));
                    goods.setKey(jdbc.rs.getString("key"));
                    goods.setType_id(jdbc.rs.getInt("type_id"));
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
     * 获取某用户的所有商品信息
     * @param user_id
     * @return
     */
    public List<Goods> getCartByUserId(int user_id){
        String sql="select g.*,c.* from tbl_goods g join tbl_cart c on g.goods_id=c.goods_id where c.user_id=? and state=0";
        List<Goods> list=new ArrayList<>();
        Goods goods=null;
        JDBC jdbc=executeQuery(sql,user_id);
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
                    goods.setIndex(jdbc.rs.getInt("index"));
                    goods.setKey(jdbc.rs.getString("key"));
                    goods.setType_id(jdbc.rs.getInt("type_id"));
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
     * 通过card_id删除购物车内的商品
     * @param cart_id
     * @return
     */
    public int deleteCart(int cart_id){
        String sql="delete from tbl_cart where cart_id=?";
        return executeUpdate(sql,cart_id);
    }

    /**
     * 通过card_id修改商品购买数量
     * @param cart_id
     * @param num
     * @return
     */
    public int opreateCartNum(int num,int cart_id){
        String sql="update tbl_cart set num=num+? where cart_id=?";
        return executeUpdate(sql,num,cart_id);
    }

    /**
     * 先查询当前用户是否以存在当前商品
     * @param user_id
     * @param goods_id
     * @return
     */
    public int selectAllGoods(int user_id,int goods_id){
        String sql = "select cart_id from tbl_cart where user_id=? and goods_id=?";
        JDBC jdbc = executeQuery(sql,user_id,goods_id);
        int cart_id = 0;
        if (jdbc.rs!=null){
            try {
                if (jdbc.rs.next()){
                    cart_id =jdbc.rs.getInt("cart_id");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return cart_id;
    }

    public int updateCartId(int cart_id){
        String sql = "update tbl_cart set num=num+1 where cart_id=?";
        return executeUpdate(sql,cart_id);
    }

    /**
     * 将商品加入购物车
     * @param goods_id
     * @param user_id
     * @return
     */
    public int addCartId(int goods_id, int user_id){
        String sql = "insert into tbl_cart(goods_id,user_id,num) values(?,?,1)";
        return executeUpdate(sql,goods_id,user_id);
    }

    /**
     * 通过user_id查询某用户收藏的所有商品
     * @param user_id
     * @return
     */
    public List<Goods> getLoveByUser(int user_id) {
        String sql = "select * from tbl_goods g join tbl_love l on g.goods_id=l.goods_id where user_id=? and state=1";
        List<Goods> list = new ArrayList<>();
        Goods goods = null;
        JDBC jdbc = executeQuery(sql, user_id);
        if (jdbc.rs != null) {
            try {
                while (jdbc.rs.next()){
                    goods = new Goods();
                    goods.setGoods_id(jdbc.rs.getInt("goods_id"));
                    goods.setLove_id(jdbc.rs.getInt("love_id"));
                    goods.setUrl(jdbc.rs.getString("url"));
                    goods.setGoods_name(jdbc.rs.getString("goods_name"));
                    goods.setPrice(jdbc.rs.getDouble("price"));
                    goods.setLove_state(jdbc.rs.getInt("state"));
                    goods.setLove_time(jdbc.rs.getDate("love_time"));
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
     * 通过love_id取消收藏
     * @param love_id
     * @return
     */
    public int notLove(int love_id){
        String sql="update tbl_love set state=0 where love_id=?";
        return executeUpdate(sql,love_id);
    }

    /**
     * 根据用户和商品编号查询该用户是否收藏或收藏过此商品
     * @param user_id
     * @param goods_id
     * @return
     */
    public List<Goods> loveID(int user_id,int goods_id){
        String sql = "select * from tbl_love where user_id=? and goods_id=?";
        JDBC jdbc = executeQuery(sql,user_id,goods_id);
        List<Goods> list = new ArrayList<>();
        Goods goods = null;
        if (jdbc.rs != null) {
            try {
                while (jdbc.rs.next()){
                    goods = new Goods();
                    goods.setLove_id(jdbc.rs.getInt("love_id"));
                    goods.setLove_state(jdbc.rs.getInt("state"));
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
     * 添加收藏
     * @param goods_id
     * @param user_id
     * @return
     */
    public int loveGoods(int goods_id,int user_id){
        String sql="insert into tbl_love(goods_id,user_id,state,love_time) values(?,?,1,now())";
        return executeUpdate(sql,goods_id,user_id);
    }

    /**
     * 如果曾经收藏过，后来又取消了，应该修改其状态
     * @param love_id
     * @return
     */
    public int updateLoveGoods(int love_id){
        String sql="update tbl_love set state=1 where love_id=?";
        return executeUpdate(sql,love_id);
    }

    /**
     * 通过关键字查询用户可能喜欢的数据
     * @param key
     * @return
     */
    public List<Goods> getGoodsKey(String key){
        String sql = "select * from tbl_goods where `key` like CONCAT('%',?,'%') order by `index` desc, sell_num desc limit 6 ";
        List<Goods> list = new ArrayList<>();
        Goods goods=null;
        JDBC jdbc=executeQuery(sql,key);
        if(jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    goods=new Goods();
                    goods.setGoods_id(jdbc.rs.getInt("goods_id"));
                    goods.setGoods_name(jdbc.rs.getString("goods_name"));
                    goods.setUrl(jdbc.rs.getString("url"));
                    goods.setPrice(jdbc.rs.getDouble("price"));
                    goods.setSell_num(jdbc.rs.getInt("sell_num"));
                    goods.setStore_num(jdbc.rs.getInt("store_num"));
                    goods.setIndex(jdbc.rs.getInt("index"));
                    goods.setKey(jdbc.rs.getString("key"));
                    goods.setType_id(jdbc.rs.getInt("type_id"));
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
     * 最近浏览前五条
     * @param user_id
     * @return
     */
    public List<Goods> recentLook(int user_id){
        String sql = "select * from tbl_goods g join tbl_look l on g.goods_id=l.goods_id where l.user_id=? order by l.look_num desc limit 5";
        List<Goods> list = new ArrayList<>();
        Goods goods=null;
        JDBC jdbc=executeQuery(sql,user_id);
        if(jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    goods=new Goods();
                    goods.setGoods_id(jdbc.rs.getInt("goods_id"));
                    goods.setGoods_name(jdbc.rs.getString("goods_name"));
                    goods.setUrl(jdbc.rs.getString("url"));
                    goods.setPrice(jdbc.rs.getDouble("price"));
                    goods.setSell_num(jdbc.rs.getInt("sell_num"));
                    goods.setStore_num(jdbc.rs.getInt("store_num"));
                    goods.setIndex(jdbc.rs.getInt("index"));
                    goods.setKey(jdbc.rs.getString("key"));
                    goods.setType_id(jdbc.rs.getInt("type_id"));
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
     * 显示所有最近浏览记录
     * @param user_id
     * @return
     */
    public List<Goods> findLook(int user_id){
        String sql = "select * from tbl_goods g join tbl_look l on g.goods_id=l.goods_id where l.user_id=? ";
        List<Goods> list = new ArrayList<>();
        Goods goods=null;
        JDBC jdbc=executeQuery(sql,user_id);
        if(jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    goods=new Goods();
                    goods.setGoods_id(jdbc.rs.getInt("goods_id"));
                    goods.setGoods_name(jdbc.rs.getString("goods_name"));
                    goods.setUrl(jdbc.rs.getString("url"));
                    goods.setPrice(jdbc.rs.getDouble("price"));
                    goods.setSell_num(jdbc.rs.getInt("sell_num"));
                    goods.setStore_num(jdbc.rs.getInt("store_num"));
                    goods.setIndex(jdbc.rs.getInt("index"));
                    goods.setKey(jdbc.rs.getString("key"));
                    goods.setType_id(jdbc.rs.getInt("type_id"));
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
     * 查询商品总条数
     * @return
     */
    public int findGoodsCount(){
        String sql = "SELECT count(1) as count FROM tbl_goods";
        JDBC jdbc = executeQuery(sql);
        int count = 0;
        if (jdbc.rs != null) {
            try {
                while (jdbc.rs.next()){
                    count = jdbc.rs.getInt("count");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return count;
    }

    /**
     * 商品分页显示
     * @param start
     * @param end
     * @return
     */
    public List<Goods> getAllGoods(int start,int end){
        String sql = "SELECT * FROM tbl_goods limit ?,?";
        List<Goods> list = new ArrayList<>();
        JDBC jdbc = executeQuery(sql,start,end);
        Goods goods = null;
        if (jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    goods = new Goods();
                    goods.setGoods_id(jdbc.rs.getInt("goods_id"));
                    goods.setGoods_name(jdbc.rs.getString("goods_name"));
                    goods.setUrl(jdbc.rs.getString("url"));
                    goods.setPrice(jdbc.rs.getDouble("price"));
                    goods.setSell_num(jdbc.rs.getInt("sell_num"));
                    goods.setStore_num(jdbc.rs.getInt("store_num"));
                    goods.setRemake(jdbc.rs.getString("remake"));
                    goods.setIndex(jdbc.rs.getInt("index"));
                    goods.setKey(jdbc.rs.getString("key"));
                    goods.setCreate_time(jdbc.rs.getDate("create_time"));
                    goods.setType_id(jdbc.rs.getInt("type_id"));
                    list.add(goods);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return list;
    }

    /**
     * 管理员删除商品
     * @param goods_id
     * @return
     */
    public int deleteGoods(int goods_id){
        String sql = "delete from tbl_goods where goods_id=?";
        return executeUpdate(sql,goods_id);
    }

    /**
     * 查询商品的所有信息
     * @param goods_id
     * @return
     */
    public Goods findGoods(int goods_id){
        String sql = "select * from tbl_goods g join tbl_type t on g.type_id=t.type_id where goods_id=?";
        JDBC jdbc = executeQuery(sql,goods_id);
        Goods goods = null;
        if (jdbc.rs!=null){
            try {
                if (jdbc.rs.next()){
                    goods = new Goods();
                    goods.setGoods_id(jdbc.rs.getInt("goods_id"));
                    goods.setUrl(jdbc.rs.getString("url"));
                    goods.setPrice(jdbc.rs.getDouble("price"));
                    goods.setGoods_name(jdbc.rs.getString("goods_name"));
                    goods.setStore_num(jdbc.rs.getInt("store_num"));
                    goods.setRemake(jdbc.rs.getString("remake"));
                    goods.setIndex(jdbc.rs.getInt("index"));
                    goods.setKey(jdbc.rs.getString("key"));
                    goods.setSell_num(jdbc.rs.getInt("sell_num"));
                    goods.setCreate_time(jdbc.rs.getDate("create_time"));
                    goods.setType_id(jdbc.rs.getInt("type_id"));
                    goods.setType_name(jdbc.rs.getString("type_name"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return goods;
    }

}
