/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50629
Source Host           : 192.168.2.9:3306
Source Database       : ucoin

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2016-05-12 21:03:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chinapost_customer
-- ----------------------------
DROP TABLE IF EXISTS `chinapost_customer`;
CREATE TABLE `chinapost_customer` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '会员编号',
  `idCard_no` varchar(22) NOT NULL COMMENT '身份证号',
  `password` varchar(500) DEFAULT NULL COMMENT '密码',
  `paykey` varchar(50) DEFAULT NULL COMMENT '支付密码',
  `phone_no` varchar(100) DEFAULT NULL COMMENT '用户名',
  `mobile_checked` tinyint(4) DEFAULT '0' COMMENT ' 手机是否验证',
  `fullname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `male` tinyint(4) DEFAULT NULL COMMENT '是否为男性',
  `img_url` varchar(255) DEFAULT NULL,
  `contact_phone` varchar(20) DEFAULT NULL,
  `contact_addr` varchar(40) DEFAULT NULL COMMENT '联系地址',
  `non_disabled` tinyint(4) DEFAULT NULL COMMENT '是否禁用',
  `is_active` tinyint(4) DEFAULT '0' COMMENT '是否激活',
  `manager_no` varchar(10) DEFAULT NULL COMMENT '客户经理号',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `tag` varchar(255) DEFAULT NULL COMMENT 'tag(标签，json格式)',
  `province_id` bigint(20) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `district_id` bigint(20) DEFAULT NULL,
  `street_id` bigint(20) DEFAULT NULL,
  `addr` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_IDCARD` (`idCard_no`) USING BTREE,
  UNIQUE KEY `IDX_PHONE` (`phone_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=510518 DEFAULT CHARSET=utf8 COMMENT='中国邮政用户';

-- ----------------------------
-- Table structure for credit_order_log
-- ----------------------------
DROP TABLE IF EXISTS `credit_order_log`;
CREATE TABLE `credit_order_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `back_order_id` bigint(20) DEFAULT NULL,
  `log_str` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5360 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for enterprise_business_type
-- ----------------------------
DROP TABLE IF EXISTS `enterprise_business_type`;
CREATE TABLE `enterprise_business_type` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_back_order
-- ----------------------------
DROP TABLE IF EXISTS `np_back_order`;
CREATE TABLE `np_back_order` (
  `back_order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '退单ID',
  `order_id` bigint(20) DEFAULT NULL,
  `back_order_code` bigint(20) DEFAULT NULL COMMENT '退单编号',
  `back_time` timestamp NULL DEFAULT NULL COMMENT '退单时间',
  `back_remark` varchar(500) DEFAULT NULL COMMENT '退单原因',
  `back_price` decimal(10,2) DEFAULT NULL COMMENT '退单金额',
  `back_check` varchar(30) DEFAULT NULL COMMENT '0:退货申请 1:同意退货 2:拒绝退货 3:待收货 4:退单结束 6:审核退款 7:拒绝退款 8:收货失败 9:待客户填写物流地址 10:退款成功 11:退货待退款 12:退款待退款',
  `back_del_flag` enum('0','1') DEFAULT '0' COMMENT '是否删除',
  `back_real_name` varchar(30) DEFAULT NULL COMMENT '真实姓名',
  `back_bank_name` varchar(50) DEFAULT NULL COMMENT '银行',
  `back_bank_account` varchar(50) DEFAULT NULL COMMENT '银行账户',
  `dealer_type` enum('0','1') DEFAULT '0' COMMENT '第三方0  经销商1',
  `business_id` bigint(10) DEFAULT NULL COMMENT '商家ID',
  `author_name` varchar(30) DEFAULT NULL COMMENT '操作人',
  `author_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `author_log` varchar(500) DEFAULT NULL COMMENT '操作记录',
  `back_collect_phone` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `back_collect_address` varchar(15) DEFAULT NULL COMMENT '联系地址',
  `back_collect_person` varchar(15) DEFAULT NULL COMMENT '联系人',
  `back_status` bigint(10) DEFAULT NULL COMMENT '退款状态',
  `is_back` enum('1','2') DEFAULT NULL COMMENT '是否退货 1 退货 2 退款',
  `back_reason` enum('1','2','3','4') DEFAULT NULL COMMENT '退单原因 1 不想买了 2 收货人信息有误 3 商品损坏 4 其他',
  `apply_credentials` varchar(255) DEFAULT NULL COMMENT '申请凭据 1 有发票 2 有质检报告 3 没有任何凭据',
  `upload_documents` varchar(255) DEFAULT NULL COMMENT '上传凭证',
  `back_way` enum('0','1') DEFAULT '1' COMMENT '商品返回方式 0 快递',
  `back_gid_gsum` varchar(100) DEFAULT NULL COMMENT '需要退货的商品id和商品的数量',
  `customer_id` bigint(10) DEFAULT NULL COMMENT '保存会员的Id 这个退单是属于哪个会员的',
  `mall_id` int(10) DEFAULT '0' COMMENT '0:商城平台商品，非0：其他商城ID',
  PRIMARY KEY (`back_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5362 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_back_order_log
-- ----------------------------
DROP TABLE IF EXISTS `np_back_order_log`;
CREATE TABLE `np_back_order_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '退单日志id',
  `back_log_person` varchar(255) DEFAULT NULL COMMENT '退单操作日志者',
  `back_log_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '退单操作日志时间',
  `back_log_status` enum('7','6','5','4','3','2','9','8','1') DEFAULT NULL COMMENT '退单操作日志类型(1 顾客 申请退货 2 admin 审核通过 3admin 申请审核不通过 4 客户 待填写快递单号 5 admin 确认收货 6 admin 收货失败 7 admin 退款 8 admin 同意退款 9 admin 拒绝退款)',
  `back_order_id` bigint(20) DEFAULT NULL COMMENT '退单id',
  `back_remark` varchar(255) DEFAULT NULL COMMENT '退单备注',
  `customer_remark` varchar(255) DEFAULT NULL COMMENT '给客户留言',
  `mall_remark` varchar(255) DEFAULT NULL COMMENT '商家留言',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5271 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for np_codex
-- ----------------------------
DROP TABLE IF EXISTS `np_codex`;
CREATE TABLE `np_codex` (
  `codex_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codex_name` varchar(50) DEFAULT NULL COMMENT '促销类型名称',
  `codex_des` varchar(500) DEFAULT NULL COMMENT '促销类型描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 ',
  `mod_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `flag` enum('0','1') DEFAULT NULL COMMENT '标记',
  `codex_type` enum('1','2','3','4','5','6','7','8','9','11','12','13','14','15','10') DEFAULT NULL COMMENT '规则类型 1直降',
  `codex_status` bigint(20) DEFAULT NULL COMMENT '0、单品优惠  1、活动优惠',
  `codex_img` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`codex_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='规则';

-- ----------------------------
-- Table structure for np_comment
-- ----------------------------
DROP TABLE IF EXISTS `np_comment`;
CREATE TABLE `np_comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `customer_id` bigint(20) NOT NULL COMMENT '会员编号 ',
  `goods_id` bigint(20) NOT NULL COMMENT '商品编号',
  `goods_name` varchar(120) DEFAULT NULL,
  `comment_score` decimal(2,1) NOT NULL DEFAULT '0.0' COMMENT '综合评分',
  `comment_content` varchar(255) DEFAULT NULL COMMENT '评论内容 ',
  `is_display` enum('0','1') NOT NULL DEFAULT '0' COMMENT '是否显示(0否1是)',
  `is_anonymous` enum('0','1') NOT NULL DEFAULT '0' COMMENT '是否匿名(0否1是) ',
  `publish_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发表时间 ',
  `publish_ip` varchar(15) DEFAULT NULL COMMENT '发表人IP',
  `comment_contact` varchar(30) DEFAULT NULL COMMENT '联系人',
  `modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更改时间',
  `del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记',
  `is_consult` enum('0','1') NOT NULL DEFAULT '0' COMMENT '是否咨询',
  `consult_item` varchar(30) DEFAULT NULL COMMENT '咨询项目',
  `comment_type` enum('0','1','2') DEFAULT NULL COMMENT '评论类型 0 好评 1中评 2差评',
  `customer_nickname` varchar(30) DEFAULT NULL COMMENT '昵称',
  `comment_tag` varchar(255) DEFAULT NULL COMMENT '评论标签',
  `sattitude_score` decimal(2,1) NOT NULL DEFAULT '0.0' COMMENT '服务态度评分',
  `dgoods_score` decimal(2,1) NOT NULL DEFAULT '0.0' COMMENT '发货速度评分',
  `order_goods_id` bigint(20) DEFAULT NULL COMMENT '订单货品编号',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7924 DEFAULT CHARSET=utf8 COMMENT='会员商品评论表 ';

-- ----------------------------
-- Table structure for np_customer
-- ----------------------------
DROP TABLE IF EXISTS `np_customer`;
CREATE TABLE `np_customer` (
  `customer_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '会员编号',
  `customer_username` varchar(1024) DEFAULT NULL COMMENT '用户名',
  `customer_password` varchar(500) DEFAULT NULL COMMENT '密码',
  `customer_nickname` varchar(1024) DEFAULT NULL COMMENT '昵称',
  `is_mobile` enum('0','2','1') DEFAULT '0' COMMENT ' 手机是否验证',
  `is_email` enum('0','1') DEFAULT '0' COMMENT '邮箱是否验证',
  `is_flag` enum('0','2','1') DEFAULT '0' COMMENT '是否禁用',
  `login_ip` varchar(15) DEFAULT '0.0.0.0' COMMENT '登录IP',
  `login_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '登录时间',
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `modified_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记',
  `del_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `customer_img` varchar(255) DEFAULT NULL COMMENT '头像',
  `captcha` varchar(15) DEFAULT NULL COMMENT '手机验证码',
  `aead_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '发送验证码的时间 30分钟失效',
  `pwd_captcha` varchar(75) DEFAULT NULL COMMENT '邮件验证码',
  `pwd_aead_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '发送邮箱验证码的时间 120分钟失效',
  `third_id` bigint(20) DEFAULT NULL COMMENT '第三方编号',
  `is_seller` enum('0','1','2','3') DEFAULT '0' COMMENT '0:前台注册用户,1:通过审核的商家,2:商家的员工,3:第三方注册的用户',
  `is_temp_cust` enum('0','1','2') DEFAULT '0' COMMENT '是否是临时用户，0是，1不是',
  `login_key` varchar(50) DEFAULT NULL,
  `is_site_manager` enum('0','1') DEFAULT '0' COMMENT '是否是前端管理员：0：不是 1：是',
  `login_error_count` bigint(10) DEFAULT '0' COMMENT '记录当前会员登陆的错误次数',
  `login_lock_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '登陆错误账户锁定时间',
  `user_unique_code` varchar(128) DEFAULT NULL COMMENT '用户唯一code,当为公有云同步用户时值为userId,否则为customerId',
  `user_salt_val` varchar(128) DEFAULT NULL COMMENT '盐值,用于密码加密',
  `customer_pay_password` varchar(50) DEFAULT NULL COMMENT '支付密码',
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `IDX_NAME` (`customer_username`(255)) USING BTREE,
  KEY `inx_del_flag_is_seller` (`is_flag`,`is_seller`) USING BTREE,
  KEY `IDX_CREATE_TIME` (`create_time`),
  KEY `IDX_NAME_PASSWORD` (`customer_username`(255),`customer_password`(255),`del_flag`) USING BTREE,
  KEY `IDX_IS_SELLER` (`is_seller`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=510487 DEFAULT CHARSET=utf8 COMMENT='��Ա��';

-- ----------------------------
-- Table structure for np_customer_address
-- ----------------------------
DROP TABLE IF EXISTS `np_customer_address`;
CREATE TABLE `np_customer_address` (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '��ַ��� ���� ',
  `customer_id` bigint(20) NOT NULL COMMENT '��ԱID',
  `address_name` varchar(30) DEFAULT NULL,
  `address_province` varchar(30) DEFAULT NULL COMMENT 'ʡ',
  `address_city` varchar(30) DEFAULT NULL,
  `address_county` varchar(30) DEFAULT NULL,
  `address_detail` varchar(75) DEFAULT NULL,
  `address_moblie` varchar(30) DEFAULT NULL,
  `address_phone` varchar(30) DEFAULT NULL COMMENT '�绰����',
  `address_zip` varchar(30) DEFAULT NULL COMMENT '�������� ',
  `address_pay` varchar(30) DEFAULT NULL COMMENT '֧����ʽ',
  `address_ship` varchar(30) DEFAULT NULL COMMENT '���ͷ�ʽ',
  `address_time` varchar(30) DEFAULT NULL COMMENT '�ͻ�ʱ��',
  `address_bill_type` varchar(30) DEFAULT NULL COMMENT '��Ʊ���� ',
  `address_bill_title` varchar(30) DEFAULT NULL COMMENT '��Ʊ̧ͷ',
  `address_bill_content` varchar(255) DEFAULT NULL COMMENT '��Ʊ����',
  `address_confirm` varchar(30) DEFAULT NULL COMMENT '�Ƿ��ͻ��绰ȷ��',
  `address_alias` varchar(30) DEFAULT NULL COMMENT '����',
  `is_default` enum('0','1') DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `del_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'ɾ',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT 'ɾ�����(0�� 1��)',
  `address_street` varchar(30) DEFAULT NULL,
  `address_telephone` varchar(20) DEFAULT NULL COMMENT '固定电话',
  `address_email` varchar(255) DEFAULT NULL COMMENT '收货邮箱',
  PRIMARY KEY (`address_id`),
  KEY `IDX_CUSTOMER_ID` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=307418 DEFAULT CHARSET=utf8 COMMENT='��Ա�ջ���ַ��';

-- ----------------------------
-- Table structure for np_customer_browserecord
-- ----------------------------
DROP TABLE IF EXISTS `np_customer_browserecord`;
CREATE TABLE `np_customer_browserecord` (
  `like_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `customer_id` bigint(20) NOT NULL COMMENT '会员ID ',
  `goods_id` mediumint(8) NOT NULL COMMENT '商品ID ',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记(0否 1是)',
  `is_mobile` enum('1','0') DEFAULT '0' COMMENT '是否是手机浏览 0：pc端 1：移动端',
  PRIMARY KEY (`like_id`),
  KEY `IDX_CUSTOMER_ID_GOODS_ID` (`customer_id`,`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=170881 DEFAULT CHARSET=utf8 COMMENT='会员浏览记录表';

-- ----------------------------
-- Table structure for np_customer_consume
-- ----------------------------
DROP TABLE IF EXISTS `np_customer_consume`;
CREATE TABLE `np_customer_consume` (
  `balance_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '余额ID',
  `customer_id` bigint(20) NOT NULL COMMENT '会员ID',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `balance_num` decimal(20,2) DEFAULT '0.00' COMMENT '金额',
  `balance_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `balance_type` enum('3','2','1','0') NOT NULL DEFAULT '0' COMMENT '余额类型(0待审核 1支出 2存入 3 退款)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记(0否 1是)',
  `pay_type` enum('0','1','2','3','4') DEFAULT NULL COMMENT '支付方式（0,支付宝1，银联）',
  PRIMARY KEY (`balance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22619 DEFAULT CHARSET=utf8 COMMENT='会员充值消费记录表';

-- ----------------------------
-- Table structure for np_customer_follow
-- ----------------------------
DROP TABLE IF EXISTS `np_customer_follow`;
CREATE TABLE `np_customer_follow` (
  `follow_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关注ID',
  `customer_id` bigint(20) NOT NULL COMMENT '会员ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `follow_price` decimal(20,3) DEFAULT '0.000' COMMENT '关注时价格',
  `follow_tag` varchar(255) DEFAULT NULL COMMENT '关注标签',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `del_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记',
  `district_id` bigint(20) DEFAULT NULL COMMENT '当前所关注商品对应的区县Id',
  PRIMARY KEY (`follow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2008 DEFAULT CHARSET=utf8 COMMENT='会员产品关注表';

-- ----------------------------
-- Table structure for np_customer_info
-- ----------------------------
DROP TABLE IF EXISTS `np_customer_info`;
CREATE TABLE `np_customer_info` (
  `info_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '��Ա��ϢID',
  `customer_id` bigint(20) NOT NULL COMMENT '会员ID',
  `info_realname` varchar(30) DEFAULT NULL COMMENT '真实姓名',
  `info_cardid` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `info_gender` enum('0','1','2') DEFAULT '0' COMMENT '性别(0保密1男2女)',
  `point_level_id` bigint(20) DEFAULT NULL COMMENT '等级编号',
  `point_level_name` varchar(30) DEFAULT NULL COMMENT '会员等级名称',
  `info_birthday` varchar(30) DEFAULT NULL COMMENT '生日',
  `info_regip` varchar(15) DEFAULT NULL COMMENT '注册IP',
  `info_province` varchar(30) DEFAULT NULL COMMENT '省份',
  `info_city` varchar(30) DEFAULT NULL COMMENT '城市',
  `info_county` varchar(30) DEFAULT NULL COMMENT '区县',
  `infoStreet` varchar(30) DEFAULT NULL COMMENT '街道',
  `info_address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `info_marital` enum('0','1','2') DEFAULT '0' COMMENT '婚姻状况(0保密1已婚2未婚) ',
  `info_salary` varchar(20) DEFAULT '-1' COMMENT '月收入',
  `info_interest` varchar(255) DEFAULT NULL COMMENT '兴趣爱好',
  `info_email` varchar(30) DEFAULT NULL COMMENT '会员邮箱',
  `info_mobile` varchar(30) DEFAULT NULL COMMENT '会员手机',
  `balance_sum` decimal(20,3) DEFAULT '0.000' COMMENT '账户余额',
  `info_point_sum` int(8) DEFAULT '0' COMMENT '会员总积分',
  `info_type` int(2) DEFAULT NULL COMMENT '会员类型',
  `info_register_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `modified_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间 ',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记(0否 1是)',
  `info_phone` varchar(30) DEFAULT NULL COMMENT '固定电话',
  `info_zip` varchar(30) DEFAULT NULL COMMENT '邮编',
  `del_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间 ',
  `info_qq` varchar(30) DEFAULT NULL,
  `wangwangNo` varchar(30) DEFAULT NULL,
  `info_profession` varchar(30) DEFAULT NULL COMMENT '职业',
  `info_education` varchar(30) DEFAULT NULL COMMENT '教育程度',
  PRIMARY KEY (`info_id`),
  KEY `inx_customer_id` (`customer_id`) USING BTREE,
  KEY `IDX_INFO_EMAIL` (`info_email`),
  KEY `IDX_INFO_MOBILE` (`info_mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=508125 DEFAULT CHARSET=utf8 COMMENT='��Ա��չ��';

-- ----------------------------
-- Table structure for np_customer_insideletter
-- ----------------------------
DROP TABLE IF EXISTS `np_customer_insideletter`;
CREATE TABLE `np_customer_insideletter` (
  `letter_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '站内信ID',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '发表人ID',
  `receive_customer_id` bigint(20) DEFAULT NULL COMMENT '接收人ID',
  `letter_title` varchar(75) DEFAULT NULL COMMENT '站内信标题',
  `letter_content` varchar(255) DEFAULT NULL COMMENT '站内信内容 ',
  `letter_ip` varchar(15) DEFAULT '0.0.0.0' COMMENT '发表人IP',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '回复ID 设置此信回复的父信 默认null',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发表时间',
  `del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记',
  `is_read` enum('0','1') DEFAULT NULL COMMENT '是否已读',
  PRIMARY KEY (`letter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='会员站内信表';

-- ----------------------------
-- Table structure for np_customer_point_level
-- ----------------------------
DROP TABLE IF EXISTS `np_customer_point_level`;
CREATE TABLE `np_customer_point_level` (
  `point_lelvel_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `point_level_name` varchar(30) NOT NULL COMMENT '等级名称',
  `point_need` varchar(30) NOT NULL COMMENT '所需最低积分',
  `point_discount` decimal(8,2) DEFAULT NULL COMMENT '等级折扣',
  `is_default` enum('0','1') NOT NULL COMMENT '是否默认，0否，1是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记，0未删除，1已删除',
  PRIMARY KEY (`point_lelvel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COMMENT='��Ա���ֵȼ�';

-- ----------------------------
-- Table structure for np_educationinfo
-- ----------------------------
DROP TABLE IF EXISTS `np_educationinfo`;
CREATE TABLE `np_educationinfo` (
  `education_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '教育编号',
  `school_type` varchar(50) DEFAULT NULL COMMENT '学校类型（大学、高中、初中、小学）',
  `school_name` varchar(255) DEFAULT NULL COMMENT '学校名称',
  `class_name` varchar(255) DEFAULT NULL COMMENT '学院或班级名称',
  `come_time` varchar(255) DEFAULT NULL COMMENT '入学时间',
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `modified_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记',
  `del_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `customer_id` bigint(20) NOT NULL COMMENT '外键会员表（会员编号）',
  PRIMARY KEY (`education_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='教育信息';

-- ----------------------------
-- Table structure for np_freight_express
-- ----------------------------
DROP TABLE IF EXISTS `np_freight_express`;
CREATE TABLE `np_freight_express` (
  `distribution_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `freight_template_id` bigint(10) DEFAULT NULL COMMENT '模板ID',
  `log_com_id` bigint(10) DEFAULT NULL COMMENT '快递公司ID',
  `express_start` bigint(10) DEFAULT '0' COMMENT '默认首重/件',
  `express_postage` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `express_plus_n1` bigint(10) DEFAULT NULL COMMENT '续重/续件',
  `express_postageplus` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `express_delflag` enum('1','0') DEFAULT '0',
  PRIMARY KEY (`distribution_id`)
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_freight_express_all
-- ----------------------------
DROP TABLE IF EXISTS `np_freight_express_all`;
CREATE TABLE `np_freight_express_all` (
  `express_area_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `express_area` varchar(3000) DEFAULT NULL COMMENT '城市ID 逗号隔开',
  `distribution_id` bigint(10) DEFAULT NULL COMMENT '运送方式ID',
  `express_start` bigint(10) DEFAULT '0' COMMENT '默认首重/件',
  `express_postage` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `express_plus_n1` bigint(10) DEFAULT NULL COMMENT '续重/续件',
  `express_postageplus` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `express_delflag` enum('1','0') CHARACTER SET utf8 DEFAULT '0',
  PRIMARY KEY (`express_area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for np_freight_template
-- ----------------------------
DROP TABLE IF EXISTS `np_freight_template`;
CREATE TABLE `np_freight_template` (
  `freight_template_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `freight_template_name` varchar(30) DEFAULT NULL,
  `freight_city_id` bigint(10) DEFAULT NULL COMMENT '宝贝所在市',
  `freight_province_id` bigint(10) DEFAULT NULL COMMENT '宝贝所在省',
  `freight_county_id` bigint(10) DEFAULT NULL COMMENT '宝贝所在区县',
  `freight_package_mail` enum('0','1') DEFAULT '0' COMMENT '0 买家承担运费 1卖家承担运费',
  `freight_methods` enum('0','1') DEFAULT '0' COMMENT '0计件 1计重',
  `freight_restri_area` enum('1','0') DEFAULT '0' COMMENT '区域受限   0不支持 1支持',
  `freight_is_default` enum('1','0') DEFAULT '0' COMMENT '是否默认',
  `freight_del_flag` enum('1','0') DEFAULT '0' COMMENT '是否删除',
  `freight_third_id` bigint(10) DEFAULT NULL COMMENT '第三方ID 如果为Boss的 为0',
  `freight_create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `freight_modify_time` timestamp NULL DEFAULT NULL,
  `freight_remark` varchar(150) DEFAULT NULL COMMENT '备注',
  `freight_no_delete` enum('1','0') DEFAULT '0' COMMENT '是否可删除',
  PRIMARY KEY (`freight_template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_goods
-- ----------------------------
DROP TABLE IF EXISTS `np_goods`;
CREATE TABLE `np_goods` (
  `goods_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID ',
  `cat_id` bigint(20) DEFAULT NULL COMMENT '商品分类ID',
  `type_id` bigint(20) DEFAULT NULL COMMENT '类型ID',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `goods_subtitle` varchar(256) DEFAULT NULL COMMENT '商品副标题 ',
  `goods_no` varchar(120) DEFAULT NULL COMMENT '商品编号',
  `goods_keywords` varchar(255) DEFAULT NULL COMMENT '商品关键词',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌',
  `goods_brief` varchar(255) DEFAULT NULL COMMENT '商品简介',
  `goods_added` enum('0','1') DEFAULT NULL COMMENT '立即上架',
  `goods_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上架时间',
  `goods_price` decimal(20,2) DEFAULT NULL COMMENT '销售价',
  `goods_recom` enum('0','1') DEFAULT NULL COMMENT '商品推荐',
  `goods_img` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `goods_score` decimal(10,2) DEFAULT NULL COMMENT '商品评分 ',
  `goods_deno` varchar(30) DEFAULT '个' COMMENT '计价单位 ',
  `goods_detail_desc` text COMMENT '详细介绍 ',
  `goods_seo_title` varchar(128) DEFAULT NULL COMMENT 'SEO title',
  `goods_seo_keyword` varchar(256) DEFAULT NULL COMMENT 'SEO keyword',
  `goods_seo_desc` varchar(512) DEFAULT NULL COMMENT 'SEO Desc',
  `goods_prom` enum('0','1') DEFAULT NULL COMMENT '是否促销 ',
  `goods_info_instocksell` enum('0','1') DEFAULT NULL COMMENT '无库存也可销售',
  `goods_usecoupon` enum('0','1') DEFAULT NULL COMMENT '是否允许使用优惠劵',
  `goods_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `goods_create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `goods_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `goods_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `goods_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `goods_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `goods_delflag` enum('0','1') DEFAULT NULL COMMENT '是否删除',
  `goods_belo` bigint(30) DEFAULT '0',
  `goods_belo_name` varchar(120) DEFAULT NULL,
  `is_third` enum('1','2','0') DEFAULT '0' COMMENT '1:第三方商家商品  2:B2B商品 0:后台Boss商品',
  `third_cate_id` bigint(30) DEFAULT NULL,
  `mobile_desc` text COMMENT '手机版详细介绍',
  `is_third_audit_used` enum('0','1') DEFAULT NULL COMMENT '第三方审核是否开启 0不开启 1开启',
  `audit_status` enum('0','1','2','3') DEFAULT '0' COMMENT '审核状态(0未审核、审核通过  1审核中 2审核不通过 3需审核)',
  `refuse_reason` varchar(255) DEFAULT NULL COMMENT '审核拒绝原因',
  `freight_template_id` int(30) DEFAULT NULL COMMENT '运费模板id',
  PRIMARY KEY (`goods_id`),
  KEY `IDX_CAT_ID_BRAND_ID` (`cat_id`,`brand_id`),
  KEY `IDX_CAT_ID` (`cat_id`),
  KEY `IDX_BRAND_ID_CAT_ID` (`brand_id`,`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1886 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Table structure for np_goods_brand
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_brand`;
CREATE TABLE `np_goods_brand` (
  `brand_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `brand_nickname` varchar(30) DEFAULT NULL COMMENT '品牌别名',
  `brand_name` varchar(30) NOT NULL COMMENT '品牌名称',
  `brand_url` varchar(128) DEFAULT NULL COMMENT '品牌网址 ',
  `brand_logo` varchar(255) DEFAULT NULL COMMENT '品牌LOGO',
  `brand_prom_index` enum('0','1') DEFAULT '0' COMMENT '推荐到首页',
  `brand_sort` mediumint(3) DEFAULT NULL COMMENT '排序',
  `brand_seo_title` varchar(128) DEFAULT ''' ''' COMMENT 'SEO title	',
  `brand_seo_keyword` varchar(256) DEFAULT ''' ''' COMMENT 'SEO keyword',
  `brand_seo_desc` varchar(512) DEFAULT ''' ''' COMMENT 'SEO Desc',
  `brand_desc` text COMMENT '品牌介绍',
  `brand_delflag` enum('0','1') DEFAULT NULL COMMENT '删除标记',
  `brand_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `brand_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `brand_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `brand_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `brand_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `brand_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `_MASK_TO_V2` datetime DEFAULT NULL,
  PRIMARY KEY (`brand_id`),
  KEY `IDX_7dc4dfdd7fc547e38e0af9d29d5ccf0e` (`_MASK_TO_V2`)
) ENGINE=InnoDB AUTO_INCREMENT=715 DEFAULT CHARSET=utf8 COMMENT='商品品牌表';

-- ----------------------------
-- Table structure for np_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_category`;
CREATE TABLE `np_goods_category` (
  `cat_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(50) DEFAULT NULL,
  `cat_parent_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `cat_sort` mediumint(9) DEFAULT NULL,
  `cat_grade` mediumint(9) DEFAULT NULL,
  `cat_seo_title` varchar(128) DEFAULT NULL,
  `cat_seo_keyword` varchar(256) DEFAULT NULL,
  `cat_seo_desc` varchar(512) DEFAULT NULL,
  `cat_is_show` enum('0','1') DEFAULT NULL,
  `cat_delflag` enum('0','1') DEFAULT NULL,
  `cat_create_name` varchar(30) DEFAULT NULL,
  `cat_create_time` datetime DEFAULT NULL,
  `cat_modified_name` varchar(30) DEFAULT NULL,
  `cat_modified_time` datetime DEFAULT NULL,
  `cat_del_name` varchar(30) DEFAULT NULL,
  `cat_del_time` datetime DEFAULT NULL,
  `cat_rate` decimal(3,2) DEFAULT NULL,
  `cat_model` enum('1','0') DEFAULT NULL,
  `_MASK_TO_V2` datetime DEFAULT NULL,
  PRIMARY KEY (`cat_id`),
  KEY `IDX_efd1081973f34b55acd6129de6d51d26` (`_MASK_TO_V2`)
) ENGINE=InnoDB AUTO_INCREMENT=5946 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_goods_image
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_image`;
CREATE TABLE `np_goods_image` (
  `goods_img_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID ',
  `goods_info_id` bigint(20) DEFAULT NULL COMMENT '货品ID ',
  `image_in_name` varchar(128) DEFAULT NULL COMMENT '中图图片名称',
  `image_thum_name` varchar(128) DEFAULT NULL COMMENT '小图图片名称',
  `image_big_name` varchar(128) DEFAULT NULL COMMENT '大图图片名称',
  `image_artwork_name` varchar(128) DEFAULT NULL COMMENT '原图图片名称',
  `goods_img_sort` mediumint(3) DEFAULT NULL COMMENT '排序',
  `goods_img_delflag` enum('0','1') DEFAULT NULL COMMENT '是否删除',
  `goods_img_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `goods_img_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `goods_img_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `goods_img_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `goods_img_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `goods_img_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  PRIMARY KEY (`goods_img_id`),
  KEY `IDX_GOODS_INFO_ID` (`goods_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5522 DEFAULT CHARSET=utf8 COMMENT='货品图片表';

-- ----------------------------
-- Table structure for np_goods_info
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_info`;
CREATE TABLE `np_goods_info` (
  `goods_info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `goods_info_item_no` varchar(60) DEFAULT NULL COMMENT '货号',
  `goods_info_name` varchar(255) DEFAULT NULL COMMENT '货品名称',
  `goods_info_subtitle` varchar(256) DEFAULT NULL COMMENT '货品副标题',
  `goods_info_added` enum('0','3','2','1') DEFAULT NULL COMMENT '上架  0:下架   1:上架   2:未采集   3:线下',
  `goods_info_added_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上架时间',
  `goods_info_unadded_time` timestamp NULL DEFAULT NULL COMMENT '下架时间',
  `goods_info_stock` bigint(20) DEFAULT NULL COMMENT '库存',
  `goods_info_prefer_price` decimal(20,2) DEFAULT NULL COMMENT '销售价格',
  `goods_info_cost_price` decimal(20,2) DEFAULT NULL COMMENT '成本价',
  `goods_info_market_price` decimal(20,2) DEFAULT NULL COMMENT '市场价',
  `goods_info_weight` decimal(20,2) DEFAULT NULL COMMENT '重量',
  `goods_info_img_id` varchar(128) NOT NULL DEFAULT '' COMMENT '货品图片',
  `goods_info_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `goods_info_create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `goods_info_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `goods_info_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `goods_info_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `goods_info_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `goods_info_delflag` enum('0','1') DEFAULT NULL COMMENT '是否删除',
  `third_id` bigint(30) DEFAULT '0' COMMENT '第三方店铺ID',
  `third_name` varchar(60) DEFAULT NULL COMMENT '第三方店铺名称',
  `is_third` enum('2','1','0') DEFAULT '0' COMMENT '0:不是第三方商品  1:第三方商品 2:B2B商品',
  `goods_info_isbn` varchar(50) DEFAULT NULL COMMENT '商品ISBN',
  `show_list` enum('1','0') DEFAULT '1' COMMENT '是否在列表页显示',
  `show_mobile` enum('0','1') DEFAULT '0',
  `is_customer_discount` enum('0','1') DEFAULT '0' COMMENT '是否参与会员折扣',
  `audit_status` enum('0','1','2','3') DEFAULT '0' COMMENT '审核状态 0 未审核 1 审核中 2审核失败',
  `isMailBay` enum('0','1') NOT NULL DEFAULT '0' COMMENT '1卖家包邮 0,买家自费',
  `refuse_reason` varchar(255) DEFAULT NULL COMMENT '审核拒绝原因',
  `evaluation_count` bigint(10) DEFAULT '0' COMMENT '评论数',
  `goods_salenum` bigint(10) DEFAULT '0' COMMENT '销售数',
  `goods_collect` bigint(10) DEFAULT '0' COMMENT '加入收藏数',
  PRIMARY KEY (`goods_info_id`),
  KEY `IDX_GOODS_INFO_DELFLAG` (`goods_info_delflag`),
  KEY `IDX_GOODS_INFO_PREFER_PRICE_GOODS_INFO_DELFLAG` (`goods_info_prefer_price`,`goods_info_delflag`),
  KEY `IDX_GOODS_INFO_DELFLAG_SHOW_MOBILE` (`goods_info_delflag`,`show_mobile`),
  KEY `IDX_GOODS_INFO_ADDED` (`goods_info_added`)
) ENGINE=InnoDB AUTO_INCREMENT=4641 DEFAULT CHARSET=utf8 COMMENT='货品信息表';

-- ----------------------------
-- Table structure for np_goods_info_rele_spec_detail
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_info_rele_spec_detail`;
CREATE TABLE `np_goods_info_rele_spec_detail` (
  `rele_spec_detail_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_info_id` bigint(20) DEFAULT NULL COMMENT '货品ID',
  `spec_detail_id` bigint(20) DEFAULT NULL COMMENT '规格值ID',
  `spec_id` bigint(20) DEFAULT NULL COMMENT '规格ID',
  `spec_value_remark` varchar(255) DEFAULT NULL COMMENT '自定义规格值',
  PRIMARY KEY (`rele_spec_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2153 DEFAULT CHARSET=utf8 COMMENT='货品信息与规格值关联表';

-- ----------------------------
-- Table structure for np_goods_openspec
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_openspec`;
CREATE TABLE `np_goods_openspec` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID ',
  `spec_id` bigint(20) DEFAULT NULL COMMENT '规格ID',
  `del_flag` enum('0','1') DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1444 DEFAULT CHARSET=utf8 COMMENT='商品开启规格表';

-- ----------------------------
-- Table structure for np_goods_openspecvalue
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_openspecvalue`;
CREATE TABLE `np_goods_openspecvalue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID ',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `spec_id` bigint(20) DEFAULT NULL COMMENT '规格ID',
  `spec_value_id` bigint(20) DEFAULT NULL COMMENT '规格值ID',
  `del_flag` enum('0','1') DEFAULT NULL COMMENT '删除标记',
  `img_url` varchar(256) DEFAULT NULL COMMENT '图片地址',
  `spec_value_remark` varchar(255) DEFAULT NULL COMMENT '自定义规格值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2192 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_goods_related
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_related`;
CREATE TABLE `np_goods_related` (
  `related_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `rela_goods_id` bigint(20) DEFAULT NULL COMMENT '相关商品ID',
  `rela_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `rela_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `rela_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `rela_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `rela_delflag` enum('0','1') DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`related_id`)
) ENGINE=InnoDB AUTO_INCREMENT=626 DEFAULT CHARSET=utf8 COMMENT='商品相关商品表';

-- ----------------------------
-- Table structure for np_goods_rela_tag
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_rela_tag`;
CREATE TABLE `np_goods_rela_tag` (
  `rela_tag_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `tag_id` bigint(20) DEFAULT NULL COMMENT '标签ID',
  `rela_tag_delflag` enum('0','1') DEFAULT NULL COMMENT '是否删除',
  `rela_tag_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `rela_tag_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `rela_tag_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `rela_tag_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  PRIMARY KEY (`rela_tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1724 DEFAULT CHARSET=utf8 COMMENT='商品关联标签表';

-- ----------------------------
-- Table structure for np_goods_rele_expandparam
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_rele_expandparam`;
CREATE TABLE `np_goods_rele_expandparam` (
  `rele_expandparam_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `expandparam_id` bigint(20) DEFAULT NULL COMMENT '类型扩展属性ID',
  `expangparam_value_id` varchar(20) DEFAULT NULL COMMENT '类型货站属性值ID',
  `expandparam_value_name` varchar(30) DEFAULT NULL COMMENT '类型扩展属性值',
  `rele_expandparam_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `rele_expandparam_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `rele_expandparam_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `rele_expandparam_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `rele_expandparam_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `rele_expandparam_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `rele_expandparam_delflag` enum('0','1') DEFAULT NULL COMMENT '删除 ',
  `_MASK_TO_V2` datetime DEFAULT NULL,
  PRIMARY KEY (`rele_expandparam_id`),
  KEY `IDX_20de1770f4044679b61d5b9b7beb32fd` (`_MASK_TO_V2`)
) ENGINE=InnoDB AUTO_INCREMENT=5899 DEFAULT CHARSET=utf8 COMMENT='商品关联类型扩展属性';

-- ----------------------------
-- Table structure for np_goods_rele_param
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_rele_param`;
CREATE TABLE `np_goods_rele_param` (
  `rele_param_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `param_id` bigint(20) DEFAULT NULL COMMENT '参数ID',
  `param_value` varchar(128) DEFAULT NULL COMMENT '参数值',
  `param_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `param_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `param_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `param_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `param_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `param_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `param_delflag` enum('0','1') DEFAULT NULL COMMENT '是否删除',
  `_MASK_TO_V2` datetime DEFAULT NULL,
  PRIMARY KEY (`rele_param_id`),
  KEY `IDX_3135eba401014d95af07a4219cbb8600` (`_MASK_TO_V2`)
) ENGINE=InnoDB AUTO_INCREMENT=12582 DEFAULT CHARSET=utf8 COMMENT='商品关联类型参数表';

-- ----------------------------
-- Table structure for np_goods_spec
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_spec`;
CREATE TABLE `np_goods_spec` (
  `spec_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `spec_name` varchar(30) NOT NULL COMMENT '规格名称',
  `spec_remark` varchar(255) DEFAULT NULL COMMENT '规格备注',
  `spec_nickname` varchar(30) DEFAULT NULL COMMENT '规格别名',
  `spec_showtype` enum('0','1') DEFAULT NULL COMMENT '显示类型',
  `spec_showmode` enum('0','1') DEFAULT NULL COMMENT '显示方式 ',
  `spec_delflag` enum('0','1') DEFAULT NULL COMMENT '是否删除',
  `spec_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `spec_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `spec_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `spec_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `spec_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `spec_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `_MASK_TO_V2` datetime DEFAULT NULL,
  PRIMARY KEY (`spec_id`),
  KEY `IDX_6f9f7ec8ae3f43d2bc0e34226bdb83c9` (`_MASK_TO_V2`)
) ENGINE=InnoDB AUTO_INCREMENT=280 DEFAULT CHARSET=utf8 COMMENT='商品规格表';

-- ----------------------------
-- Table structure for np_goods_spec_detail
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_spec_detail`;
CREATE TABLE `np_goods_spec_detail` (
  `spec_detail_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `spec_id` bigint(20) DEFAULT NULL COMMENT '规格ID',
  `spec_detail_name` varchar(30) DEFAULT NULL COMMENT '规格值名称',
  `spec_detail_nickname` varchar(30) DEFAULT NULL COMMENT '规格值别名',
  `spec_detail_img` varchar(255) DEFAULT NULL COMMENT '规格图片',
  `spec_detail_sort` mediumint(3) DEFAULT NULL COMMENT '排序',
  `spec_detail_delflag` enum('0','1') DEFAULT NULL COMMENT '是否删除',
  `spec_detail_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称 ',
  `spec_detail_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `spec_detail_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `spec_detail_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `spec_detail_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `spec_detail_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `_MASK_TO_V2` datetime DEFAULT NULL,
  PRIMARY KEY (`spec_detail_id`),
  KEY `IDX_1ca8a50c990b4518816157f3197ea6f2` (`_MASK_TO_V2`)
) ENGINE=InnoDB AUTO_INCREMENT=1281 DEFAULT CHARSET=utf8 COMMENT='商品规格表具体规格值';

-- ----------------------------
-- Table structure for np_goods_tag
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_tag`;
CREATE TABLE `np_goods_tag` (
  `tag_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tag_name` varchar(20) NOT NULL COMMENT '标签名称',
  `tag_img` varchar(150) DEFAULT NULL COMMENT '标签图片',
  `tag_delflag` enum('0','1') NOT NULL COMMENT '是否删除',
  `tag_create_name` varchar(30) NOT NULL COMMENT '创建人名称',
  `tag_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tag_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `tag_modified_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `tag_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `tag_del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='商品标签表';

-- ----------------------------
-- Table structure for np_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_type`;
CREATE TABLE `np_goods_type` (
  `type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_name` varchar(30) DEFAULT NULL COMMENT '类型名称',
  `type_nickname` varchar(30) DEFAULT NULL COMMENT '类型别名',
  `type_isreal` enum('0','1') DEFAULT NULL COMMENT '实体商品',
  `type_img` varchar(150) DEFAULT NULL COMMENT '类型图片',
  `type_contact_brand` enum('0','1') DEFAULT '1' COMMENT '关联品牌',
  `type_goods_expand` enum('0','1') DEFAULT '1' COMMENT '商品扩展属性',
  `type_goods_paramer` enum('0','1') DEFAULT '1' COMMENT '商品参数表',
  `type_delflag` enum('0','1') DEFAULT '0' COMMENT '是否删除',
  `type_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `type_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `type_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `type_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `type_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `type_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `_MASK_TO_V2` datetime DEFAULT NULL,
  PRIMARY KEY (`type_id`),
  KEY `IDX_15b2e0a2fec246e4a10a40767d42c749` (`_MASK_TO_V2`)
) ENGINE=InnoDB AUTO_INCREMENT=604 DEFAULT CHARSET=utf8 COMMENT='商品类型表';

-- ----------------------------
-- Table structure for np_goods_type_brand
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_type_brand`;
CREATE TABLE `np_goods_type_brand` (
  `type_brand_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` bigint(20) DEFAULT NULL COMMENT '类型ID ',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌ID',
  `delflag` enum('0','1') DEFAULT '0' COMMENT '删除标记',
  `del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `del_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间',
  `_MASK_TO_V2` datetime DEFAULT NULL,
  PRIMARY KEY (`type_brand_id`),
  KEY `IDX_f6f521d0587e478ba2161a7bae365ed4` (`_MASK_TO_V2`)
) ENGINE=InnoDB AUTO_INCREMENT=852 DEFAULT CHARSET=utf8 COMMENT='商品类型表关联品牌';

-- ----------------------------
-- Table structure for np_goods_type_expandparam
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_type_expandparam`;
CREATE TABLE `np_goods_type_expandparam` (
  `expandparam_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` bigint(20) DEFAULT NULL COMMENT '类型ID',
  `expandparam_name` varchar(30) DEFAULT NULL COMMENT '属性名',
  `expandparam_nickname` varchar(30) DEFAULT NULL COMMENT '属性别名 ',
  `expandparam_showtype` mediumint(3) DEFAULT NULL COMMENT '前台展现类型 ',
  `expanparam_isshow` enum('0','1') DEFAULT NULL COMMENT '是否显示',
  `expandparam_sort` mediumint(3) DEFAULT NULL COMMENT '排序',
  `expandparam_delflag` enum('0','1') DEFAULT '0' COMMENT '删除 ',
  `expandparam_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `expandparam_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `expandparam_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `expandparam_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `expandparam_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `expandparam_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `_MASK_TO_V2` datetime DEFAULT NULL,
  PRIMARY KEY (`expandparam_id`),
  KEY `IDX_34acec63d6624d21951d108f570414fa` (`_MASK_TO_V2`)
) ENGINE=InnoDB AUTO_INCREMENT=1428 DEFAULT CHARSET=utf8 COMMENT='商品类型扩展属性';

-- ----------------------------
-- Table structure for np_goods_type_expandparam_value
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_type_expandparam_value`;
CREATE TABLE `np_goods_type_expandparam_value` (
  `expandparam_value_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `expandparam_id` bigint(20) DEFAULT NULL COMMENT '类型扩展属性ID',
  `expandparam_value_name` varchar(30) DEFAULT NULL COMMENT '可选值  ',
  `expandparam_value_sort` mediumint(3) DEFAULT '0' COMMENT '排序  ',
  `expandparam_value_delflag` enum('0','1') DEFAULT '0' COMMENT '是否删除 ',
  `expandparam_value_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `expandparam_value_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `expandparam_value_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `expandparam_value_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `expandparam_value_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `expandparam_value_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `_MASK_TO_V2` datetime DEFAULT NULL,
  PRIMARY KEY (`expandparam_value_id`),
  KEY `IDX_e7c362bf404b4f1ea98a5d162ed98962` (`_MASK_TO_V2`)
) ENGINE=InnoDB AUTO_INCREMENT=6853 DEFAULT CHARSET=utf8 COMMENT='商品类型扩展属性值';

-- ----------------------------
-- Table structure for np_goods_type_param
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_type_param`;
CREATE TABLE `np_goods_type_param` (
  `param_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` bigint(20) DEFAULT NULL COMMENT '类型ID',
  `param_name` varchar(30) DEFAULT NULL COMMENT '参数名',
  `param_nickname` varchar(30) DEFAULT NULL COMMENT '参数别名',
  `param_delflag` enum('0','1') DEFAULT '0' COMMENT '是否删除',
  `param_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `param_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `param_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `param_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `param_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `param_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `_MASK_TO_V2` datetime DEFAULT NULL,
  PRIMARY KEY (`param_id`),
  KEY `IDX_4f45cafa178a485cb1e4bab8214093b0` (`_MASK_TO_V2`)
) ENGINE=InnoDB AUTO_INCREMENT=2689 DEFAULT CHARSET=utf8 COMMENT='商品类型详细参数';

-- ----------------------------
-- Table structure for np_goods_type_spec
-- ----------------------------
DROP TABLE IF EXISTS `np_goods_type_spec`;
CREATE TABLE `np_goods_type_spec` (
  `type_spec_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` bigint(20) DEFAULT NULL COMMENT '商品类型ID',
  `spec_id` bigint(20) DEFAULT NULL COMMENT '规格ID ',
  `type_spec_showtype` mediumint(3) DEFAULT NULL COMMENT '显示方式',
  `type_spec_sort` mediumint(3) DEFAULT NULL COMMENT '排序',
  `type_spec_delflag` enum('0','1') DEFAULT '0' COMMENT '是否删除',
  `type_spec_create_name` varchar(30) DEFAULT NULL COMMENT '创建人名称',
  `type_spec_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `type_spec_modified_name` varchar(30) DEFAULT NULL COMMENT '修改人名称',
  `type_spec_modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `type_spec_del_name` varchar(30) DEFAULT NULL COMMENT '删除人名称',
  `type_spec_del_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `_MASK_TO_V2` datetime DEFAULT NULL,
  PRIMARY KEY (`type_spec_id`),
  KEY `IDX_32df4340bf874da5b7763722e63589a2` (`_MASK_TO_V2`)
) ENGINE=InnoDB AUTO_INCREMENT=833 DEFAULT CHARSET=utf8 COMMENT='商品类型关联规格';

-- ----------------------------
-- Table structure for np_logistics
-- ----------------------------
DROP TABLE IF EXISTS `np_logistics`;
CREATE TABLE `np_logistics` (
  `np_logistics_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '退货物流ID',
  `np_logistics_no` varchar(50) NOT NULL COMMENT '退货物流单号',
  `np_logistics_name` varchar(30) NOT NULL COMMENT '退货物流名称',
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `back_order_Id` bigint(10) NOT NULL COMMENT '退单ID',
  `temp` varchar(20) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`np_logistics_id`)
) ENGINE=InnoDB AUTO_INCREMENT=719 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_marketing
-- ----------------------------
DROP TABLE IF EXISTS `np_marketing`;
CREATE TABLE `np_marketing` (
  `marketing_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '营销ID',
  `marketing_name` varchar(50) DEFAULT NULL COMMENT '营销名称 ',
  `marketing_des` text COMMENT '营销描述',
  `marketing_type` enum('0','1') DEFAULT NULL COMMENT '营销类型 1:订单 0:商品',
  `marketing_begin` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `marketing_end` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 ',
  `mod_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `flag` enum('0','1') DEFAULT NULL COMMENT '标记',
  `is_repeat` enum('0','1') DEFAULT '0' COMMENT '0唯一 1重复',
  `business_id` bigint(10) DEFAULT NULL COMMENT '商家ID',
  `m_group_id` bigint(10) DEFAULT NULL,
  `is_all` enum('0','1') DEFAULT '0' COMMENT '是否全场促销',
  `add_give_type` enum('1','0') DEFAULT NULL COMMENT '附加赠送类型：0：赠送积分 1：赠送优惠券',
  `give_integral` int(8) DEFAULT NULL COMMENT '赠送积分',
  `coupon_id` bigint(20) DEFAULT NULL COMMENT '优惠券id',
  `active_site_type` enum('2','1','0') DEFAULT NULL COMMENT '活动站点:0：平台 1：移动端 2：全部',
  `shipping_money` decimal(10,2) DEFAULT NULL COMMENT '满多少元包邮',
  PRIMARY KEY (`marketing_id`)
) ENGINE=InnoDB AUTO_INCREMENT=818 DEFAULT CHARSET=utf8 COMMENT='营销 ';

-- ----------------------------
-- Table structure for np_marketing_codex
-- ----------------------------
DROP TABLE IF EXISTS `np_marketing_codex`;
CREATE TABLE `np_marketing_codex` (
  `marketing_codex_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `marketing_id` bigint(20) DEFAULT NULL COMMENT '促销ID',
  `codex_id` bigint(20) DEFAULT NULL COMMENT '促销类型ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mod_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `flag` enum('0','1') DEFAULT NULL,
  PRIMARY KEY (`marketing_codex_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1100 DEFAULT CHARSET=utf8 COMMENT='营销 规则';

-- ----------------------------
-- Table structure for np_marketing_group
-- ----------------------------
DROP TABLE IF EXISTS `np_marketing_group`;
CREATE TABLE `np_marketing_group` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '优惠分组id',
  `preferential_name` varchar(50) DEFAULT NULL COMMENT '优惠分组名称',
  `is_show` enum('1','0') DEFAULT NULL COMMENT '是否显示',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_marketing_scope
-- ----------------------------
DROP TABLE IF EXISTS `np_marketing_scope`;
CREATE TABLE `np_marketing_scope` (
  `marketing_scope_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `marketing_id` bigint(20) DEFAULT NULL COMMENT '促销ID',
  `scope_id` bigint(20) DEFAULT NULL COMMENT '范围ID',
  `scope_type` enum('0','1','2') DEFAULT NULL COMMENT '范围类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 ',
  `mod_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `flag` enum('0','1') DEFAULT NULL COMMENT '标记',
  PRIMARY KEY (`marketing_scope_id`)
) ENGINE=InnoDB AUTO_INCREMENT=77157 DEFAULT CHARSET=utf8 COMMENT='营销范围';

-- ----------------------------
-- Table structure for np_order
-- ----------------------------
DROP TABLE IF EXISTS `np_order`;
CREATE TABLE `np_order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单Id',
  `order_code` bigint(20) DEFAULT NULL COMMENT '订单编号',
  `order_price` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  `order_pre_price` decimal(10,2) DEFAULT NULL COMMENT '订单优惠金额',
  `order_old_price` decimal(10,2) DEFAULT NULL COMMENT '订单原始总金额',
  `order_status` varchar(30) DEFAULT NULL COMMENT '订单状态 0未付款 1已付款未发货 2已发货 3已经收货 4作废 7:申请退货 8：同意退货 9:拒绝退货 10:确认收货 11:订单结束 12:同意退款13： 拒绝退款 14:已提交退货审核 15：已提交退款审核 16: 收货失败 17:已退款 18:申请退款 退款成功19:退款待退款 20：退货待退款',
  `customer_id` bigint(10) DEFAULT NULL COMMENT '会员ID',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `send_express_time` timestamp NULL DEFAULT NULL COMMENT '发货时间',
  `get_goods_time` timestamp NULL DEFAULT NULL COMMENT '收货时间',
  `shopping_addr_id` bigint(10) DEFAULT NULL COMMENT '收货地址ID',
  `shipping_province` varchar(30) DEFAULT NULL COMMENT '收货省',
  `shipping_city` varchar(30) DEFAULT NULL COMMENT '收货市',
  `shipping_county` varchar(30) DEFAULT NULL COMMENT '收货区县',
  `shipping_address` varchar(330) DEFAULT NULL COMMENT '收货详细地址',
  `shipping_person` varchar(30) DEFAULT NULL COMMENT '收货人',
  `shipping_phone` varchar(30) DEFAULT NULL COMMENT '收货电话',
  `shipping_mobile` varchar(30) DEFAULT NULL COMMENT '收货手机',
  `invoice_title` varchar(50) DEFAULT NULL COMMENT '发票抬头',
  `invoice_content` varchar(350) DEFAULT NULL COMMENT '发票内容',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记',
  `invoice_type` enum('0','1','2') DEFAULT NULL COMMENT '发票类型',
  `customer_remark` varchar(300) DEFAULT NULL COMMENT '客户留言',
  `pay_id` bigint(10) DEFAULT NULL COMMENT '支付方式',
  `order_integral` bigint(10) DEFAULT NULL COMMENT '积分',
  `coupon_no` varchar(50) DEFAULT NULL COMMENT '使用的优惠券',
  `evaluate_flag` enum('0','1') DEFAULT '0' COMMENT '是否评价 0未评价 1已经评价',
  `express_price` decimal(10,2) DEFAULT '0.00' COMMENT '运费',
  `shipping_postcode` varchar(20) DEFAULT NULL COMMENT '邮编',
  `order_cancel_time` timestamp NULL DEFAULT NULL COMMENT '取消时间',
  `order_cancel_remark` varchar(500) DEFAULT NULL COMMENT '原因',
  `back_price` decimal(10,2) DEFAULT NULL COMMENT '退单金额',
  `business_id` bigint(10) DEFAULT NULL COMMENT '商家ID',
  `dealer_type` enum('0','1') DEFAULT '0' COMMENT '第三方 0  经销商1',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_date` date DEFAULT NULL,
  `order_old_code` bigint(20) DEFAULT NULL COMMENT '主单编号',
  `order_new_status` enum('0','1') DEFAULT '0' COMMENT '是否是新增订单 0 新增订单 1 已查看订单',
  `order_cargo_status` enum('0','1','3','4','2') DEFAULT '0',
  `order_m_type` enum('2','1','0') DEFAULT '0',
  `order_express_type` enum('0','1') DEFAULT '0',
  `order_pre_price_order` decimal(10,3) DEFAULT '0.000' COMMENT '订单促销优惠',
  `order_line_pay` enum('0','1') DEFAULT '1' COMMENT '1在线支付 0货到付款',
  `shipping_county_id` int(20) DEFAULT NULL COMMENT '区id',
  `ware_id` bigint(10) DEFAULT NULL COMMENT '仓库id',
  `ware_name` varchar(50) DEFAULT NULL COMMENT '仓库名',
  `send_person` varchar(30) DEFAULT NULL COMMENT '送货人',
  `send_mobile` varchar(30) DEFAULT NULL COMMENT '送货人电话',
  `direct_type` enum('1','0') DEFAULT '0' COMMENT '0 商家订单 1直营店订单',
  `jf_price` decimal(10,2) DEFAULT NULL COMMENT '积分兑换金额',
  `seller_remark` varchar(300) DEFAULT NULL COMMENT '卖家备注',
  `pay_name` varchar(120) DEFAULT NULL COMMENT '支付方式名称',
  `ucoin_prise` decimal(10,2) DEFAULT NULL COMMENT 'U宝金额',
  `other_amount` decimal(10,2) DEFAULT NULL COMMENT '其他金额',
  `is_ucoin` enum('1','0') DEFAULT '0' COMMENT '1表示从U宝端购买商品，0表示从商城端购买商品',
  `mall_id` int(10) DEFAULT '0' COMMENT '0:商城平台商品，非0：其他商城ID',
  `ucoin_pay_time` timestamp NULL DEFAULT NULL COMMENT 'U宝付款时间',
  `enterprise_id` bigint(10) DEFAULT NULL COMMENT 'enterprise_id（企业节点ID）',
  `self_pick` tinyint(4) DEFAULT NULL COMMENT '自提标识 (1为自提、0为非自提)',
  `delivery_code` varchar(12) DEFAULT NULL COMMENT '自提码',
  `is_valet` tinyint(4) DEFAULT NULL COMMENT '是否为代客下单',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作人ID',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `IDX_ORDER_CODE` (`order_code`) USING BTREE,
  KEY `IDX_BUSINESS_ID_CUSTOMER_ID` (`business_id`,`customer_id`),
  KEY `IDX_OR_BU_CU` (`order_code`,`business_id`,`customer_id`),
  KEY `IDX_ORDER_STATUS` (`order_status`),
  KEY `IDX_BU_SH_CU` (`business_id`,`shipping_mobile`,`customer_id`),
  KEY `IDX_BU_PA_CU` (`business_id`,`pay_time`,`customer_id`),
  KEY `IDX_CREATE_TIME` (`create_time`),
  KEY `IDX_SHIPPING_MOBILE` (`shipping_mobile`),
  KEY `IDX_SHIPPING_PERSON` (`shipping_person`),
  KEY `IDX_CUSTOMER_ID` (`customer_id`),
  KEY `IDX_CUSTOMER_ID_ORDER_STATUS` (`customer_id`,`order_status`),
  KEY `IDX_CREATE_DATE` (`create_date`) USING BTREE,
  KEY `IDX_PAY_TIME` (`pay_time`)
) ENGINE=InnoDB AUTO_INCREMENT=267838 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_order_container
-- ----------------------------
DROP TABLE IF EXISTS `np_order_container`;
CREATE TABLE `np_order_container` (
  `container_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '装箱单子id',
  `goods_info_id` bigint(10) DEFAULT NULL COMMENT '商品id或赠品id',
  `goods_num` bigint(10) DEFAULT NULL COMMENT '数量',
  `container_status` enum('0','1') DEFAULT NULL COMMENT '状态 0:表示此为商品  1：表示此为赠品',
  `relation_id` bigint(10) DEFAULT NULL COMMENT '外键',
  PRIMARY KEY (`container_id`)
) ENGINE=InnoDB AUTO_INCREMENT=162035 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_order_container_relation
-- ----------------------------
DROP TABLE IF EXISTS `np_order_container_relation`;
CREATE TABLE `np_order_container_relation` (
  `relation_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '装箱单id',
  `order_id` bigint(10) DEFAULT NULL COMMENT '订单id',
  `del_flge` enum('1','0') DEFAULT '0' COMMENT '删除标记',
  `express_no` varchar(50) DEFAULT NULL COMMENT '物流单号',
  `order_express_id` bigint(10) DEFAULT NULL COMMENT '物流单id',
  `express_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`relation_id`),
  UNIQUE KEY `IDX_ORDER_No` (`order_id`,`express_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=160025 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_order_express
-- ----------------------------
DROP TABLE IF EXISTS `np_order_express`;
CREATE TABLE `np_order_express` (
  `order_express_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '订单物流ID',
  `order_id` bigint(10) DEFAULT NULL COMMENT '订单Id',
  `express_name` varchar(100) DEFAULT NULL COMMENT '物流名称',
  `express_no` varchar(50) DEFAULT NULL COMMENT '物流单号',
  `express_id` bigint(10) DEFAULT NULL COMMENT '物流ID',
  `del_flag` enum('1','0') DEFAULT '0' COMMENT '是否删除',
  `relation_id` bigint(10) DEFAULT NULL,
  `express_type_id` bigint(10) DEFAULT NULL,
  `express_type_name` varchar(255) DEFAULT NULL COMMENT '配送方式名称',
  PRIMARY KEY (`order_express_id`),
  KEY `IDX_ORDER_ID` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=267532 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `np_order_goods`;
CREATE TABLE `np_order_goods` (
  `order_goods_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '订单货品ID',
  `order_id` bigint(10) DEFAULT NULL COMMENT '订单Id',
  `goods_id` bigint(10) DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `comment_id` bigint(20) DEFAULT NULL COMMENT '评论编号',
  `goods_info_id` bigint(10) DEFAULT NULL COMMENT '货品ID',
  `goods_info_num` bigint(10) DEFAULT NULL COMMENT '货品个数',
  `goods_info_old_price` decimal(10,2) DEFAULT NULL COMMENT '货品原始价格',
  `goods_info_price` decimal(10,2) DEFAULT NULL COMMENT '货品交易价格',
  `goods_coupon_price` decimal(10,2) DEFAULT NULL COMMENT '货品优惠金额',
  `goods_info_sum_price` decimal(10,2) DEFAULT NULL COMMENT '小计金额',
  `have_gift_status` enum('0','1') DEFAULT '0' COMMENT '是否有赠品',
  `have_coupon_status` enum('0','1') DEFAULT '0' COMMENT '是否有优惠券',
  `goods_marketing_id` bigint(10) DEFAULT NULL COMMENT '商品促销ID',
  `buy_time` timestamp NULL DEFAULT NULL COMMENT '购买时间',
  `del_flag` enum('1','0') DEFAULT '0' COMMENT '是否删除',
  `evaluate_flag` enum('0','1') DEFAULT '0' COMMENT '是否评价  1评价 0未评价',
  `back_flag` enum('0','1') DEFAULT '0' COMMENT '是否退单',
  `back_order_code` varchar(50) DEFAULT NULL COMMENT '退单号',
  `barter_order_code` varchar(50) DEFAULT NULL COMMENT '换单号',
  `share_id` bigint(20) DEFAULT NULL COMMENT '晒单id',
  `share_flag` enum('1','0') DEFAULT '0' COMMENT '是否已晒单。0未晒，1已晒',
  `goods_active_marketingId` bigint(10) DEFAULT NULL COMMENT '活动促销id',
  `distinct_id` bigint(20) DEFAULT NULL,
  `goods_group_marketing_id` int(20) DEFAULT NULL COMMENT '团购促销id',
  `subject_goods_price` decimal(20,2) DEFAULT NULL COMMENT '专区价格',
  `subject_id` bigint(20) DEFAULT NULL COMMENT '专区ID',
  `subject_name` varchar(100) DEFAULT NULL COMMENT '专区名称',
  PRIMARY KEY (`order_goods_id`),
  KEY `IDX_ORDER_ID_GOODS_INFO_ID` (`order_id`,`goods_info_id`),
  KEY `IDX_GOODS_INFO_ID` (`goods_info_id`),
  KEY `IDX_COMMENT_ID` (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=273848 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_pre_discount_marketing
-- ----------------------------
DROP TABLE IF EXISTS `np_pre_discount_marketing`;
CREATE TABLE `np_pre_discount_marketing` (
  `discount_id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `marketing_id` int(20) DEFAULT NULL COMMENT '促销Id',
  `discount_info` decimal(10,2) DEFAULT NULL COMMENT '折扣',
  `del_flag` enum('0','1') DEFAULT NULL COMMENT '删除标记',
  `goods_id` int(20) DEFAULT NULL COMMENT '货品id',
  `discount_flag` enum('0','1','2') DEFAULT NULL COMMENT '是否抹掉分角 0 未抹掉 1 抹掉分 2 抹掉角',
  `discount_price` decimal(10,2) DEFAULT NULL COMMENT '折后价',
  PRIMARY KEY (`discount_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for np_product_ware
-- ----------------------------
DROP TABLE IF EXISTS `np_product_ware`;
CREATE TABLE `np_product_ware` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) DEFAULT NULL COMMENT '货品ID',
  `ware_id` bigint(20) DEFAULT NULL COMMENT '仓库ID ',
  `ware_stock` bigint(20) DEFAULT NULL COMMENT '货品库存',
  `ware_price` decimal(10,2) DEFAULT NULL COMMENT '货品价格',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7824 DEFAULT CHARSET=utf8 COMMENT='货品关联仓库';

-- ----------------------------
-- Table structure for np_province_default
-- ----------------------------
DROP TABLE IF EXISTS `np_province_default`;
CREATE TABLE `np_province_default` (
  `d_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `district_id` bigint(10) DEFAULT NULL COMMENT '地区ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `de_falg` enum('0','1') DEFAULT '0' COMMENT '删除标记',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `np_shopping_cart`;
CREATE TABLE `np_shopping_cart` (
  `shopping_cart_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `goods_info_id` bigint(10) DEFAULT NULL COMMENT '货品id',
  `goods_pre_price` bigint(10) DEFAULT NULL COMMENT '优惠价格',
  `goods_price` decimal(10,2) DEFAULT NULL COMMENT '货品价格',
  `goods_num` bigint(10) DEFAULT '1' COMMENT '货品数量',
  `fit_id` bigint(10) DEFAULT NULL,
  `customer_id` bigint(10) DEFAULT NULL COMMENT '用户id',
  `marketing_id` bigint(10) DEFAULT NULL COMMENT '促销id',
  `shopping_cart_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记',
  `distinct_id` bigint(20) DEFAULT NULL COMMENT '仓库Id',
  `marketing_activity_id` bigint(10) DEFAULT NULL COMMENT '促销活动id',
  `order_marketing_id` bigint(10) DEFAULT NULL COMMENT '订单优惠id',
  `goods_group_id` int(20) DEFAULT NULL COMMENT '团购促销id',
  `subject_name` varchar(100) DEFAULT NULL COMMENT '专区名称',
  `subject_id` bigint(20) DEFAULT '0' COMMENT '专区ID',
  `subject_goods_price` decimal(20,2) DEFAULT NULL COMMENT '专区价格',
  PRIMARY KEY (`shopping_cart_id`)
) ENGINE=MEMORY AUTO_INCREMENT=258 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for np_sys_city
-- ----------------------------
DROP TABLE IF EXISTS `np_sys_city`;
CREATE TABLE `np_sys_city` (
  `city_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '城市ID ',
  `city_name` varchar(255) DEFAULT NULL COMMENT '城市名称',
  `city_sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `province_id` bigint(20) DEFAULT NULL COMMENT '省份ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `del_flag` enum('0','1') DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=345 DEFAULT CHARSET=utf8 COMMENT='地区城市表';

-- ----------------------------
-- Table structure for np_sys_district
-- ----------------------------
DROP TABLE IF EXISTS `np_sys_district`;
CREATE TABLE `np_sys_district` (
  `district_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '区县ID',
  `district_name` varchar(255) DEFAULT NULL COMMENT '区县名称 ',
  `district_sort` bigint(10) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL COMMENT '城市',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间 ',
  `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `del_flag` enum('0','1') DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`district_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2998 DEFAULT CHARSET=utf8 COMMENT='地区区县表 ';

-- ----------------------------
-- Table structure for np_sys_express_conf
-- ----------------------------
DROP TABLE IF EXISTS `np_sys_express_conf`;
CREATE TABLE `np_sys_express_conf` (
  `EXPRESS_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `NAME` varchar(100) DEFAULT NULL COMMENT '配送方式名称',
  `EXPRESS` bigint(20) DEFAULT NULL COMMENT '配送公司',
  `PRICE` double(10,2) DEFAULT NULL COMMENT '运费',
  `SEND_EXPRESS` bigint(20) DEFAULT NULL COMMENT '承运公司',
  `DES` varchar(200) DEFAULT NULL COMMENT '备注',
  `USED_STATUS` char(1) DEFAULT NULL COMMENT '启用',
  `EXP_FLEID_1` varchar(200) DEFAULT NULL COMMENT '扩展字段1',
  `EXP_FLEID_2` varchar(200) DEFAULT NULL COMMENT '扩展字段2',
  `INSERT_ID` bigint(20) DEFAULT NULL COMMENT '添加人',
  `INSERT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
  `MODIFY_ID` bigint(20) DEFAULT NULL COMMENT '修改人',
  `MODIFY_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `DELETE_STATUS` smallint(6) DEFAULT NULL COMMENT '删除标识',
  `PICKUP_FLAG` char(1) NOT NULL DEFAULT '0' COMMENT '自提标记 0不自提；1自提',
  PRIMARY KEY (`EXPRESS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='配送方式设置';

-- ----------------------------
-- Table structure for np_sys_province
-- ----------------------------
DROP TABLE IF EXISTS `np_sys_province`;
CREATE TABLE `np_sys_province` (
  `province_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '省份ID ',
  `province_name` varchar(255) DEFAULT NULL COMMENT '省份名称',
  `province_sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `del_flag` enum('0','1') DEFAULT NULL COMMENT '删除标记 ',
  PRIMARY KEY (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='地区省份表';

-- ----------------------------
-- Table structure for np_sys_street
-- ----------------------------
DROP TABLE IF EXISTS `np_sys_street`;
CREATE TABLE `np_sys_street` (
  `street_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '街道ID ',
  `street_name` varchar(255) DEFAULT NULL COMMENT '街道名称',
  `street_sort` bigint(10) DEFAULT NULL,
  `district_id` bigint(20) DEFAULT NULL COMMENT '区县ID ',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `del_flag` enum('0','1') DEFAULT NULL COMMENT '删除标记 ',
  PRIMARY KEY (`street_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='地区街道表';

-- ----------------------------
-- Table structure for np_third_store_info
-- ----------------------------
DROP TABLE IF EXISTS `np_third_store_info`;
CREATE TABLE `np_third_store_info` (
  `store_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '店铺ID',
  `customerId` bigint(20) NOT NULL COMMENT '会员编号',
  `store_name` varchar(75) DEFAULT NULL COMMENT '店铺名称',
  `store_status` enum('1','0') DEFAULT NULL COMMENT '店铺开通状态',
  `company_cre_time` varchar(50) DEFAULT NULL COMMENT '公司创建时间',
  `company_name` varchar(75) DEFAULT NULL COMMENT '公司名称',
  `company_addr` varchar(75) DEFAULT NULL COMMENT '公司所在地',
  `company_addr_id` varchar(50) DEFAULT NULL COMMENT '公司所在地编号',
  `company_addr_del` varchar(255) DEFAULT NULL COMMENT '公司详细地址',
  `company_tel` varchar(30) DEFAULT NULL COMMENT '公司电话',
  `company_emp_num` bigint(20) DEFAULT NULL COMMENT '员工总数',
  `company_capital` decimal(20,2) DEFAULT NULL COMMENT '注册资金',
  `company_email` varchar(75) DEFAULT NULL COMMENT '电子邮箱',
  `company_type` enum('1','2','3','4') DEFAULT NULL COMMENT '企业类型',
  `company_contact_name` varchar(75) DEFAULT NULL COMMENT '联系人姓名',
  `card_url` varchar(255) DEFAULT NULL COMMENT '法人身份证URL',
  `bank_url` varchar(255) DEFAULT NULL COMMENT '银行开户许可证URL',
  `company_contact_tel` varchar(30) DEFAULT NULL COMMENT '联系人电话',
  `company_research_url` varchar(255) DEFAULT NULL COMMENT '调研资料表url',
  `company_sku` decimal(20,2) DEFAULT NULL COMMENT 'SKU数量合计',
  `company_avg` enum('0','1') DEFAULT NULL COMMENT '平均客单价',
  `return_addr` varchar(255) DEFAULT NULL COMMENT '退货地址',
  `return_mail` varchar(30) DEFAULT NULL COMMENT '退货邮编',
  `return_contact_name` varchar(50) DEFAULT NULL COMMENT '退货联系人',
  `return_contact_tel` varchar(30) DEFAULT NULL COMMENT '退货联系电话',
  `buss_id` varchar(50) DEFAULT NULL COMMENT '营业执照号',
  `buss_addr` varchar(255) DEFAULT NULL COMMENT '营业执照所在地',
  `buss_addr_id` varchar(50) DEFAULT NULL COMMENT '营业执照所在地省市区街道编号',
  `BUSS_DATE` varchar(255) DEFAULT NULL COMMENT '营业执照有效期',
  `buss_dept_no` varchar(30) DEFAULT NULL COMMENT '组织机构代码',
  `buss_tax_regist_id` varchar(30) DEFAULT NULL COMMENT '税务登记证号',
  `buss_tax_regist_url` varchar(255) DEFAULT NULL COMMENT '税务登记证Url',
  `buss_tax_payer_id` varchar(30) DEFAULT NULL COMMENT '纳税人识别号',
  `buss_tax_cred_url` varchar(255) DEFAULT NULL COMMENT '纳税人资格证URL',
  `buss_tax_type` varchar(30) DEFAULT NULL COMMENT '税务类型',
  `buss_tax_type_id` varchar(30) DEFAULT NULL COMMENT '税务类型税码',
  `buss_legal_name` varchar(75) DEFAULT NULL COMMENT '法人代表',
  `buss_legal_card_id` varchar(18) DEFAULT NULL COMMENT '法人身份证编号',
  `buss_url` varchar(255) DEFAULT NULL COMMENT '营业执照电子版url',
  `buss_range` varchar(255) DEFAULT NULL COMMENT '经营范围',
  `bank_username` varchar(50) DEFAULT NULL COMMENT '银行开户名',
  `bank_card_id` varchar(50) DEFAULT NULL COMMENT '公司银行账号',
  `bank_addr` varchar(255) DEFAULT NULL COMMENT '开户银行所在地',
  `bank_addr_id` varchar(50) DEFAULT NULL COMMENT '银行所在地编号',
  `bank_name` varchar(75) DEFAULT NULL COMMENT '开户行支行名称',
  `back_status` enum('0','1') DEFAULT NULL COMMENT '0 未验证 1已打款 2 验证成功',
  `bank_id` varchar(75) DEFAULT NULL COMMENT '开户支行联行号',
  `check_status` enum('1','2','0') DEFAULT NULL COMMENT '审核状态',
  `contract_url` varchar(255) DEFAULT NULL COMMENT '合同url',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mod_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `del_flag` enum('1','0') DEFAULT NULL COMMENT '删除标记',
  `shop_trans_fee` decimal(20,2) DEFAULT NULL COMMENT '店铺运费',
  `is_submit` enum('1','0') DEFAULT NULL COMMENT '0未完全提交 1完成提交',
  `is_store_index` enum('1','0') DEFAULT '0' COMMENT '是否开启商家首页 0否 1是',
  `refuse_content` varchar(255) DEFAULT NULL COMMENT '打回原因',
  `billing_cycle` varchar(225) DEFAULT NULL,
  `store_promise` varchar(200) DEFAULT NULL COMMENT '店铺承诺',
  `store_qi` enum('2','1','0') DEFAULT '0' COMMENT ' 0专营店 1旗舰店 2专卖店',
  `sw_value` bigint(20) DEFAULT '30' COMMENT '货品库存预警值',
  `store_point` int(8) DEFAULT '100' COMMENT '商家积分',
  `store_balance` decimal(20,3) DEFAULT '100.000' COMMENT '商家账户余额',
  `service_qq` varchar(100) DEFAULT NULL,
  `expiry_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '商家店铺到期时间',
  `is_supplier` enum('1','0') DEFAULT '0' COMMENT '0不是供应商 1是供应商',
  `store_street_sort` bigint(20) DEFAULT NULL COMMENT '店铺街排序',
  `store_street_is_show` enum('1','0') DEFAULT '0' COMMENT '是否在店铺街显示 0：显示 1：不显示',
  `store_province` bigint(20) DEFAULT NULL COMMENT '省',
  `store_city` bigint(20) DEFAULT NULL COMMENT '市',
  `store_county` bigint(20) DEFAULT NULL COMMENT '区',
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8 COMMENT='店铺基本信息表';

-- ----------------------------
-- Table structure for np_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `np_warehouse`;
CREATE TABLE `np_warehouse` (
  `ware_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ware_name` varchar(50) DEFAULT NULL COMMENT '仓库名称',
  `ware_remark` varchar(50) DEFAULT NULL COMMENT '仓库备注',
  `ware_admin` bigint(20) DEFAULT NULL COMMENT '仓库管理员',
  `ware_address` varchar(50) DEFAULT NULL COMMENT '仓库地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间 ',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记',
  `exec_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  `identify_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ware_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='仓库信息表';

-- ----------------------------
-- Table structure for np_ware_city
-- ----------------------------
DROP TABLE IF EXISTS `np_ware_city`;
CREATE TABLE `np_ware_city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ware_id` bigint(20) DEFAULT NULL COMMENT '仓库iD',
  `city_id` bigint(20) DEFAULT NULL COMMENT '区县ID',
  `del_flag` enum('0','1') DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22912 DEFAULT CHARSET=utf8 COMMENT='仓库关联地区表';

-- ----------------------------
-- Table structure for ysh_custom_ucion
-- ----------------------------
DROP TABLE IF EXISTS `ysh_custom_ucion`;
CREATE TABLE `ysh_custom_ucion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID号',
  `enterprise_id` bigint(20) DEFAULT NULL COMMENT '企业信息ID',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rese_price` decimal(10,2) DEFAULT NULL COMMENT 'u宝剩余多少金额',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'U宝 创建时间',
  `start_time` timestamp NULL DEFAULT NULL COMMENT 'U宝有效开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT 'U宝有效期结束时间，如果为空表示无限时。',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_custom_ucion_history
-- ----------------------------
DROP TABLE IF EXISTS `ysh_custom_ucion_history`;
CREATE TABLE `ysh_custom_ucion_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID号',
  `enterprise_id` bigint(20) DEFAULT NULL COMMENT '企业信息ID',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `batch_id` bigint(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL COMMENT '历史记录类型（发放，兑换码，消费）',
  `price` decimal(10,2) DEFAULT NULL COMMENT 'u宝面值金额',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'U宝 创建时间',
  `order_id` bigint(20) DEFAULT NULL COMMENT 'U宝兑换码从表ID',
  `send_type` varchar(10) DEFAULT NULL COMMENT '发放类型',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_delivery_code
-- ----------------------------
DROP TABLE IF EXISTS `ysh_delivery_code`;
CREATE TABLE `ysh_delivery_code` (
  `delivery_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自提码',
  `delivery_status` varchar(12) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`delivery_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ysh_enterprise_allocation
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_allocation`;
CREATE TABLE `ysh_enterprise_allocation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `in_id` bigint(20) DEFAULT NULL COMMENT '转入U宝企业ID',
  `out_id` bigint(20) DEFAULT NULL,
  `fee` decimal(10,2) DEFAULT NULL COMMENT '交易金额',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '交易时间',
  `batch_id` bigint(20) DEFAULT NULL COMMENT '分配表主ID',
  `code` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_enterprise_authority
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_authority`;
CREATE TABLE `ysh_enterprise_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `enterprise_id` bigint(20) DEFAULT NULL COMMENT '企业ID',
  `designation` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `characterization` varchar(255) DEFAULT NULL COMMENT '说明',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mod_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '状态标记 1:删除状态 0:正常状态',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_enterprise_authority_page
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_authority_page`;
CREATE TABLE `ysh_enterprise_authority_page` (
  `authority_id` bigint(20) NOT NULL COMMENT '权限ID',
  `page_id` bigint(20) NOT NULL COMMENT '页面ID',
  PRIMARY KEY (`authority_id`,`page_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_enterprise_batchallocat
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_batchallocat`;
CREATE TABLE `ysh_enterprise_batchallocat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `out_id` bigint(20) DEFAULT NULL COMMENT '转出U宝企业ID',
  `code` bigint(20) DEFAULT NULL COMMENT '交易流水号（14位 参考订单号生成规则）',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '交易金额',
  `count` int(11) DEFAULT NULL COMMENT '总笔数',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '交易时间',
  `remark` varchar(60) DEFAULT NULL COMMENT '交易备注',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '账号ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ysh_enterprise_batchgrand
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_batchgrand`;
CREATE TABLE `ysh_enterprise_batchgrand` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `enterprise_id` bigint(10) DEFAULT NULL COMMENT '企业信息ID',
  `code` bigint(20) DEFAULT NULL,
  `fee` decimal(10,2) DEFAULT NULL COMMENT '交易金额',
  `send_type` varchar(10) DEFAULT NULL COMMENT '发放类型(1:员工福利 2:会员管理 3：企业营销)',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '交易时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '交易备注',
  `ucoin_count` int(11) DEFAULT NULL COMMENT '发放总数 ',
  `grand_type` varchar(10) DEFAULT '1' COMMENT '1代表批量发放，2代表单个发放，3代表查看、导出',
  `ucoin_price` decimal(10,2) DEFAULT NULL COMMENT '面值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_enterprise_func
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_func`;
CREATE TABLE `ysh_enterprise_func` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '企业上级ID',
  `store_id` bigint(20) DEFAULT NULL COMMENT '店铺ID',
  `paykey` varchar(32) DEFAULT NULL COMMENT '支付密码',
  `undistributed_price` decimal(10,2) DEFAULT '0.00' COMMENT '未分配金额',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '已分配财富',
  `ucoin_price` decimal(10,2) DEFAULT '0.00' COMMENT 'U宝财富',
  `ucoincode_price` decimal(10,2) DEFAULT '0.00' COMMENT 'U宝兑换码总财富',
  `inventory_forewarn` int(11) DEFAULT NULL,
  `has_permit` tinyint(4) DEFAULT '0' COMMENT '是否拥有代客下单免验证权限',
  `account_time` timestamp NULL DEFAULT NULL COMMENT '财富有郊期',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '删除状态 0：未删除 1：已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ysh_enterprise_info
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_info`;
CREATE TABLE `ysh_enterprise_info` (
  `enterprise_id` bigint(20) NOT NULL COMMENT 'ID',
  `account_name` varchar(60) DEFAULT NULL COMMENT '帐号名称',
  `enterprise_name` varchar(60) DEFAULT NULL COMMENT '企业名称',
  `profile` varchar(255) DEFAULT NULL COMMENT '公司简介',
  `img_url` varchar(255) DEFAULT NULL COMMENT '企业LOG',
  `business_license_no` varchar(50) DEFAULT NULL COMMENT '营业执照号',
  `legal_person` varchar(10) DEFAULT NULL COMMENT '企业法人',
  `register_address` varchar(255) DEFAULT NULL COMMENT '注册地址',
  `district_id` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL COMMENT '企业地址',
  `mobile` varchar(20) DEFAULT NULL COMMENT '企业电话',
  `linkman` varchar(10) DEFAULT NULL COMMENT '联系人',
  `link_mobile` varchar(20) DEFAULT NULL COMMENT '联系人电话',
  `business_start_time` timestamp NULL DEFAULT NULL COMMENT '营业期限开始时间',
  `business_end_time` timestamp NULL DEFAULT NULL COMMENT '营养期限结束时间',
  PRIMARY KEY (`enterprise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ysh_enterprise_inventory
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_inventory`;
CREATE TABLE `ysh_enterprise_inventory` (
  `enterprise_id` bigint(20) NOT NULL,
  `goods_info_id` bigint(20) NOT NULL COMMENT '货品id',
  `inventory` int(11) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`enterprise_id`,`goods_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ysh_enterprise_manager
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_manager`;
CREATE TABLE `ysh_enterprise_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '企业帐号ID',
  `enterprise_id` bigint(20) DEFAULT NULL COMMENT '企业信息ID',
  `is_primary` tinyint(4) DEFAULT NULL COMMENT '1：主帐号 0：员工账号',
  `username` varchar(50) DEFAULT NULL COMMENT '帐号名称',
  `password` varchar(32) DEFAULT NULL COMMENT '帐号密码',
  `cellphone` varchar(30) DEFAULT NULL COMMENT '帐号联系电话',
  `staffname` varchar(50) DEFAULT NULL COMMENT '姓名',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `mod_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `non_disabled` tinyint(4) DEFAULT '1' COMMENT '没有被禁用',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '状态标记 2:冻结状态1:删除状态0:正常状态',
  `photo_img` varchar(255) DEFAULT '' COMMENT '账户头像',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=333 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_enterprise_manager_authority
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_manager_authority`;
CREATE TABLE `ysh_enterprise_manager_authority` (
  `manager_id` bigint(10) NOT NULL COMMENT '权限ID',
  `authority_id` bigint(10) NOT NULL COMMENT '企业账户ID',
  PRIMARY KEY (`manager_id`,`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_enterprise_message
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_message`;
CREATE TABLE `ysh_enterprise_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `title` varchar(100) DEFAULT NULL COMMENT '消息标题',
  `content` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `author_id` bigint(10) DEFAULT NULL COMMENT '创建人ID',
  `type` varchar(10) DEFAULT NULL COMMENT '消息类型:2:U币块过期消息 1:交易消息 0:系统消息',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '状态标记:1:删除状态 0:正常状态',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '企业帐号ID',
  `is_read` tinyint(4) DEFAULT '0' COMMENT '消息是否已读，0表示未读、1表示已读',
  `url` varchar(100) DEFAULT NULL COMMENT '消息链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_enterprise_page
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_page`;
CREATE TABLE `ysh_enterprise_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页面ID',
  `designation` varchar(50) DEFAULT NULL COMMENT '页面名称',
  `url` varchar(255) DEFAULT NULL COMMENT '页面URL',
  `parent_id` bigint(10) DEFAULT NULL COMMENT '父菜单ID',
  `grade` int(11) DEFAULT NULL COMMENT '层级数',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `characterization` varchar(255) DEFAULT NULL COMMENT '页面描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_enterprise_recharge
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_recharge`;
CREATE TABLE `ysh_enterprise_recharge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `enterprise_id` bigint(20) DEFAULT NULL COMMENT '企业信息ID',
  `code` varchar(40) DEFAULT NULL COMMENT '交易流水号（14位 参考订单号生成规则）',
  `status` varchar(10) DEFAULT NULL COMMENT '交易状态（0：未支付 1:已支付 2:取消）',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '交易金额',
  `pay_name` varchar(255) DEFAULT NULL COMMENT '支付方式名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '交易时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '交易备注',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '账号ID',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_enterprise_requisition
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_requisition`;
CREATE TABLE `ysh_enterprise_requisition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `enterprise_id` bigint(20) DEFAULT NULL COMMENT '企业信息ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '要申请的企业ID',
  `status` varchar(10) DEFAULT NULL COMMENT '交易状态（0：未支付 1:已支付 2:取消）',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '申请金额',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '交易时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '交易备注',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `code` bigint(20) DEFAULT NULL COMMENT '交易流水号（14位 参考订单号生成规则）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_enterprise_serial_number
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_serial_number`;
CREATE TABLE `ysh_enterprise_serial_number` (
  `enterprise_id` bigint(20) NOT NULL,
  `serial_number` int(11) DEFAULT NULL,
  `current_date` int(11) DEFAULT NULL,
  PRIMARY KEY (`enterprise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ysh_enterprise_ucoincode
-- ----------------------------
DROP TABLE IF EXISTS `ysh_enterprise_ucoincode`;
CREATE TABLE `ysh_enterprise_ucoincode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `enterprise_id` bigint(20) DEFAULT NULL COMMENT '企业信息ID',
  `ucoincode_id` bigint(20) DEFAULT NULL COMMENT '兑换码ID',
  `code` varchar(40) DEFAULT NULL COMMENT '交易流水号（14位 参考订单号生成规则）',
  `status` varchar(10) DEFAULT NULL COMMENT '交易状态（0：未支付 1:已支付 2:取消）',
  `ucoin_count` int(10) DEFAULT NULL COMMENT '总数 （兑换码数）',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '交易金额',
  `ucoin_price` decimal(10,2) DEFAULT NULL COMMENT '面值',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '交易时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '交易备注',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '账号ID',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_mall
-- ----------------------------
DROP TABLE IF EXISTS `ysh_mall`;
CREATE TABLE `ysh_mall` (
  `mall_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商城ID',
  `mall_name` varchar(50) DEFAULT NULL COMMENT '商城名称',
  `mall_currency` varchar(30) DEFAULT NULL COMMENT '商城货币名称',
  `mall_exchange_rate` decimal(10,2) DEFAULT NULL COMMENT '商城货币兑换比率',
  `mall_flag` enum('1','0') DEFAULT NULL COMMENT '状态标志（1：停用 0：启用）',
  `mall_create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `mall_modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `mall_create_id` int(10) DEFAULT NULL COMMENT '创建人',
  `mall_modify_id` int(10) DEFAULT NULL COMMENT '修改人',
  `mall_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`mall_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ysh_mall_goods
-- ----------------------------
DROP TABLE IF EXISTS `ysh_mall_goods`;
CREATE TABLE `ysh_mall_goods` (
  `mall_goods_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商城货品表ID',
  `mall_id` int(10) DEFAULT NULL COMMENT '商城ID',
  `goods_info_id` int(10) DEFAULT NULL COMMENT '货品ID',
  `goods_id` int(10) DEFAULT NULL COMMENT '商品ID',
  `store_id` int(10) DEFAULT NULL COMMENT '商家ID',
  `mall_goods_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `mall_goods_price` decimal(10,2) DEFAULT NULL COMMENT '商城价格',
  `mall_goods_create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `mall_goods_modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `mall_goods_create_id` int(10) DEFAULT NULL COMMENT '创建人',
  `mall_goods_modify_id` int(10) DEFAULT NULL COMMENT '修改人',
  `mall_goods_flag` enum('1','0') DEFAULT NULL COMMENT '删除标志（1：已删除 0：未删除）',
  PRIMARY KEY (`mall_goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ysh_msg_log
-- ----------------------------
DROP TABLE IF EXISTS `ysh_msg_log`;
CREATE TABLE `ysh_msg_log` (
  `msg_log_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '短信验证码ID',
  `msg_content` varchar(255) DEFAULT NULL COMMENT '短信内容',
  `msg_cellphone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `msg_create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`msg_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_sync
-- ----------------------------
DROP TABLE IF EXISTS `ysh_sync`;
CREATE TABLE `ysh_sync` (
  `sync_function_name` varchar(20) DEFAULT NULL COMMENT '同步方法名',
  `sync_function_time` timestamp NULL DEFAULT NULL COMMENT '同步方法时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ysh_ucoincode
-- ----------------------------
DROP TABLE IF EXISTS `ysh_ucoincode`;
CREATE TABLE `ysh_ucoincode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '兑换券ID',
  `enterprise_id` bigint(20) DEFAULT NULL COMMENT '企业信息ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '兑换码备注',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '兑换码开始时间',
  `send_time` timestamp NULL DEFAULT NULL COMMENT '兑换码发放时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除,0表示未删除，1表示删除',
  `price` decimal(10,2) DEFAULT NULL COMMENT '兑换券面值',
  `count` int(10) DEFAULT NULL COMMENT '数量',
  `sum_price` decimal(10,2) DEFAULT NULL COMMENT '兑换码总金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for ysh_ucoincode_code
-- ----------------------------
DROP TABLE IF EXISTS `ysh_ucoincode_code`;
CREATE TABLE `ysh_ucoincode_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '兑换码详情ID',
  `ucoincode_id` bigint(20) DEFAULT NULL COMMENT '兑换码ID',
  `code_no` varchar(60) DEFAULT NULL COMMENT '兑换码',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '领取用户ID',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '领取用户名称',
  `send_time` timestamp NULL DEFAULT NULL COMMENT '发放时间',
  `get_time` timestamp NULL DEFAULT NULL COMMENT '领取时间',
  `send_type` varchar(10) DEFAULT NULL COMMENT '发放ID(1:员工福利 2:会员管理 3：企业营销)',
  `send_name` varchar(255) DEFAULT NULL COMMENT '发放名称',
  `status` varchar(10) DEFAULT NULL COMMENT '发放状态 2:已领取 1:已发放 0:未发放',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '兑换码生成时间',
  `batchgrand_id` bigint(20) DEFAULT NULL COMMENT 'batchgrand 表主键ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for _system_server_id
-- ----------------------------
DROP TABLE IF EXISTS `_system_server_id`;
CREATE TABLE `_system_server_id` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mac_address` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
