package org.lanqiao.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

//创建一个request监听器
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("请求创建");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("请求销毁");
    }
}
