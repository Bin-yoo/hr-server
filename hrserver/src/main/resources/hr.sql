/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : hr

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 10/01/2021 18:43:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

Use hr;

-- ----------------------------
-- Table structure for assessment
-- ----------------------------
DROP TABLE IF EXISTS `assessment`;
CREATE TABLE `assessment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考核id',
  `did` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考核名称',
  `remarks` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `state` enum('未开始','已开始','已结束') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '未开始' COMMENT '考核状态',
  `beginDate` datetime(0) NULL DEFAULT NULL COMMENT '开始日期',
  `endDate` datetime(0) NULL DEFAULT NULL COMMENT '结束日期',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of assessment
-- ----------------------------
INSERT INTO `assessment` VALUES (2, 1, '2019年第一季度考核', NULL, '已结束', '2019-11-12 13:14:10', '2019-11-12 15:03:26', '2019-11-12 13:14:39');
INSERT INTO `assessment` VALUES (3, 20, '2019年第二季度考核', NULL, '已开始', '2019-11-18 19:21:44', NULL, '2019-11-12 13:14:53');
INSERT INTO `assessment` VALUES (4, 1, '2019年第三季度考核01', NULL, '已开始', '2019-11-12 15:03:26', NULL, '2019-11-12 15:03:42');
INSERT INTO `assessment` VALUES (5, 1, '考核测试', '这是测试', '已开始', '2019-11-19 22:49:04', NULL, '2019-11-14 13:54:21');
INSERT INTO `assessment` VALUES (6, 1, '考核测试', '这是测试', '未开始', NULL, NULL, '2019-11-14 13:54:58');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `depNum` int(10) NOT NULL COMMENT '部门编号',
  `orderNum` int(10) NULL DEFAULT NULL COMMENT '排序编号',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `parentId` int(11) NOT NULL COMMENT '父亲节点id',
  `depPath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `depNum`(`depNum`) USING BTREE COMMENT '部门编号唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, 1, NULL, '股东会', -1, NULL);
INSERT INTO `department` VALUES (2, 2, 1, '董事会', 1, NULL);
INSERT INTO `department` VALUES (15, 11, NULL, '营销策划部', 2, NULL);
INSERT INTO `department` VALUES (16, 12, NULL, '工程管理部', 2, NULL);
INSERT INTO `department` VALUES (17, 13, NULL, '企业管理部', 2, NULL);
INSERT INTO `department` VALUES (18, 14, NULL, '销售部', 15, NULL);
INSERT INTO `department` VALUES (19, 15, NULL, '策划部', 15, NULL);
INSERT INTO `department` VALUES (20, 16, NULL, '设计部', 16, NULL);
INSERT INTO `department` VALUES (21, 17, NULL, '工程部', 16, NULL);
INSERT INTO `department` VALUES (22, 18, NULL, '总工室', 16, NULL);
INSERT INTO `department` VALUES (23, 19, NULL, '装修部', 16, NULL);
INSERT INTO `department` VALUES (24, 20, NULL, '预决算部', 16, NULL);
INSERT INTO `department` VALUES (25, 21, NULL, '项目拓展部', 16, NULL);
INSERT INTO `department` VALUES (26, 22, NULL, '人力资源部', 17, NULL);
INSERT INTO `department` VALUES (27, 23, NULL, '行政部', 17, NULL);
INSERT INTO `department` VALUES (28, 24, NULL, '物业后勤部', 17, NULL);
INSERT INTO `department` VALUES (29, 25, NULL, '公关部', 17, NULL);
INSERT INTO `department` VALUES (30, 27, NULL, '财务部', 2, NULL);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `workId` bigint(20) NOT NULL COMMENT '员工工号',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工名称',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别',
  `nationId` int(11) NOT NULL COMMENT '民族',
  `birthday` date NOT NULL COMMENT '出生日期',
  `politiclId` int(11) NOT NULL COMMENT '政治面貌',
  `wedlock` enum('已婚','未婚','离异') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '婚姻状态',
  `nativePlace` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `idCard` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系电话',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系地址',
  `departmentId` int(11) NULL DEFAULT NULL COMMENT '所属部门',
  `positionId` int(11) NULL DEFAULT NULL COMMENT '职位id',
  `jobLevelId` int(11) NULL DEFAULT NULL COMMENT '职称id',
  `titopDegree` enum('博士','硕士','本科','大专','高中','初中','小学','其他') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最高学历',
  `specialty` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属专业',
  `school` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '毕业院校',
  `beginDate` date NOT NULL COMMENT '入职日期',
  `workState` enum('在职','离职') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '在职状态',
  `conversionTime` date NULL DEFAULT NULL COMMENT '转正日期',
  `quitTime` date NULL DEFAULT NULL COMMENT '离职日期',
  `beginContract` date NOT NULL COMMENT '合同起始日期',
  `endContract` date NOT NULL COMMENT '合同结束日期',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工照片',
  `baseSalary` double(10, 2) NULL DEFAULT NULL COMMENT '基本工资',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `workId`(`workId`) USING BTREE,
  INDEX `FK_Reference_1`(`politiclId`) USING BTREE,
  INDEX `FK_Reference_2`(`nationId`) USING BTREE,
  INDEX `FK_Reference_3`(`departmentId`) USING BTREE,
  INDEX `jobLevelID`(`jobLevelId`) USING BTREE,
  INDEX `positionId`(`positionId`) USING BTREE,
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`politiclId`) REFERENCES `political_status` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`nationId`) REFERENCES `nation` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`jobLevelId`) REFERENCES `job_level` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`positionId`) REFERENCES `position` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (16, 1702040011, '宇哥', '男', 1, '1998-11-07', 4, '未婚', '广西', '450403119980808465', '13131313@qq.com', '18087060974', '测试', 1, 1, 3, '本科', '软件', '南职', '2019-11-06', '在职', '2019-11-12', '2019-11-12', '2019-11-06', '2019-11-06', '', 3200.00);
INSERT INTO `employee` VALUES (17, 1702040065, '韦寿海', '男', 1, '1998-08-08', 3, '未婚', '广东', '4504061998081561', '180@qq.com', '180780900446', '南职', 1, 1, 1, '高中', '弟弟', '南职', '2011-02-02', '在职', '2019-11-01', '2019-11-12', '2011-02-02', '2020-01-16', NULL, 3000.00);
INSERT INTO `employee` VALUES (18, 1001, '李四', '男', 53, '2019-10-31', 1, '未婚', '广西', '450208154512252524', '134@163.com', '10086', '广西南宁市', 1, 1, 1, '本科', '软件技术', '南宁职业技术学院', '2019-11-05', '在职', '2019-12-05', '2019-11-11', '2019-11-12', '2023-03-08', NULL, 1200.00);
INSERT INTO `employee` VALUES (19, 1002, '张三', '男', 3, '2019-11-05', 1, '未婚', '广西', '45020815555122254', '19234@qq.com', '174718522321', '广西南宁市', 2, 1, 1, '小学', '软件技术', '南宁职业技术学院', '2019-11-01', '在职', '2020-01-01', NULL, '2019-11-01', '2019-11-30', NULL, 1200.00);
INSERT INTO `employee` VALUES (20, 1702040160, '方华', '女', 5, '1988-11-10', 13, '未婚', '上海', '431202198811101720', '134515@qq.com', '18724864821', '上海', 27, 1, 3, '本科', '软件工程', '广西民族大学', '2019-11-11', '在职', '2020-01-11', '2019-11-11', '2019-11-11', '2020-11-11', NULL, 10000.00);
INSERT INTO `employee` VALUES (21, 1702040161, '常善勤', '男', 1, '1991-03-27', 1, '未婚', '南宁', '130821199103278829', '535156@qq.com', '158456715841', '南宁', 1, 1, 4, '硕士', '软件工程', '广西大学', '2018-11-12', '在职', '2018-12-12', NULL, '2018-11-12', '2020-11-12', NULL, 10000.00);
INSERT INTO `employee` VALUES (22, 1702040162, '伏佳', '女', 6, '1995-06-13', 1, '未婚', '南宁', '350105199506138487', '5641156@qq.com', '18734884561', '南宁', 2, 1, 4, '本科', '软件工程', '广西民族大学', '2019-11-11', '在职', '2020-02-11', NULL, '2019-11-11', '2020-11-11', NULL, 10000.00);
INSERT INTO `employee` VALUES (23, 1702040163, '汤琪', '男', 3, '1989-08-03', 10, '已婚', '广东佛山', '152224198908049211', '5641115634156@qq.com', '15612468468571', '广东佛山', 23, 2, 3, '本科', '软件工程', '东南大学', '2019-11-11', '在职', '2020-02-11', '2019-11-12', '2019-11-11', '2020-11-11', NULL, 10000.00);
INSERT INTO `employee` VALUES (24, 1702040164, '司空坚', '男', 1, '1994-07-12', 7, '未婚', '广东佛山', '235402199407121617', '5641115634156@qq.com', '15612468468571', '广东佛山', 22, 2, 3, '本科', '软件工程', '东南大学', '2019-11-11', '在职', '2020-02-11', '2019-11-12', '2019-11-11', '2020-11-11', NULL, 10000.00);
INSERT INTO `employee` VALUES (25, 1702040165, '胡杰枝', '女', 1, '1989-12-11', 1, '未婚', '广东佛山', '120111198912116881', '5641115634156@qq.com', '15634587911', '广东佛山', 20, 2, 3, '本科', '软件工程', '东南大学', '2019-11-11', '在职', '2020-02-11', '2019-11-12', '2019-11-11', '2020-11-11', NULL, 10000.00);
INSERT INTO `employee` VALUES (29, 1702040167, '孔姬宜', '女', 1, '1987-01-08', 12, '未婚', '广东佛山', '441623198701083264', '56411234@qq.com', '15634584891', '广东佛山', 1, 2, 3, '本科', '软件工程', '东南大学', '2019-11-11', '在职', '2020-02-11', NULL, '2019-11-11', '2020-11-11', NULL, 10000.00);
INSERT INTO `employee` VALUES (35, 1702030023, '李华', '男', 2, '2015-10-09', 1, '未婚', '广西', '450102200002012323', '19239@qq.com', '18072327181', '广西南宁市', 19, 1, 1, '大专', '软件', '南宁职业技术学院', '2019-11-12', '在职', '2020-11-17', NULL, '2019-11-12', '2021-11-18', NULL, 1400.00);
INSERT INTO `employee` VALUES (36, 1003, '杨仁', '男', 2, '2019-11-01', 2, '未婚', '广西', '450102200001012343', '1003@163.com', '18073718291', '广西南宁市', 27, 1, 1, '大专', '软件技术', '南宁职业技术学院', '2019-11-12', '在职', '2020-01-21', NULL, '2019-11-12', '2021-11-12', NULL, 1400.00);
INSERT INTO `employee` VALUES (37, 1004, '杨德', '男', 3, '2019-11-01', 2, '未婚', '广西', '450102200001012343', '1003@163.com', '18073718291', '广西南宁市', 19, 1, 2, '大专', '软件技术', '南宁职业技术学院', '2019-11-12', '在职', '2020-01-21', NULL, '2019-11-12', '2021-11-12', NULL, 1500.00);
INSERT INTO `employee` VALUES (38, 1005, '杨标', '男', 28, '2019-11-01', 3, '未婚', '广西', '450102200001012344', '1003@163.com', '18073718291', '广西南宁市', 21, 2, 1, '大专', '软件技术', '南宁职业技术学院', '2019-11-12', '在职', '2020-01-21', NULL, '2019-11-12', '2021-11-12', NULL, 1500.00);
INSERT INTO `employee` VALUES (40, 1006, '杨志', '男', 15, '2019-11-01', 2, '未婚', '广西', '450102200001012345', '1003@163.com', '18073718291', '广西南宁市', 19, 1, 1, '大专', '软件技术', '南宁职业技术学院', '2019-11-12', '在职', '2020-01-21', NULL, '2019-11-12', '2021-11-12', NULL, 1400.00);
INSERT INTO `employee` VALUES (42, 1007, '李三', '男', 1, '2018-10-08', 1, '未婚', '广西', '450102199912334334', '1239@163.com', '18078321232', '广西南宁市', 1, 1, 1, '大专', '软件技术', '南宁职业技术学院', '2019-11-12', '在职', '2020-11-19', '2019-11-19', '2019-11-12', '2021-11-12', 'http://111.230.141.100:8080/uploadFiles/emp/2019112010352884353b61ly1g3w6p60v2wj21w019c7wj.jpg', 0.00);
INSERT INTO `employee` VALUES (43, 1008, '梁生', '男', 1, '1998-06-13', 1, '未婚', '广西南宁', '45088119980613114X', '15144156@qq.com', '15677561359', '南宁', 26, 1, 9, '大专', '软件技术', '南宁职业技术学院', '2019-11-12', '在职', '2019-11-12', '2019-11-12', '2019-11-12', '2020-11-12', NULL, 15000.00);
INSERT INTO `employee` VALUES (44, 1009, '梁彬', '男', 1, '1998-12-26', 13, '未婚', '广西', '450205199812263438', '16663@163.com', '18074611224', '广西南宁市', 21, 1, 2, '大专', '软件技术', '南宁职业技术学院', '2019-11-12', '在职', '2019-11-12', '2019-11-12', '2019-11-12', '2021-11-19', NULL, 1400.00);
INSERT INTO `employee` VALUES (45, 1702040031, '洋大人', '男', 6, '2019-11-14', 4, '未婚', '', '450406999984851555', '', '180780609777', '', 22, 2, 3, '本科', '软件', '南职院', '2019-10-28', '在职', '2019-11-18', '2019-11-18', '2019-11-04', '2019-11-30', 'http://111.230.141.100:8080/uploadFiles/emp/20191118233418QQ图片20191113153348.jpg', 2000.00);
INSERT INTO `employee` VALUES (46, 191126153209, '杨志标', '男', 2, '2019-10-08', 2, '未婚', '广西', '450403166180891561', '16464161@qq.com', '19080856215', '西乡塘', 21, 2, 2, '高中', '软件', '南职', '2019-11-12', '在职', '2019-11-20', NULL, '2019-06-30', '2019-11-30', '', 2000.00);
INSERT INTO `employee` VALUES (55, 19193615731, '代沟高', '男', 2, '2019-10-08', 2, '未婚', '广西', '450403166180891561', '16464161@qq.com', '19080856215', '西乡塘', 21, 2, 2, '高中', '软件', '南职', '2019-11-12', '在职', NULL, NULL, '2019-06-30', '2019-11-30', NULL, 200.00);
INSERT INTO `employee` VALUES (56, 19193615737, '带哥', '男', 6, '2019-11-14', 4, '未婚', '', '450406999984851555', '', '180780609777', '', 22, 2, 3, '本科', '软件', '南职院', '2019-10-28', '在职', NULL, '2019-11-18', '2019-11-04', '2019-11-30', NULL, 300.00);
INSERT INTO `employee` VALUES (57, 19193801995, '测试一下', '男', 3, '2019-11-14', 3, '未婚', '', '4504609979080844', '', '18078069974', '', 17, 2, 3, '大专', 'UR哪家', '阿斯顿', '2019-11-03', '在职', NULL, NULL, '2019-11-19', '2019-11-30', 'ok', 2000.00);

-- ----------------------------
-- Table structure for employee_assessment
-- ----------------------------
DROP TABLE IF EXISTS `employee_assessment`;
CREATE TABLE `employee_assessment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工考核表id',
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `aid` int(11) NULL DEFAULT NULL COMMENT '考核项目id',
  `data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `result` enum('未通过','已通过','打回修改') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批结果',
  `isCheck` int(1) NULL DEFAULT NULL COMMENT '是否查阅',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `eid`(`eid`, `aid`) USING BTREE,
  INDEX `emp_id`(`eid`) USING BTREE,
  INDEX `ass_id`(`aid`) USING BTREE,
  CONSTRAINT `employee_assessment_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employee_assessment_ibfk_2` FOREIGN KEY (`aid`) REFERENCES `assessment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_assessment
-- ----------------------------
INSERT INTO `employee_assessment` VALUES (1, 16, 4, NULL, '测试', NULL, 0);
INSERT INTO `employee_assessment` VALUES (2, 17, 4, NULL, '这也是测试', NULL, 0);
INSERT INTO `employee_assessment` VALUES (3, 42, 3, 'http://111.230.141.100:8080/uploadFiles/assessment/20191119210534班会照片.rar', '测试一哈', '未通过', 1);
INSERT INTO `employee_assessment` VALUES (4, 42, 5, 'http://111.230.141.100:8080/uploadFiles/assessment/20191119224927附件7 ：信息工程学院 《广西壮族自治区普通高等学校毕业生登记表》填写范本.docx', '测试01', NULL, NULL);

-- ----------------------------
-- Table structure for employee_salary
-- ----------------------------
DROP TABLE IF EXISTS `employee_salary`;
CREATE TABLE `employee_salary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工资表id',
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `baseSalary` double(10, 2) NULL DEFAULT NULL COMMENT '基本工资',
  `meritSalary` double(10, 2) NULL DEFAULT NULL COMMENT '绩效工资',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `eid`(`eid`) USING BTREE,
  CONSTRAINT `employee_salary_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_salary
-- ----------------------------
INSERT INTO `employee_salary` VALUES (7, 16, 3200.00, 4100.00);
INSERT INTO `employee_salary` VALUES (8, 17, 3000.00, 3500.00);
INSERT INTO `employee_salary` VALUES (9, 18, 1200.00, 1700.00);
INSERT INTO `employee_salary` VALUES (10, 19, 1200.00, 1700.00);
INSERT INTO `employee_salary` VALUES (11, 20, 10000.00, 10500.00);
INSERT INTO `employee_salary` VALUES (12, 21, 10000.00, 10500.00);
INSERT INTO `employee_salary` VALUES (13, 22, 10000.00, 10500.00);
INSERT INTO `employee_salary` VALUES (14, 23, 10000.00, 10500.00);
INSERT INTO `employee_salary` VALUES (15, 24, 10000.00, 10500.00);
INSERT INTO `employee_salary` VALUES (16, 25, 10000.00, 10500.00);
INSERT INTO `employee_salary` VALUES (17, 29, 10000.00, 10500.00);
INSERT INTO `employee_salary` VALUES (18, 35, 1400.00, 1900.00);
INSERT INTO `employee_salary` VALUES (19, 36, 1400.00, 1900.00);
INSERT INTO `employee_salary` VALUES (20, 37, 1500.00, 2000.00);
INSERT INTO `employee_salary` VALUES (21, 38, 1500.00, 2000.00);
INSERT INTO `employee_salary` VALUES (22, 40, 1400.00, 1900.00);
INSERT INTO `employee_salary` VALUES (23, 42, 1200.00, 1700.00);
INSERT INTO `employee_salary` VALUES (24, 43, 15000.00, 10500.00);
INSERT INTO `employee_salary` VALUES (25, 44, 1400.00, 1900.00);
INSERT INTO `employee_salary` VALUES (26, 45, 2000.00, 2500.00);
INSERT INTO `employee_salary` VALUES (27, 46, 2000.00, 2500.00);
INSERT INTO `employee_salary` VALUES (33, 55, 200.00, 700.00);
INSERT INTO `employee_salary` VALUES (34, 56, 300.00, 800.00);
INSERT INTO `employee_salary` VALUES (35, 57, 2000.00, 2500.00);

-- ----------------------------
-- Table structure for employee_transfer
-- ----------------------------
DROP TABLE IF EXISTS `employee_transfer`;
CREATE TABLE `employee_transfer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '调动日志id',
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `befDep` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调动前部门',
  `afterDep` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调动后部门',
  `befJobLvl` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调动前职称',
  `afterJobLvl` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调动后职称',
  `befPos` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调动前职位',
  `afterPos` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调动后职位',
  `date` date NULL DEFAULT NULL COMMENT '调动日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `eid`(`eid`) USING BTREE,
  CONSTRAINT `employee_transfer_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for integral
-- ----------------------------
DROP TABLE IF EXISTS `integral`;
CREATE TABLE `integral`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `integral` int(11) NULL DEFAULT NULL COMMENT '员工积分',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `eid`(`eid`) USING BTREE,
  CONSTRAINT `integral_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of integral
-- ----------------------------
INSERT INTO `integral` VALUES (2, 16, 100);
INSERT INTO `integral` VALUES (3, 17, 102);
INSERT INTO `integral` VALUES (4, 18, 103);
INSERT INTO `integral` VALUES (5, 19, 100);
INSERT INTO `integral` VALUES (6, 20, 100);
INSERT INTO `integral` VALUES (7, 21, 100);
INSERT INTO `integral` VALUES (8, 22, 100);
INSERT INTO `integral` VALUES (9, 23, 100);
INSERT INTO `integral` VALUES (10, 24, 100);
INSERT INTO `integral` VALUES (11, 25, 100);
INSERT INTO `integral` VALUES (12, 29, 100);
INSERT INTO `integral` VALUES (13, 35, 100);
INSERT INTO `integral` VALUES (14, 36, 100);
INSERT INTO `integral` VALUES (15, 37, 100);
INSERT INTO `integral` VALUES (16, 38, 100);
INSERT INTO `integral` VALUES (17, 40, 100);
INSERT INTO `integral` VALUES (18, 42, 105);
INSERT INTO `integral` VALUES (19, 43, 105);
INSERT INTO `integral` VALUES (20, 44, 100);
INSERT INTO `integral` VALUES (21, 45, 100);
INSERT INTO `integral` VALUES (22, 46, 100);
INSERT INTO `integral` VALUES (28, 55, 100);
INSERT INTO `integral` VALUES (29, 56, 100);
INSERT INTO `integral` VALUES (30, 57, 100);

-- ----------------------------
-- Table structure for job_level
-- ----------------------------
DROP TABLE IF EXISTS `job_level`;
CREATE TABLE `job_level`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '职称id',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职称名称',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_level
-- ----------------------------
INSERT INTO `job_level` VALUES (1, '初级工程师', '2019-10-24 10:56:58', NULL);
INSERT INTO `job_level` VALUES (2, '中级工程师', '2019-10-24 11:15:19', NULL);
INSERT INTO `job_level` VALUES (3, '高级工程师', '2019-10-24 11:15:21', NULL);
INSERT INTO `job_level` VALUES (4, '骨灰级工程师', '2019-10-24 11:15:24', NULL);
INSERT INTO `job_level` VALUES (6, '会计员', '2019-11-12 14:55:31', '');
INSERT INTO `job_level` VALUES (7, '助理会计师', '2019-11-12 14:55:39', '');
INSERT INTO `job_level` VALUES (8, '中级会计师', '2019-11-12 14:55:56', '');
INSERT INTO `job_level` VALUES (9, '高级会计师', '2019-11-12 14:56:03', '');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后台接口路径',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `iconCls` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `keepAlive` tinyint(1) NULL DEFAULT NULL,
  `requireAuth` tinyint(1) NULL DEFAULT NULL COMMENT '是否需要登录',
  `parentId` tinyint(2) NULL DEFAULT NULL COMMENT '父节点id',
  `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '/', NULL, NULL, '所有', NULL, NULL, NULL, NULL, 1);
INSERT INTO `menu` VALUES (2, '/', '/home', 'Home', '员工档案', 'ios-folder', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (3, '/', '/home', 'Home', '人事管理', 'ios-contacts\r\n\r\nios-contacts', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (4, '/', '/home', 'Home', '综合统计', 'ios-pie', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (5, '/', '/home', 'Home', '系统设置', 'ios-settings', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (6, '/employee/**', '/employee/employeefile', 'Employee', '员工档案', 'ios-folder', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (7, '/personnel/salary/**', '/personnel/salary', 'Salary', '工资管理', NULL, NULL, 1, 3, 1);
INSERT INTO `menu` VALUES (8, '/personnel/assessment/**', '/personnel/assessment', 'Assessment', '考核管理', NULL, NULL, 1, 3, 1);
INSERT INTO `menu` VALUES (9, '/personnel/rp/**', '/personnel/rp', 'Rp', '奖惩管理', NULL, NULL, 1, 3, 1);
INSERT INTO `menu` VALUES (10, '/statistics/total/**', '/statistics/total', 'TotalStatistics', '综合信息统计', NULL, NULL, 1, 4, 1);
INSERT INTO `menu` VALUES (11, '/statistics/integral/**', '/statistics/integral', 'IntegralStatistics', '员工积分统计', NULL, NULL, 1, 4, 1);
INSERT INTO `menu` VALUES (12, '/system/basic/**', '/system/basic', 'Basic', '基础设置', NULL, NULL, 1, 5, 1);
INSERT INTO `menu` VALUES (13, '/system/role/**', '/system/role', 'Role', '角色管理', NULL, NULL, 1, 5, 1);
INSERT INTO `menu` VALUES (14, '/system/user/**', '/system/user', 'User', '用户管理', NULL, NULL, 1, 5, 1);
INSERT INTO `menu` VALUES (17, '/personalfile/**', '/employee/personalfile', 'PersonalFile', '个人档案', 'ios-folder', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (18, '/personnel/assessment/**', '/personnel/myassessment', 'MyAssessment', '我的考核', 'ios-trending-up', NULL, 1, 3, 1);

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单权限id',
  `mid` int(11) NULL DEFAULT NULL COMMENT '菜单id',
  `rid` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mid`(`mid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  CONSTRAINT `menu_role_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `menu_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 131 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES (1, 6, 1);
INSERT INTO `menu_role` VALUES (2, 7, 1);
INSERT INTO `menu_role` VALUES (3, 8, 1);
INSERT INTO `menu_role` VALUES (5, 9, 1);
INSERT INTO `menu_role` VALUES (6, 10, 1);
INSERT INTO `menu_role` VALUES (7, 11, 1);
INSERT INTO `menu_role` VALUES (8, 12, 1);
INSERT INTO `menu_role` VALUES (9, 13, 1);
INSERT INTO `menu_role` VALUES (10, 14, 1);
INSERT INTO `menu_role` VALUES (103, 17, 2);
INSERT INTO `menu_role` VALUES (104, 18, 2);
INSERT INTO `menu_role` VALUES (105, 6, 125);
INSERT INTO `menu_role` VALUES (106, 17, 125);
INSERT INTO `menu_role` VALUES (107, 7, 125);
INSERT INTO `menu_role` VALUES (108, 8, 125);
INSERT INTO `menu_role` VALUES (109, 9, 125);
INSERT INTO `menu_role` VALUES (110, 18, 125);
INSERT INTO `menu_role` VALUES (111, 12, 125);
INSERT INTO `menu_role` VALUES (112, 13, 125);
INSERT INTO `menu_role` VALUES (113, 14, 125);
INSERT INTO `menu_role` VALUES (114, 6, 3);
INSERT INTO `menu_role` VALUES (115, 17, 3);
INSERT INTO `menu_role` VALUES (116, 7, 3);
INSERT INTO `menu_role` VALUES (117, 8, 3);
INSERT INTO `menu_role` VALUES (118, 9, 3);
INSERT INTO `menu_role` VALUES (119, 18, 3);
INSERT INTO `menu_role` VALUES (126, 7, 4);
INSERT INTO `menu_role` VALUES (127, 8, 4);
INSERT INTO `menu_role` VALUES (128, 9, 4);
INSERT INTO `menu_role` VALUES (129, 18, 4);
INSERT INTO `menu_role` VALUES (130, 10, 4);
INSERT INTO `menu_role` VALUES (131, 11, 4);

-- ----------------------------
-- Table structure for nation
-- ----------------------------
DROP TABLE IF EXISTS `nation`;
CREATE TABLE `nation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '民族id',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '民族名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nation
-- ----------------------------
INSERT INTO `nation` VALUES (1, '汉族');
INSERT INTO `nation` VALUES (2, '蒙古族');
INSERT INTO `nation` VALUES (3, '回族');
INSERT INTO `nation` VALUES (4, '藏族');
INSERT INTO `nation` VALUES (5, '维吾尔族');
INSERT INTO `nation` VALUES (6, '苗族');
INSERT INTO `nation` VALUES (7, '彝族');
INSERT INTO `nation` VALUES (8, '壮族');
INSERT INTO `nation` VALUES (9, '布依族');
INSERT INTO `nation` VALUES (10, '朝鲜族');
INSERT INTO `nation` VALUES (11, '满族');
INSERT INTO `nation` VALUES (12, '侗族');
INSERT INTO `nation` VALUES (13, '瑶族');
INSERT INTO `nation` VALUES (14, '白族');
INSERT INTO `nation` VALUES (15, '土家族');
INSERT INTO `nation` VALUES (16, '哈尼族');
INSERT INTO `nation` VALUES (17, '哈萨克族');
INSERT INTO `nation` VALUES (18, '傣族');
INSERT INTO `nation` VALUES (19, '黎族');
INSERT INTO `nation` VALUES (20, '傈僳族');
INSERT INTO `nation` VALUES (21, '佤族');
INSERT INTO `nation` VALUES (22, '畲族');
INSERT INTO `nation` VALUES (23, '高山族');
INSERT INTO `nation` VALUES (24, '拉祜族');
INSERT INTO `nation` VALUES (25, '水族');
INSERT INTO `nation` VALUES (26, '东乡族');
INSERT INTO `nation` VALUES (27, '纳西族');
INSERT INTO `nation` VALUES (28, '景颇族');
INSERT INTO `nation` VALUES (29, '柯尔克孜族');
INSERT INTO `nation` VALUES (30, '土族');
INSERT INTO `nation` VALUES (31, '达斡尔族');
INSERT INTO `nation` VALUES (32, '仫佬族');
INSERT INTO `nation` VALUES (33, '羌族');
INSERT INTO `nation` VALUES (34, '布朗族');
INSERT INTO `nation` VALUES (35, '撒拉族');
INSERT INTO `nation` VALUES (36, '毛难族');
INSERT INTO `nation` VALUES (37, '仡佬族');
INSERT INTO `nation` VALUES (38, '锡伯族');
INSERT INTO `nation` VALUES (39, '阿昌族');
INSERT INTO `nation` VALUES (40, '普米族');
INSERT INTO `nation` VALUES (41, '塔吉克族');
INSERT INTO `nation` VALUES (42, '怒族');
INSERT INTO `nation` VALUES (43, '乌孜别克族');
INSERT INTO `nation` VALUES (44, '俄罗斯族');
INSERT INTO `nation` VALUES (45, '鄂温克族');
INSERT INTO `nation` VALUES (46, '崩龙族');
INSERT INTO `nation` VALUES (47, '保安族');
INSERT INTO `nation` VALUES (48, '裕固族');
INSERT INTO `nation` VALUES (49, '京族');
INSERT INTO `nation` VALUES (50, '塔塔尔族');
INSERT INTO `nation` VALUES (51, '独龙族');
INSERT INTO `nation` VALUES (52, '鄂伦春族');
INSERT INTO `nation` VALUES (53, '赫哲族');
INSERT INTO `nation` VALUES (54, '门巴族');
INSERT INTO `nation` VALUES (55, '珞巴族');
INSERT INTO `nation` VALUES (56, '基诺族');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口路径',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `parentId` tinyint(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, NULL, '所有', NULL);
INSERT INTO `permission` VALUES (2, NULL, '员工档案', 1);
INSERT INTO `permission` VALUES (3, NULL, '工资管理', 1);
INSERT INTO `permission` VALUES (4, NULL, '考核管理', 1);
INSERT INTO `permission` VALUES (5, NULL, '奖惩管理', 1);
INSERT INTO `permission` VALUES (6, '/addEmpFile', '添加档案', 2);
INSERT INTO `permission` VALUES (7, '/viewEmpFile', '查看档案', 2);
INSERT INTO `permission` VALUES (8, '/modifyEmpFile', '修改档案', 2);
INSERT INTO `permission` VALUES (9, '/deleteEmpFile', '删除档案', 2);
INSERT INTO `permission` VALUES (10, '/viewSalaryLog', '查看调薪记录', 3);
INSERT INTO `permission` VALUES (11, '/updateSalary', '调薪', 3);
INSERT INTO `permission` VALUES (12, '/addAssessment', '添加考核', 4);
INSERT INTO `permission` VALUES (13, '/changeAssState', '更改考核状态', 4);
INSERT INTO `permission` VALUES (14, '/reviewAssessment', '审核考核', 4);
INSERT INTO `permission` VALUES (15, '/modifyAssessment', '编辑考核', 4);
INSERT INTO `permission` VALUES (16, '/deleteAssessment', '删除考核', 4);
INSERT INTO `permission` VALUES (17, '/addRp', '添加奖惩', 5);
INSERT INTO `permission` VALUES (18, '/updateRp', '修改奖惩', 5);
INSERT INTO `permission` VALUES (19, '/deleteRp', '删除奖惩', 5);

-- ----------------------------
-- Table structure for political_status
-- ----------------------------
DROP TABLE IF EXISTS `political_status`;
CREATE TABLE `political_status`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '政治面貌id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '政治面貌名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of political_status
-- ----------------------------
INSERT INTO `political_status` VALUES (1, '中共党员');
INSERT INTO `political_status` VALUES (2, '中共预备党员');
INSERT INTO `political_status` VALUES (3, '共青团员');
INSERT INTO `political_status` VALUES (4, '民革党员');
INSERT INTO `political_status` VALUES (5, '民盟盟员');
INSERT INTO `political_status` VALUES (6, '民建会员');
INSERT INTO `political_status` VALUES (7, '民进会员');
INSERT INTO `political_status` VALUES (8, '农工党党员');
INSERT INTO `political_status` VALUES (9, '致公党党员');
INSERT INTO `political_status` VALUES (10, '九三学社社员');
INSERT INTO `political_status` VALUES (11, '台盟盟员');
INSERT INTO `political_status` VALUES (12, '无党派民主人士');
INSERT INTO `political_status` VALUES (13, '普通公民');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '职位id',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职位名称',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (1, '部门经理', '2019-10-24 10:58:20', NULL);
INSERT INTO `position` VALUES (2, '部门主管', '2019-10-24 20:28:29', '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '系统管理员', '2019-10-22 17:31:30', NULL);
INSERT INTO `role` VALUES (2, '员工', '2019-10-22 17:31:33', NULL);
INSERT INTO `role` VALUES (3, '部门经理', '2019-10-22 17:31:35', NULL);
INSERT INTO `role` VALUES (4, '人事专员', '2019-10-22 17:31:38', NULL);
INSERT INTO `role` VALUES (5, '招聘主管', '2019-10-22 17:31:40', NULL);
INSERT INTO `role` VALUES (6, '培训主管', '2019-10-22 17:31:42', NULL);
INSERT INTO `role` VALUES (7, '薪酬绩效主管', '2019-10-23 11:30:38', NULL);
INSERT INTO `role` VALUES (125, '测试角色', '2019-11-11 16:10:28', '');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单权限id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '权限id',
  `rid` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mid`(`pid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 122 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (81, 6, 1);
INSERT INTO `role_permission` VALUES (82, 7, 1);
INSERT INTO `role_permission` VALUES (83, 8, 1);
INSERT INTO `role_permission` VALUES (84, 9, 1);
INSERT INTO `role_permission` VALUES (85, 10, 1);
INSERT INTO `role_permission` VALUES (86, 11, 1);
INSERT INTO `role_permission` VALUES (87, 12, 1);
INSERT INTO `role_permission` VALUES (88, 13, 1);
INSERT INTO `role_permission` VALUES (89, 14, 1);
INSERT INTO `role_permission` VALUES (90, 15, 1);
INSERT INTO `role_permission` VALUES (91, 16, 1);
INSERT INTO `role_permission` VALUES (92, 17, 1);
INSERT INTO `role_permission` VALUES (93, 18, 1);
INSERT INTO `role_permission` VALUES (94, 19, 1);

-- ----------------------------
-- Table structure for rp
-- ----------------------------
DROP TABLE IF EXISTS `rp`;
CREATE TABLE `rp`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '奖惩id',
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `date` date NULL DEFAULT NULL COMMENT '日期',
  `type` enum('奖励','惩罚') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '奖惩分类',
  `reason` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '奖惩理由',
  `point` int(11) NULL DEFAULT NULL COMMENT '奖惩分数',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_13`(`eid`) USING BTREE,
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rp
-- ----------------------------
INSERT INTO `rp` VALUES (1, 18, '2019-11-11', '奖励', '见义勇为', 3, NULL);
INSERT INTO `rp` VALUES (2, 43, '2019-08-10', '奖励', '超额完工', 5, NULL);
INSERT INTO `rp` VALUES (3, 17, '2019-10-14', '惩罚', '带薪拉屎', 3, NULL);
INSERT INTO `rp` VALUES (4, 17, '2019-09-25', '奖励', '超额完工', 5, NULL);
INSERT INTO `rp` VALUES (6, 42, '2019-11-19', '奖励', '超额完工', 5, '');

-- ----------------------------
-- Table structure for salary_log
-- ----------------------------
DROP TABLE IF EXISTS `salary_log`;
CREATE TABLE `salary_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `befBaseSalary` double(10, 2) NULL DEFAULT NULL COMMENT '调薪前基本工资',
  `afterBaseSalary` double(10, 2) NULL DEFAULT NULL COMMENT '调薪后基本工资',
  `befMeritSalary` double(10, 2) NULL DEFAULT NULL COMMENT '调薪前绩效工资',
  `afterMeritSalary` double(10, 2) NULL DEFAULT NULL COMMENT '调薪后绩效工资',
  `date` date NULL DEFAULT NULL COMMENT '调薪日期',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary_log
-- ----------------------------
INSERT INTO `salary_log` VALUES (3, 16, 3000.00, 3500.00, 3500.00, 4000.00, '2019-11-11', '');
INSERT INTO `salary_log` VALUES (4, 16, 3500.00, 3200.00, 4000.00, 4100.00, '2019-11-12', '工作努力');
INSERT INTO `salary_log` VALUES (5, 43, 10000.00, 15000.00, 10500.00, 10500.00, '2019-11-12', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `eid` int(11) NULL DEFAULT NULL COMMENT '绑定的员工档案ID',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系电话',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '解码盐',
  `userface` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否禁用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, NULL, '超级管理员', '13123', 'sadsa', 'admin', 'a7d59dfc5332749cb801f86a24f5f590', 'e5ykFiNwShfCXvBRPr3wXg==', NULL, NULL, 1);
INSERT INTO `user` VALUES (2, NULL, '测试用户', '18087060607', 'asdasd', 'test01', 'b119cd81942b2dcec102369b4491aa2a', 'R7KTef41DrtUYGIKxi58eg==', NULL, NULL, 1);
INSERT INTO `user` VALUES (17, 16, '宇哥', '18087060974', '测试', 'yuge', '123', '123', NULL, NULL, 1);
INSERT INTO `user` VALUES (18, 17, '韦寿海', '180780900446', '南职', '1702040065', '90042dd9b4fd89b51d21ff57dbbf3577', 'SuIYHp7FfLhjcdAF+FmkYA==', NULL, NULL, 1);
INSERT INTO `user` VALUES (19, 18, '李四', '10086', '广西南宁市', '10001', '48881823edb8e14b9edace4833322c54', 'dhQDeAe00bgyFwmxDhvYdw==', NULL, NULL, 1);
INSERT INTO `user` VALUES (20, 19, '张三', '18078030020', '广西南宁市', '1002', '8d122878ea66994c86dd73dd48ba3588', 'xxkoIHoHXl3wKHPY6q42aw==', NULL, NULL, 1);
INSERT INTO `user` VALUES (21, 20, '方华', '18724864821', '上海', '1702040160', 'e66a7f438e2ceaa54af73387631ae08a', 'Eq3HIk3CMO6grSP5E1eUvA==', NULL, NULL, 1);
INSERT INTO `user` VALUES (22, 21, '常善勤', '158456715841', '南宁', '1702040161', '600df234d538fe6e0e375a4aa42f6581', 'j3YYhHB0/FW76tHThTbz7Q==', NULL, NULL, 1);
INSERT INTO `user` VALUES (23, 22, '伏佳', '18734884561', '南宁', '1702040162', 'fda168d85a03151b5a8d3a7aefb5078a', 'HXddrxqJmLUhCqGG5FgaPA==', NULL, NULL, 1);
INSERT INTO `user` VALUES (24, 23, '汤琪', '15612468468571', '广东佛山', '1702040163', 'a482cdf98547f9b5912d751041700294', 'KBgOKYuP/vQvmRsW6xpltg==', NULL, NULL, 1);
INSERT INTO `user` VALUES (25, 24, '司空坚', '15612468468571', '广东佛山', '1702040164', '8b29ca525f162c694374b6c6f44e2f32', 'PCIGK+EFANwloSEC55mUqA==', NULL, NULL, 1);
INSERT INTO `user` VALUES (26, 25, '胡杰枝', '15634587911', '广东佛山', '1702040165', 'c1e28822240ace01495dafb39e57a277', 'QNHk+HWioq+pwHsbTzBNfQ==', NULL, NULL, 1);
INSERT INTO `user` VALUES (27, 29, '孔姬宜', '15634584891', '广东佛山', '1702040167', '5957ccfe4ba35dd294bb72d42f7347d3', 'OI9WwpH2/gRRqNb0vL+jSA==', NULL, NULL, 1);
INSERT INTO `user` VALUES (28, 35, '李华', '18072327181', '广西南宁市', '1702030023', '6dc4803aa0ccdf34dee1a0456970d1f0', '+ga+ZXZjbTdY4dxLywxY/Q==', NULL, NULL, 1);
INSERT INTO `user` VALUES (29, 36, '杨仁', '18073718291', '广西南宁市', '1003', 'c6c666a08cce858f4dd504c77c076414', 'yVx9I12RcIA91MOJIbV6OA==', NULL, NULL, 1);
INSERT INTO `user` VALUES (30, 37, '杨德', '18073718291', '广西南宁市', '1004', '627dc114f5e3b1a43e9b41bb6d6fafc4', 'Lae0UXQ7D6vHXLgs08qTag==', NULL, NULL, 1);
INSERT INTO `user` VALUES (31, 38, '杨标', '18073718291', '广西南宁市', '1005', '9860082f50090bb0e8fafa6700bd1fa7', 'xNNv561yIqrqLZFBhZ1K/Q==', NULL, NULL, 1);
INSERT INTO `user` VALUES (32, 40, '杨志', '18073718291', '广西南宁市', '1006', '2152a1e261e65aa8290ae16efd13a2e5', 'P7JLjZUDch90xxDDMb1znQ==', NULL, NULL, 1);
INSERT INTO `user` VALUES (33, 42, '李三', '18078321232', '广西南宁市', '1007', '164e867bd082abdb7d358aaf36ad689d', 'sQWDzE92d/LMrVoKxG+8dQ==', NULL, NULL, 1);
INSERT INTO `user` VALUES (34, 43, '梁生', '15677561359', '南宁', '1008', 'bf69227b0eb6de56f77d1528deb75fb5', 'nB/dzAiaoWsW7gFiIT6z/w==', NULL, NULL, 1);
INSERT INTO `user` VALUES (35, 44, '梁彬', '18074611224', '广西南宁市', '1009', 'a085764de7921bd7632f7a89dbf74f1e', '1Fj/63cHM57a+C+cat7F7Q==', NULL, NULL, 1);
INSERT INTO `user` VALUES (36, 45, '洋大人', '180780609777', '', '1702040031', '51567e4905edabc8a81594f75e75729c', 'cIgIkasE9Jkowg7LijkqBw==', 'http://111.230.141.100:8080/uploadFiles/emp/20191118232927ffd2f67b59e977c5bbb9ce1ddd2796ca.jpg', NULL, 1);
INSERT INTO `user` VALUES (37, 46, '杨志标', '19080856215', '西乡塘', '191126153209', '889042a702cbaa1937a2f44e5734e1b8', '1wfrBHDxu7atgTu2uBd1qA==', '', NULL, 1);
INSERT INTO `user` VALUES (43, 55, '代沟高', '19080856215', '西乡塘', '19193615731', '86beb4aebb09ac351e7283d3b035f5af', '7Z0Ef0c2G9EiWVaHL6GuHA==', NULL, NULL, 1);
INSERT INTO `user` VALUES (44, 56, '带哥', '180780609777', '', '19193615737', '6eedccd701e98916a1364296b4dd4c1f', 'tDsJisw9iMXOOCsg1RP4qg==', NULL, NULL, 1);
INSERT INTO `user` VALUES (45, 57, '测试一下', '18078069974', '', '19193801995', 'fbfebd311f2cde8d458ef513197bebef', 'XpmehaGI/h7SbedC6BO0ow==', 'ok', NULL, 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色id',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `rid` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uid+rid`(`uid`, `rid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (46, 2, 2);
INSERT INTO `user_role` VALUES (47, 2, 125);
INSERT INTO `user_role` VALUES (44, 17, 2);
INSERT INTO `user_role` VALUES (45, 18, 2);
INSERT INTO `user_role` VALUES (48, 19, 2);
INSERT INTO `user_role` VALUES (81, 20, 2);
INSERT INTO `user_role` VALUES (82, 20, 3);
INSERT INTO `user_role` VALUES (50, 21, 2);
INSERT INTO `user_role` VALUES (51, 22, 2);
INSERT INTO `user_role` VALUES (52, 23, 2);
INSERT INTO `user_role` VALUES (53, 24, 2);
INSERT INTO `user_role` VALUES (54, 25, 2);
INSERT INTO `user_role` VALUES (55, 26, 2);
INSERT INTO `user_role` VALUES (56, 27, 2);
INSERT INTO `user_role` VALUES (57, 28, 2);
INSERT INTO `user_role` VALUES (83, 29, 2);
INSERT INTO `user_role` VALUES (84, 29, 4);
INSERT INTO `user_role` VALUES (59, 30, 2);
INSERT INTO `user_role` VALUES (60, 31, 2);
INSERT INTO `user_role` VALUES (61, 32, 2);
INSERT INTO `user_role` VALUES (63, 33, 2);
INSERT INTO `user_role` VALUES (64, 33, 4);
INSERT INTO `user_role` VALUES (66, 34, 2);
INSERT INTO `user_role` VALUES (67, 34, 4);
INSERT INTO `user_role` VALUES (69, 35, 2);
INSERT INTO `user_role` VALUES (70, 35, 4);
INSERT INTO `user_role` VALUES (71, 36, 2);
INSERT INTO `user_role` VALUES (72, 37, 2);
INSERT INTO `user_role` VALUES (78, 43, 2);
INSERT INTO `user_role` VALUES (79, 44, 2);
INSERT INTO `user_role` VALUES (80, 45, 2);

-- ----------------------------
-- Procedure structure for addEmp
-- ----------------------------
DROP PROCEDURE IF EXISTS `addEmp`;
delimiter ;;
CREATE PROCEDURE `addEmp`(in workId BIGINT,in name varchar(20),in sex varchar(20),in nationId integer,in birthday date,IN politiclId integer,IN wedlock char(20),IN nativePlace varchar(20),IN idCard varchar(20),IN email varchar(20),IN phone varchar(20),IN address varchar(20),IN departmentId integer,IN jobLevelId integer,IN positionId integer,IN titopDegree char(20),IN specialty varchar(20),IN school varchar(20),IN beginDate date,IN workState char(20),IN conversionTime date,IN quitTime date,IN beginContract date,IN endContract date,IN picture varchar(255),IN baseSalary double, IN password varchar(255), IN salt varchar(255))
begin
	declare eid int;
	declare uid int;
	insert into employee set workId=workId, name=name,sex=sex,nationId=nationId, birthday=birthday,
      politiclId=politiclId,wedlock=wedlock,nativePlace=nativePlace,
      idCard=idCard, email=email,  phone=phone,
       address=address,  departmentId=departmentId,  jobLevelId=jobLevelId, positionId=positionId,
       titopDegree=titopDegree,  specialty=specialty,  school=school,
       beginDate=beginDate,  workState=workState,  conversionTime=conversionTime,
       quitTime=quitTime,  beginContract=beginContract,  endContract=endContract,
       picture=picture,  baseSalary=baseSalary;
	#select row_count() into result;
	select last_insert_id() into eid;
	insert into employee_salary set eid=eid,baseSalary=baseSalary,meritSalary=baseSalary+500;
	insert into integral set eid=eid, integral=100;
	insert into user set eid=eid, name=name, phone=phone, address=address, username=workId,
      password=password, salt=salt, userface=picture, remark=null, enabled=1;
			
	select last_insert_id() into uid;
	insert into user_role set uid=uid, rid=2;
	
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for insertUser
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertUser`;
delimiter ;;
CREATE PROCEDURE `insertUser`(IN name varchar(20),IN phone varchar(20),IN address varchar(50),IN username varchar(255),IN password varchar(255),IN salt varchar(255), IN userface varchar(255),IN remark varchar(255), IN enabled TINYINT(1), OUT outid int)
BEGIN

	INSERT into user set name=name, phone=phone, address=address, username= username, password=password, salt=salt, userface=userface, remark=remark, enabled=enabled;

	set outid = last_insert_id();
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for updateEmpSalary
-- ----------------------------
DROP PROCEDURE IF EXISTS `updateEmpSalary`;
delimiter ;;
CREATE PROCEDURE `updateEmpSalary`(IN inBaseSalary double,IN inMeritSalary double,IN inId integer,IN inRemark varchar(255),IN indate date)
BEGIN
	declare befBaseSalary double;
	declare befMeritSalary double;
	declare tmpeid int;

	select baseSalary,meritSalary,eid into befBaseSalary,befMeritSalary,tmpeid from employee_salary where id=inId;
	
	INSERT into salary_log set eid=tmpeid, befBaseSalary=befBaseSalary, befMeritSalary=befMeritSalary, afterBaseSalary=inBaseSalary, afterMeritSalary= inMeritSalary, remark=inRemark,date=indate;
	UPDATE employee_salary set baseSalary=inBaseSalary,meritSalary=inMeritSalary  where id=inId;
	UPDATE employee set baseSalary=inBaseSalary where id=tmpeid;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table rp
-- ----------------------------
DROP TRIGGER IF EXISTS `insertRp`;
delimiter ;;
CREATE TRIGGER `insertRp` AFTER INSERT ON `rp` FOR EACH ROW begin

	declare p int;
	SELECT integral into p from integral where eid=new.eid;

	IF new.type = '惩罚' THEN
		UPDATE integral set integral=p-new.point where eid=new.eid;
	ELSE
		UPDATE integral set integral=p+new.point where eid=new.eid;
	END IF;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
