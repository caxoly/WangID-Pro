package service;

import dao.UserDao;
import po.Goods;
import po.GoodsType;
import po.User;

import java.util.List;

public class UserService {
    UserDao dao = new UserDao();

    /**
     * 用户注册
     * @param user
     * @return
     */
    public int register(User user){
        return dao.register(user);
    }

    /**
     * 查找数据库是否有重复的用户
     * @param user
     * @return
     */
    public int insert(User user){
        return dao.insert(user);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    public User login (User user ) {
        return dao.login(user);
    }

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    public int update(User user){return dao.update(user);}


    /**
     * 账号注册验证
     * @param user
     * @param pwd
     * @return
     */
    public String checkRegister(User user, String pwd){
        if (user.getUser_name().equals("")){
            return "用户名不能为空";
        }else if (!user.getPwd().equals(pwd)){
            return "两次密码要一致";
        }else if (user.getPwd().equals("")){
            return "密码不可为空";
        }else if (user.getUser_name().length()<2){
            return "用户名不能小于六位";
        }else if (user.getPwd().length()<6){
            return "密码不能小于六位";
        }
        return "";
    }


    /**
     * 管理员添加商品并写入商品分类详情
     * @param goods
     * @return
     */
    public int addGoods(Goods goods,String[] allId){
//        获取商品id
        int goods_id = dao.addGoods(goods);
//        获取要添加的all_id
        for (int i = 0; i < allId.length; i++) {
            dao.addTota(allId[i],goods_id);
        }
        return 1;
    }

    /**
     * 修改商品
     * @param goods
     * @return
     */
    public int updateGoods(Goods goods){
        return dao.updateGoods(goods);
    }

    /**
     * 查询所有的父类商品
     * @return
     */
    public List<GoodsType> findFtype(){
        return dao.findFtype();
    }

    /**
     * 获取所有子的商品类别
     * @return
     */
    public List<GoodsType> findType(int f_id) {
        return dao.findType(f_id);
    }

    /**
     * 账户充值 修改金钱
     * @param user
     * @param user_money
     * @return
     */
    public int updateUserMoney(User user,int user_money){
        return dao.updateUserMoney(user,user_money);
    }

    /**
     * 修改头像
     * @param user_id
     * @param user_url
     * @return
     */
    public int editUser(int user_id,String user_url){
        return dao.editUser(user_id,user_url);
    }

    /**
     * 查询购物车数量
     * @param user_id
     * @return
     */
    public int findCartNum(int user_id){
        return dao.findCartNum(user_id);
    }

    /**
     * 添加用户浏览表
     * @param goods_id
     * @param user_id
     * @return
     */
    public int look(int goods_id,int user_id){
        int look_id = dao.lookNum(goods_id,user_id);
        if (look_id>0){
            return dao.updateLookNum(look_id);
        }
        return dao.look(goods_id,user_id);
    }
}
