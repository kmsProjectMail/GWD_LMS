-- DDL

-- CHATBOARD
CREATE TABLE CHATBOARD(
SEQ NUMBER,
CHATMEMBER VARCHAR2(1000), 
CONTENT VARCHAR2(4000), 
RECENTDATE DATE
);

CREATE SEQUENCE CHATBOARD_SEQ START WITH 1 INCREMENT BY 1;

-- CHATUSER
CREATE TABLE CHATUSER(
SEQ NUMBER ,
ID VARCHAR2(4000),
PW VARCHAR2(100)
);

ALTER TABLE CHATUSER ADD CONSTRAINT CHATUSER_PK PRIMARY KEY(ID);

-- 친구목록
CREATE TABLE FRIENDLIST(
	SEQ NUMBER,
	ID VARCHAR2(20),
	FRIEND_ID VARCHAR2(20)
);

CREATE SEQUENCE FRIENDLIST_SEQ START WITH 1 INCREMENT BY 1;

ALTER TABLE FRIENDLIST ADD CONSTRAINT FRIEND_FK FOREIGN KEY (ID) REFERENCES CHATUSER (ID);

--------------------------------CHATUSER-------------------------------------

-- 로그인
SELECT SEQ, ID FROM CHATUSER WHERE UPPER(ID) = UPPER('STUDENT01') AND PW='STUDENT01';

-- 사용자 추가
INSERT INTO CHATUSER
(SEQ, ID,pw)
VALUES(1, 'STUDENT01', 'STUDENT01');

INSERT INTO CHATUSER
(SEQ, ID,pw)
VALUES(1, 'STUDENT02', 'STUDENT02');

INSERT INTO CHATUSER
(SEQ, ID,pw)
VALUES(2, 'STUDENT03', 'STUDENT03');

INSERT INTO CHATUSER
(SEQ, ID,pw)
VALUES(5, 'user05', 'user05');

------------------------------- CHAT BOARD ---------------------------------

-- 해당 채팅방 존재여부 조회
SELECT SEQ, CHATMEMBER, CONTENT, RECENTDATE
		FROM CHATBOARD
		WHERE CHATMEMBER = 'STUDENT01,STUDENT02';

-- 채팅방 생성
INSERT INTO CHATBOARD(SEQ, CHATMEMBER, CONTENT, RECENTDATE) 
			VALUES(CHATBOARD_SEQ.NEXTVAL, 'STUDENT01,STUDENT02', '내용', SYSDATE);

-- 채팅방 대화내용 업데이트
UPDATE CHATBOARD
		SET CONTENT='안녕하세요 수정 테스트 입니다.', RECENTDATE=SYSDATE
		WHERE CHATMEMBER= 'STUDENT01,STUDENT02';
			
-- 사용자 채팅목록 조회
SELECT CHATMEMBER
		FROM CHATBOARD
		WHERE SUBSTR(CHATMEMBER, 1, INSTR(CHATMEMBER, ',', 1, 1)-1) = 'STUDENT01'
		OR SUBSTR(CHATMEMBER, INSTR(CHATMEMBER, ',', 1, 1)+1) = 'STUDENT01';
	
-- 채팅방 삭제
DELETE FROM CHATBOARD c 
	WHERE CHATMEMBER = 'STUDENT01,STUDENT02';
		
-----------------------------FRIEND LIST -----------------------------------------
-- 친구 목록 조회(친구 이름 포함)
SELECT f.seq, f.ID , f.FRIEND_ID , r.name 
FROM (
SELECT f.seq, f.id, f.FRIEND_ID
FROM FRIENDLIST f WHERE id = 'STUDENT01') f 
JOIN (SELECT ID, name FROM (
      SELECT ID, ENABLED, NAME FROM STUDENT 
      UNION
      SELECT ID,ENABLED, cen_name AS name FROM CENTER c 
      UNION
      SELECT ID,ENABLED, TRPRCHAP AS name FROM TRAINST_MEMBER tm 
) WHERE ENABLED = 'T')  r ON  r.id = FRIEND_ID;

-- 친구 추가
INSERT INTO FRIENDLIST(SEQ, ID, FRIEND_ID)
SELECT FRIENDLIST_SEQ.NEXTVAL, 'STUDENT01', 'STUDENT02' FROM DUAL
WHERE NOT EXISTS(SELECT f.ID, f.FRIEND_ID FROM FRIENDLIST f WHERE f.ID = 'STUDENT01' AND f.FRIEND_ID='STUDENT02');

-- 친구 목록 조회
SELECT f.FRIEND_ID 
	FROM MEMBERAUTH m ,FRIENDLIST f
	WHERE m.ID = f.ID 
	AND m.ID = 'STUDENT01';
	
-- 친구 삭제
DELETE FROM FRIENDLIST f 
	WHERE f.ID = 'STUDENT01'
	AND f.FRIEND_ID = 'STUDENT03';
	
-- 전체 사용자 조회
SELECT ID, name FROM (
SELECT ID, ENABLED, NAME FROM STUDENT 
UNION
SELECT ID,ENABLED, cen_name AS name FROM CENTER c 
UNION
SELECT ID,ENABLED, TRPRCHAP AS name FROM TRAINST_MEMBER tm 
) WHERE ENABLED = 'T'

-- 아이디에 해당하는 사용자 이름 조회
SELECT ID, name FROM (
SELECT ID, ENABLED, NAME FROM STUDENT 
UNION
SELECT ID,ENABLED, cen_name AS name FROM CENTER c 
UNION
SELECT ID,ENABLED, TRPRCHAP AS name FROM TRAINST_MEMBER tm 
) WHERE ENABLED = 'T' AND ID = 'STUDENT01'

