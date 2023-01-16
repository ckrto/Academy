drop table tbl_account;
drop table tbl_statement;

/*accountInfo table*/
create table tbl_accountInfo (
  accountTypeNo char(3) not null primary key,
  accountTypeName varchar(100) not null,
  accountType varchar(100) not null
);

/*passwordType talbe*/
create table tbl_passwordType (
  passwordTypeNo char(3) not null primary key,
  passwordQuestion varchar(500) not null
);

/*account table*/
create table tbl_account (
  accountNo char(9) not null primary key,
  accountName varchar(100) not null,
  balance int default 1000,
  accountTypeNo char(3) not null,
  accountDate date default sysdate,
  foreign key (accountTypeNo) references tbl_accountInfo(accountTypeNo)
);

/*password talbe*/
create table tbl_password (
  accountNo char(9) not null,
  password char(4) not null primary key,
  passwordTypeNo char(3) not null,
  passwordAnswer varchar(500) not null,
  foreign key (accountNo) references tbl_account(accountNo),
  foreign key (passwordTypeNo) references tbl_passwordType(passwordTypeNo)
);


/*������ ���� tbl_accountInfo*/
insert into tbl_accountInfo values ('A01','���� ����','����');
insert into tbl_accountInfo values ('A02','���� �����','�����');

/*������ ���� tbl_passwordType*/
insert into tbl_passwordType values ('Q01','�����ϴ� ������?');
insert into tbl_passwordType values ('Q02','���� ��� ������?');
insert into tbl_passwordType values ('Q03','�ʵ��б� �̸���?');

/*������ ���� tbl_account*/
insert into tbl_account values ('620-00000','�谡��',1000,'A02','2022-05-16');
insert into tbl_account values ('620-00001','�谡��',20000,'A01','2022-06-17');
insert into tbl_account values ('620-00002','��ٶ�',1000,'A02','2022-07-10');
insert into tbl_account values ('620-00003','�踶��',1000,'A02','2022-03-01');

/*������ ���� tbl_passwordType*/
insert into tbl_password values ('620-00000','1111','Q01','�̼���');
insert into tbl_password values ('620-00001','1113','Q02','��õ');
insert into tbl_password values ('620-00002','1110','Q03','�����ʵ��б�');
insert into tbl_password values ('620-00003','1112','Q02','����');

/*�� ���̺� ����*/
select * from tbl_accountInfo;
select * from tbl_passwordType;
select * from tbl_account;
select * from tbl_password;

/*���� ���� ��ȸ*/
select * from tbl_account where accountNo='?';

/*���¹�ȣ ����*/
select max(accountNo) from tbl_account;

/*�����*/
update tbl_account set balance=5000 where accountNo='?';

/*�Է��� ���¹�ȣ�� ��й�ȣ ����*/
select *from TBL_PASSWORD where ACCOUNTNO='620-00000';

/*���� ��ȸ ���� view*/
create view view_all as(
  select a.*,p.password
  from tbl_account a, tbl_password p
  where a.accountNo = p.accountNo
);

select *from VIEW_ALL;

/*Ư�� ���¹�ȣ ������ ��ȸ*/
select *from VIEW_ALL where accountNo='620-00003';

/*accountTypeNo�� ��ǰ ���� �˱�*/
select * from tbl_accountInfo where accountTypeNo=?;

/*�ŷ�Ÿ�� ���̺�(tbl_tradeType) ����*/
create table tbl_tradeType(
  tradeTypeNo char(3) not null primary key,
  tradeType varchar(100) not null
);

/*�ŷ����� ���̺� (tbl_trade) ����*/
create table tbl_trade(
  accountNo char(9) not null,
  tradeTypeNo char(3) not null,
  tradeBalance int not null,
  tradeDate date default sysdate,
  primary key(accountNo, tradeBalance,tradeDate),
  foreign key (accountNo) references tbl_account(accountNo),
  foreign key (tradeTypeNo) references tbl_tradeType(tradeTypeNo)
);
drop table tbl_trade;

/*���̺� Ȯ��*/
desc TBL_TRADETYPE;
desc TBL_TRADE;

/*���� ���µ��� �ܾ� ����*/
update tbl_account 
set balance=40000
where accountNo='620-00000';

update tbl_account 
set balance=1500000
where accountNo='620-00001';

update tbl_account 
set balance=46700
where accountNo='620-00002';

update tbl_account 
set balance=29200
where accountNo='620-00003';

/*�ܾ� Ȯ��*/
select * from tbl_account;

/*�ŷ�Ÿ�� ���̺�(tbl_tradeType) ������*/
insert into tbl_tradeType(tradeTypeNo,tradeType) values('T01','�Ա�');
insert into tbl_tradeType(tradeTypeNo,tradeType) values('T02','���');

/*Ȯ��*/
select * from tbl_tradeType;

/*�ŷ����� ���̺� (tbl_trade) ������*/
/*620-00000*/
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00000','T01',10000,'2022-05-16');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00000','T01',25000,'2022-05-22');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00000','T02',13000,'2022-05-23');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00000','T01',18000,'2022-05-30');

/*620-00001*/
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00001','T01',300000,'2022-06-17');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00001','T01',300000,'2022-07-17');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00001','T01',300000,'2022-08-17');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00001','T01',300000,'2022-09-17');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00001','T01',300000,'2022-10-17');

/*620-00002*/
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00002','T01',50000,'2022-07-10');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00002','T02',8000,'2022-07-13');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00002','T02',7300,'2022-07-20');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00002','T01',12000,'2022-07-22');

/*620-00003*/
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00003','T01',80000,'2022-03-01');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00003','T02',19800,'2022-04-03');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00003','T02',22100,'2022-04-11');
insert into tbl_trade(accountNo,tradeTypeNo,tradeBalance,tradeDate)
values('620-00003','T02',8900,'2022-04-13');

/*Ȯ��*/
select * from tbl_trade;

select * from tbl_account;

create view view_tradeInfo as(
  select ta.*, tp.password
  from tbl_account ta, tbl_password tp
  where ta.accountno = tp.accountno
);

select * from view_tradeInfo;

create view view_trade as(
  select trd.accountno, trdt.tradeType, trd.tradebalance, trd.tradedate
  from tbl_trade trd, tbl_tradeType trdt
  where trd.tradeTypeNo = trdt.tradeTypeNo
);

create view tradeview as(
  select a.*,b.tradeType
  from tbl_trade a, tbl_tradeType b
  where a.tradeTypeNo=b.tradeTypeNo
);

select * from tradeview where accountNo='620-00001';

drop view view_trade;
select * from view_trade;

update tbl_account set balance = balance + 500 where accountNo = '620-00004';

insert into tbl_trade(accountNo, tradeTypeNo, tradeBalance) values('620-00004', 'T01', 500);

select * from view_tradeInfo where accountno = '620-00004';
select * from tbl_account where accountno = '620-00004';
select * from tbl_trade where accountno = '620-00004';
delete from tbl_trade where tradebalance = 2000 and accountno = '620-00004';

commit;

commit;
commit;