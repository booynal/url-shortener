-- 创建库
CREATE SCHEMA IF NOT EXISTS url;

-- 创建url映射表
CREATE TABLE IF NOT EXISTS url.url_mapping
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  long_url VARCHAR(2000) NOT NULL,
  create_time DATETIME NOT NULL,
  expired_time DATETIME
);

-- 创建url统计分析表
CREATE TABLE IF NOT EXISTS url.url_analyze
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  url_id BIGINT NOT NULL,
  click_time DATETIME,
  click_ip VARCHAR(128) NOT NULL,
  ext_info VARCHAR(500)
);
