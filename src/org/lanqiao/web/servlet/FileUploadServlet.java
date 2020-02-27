package org.lanqiao.web.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/upload.do")
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        //此处为Java提供的一个临时目录
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        //设置工厂的存储区域
        factory.setRepository(repository);

        // Create a new file upload handler 创建文件的上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Parse the request 解析请求
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(req);
            //获取集合迭代器
            Iterator<FileItem> iter = items.iterator();
            //处理表单项
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (item.isFormField()) {//判断表单项是否是普通表单项
                    processFormField(item);
                } else {//这里是文件上传
                    processUploadedFile(item);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //处理文件上传表单项
    private void processUploadedFile(FileItem item) throws Exception {
        String fieldName = item.getFieldName();//表单项名
        String fileName = item.getName();//文件名
        long sizeInBytes = item.getSize();//文件大小
        System.out.println("fieldName:"+fieldName);
        System.out.println("fileName:"+fileName);
        System.out.println("sizeInBytes:"+sizeInBytes);
        File dir = new File("E:/upload");
        if (!dir.exists()){
            dir.mkdir();
        }
        File uploadFile = new File(dir,fileName);
        //执行文件上传
        item.write(uploadFile);
        FileUtils.copyInputStreamToFile(item.getInputStream(),uploadFile);
    }
    //处理普通表单项
    private void processFormField(FileItem item) throws UnsupportedEncodingException {
        String fieldName = item.getFieldName();
        String str = item.getString("utf-8");//控件的值
        System.out.println(fieldName + "-----" + str);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
