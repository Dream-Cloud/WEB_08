package org.lanqiao.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineUserListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //在线人数保存在servletContext中
        //获取servletContext对象
        ServletContext application = se.getSession().getServletContext();
        Integer onlineUers = (Integer) application.getAttribute("onlineUsers");
        if (onlineUers == null){//如果onlineUsers为null 这表示当前这个会话为第一个会话 是访问的第一人
            onlineUers = 1;
        }else {
            onlineUers++;
        }
        application.setAttribute("onlineUsers",onlineUers);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //在线人数保存在servletContext中
        //获取servletContext对象
        ServletContext application = se.getSession().getServletContext();
        Integer onlineUers = (Integer) application.getAttribute("onlineUsers");
        if (onlineUers == null){//如果onlineUsers为null 这表示当前这个会话为第一个会话 是访问的第一人
            onlineUers = 1;
        }else {
            onlineUers--;
        }
        application.setAttribute("onlineUsers",onlineUers);
    }
}
