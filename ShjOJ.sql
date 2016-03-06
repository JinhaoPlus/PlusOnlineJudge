/*
 Navicat Premium Data Transfer

 Source Server         : conn
 Source Server Type    : MySQL
 Source Server Version : 50626
 Source Host           : localhost
 Source Database       : ShjOJ

 Target Server Type    : MySQL
 Target Server Version : 50626
 File Encoding         : utf-8

 Date: 03/06/2016 17:10:29 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tbl_ploj_problems`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ploj_problems`;
CREATE TABLE `tbl_ploj_problems` (
  `PROBLEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROBLEM_CATEGORY` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `PROBLEM_DIGEST` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `PROBLEM_CONTENT` varchar(1024) CHARACTER SET latin1 DEFAULT NULL,
  `PROBLEM_LANGUAGE` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`PROBLEM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbl_ploj_solution`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ploj_solution`;
CREATE TABLE `tbl_ploj_solution` (
  `solution_id` int(11) NOT NULL AUTO_INCREMENT,
  `problem_id` int(11) DEFAULT NULL,
  `code_submit` varchar(10240) CHARACTER SET latin1 DEFAULT NULL,
  `solution_coder_id` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `solution_language` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `solution_submit_time` datetime DEFAULT NULL,
  `solution_oj_result` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`solution_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbl_ploj_test_result`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ploj_test_result`;
CREATE TABLE `tbl_ploj_test_result` (
  `result_id` int(11) NOT NULL AUTO_INCREMENT,
  `problem_id` int(11) DEFAULT NULL,
  `test_id` int(11) DEFAULT NULL,
  `result` varchar(1024) DEFAULT NULL,
  `oj_result` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`result_id`)
) ENGINE=InnoDB AUTO_INCREMENT=265 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `tbl_ploj_tests`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ploj_tests`;
CREATE TABLE `tbl_ploj_tests` (
  `TESTID` int(11) NOT NULL AUTO_INCREMENT,
  `TEST_PROBLEM_ID` int(11) DEFAULT NULL,
  `TEST_INPUT` varchar(1024) CHARACTER SET latin1 DEFAULT NULL,
  `TEST_OUTPUT` varchar(1024) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`TESTID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbl_ploj_user`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ploj_user`;
CREATE TABLE `tbl_ploj_user` (
  `USERID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
