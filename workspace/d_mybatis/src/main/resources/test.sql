create table TBL_BIRD
(
    id     NUMBER
        constraint pk_bird
            primary key,
    name   NVARCHAR2(100),
    age    NUMBER,
    gender NVARCHAR2(100)
);

CREATE SEQUENCE SEQ_BIRD
START WITH 1
INCREMENT BY 1
NOCACHE;

select *from TBL_BIRD
where id=1;