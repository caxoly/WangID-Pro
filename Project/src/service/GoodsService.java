package service;

import dao.GoodsDao;
import po.Goods;
import util.Page;

import java.util.List;

public class GoodsService {
    GoodsDao dao = new GoodsDao();
    public Goods getGoodsById(int goods_id){
        Goods goods = dao.getGoodsById(goods_id);
        String url = goods.getUrl();
        goods.setUrls(url.split(","));
        return goods;
    }

    public List<Goods> getGoodsBySell(){
        return dao.getGoodsBySell();
    }

    public List<Goods> getCartByUserId(int user_id){
        List<Goods> list = dao.getCartByUserId(user_id);
        String[] showUrl = null;
        for (Goods goods : list) {
            String url = goods.getUrl();
            showUrl = url.split(",");
            goods.setUrl(showUrl[0]);
        }
        return list;
    }

    public int deleteCart(int cart_id){
        return dao.deleteCart(cart_id);
    }

    public int opreateCartNum(int num,int cart_id){
        return dao.opreateCartNum(num,cart_id);
    }

    public int addCart(int user_id,int goods_id){
        //先查询当前用户是否以存在当前商品
        int cart_id =  dao.selectAllGoods(user_id,goods_id);
        //若存在 修改数量
        if (cart_id>0){
            int result =  dao.updateCartId(cart_id);
            if (result > 0) {
                //1则表示修改成功
                return 1;
            }else {
                //0则表示修改失败
                return 0;
            }
        }else {
            //不存在就添加
            int result = dao.addCartId(goods_id,user_id);
            if (result>0){
                //2则表示添加成功
                return 2;
            } else {
                //0则表示添加失败
                return 0;
            }
        }

    }

    public List<Goods> getLoveByUser(int user_id){
        List<Goods> list = dao.getLoveByUser(user_id);
        String[] showUrl = null;
        for (Goods goods : list) {
            showUrl = goods.getUrl().split(",");
            goods.setUrl(showUrl[0]);
        }
        return list;
    }

    public int notLove(int love_id){
        return dao.notLove(love_id);
    }

    public int addMyCart(int goods_id,int user_id){
        return dao.addCartId(goods_id,user_id);
    }

    public List<Goods> getGoodsKey(String key){
        return dao.getGoodsKey(key);
    }

    /**
     * 添加收藏
     * @param goods_id
     * @param user_id
     * @return
     */
    public int loveGoods(int goods_id,int user_id){
        List<Goods> list = dao.loveID(user_id,goods_id);
//        判断用户是否收藏 已收藏0 未收藏2 曾经收藏过1
        int result = 0;
        for (Goods goods : list) {
            if (goods.getLove_id()>0&&goods.getLove_state()==1){
                result = 0;
            }else if (goods.getLove_id()>0&&goods.getLove_state()==0){
                dao.updateLoveGoods(goods.getLove_id());
                result = 1;
            }
        }
        if (list.size()==0){
            dao.loveGoods(goods_id,user_id);
            result = 2;
        }
        return result;
    }

    /**
     * 最近浏览
     * @param user_id
     * @return
     */
    public List<Goods> recentLook(int user_id,int cartLook){
        List<Goods> list = null;
//        购物车显示前五条浏览记录
        if (cartLook==0){
            list = dao.recentLook(user_id);
        }else if (cartLook==1){//显示所有
            list = dao.findLook(user_id);
        }
        String[] showUrl = null;
        for (Goods goods : list) {
            showUrl = goods.getUrl().split(",");
            goods.setUrl(showUrl[0]);
        }
        return list;
    }

    /**
     * 查询商品并分页
     * @param index
     * @return
     */
    public Page<Goods> getAllGoods(int index){
        Page<Goods> pages = new Page<>();
//        每页显示页数
        int end = 4;
//        过滤的条数=（当前页数-1）*每页显示页数
        int start = (index-1)*end;
//        一、查询每页显示数据
        List<Goods> list = dao.getAllGoods(start,end);
        String[] showUrl = null;
        for (Goods goods : list) {
            showUrl = goods.getUrl().split(",");
            goods.setUrl(showUrl[0]);
        }
//        将所有数据存入page类
        pages.setList(list);
//        设置页码
        pages.setIndex(index);
//        二、查询总条数
        int count = dao.findGoodsCount();
//        求总页数
        int pageCount = (int)Math.ceil(count/(end+0.0));
        pages.setPageCount(pageCount);

        return pages;
    }

    /**
     * 管理员删除商品
     * @param goods_id
     * @return
     */
    public int deleteGoods(int goods_id){
        return dao.deleteGoods(goods_id);
    }

    public Goods findGoods(int goods_id){
        return dao.findGoods(goods_id);
    }

 }
