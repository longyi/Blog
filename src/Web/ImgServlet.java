package Web;

import Entity.Results;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * @author LongYi
 * @date 2018/10/28 19:16
 * @log: 1.
 */
@WebServlet("/img")
@MultipartConfig
public class ImgServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // MultipartFile file;
        String fileName = System.currentTimeMillis() + ".jpg";
        String basePath = request.getSession().getServletContext().getRealPath("/images/");
        String Url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/" + fileName;
        FileItemFactory factory = new DiskFileItemFactory();
        // 创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 开始解析请求信息
        List items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        // 对所有请求信息进行判断
        Iterator file = items.iterator();
        while (file.hasNext()) {
            FileItem item = (FileItem) file.next();
            // 信息为普通的格式
            if (item.isFormField()) {
                String fieldName = item.getFieldName();
                String value = item.getString();
                request.setAttribute(fieldName, value);
            }
            // 信息为文件格式
            else {
                String filename = item.getName();
                int index = filename.lastIndexOf("\\");
                File files = new File(basePath+fileName);
                try {
                    item.write(files);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String msg = "{\"success\":\"" + true + "\",\"file_path\":\"" + Url + "\"}";
                response.getWriter().write(msg);
            }
        }

    }
            protected void doGet (HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
                doPost(request, response);
            }
   /* public String imgUpload(HttpServletRequest request,HttpServletResponse response) {
        //获取response、request对象


        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String saveRealFilePath = request.getServletContext().getRealPath("/images");
        fileDataFileName = System.currentTimeMillis() + ".jpg";
        File fileDir = new File(saveRealFilePath);
        if (!fileDir.exists()) { //如果不存在 则创建
            fileDir.mkdirs();
        }
        File savefile;
        savefile = new File(saveRealFilePath + "/" + fileDataFileName);
        try {
            FileUtils.write(savefile,fileDataFileName);
        } catch (IOException e) {
            err = "错误"+e.getMessage();
            e.printStackTrace();
        }
        String file_Name = request.getContextPath() + "/images/" + fileDataFileName;

        msg = "{\"success\":\"" + true + "\",\"file_path\":\"" + file_Name + "\"}";
        out.print(msg); //返回msg信息
        return msg;
    }

    public String getErr() {
        return err;
    }
    public void setErr(String err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public File getFileData() {
        return fileData;
    }
    public void setFileData(File fileData) {
        this.fileData = fileData;
    }

    public String getFileDataFileName() {
        return fileDataFileName;
    }
    public void setFileDataFileName(String fileDataFileName) {
        this.fileDataFileName = fileDataFileName;
    }*/

        }


