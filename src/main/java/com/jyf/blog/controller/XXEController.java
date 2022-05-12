/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/29
 * Time: 13:19
 **/
package com.jyf.blog.controller;

import com.jyf.blog.util.AjaxResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="api")
public class XXEController {
    /*
        <login>
            <username>aaa</username>
            <password>adfasds</password>
        </login>
    */
    private static final Logger logger = LogManager.getLogger(XXEController.class);
    
    @ResponseBody
    @RequestMapping(value="loginXml.json"/*,method= RequestMethod.POST*/)
    public AjaxResponse testXXE(String xml){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
            
            //过滤部分关键字，防止XXE
            /*
            String newXml = xml.replace("!","")
                    .replace("DOCTYPE","")
                    .replace("ELEMENT","")
                    .replace("ENTITY","");
            */
            String newXml = xml;
            
            Document document = DocumentHelper.parseText(newXml);
            Element rootEle = document.getRootElement();
            String username = rootEle.elementTextTrim("username");
            //String password = rootEle.elementTextTrim("password");
            ajaxResponse.setCode(210);
            ajaxResponse.setMsg("登录失败");
            ajaxResponse.setContent(username);
            logger.error("登录失败！username:" + username);
        }catch (Exception e){
            e.printStackTrace();
            ajaxResponse.setCode(211);
            ajaxResponse.setMsg("数据处理失败");
        }
        
        return ajaxResponse;
    }
    
}
