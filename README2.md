# 文件的上传和下载

## 1、文件上传：

将本地的文件 以IO流的形式写入到服务器

**1.1.前提条件**

​		a、在form表单中必须使用文件的上传控件<input type="file"/>（form中提供input的type是file类型的文件上传域）

 		b、form表单的method必须是post

 		c、form表单的enctype必须是multipart/form-data（决定了POST请求方式，请求正文的数据类型）

**需要的依赖**

CommonsFileUpload  

CommonsIO

![img](E:\YouDaoYun\m15234512314@163.com\8ead84c42dfb4a52bfd14634f4d2358c\clipboard.png)

表单控件分为：文件上传控件  普通表单控件

**实例**

文件上传：

```html
文件上传：
  <form action="/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="pic" value=""><br>
    <input type="text" name="username" value=""><br>
    <input type="submit" value="上传">
  </form>
```

处理文件上传的Servlet

```java
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
```



# 2文件下载

原来我们响应的都是html的字符数据！

把一个文件变成字节数组，使用response.getOutputStream()来各应给浏览器

利用程序实现下载需要设置 2 个报头：

- Web 服务器需要告诉浏览器其所输出的内容的类型不是普通的文本文件或 HTML 文件，而是一个要保存到本地的下载文件。设置Content-Type 的值为：**application/x-msdownload**

  ```java
  resp.setContentType("application/x-msdownload");
  ```

- Web 服务器希望浏览器不直接处理相应的实体内容，而是由用户选择将相应的实体内容保存到一个文件中，这需要**设置 Content-Disposition 报头。该报头指定了接收程序处理数据内容的方式，在 HTTP 应用中只有 attachment 是标准方式，attachment 表示要求用户干预**。在 attachment 后面还可以指定 filename 参数，该参数是服务器建议浏览器将实体内容保存到文件中的文件名称。在设置 Content-Dispostion 之前一定要指定 Content-Type.

  ```java
  resp.addHeader("content-disposition","attachment;filename="+filename);
  ```

**实例**

处理文件下载的servlet

```java
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
```

