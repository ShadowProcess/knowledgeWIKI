-- ----------------------------
-- 建立用户表
-- ----------------------------
DROP TABLE IF EXISTS book_user;
CREATE TABLE book_user (
  id int NOT NULL AUTO_INCREMENT,
  user_name varchar(40) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id)
)

-- ----------------------------
-- Records of book_user
-- ----------------------------
INSERT INTO book_user VALUES ('1', '123@qq.com', '123');
INSERT INTO book_user VALUES ('2', '123@mail.com', '456');