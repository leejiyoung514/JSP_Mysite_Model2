create table users(
no number, --식별자
name varchar2(80),
email varchar2(100) unique not null, --바뀌면 문제가 생길소지가 있어서 unique
password varchar2(20) not null,
gender varchar2(10),
primary key(no)
)
--보통 테이블마다 sqeunce가 있음 숫자를 하나하나 셀 수 없어서
--번호표인데 이름이 seq_user_no  여기다 .nextvar하면 번호표가 하나씩 올라감 
create sequence seq_user_no  
increment by 1
start with 1
nocache;

insert into users values(
SEQ_USER_NO.NEXTVAL,
'이지영',
'leejiyoung514@naver.com',
'1234',
'male'
);

select * from users;