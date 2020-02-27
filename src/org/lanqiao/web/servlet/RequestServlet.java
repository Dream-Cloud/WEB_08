package org.lanqiao.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/req.do")
public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");
        switch (method){
            case "add":
                addAttr(req,resp);
                break;
            case "remove":
                removeAttr(req,resp);
                break;
            case "replace":
                replaceAttr(req,resp);
                break;
            default:break;
        }

    }

    private void replaceAttr(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("username","tom");
    }

    private void removeAttr(HttpServletRequest req, HttpServletResponse resp) {
        req.removeAttribute("username");
    }

    private void addAttr(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("username","admin");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
