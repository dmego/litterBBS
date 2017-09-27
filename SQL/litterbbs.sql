/*
Navicat MySQL Data Transfer

Source Server         : mysql5.5
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : litterbbs

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2017-09-27 20:08:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentid` int(20) NOT NULL AUTO_INCREMENT,
  `postid` int(20) NOT NULL,
  `userid` int(20) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `comtime` varchar(20) NOT NULL,
  `flood` int(20) NOT NULL,
  PRIMARY KEY (`commentid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '23', '<p>这是谁，上来就发这种<img src=\"http://img.baidu.com/hi/jx2/j_0007.gif\"/></p>', '2017-03-03 14:32', '1');
INSERT INTO `comment` VALUES ('2', '2', '10', '<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;666<img src=\"http://img.baidu.com/hi/jx2/j_0020.gif\"/></p>', '2017-03-03 14:34', '1');
INSERT INTO `comment` VALUES ('3', '3', '11', '<p>选择自己选喜欢做的事情就对了，有兴趣，才能激发你的潜力，加油！<img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\"/> &nbsp; &nbsp; &nbsp; &nbsp;</p>', '2017-03-03 20:00', '1');
INSERT INTO `comment` VALUES ('4', '1', '12', '<p>&nbsp;音乐很好听！<img src=\"http://img.baidu.com/hi/jx2/j_0067.gif\"/></p>', '2017-03-28 11:17', '2');
INSERT INTO `comment` VALUES ('5', '2', '14', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0012.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0035.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0047.gif\" style=\"width: 58px; height: 62px;\"/></p>', '2017-04-11 15:09', '2');
INSERT INTO `comment` VALUES ('6', '3', '15', '<p>好看啊！<img src=\"http://img.baidu.com/hi/face/i_f48.gif\"/></p>', '2017-05-04 19:44', '2');
INSERT INTO `comment` VALUES ('7', '1', '16', '<p>好听啊！<img src=\"http://img.baidu.com/hi/jx2/j_0017.gif\"/></p>', '2017-05-16 21:41', '3');
INSERT INTO `comment` VALUES ('8', '2', '17', '<p>成都带不走只有你！</p>', '2017-05-16 21:51', '3');
INSERT INTO `comment` VALUES ('9', '3', '18', '<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;11111111</p>', '2017-09-14 20:02', '3');
INSERT INTO `comment` VALUES ('10', '1', '19', '<p>好听啊！<img src=\"http://img.baidu.com/hi/jx2/j_0017.gif\"/></p>', '2017-05-16 21:41', '4');
INSERT INTO `comment` VALUES ('11', '2', '20', '<p>选择自己选喜欢做的事情就对了，有兴趣，才能激发你的潜力，加油！<img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\"/> &nbsp; &nbsp; &nbsp; &nbsp;</p>', '2017-03-03 20:00', '4');
INSERT INTO `comment` VALUES ('12', '3', '22', '<p>成都带不走只有你！</p>', '2017-05-16 21:51', '4');
INSERT INTO `comment` VALUES ('15', '2', '17', '<p>tt123</p>', '2017-09-18 01:17', '5');
INSERT INTO `comment` VALUES ('16', '2', '17', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0013.gif\"/>第六楼</p>', '2017-09-18 01:19', '6');
INSERT INTO `comment` VALUES ('17', '9', '17', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0058.gif\"/></p>', '2017-09-18 01:23', '1');
INSERT INTO `comment` VALUES ('18', '9', '17', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0010.gif\"/>好困啊</p>', '2017-09-18 01:23', '2');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `noticeid` int(20) NOT NULL AUTO_INCREMENT,
  `userid` int(20) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `title` varchar(200) NOT NULL,
  `noticetime` varchar(20) NOT NULL,
  PRIMARY KEY (`noticeid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '1', '社区问答-数据结构背后的原理社区问答-数据结构背后的原理社区问答-数据结构背后的原理社区问答-数据结构背后的原理', '社区问答-数据结构背后的原理', '2016-03-17');
INSERT INTO `notice` VALUES ('2', '1', '不容错过，知识库精华资源推荐社区问答-数据结构背后的原理社区问答-数据结构背后的原理', '不容错过，知识库精华资源推荐', '2016-03-20');
INSERT INTO `notice` VALUES ('3', '1', '2016年上半年热门下载资社区问答-数据结构背后的原理', '2016年上半年热门下载资源', '2016-03-18');
INSERT INTO `notice` VALUES ('4', '1', '问答2016年3月活动开始啦！', '问答2016年3月活动开始啦！', '2016-03-19');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `postid` int(20) NOT NULL AUTO_INCREMENT,
  `userid` int(20) NOT NULL,
  `tychid` int(20) NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `replynum` int(20) NOT NULL DEFAULT '0',
  `posttime` varchar(20) NOT NULL,
  PRIMARY KEY (`postid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '1', '1', 'javaEE开发', '<p>各市（含定州、辛集）团委，省各直属团委，高校团委，企业团委，团省委机关各部室及下属单位：\r\n &nbsp; &nbsp;为深入学习贯彻习近平总书记系列重要讲话精神，扎实推动共青团改革攻坚、从严治团，组织全省各级团干部、共青团员深入学习团章、“三会两制一课”等团务知识，保持和增强团的先进性，发挥团员先锋模范作用，以良好精神风貌和优异成绩喜迎党的十九大胜利召开，团省委决定在全省开展2017年首届团务知识网络竞赛。现将有关事宜通知如下\r\n安全啊QAQ安全啊去</p><p><img src=\"/litterBBS/ueditor/jsp/upload/image/20170915/1505417619624053398.jpg\" title=\"1505417619624053398.jpg\" alt=\"MOKA-PC - ProfilePhoto.jpg\"/><img src=\"http://img.baidu.com/hi/jx2/j_0012.gif\"/>111</p>', '4', '2017-09-18 08:39');
INSERT INTO `post` VALUES ('2', '16', '1', '啊啊啊啊·', '<p>各市（含定州、辛集）团委，省各直属团委，高校团委，企业团委，团省委机关各部室及下属单位：\r\n    为深入学习贯彻习近平总书记系列重要讲话精神，扎实推动共青团改革攻坚、从严治团，组织全省各级团干部、共青团员深入学习团章、“三会两制一课”等团务知识，保持和增强团的先进性，发挥团员先锋模范作用，以良好精神风貌和优异成绩喜迎党的十九大胜利召开，团省委决定在全省开展2017年首届团务知识网络竞赛。现将有关事宜通知如下\r\n安全啊QAQ安全啊去</p><p><img src=\"/litterBBS/ueditor/jsp/upload/image/20170915/1505417619624053398.jpg\" title=\"1505417619624053398.jpg\" alt=\"MOKA-PC - ProfilePhoto.jpg\"/><img src=\"http://img.baidu.com/hi/jx2/j_0012.gif\"/></p>', '6', '2017-09-15 03:33');
INSERT INTO `post` VALUES ('3', '22', '1', '啊啊啊啊·', '<p>各市（含定州、辛集）团委，省各直属团委，高校团委，企业团委，团省委机关各部室及下属单位：\r\n    为深入学习贯彻习近平总书记系列重要讲话精神，扎实推动共青团改革攻坚、从严治团，组织全省各级团干部、共青团员深入学习团章、“三会两制一课”等团务知识，保持和增强团的先进性，发挥团员先锋模范作用，以良好精神风貌和优异成绩喜迎党的十九大胜利召开，团省委决定在全省开展2017年首届团务知识网络竞赛。现将有关事宜通知如下\r\n安全啊QAQ安全啊去</p><p><img src=\"/litterBBS/ueditor/jsp/upload/image/20170915/1505417619624053398.jpg\" title=\"1505417619624053398.jpg\" alt=\"MOKA-PC - ProfilePhoto.jpg\"/><img src=\"http://img.baidu.com/hi/jx2/j_0012.gif\"/></p>', '4', '2017-09-15 03:33');
INSERT INTO `post` VALUES ('4', '17', '1', '这两个提升至', '<p>1111111111111111111111</p><p>z杀杀杀<img src=\"http://img.baidu.com/hi/jx2/j_0001.gif\"/><img src=\"/litterBBS/ueditor/jsp/upload/image/20170915/1505445705846012146.jpeg\" title=\"1505445705846012146.jpeg\" alt=\"20141101230029_rBAkE.thumb.224_0.jpeg\"/></p>', '0', '2017-09-15 11:21');
INSERT INTO `post` VALUES ('5', '18', '1', '11111111111', '<p>1111111111111<img src=\"/litterBBS/ueditor/jsp/upload/image/20170917/1505617341332046879.jpg\" title=\"1505617341332046879.jpg\" alt=\"MOKA-PC - ProfilePhoto.jpg\"/></p>', '0', '2017-09-17 11:02');
INSERT INTO `post` VALUES ('6', '17', '1', '1111111111111', '<p>111111111111111111111111111<img src=\"http://img.baidu.com/hi/jx2/j_0045.gif\"/><img src=\"/litterBBS/ueditor/jsp/upload/image/20170917/1505637124398092311.jpg\" title=\"1505637124398092311.jpg\" alt=\"MOKA-PC - ProfilePhoto.jpg\"/></p>', '0', '2017-09-17 16:32');
INSERT INTO `post` VALUES ('7', '16', '1', '啊啊啊啊·', '<p>各市（含定州、辛集）团委，省各直属团委，高校团委，企业团委，团省委机关各部室及下属单位：\r\n    为深入学习贯彻习近平总书记系列重要讲话精神，扎实推动共青团改革攻坚、从严治团，组织全省各级团干部、共青团员深入学习团章、“三会两制一课”等团务知识，保持和增强团的先进性，发挥团员先锋模范作用，以良好精神风貌和优异成绩喜迎党的十九大胜利召开，团省委决定在全省开展2017年首届团务知识网络竞赛。现将有关事宜通知如下\r\n安全啊QAQ安全啊去</p><p><img src=\"/litterBBS/ueditor/jsp/upload/image/20170915/1505417619624053398.jpg\" title=\"1505417619624053398.jpg\" alt=\"MOKA-PC - ProfilePhoto.jpg\"/><img src=\"http://img.baidu.com/hi/jx2/j_0012.gif\"/></p>', '0', '2017-09-15 03:33');
INSERT INTO `post` VALUES ('8', '22', '1', '啊啊啊啊·', '<p>各市（含定州、辛集）团委，省各直属团委，高校团委，企业团委，团省委机关各部室及下属单位：\r\n    为深入学习贯彻习近平总书记系列重要讲话精神，扎实推动共青团改革攻坚、从严治团，组织全省各级团干部、共青团员深入学习团章、“三会两制一课”等团务知识，保持和增强团的先进性，发挥团员先锋模范作用，以良好精神风貌和优异成绩喜迎党的十九大胜利召开，团省委决定在全省开展2017年首届团务知识网络竞赛。现将有关事宜通知如下\r\n安全啊QAQ安全啊去</p><p><img src=\"/litterBBS/ueditor/jsp/upload/image/20170915/1505417619624053398.jpg\" title=\"1505417619624053398.jpg\" alt=\"MOKA-PC - ProfilePhoto.jpg\"/><img src=\"http://img.baidu.com/hi/jx2/j_0012.gif\"/></p>', '0', '2017-09-15 03:33');
INSERT INTO `post` VALUES ('9', '17', '1', '这两个提升至', '<p>1111111111111111111111</p><p>z杀杀杀<img src=\"http://img.baidu.com/hi/jx2/j_0001.gif\"/><img src=\"/litterBBS/ueditor/jsp/upload/image/20170915/1505445705846012146.jpeg\" title=\"1505445705846012146.jpeg\" alt=\"20141101230029_rBAkE.thumb.224_0.jpeg\"/></p>', '2', '2017-09-15 11:21');
INSERT INTO `post` VALUES ('10', '18', '1', '11111111111', '<p>1111111111111<img src=\"/litterBBS/ueditor/jsp/upload/image/20170917/1505617341332046879.jpg\" title=\"1505617341332046879.jpg\" alt=\"MOKA-PC - ProfilePhoto.jpg\"/></p>', '0', '2017-09-17 11:02');
INSERT INTO `post` VALUES ('11', '17', '1', '1111111111111', '<p>111111111111111111111111111<img src=\"http://img.baidu.com/hi/jx2/j_0045.gif\"/><img src=\"/litterBBS/ueditor/jsp/upload/image/20170917/1505637124398092311.jpg\" title=\"1505637124398092311.jpg\" alt=\"MOKA-PC - ProfilePhoto.jpg\"/></p>', '0', '2017-09-17 16:32');
INSERT INTO `post` VALUES ('12', '17', '1', '1111111111111', '<p>111111111111111111111111111<img src=\"http://img.baidu.com/hi/jx2/j_0045.gif\"/><img src=\"/litterBBS/ueditor/jsp/upload/image/20170917/1505637124398092311.jpg\" title=\"1505637124398092311.jpg\" alt=\"MOKA-PC - ProfilePhoto.jpg\"/></p>', '0', '2017-09-17 16:32');
INSERT INTO `post` VALUES ('14', '18', '1', '阿萨德', '<p>asdaZFD<img src=\"http://img.baidu.com/hi/jx2/j_0068.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0027.gif\"/></p>', '0', '2017-09-17 20:21');
INSERT INTO `post` VALUES ('15', '18', '5', 'javaEE要开源了', '<p>111111111111111</p><p>哈哈哈啊<img src=\"http://img.baidu.com/hi/jx2/j_0035.gif\"/></p><p>哈哈哈哈</p><p><img src=\"http://img.baidu.com/hi/tsj/t_0001.gif\"/></p>', '0', '2017-09-17 20:26');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `typeid` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `info` varchar(200) NOT NULL,
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '移动开发', '<p>这是移动开发</p>');
INSERT INTO `type` VALUES ('2', 'WEB开发', 'WEB开发');
INSERT INTO `type` VALUES ('3', '音乐分享', '音乐');
INSERT INTO `type` VALUES ('4', '视频分享', '视频');

-- ----------------------------
-- Table structure for typechild
-- ----------------------------
DROP TABLE IF EXISTS `typechild`;
CREATE TABLE `typechild` (
  `tychid` int(20) NOT NULL AUTO_INCREMENT,
  `parentid` int(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `info` varchar(200) NOT NULL,
  PRIMARY KEY (`tychid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of typechild
-- ----------------------------
INSERT INTO `typechild` VALUES ('1', '1', 'android开发', 'android开发');
INSERT INTO `typechild` VALUES ('2', '1', 'ios开发', 'ios开发');
INSERT INTO `typechild` VALUES ('3', '2', 'php基础', 'php基础');
INSERT INTO `typechild` VALUES ('4', '2', 'php框架', 'php框架');
INSERT INTO `typechild` VALUES ('5', '2', 'javaEE', 'javaEE');
INSERT INTO `typechild` VALUES ('6', '1', 'UWP开发', '<p>UWP开发</p>');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `nickname` varchar(40) NOT NULL,
  `sex` varchar(20) NOT NULL DEFAULT '未知',
  `birthday` varchar(20) NOT NULL DEFAULT '1990-1-1',
  `usericon` varchar(50) NOT NULL DEFAULT 'static/images/default.ico',
  `regtime` varchar(20) NOT NULL,
  `level` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '我是Admin', '男', '1993-6-2', '/litterBBS/static/images/face/1.png', '2017-03-02 23:22', '3');
INSERT INTO `user` VALUES ('10', 'lichangqing', '123456', 'hello', '未知', '1990-1-1', '/litterBBS/static/images/face/12.png', '2017-03-02 23:22', '1');
INSERT INTO `user` VALUES ('11', '1111', '1111', '111111', '未知', '1990-1-1', '/litterBBS/static/images/face/13.png', '2017-03-02 23:22', '1');
INSERT INTO `user` VALUES ('12', 'dengbaoling', 'd147258', 'deng', '未知', '1990-1-1', '/litterBBS/static/images/face/15.png', '2017-03- 02 23:22', '1');
INSERT INTO `user` VALUES ('14', '123456', '123456', 'clanceRen', '未知', '1990-1-1', '/litterBBS/static/images/face/12.png', '2017-03- 02 23:22', '1');
INSERT INTO `user` VALUES ('15', 'xxssa11', '1234567_', '4588', '未知', '1990-1-1', '/litterBBS/static/images/face/8.png', '2017-03- 02 23:22', '1');
INSERT INTO `user` VALUES ('16', 'a123456', '123456', '老司机12a', '未知', '1990-1-1', '/litterBBS/static/images/face/15.png', '2017-03- 02 23:22', '1');
INSERT INTO `user` VALUES ('17', 'ttt123', '123456', '成都的街头', '未知', '1990-1-1', '/litterBBS/static/images/face/11.png', '2017-03- 02 23:22', '1');
INSERT INTO `user` VALUES ('18', 'java', '1234', '千与千寻', '女', '1993-6-2', '/litterBBS/static/images/face/3.png', '2017-03- 02 23:22', '1');
INSERT INTO `user` VALUES ('19', 'dmego', '1234', '不跟你多BB', '未知', '1990-1-1', '/litterBBS/static/images/face/5.png', '2017-03- 02 23:22', '1');
INSERT INTO `user` VALUES ('20', 'tyq123', '123456', '我为代码狂', '男', '1990-1-1', '/litterBBS/static/images/face/8.png', '2017-03- 02 23:22', '1');
INSERT INTO `user` VALUES ('21', 'bigbiggirl', '12345678', 'bigbiggril', '未知', '1990-1-1', '/litterBBS/static/images/face/10.png', '2017-03- 02 23:22', '1');
INSERT INTO `user` VALUES ('22', 'lyh157', 'lyh157', '星源', '未知', '1990-1-1', '/litterBBS/static/images/face/6.png', '2017-03- 02 23:22', '1');
INSERT INTO `user` VALUES ('23', 'saraband', '123456', 'sara', '未知', '1990-1-1', '/litterBBS/static/images/face/4.png', '2017-03- 02 23:22', '1');
INSERT INTO `user` VALUES ('24', '123ghhg', 'ASD123', 'hh445', '未知', '1990-1-1', '/litterBBS/static/images/default.ico', '2017-03- 02 23:22', '1');
INSERT INTO `user` VALUES ('26', 'admin66', '123456', '你好明天d', '未知', '1990-1-1', '/litterBBS/static/images/default.ico', '2017-04-16 11:23', '1');
INSERT INTO `user` VALUES ('27', 'root12', 'tyq123', 'tyq123', '未知', '1990-1-1', '/litterBBS/static/images/default.ico', '2017-04-16 11:23', '1');
INSERT INTO `user` VALUES ('28', 'admin123', 'tyq123', '青青子衿', '女', '1999-1-1', '/litterBBS/static/images/face/9.png', '2017-05-04 11:23', '1');
INSERT INTO `user` VALUES ('29', 'tqy123', '123456', 'sdasda', '未知', '1990-1-1', '/litterBBS/static/images/default.ico', '2017-03-05 11:23', '1');
INSERT INTO `user` VALUES ('30', '19899', '1111', '11', '男', '2017-02-09', '/litterBBS/static/images/default.ico', '2017-03- 02 23:22', '1');
