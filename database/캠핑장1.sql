select * from tbl_camp;
select * from tbl_facility;
select * from tbl_type;
select * from tbl_camp_facility order by code;

drop view view_facility;

create view view_facility as (
select c.*, fname
from tbl_camp_facility c, tbl_facility f
where c.fcode = f.fcode
);

select * from view_facility;
select * from tbl_camp_type;

create view view_type as (
select c.*, tname
from tbl_camp_type c, tbl_type t
where c.tcode = t.tcode
);

select * from view_type;

drop view view_type;