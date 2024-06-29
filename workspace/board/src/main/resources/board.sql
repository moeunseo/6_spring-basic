DROP SEQUENCE SEQ_BOARD;
DROP SEQUENCE SEQ_FILE;
DROP SEQUENCE SEQ_USERS;
DROP SEQUENCE SEQ_COMMENT;
DROP TABLE TBL_USERS CASCADE CONSTRAINTS;
DROP TABLE TBL_BOARD CASCADE CONSTRAINTS;
DROP TABLE TBL_FILE;
DROP TABLE TBL_COMMENT;

CREATE SEQUENCE seq_board
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE SEQUENCE seq_file
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE SEQUENCE seq_users
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE SEQUENCE seq_comment
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE TABLE TBL_USERS
(
    NAME        VARCHAR2(100),
    PROFILE_PIC VARCHAR2(255),
    PROVIDER    VARCHAR2(50),
    PROVIDER_ID VARCHAR2(255) PRIMARY KEY,
    CREATE_AT   DATE,
    UPDATE_AT   DATE
);

CREATE TABLE TBL_BOARD
(
    BOARD_ID            NUMBER PRIMARY KEY,
    BOARD_TITLE         VARCHAR2(255)    NOT NULL,
    BOARD_CONTENT       VARCHAR2(1000)   NOT NULL,
    BOARD_VIEWS         NUMBER DEFAULT 0 NOT NULL,
    BOARD_REGISTER_DATE DATE   DEFAULT SYSDATE,
    BOARD_UPDATE_DATE   DATE   DEFAULT SYSDATE,
    PROVIDER_ID         VARCHAR2(255),
    CONSTRAINT FK_BOARD_TO_USER FOREIGN KEY (PROVIDER_ID)
        REFERENCES TBL_USERS (PROVIDER_ID) ON DELETE CASCADE
);

CREATE TABLE TBL_FILE
(
    FILE_ID            NUMBER PRIMARY KEY,
    ORIGINAL_FILE_NAME VARCHAR2(1000),
    STORED_FILE_NAME   VARCHAR2(1000),
    FILE_SIZE          NUMBER,
    UPLOAD_TIME        DATE DEFAULT SYSDATE,
    BOARD_ID           NUMBER NOT NULL,
    CONSTRAINT FK_FILE_TO_BOARD FOREIGN KEY (BOARD_ID)
        REFERENCES TBL_BOARD (BOARD_ID) ON DELETE CASCADE
);

CREATE TABLE TBL_COMMENT
(
    COMMENT_ID            NUMBER PRIMARY KEY,
    BOARD_ID              NUMBER NOT NULL,
    PROVIDER_ID           VARCHAR2(255) NOT NULL,
    COMMENT_CONTENT       VARCHAR2(1000) NOT NULL,
    COMMENT_REGISTER_DATE DATE,
    COMMENT_UPDATE_DATE   DATE,
    CONSTRAINT FK_COMMENT_TO_BOARD FOREIGN KEY (BOARD_ID)
        REFERENCES TBL_BOARD (BOARD_ID) ON DELETE CASCADE,
    CONSTRAINT FK_COMMENT_TO_USER FOREIGN KEY (PROVIDER_ID)
        REFERENCES TBL_USERS (PROVIDER_ID) ON DELETE CASCADE
);

SELECT *
FROM tbl_file;
SELECT *
FROM TBL_BOARD;
select *
from tbl_users;
SELECT *
FROM TBL_COMMENT;

insert into TBL_BOARD
(BOARD_ID, BOARD_TITLE, BOARD_CONTENT, BOARD_VIEWS, BOARD_REGISTER_DATE, BOARD_UPDATE_DATE,
 PROVIDER_ID)
values (seq_board.nextval, '제목', '내용', 1, sysdate, sysdate, '3525143309');

select *from TBL_COMMENT;

select u.name, c.*
from tbl_users u join TBL_COMMENT c
                      on u.PROVIDER_ID = c.PROVIDER_ID
                          and BOARD_ID = 24
order by COMMENT_UPDATE_DATE desc;

update tbl_comment
set comment_content = '이거를 넣어볼게', comment_update_date = sysdate
where comment_id = 1
