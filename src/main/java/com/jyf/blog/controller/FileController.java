/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/27
 * Time: 14:23
 **/
package com.jyf.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;

@Controller
public class FileController {
    
    
    @RequestMapping(value="download.htm",method= RequestMethod.GET)
    public void fileDownLoad(HttpServletRequest request, HttpServletResponse response,String fileName) throws Exception{
    
        ServletContext servletContext = request.getServletContext();
        String myFileName = fileName;
        //System.out.println("myFileName:" + myFileName);
        /*
        if(myFileName.contains("../")){
            response.setStatus(404);
        }
        */
        String realPath = servletContext.getRealPath("/file/"+myFileName);
        InputStream in = new FileInputStream(new File(realPath));
    
        fileName=new String(fileName.getBytes("UTF-8"),"UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = in.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
        
    }
    
    
    @RequestMapping("fileUpload.htm")
    public String  springUpload(HttpServletRequest request) throws IllegalStateException, IOException
    {
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if(multipartResolver.isMultipart(request))
        {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            Iterator iter = multiRequest.getFileNames();
            while(iter.hasNext())
            {
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    String path = realPath + "file/" + System.currentTimeMillis() + "-" + file.getOriginalFilename();
                    //System.out.println("path:" + path);
                    
                    File file1 = new File(path);
                    if(file1.exists()){
                        return "redirect:uploadStatus.htm?status=5";
                    }
                    try {
                        file.transferTo(file1);
                    }catch (Exception e){
                        return "redirect:uploadStatus.htm?status=2";
                    }
                    FileInputStream fis;
                    try {
                        fis = new FileInputStream(file1);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        //ObjectInputStreamFilter ois = new ObjectInputStreamFilter(fis);
                        ois.readObject();
                        ois.close();
                        return "redirect:uploadStatus.htm?status=3";
                    }catch (Exception e){
                        e.printStackTrace();
                        return "redirect:uploadStatus.htm?status=4";
                    }
                    
                }
            
            }
        
        }
        return "redirect:uploadStatus.htm?status=1";
    }

    
    
    
    
    
}
