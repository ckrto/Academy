desc tbl_type;
alter table tbl_type add tdate date default sysdate;

select * from tbl_type;

update tbl_type set tdate = '2020/03/23'
where tcode = 'T10'

update tbl_type set tdate = '2020/06/30'
where tcode = 'T20'

update tbl_type set tdate = '2020/09/25'
where tcode = 'T30'

desc tbl_score;
alter table tbl_score drop column sdate;
select * from tbl_score;

create view view_student as (
select s.*, dname 
from tbl_student s, tbl_dept d
where s.dcode = d.dcode
);

drop view view_score;

select * from view_student order by sname;

insert into tbl_score
value('900', 'T10', 100);

select * from tbl_type;
select * from tbl_score where sno = '100';

create view view_score as (
select s.*, tname, tdate
from tbl_score s, tbl_type t
where s.tcode = t.tcode
);

select * from view_score;

desc tbl_score;

select count(*)
from tbl_score
where sno = '100' and tcode = 'T10';


commit;