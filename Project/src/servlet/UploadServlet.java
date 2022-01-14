package servlet;

import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Result rs = new Result();
        //存储图片路径
        List<String>  pathList  = new ArrayList<>();
        String fileName = "";
        String uploadName = "";
        //判断是否是可以上传的文件

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        //图片的存储路径
        String uploadFilePath =req.getSession().getServletContext().getRealPath("images\\head\\");
        //临时文件中转
        File temp = new File("d:\\temp\\");
        File temp1 = new File(uploadFilePath);
        if(!temp1.exists()){
            temp1.mkdirs();
        }
        //如果目录不存在
        if(!temp.exists()){
            //创建指定目录
            temp.mkdirs();
        }
        //如果该文件是可上传文件
        if(isMultipart){
            //创建文件工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置缓冲区4kb
            factory.setSizeThreshold(4096);
            //设置临时存放路径
            factory.setRepository(temp);
            ServletFileUpload upload = new ServletFileUpload(factory);
            //设置单个文件上传最大值
            upload.setSizeMax(1024*1024*30);
            try{
                //解析form表单文件
                List<FileItem> items = upload.parseRequest(req);
                //获取集合的迭代器
                Iterator<FileItem> iter = items.iterator();
                while(iter.hasNext()){
                    FileItem item = iter.next();
                    if(!item.isFormField()){
                        fileName= item.getName();
                        //替换文件名称
                        fileName = UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("."));
                        if(fileName!=null&&!fileName.equals("")){
                            File fullFile = new File(fileName);
                            File saveFile = new File(uploadFilePath,fileName);
                            item.write(saveFile);
                            uploadName = fullFile.getName();
                            pathList.add(uploadName);

                        }
                    }
                }
                rs.setCode(200);
                rs.setInfo(pathList);
            }catch (Exception ex){
                rs.setCode(400);
                ex.printStackTrace();
            }
        }
        PrintWriter out = resp.getWriter();
        out.print(JSON.toJSONString(rs));
        out.flush();
        out.close();
    }
}
