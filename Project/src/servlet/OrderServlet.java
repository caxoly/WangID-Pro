package servlet;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.Or;
import po.*;
import service.OrderService;
import util.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OrderServlet extends HttpServlet {
    OrderService orderService = new OrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if (type==null){
            resp.sendRedirect("index.jsp");
            return;
        }
        if (type.equals("findProvince")){
            findProvince(req, resp);
        }else if (type.equals("findCity")){
            findCity(req, resp);
        }else if (type.equals("address")){
            address(req, resp);
        }else if (type.equals("showAddress")){
            showAddress(req, resp);
        }else if(type.equals("getOrder")){
            getOrder(req, resp);
        }else if(type.equals("createOrder")){
            createOrder(req, resp);
        }else if(type.equals("setUserMoney")){
            setUserMoney(req, resp);
        }else if(type.equals("setState")){
            setState(req, resp);
        }else if(type.equals("showOrder")){
            showOrder(req, resp);
        }else if (type.equals("delete")){
            delete(req,resp);
        }else if (type.equals("update")){
            update(req,resp);
        }else if (type.equals("insertid")){
            insertid(req,resp);
        }else if(type.equals("setShow")){
            setShow(req, resp);
        }else if(type.equals("showOneOrder")){
            showOneOrder(req, resp);
        }else if(type.equals("rightBuy")){
            rightBuy(req, resp);
        }
    }

    /**
     * 显示不同状态的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOneOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        int state = Integer.parseInt(req.getParameter("state"));
        List<MyOrder> list = orderService.showStateOrder(user_id,state);
        printInfo(resp,list,200);
    }

    /**
     * 删除订单信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int order_id = Integer.parseInt(req.getParameter("order_id"));
        int result = orderService.setShow(order_id);
        printInfo(resp,"删除订单成功",result);
    }

    /**
     * 修改地址信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Shop shop = new Shop();
        shop.setShop_id((Integer) req.getSession().getAttribute("shop_id"));
        shop.setShop_name(req.getParameter("shop_name"));
        shop.setShop_phone(req.getParameter("shop_phone"));
        shop.setProvince(req.getParameter("p_name"));
        shop.setCity(req.getParameter("c_name"));
        shop.setDistrict(req.getParameter("d_name"));
        shop.setAddress(req.getParameter("address"));
        orderService.update(shop);
        req.getRequestDispatcher("jsp/myaddress.jsp").forward(req, resp);
    }

    /**
     * 跳转去修改
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void insertid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Shop shop = new Shop();
        shop.setShop_id(Integer.parseInt(req.getParameter("shop_id")));
        req.getSession().setAttribute("shop_id",shop.getShop_id());
        req.getRequestDispatcher("jsp/Upress.jsp");
    }

    /**
     * 删除地址信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = new Order();
        order.setShop_id(Integer.parseInt(req.getParameter("shop_id")));
        int result = orderService.delete(order);
        printInfo(resp,"",result);
    }

    /**
     * 显示用户的所有订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        List<MyOrder> list = orderService.showOrder(user_id);
        printInfo(resp,list,200);
    }

    /**
     * 支付改变订单状态
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int order_id = Integer.parseInt(req.getParameter("order_id"));
        int result = orderService.setState(order_id);
        printInfo(resp,"订单状态修改成功!",result);
    }

    /**
     * 支付改变用户金额
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setUserMoney(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user= (User)req.getSession().getAttribute("user");
        double sum_money = Double.parseDouble(req.getParameter("sum_money"));
        int result = orderService.setUserMoney(user.getUser_id(),sum_money);
//        更新session
        user.setUser_money((user.getUser_money()-sum_money));
        req.getSession().setAttribute("user",user);
        printInfo(resp,"支付成功！",result);
    }

    protected void rightBuy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = new Order();
        User user = (User) req.getSession().getAttribute("user");
        order.setShop_id(Integer.parseInt(req.getParameter("shop_id")));
        order.setPay_way(Integer.parseInt(req.getParameter("pay_way")));
        order.setGive_way(req.getParameter("give_way"));
        order.setSum_money(Double.parseDouble(req.getParameter("sum")));
        order.setUser_id(user.getUser_id());
        int goods_id = Integer.parseInt(req.getParameter("goods_id"));

        order = orderService.rightBuy(order,goods_id);
        req.getRequestDispatcher("jsp/pay.jsp?order_id="+order.getOrder_id()+"&sum="+order.getSum_money()+"").forward(req,resp);
    }

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user.getUser_type()==1){
            req.setAttribute("info","管理员不能购买");
            req.getRequestDispatcher("jsp/cart.jsp").forward(req,resp);
            return;
        }
        Order order = new Order();
        order.setShop_id(Integer.parseInt(req.getParameter("shop_id")));
        order.setPay_way(Integer.parseInt(req.getParameter("pay_way")));
        order.setGive_way(req.getParameter("give_way"));
        order.setUser_id(user.getUser_id());
        String[] cart_id = req.getParameterValues("cart_id");
        String ids= Arrays.toString(cart_id);
        String reg = "[\\[\\]]";
        ids=ids.replaceAll(reg,"");

        order = orderService.createOrder(order,ids);
        req.getRequestDispatcher("jsp/pay.jsp?order_id="+order.getOrder_id()+"&sum="+order.getSum_money()+"").forward(req,resp);
    }

    /**
     * 通过购物车所选中的商品得到订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double sumMoney = Double.parseDouble(req.getParameter("sumMoney"));
        String[] cart_id = req.getParameterValues("cart_id");
        String ids= Arrays.toString(cart_id);
        String reg = "[\\[\\]]";
        ids=ids.replaceAll(reg,"");
//        System.out.println(ids);
        User user=(User)req.getSession().getAttribute("user");
        if(user==null){
            String path="/jsp/cart.jsp";
            req.getSession().setAttribute("path",path);
            resp.sendRedirect("jsp/login.jsp");
            return;
        }
        List<Goods> list = orderService.getMyCart(ids);
        req.setAttribute("list",list);
        req.getRequestDispatcher("jsp/getorder.jsp?sumMoney="+sumMoney+"").forward(req,resp);

    }

    /**
     * 查询登录用户的所有收货地址
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_id = req.getParameter("user_id");
        List<Shop> list = orderService.getSiteById(Integer.parseInt(user_id));
        printInfo(resp,list,200);
    }

    /**
     * 添加收货地址
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void address(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Shop shop = new Shop();
        shop.setUser_id(Integer.parseInt(req.getParameter("user_id")));
        shop.setShop_name(req.getParameter("shop_name"));
        shop.setShop_phone(req.getParameter("shop_phone"));
        shop.setProvince(req.getParameter("p_name"));
        shop.setCity(req.getParameter("c_name"));
        shop.setDistrict(req.getParameter("d_name"));
        shop.setP_id(Integer.parseInt(req.getParameter("province")));
        shop.setC_id(Integer.parseInt(req.getParameter("city")));
        shop.setD_id(Integer.parseInt(req.getParameter("district")));
        shop.setAddress(req.getParameter("address"));
        shop.setCreate_time(new Date());
        int result = orderService.address(shop);
        if (result>0){
            resp.sendRedirect("jsp/myaddress.jsp");
        }else {
            req.setAttribute("error","添加失败");
            req.getRequestDispatcher("jsp/address.jsp").forward(req,resp);
        }

    }

    /**
     * 查找城市
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findCity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Pid = Integer.parseInt(req.getParameter("Pid"));
        List<China> list = orderService.getCityByPid(Pid);
        printInfo(resp,list,200);
    }

    /**
     * 查询省份
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findProvince(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<China> list = orderService.findProvince();
        printInfo(resp,list,200);
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
