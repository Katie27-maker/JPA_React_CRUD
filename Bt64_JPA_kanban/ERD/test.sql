-- 필요한 테스트 쿼리
show tables;

select * from t8_post;

insert into t8_authority(name) values("ROLE_MEMBER"),("ADMIN_MENBER");
insert into t8_user_authorities(authorities_id, t8_user_id) values(1,1);


SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'db907'
  AND TABLE_NAME LIKE 't8_%'
;

SELECT * FROM t8_authority;
SELECT * FROM t8_user;
SELECT * FROM t8_user_authorities;  # jpa에서 m/n관계 테이블 생성
SELECT * FROM t8_post ORDER BY id DESC;
SELECT * FROM t8_attachment ORDER BY id DESC;
SELECT * FROM t8_comment ORDER BY id DESC;


-- 테이블 삭제
DROP TABLE IF EXISTS t8_comment;
DROP TABLE IF EXISTS t8_attachment;
DROP TABLE IF EXISTS t8_post;
DROP TABLE IF EXISTS t8_user_authorities;
DROP TABLE IF EXISTS t8_authority;
DROP TABLE IF EXISTS t8_user;