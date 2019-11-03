/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : hr

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 03/11/2019 15:58:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for assessment
-- ----------------------------
DROP TABLE IF EXISTS `assessment`;
CREATE TABLE `assessment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考核id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考核名称',
  `remarks` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '说明',
  `state` enum('未开始','已开始','已结束') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '未开始' COMMENT '考核状态',
  `beginDate` date NULL DEFAULT NULL COMMENT '开始日期',
  `endDate` date NULL DEFAULT NULL COMMENT '结束日期',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `depNum` int(10) NOT NULL COMMENT '部门编号',
  `orderNum` int(10) NULL DEFAULT NULL COMMENT '排序编号',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门名称',
  `parentId` int(11) NOT NULL COMMENT '父亲节点id',
  `depPath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `depNum`(`depNum`) USING BTREE COMMENT '部门编号唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, 1, NULL, '股东会', -1, NULL);
INSERT INTO `department` VALUES (2, 2, 1, '董事会', 1, NULL);
INSERT INTO `department` VALUES (3, 3, 2, '测试部门01', 2, NULL);
INSERT INTO `department` VALUES (4, 4, 3, '测试部门02', 2, NULL);
INSERT INTO `department` VALUES (5, 5, 1, '测试部门03', 1, NULL);
INSERT INTO `department` VALUES (6, 6, NULL, '测试部门04', 1, NULL);
INSERT INTO `department` VALUES (7, 7, 1, '测试部门05', 1, NULL);
INSERT INTO `department` VALUES (8, 8, NULL, '弟弟会', 1, NULL);
INSERT INTO `department` VALUES (10, 9, NULL, '添加测试', 8, NULL);
INSERT INTO `department` VALUES (11, 10, NULL, '测试', 1, NULL);
INSERT INTO `department` VALUES (14, 60, NULL, '部门编号重复测试', 1, NULL);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `workId` bigint(11) NOT NULL COMMENT '员工工号',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工名称',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `nationId` int(11) NOT NULL COMMENT '民族',
  `birthday` date NOT NULL COMMENT '出生日期',
  `politiclId` int(11) NOT NULL COMMENT '政治面貌',
  `wedlock` enum('已婚','未婚','离异') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '婚姻状态',
  `nativePlace` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '籍贯',
  `idCard` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系地址',
  `departmentId` int(11) NULL DEFAULT NULL COMMENT '所属部门',
  `positionId` int(11) NULL DEFAULT NULL COMMENT '职位id',
  `jobLevelId` int(11) NULL DEFAULT NULL COMMENT '职称id',
  `titopDegree` enum('博士','硕士','本科','大专','高中','初中','小学','其他') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最高学历',
  `specialty` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属专业',
  `school` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '毕业院校',
  `beginDate` date NOT NULL COMMENT '入职日期',
  `workState` enum('在职','离职') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '在职状态',
  `conversionTime` date NULL DEFAULT NULL COMMENT '转正日期',
  `quitTime` date NULL DEFAULT NULL COMMENT '离职日期',
  `beginContract` date NOT NULL COMMENT '合同起始日期',
  `endContract` date NOT NULL COMMENT '合同结束日期',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工照片',
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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 1702040031, '宇哥', '男', 1, '2017-01-01', 1, '未婚', '广西梧州', '450403111694941516', '120645616@qq.com', '1808708080808', '水电费水电费水电费十多个水电费', 2, 1, 1, '硕士', '软件开发', '弟中弟学院', '2019-10-21', '在职', '2019-10-21', '2019-10-25', '2019-10-21', '2019-10-21', NULL, NULL);
INSERT INTO `employee` VALUES (2, 1702040032, '宇哥', '男', 1, '2017-01-01', 1, '未婚', '广西梧州', '450403111694941516', '120645616@qq.com', '1808708080808', '水电费水电费水电费十多个水电费', 2, 1, 1, '硕士', '软件开发', '弟中弟学院', '2019-10-21', '在职', '2019-10-21', '2019-10-25', '2019-10-21', '2019-10-21', NULL, NULL);
INSERT INTO `employee` VALUES (3, 1702040033, '宇哥', '男', 1, '2017-01-01', 1, '未婚', '广西梧州', '450403111694941516', '120645616@qq.com', '1808708080808', '水电费水电费水电费十多个水电费', 2, 1, 1, '硕士', '软件开发', '弟中弟学院', '2019-10-21', '在职', '2019-10-21', '2019-10-25', '2019-10-21', '2019-10-21', NULL, NULL);
INSERT INTO `employee` VALUES (4, 1702040034, '宇哥', '男', 1, '2017-01-01', 1, '未婚', '广西梧州', '450403111694941516', '120645616@qq.com', '1808708080808', '水电费水电费水电费十多个水电费', 2, 1, 1, '硕士', '软件开发', '弟中弟学院', '2019-10-21', '在职', '2019-10-21', '2019-10-25', '2019-10-21', '2019-10-21', NULL, NULL);
INSERT INTO `employee` VALUES (5, 1702040035, '宇哥', '男', 1, '2017-01-01', 1, '未婚', '广西梧州', '450403111694941516', '120645616@qq.com', '1808708080808', '水电费水电费水电费十多个水电费', 2, 1, 1, '硕士', '软件开发', '弟中弟学院', '2019-10-21', '在职', '2019-10-21', '2019-10-25', '2019-10-21', '2019-10-21', NULL, NULL);
INSERT INTO `employee` VALUES (6, 1702040036, '宇哥', '男', 1, '2017-01-01', 1, '未婚', '广西梧州', '450403111694941516', '120645616@qq.com', '1808708080808', '水电费水电费水电费十多个水电费', 2, 1, 1, '硕士', '软件开发', '弟中弟学院', '2019-10-21', '在职', '2019-10-21', '2019-10-25', '2019-10-21', '2019-10-21', NULL, NULL);
INSERT INTO `employee` VALUES (7, 1702040037, '宇哥', '男', 1, '2017-01-01', 1, '未婚', '广西梧州', '450403111694941516', '120645616@qq.com', '1808708080808', '水电费水电费水电费十多个水电费', 2, 1, 1, '硕士', '软件开发', '弟中弟学院', '2019-10-21', '在职', '2019-10-21', '2019-10-25', '2019-10-21', '2019-10-21', NULL, NULL);
INSERT INTO `employee` VALUES (8, 1702040038, '宇哥', '男', 1, '2017-01-01', 1, '未婚', '广西梧州', '450403111694941516', '120645616@qq.com', '1808708080808', '水电费水电费水电费十多个水电费', 2, 1, 1, '硕士', '软件开发', '弟中弟学院', '2019-10-21', '在职', '2019-10-21', '2019-10-25', '2019-10-21', '2019-10-21', NULL, NULL);
INSERT INTO `employee` VALUES (9, 1702040039, '宇哥', '男', 1, '2017-01-01', 1, '未婚', '广西梧州', '450403111694941516', '120645616@qq.com', '1808708080808', '水电费水电费水电费十多个水电费', 2, 1, 1, '硕士', '软件开发', '弟中弟学院', '2019-10-21', '在职', '2019-10-21', '2019-10-25', '2019-10-21', '2019-10-21', NULL, NULL);
INSERT INTO `employee` VALUES (10, 1702040040, '宇哥', '男', 1, '2017-01-01', 1, '未婚', '广西梧州', '450403111694941516', '120645616@qq.com', '1808708080808', '水电费水电费水电费十多个水电费', 2, 1, 1, '硕士', '软件开发', '弟中弟学院', '2019-10-21', '在职', '2019-10-21', '2019-10-25', '2019-10-21', '2019-10-21', NULL, NULL);

-- ----------------------------
-- Table structure for employee_assessment
-- ----------------------------
DROP TABLE IF EXISTS `employee_assessment`;
CREATE TABLE `employee_assessment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工考核表id',
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `aid` int(11) NULL DEFAULT NULL COMMENT '考核项目id',
  `data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '附件',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '说明',
  `result` enum('未通过','已通过','修改后提交') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审批结果',
  `isCheck` int(1) NULL DEFAULT NULL COMMENT '是否查阅',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `emp_id`(`eid`) USING BTREE,
  INDEX `ass_id`(`aid`) USING BTREE,
  CONSTRAINT `employee_assessment_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employee_assessment_ibfk_2` FOREIGN KEY (`aid`) REFERENCES `assessment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_salary
-- ----------------------------
INSERT INTO `employee_salary` VALUES (1, 1, 2000.00, 2500.00);

-- ----------------------------
-- Table structure for employee_transfer
-- ----------------------------
DROP TABLE IF EXISTS `employee_transfer`;
CREATE TABLE `employee_transfer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '调动日志id',
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `befDep` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调动前部门',
  `afterDep` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调动后部门',
  `befJobLvl` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调动前职称',
  `afterJobLvl` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调动后职称',
  `befPos` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调动前职位',
  `afterPos` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调动后职位',
  `Date` date NULL DEFAULT NULL COMMENT '调动日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `eid`(`eid`) USING BTREE,
  CONSTRAINT `employee_transfer_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for integral
-- ----------------------------
DROP TABLE IF EXISTS `integral`;
CREATE TABLE `integral`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NULL DEFAULT NULL,
  `integral` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `eid`(`eid`) USING BTREE,
  CONSTRAINT `integral_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_level
-- ----------------------------
DROP TABLE IF EXISTS `job_level`;
CREATE TABLE `job_level`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '职称id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职称名称',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_level
-- ----------------------------
INSERT INTO `job_level` VALUES (1, '初级工程师', '2019-10-24 10:56:58', NULL);
INSERT INTO `job_level` VALUES (2, '中级工程师', '2019-10-24 11:15:19', NULL);
INSERT INTO `job_level` VALUES (3, '高级工程师', '2019-10-24 11:15:21', NULL);
INSERT INTO `job_level` VALUES (4, '骨灰级工程师', '2019-10-24 11:15:24', NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '后台接口路径',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件名称',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `iconCls` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `keepAlive` tinyint(1) NULL DEFAULT NULL,
  `requireAuth` tinyint(1) NULL DEFAULT NULL COMMENT '是否需要登录',
  `parentId` tinyint(2) NULL DEFAULT NULL COMMENT '父节点id',
  `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '/', NULL, NULL, '所有', NULL, NULL, NULL, NULL, 1);
INSERT INTO `menu` VALUES (2, '/', '/home', 'Home', '员工档案', NULL, NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (3, '/', '/home', 'Home', '人事管理', 'ios-contacts\r\n\r\nios-contacts', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (4, '/', '/home', 'Home', '综合统计', 'ios-pie', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (5, '/', '/home', 'Home', '系统设置', 'ios-settings', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (6, '/employee/**', '/employee', 'Employee', '员工档案', 'ios-folder', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (7, '/personnel/salary', '/personnel/salary', 'Salary', '工资管理', NULL, NULL, 1, 3, 1);
INSERT INTO `menu` VALUES (8, '/personnel/assessment', '/personnel/assessment', 'Assessment', '考核管理', NULL, NULL, 1, 3, 1);
INSERT INTO `menu` VALUES (9, '/personnel/rp', '/personnel/rp', 'Rp', '奖惩管理', NULL, NULL, 1, 3, 1);
INSERT INTO `menu` VALUES (10, '/statistics/total', '/statistics/total', 'TotalStatistics', '综合信息统计', NULL, NULL, 1, 4, 1);
INSERT INTO `menu` VALUES (11, '/statistics/integral', '/statistics/integral', 'IntegralStatistics', '员工积分统计', NULL, NULL, 1, 4, 1);
INSERT INTO `menu` VALUES (12, '/system/basic', '/system/basic', 'Basic', '基础设置', NULL, NULL, 1, 5, 1);
INSERT INTO `menu` VALUES (13, '/system/role', '/system/role', 'Role', '角色管理', NULL, NULL, 1, 5, 1);
INSERT INTO `menu` VALUES (14, '/system/user', '/system/user', 'User', '用户管理', NULL, NULL, 1, 5, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for nation
-- ----------------------------
DROP TABLE IF EXISTS `nation`;
CREATE TABLE `nation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '民族id',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '民族名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
-- Table structure for political_status
-- ----------------------------
DROP TABLE IF EXISTS `political_status`;
CREATE TABLE `political_status`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '政治面貌id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '政治面貌名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职位名称',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '系统管理员', '2019-10-22 17:31:30', NULL);
INSERT INTO `role` VALUES (2, '部门经理', '2019-10-22 17:31:33', NULL);
INSERT INTO `role` VALUES (3, '人事专员', '2019-10-22 17:31:35', NULL);
INSERT INTO `role` VALUES (4, '招聘主管', '2019-10-22 17:31:38', NULL);
INSERT INTO `role` VALUES (5, '培训主管', '2019-10-22 17:31:40', NULL);
INSERT INTO `role` VALUES (6, '薪酬绩效主管', '2019-10-22 17:31:42', NULL);
INSERT INTO `role` VALUES (16, '新弟', '2019-10-23 11:30:38', '嘻嘻');

-- ----------------------------
-- Table structure for rp
-- ----------------------------
DROP TABLE IF EXISTS `rp`;
CREATE TABLE `rp`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '奖惩id',
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `date` date NULL DEFAULT NULL COMMENT '日期',
  `type` enum('奖励','惩罚') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '奖惩分类',
  `reason` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '奖惩理由',
  `point` int(11) NULL DEFAULT NULL COMMENT '奖惩分数',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_13`(`eid`) USING BTREE,
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `address` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系地址',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '解码盐',
  `userface` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `enable` tinyint(1) NULL DEFAULT NULL COMMENT '是否禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '宇哥', NULL, NULL, 'admin', 'a7d59dfc5332749cb801f86a24f5f590', 'e5ykFiNwShfCXvBRPr3wXg==', NULL, NULL, 1);
INSERT INTO `user` VALUES (2, '测试用户', NULL, NULL, 'test01', 'a7d59dfc5332749cb801f86a24f5f590', 'e5ykFiNwShfCXvBRPr3wXg==', NULL, NULL, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
