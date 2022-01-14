package dao;


import po.Goods;
import po.GoodsType;
import po.JDBC;
import po.User;
import sun.swing.plaf.GTKKeybindings;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao {
    /**
     * 用户注册
     * @param user
     * @return
     */
    public int register(User user){
        String sql = "insert into tbl_user(user_name,like_name,pwd,user_money) values(?,?,?,?)";
        return executeUpdate(sql,user.getUser_name(),user.getLike_name(),user.getPwd(),user.getUser_money());
    }

    /**
     * 查找数据库是否有重复的用户
     * @param user
     * @return
     */
    public int insert(User user){
        String sql = "select count(1) as count from tbl_user where user_name=?";
        JDBC jdbc = executeQuery(sql,user.getUser_name());
        int count = 0;
        if(jdbc.rs!=null){
            try {
                if(jdbc.rs.next()){
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
     * 登录
     * @param user
     * @return
     */
    public User login(User user){
        String sql = "select * from tbl_user where user_name=? and pwd=? limit 1";
        JDBC jdbc = executeQuery(sql,user.getUser_name(),user.getPwd());
        if(jdbc.rs!=null){
            try {
                if(jdbc.rs.next()){
                    user.setUser_id(jdbc.rs.getInt("user_id"));
                    user.setLike_name(jdbc.rs.getString("like_name"));
                    user.setImage_url(jdbc.rs.getString("image_url"));
                    user.setSex(jdbc.rs.getInt("sex"));
                    user.setAge(jdbc.rs.getInt("age"));
                    user.setUser_type(jdbc.rs.getInt("user_type"));
                    user.setUser_money(jdbc.rs.getDouble("user_money"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return user;
    }

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    public int update(User user){
        String sql = "update tbl_user set like_name=?,age=?,sex=? where user_id=?";
        return executeUpdate(sql,user.getLike_name(),user.getAge(),user.getSex(),user.getUser_id());
    }


    /**
     * 管理员添加商品并返回商品id
     * @param goods
     * @return
     */
    public int addGoods(Goods goods){
        String sql = "insert into tbl_goods(goods_name,url,price,sell_num,store_num,remake,`index`,`key`,create_time,type_id) values(?,?,?,?,?,?,?,?,?,?)";
        return executeUpdateGetId(sql,goods.getGoods_name(),goods.getUrl(),goods.getPrice(),goods.getSell_num(),goods.getStore_num(),goods.getRemake(),goods.getIndex(),goods.getKey(),goods.getCreate_time(),goods.getType_id());
    }

    /**
     * 添加商品分类详情
     * @param all_id
     * @param goods_id
     * @return
     */
    public int addTota(String all_id,int goods_id){
        String sql = "insert into tbl_tota(all_id,goods_id) values(?,?)";
        return executeUpdate(sql,all_id,goods_id);
    }

    /**
     * 查询所有的父类商品
     * @return
     */
    public List<GoodsType> findFtype(){
        String sql = "select * from tbl_type where f_id=0";
        List<GoodsType> list = new ArrayList<>();
        GoodsType gt = null;
        JDBC jdbc = executeQuery(sql);
        if (jdbc.rs!=null){
            try {
                while (jdbc.rs.next()) {
                    gt = new GoodsType();
                    gt.setType_id(jdbc.rs.getInt("type_id"));
                    gt.setType_name(jdbc.rs.getString("type_name"));
                    gt.setF_id(jdbc.rs.getInt("f_id"));
                    list.add(gt);
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
     * 获取所有子的商品类别
     * @return
     */
    public List<GoodsType> findType(int f_id){
        String sql = "SELECT * FROM tbl_type WHERE f_id=?";
        List<GoodsType> list = new ArrayList<>();
        GoodsType gt = null;
        JDBC jdbc = executeQuery(sql,f_id);
        if (jdbc.rs!=null){
            try {
                while (jdbc.rs.next()) {
                    gt = new GoodsType();
                    gt.setType_id(jdbc.rs.getInt("type_id"));
                    gt.setType_name(jdbc.rs.getString("type_name"));
                    gt.setF_id(jdbc.rs.getInt("f_id"));
                    list.add(gt);
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
     * 账户充值 修改金钱
     * @param user
     * @param user_money
     * @return
     */
    public int updateUserMoney(User user,int user_money){
        String sql = "update tbl_user set user_money=user_money+? where user_name=? and pwd=? ";
        return executeUpdate(sql,user_money,user.getUser_name(),user.getPwd());
    }

    /**
     * 修改头像
     * @param user_id
     * @param user_url
     * @return
     */
    public int editUser(int user_id,String user_url){
        String sql="update tbl_user set image_url=? where user_id=?";
        return executeUpdate(sql,user_url,user_id);
    }

    /**
     * 查询购物车数量
     * @param user_id
     * @return
     */
    public int findCartNum(int user_id){
        String sql = "select count(1) as count from tbl_cart where user_id=? and state=0";
        JDBC jdbc = executeQuery(sql,user_id);
        int count = 0;
        if(jdbc.rs!=null){
            try {
                if(jdbc.rs.next()){
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
     * 添加用户浏览表
     * @param goods_id
     * @param user_id
     * @return
     */
    public int look(int goods_id,int user_id){
        String sql = "insert into tbl_look(goods_id,user_id,look_num) values(?,?,1)";
        return executeUpdate(sql,goods_id,user_id);
    }

    /**
     * 查询是否曾经浏览过并返回浏览次数
     * @param goods_id
     * @param user_id
     * @return
     */
    public int lookNum(int goods_id,int user_id){
        String sql = "select * from tbl_look where goods_id=? and user_id=?";
        JDBC jdbc = executeQuery(sql,goods_id,user_id);
        int look_id = 0;
        if(jdbc.rs!=null){
            try {
                if(jdbc.rs.next()){
                    look_id = jdbc.rs.getInt("look_id");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return look_id;
    }

    /**
     * 如果用户曾经浏览过则添加浏览数量
     * @param look_id
     * @return
     */
    public int updateLookNum(int look_id){
        String sql = "update tbl_look set look_num=look_num+1 where look_id=?";
        return executeUpdate(sql,look_id);
    }

    /**
     * 修改商品
     * @param goods
     * @return
     */
    public int updateGoods(Goods goods){
        String sql = "update tbl_goods set goods_name=?,url=?,price=?,sell_num=?,store_num=?,remake=?,`index`=?,`key`=?,type_id=? where goods_id=?";
        return executeUpdate(sql,goods.getGoods_name(),goods.getUrl(),goods.getPrice(),goods.getSell_num(),goods.getStore_num(),goods.getRemake(),goods.getIndex(),goods.getKey(),goods.getType_id(),goods.getGoods_id());
    }
}
