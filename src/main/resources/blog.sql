-- --------------------------------------------------------
-- 主机:                           192.168.32.32
-- 服务器版本:                        10.1.13-MariaDB - MariaDB Server
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 blog 的数据库结构
CREATE DATABASE IF NOT EXISTS `blog` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `blog`;

-- 导出  表 blog.article 结构
CREATE TABLE IF NOT EXISTS `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT '0',
  `title` varchar(256) DEFAULT '0',
  `sub_title` varchar(256) DEFAULT NULL,
  `pic` varchar(128) DEFAULT NULL,
  `content` varchar(2048) DEFAULT NULL,
  `hits` int(11) NOT NULL DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0:删除,1:私有,2:发布',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `hits` (`hits`),
  KEY `status` (`status`),
  CONSTRAINT `FK1_user_id_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 正在导出表  blog.article 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` (`id`, `user_id`, `title`, `sub_title`, `pic`, `content`, `hits`, `status`, `create_time`) VALUES
	(1, 2, '基于NPAPI接口的windows平台安全控件开发（一）', 'NPAPI接口安全控件开发', 'img/5.png', '<p>&nbsp;&nbsp;由于最近公司在做支付，所以需要开发安全控件。可能就因为有“安全”两个字，所以这个控件的开发任务就到我这个做信息安全的人身上了，，，，对于没写过安全控件的我来说，自然是各种查资料，找博客，期望能够找到现成的代码（懒。。。），不过，可能是因为这个比较小众，连有点干货的文章都没找到几篇，更不用说找到源代码了。所以，只能自食其力，自己折腾。</p>\r\n<p>&nbsp;&nbsp;这次安全控件主要分为两个版本：IE内核的和支持NPAPI借口的。IE一直表现的比较个性啊，就玩自己的一套，导致前端人员比较苦逼。结合各种博客，书籍等资料，经过一段时间的开发，算是把两个版本的空间都写好了，这系列的博客就当一个开发记录吧。由于也是第一次开发这种空间，水平有限，大神可以直接PASS。</p>\r\n<p>&nbsp;&nbsp;首先是基于NPAPI接口的安全控件。当然了，这个接口很快就要被抛弃了,chrome和firefox都宣布要逐步放弃该接口。不管以后是HTML5，PPAPI还是什么高达上的新技术出来，至少目前还是要继续使用这个接口。</p>\r\n<p>&nbsp;&nbsp;安全控件目前主流的功能大体是：1.加密密码。2.通过hook技术防止键盘被窃听。3.其他搜集信息功能。（个人见解，大神有不同意见，请指教），在开发的角度来说，控件主要包含一个图形化的输入框以及对外提供的各种借口。NPAPI体系我就不介绍了，网上也有一些介绍的文章（我也介绍不来，，，），以下是基于VS2003平台开发的插件介绍（用2003主要是因为不需要额外安装运行库，用08,10什么的也行的）</p>', 171, 2, '2019-07-25 15:11:48'),
	(2, 2, '基于NPAPI接口的windows平台安全控件开发（二）', 'NPAPI接口安全控件开发 JS交互', 'img/5.png', '上面提到我们重写了NPP_GetValue函数，使NPP_GetValue去调用CPlugin中的GetScriptObject，而不是按默认流程调用GetValue（其实无所谓，改个名称而已）。所以，当插件加载完成后，浏览器会调用NPP_GetValue并将variable参数设置为NPPVpluginScriptableNPObject来查询浏览器是否支持与脚本语言的交互功能，如果插件支持交互功能，则NPP_GetValue会返回一个NPOject对象给浏览器，此时浏览器就可以通过这个NPObject对象与插件建立联系。所以在CPlugin::GetScriptObject函数中，我们要实现NPObject对象。', 156, 2, '2019-07-25 15:45:54'),
	(3, 1, 'Linux下使用libnet实现ARP攻击', NULL, 'img/5.png', ' 闲来无事，用libnet写了个简单的ARP攻击。以前感觉ARP攻击不是特别常见，不过最近工作中倒是遇到过两次ARP欺骗的事件。其实ARP欺骗的原理灰常简单滴，大部分都是在局域网中发送伪造的ARP广播包， 广播包的目的是干嘛呢？很简单，欺骗内网的所有主机，告诉受害主机，“我”是网关。 内网机器接收到这种广播包之后，会刷新自己ARP缓存表，把网关的IP和广播包中的源MAC绑定。这样攻击机器就达到了冒充网关的目的。', 54, 2, '2019-07-25 15:54:29'),
	(4, 1, '谈谈arp欺骗的那点破事', 'ARP 工作原理、ARP 攻击分析', 'img/5.png', '随着网络设备在接入市场的应用也越来越多；同时遇到的问题也越来越多样，其中最让人头疼的就是ARP的问题。', 83, 2, '2019-07-25 16:01:22'),
	(5, 1, '实时日志分析平台搭建笔记（一）', '搭建日志分析平台', 'img/5.png', ' 基于ELK构架的日志收集平台，很多公司都搭建好了，但ELK只是做到了收集存储，缺少了分析功能。博主作为信息安全从业人员，需要对所有收集的日志进行安全分析，给出处理结果，这样才算完成一个闭环。正好目前所在的公司也准备启动日志分析工作，所以最近研究了日志分析平台。日志分析平台主要目的是收集生产和办公系统中产生的各种日志（目前主要是access log和系统日志，可能后期还会包含防火墙等设备的日志），实时的做出分析，找出风险，及时预警。结合ELK以及同行老司机们的经验，目前暂时的构架如下，目前只是在完成了测试环境的简单测试demo，但还有很多未研究或者未解决的问题（例如如何将不同日志存入不同的kafka topic中）', 4, 2, '2019-07-26 16:46:28'),
	(6, 3, 'Spring Security之实现登录后跳转到登录前页面', 'spring security', NULL, '通过登录页登录后，跳转到后台首页', 15, 2, '2019-07-30 16:15:53');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;

-- 导出  表 blog.message 结构
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `content` varchar(128) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK1_user_id_to_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  blog.message 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`id`, `user_id`, `content`, `status`, `create_time`) VALUES
	(1, 2, '今天是2019年7月30日', 1, '2019-07-30 15:10:32'),
	(2, 1, 'org.springframework.web.servlet.mvc.method', 1, '2019-07-30 15:12:06'),
	(4, NULL, '猜猜我是谁', 1, '2019-07-30 15:12:46');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;

-- 导出  表 blog.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `real_name` varchar(64) DEFAULT NULL,
  `mail` varchar(64) DEFAULT NULL,
  `pic` varchar(64) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `auth` int(11) DEFAULT '0',
  `follower` int(11) DEFAULT '0',
  `fans` int(11) DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  blog.user 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `password`, `real_name`, `mail`, `pic`, `age`, `status`, `auth`, `follower`, `fans`, `create_time`) VALUES
	(1, 'security', '17726df193c5c3f906c9fd44b7895c0796975157dcf1db90eb58074e322e97e8', '信息安全部', 'security@xxx.com', 'img/2.jpg', 35, 1, 0, 56, 367, '2019-07-25 15:02:04'),
	(2, 'tridentj', '87a93ecd1bec4d9fd1f729e7337da93ae243dac846260ca80973ceef4bc96da3', '摸鱼小能手', 'tridentj@xxx.com', 'img/1.png', 25, 1, 0, 12, 23, '2019-07-25 15:05:18'),
	(3, 'test', 'd183df4a9721fe6d1267fcf086a6f0cc0b7706df565eef5be59c6c7de075d1cf', '测试账号', 'test@xxx.com', 'img/3.png', 36, 1, 0, 0, 0, '2019-07-26 11:08:56');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
