#대량 데이터를 입력

#상황 : 테이블이 준비되어 있는 상황에서 260만건의 데이터를 넣어야 한다.

#         DATA는 조현권|잘생김|28살||천재|솔로|미혼|| 과 같이 '|'로 구분되어 있다. 
#         위와 같은 데이터가 265만건
#         파일은 txt형식으로 저장되어있음.
#         INSERT문으로 파싱하기에는 매우 많은 시간이 소요됨


#해결책
LOAD DATA LOCAL INFILE '/Users/Administrator/GMK/data.txt' INTO TABLE db_gm_ec.tbl_juso FIELDS TERMINATED BY '|' ENCLOSED BY '"' LINES TERMINATED BY '\n';

#구분자 '|'로 구분되어 정해진 컬럼으로 삽입되며, 행간은 \n으로 교체되어 다음으로 넘어간다

#실제 실행한 결과 260만건의 INSERT가 5분 내로 완료됨 (컴퓨터 스택, 램 8기가 보통 CPU)


#결론


#나 잘남 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ