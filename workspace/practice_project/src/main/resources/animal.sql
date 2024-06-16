drop table user_join;

CREATE TABLE user_join (
      id NUMBER PRIMARY KEY,
      userid VARCHAR2(100) NOT NULL unique,
      email VARCHAR2(50),
      password VARCHAR2(1000)
);

-- seq_join

drop sequence seq_join;

CREATE SEQUENCE seq_join
    START WITH 1
    INCREMENT BY 1
    NOCACHE;


alter table tbl_user add name varchar2(500);