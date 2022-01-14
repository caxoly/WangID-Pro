package servlet;

import com.alibaba.fastjson.JSON;
import po.Goods;
import po.GoodsType;
import po.User;
import service.UserService;
import util.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class UserServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        //如果type取不到值 则返回到首页
        if (type==null){
            resp.sendRedirect("index.jsp");
            return;
        }
        if (type.equals("register")){
            register(req, resp);
        } else if (type.equals("login")){
            login(req, resp);
        }else if (type.equals("addGoods")){
            addGoods(req, resp);
        }else if (type.equals("findType")){
            findType(req, resp);
        }else if (type.equals("setMoney")){
            setMoney(req, resp);
        }else if(type.equals("editUser")){
            editUser(req, resp);
        }else if (type.equals("findFtype")){
            findFtype(req, resp);
        }else if (type.equals("updateXXi")){
            updateXXi(req,resp);
        }else if (type.equals("findCartNum")){
            findCartNum(req,resp);
        }else if (type.equals("look")){
            look(req,resp);
        }else if (type.equals("updateGoods")){
            updateGoods(req,resp);
        }
    }

    /**
     * 修改商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Goods goods = new Goods();
        goods.setGoods_id(Integer.parseInt(req.getParameter("goods_id")));
        goods.setUrl(req.getParameter("url"));
        goods.setGoods_name(req.getParameter("goods_name"));
        goods.setPrice(Double.parseDouble(req.getParameter("price")));
        goods.setSell_num(Integer.parseInt(req.getParameter("sell_num")));
        goods.setType_id(Integer.parseInt(req.getParameter("type_id")));
        goods.setStore_num(Integer.parseInt(req.getParameter("store_num")));
        goods.setRemake(req.getParameter("remake"));
        goods.setIndex(Integer.parseInt(req.getParameter("index")));
        goods.setKey(req.getParameter("key"));

        int result = userService.updateGoods(goods);
        if (result>0){
            resp.sendRedirect("jsp/managershow.jsp");
        }else {
            req.getSession().setAttribute("info","修改失败");
            req.getRequestDispatcher("jsp/managershow.jsp").forward(req,resp);
        }

    }

    /**
     * 如果用户登录则添加用户浏览记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void look(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int goods_id = Integer.parseInt(req.getParameter("goods_id"));
        User user = (User)req.getSession().getAttribute("user");
//        如果登录则记录用户浏览
        if (user==null){
            printInfo(resp,0,400);
        }else {
            int result = userService.look(goods_id,user.getUser_id());
            printInfo(resp,result,200);
        }

    }

    /**
     * 查询购物车数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findCartNum(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
//        如果登录则显示用户购物车的数量 否则默认0
        if (user==null){
            printInfo(resp,0,400);
        }else {
            int count = userService.findCartNum(user.getUser_id());
            printInfo(resp,count,200);
        }

    }

    /**
     * 查询所有的父类商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findFtype(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GoodsType> list = userService.findFtype();
        printInfo(resp,list,200);

    }

    /**
     * 账户充值
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setMoney(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_money = Integer.parseInt(req.getParameter("user_money"));
        String pwd = req.getParameter("pwd");
        User user = (User)req.getSession().getAttribute("user");
        String error = "";
        if (user.getPwd().equals(pwd)){
            int result = userService.updateUserMoney(user,user_money);
            if (result>0){
//            更新用户信息
                user.setUser_money(user.getUser_money()+user_money);
                req.getSession().setAttribute("user",user);
                error = "充值成功！";
            }else {
                error = "充值失败！";
            }
        }else {
            error = "账户密码错误！";
        }
        req.setAttribute("error",error);
        req.getRequestDispatcher("jsp/operatemoney.jsp").forward(req,resp);
    }

    /**
     * 查询所有子的商品类别
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int f_id = Integer.parseInt(req.getParameter("f_id"));
        List<GoodsType> list = userService.findType(f_id);
        printInfo(resp,list,200);

    }

    /**
     * 添加商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Goods goods = new Goods();
        goods.setUrl(req.getParameter("url"));
        goods.setGoods_name(req.getParameter("goods_name"));
        goods.setPrice(Double.parseDouble(req.getParameter("price")));
        goods.setSell_num(Integer.parseInt(req.getParameter("sell_num")));
        goods.setStore_num(Integer.parseInt(req.getParameter("store_num")));
        goods.setIndex(Integer.parseInt(req.getParameter("index")));
        goods.setKey(req.getParameter("key"));
        goods.setCreate_time(new Date());
        goods.setType_id(Integer.parseInt(req.getParameter("type_id")));
        goods.setRemake(req.getParameter("remake"));

        String[] allId = req.getParameterValues("allid");
        int result = userService.addGoods(goods,allId);
        if (result>0){
//            用request存会在重定向后进入一个新的请求，用session添加到整个会话
//            req.getSession().setAttribute("info","添加成功");
            resp.sendRedirect("jsp/manager.jsp");
        }else {
            req.getSession().setAttribute("info","添加失败");
            req.getRequestDispatcher("jsp/manager.jsp").forward(req,resp);
        }

    }

    /**
     * 账户登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void login ( HttpServletRequest request, HttpServletResponse response ) throws ServletException,IOException{
        User user = new User();
        user.setUser_name(request.getParameter("user_name"));
        user.setPwd(request.getParameter("pwd"));
        user = userService.login(user);
        //登录成功
        if (user.getUser_id()==null){
            request.setAttribute("error","用户不存在，请注册!");
            request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
        }else if (user.getUser_id()>0){
            request.getSession().setAttribute("user",user);
            //如果是管理员则直接去后台
            if(user.getUser_type()==1){
                request.getRequestDispatcher("jsp/managerindex.jsp").forward(request,response);
                return;
            }

            //取出用户想访问的路径
            String path = (String)request.getSession().getAttribute("path");
            if(path==null){
                response.sendRedirect("index.jsp");
            }else{
                response.sendRedirect(request.getContextPath()+path);
            }
        }else {
            request.setAttribute("error","用户名或密码错误");
            request.getRequestDispatcher("jsp/login.jsp").forward(request,response);
        }

    }

    /**
     * 修改头像
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void editUser ( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,IOException{
        User user = (User) req.getSession().getAttribute("user");
        String user_url = req.getParameter("user_url");
        int result = userService.editUser(user.getUser_id(),user_url);
        if(result>0){
            //更新session
            user.setImage_url(user_url);
            req.getSession().setAttribute("user",user);
            resp.sendRedirect("jsp/shez_toux.jsp");
        }else{
            req.setAttribute("error","更新失败");
            req.getRequestDispatcher("jsp/shez_toux.jsp").forward(req,resp);
        }
    }

    /**
     * 账户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUser_name(request.getParameter("user_name"));
        user.setLike_name(request.getParameter("like_name"));
        user.setPwd(request.getParameter("pwd"));
        String userMoney = request.getParameter("user_money");
        if (userMoney.equals("")){
            user.setUser_money(0.00);
        }else {
            user.setUser_money(Double.parseDouble(userMoney));
        }

        String pwd =request.getParameter("pwdAgain");
        String error = userService.checkRegister(user,pwd);
        /*
        判断账号是否重复
        insert 是判断方法
         */
        int count = userService.insert(user);
        if (count!=0){
            request.setAttribute("error","该用户已被注册");
            request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
            return;
        }
        if (!error.equals("")){
            request.setAttribute("error",error);
            request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
            return;
        }
        int result = userService.register(user);
        if (result > 0) {
            //转发 登录页面
            response.sendRedirect("jsp/login.jsp");
        } else {
            request.setAttribute("error","注册失败");
            request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
        }
    }

    /**
     * 修改个人信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateXXi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUser_id(Integer.parseInt(request.getParameter("user_id")));
        user.setLike_name(request.getParameter("like_name"));
        user.setAge(Integer.parseInt(request.getParameter("age")));
//        user.setSex(Integer.parseInt(request.getParameter("sex")));
        /*
        把性别男转成int 0 女 转成 int 1 传回数据库
         */
        if (request.getParameter("sex").equals("0")){
            user.setSex(0);
        }else{
            user.setSex(1);
        }
        int result = userService.update(user);
        if(result>0){
            //更新session
            User tUser = (User)request.getSession().getAttribute("user");
            tUser.setAge(user.getAge());
            tUser.setLike_name(user.getLike_name());
            tUser.setSex(user.getSex());
            //tUser.setUser_money(user.getUser_money());
            request.getSession().setAttribute("user",tUser);
            response.sendRedirect("jsp/usercenter.jsp");
        }else{
            request.setAttribute("error","保存失败！");
            request.getRequestDispatcher("jsp/usercenter.jsp").forward(request,response);
        }
    }


    /**
     * ajax的请求
     * @param response
     * @param entity
     * @param code
     * @throws IOException
     */
    private void printInfo(HttpServletResponse response,Object entity,int code) throws IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Result rs = new Result();
        rs.setCode(code);
        rs.setInfo(entity);
        out.println(JSON.toJSONString(rs));
        out.flush();
        out.close();
    }

}
