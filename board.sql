CREATE TABLE board(
no NUMBER,
title VARCHAR2(500),
content VARCHAR2(4000),
hit NUMBER,
reg_date DATE,
user_no NUMBER,
PRIMARY KEY(no),
CONSTRAINT c_board_fk FOREIGN KEY (user_no)
REFERENCES users(no)
);

CREATE SEQUENCE seq_board_no
INCREMENT BY 1
START WITH 1
NOCACHE;

INSERT INTO BOARD values(seq_board_no.nextval,'자바','웹페이지 만들기',1,sysdate,1);

select * from board;

select b.no,
       b.title,
       u.name,
       b.hit,
       b.reg_date,
       b.user_no
from board b, users u
where b.user_no = u.no
order by no desc;


select b.no, 
b.title, 
 u.name, 
b.hit, 
b.reg_date, 
b.user_no 
from board b, users u 
where b.user_no = u.no 
order by no desc;

SELECT title, content FROM board where no=1;

SELECT no, title, content, user_no FROM board where no=1;

SELECT B.rnum, B.no, B.title, B.name, B.hit, B.reg_date, B.user_no
FROM
   (SELECT rownum AS rnum, A.no, A.title, A.name, A.hit, A.reg_date, A.user_no
    FROM(SELECT b.no,
                b.title,
                u.name,
                b.hit,
                b.reg_date,
                b.user_no
         FROM board b, users u
         WHERE b.user_no = u.no
         ORDER BY NO DESC) A
    WHERE rownum <=5)B
WHERE B.rnum >=1;





