--SELECT 'DROP TABLE "' || TABLE_NAME || '" CASCADE CONSTRAINTS;' FROM user_tables;
--commit;

-- 회원 테이블
create table tblUser(
    userSeq         number primary key,                                     -- 사용자 번호(PK)
    userId          varchar(20)  unique not null,                           -- 아이디(UQ)
    userPw          varchar2(20) not null,                                  -- 비밀번호
    userName        varchar2(20) not null,                                  -- 이름
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
    userCatContents varchar2(200) not null       -- 내용
);

-- 사용자 행동 로그 테이블
create table tblUserLog(
    userLogSeq      number primary key,                                 -- 사용자 로그 번호(PK)
    userLogDate     date default sysdate not null,                      -- 날짜
    userSeq         number references tblUser(userSeq) not null,        -- 사용자 번호(FK)
    userLogContents varchar2(2000) not null,                             -- 로그 내용
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
    adNick      varchar2(50) not null,                   -- 닉네임(UQ)
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
    adLogContents varchar2(2000) not null,                    -- 로그 내용
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
    sgstSeq      number references tblSuggestion(sgstSeq) not null,      -- 부모글 번호(FK)
    sgstAnsw    varchar2(2000) not null,                                -- 답변 내용
    sgstRegdate date default sysdate not null                           -- 등록 날짜
);

-- 유입경로 카테고리 테이블
create table tblInflowCat(
    inflowCatSeq    number primary key,             -- 유입경로 카테고리 번호(PK)
    inflowName      varchar2(100) unique not null   -- 유입경로 이름(UQ)
);

-- 회원 유입경로
create table tblUserInflow(
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

-- 자유게시판 신고 기록
create table tblBoardReport(
    boardSeq number references tblBoard(boardSeq), -- 게시글 번호(PK, FK)
    userSeq number references tblUser(userSeq), -- 사용자 번호(PK, FK)
    
    constraints tblBoardReport_pk primary key(boardSeq, userSeq)
);

-- 자유게시판 댓글 신고 기록
create table tblBoardCommentsReport(
    cmntSeq number references tblBoardComments(cmntSeq), -- 댓글 번호(PK, FK)
    userSeq number references tblUser(userSeq),         -- 사용자 번호(PK, FK)
    
    constraints tblBoardCommentsReport_pk primary key(cmntSeq, userSeq)
);

-- 동화책 신고 기록
create table tblBookShareReport(
    bookSeq number references tblBookShare(bookSeq), -- 동화책 공유글 번호(PK, FK)
    userSeq number references tblUser(userSeq),      -- 사용자 번호(PK, FK)
    
    constraints tblBookShareReport_pk primary key(bookSeq, userSeq)
);

-- 동화책 리뷰 신고 기록
create table tblReviewReport(
    reviewSeq number references tblReview(reviewSeq), -- 동화책 리뷰 번호(PK, FK)
    userSeq number references tblUser(userSeq),      -- 사용자 번호(PK, FK)
    
    constraints tblReviewReport_pk primary key(reviewSeq, userSeq)
);

-- 동화책 리뷰 좋아요 기록
create table tblReviewLike(
    reviewSeq number references tblReview(reviewSeq), -- 동화책 리뷰 번호(PK, FK)
    userSeq number references tblUser(userSeq),      -- 사용자 번호(PK, FK)
    
    constraints tblReviewLike_pk primary key(reviewSeq, userSeq)
);

-- 성향 테이블
create table tblTendency(
    tendencySeq number primary key,         -- 성향 번호(PK)
    tendencyName varchar2(50) not null      -- 성향 이름
);

-- 성향정보 테이블
create table tblTendencyGenre(
    tendencySeq number references tblTendency(tendencySeq),
    genreSeq number references tblGenre(genreSeq),
    
    constraints tblTendencyGenre_pk primary key(tendencySeq, genreSeq)
);

---------------------
-- insert Category --
---------------------

-- tblAgeCat 나이 카테고리
insert into tblAgeCat(ageCatSeq, ageRange) values(1, '1세이상');
insert into tblAgeCat(ageCatSeq, ageRange) values(2, '3세이상');
insert into tblAgeCat(ageCatSeq, ageRange) values(3, '5세이상');
insert into tblAgeCat(ageCatSeq, ageRange) values(4, '7세이상');
insert into tblAgeCat(ageCatSeq, ageRange) values(5, '10세이상');

-- tblInflowCat 유입경로 카테고리
insert into tblInflowCat(inflowCatSeq, inflowName) values(1, '인터넷 검색');
insert into tblInflowCat(inflowCatSeq, inflowName) values(2, '지인 소개');
insert into tblInflowCat(inflowCatSeq, inflowName) values(3, '카페');
insert into tblInflowCat(inflowCatSeq, inflowName) values(4, '블로그');
insert into tblInflowCat(inflowCatSeq, inflowName) values(5, '소셜 미디어 플랫폼(인스타그램, 페이스북 등)');
insert into tblInflowCat(inflowCatSeq, inflowName) values(6, '광고지');
insert into tblInflowCat(inflowCatSeq, inflowName) values(7, '기타');

-- tblTendency 성향 테이블
insert into tblTendency(tendencySeq, tendencyName) values(1, '모험 성향');
insert into tblTendency(tendencySeq, tendencyName) values(2, '감성 성향');
insert into tblTendency(tendencySeq, tendencyName) values(3, '교육 성향');
insert into tblTendency(tendencySeq, tendencyName) values(4, '유머 성향');
insert into tblTendency(tendencySeq, tendencyName) values(5, '창의성 성향');

-- tblGenre 동화책 장르정보
-- 모험성향 --
insert into tblGenre(genreSeq, genreName) values(1, '판타지 동화');
insert into tblGenre(genreSeq, genreName) values(2, '탐험 동화');

-- 감성 성향
insert into tblGenre(genreSeq, genreName) values(3, '친구/가족 이야기 동화');
insert into tblGenre(genreSeq, genreName) values(4, '감동적인 동화');

-- 교육 성향
insert into tblGenre(genreSeq, genreName) values(5, '과학/자연 동화');
insert into tblGenre(genreSeq, genreName) values(6, '역사 동화');

-- 유머 성향
insert into tblGenre(genreSeq, genreName) values(7, '익살스런 동화');
insert into tblGenre(genreSeq, genreName) values(8, '말장난 동화');

-- 창의성 성향
insert into tblGenre(genreSeq, genreName) values(9, '발명품 관련 동화');
insert into tblGenre(genreSeq, genreName) values(10, '상상력 자극 동화');


-- tblTendencyGenre 성향 정보
insert into tblTendencyGenre(tendencySeq, genreSeq) values(1, 1);
insert into tblTendencyGenre(tendencySeq, genreSeq) values(1, 2);
insert into tblTendencyGenre(tendencySeq, genreSeq) values(2, 3);
insert into tblTendencyGenre(tendencySeq, genreSeq) values(2, 4);
insert into tblTendencyGenre(tendencySeq, genreSeq) values(3, 5);
insert into tblTendencyGenre(tendencySeq, genreSeq) values(3, 6);
insert into tblTendencyGenre(tendencySeq, genreSeq) values(4, 7);
insert into tblTendencyGenre(tendencySeq, genreSeq) values(4, 8);
insert into tblTendencyGenre(tendencySeq, genreSeq) values(5, 9);
insert into tblTendencyGenre(tendencySeq, genreSeq) values(5, 10);

-- tblRecommendAge 추천연령 
insert into tblRecommendAge(rcmAgeSeq, rcmAge) values(1, '1세이상');
insert into tblRecommendAge(rcmAgeSeq, rcmAge) values(2, '3세이상');
insert into tblRecommendAge(rcmAgeSeq, rcmAge) values(3, '5세이상');
insert into tblRecommendAge(rcmAgeSeq, rcmAge) values(4, '7세이상');
insert into tblRecommendAge(rcmAgeSeq, rcmAge) values(5, '10세이상');

-- tblAdCat 관리자 행동로그 카테고리
insert into tblAdcat(adCatSeq, adCatContents) values(1, '사용자 비활성화');
insert into tblAdcat(adCatSeq, adCatContents) values(2, '사용자 활성화');
insert into tblAdcat(adCatSeq, adCatContents) values(3, '저장소 용량 변경');
insert into tblAdcat(adCatSeq, adCatContents) values(4, '건의사항 답변');
insert into tblAdcat(adCatSeq, adCatContents) values(5, '자유게시판 게시글 비활성화');
insert into tblAdcat(adCatSeq, adCatContents) values(6, '자유게시판 게시글 활성화');
insert into tblAdcat(adCatSeq, adCatContents) values(7, '공지사항 작성');
insert into tblAdcat(adCatSeq, adCatContents) values(8, '공지사항 삭제');
insert into tblAdcat(adCatSeq, adCatContents) values(9, '공지사항 고정');
insert into tblAdcat(adCatSeq, adCatContents) values(10, '공지사항 고정해제');
insert into tblAdcat(adCatSeq, adCatContents) values(11, '동화책 비활성화');
insert into tblAdcat(adCatSeq, adCatContents) values(12, '동화책 활성화');
insert into tblAdcat(adCatSeq, adCatContents) values(13, '동화책 감상 비활성화');
insert into tblAdcat(adCatSeq, adCatContents) values(14, '동화책 감상 활성화');
insert into tblAdcat(adCatSeq, adCatContents) values(15, '동화책 수상');
insert into tblAdcat(adCatSeq, adCatContents) values(16, '자유게시판 댓글 비활성화');
insert into tblAdcat(adCatSeq, adCatContents) values(17, '자유게시판 댓글 활성화');
insert into tblAdcat(adCatSeq, adCatContents) values(18, '공지사항 수정');
insert into tblAdcat(adCatSeq, adCatContents) values(19, '건의사항 답변 수정');

-- 사용자 행동로그 카테고리
insert into tblUserCat(userCatSeq, userCatContents) values(1, '회원가입');
insert into tblUserCat(userCatSeq, userCatContents) values(2, '탈퇴');
insert into tblUserCat(userCatSeq, userCatContents) values(3, '로그인');
insert into tblUserCat(userCatSeq, userCatContents) values(4, '자유게시판 글작성');
insert into tblUserCat(userCatSeq, userCatContents) values(5, '자유게시판 글수정');
insert into tblUserCat(userCatSeq, userCatContents) values(6, '자유게시판 글 신고누적');
insert into tblUserCat(userCatSeq, userCatContents) values(7, '자유게시판 댓글 작성');
insert into tblUserCat(userCatSeq, userCatContents) values(8, '자유게시판 댓글 수정');
insert into tblUserCat(userCatSeq, userCatContents) values(10, '자유게시판 댓글 신고누적');
insert into tblUserCat(userCatSeq, userCatContents) values(11, '동화책 공유 등록');
insert into tblUserCat(userCatSeq, userCatContents) values(12, '동화책 조회');
insert into tblUserCat(userCatSeq, userCatContents) values(13, '동화책 수정');
insert into tblUserCat(userCatSeq, userCatContents) values(14, '동화책 스크랩');
insert into tblUserCat(userCatSeq, userCatContents) values(15, '동화책 좋아요');
insert into tblUserCat(userCatSeq, userCatContents) values(16, '동화책 신고');
insert into tblUserCat(userCatSeq, userCatContents) values(17, '동화책 신고누적');
insert into tblUserCat(userCatSeq, userCatContents) values(18, '동화책 감상 작성');
insert into tblUserCat(userCatSeq, userCatContents) values(19, '동화책 감상 수정');
insert into tblUserCat(userCatSeq, userCatContents) values(20, '동화책 감상 좋아요');
insert into tblUserCat(userCatSeq, userCatContents) values(21, '동화책 감상 신고누적');
insert into tblUserCat(userCatSeq, userCatContents) values(22, '남의 동화책 이야기 바꾸기');
insert into tblUserCat(userCatSeq, userCatContents) values(23, '건의사항 작성');
insert into tblUserCat(userCatSeq, userCatContents) values(24, '건의사항 수정');

--------------------
-- 최고관리자 계정--
--------------------
insert into tblAdmin(adId, adPw, adName, adNick, adAddress, adTel, adLv)
values('super', '1111', '최고관리자', '최고관리자', ' ', ' ', 3);

commit;

select
    t.tendencyName,
    g.genreName
from tblTendencyGenre tg
    inner join tblTendency t
    on t.tendencySeq = tg.tendencySeq
        inner join tblGenre g
        on g.genreSeq = tg.genreSeq;
