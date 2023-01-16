drop table tbl_score;
drop table tbl_type;
drop table tbl_student;
drop table tbl_dept;

create table tbl_dept (
  dcode char(3) not null primary key,
  dname varchar(100) not null
);
desc tbl_dept;

create table tbl_type (
  tcode char(3) not null primary key,
  tname varchar(100) not null
);

create table tbl_student (
  sno char(3) not null primary key,
  sname varchar(20) not null,
  dcode char(3),
  foreign key(dcode) REFERENCES tbl_dept(dcode)
);

create table tbl_score (
  sno char(3) not null,
  tcode char(3) not null,
  sdate date default sysdate,
  grade int default 0,
  primary key(sno, tcode),
  FOREIGN key(sno) REFERENCES tbl_student(sno),
  FOREIGN key(tcode) REFERENCES tbl_type(tcode)
);