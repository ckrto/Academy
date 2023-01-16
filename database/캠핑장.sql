drop table tbl_camp;
drop table tbl_facility;
drop table tbl_type;
drop table tbl_camp_facility;
drop table tbl_camp_type;

/*캠핑장 테이블 생성*/
create table tbl_camp (
  code char(4) not null primary key,
  cname varchar(200) not null,
  address varchar (200) not null
);

/*시설물 테이블 생성*/
create table tbl_facility (
  fcode char(3) not null primary key,
  fname varchar(200) not null
);

/*유형 테이블 생성*/
create table tbl_type (
   tcode char(3) not null primary key,
   tname varchar(200) not null
);

/*캠핑장 시설물 테이블 생성*/
create table tbl_camp_facility (
  code char(4) not null,
  fcode char(3) not null,
  primary key (code,fcode),
  foreign key (code) references tbl_camp (code),
  foreign key (fcode) references tbl_facility (fcode)
);

/*캠핑장 유형 테이블 생성*/
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
values('c100', '도토리', '강원도');
insert into tbl_camp
values('c200', '솔밭', '인천');
insert into tbl_camp
values('c300', '자연', '경기도');

select * from tbl_camp;

insert into tbl_facility
values('f10', '전기');
insert into tbl_facility
values('f20', '수도');
insert into tbl_facility
values('f30', '화장실');
insert into tbl_facility
values('f40', '와이파이');

select * from tbl_facility;

insert into tbl_type
values('t10', '오토캠핑');
insert into tbl_type
values('t20', '글램핑');
insert into tbl_type
values('t30', '카라반');

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