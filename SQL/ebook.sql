/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : ebook

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-02-16 18:35:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_advertisement
-- ----------------------------
DROP TABLE IF EXISTS `sys_advertisement`;
CREATE TABLE `sys_advertisement` (
  `AD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOOK_ID` int(11) DEFAULT NULL,
  `IMG_ADDR` varchar(255) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `USEFUL_LIFE` int(11) DEFAULT NULL COMMENT '有效期',
  PRIMARY KEY (`AD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_advertisement
-- ----------------------------

-- ----------------------------
-- Table structure for sys_book
-- ----------------------------
DROP TABLE IF EXISTS `sys_book`;
CREATE TABLE `sys_book` (
  `BOOK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOOK_NAME` varchar(255) DEFAULT NULL,
  `AUTHOR` varchar(40) DEFAULT NULL COMMENT '作者',
  `BOOK_INFO` text COMMENT '书籍信息',
  `PRICE` decimal(10,0) DEFAULT NULL COMMENT '单价',
  `USER_ID` int(11) DEFAULT NULL,
  `CATEGORY_ID` int(11) DEFAULT NULL,
  `COVER` varchar(255) DEFAULT NULL COMMENT '封面',
  `PUBLISHING_COMPANY` varchar(255) DEFAULT NULL COMMENT '出版社',
  `USE_HOURS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`BOOK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_book
-- ----------------------------
INSERT INTO `sys_book` VALUES ('1', '西游记', '吴承恩', '《西游记》是一部中国古典神魔小说，为中国“四大名著”之一。书中讲述唐朝法师西天取经的故事，表现了惩恶扬善的古老主题。《西游记》成书于16世纪明朝中叶，自问世以来在中国及世界各地广为流传，被翻译成多种语言。西游记是中国古典四大名著之一，是最优秀的神话小说，也是一部群众创作和文人创作相结合的作品。小说以整整七回的“大闹天宫”故事开始，把孙悟空的形象提到全书首要的地位。第八至十二回写如来说法，观音访僧，魏徵斩龙，唐僧出世等故事，交待取经的缘起。从十四回到全书结束，写孙悟空被迫皈依佛教，保护唐僧取经，在八戒、沙僧协助下，一路斩妖除魔，到西天成了“正果', '40', '10', '17', 'xiyouji.jpg', '出版社名称', null);
INSERT INTO `sys_book` VALUES ('2', '红楼梦', '曹雪芹', '《红楼梦》是一部章回体长篇小说。早期仅有前八十回抄本流传，八十回后部分未完成且原稿佚失。原名《脂砚斋重评石头记》。程伟元邀请高鹗协同整理出版百二十回全本，定名《红楼梦》。亦有版本作《金玉缘》。', '40', '10', '17', 'hongloumeng.jpg', '红楼梦出版社', null);

-- ----------------------------
-- Table structure for sys_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category` (
  `CATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(40) DEFAULT NULL,
  `PARENT_C_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_category
-- ----------------------------
INSERT INTO `sys_category` VALUES ('1', '教育', null);
INSERT INTO `sys_category` VALUES ('2', '文艺', null);
INSERT INTO `sys_category` VALUES ('3', '人文社科', null);
INSERT INTO `sys_category` VALUES ('4', '童书', null);
INSERT INTO `sys_category` VALUES ('5', '经管', null);
INSERT INTO `sys_category` VALUES ('6', '励志', null);
INSERT INTO `sys_category` VALUES ('7', '科技', null);
INSERT INTO `sys_category` VALUES ('8', '生活', null);
INSERT INTO `sys_category` VALUES ('9', '刊/报', null);
INSERT INTO `sys_category` VALUES ('10', '中小学教辅', '1');
INSERT INTO `sys_category` VALUES ('11', '大中专教材', '1');
INSERT INTO `sys_category` VALUES ('12', '外语', '1');
INSERT INTO `sys_category` VALUES ('13', '英语四级', '1');
INSERT INTO `sys_category` VALUES ('14', '英语六级', '1');
INSERT INTO `sys_category` VALUES ('15', '考研', '1');
INSERT INTO `sys_category` VALUES ('16', '小说', '2');
INSERT INTO `sys_category` VALUES ('17', '文学', '2');
INSERT INTO `sys_category` VALUES ('18', '传记', '2');
INSERT INTO `sys_category` VALUES ('19', '经济管理', '5');
INSERT INTO `sys_category` VALUES ('20', '投资理财', '5');
INSERT INTO `sys_category` VALUES ('21', '市场/销售', '5');
INSERT INTO `sys_category` VALUES ('22', '会计', '5');
INSERT INTO `sys_category` VALUES ('23', '休闲', '8');
INSERT INTO `sys_category` VALUES ('24', '旅游', '8');
INSERT INTO `sys_category` VALUES ('25', '美食', '8');
INSERT INTO `sys_category` VALUES ('26', '美妆', '8');

-- ----------------------------
-- Table structure for sys_comment
-- ----------------------------
DROP TABLE IF EXISTS `sys_comment`;
CREATE TABLE `sys_comment` (
  `COMMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `BOOK_ID` int(11) DEFAULT NULL,
  `CONTENT` text,
  `BOOK_COMMENT_LEVEL` int(11) DEFAULT NULL COMMENT '书本描述分数',
  `SELLER_COMMENT_LEVEL` int(11) DEFAULT NULL COMMENT '卖家服务评价',
  `UPS_COMMENT_LEVEL` int(11) DEFAULT NULL COMMENT '发货速度评价',
  `COMMENT_LEVEL` int(11) DEFAULT NULL COMMENT '''0''差评；''1''中评；“2”好评',
  PRIMARY KEY (`COMMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_comment
-- ----------------------------

-- ----------------------------
-- Table structure for sys_inventory
-- ----------------------------
DROP TABLE IF EXISTS `sys_inventory`;
CREATE TABLE `sys_inventory` (
  `INVENTORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOOK_ID` int(11) DEFAULT NULL,
  `TOTAL_NUM` int(11) DEFAULT NULL COMMENT '总量',
  `SOLD_NUM` int(11) DEFAULT NULL COMMENT '售出数量',
  `REMAIN_NUM` int(11) DEFAULT NULL COMMENT '剩余数量',
  PRIMARY KEY (`INVENTORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_inventory
-- ----------------------------

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order` (
  `ORDER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUY_USER_ID` int(11) DEFAULT NULL COMMENT '买家',
  `STATUS` int(11) DEFAULT '0' COMMENT '''0''待付款；’1‘待发货；’2‘待收货；’3‘待评价；’4‘完成',
  `TOTAL_PRICE` decimal(10,0) DEFAULT NULL COMMENT '总价',
  `SELL_USER_ID` int(11) DEFAULT NULL COMMENT '卖家',
  `DATE` datetime DEFAULT NULL COMMENT '日期',
  `BOOK_PRICE` decimal(10,0) DEFAULT NULL COMMENT '商品总价',
  `FREIGHT` decimal(10,0) DEFAULT NULL COMMENT '运费',
  PRIMARY KEY (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_order
-- ----------------------------

-- ----------------------------
-- Table structure for sys_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_detail`;
CREATE TABLE `sys_order_detail` (
  `ORDER_DETAIL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(11) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `SELLER_INFO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ORDER_DETAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sys_shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `sys_shopping_cart`;
CREATE TABLE `sys_shopping_cart` (
  `SHOPPING_CART_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOOK_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `TOTAL` decimal(10,0) DEFAULT NULL COMMENT '总价',
  `COUNTS` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`SHOPPING_CART_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(40) DEFAULT NULL,
  `EMAIL` varchar(150) DEFAULT NULL,
  `PHONE` int(11) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `USER_ROLE` int(11) DEFAULT '2' COMMENT '“0”是admin；“1”是管理员；“1”是卖家；“2”是纯买家',
  `PASS_WORD` varchar(255) DEFAULT NULL,
  `ACCOUNT` int(255) DEFAULT '0',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `IDIndex` (`PHONE`,`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', 'Jan', 'Jan@', '111', null, '1', '111111', '0');
INSERT INTO `sys_user` VALUES ('3', 'Anne', 'Anne@', '122', null, '2', '111111', '0');
INSERT INTO `sys_user` VALUES ('4', 'Bob', 'Bob@', '123', null, '3', '111111', '0');
INSERT INTO `sys_user` VALUES ('10', 'test', '', '1113', null, '2', '96e79218965eb72c92a549dd5a330112', '0');
INSERT INTO `sys_user` VALUES ('12', 'admin', '', '111111', null, '2', '21232f297a57a5a743894a0e4a801fc3', '0');

-- ----------------------------
-- Table structure for sys_use_hours
-- ----------------------------
DROP TABLE IF EXISTS `sys_use_hours`;
CREATE TABLE `sys_use_hours` (
  `USE_HOURS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USE__HOURS_STATUS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USE_HOURS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_use_hours
-- ----------------------------
INSERT INTO `sys_use_hours` VALUES ('1', '全新');
INSERT INTO `sys_use_hours` VALUES ('2', '九成');
INSERT INTO `sys_use_hours` VALUES ('3', '八成');
INSERT INTO `sys_use_hours` VALUES ('4', '七成');
INSERT INTO `sys_use_hours` VALUES ('5', '六成');
INSERT INTO `sys_use_hours` VALUES ('6', '五成');
INSERT INTO `sys_use_hours` VALUES ('7', '破烂');

-- ----------------------------
-- Table structure for sys_wish_list
-- ----------------------------
DROP TABLE IF EXISTS `sys_wish_list`;
CREATE TABLE `sys_wish_list` (
  `WISH_LIST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL COMMENT '收藏者',
  `BOOK_ID` int(11) DEFAULT NULL COMMENT '收藏的商品',
  `USER_BOOK_ID` int(11) DEFAULT NULL COMMENT '收藏的用户',
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`WISH_LIST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_wish_list
-- ----------------------------
