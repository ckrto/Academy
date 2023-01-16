drop table tbl_dept;
drop table tbl_student;
drop table tbl_score;
drop table tbl_type;

create table tbl_dept (
  dcode char(3) not null PRIMARY key,
  dname varchar(100) not null
);
desc tbl_dept;