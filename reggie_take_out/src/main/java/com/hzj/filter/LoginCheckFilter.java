package com.hzj.filter;

import com.alibaba.fastjson.JSON;
import com.hzj.common.BaseContext;
import com.hzj.common.R;
import com.hzj.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebFault;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * PackageName :com.hzj.filter
 * ClassName: LoginCheckFilter
 * Description:
 *      检查用户是否完成登录
 * @Author 郝紫俊
 * @Create 2023/8/16  11:43
 * @edition 1.0
 */
@Slf4j
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符写法
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //定义不需要处理的请求路径
        //2、判断本次请求是否需要处理
        //3、如果不需要处理，则直接放行
        //4、判断登录状态，如果已登录，则直接放行
        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据

        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        log.info("过滤器拦截请求: {}",request.getRequestURI());
        //1、获取本次请求的URI
        String requestURI = request.getRequestURI();
        log.info("拦截到请求: {}",requestURI);
        //定义不需要处理的请求路径
        String[] urls=new String[]{
            "/employee/login","/employee/logout",
             "/backend/**","/front/**"
        };

        //2、判断本次请求是否需要处理
        boolean check = check(urls, requestURI);
        log.info("本次请求为{}，不需要处理",check);
        if (check){
            filterChain.doFilter(request,response);
            return;
        }

        //4、判断登录状态，如果已登录，则直接放行
        if ((request.getSession().getAttribute("employee")!=null)){
            log.info("用户以及登录，用户id为:{}",request.getSession().getAttribute("employee"));
            Long empId =(Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }
        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));

    }


    /**
     * 检查当前路径是否放行，进行路径匹配
     * @param urls
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for (String url:urls) {
            //match()遍历进行路径匹配
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }


    /**
     * 新增员工
     * @param employee
     * @return
     */

}
