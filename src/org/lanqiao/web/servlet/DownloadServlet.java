package org.lanqiao.web.servlet;

import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/download.do")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应内容类型
        resp.setContentType("application/x-msdownload");
        //获取文件名
        String filename = req.getParameter("filename");
        //文件来源
        File srcFile = new File("E://upload",filename);
        File targetFile = new File("D://download",filename);
        FileUtils.copyFile(srcFile,resp.getOutputStream());
        //设置响应头 让下载的过程由用户参与干预，还可以保证文件名称filename也可以自己去设置
        resp.addHeader("content-disposition","attachment;filename="+filename);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
