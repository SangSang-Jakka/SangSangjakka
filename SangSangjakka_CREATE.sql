--SELECT 'DROP TABLE "' || TABLE_NAME || '" CASCADE CONSTRAINTS;' FROM user_tables;
--
--DROP TABLE "TBLUSER" CASCADE CONSTRAINTS;
--DROP TABLE "TBLBLACKLIST" CASCADE CONSTRAINTS;
--DROP TABLE "TBLUSERCAT" CASCADE CONSTRAINTS;
--DROP TABLE "TBLUSERLOG" CASCADE CONSTRAINTS;
--DROP TABLE "TBLAGECAT" CASCADE CONSTRAINTS;
--DROP TABLE "TBLCHILDAGE" CASCADE CONSTRAINTS;
--DROP TABLE "TBLADMIN" CASCADE CONSTRAINTS;
--DROP TABLE "TBLNOTICE" CASCADE CONSTRAINTS;
--DROP TABLE "TBLNOTCIEFIX" CASCADE CONSTRAINTS;
--DROP TABLE "TBLADCAT" CASCADE CONSTRAINTS;
--DROP TABLE "TBLADLOG" CASCADE CONSTRAINTS;
--DROP TABLE "TBLSUGGESTION" CASCADE CONSTRAINTS;
--DROP TABLE "TBLSUGGESTIONANSWER" CASCADE CONSTRAINTS;
--DROP TABLE "TBLINFLOWCAT" CASCADE CONSTRAINTS;
--DROP TABLE "USERINFLOW" CASCADE CONSTRAINTS;
--DROP TABLE "TBLGENRE" CASCADE CONSTRAINTS;
--DROP TABLE "TBLBOARD" CASCADE CONSTRAINTS;
--DROP TABLE "TBLBOARDWHITELIST" CASCADE CONSTRAINTS;
--DROP TABLE "TBLBOARDCOMMENTS" CASCADE CONSTRAINTS;
--DROP TABLE "TBLBOARDCOMMENTWHITELIST" CASCADE CONSTRAINTS;
--DROP TABLE "TBLRECOMMENDAGE" CASCADE CONSTRAINTS;
--DROP TABLE "TBLBOOK" CASCADE CONSTRAINTS;
--DROP TABLE "TBLBOOKWHITELIST" CASCADE CONSTRAINTS;
--DROP TABLE "TBLGENREINFO" CASCADE CONSTRAINTS;
--DROP TABLE "TBLPAGE" CASCADE CONSTRAINTS;
--DROP TABLE "TBLSCRAP" CASCADE CONSTRAINTS;
--DROP TABLE "TBLBOOKSHARE" CASCADE CONSTRAINTS;
--DROP TABLE "TBLAWARD" CASCADE CONSTRAINTS;
--DROP TABLE "TBLUSERGENREPREFERENCE" CASCADE CONSTRAINTS;
--DROP TABLE "TBLREVIEW" CASCADE CONSTRAINTS;
--DROP TABLE "TBLREVIEWWHITELIST" CASCADE CONSTRAINTS;
--DROP TABLE "TBLLIKE" CASCADE CONSTRAINTS;
--
--commit;

-- 회원 테이블
create table tblUser(
    userSeq         number primary key,                                     -- 사용자 번호(PK)
    userId          varchar(20)  unique not null,                           -- 아이디(UQ)
    userPw          varchar2(20) not null,                                  -- 비밀번호
    userNick        varchar2(50) not null unique,                           -- 닉네임(UQ)
    userTel         varchar2(30) not null unique,                           -- 전화번호(UQ)
    userAddress     varchar2(500) not null,                                 -- 주소
    userEmail       varchar2(100) unique not null,                          -- 이메일(UQ)
    userLeftSsn     varchar2(20) not null,                                  -- 주민번호 앞자리
    userRightSsn     varchar2(20) not null,                                  -- 주민번호 뒷자리
    userState       varchar2(10) check (userState in ('y', 'n')) not null,  -- 활동
    userLv          number default 1 not null,                              -- 등급
    userRegdate     date default sysdate not null,                          -- 가입일
    limitStorage    number default 10737418240 not null                     -- 제한용량 (기본 10Gb, 10737418240byte)
);


-- 차단 계정 테이블
create table tblBlackList(
    userSeq number primary key references tblUser(userSeq)  -- 차단 사용자 번호(PK, FK)
);


-- 사용자 행동로그 카테고리
create table tblUserCat(
    userCatSeq     number not null primary key, -- 카테고리 번호(PK)
    userCatContent varchar2(200) not null       -- 내용
);

-- 사용자 행동 로그 테이블
create table tblUserLog(
    userLogSeq      number primary key,                                 -- 사용자 로그 번호(PK)
    userLogDate     date default sysdate not null,                      -- 날짜
    userSeq         number references tblUser(userSeq) not null,        -- 사용자 번호(FK)
    userLogContents varchar2(300) not null,                             -- 로그 내용
    userCatSeq      number references tblUserCat(userCatSeq) not null   -- 카테고리 번호(FK)
);

-- 나이 카테고리
create table tblAgeCat(
    ageCatSeq number primary key,    -- 카테고리 seq(PK)
    ageRange varchar2(30) not null   -- 나이대
);

-- 자녀 나이
create table tblChildAge(
    childAgeSeq number primary key,                             -- 자녀나이seq(PK)
    childBirth  varchar2(20) not null,                          -- 생년월일
    userSeq     number references tblUser(userSeq) not null,    -- 회원번호(FK)
    ageCatSeq   number references tblAgeCat(ageCatSeq) not null -- 카테고리seq(FK)
);

-- 관리자 테이블
create table tblAdmin(
    adId        varchar2(20) primary key,                       -- 아이디(PK)
    adPw        varchar2(20) default '0000' not null,           -- 비밀번호
    adName      varchar2(20) default '관리자' not null,         -- 이름
    adNick      varchar2(50) unique not null,                   -- 닉네임(UQ)
    adAddress   varchar2(500) not null,                         -- 주소
    adTel       varchar2(30) unique not null,                   -- 전화번호
    adLv        number default 2 not null                       -- 등급(2: 일반, 3: 루트)
);

-- 공지사항 게시판 테이블
create table tblNotice(
    noticeSeq       number primary key not null,                    -- 공지사항 번호(PK)
    noticeTitle     varchar2(200) not null,                         -- 제목
    noticeContents  varchar2(4000) not null,                        -- 내용
    noticeRegdate   date default sysdate not null,                  -- 작성날짜
    noticeCnt       number default 0 not null,                      -- 조회수
    adId            varchar2(20) references tblAdmin(adId) not null -- 관리자 아이디(FK)
);

-- 고정사항 고정글 테이블
create table tblNoticeFix(
    noticeSeq number primary key references tblNotice(noticeSeq)    -- 고정 공지사항 번호(PK, FK)
);

-- 관리자 행동 카테고리
create table tblAdCat(
    adCatSeq number primary key,                   -- 관리자 행동 카테고리 번호(PK)
    adCatContents varchar2(200) unique not null    -- 카테고리 내용(UQ)
);


-- 관리자 행동 로그 테이블
create table tblAdLog(
    adLogSeq number primary key,                            -- 관리자 로그 번호(PK)
    adLogDate date default sysdate not null,                -- 로그 날짜
    adId varchar2(20) references tblAdmin(adId) not null,   -- 관리자 아이디(FK)
    adLogContent varchar2(300) not null,                    -- 로그 내용
    adCatSeq number references tblAdCat(adCatSeq)           -- 관리자 행동 카테고리 번호(FK)
);

-- 건의사항 게시판 테이블
create table tblSuggestion(
    sgstSeq         number primary key,                                                     -- 건의사항 게시글 번호(PK)
    sgstTitle       varchar2(100) not null,                                                 -- 제목
    sgstContents    varchar2(2000) not null,                                                -- 내용
    sgstRegdate     date default sysdate not null,                                          -- 작성날짜
    sgstSecretYN    varchar2(1) default 'n' check (sgstSecretYN in ('y', 'n')) not null,    -- 비밀글 여부
    userSeq         number references tblUser(userSeq) not null,                            -- 사용자 번호(FK)
    sgstCnt         number default 0 not null                                               -- 건의사항 조회수
);

-- 건의사항 답변 테이블
create table tblSuggestionAnswer(
    answSeq     number primary key,                                     -- 건의사항 답변 번호(PK)
    adId        varchar2(20) references tblAdmin(adId) not null,        -- 관리자 아이디(FK)
    sgsSeq      number references tblSuggestion(sgstSeq) not null,      -- 부모글 번호(FK)
    sgstAnsw    varchar2(2000) not null,                                -- 답변 내용
    sgstRegdate date default sysdate not null                           -- 등록 날짜
);

-- 유입경로 카테고리 테이블
create table tblInflowCat(
    inflowCatSeq    number primary key,             -- 유입경로 카테고리 번호(PK)
    inflowName      varchar2(100) unique not null   -- 유입경로 이름(UQ)
);

-- 회원 유입경로
create table userInflow(
    userSeq         number references tblUser(userSeq),             -- 사용자 번호(PK)
    inflowCatSeq    number references tblInflowCat(inflowCatSeq),   -- 유입경로 카테고리 seq(PK)
    
    constraints userInflow_pk primary key(userSeq, inflowCatSeq)
);

-- 동화책 장르 테이블
create table tblGenre(
    genreSeq number primary key,                -- 장르 seq(PK)
    genreName varchar2(100) unique not null     -- 장르 이름(UQ)
);

-- 자유 게시판 테이블
create table tblBoard(
    boardSeq        number primary key,                         -- 자유 게시판 게시글 번호(PK)
    boardTitle      varchar2(100) not null,                     -- 제목
    boardContents   varchar2(2000) not null,                    -- 내용
    boardRegdate    date default sysdate not null,              -- 작성일
    boardReportCnt  number default 0 not null,                  -- 신고횟수
    boardCnt        number default 0 not null,                  -- 조회수
    userSeq         number references tblUser(userSeq) not null -- 사용자 번호(FK)
); 

-- 게시판 화이트 리스트 테이블
create table tblBoardWhiteList(
    boardSeq number primary key references tblBoard(boardSeq)   -- 블라인드 처리되지 않는 자유게시판 게시글(PK, FK)
);

-- 자유게시판 댓글 테이블
create table tblBoardComments(
    cmntSeq         number primary key,                                -- 자유게시판 댓글 번호(PK)
    userSeq         number references tblUser(userSeq) not null,       -- 작성자 번호(FK)
    boardSeq        number references tblBoard(boardSeq) not null,     -- 부모 게시글 번호(FK)
    cmntContents    varchar2(2000) not null,                           -- 내용
    cmntReportCnt   number default 0 not null,                         -- 신고횟수
    cmntRegdate     date default sysdate not null                      -- 작성날짜
);

-- 자유 게시판 댓글 화이트 리스트 테이블
create table tblBoardCommentsWhiteList(
    cmntSeq number primary key references tblBoardComments(cmntSeq) -- 블라인드 처리되지 않는 자유게시판 댓글(PK, FK)
);

-- 추천 연령 테이블
create table tblRecommendAge(
    rcmAgeSeq number primary key,           -- 추천 연령 번호
    rcmAge    varchar2(30) unique not null  -- 추천 연령
);

-- 동화책 테이블
-- likeCnt(좋아요수), bookReviewCnt(리뷰수), bookScrapCnt(저장수)는 뷰로 계산
create table tblBook(
    bookSeq         number primary key,                                     -- 동화책 번호(PK)
    bookTitle       varchar2(100) not null,                                 -- 제목
    bookInfo        varchar2(2000) not null,                                -- 한줄소개
    bookCover       varchar2(3000) not null,                                -- 표지 url
    bookRegdate     date default sysdate not null,                          -- 등록일
    bookModDate     date default null,                                      -- 수정일
    bookReportCnt   number default 0 not null,                              -- 신고횟수
    userSeq         number references tblUser(userSeq) not null,            -- 회원번호(FK)
    parentBookSeq   number default null references tblBook(bookSeq),        -- 부모 동화책 번호(FK)
    rcmAgeSeq       number references tblRecommendAge(rcmAgeSeq) not null   -- 추천 연령 번호(FK)
);

-- 동화책 화이트 리스트 테이블
create table tblBookWhiteList(
    bookSeq primary key references tblBook(bookSeq) -- 블라인드 처리되지 않은 동화책(PK, FK)
);

-- 동화책 장르 정보
create table tblGenreInfo(
    bookSeq     number references tblBook(bookSeq),         -- 동화책 번호(PK, FK)
    genreSeq    number references tblGenre(genreSeq),       -- 장르seq(PK, FK)
    
    constraints tblGenreInfo_pk primary key(bookSeq, genreSeq)
);

-- 동화책 페이지 테이블
create table tblPage(
    pageSeq         number,                                                         -- 페이지 번호(PK)
    bookSeq         number references tblBook(bookSeq),                             -- 동화책 번호(PK, FK)
    pageUrl         varchar2(200) not null,                                         -- 이미지 url
    pageContents    varchar2(4000) not null,                                        -- 페이지 내용
    cmntYN          varchar2(1) default 'y' check (cmntYN in ('y', 'n')) not null,  -- 문장 ai 사용 여부
    imgYN           varchar2(1) default 'y' check (imgYN in ('y', 'n')) not null,   -- 생성형 ai 사용 여부
    pageRegdate     date default sysdate not null,                                  -- 페이지 등록일
    
    constraints tblPage_pk primary key(pageSeq, bookSeq)
);

-- 저장한 동화책 기록
create table tblScrap(
    userSeq number references tblUser(userSeq),             -- 회원번호(PK, FK)
    bookSeq number references tblBook(bookSeq),             -- 동화책 번호(PK, FK)
    
    constraints tblScrap_pk primary key(userSeq, bookSeq)
);

-- 동화책 공유 게시판 테이블
create table tblBookShare(
    bookSeq         number primary key,              -- 동화책 번호(PK)
    shareRegdate    date default sysdate not null,   -- 공유 날짜
    shareCnt        number default 0 not null        -- 동화책 조회수
);

-- 수상 게시판 테이블
create table tblAward(
    bookSeq         number primary key references tblBookShare(bookSeq),    -- 수상 동화책 번호(PK, FK)
    awardRegdate    date default sysdate not null,                          -- 수상일
    awardRank       number                                                  -- 등수
);

-- 동화책 리뷰 테이블
create table tblReview(
    reviewSeq       number primary key,                             -- 동화책 리뷰 번호(PK)
    reviewContents  varchar2(2000) not null,                        -- 리뷰 내용
    reviewLikeCnt   number default 0 not null,                      -- 좋아요수
    reviewReportCnt number default 0 not null,                      -- 신고횟수
    userSeq         number references tblUser(userSeq) not null,    -- 리뷰 작성 사용자 번호(FK)
    bookSeq         number references tblBook(BookSeq) not null,    -- 부모 동화책 공유 게시글 번호(FK)
    reviewRegdate   date default sysdate not null
);

-- 사용자 장르 선호도 테이블
create table tblUserGenrePreference(
    userSeq     number references tblUser(userSeq),     -- 사용자 번호(PK, FK)
    genreSeq    number references tblGenre(genreSeq),   -- 장르 seq(PK, FK)
    genreCnt    number default 0 not null,              -- 횟수
    
    constraints tblUserGenrePreference_pk primary key(userSeq, genreSeq)
);

-- 리뷰 화이트 리스트 테이블
create table tblReviewWhiteList(
    reviewSeq number primary key references tblReview(reviewSeq)    -- 블라인드 처리되지 않은 감상글(PK, FK)
);

-- 동화책 좋아요 기록
create table tblLike(
    userSeq number references tblUser(userSeq), -- 회원 번호(PK, FK)
    bookSeq number references tblBook(BookSeq), -- 동화책 번호(PK, FK)
    
    constraints tblLike_pk primary key(userSeq, bookSeq)
);

commit;