DROP TABLE IF EXISTS TENANT;

CREATE TABLE TENANT
(
    ID             BIGINT                                   NOT NULL COMMENT 'ID',
    NAME           VARCHAR(30) COLLATE utf8mb4_unicode_ci   NULL COMMENT '테넌트명',
    PRIMARY KEY (ID)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '테넌트';
