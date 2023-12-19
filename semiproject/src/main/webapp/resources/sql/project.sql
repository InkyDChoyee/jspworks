CREATE TABLE member (
   id               VARCHAR2(20)  PRIMARY KEY,
   passwd           VARCHAR2(25)   NOT NULL,
   name             VARCHAR2(30),
   email            VARCHAR2(30),
   phone            VARCHAR2(20)  NOT NULL,
   joindate         TIMESTAMP     DEFAULT SYSTIMESTAMP,
   zip_code         VARCHAR2(10),
   address          VARCHAR2(40),
   detailaddress    VARCHAR2(40)
);

CREATE TABLE purchasedetail (
   receipt      VARCHAR2(50)    PRIMARY KEY,
   product      VARCHAR2(40),
   orderdate    TIMESTAMP       DEFAULT SYSTIMESTAMP,
   price        NUMBER(25),
   id           VARCHAR2(20)    NOT NULL,
   orderno      NUMBER          NOT NULL,
   FOREIGN KEY(id) REFERENCES member,
   FOREIGN KEY(orderno) REFERENCES orders
);

CREATE TABLE product (
   pno          NUMBER          PRIMARY KEY,
   pname        VARCHAR2(40)    NOT NULL,
   price        NUMBER          NOT NULL,
   p_score      NUMBER,
   sal_num      NUMBER,
   pcontent     VARCHAR2(200),
   category		VARCHAR2(40)
);
CREATE SEQUENCE seq_pno NOCACHE;

CREATE TABLE qa (
   qno      NUMBER          PRIMARY KEY,
   qtitle   VARCHAR2(50)    NOT NULL,
   qname    VARCHAR2(20)    NOT NULL,
   qfilename   VARCHAR2(100),
   qdate    TIMESTAMP,
   qhit     NUMBER,
   id       VARCHAR2(30)    NOT NULL,
   FOREIGN KEY(id) REFERENCES member
);

CREATE TABLE review (
   rno       NUMBER             PRIMARY KEY,
   rtype     VARCHAR2(20)       NOT NULL,
   rtitle    VARCHAR2(50)       NOT NULL,
   rdate     TIMESTAMP,
   rcontent  VARCHAR2(200),
   rfilename   VARCHAR2(100),
   rrate     NUMBER,
   likes      NUMBER,
   receipt   VARCHAR2(50)       NOT NULL,
   FOREIGN KEY(receipt) REFERENCES purchasedetail
);

CREATE TABLE orders (
   orderno          NUMBER          PRIMARY KEY,
   orderdate        TIMESTAMP       DEFAULT SYSTIMESTAMP,
   orderprice       NUMBER,               
   cardcompany      VARCHAR2(30),   
   count            NUMBER,         
   orderstatus      VARCHAR2(60),    
   pno              NUMBER          NOT NULL,
   id               VARCHAR2(20)    NOT NULL,
   FOREIGN KEY(id)  REFERENCES member,
   FOREIGN KEY(pno) REFERENCES product
);

CREATE TABLE notice (
   nno      NUMBER          PRIMARY KEY,
   ntitle   VARCHAR2(50)    NOT NULL,
   nname    VARCHAR2(20)    NOT NULL,
   nfilename   VARCHAR2(100),
   ndate    TIMESTAMP,
   nhit     NUMBER
);