drop table tbl_camp;
drop table tbl_facility;
drop table tbl_type;
drop table tbl_camp_facility;
drop table tbl_camp_type;

/*ķ���� ���̺� ����*/
create table tbl_camp (
  code char(4) not null primary key,
  cname varchar(200) not null,
  address varchar (200) not null
);

/*�ü��� ���̺� ����*/
create table tbl_facility (
  fcode char(3) not null primary key,
  fname varchar(200) not null
);

/*���� ���̺� ����*/
create table tbl_type (
   tcode char(3) not null primary key,
   tname varchar(200) not null
);

/*ķ���� �ü��� ���̺� ����*/
create table tbl_camp_facility (
  code char(4) not null,
  fcode char(3) not null,
  primary key (code,fcode),
  foreign key (code) references tbl_camp (code),
  foreign key (fcode) references tbl_facility (fcode)
);

/*ķ���� ���� ���̺� ����*/
create table tbl_camp_type (
  code char(4) not null,
  tcode char(3) not null,
  qnt int default 0,
  primary key (code,tcode),
  FOREIGN key (code) REFERENCES tbl_camp (code),
  FOREIGN key (tcode) REFERENCES tbl_type (tcode)
);

commit;

insert into tbl_camp
values('c100', '���丮', '������');
insert into tbl_camp
values('c200', '�ֹ�', '��õ');
insert into tbl_camp
values('c300', '�ڿ�', '��⵵');

select * from tbl_camp;

insert into tbl_facility
values('f10', '����');
insert into tbl_facility
values('f20', '����');
insert into tbl_facility
values('f30', 'ȭ���');
insert into tbl_facility
values('f40', '��������');

select * from tbl_facility;

insert into tbl_type
values('t10', '����ķ��');
insert into tbl_type
values('t20', '�۷���');
insert into tbl_type
values('t30', 'ī���');

select * from tbl_type;

insert into tbl_camp_facility(code, fcode)
values('c100', 'f10');
insert into tbl_camp_facility(code, fcode)
values('c100', 'f20');
insert into tbl_camp_facility(code, fcode)
values('c100', 'f30');
insert into tbl_camp_facility(code, fcode)
values('c200', 'f10');
insert into tbl_camp_facility(code, fcode)
values('c200', 'f20');
insert into tbl_camp_facility(code, fcode)
values('c300', 'f30');
insert into tbl_camp_facility(code, fcode)
values('c300', 'f40');

select * from tbl_camp_facility;

insert into tbl_camp_type(code, tcode, qnt)
values('c100', 't10', 5);
insert into tbl_camp_type(code, tcode, qnt)
values('c100', 't20', 10);
insert into tbl_camp_type(code, tcode, qnt)
values('c200', 't10', 12);
insert into tbl_camp_type(code, tcode, qnt)
values('c200', 't20', 5);
insert into tbl_camp_type(code, tcode, qnt)
values('c200', 't30', 20);
insert into tbl_camp_type(code, tcode, qnt)
values('c300', 't10', 22);
insert into tbl_camp_type(code, tcode, qnt)
values('c300', 't30', 10);

select * from tbl_camp_type;

create view view_facility as (
  select c.*, fname
  from tbl_facility f, tbl_camp_facility c
  where f.fcode = c.fcode
);

select * from view_facility;

create view view_type as (
  select c.*, tname
  from tbl_camp_type c, tbl_type t
  where c.tcode = t.tcode
);

select * from view_type;

select * from view_facility where code='c100';

select * from tbl_facility;

select max(fcode) mcode from tbl_facility;

commit;