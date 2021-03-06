----DB에 과정이 존재하는지 검색
	SELECT TRPR_ID, TRPR_DEGR
		FROM TRPR_INFO
	WHERE TRPR_ID=#{trpr_id} AND TRPR_DEGR=#{trpr_degr}

--DB에 기관이 존재하는지 검색
	SELECT TRAINST_CST_ID
		FROM TRAINST_INFO
	WHERE TRAINST_CST_ID=#{trainst_cst_id}

--기관 DB입력
	INSERT INTO TRAINST_INFO(
 	TRAINST_CST_ID, P_FILE_NAME, FILE_PATH,  
 	INO_NM, ADDR1, ADDR2,  
 	TEL_NO, HP_ADDR, TORG_PAR_GRAD) 
 	VALUES( 
 	#{trainst_cst_id}, #{p_file_name}, #{file_path},  
 	#{ino_nm}, #{addr1}, #{addr2},  
 	#{tel_no}, #{hp_addr}, #{torg_par_grad})


--과정 DB입력
	INSERT INTO TRPR_INFO(
	TRPR_DEGR, ADDRESS, TRAINST_CST_ID,
	TRPR_ID, TRA_END_DATE, TRA_START_DATE,
	NCS_CD, TRPR_CHAP_TEL, TRPR_CHAP_EMAIL,
	TRPR_CHAP, TRTM, TRPR_NM,
	NCS_NM, EQMN_INFO_LIST, FACIL_INFO_LIST
	)
		VALUES(
		#{trpr_degr}, #{address}, #{trainst_cst_id},
		#{trpr_id}, #{tra_end_date}, #{tra_start_date},
		#{ncs_cd}, #{trpr_chap_tel}, #{trpr_chap_email},
		#{trpr_chap}, #{trtm}, #{trpr_nm},
		#{ncs_nm}, #{eqmn_info_list}, #{facil_info_list}
	)
--과정 DB List 업데이트
	UPDATE TRPR_INFO
	SET EQMN_INFO_LIST=#{eqmn_info_list}, FACIL_INFO_LIST=#{facil_info_list}
	WHERE TRPR_ID=#{trpr_id} AND TRPR_DEGR=#{trpr_degr}

--페이징: 전체 글 개수
SELECT COUNT(*)
	FROM (SELECT ti2.INO_NM, ti.TRPR_NM, ti.TRA_START_DATE, ti.TRA_END_DATE, ti.TRTM, ti.TRPR_DEGR, ti.TRPR_ID, ti2.TRAINST_CST_ID
		FROM TRPR_INFO ti , TRAINST_INFO ti2
			WHERE ti.TRAINST_CST_ID = ti2.TRAINST_CST_ID
					AND ti.ADDRESS LIKE #{address}||'%'
					AND ti.NCS_CD LIKE #{ncs_cd}||'%'
					AND 
					(LOWER(ti.TRPR_NM) LIKE LOWER('%'||#{trpr_nm}||'%') 
					OR LOWER(ti2.INO_NM) LIKE LOWER('%'||#{trpr_nm}||'%'))
					
--기관/과정 목록 조회 (교육기관명, 교육과정명, 교육시작일, 교육종료일, 교육 시간, 회차정보)
	SELECT INO_NM, TRPR_NM, TRA_START_DATE, TRA_END_DATE, TRTM, TRPR_DEGR, TRPR_ID, TRAINST_CST_ID
	FROM (SELECT ti2.INO_NM, ti.TRPR_NM, ti.TRA_START_DATE, 
			ti.TRA_END_DATE, ti.TRTM, ti.TRPR_DEGR, ti.TRPR_ID, ti2.TRAINST_CST_ID,
			ROW_NUMBER () OVER(ORDER BY ti.TRA_START_DATE) RN
			FROM TRPR_INFO ti , TRAINST_INFO ti2
				WHERE ti.TRAINST_CST_ID = ti2.TRAINST_CST_ID
		--			AND ti.ADDRESS LIKE '서울%'
		--				AND ti.NCS_CD LIKE '2%'
--						AND 
--						(LOWER(ti.TRPR_NM) LIKE LOWER('%java%') 
--						OR LOWER(ti2.INO_NM) LIKE LOWER('%java%'))
						AND ti.TRA_START_DATE BETWEEN '2021-01-14' AND '2021-03-19'
		)
--	WHERE (RN BETWEEN 1 AND 20) AND (TRA_START_DATE = TO_CHAR(SYSDATE,'YYYYMMDD'));
	WHERE (RN BETWEEN 1 AND 20);

---------------------	
	SELECT INO_NM, TRPR_NM, TRA_START_DATE, TRA_END_DATE, TRTM, TRPR_DEGR
	FROM (SELECT ti2.INO_NM, ti.TRPR_NM, ti.TRA_START_DATE, 
		ti.TRA_END_DATE, ti.TRTM, ti.TRPR_DEGR, 
		ROW_NUMBER () OVER(ORDER BY ti.TRA_START_DATE) RN
		FROM TRPR_INFO ti , TRAINST_INFO ti2
			WHERE ti.TRAINST_CST_ID = ti2.TRAINST_CST_ID
				AND ti.ADDRESS LIKE '서울%'
				AND ti.NCS_CD LIKE '2%'
				AND 
				(LOWER(ti.TRPR_NM) LIKE LOWER('%java%') 
				OR LOWER(ti2.INO_NM) LIKE LOWER('%java%'))
			)
	WHERE RN BETWEEN 1 AND 5

--기관/과정 목록 조회 (교육기관명, 교육과정명, 교육시작일, 교육종료일, 교육 시간, 회차정보)
--	SELECT ti2.INO_NM, ti.TRPR_NM, ti.TRA_START_DATE, ti.TRA_END_DATE, ti.TRTM, ti.TRPR_DEGR, ti.TRPR_ID, ti2.TRAINST_CST_ID
--		FROM TRPR_INFO ti , TRAINST_INFO ti2
--			WHERE ti.TRAINST_CST_ID = ti2.TRAINST_CST_ID
----					AND ti.ADDRESS LIKE '서울%'
----					AND ti.NCS_CD LIKE '2%'
--					AND 
--					(LOWER(ti.TRPR_NM) LIKE LOWER('%java%') 
--					OR LOWER(ti2.INO_NM) LIKE LOWER('%java%'))
--				ORDER BY TRA_START_DATE;

--과정 상세 조회
--교육기관 로고, 파일경로, 교육기관명, 교육과정명, 교육시작일, 교육종료일, 교육시간, 회차정보, NCS직종, 
	담당자 전화번호, 담당자 성명, 담당자 이메일, 담당자ID, 교육목표(교육과정개요), 교육교재, 교육강사,
	교육시설(시설면적, 시설수, 인원수, 장비명, 보유수량)
<select id="hrdDetailTrpr" parameterType="java.util.Map" resultType="HRD_View_Vo">
	SELECT ti.TRPR_NM, ti.TRA_START_DATE, ti.TRA_END_DATE, ti.TRTM, ti2.INO_NM,
		ti.TRPR_DEGR, ti.NCS_NM, ti.TRPR_OVERVIEW, ti.TRPR_BOOK, ti.TRPR_TEACHER,
		ti.TRPR_CHAP_TEL, ti.TRPR_CHAP, ti.TRPR_CHAP_EMAIL, ti.TRPR_CHAP_ID, ti2.P_FILE_NAME,
		ti2.FILE_PATH, ti.FACIL_INFO_LIST, ti.EQMN_INFO_LIST
	FROM TRPR_INFO ti, TRAINST_INFO ti2 
WHERE ti.TRAINST_CST_ID = ti2.TRAINST_CST_ID AND ti.TRPR_ID = #{trpr_id} AND ti.TRPR_DEGR = #{trpr_degr}
</select>


--기관 상세 조회
--교육기관 로고, 파일경로, 교육기관명, 주소(ADDR1, ADDR2), 전화번호, 홈페이지, 담당자 전화번호, 담당자 성명, 담당자 이메일, 담당자ID, 기관소개, 기관사진, 기관영상
	SELECT TI2.P_FILE_NAME, TI2.FILE_PATH, TI2.INO_NM, TI2.ADDR1, TI2.ADDR2, TI2.TEL_NO, TI2.HP_ADDR,
		TI.TRPR_CHAP_TEL, TI.TRPR_CHAP, TI.TRPR_CHAP_EMAIL, TI.TRPR_CHAP_ID, TI2.TRAINST_INTRO, TI2.TRAINST_PHOTO, TI2.TRAINST_VIDEO
		FROM TRPR_INFO TI, TRAINST_INFO TI2
		WHERE TI.TRAINST_CST_ID = TI2.TRAINST_CST_ID AND TI2.TRAINST_CST_ID = '500020014067' AND TI.TRPR_ID = 'AIG20200000280531' AND TI.TRPR_DEGR = '3';


--#################여기까지 작업중#################

--<교육기관 기능> 기관 정보 입력,수정 (기관소개, 기관사진, 기관영상)
<update id="trainstAddTrainst">
	UPDATE TRAINST_INFO
	SET TRAINST_INTRO = '안녕', TRAINST_PHOTO = '이건', TRAINST_VIDEO = '수정'
	WHERE TRAINST_CST_ID='500020028075'
</update>

--<교육기관 기능> 기관 추가 입력정보 초기화 (기관소개, 기관사진, 기관영상)
<update id="trainstDelTrainst">
	UPDATE TRAINST_INFO
	SET TRAINST_INTRO = NULL, TRAINST_PHOTO = NULL, TRAINST_VIDEO = NULL
	WHERE TRAINST_CST_ID='500020028075'
</update>

--<교육기관 기능> 과정 정보 입력,수정(교육과정 개요, 교육 교재, 교육 강사)
<update id="trainstAddTrpr">
	UPDATE TRPR_INFO
	SET TRPR_OVERVIEW='개요수정', TRPR_BOOK='교재수정', TRPR_TEACHER='강사수정'
	WHERE TRPR_ID='AIG20180000211278'
</update>

--<교육기관 기능> 과정 추가 입력정보 초기화 (교육과정 개요, 교육 교재, 교육 강사)
<update id="trainstDelTrpr">
	UPDATE TRPR_INFO
	SET TRPR_OVERVIEW=NULL, TRPR_BOOK=NULL, TRPR_TEACHER=NULL
	WHERE TRPR_ID='AIG20180000211278'
</update>


--과정 즐겨찾기 조회
<select id="trprBmkList">
	SELECT TRPR_ID 
		FROM TRPR_BMK
	WHERE USER_ID = 'STUDENT01'
</select>

--과정 즐겨찾기 최초 1회 추가
<insert id="trprBmkInsert">
	INSERT INTO TRPR_BMK
	(SEQ, USER_ID, TRPR_ID)
		VALUES (TRPR_BMK_SEQ.NEXTVAL, 'STUDENT01', '{"AIG20190000229569"}')
</insert>

--과정 즐겨찾기 최초 1회 이후 수정(즐겨찾기 리스트 추가, 삭제)
<update id="trprBmkUpdate">
	UPDATE TRPR_BMK
		SET TRPR_ID = '{"AIG20190000229569"}'
	WHERE USER_ID = 'STUDENT01'
</update>

--과정 즐겨찾기 완전삭제
<delete id="trprBmkRealDel">
	DELETE FROM TRPR_BMK
	WHERE USER_ID = 'STUDENT01' AND TRPR_ID = '{"AIG20190000229569"}'
</delete>

--기관 즐겨찾기 조회
<select id="trainstBmkList">
	SELECT TRAINST_CST_ID
		FROM TRAINST_BMK
	WHERE USER_ID = 'STUDENT01'
</select>

--기관 즐겨찾기 최초 1회 추가
<insert id="trainstBmkInsert">
	INSERT INTO TRAINST_BMK
	(SEQ, USER_ID, TRAINST_CST_ID)
		VALUES (TRAINST_BMK_SEQ.NEXTVAL, 'STUDENT01', '{"500020028075"}')
</insert>

--기관 즐겨찾기 최초 1회 이후 수정(즐겨찾기 리스트 추가, 삭제)
<update id="trainstBmkUpdate">
	UPDATE TRAINST_BMK
		SET TRAINST_CST_ID = '{"500020028075"}'
	WHERE USER_ID = 'STUDENT01'
</update>

--기관 즐겨찾기 완전삭제
<delete id="trainstBmkRealDel">
	DELETE FROM TRAINST_BMK
	WHERE USER_ID = 'STUDENT01' AND TRAINST_CST_ID = '500020028075'
</delete>

--기관에서 채팅 담당자를 지정/수정
<update id="trainstManagerSet">
	UPDATE TRPR_INFO
	SET TRPR_CHAP_ID ='goodee1234'
	WHERE TRPR_ID='AIG20180000217347'
</update>

--기관에서 채팅 담당자를 삭제
<update id="trainstManagerDel">
	UPDATE TRPR_INFO
	SET TRPR_CHAP_ID = NULL
	WHERE TRPR_ID='AIG20180000217347'
</update>




--주소 조회시 사용
<select id = "alltrainstinfo" parameterType="java.util.Map" resultType="HRD_Trainst_Info_Vo" >
SELECT TRAINST_CST_ID, INO_NM, ADDR1, ADDR2, TEL_NO, HP_ADDR, TRAINST_INTRO, TRAINST_PHOTO, TRAINST_VIDEO
FROM TRAINST_INFO
	<where>
		<if test = "addr1 != null">
			 ADDR1 LIKE '%' || #{addr1} ||'%' 
		</if>
		<if test = "ino_nm != null">
			 INO_NM LIKE '%' || #{ino_nm} ||'%' 
		</if>
	</where>
</select>


SELECT *
	FROM TRPR_INFO ti 
		WHERE TRPR_ID='AIG20180000217347';


--즐겨찾기 목록 조회
SELECT ti2.INO_NM, ti.TRPR_NM, ti.TRA_START_DATE, ti.TRA_END_DATE, ti.TRTM, ti.TRPR_DEGR, ti.TRPR_ID, ti2.TRAINST_CST_ID
	FROM TRPR_INFO ti , TRAINST_INFO ti2
		WHERE ti.TRAINST_CST_ID = ti2.TRAINST_CST_ID AND TRPR_ID = 'AIG20200000293430' AND TRPR_DEGR = '1'
