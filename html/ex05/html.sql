use htmldb;
create table users (
	userid varchar(20) not null primary key,
    username varchar(100) not null,
    email varchar(100)
);

desc users;

create table posts (
	id int auto_increment not null primary key,
    userid varchar(100) not null,
    title varchar(1000) not null,
    body varchar(2000),
    foreign key(userid) references users(userid)
);

desc posts;

insert into users(userid, username, email)
values('blue', 'pass', 'blue@icia.com');
insert into users(userid, username, email)
values('red', 'pass', 'red@icia.com');
insert into users(userid, username, email)
values('green', 'pass', 'green@icia.com');

select * from users;

insert into posts(title, body, userid)
values('새로운 도전을 시작한 당신을 위한 멘토 프로그램!', 'IT 취업 도전하겠다고 결심했지만, 여전히 취업은 막막하고 고민되시죠?', 'blue');
insert into posts(title, body, userid)
values('코로나 19를 이겨내자!!', '코로나 19를 이겨내고, 수강생 여러분의 취업을 위해 인천일보아카데미가 돕겠습니다.', 'red');
insert into posts(title, body, userid)
values('비대면 수업 실시 안내', '안녕하세요 인천일보 아카데미입니다. 코로나 사회적 거리두기 2.5단계 시행에 따라 9월 3~4일 양일간 집합교육을 실시간 비대면 교육으로 전환하여 실시합니다.', 'green');

insert into posts(title, body, userid)
select title,body,userid from posts;



select count(*) from posts;

select * from posts
where title like '코로나%'
order by id desc
limit 6, 5;
