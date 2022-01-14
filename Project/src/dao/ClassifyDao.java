package dao;

import po.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassifyDao extends BaseDao{
    //查询一级目录
    public List<GoodsType> findTypeName(){
        String sql = "SELECT * FROM tbl_type WHERE f_id=0";
        GoodsType classType = null;
        List<GoodsType> list = new ArrayList<>();
        JDBC jdbc = executeQuery(sql);
        if (jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    classType = new GoodsType();
                    classType.setType_id(jdbc.rs.getInt("type_id"));
                    classType.setType_name(jdbc.rs.getString("type_name"));
                    classType.setF_id(jdbc.rs.getInt("f_id"));
                    list.add(classType);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return list;
    }

    //根据一级目录所得的ID来查询二级目录
    public List<GoodsType> findPrent(int type_id){
        String sql = "SELECT * FROM tbl_type WHERE f_id=?";
        GoodsType classType = null;
        List<GoodsType> list = new ArrayList<>();
        JDBC jdbc = executeQuery(sql,type_id);
        if (jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    classType = new GoodsType();
                    classType.setType_id(jdbc.rs.getInt("type_id"));
                    classType.setType_name(jdbc.rs.getString("type_name"));
                    classType.setF_id(jdbc.rs.getInt("f_id"));
                    list.add(classType);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return list;
    }

    //根据首页目录来查询三级目录所得内容
    public List<AllType> findAllPren(int type_id,int all_id){
        String sql ="SELECT * FROM tbl_all WHERE type_id=? AND af_id=?";
        AllType allType = null;
        List<AllType> allList = new ArrayList<AllType>();
        JDBC jdbc = executeQuery(sql,type_id,all_id);
        if (jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    allType = new AllType();
                    allType.setType_id(jdbc.rs.getInt("type_id"));
                    allType.setAll_id(jdbc.rs.getInt("all_id"));
                    allType.setAll_name(jdbc.rs.getString("all_name"));
                    allType.setAf_id(jdbc.rs.getInt("af_id"));
                    allList.add(allType);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return allList;
    }
    //根据父类的id来查询标题
    public List<AllType> findAllType(int type_id){
        String sql ="SELECT * FROM tbl_all WHERE type_id=? AND af_id=0";
        AllType allType = null;
        List<AllType> allList = new ArrayList<AllType>();
        JDBC jdbc = executeQuery(sql,type_id);
        if (jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    allType = new AllType();
                    allType.setType_id(jdbc.rs.getInt("type_id"));
                    allType.setAll_id(jdbc.rs.getInt("all_id"));
                    allType.setAll_name(jdbc.rs.getString("all_name"));
                    allType.setAf_id(jdbc.rs.getInt("af_id"));
                    allList.add(allType);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return allList;
    }

    public List<Goods> findParam(int type_id){
        String sql = "SELECT * FROM tbl_goods WHERE type_id=? ORDER BY `index` DESC,sell_num DESC LIMIT 16";
        Goods classParam = null;
        List<Goods> paramList = new ArrayList<>();
        JDBC jdbc = executeQuery(sql,type_id);
        if (jdbc.rs!=null){
            try {
                while (jdbc.rs.next()){
                    classParam = new Goods();
                    classParam.setCreate_time(jdbc.rs.getDate("create_time"));
                    classParam.setKey(jdbc.rs.getString("key"));
                    classParam.setGoods_id(jdbc.rs.getInt("goods_id"));
                    classParam.setGoods_name(jdbc.rs.getString("goods_name"));
                    classParam.setIndex(jdbc.rs.getInt("index"));
                    classParam.setRemake(jdbc.rs.getString("remake"));
                    classParam.setSell_num(jdbc.rs.getInt("sell_num"));
                    classParam.setStore_num(jdbc.rs.getInt("store_num"));
                    classParam.setType_id(jdbc.rs.getInt("type_id"));
                    classParam.setPrice(jdbc.rs.getDouble("price"));
                    classParam.setUrl(jdbc.rs.getString("url"));
                    paramList.add(classParam);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return paramList;
    }

    //根据首页分类和关键字来  查询商品总数
    public int findCount(ClassGoods cg){
        String sql = "SELECT count(1) as count FROM tbl_goods g JOIN tbl_type t1 ON g.type_id=t1.`type_id`";
        if(cg.allid!=null&&!cg.allid.equals("")){
            String[] ids = cg.allid.split(",");
            for (String id : ids) {
                sql+=" join tbl_tota o"+id+" on o"+id+".`goods_id`=g.`goods_id` and o"+id+".all_id="+id+"";
            }
        }
        sql+=" Where 1=1";
        if(cg.fid!=null&&!cg.fid.equals("")){
            sql+=" and t1.`f_id`="+cg.fid+"";
        }
        if(cg.typeid!=null&&!cg.typeid.equals("")){
            sql+=" and t1.`type_id`="+cg.typeid+" ";
        }
        if(cg.key!=null&&!cg.key.equals("")){
            sql+=" AND (g.`goods_name` LIKE CONCAT('%','"+cg.key+"','%') or g.`key` LIKE CONCAT('%','"+cg.key+"','%'))";
        }
        sql+=" limit "+cg.start+","+cg.end+"";
        JDBC jdbc = executeQuery(sql);
        int count =0;
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


    //根据关键字和首页的 查询来 查找商品
    public List<Goods> findKey(ClassGoods cg){
        String sql = "SELECT * FROM tbl_goods g JOIN tbl_type t1 ON g.type_id=t1.`type_id`";
//                "#join tbl_tota o1 on o1.`goods_id`=g.`goods_id` and o1.all_id=221\n" +
//                "WHERE t1.`f_id`=20 AND t1.`type_id`=21 AND g.`goods_name` LIKE CONCAT('%','努比亚','%')";
        if(cg.allid!=null&&!cg.allid.equals("")){
            String[] ids = cg.allid.split(",");
            for (String id : ids) {
                sql+=" join tbl_tota o"+id+" on o"+id+".`goods_id`=g.`goods_id` and o"+id+".all_id="+id+"";
            }
        }
        sql+=" Where 1=1";
        if(cg.fid!=null&&!cg.fid.equals("")){
            sql+=" and t1.`f_id`="+cg.fid+"";
        }
        if(cg.typeid!=null&&!cg.typeid.equals("")){
            sql+=" and t1.`type_id`="+cg.typeid+" ";
        }
        if(cg.key!=null&&!cg.key.equals("")){
            sql+=" AND (g.`goods_name` LIKE CONCAT('%','"+cg.key+"','%') or g.`key` LIKE CONCAT('%','"+cg.key+"','%'))";
        }
        sql+=" limit "+cg.start+","+cg.end+"";
        JDBC jdbc = executeQuery(sql);
        Goods classParam = null;
        List<Goods> paramList = new ArrayList<>();
        if(jdbc.rs!=null){
            try {
                while(jdbc.rs.next()){
                    classParam = new Goods();
                    classParam.setCreate_time(jdbc.rs.getDate("create_time"));
                    classParam.setKey(jdbc.rs.getString("key"));
                    classParam.setGoods_id(jdbc.rs.getInt("goods_id"));
                    classParam.setGoods_name(jdbc.rs.getString("goods_name"));
                    classParam.setIndex(jdbc.rs.getInt("index"));
                    classParam.setRemake(jdbc.rs.getString("remake"));
                    classParam.setSell_num(jdbc.rs.getInt("sell_num"));
                    classParam.setStore_num(jdbc.rs.getInt("store_num"));
                    classParam.setType_id(jdbc.rs.getInt("type_id"));
                    classParam.setPrice(jdbc.rs.getDouble("price"));
                    classParam.setUrl(jdbc.rs.getString("url"));
                    paramList.add(classParam);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                close(jdbc.ps,jdbc.conn,jdbc.rs);
            }
        }
        return paramList;
    }

}
