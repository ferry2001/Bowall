//package com.ferry.bowall.filter;
//
//
//import com.alibaba.fastjson.JSON;
//import com.ferry.bowall.common.BaseContext;
//import com.ferry.bowall.common.R;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.AntPathMatcher;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 检查用户是否已经完成登录
// */
//
//@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
//@Slf4j
//public class LoginCheckFilter implements Filter {
//
//    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String requestURI = request.getRequestURI();
//
//        log.info("拦截到请求：{}", requestURI);
//
//        //2:添加不需要处理地址
//        String[] urls = new String[] {
//                "/employee/login",
//                "/employee/logout",
//                "/backend/**",
//                "/front/**",
//                "/common/**",
//                "/user/sendMsg",
//                "/user/login",
//                "/doc.html",
//                "/webjars/**",
//                "/swagger-resources",
//                "/v2/api-docs",
//                "/image/**",
//                "/image/getImage",
//                "/**/**"
//        };
//
//        boolean check = check(urls, requestURI);
//
//        //3:如果不需要处理直接放行
//        if (check) {
//            log.info("本次请求{}不需要处理", requestURI);
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//
//        //4-1:判断员工登录状态
//        if (request.getSession().getAttribute("employee") != null) {
//            log.info("用户已登录，用户id为:{}", request.getSession().getAttribute("employee"));
//
//            Long empId = (Long)request.getSession().getAttribute("employee");
//
//            BaseContext.setCurrentId(empId);
//
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        //4-2:判断用户登录状态
//        if (request.getSession().getAttribute("user") != null) {
//            log.info("用户已登录，用户id为:{}", request.getSession().getAttribute("user"));
//
//            Long userId = (Long)request.getSession().getAttribute("user");
//
//            BaseContext.setCurrentId(userId);
//
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        log.info("用户未登录");
//        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
//        return;
//    }
//
//
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//
//    /**
//     * 路径匹配，检查本次请求是否需要放行
//     * @param requestURI
//     * @return
//     */
//    public boolean check(String[] urls, String requestURI) {
//        for (String url : urls) {
//            boolean match = PATH_MATCHER.match(url, requestURI);
//            if (match) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
