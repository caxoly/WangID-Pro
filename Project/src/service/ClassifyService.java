package service;

import dao.ClassifyDao;
import po.*;
import util.Page;

import java.util.ArrayList;
import java.util.List;

public class ClassifyService {
    ClassifyDao dao = new ClassifyDao();
    //循环所得子级目录
    public List<GoodsType> findTypeName(){
        List<GoodsType> list = new ArrayList<>();
        list = dao.findTypeName();
        for (GoodsType classType : list) {
            classType.setList(dao.findPrent(classType.getType_id()));
        }
        return list;
    }
    //根据id循环所得子级目录
    public List<AllType> findAllType(int type_id){
        List<AllType> allList = new ArrayList<AllType>();
        allList = dao.findAllType(type_id);
        for (AllType allType : allList) {
            allType.setAllList(dao.findAllPren(allType.getType_id(),allType.getAll_id()));
        }
        return allList;
    }

    public List<Goods> findParam(int type_id){
        return dao.findParam(type_id);
    }

    public Page<Goods> findByKey(ClassGoods cg){
        Page<Goods> page = new Page<>();
        cg.end = 8;
        cg.start=(cg.index-1)*cg.end;

        //第一步 查询数据
        List<Goods> paramList = dao.findKey(cg);
        page.setList(paramList);
        //设置当前页码
        page.setIndex(cg.index);


        //第二步  查询总条数
        int count = dao.findCount(cg);

        //根据总条数求出总页数
        int pageCount = (int)Math.ceil(count/(cg.end+0.0));
        page.setPageCount(pageCount);
        //返回对象
        return page;
    }

    public List<GoodsType> getClassType(){
        return dao.findTypeName();
    }
    public List<GoodsType> getClassTypeByFid(String fid){
        return dao.findPrent(Integer.parseInt(fid));
    }


}
