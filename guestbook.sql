CREATE TABLE guestbook(
no NUMBER PRIMARY KEY,
name varchar2(80) not null,
password varchar2(20) not null,
content varchar2(2000) not null,
reg_date date not null
);

CREATE SEQUENCE seq_guestbook_no
INCREMENT BY 1
START WITH 1
nocache;

SELECT * FROM GUESTBOOK;

DROP TABLE GUESTBOOK;
