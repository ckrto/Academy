/*��� ���̺� ����*/
drop table tbl_score;
drop table tbl_student;
drop table tbl_type;
drop table tbl_dept;

/*�а� ���̺� ����*/
create table tbl_dept(
  dcode char(3) not null primary key,
  dname varchar(100) not null
);
desc tbl_dept;

/*�л� ���̺� ����*/
create table tbl_student(
  sno char(3) not null primary key,
  sname varchar(20) not null,
  dcode char(3) not null,
  FOREIGN key(dcode) REFERENCES tbl_dept(dcode)
);

/*���� ���̺� ����*/
create table tbl_type(
  tcode char(3) not null primary key,
  tname varchar(20) not null
);
desc tbl_type;

/*���� ���̺� ����*/
create table tbl_score(
  sno char(3) not null,
  tcode char(3) not null,
  sdate date default sysdate,
  grade int default 0,
  primary key(sno, tcode),
  FOREIGN key(sno) REFERENCES tbl_student(sno),
  FOREIGN key(tcode) REFERENCES tbl_type(tcode)
);

insert into tbl_dept
values('D10', '��ǻ����������');
insert into tbl_dept
values('D20', '���ڰ���');
insert into tbl_dept
values('D30', '�濵��');
select * from tbl_dept;

insert into tbl_student
values('100', 'ȫ�浿', 'D10');
insert into tbl_student
values('200', '��û��', 'D20');
insert into tbl_student
values('300', '������', 'D10');
select * from tbl_student;

insert into tbl_type
values('T10', '�߰�');
insert into tbl_type
values('T20', '�⸻');
insert into tbl_type
values('T30', '9������');

insert into tbl_score(sno, tcode, sdate, grade)
values('100', 'T10', '2020-03-23', 99);
insert into tbl_score(sno, tcode, sdate, grade)
values('200', 'T10', '2020-03-23', 88);
insert into tbl_score(sno, tcode, sdate, grade)
values('300', 'T10', '2020-03-23', 100);
insert into tbl_score(sno, tcode, sdate, grade)
values('100', 'T20', '2020-06-30', 85);
insert into tbl_score(sno, tcode, sdate, grade)
values('200', 'T20', '2020-06-30', 76);
insert into tbl_score(sno, tcode, sdate, grade)
values('300', 'T30', '2020-09-25', 69);
select * from tbl_score;

select s.*, dname
from tbl_student s, tbl_dept d
where s.dcode = d.dcode;

/* �й�, �л���, �����ڵ�, �����ڵ��, ������, ���� */
create view view_score as ( 
select g.*, s.sname, t.tname, d.dname
from tbl_score g, tbl_student s, tbl_type t, tbl_dept d
where g.sno = s.sno and g.tcode = t.tcode and s.dcode = d.dcode
);

select * from view_score order by sdate desc; 

commit;

select * from tbl_dept;

select max(dcode) mcode from tbl_dept;

update tbl_dept set dname = '�������ڰ��а�'
where dcode = 'D20';
