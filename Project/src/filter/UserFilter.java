package filter;

import po.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserFilter implements Filter {
    //存储需要放行的页面
    List<String> pathList = null;

    //存储管理后台页面
    List<String> manageList = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        初始化调用
        pathList = new ArrayList<>();
        pathList.add("/index.jsp");
        pathList.add("/user");
        pathList.add("/upload");
        pathList.add("/order");
        pathList.add("/goods");
        pathList.add("/jsp/login.jsp");
        pathList.add("/jsp/classify.jsp");
        pathList.add("/jsp/register.jsp");

        //存储管理后台的页面
        manageList = new ArrayList<>();
        manageList.add("/jsp/managerindex.jsp");
        manageList.add("/jsp/managershow.jsp");
        manageList.add("/jsp/manager.jsp");
        manageList.add("/jsp/managercustom.jsp");
        manageList.add("/jsp/editGoods.jsp");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String path = request.getServletPath();

        //不过滤的请求
        for (String s : pathList){
//            检查字符串是否是以指定子字符串开头，如果是则返回 True，否则返回 False
            if (path.startsWith(s)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }
//        静态内容
        if (path.startsWith("/css/")||path.startsWith("/js/")||path.startsWith("/images/")||path.startsWith("/pie/")||path.startsWith("/houl/")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            if(user.getUser_type()==0){
                for (String s : manageList){
                    //检查用户是否有访问管理后台的权限
                    if (path.startsWith(s)){
                        response.sendRedirect(request.getContextPath()+"/index.jsp");
                        return;
                    }
                }
            }
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }else {
//            记录登录前所点击的页面路径
            request.getSession().setAttribute("path",path);
            response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
