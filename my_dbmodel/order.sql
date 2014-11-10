-- 주문정보
DROP TABLE IF EXISTS ORDERS RESTRICT;

-- 제품정보
DROP TABLE IF EXISTS PRODUCTS RESTRICT;

-- 제품사진
DROP TABLE IF EXISTS PROD_PHOTS RESTRICT;

-- 제조사
DROP TABLE IF EXISTS MAKERS RESTRICT;

-- 주문자정보
DROP TABLE IF EXISTS MEMBERS RESTRICT;

-- 주소정보
DROP TABLE IF EXISTS ADDRS RESTRICT;

-- 주문정보
CREATE TABLE ORDERS (
  ONO   INTEGER     NOT NULL COMMENT '주문번호', -- 주문번호
  QTY   INTEGER     NOT NULL COMMENT '주문수량', -- 주문수량
  ODATE DATE        NOT NULL COMMENT '주문일', -- 주문일
  PNO   INTEGER     NOT NULL COMMENT '제품번호', -- 제품번호
  UID   VARCHAR(20) NOT NULL COMMENT '아이디' -- 아이디
)
COMMENT '주문정보';

-- 주문정보
ALTER TABLE ORDERS
  ADD CONSTRAINT PK_ORDERS -- 주문정보 기본키
    PRIMARY KEY (
      ONO -- 주문번호
    );

ALTER TABLE ORDERS
  MODIFY COLUMN ONO INTEGER NOT NULL AUTO_INCREMENT COMMENT '주문번호';

-- 제품정보
CREATE TABLE PRODUCTS (
  PNO   INTEGER      NOT NULL COMMENT '제품번호', -- 제품번호
  PNAME VARCHAR(255) NOT NULL COMMENT '제품명', -- 제품명
  QTY   INTEGER      NOT NULL COMMENT '잔여수량', -- 잔여수량
  MKNO  INTEGER      NULL     COMMENT '제조사번호' -- 제조사번호
)
COMMENT '제품정보';

-- 제품정보
ALTER TABLE PRODUCTS
  ADD CONSTRAINT PK_PRODUCTS -- 제품정보 기본키
    PRIMARY KEY (
      PNO -- 제품번호
    );

-- 제품정보 인덱스
CREATE INDEX IX_PRODUCTS
  ON PRODUCTS( -- 제품정보
    PNAME ASC -- 제품명
  );

ALTER TABLE PRODUCTS
  MODIFY COLUMN PNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '제품번호';

-- 제품사진
CREATE TABLE PROD_PHOTS (
  PPNO INTEGER      NOT NULL COMMENT '제품사진번호', -- 제품사진번호
  PNO  INTEGER      NULL     COMMENT '제품번호', -- 제품번호
  URL  VARCHAR(255) NULL     COMMENT '제품사진경로' -- 제품사진경로
)
COMMENT '제품사진';

-- 제품사진
ALTER TABLE PROD_PHOTS
  ADD CONSTRAINT PK_PROD_PHOTS -- 제품사진 기본키
    PRIMARY KEY (
      PPNO -- 제품사진번호
    );

ALTER TABLE PROD_PHOTS
  MODIFY COLUMN PPNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '제품사진번호';

-- 제조사
CREATE TABLE MAKERS (
  MKNO   INTEGER      NOT NULL COMMENT '제조사번호', -- 제조사번호
  MKNAME VARCHAR(80)  NOT NULL COMMENT '제조사', -- 제조사
  HOME   VARCHAR(255) NULL     COMMENT '제조사홈페이지', -- 제조사홈페이지
  TEL    VARCHAR(30)  NULL     COMMENT '제조사전화', -- 제조사전화
  FAX    VARCHAR(30)  NULL     COMMENT '제조사팩스' -- 제조사팩스
)
COMMENT '제조사';

-- 제조사
ALTER TABLE MAKERS
  ADD CONSTRAINT PK_MAKERS -- 제조사 기본키
    PRIMARY KEY (
      MKNO -- 제조사번호
    );

ALTER TABLE MAKERS
  MODIFY COLUMN MKNO INTEGER NOT NULL AUTO_INCREMENT COMMENT '제조사번호';

-- 주문자정보
CREATE TABLE MEMBERS (
  UID      VARCHAR(20)  NOT NULL COMMENT '아이디', -- 아이디
  PWD      VARCHAR(20)  NOT NULL COMMENT '암호', -- 암호
  EMAIL    VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  UNAME    VARCHAR(80)  NOT NULL COMMENT '주문자명', -- 주문자명
  TEL      VARCHAR(30)  NOT NULL COMMENT '전화', -- 전화
  FAX      VARCHAR(30)  NULL     COMMENT '팩스', -- 팩스
  DET_ADDR VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
  PHOT     VARCHAR(255) NULL     COMMENT '주문자사진', -- 주문자사진
  ANO      INTEGER      NULL     COMMENT '주소번호' -- 주소번호
)
COMMENT '주문자정보';

-- 주문자정보
ALTER TABLE MEMBERS
  ADD CONSTRAINT PK_MEMBERS -- 주문자정보 기본키
    PRIMARY KEY (
      UID -- 아이디
    );

-- 주문자정보 유니크 인덱스
CREATE UNIQUE INDEX UIX_MEMBERS
  ON MEMBERS ( -- 주문자정보
    EMAIL ASC -- 이메일
  );

-- 주문자정보 인덱스
CREATE INDEX IX_MEMBERS
  ON MEMBERS( -- 주문자정보
    UNAME ASC -- 주문자명
  );

-- 주소정보
CREATE TABLE ADDRS (
  ANO      INTEGER      NOT NULL COMMENT '주소번호', -- 주소번호
  POSTNO   VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
  BAS_ADDR VARCHAR(255) NOT NULL COMMENT '기본주소' -- 기본주소
)
COMMENT '주소정보';

-- 주소정보
ALTER TABLE ADDRS
  ADD CONSTRAINT PK_ADDRS -- 주소정보 기본키
    PRIMARY KEY (
      ANO -- 주소번호
    );

ALTER TABLE ADDRS
  MODIFY COLUMN ANO INTEGER NOT NULL AUTO_INCREMENT COMMENT '주소번호';

-- 주문정보
ALTER TABLE ORDERS
  ADD CONSTRAINT FK_PRODUCTS_TO_ORDERS -- 제품정보 -> 주문정보
    FOREIGN KEY (
      PNO -- 제품번호
    )
    REFERENCES PRODUCTS ( -- 제품정보
      PNO -- 제품번호
    );

-- 주문정보
ALTER TABLE ORDERS
  ADD CONSTRAINT FK_MEMBERS_TO_ORDERS -- 주문자정보 -> 주문정보
    FOREIGN KEY (
      UID -- 아이디
    )
    REFERENCES MEMBERS ( -- 주문자정보
      UID -- 아이디
    );

-- 제품정보
ALTER TABLE PRODUCTS
  ADD CONSTRAINT FK_MAKERS_TO_PRODUCTS -- 제조사 -> 제품정보
    FOREIGN KEY (
      MKNO -- 제조사번호
    )
    REFERENCES MAKERS ( -- 제조사
      MKNO -- 제조사번호
    );

-- 제품사진
ALTER TABLE PROD_PHOTS
  ADD CONSTRAINT FK_PRODUCTS_TO_PROD_PHOTS -- 제품정보 -> 제품사진
    FOREIGN KEY (
      PNO -- 제품번호
    )
    REFERENCES PRODUCTS ( -- 제품정보
      PNO -- 제품번호
    );

-- 주문자정보
ALTER TABLE MEMBERS
  ADD CONSTRAINT FK_ADDRS_TO_MEMBERS -- 주소정보 -> 주문자정보
    FOREIGN KEY (
      ANO -- 주소번호
    )
    REFERENCES ADDRS ( -- 주소정보
      ANO -- 주소번호
    );
    
    

    
    
    
    
    
    
    