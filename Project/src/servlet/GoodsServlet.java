package servlet;

import com.alibaba.fastjson.JSON;
import po.*;
import service.ClassifyService;
import service.GoodsService;
import util.Page;
import util.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GoodsServlet extends HttpServlet {
    GoodsService goodsService = new GoodsService();
    ClassifyService classifyService = new ClassifyService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if (type==null){
            request.getRequestDispatcher("index.jsp").forward(request,response);
            return;
        }
        if (type.equals("getGoodsById")){
            getGoodsById(request,response);
        } else if(type.equals("getCart")){
            getCart(request, response);
        } else if(type.equals("deleteCart")){
            deleteCart(request, response);
        } else if(type.equals("addCartNum")){
            addCartNum(request, response);
        } else if(type.equals("delCartNum")){
            delCartNum(request, response);
        } else if (type.equals("getGoodsBySell")){
            getGoodsBySell(request, response);
        } else if (type.equals("addCart")){
            addCart(request, response);
        } else if (type.equals("isLogin")){
            isLogin(request, response);
        }else if (type.equals("findParam")){
            findParam(request, response);
        }else if (type.equals("findByKey")){
            findByKey(request, response);
        }else if(type.equals("findOneLevel")){
            findOneLevel(request, response);
        }else if(type.equals("findTwoLevel")){
            findTwoLevel(request, response);
        }else if(type.equals("findThreeLevel")){
            findThreeLevel(request, response);
        }else if(type.equals("findLove")){
            findLove(request, response);
        }else if(type.equals("addMyCart")){
            addMyCart(request, response);
        }else if(type.equals("notLove")){
            notLove(request, response);
        }else if (type.equals("getGoodsKeyId")){
            getGoodsKeyId(request,response);
        }else if (type.equals("loveGoods")){
            loveGoods(request,response);
        }else if (type.equals("buyCart")){
            buyCart(request,response);
        }else if (type.equals("recentLook")){
            recentLook(request,response);
        }else if (type.equals("allGoods")){
            allGoods(request,response);
        }else if (type.equals("deleteGoods")){
            deleteGoods(request,response);
        }else if (type.equals("updateGoods")){
            updateGoods(request,response);
        }

    }

    /**
     * 通过商品id查询商品信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int goods_id = Integer.parseInt(request.getParameter("goods_id"));
        Goods goods = goodsService.findGoods(goods_id);
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("/jsp/editGoods.jsp").forward(request,response);
    }

    /**
     *  删除商品
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int goods_id = Integer.parseInt(request.getParameter("goods_id"));
        int result = goodsService.deleteGoods(goods_id);
        printInfo(response,result,200);
    }

    /**
     * 查询商品并分页
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void allGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        Page<Goods> pages = goodsService.getAllGoods(index);
        printInfo(response,pages,200);
    }

    /**
     * 最近浏览
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void recentLook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        int cartLook = Integer.parseInt(request.getParameter("cartLook"));
        List<Goods> list = goodsService.recentLook(user.getUser_id(),cartLook);
        printInfo(response,list,200);
    }

    /**
     * 立即购买获取商品
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void buyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int goods_id = Integer.parseInt(request.getParameter("goods_id"));
        User user = (User) request.getSession().getAttribute("user");
        if (user.getUser_type()==1){
            request.setAttribute("info","管理员不能购买");
            request.getRequestDispatcher("/goods?type=getGoodsById&goods_id="+goods_id+"").forward(request,response);
            return;
        }
        Goods goods = new Goods();
        goods.setUrl(request.getParameter("url"));
        goods.setGoods_name(request.getParameter("goods_name"));
        goods.setPrice(Double.parseDouble(request.getParameter("price")));
        goods.setGoods_id(goods_id);
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("jsp/rightpay.jsp").forward(request,response);

    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void loveGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goods_id = request.getParameter("goods_id");
        User user = (User)request.getSession().getAttribute("user");
        if (user==null){
            printInfo(response,"未登录",400);
            return;
        }
        int result = goodsService.loveGoods(Integer.parseInt(goods_id),user.getUser_id());
        if (result==0){
            printInfo(response,"已收藏",0);
        }else if (result==1||result==2){
            printInfo(response,"收藏成功",1);
        }
    }

    /**
     * 猜你喜欢
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getGoodsKeyId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        List<Goods> list = goodsService.getGoodsKey(key);
        printInfo(response,list,200);
    }

    /**
     * 查找对应用户的所有收藏
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findLove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=(User)req.getSession().getAttribute("user");
        List<Goods> list=goodsService.getLoveByUser(user.getUser_id());
        printInfo(resp,list,200);
    }

    /**
     * 通过l_id取消收藏并更新session
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void notLove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String love_id=req.getParameter("love_id");
        int result=goodsService.notLove(Integer.parseInt(love_id));
        printInfo(resp,"取消收藏成功",result);

    }

    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addMyCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=(User)req.getSession().getAttribute("user");
        String goods_id=req.getParameter("goods_id");
        int result = goodsService.addMyCart(Integer.parseInt(goods_id),user.getUser_id());
        printInfo(resp,"添加购物车成功",result);
    }

    private void findThreeLevel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AllType> list = classifyService.findAllType(Integer.parseInt(req.getParameter("id")));
        printInfo(resp,list,200);
    }
    private void findTwoLevel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GoodsType> list = classifyService.getClassTypeByFid(req.getParameter("id"));
        printInfo(resp,list,200);
    }
    private void findOneLevel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GoodsType> list = classifyService.getClassType();
        printInfo(resp,list,200);
    }

    private void findByKey(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClassGoods cg = new ClassGoods();
        cg.index = Integer.parseInt(req.getParameter("index"));
        cg.key = req.getParameter("key");
        if (req.getParameter("typeid")!=null&&!req.getParameter("typeid").equals("")){
            cg.typeid = req.getParameter("typeid");
        }else {
            cg.typeid = req.getParameter("type_id");
        }
        cg.fid = req.getParameter("fid");
        cg.allid = req.getParameter("allid");
        Page<Goods> pages = classifyService.findByKey(cg);
        printInfo(resp,pages,200);

    }

    protected void findParam(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClassGoods cg = new ClassGoods();
        cg.type_id = Integer.parseInt(req.getParameter("type_id"));
        List<Goods> paramList = classifyService.findParam(cg.type_id);
        printInfo(resp,paramList,200);
    }


    /**
     * 商品详情
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getGoodsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goods_id = request.getParameter("goods_id");
        Goods goods = goodsService.getGoodsById(Integer.parseInt(goods_id));
        request.setAttribute("goodsinfo",goods);
//        System.out.println(goods.getRemake());
        request.getRequestDispatcher("jsp/goodsinfo.jsp").forward(request,response);
    }

    /**
     * 商品列表排序
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getGoodsBySell(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Goods> list = goodsService.getGoodsBySell();
        printInfo(response,list,200);
    }
    protected void delCartNum(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String c_id=req.getParameter("c_id");
        int result=goodsService.opreateCartNum(-1,Integer.parseInt(c_id));
        printInfo(resp,"",result);
    }
    protected void addCartNum(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String c_id=req.getParameter("c_id");
        int result=goodsService.opreateCartNum(+1,Integer.parseInt(c_id));
        printInfo(resp,"",result);
    }
    protected void deleteCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cart_id=req.getParameter("cart_id");
        int result=goodsService.deleteCart(Integer.parseInt(cart_id));
//         如果result有数据，则更新session中购物车的数量
//        if(result>0){
//            User user=(User)req.getSession().getAttribute("user");
//            user.setNum(user.getNum()-1);
//            req.getSession().setAttribute("user",user);
//        }
        printInfo(resp,"",result);
    }

    /**
     * 添加商品
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goods_id=request.getParameter("goods_id");
        User user=(User)request.getSession().getAttribute("user");
        user.setNum(0);
        int result=goodsService.addCart(user.getUser_id(),Integer.parseInt(goods_id));
        if(result==2){
            User tUser = (User)request.getSession().getAttribute("user");
            user.setNum(user.getNum()+1);
            request.getSession().setAttribute("user",tUser);
        }
        printInfo(response,"",result);
    }

    /**
     * 加入购物车时判断用户是否登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void isLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        //如果user == null 则用户没有登录 需要登录
        if (user==null){
            printInfo(response,"",400);
        } else {
            //user!=null 则用户已经登录成功
            printInfo(response,"",200);
        }
    }

    protected void getCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=(User)req.getSession().getAttribute("user");
        if(user==null){
            String path="/goods?type=getCart";
            req.getSession().setAttribute("path",path);
            resp.sendRedirect("jsp/login.jsp");
            return;
        }
        List<Goods> list=goodsService.getCartByUserId(user.getUser_id());
        req.setAttribute("list",list);
        req.getRequestDispatcher("jsp/cart.jsp").forward(req,resp);

    }

    private void printInfo(HttpServletResponse response, Object entity, int code) throws IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Result rs = new Result();
        rs.setCode(code);
        rs.setInfo(entity);
        out.print(JSON.toJSONString(rs));
        out.flush();
        out.close();
    }
}
