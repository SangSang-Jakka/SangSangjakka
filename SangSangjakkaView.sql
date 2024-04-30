-- 동화책 테이블 + 좋아요, 리뷰수, 저장수
-- 블라인드제외 동화책
create or replace view vwBook
as
SELECT
    b.bookSeq,
    b.bookTitle,
    b.bookInfo,
    b.bookCover,
    b.bookRegdate,
    b.bookModDate,
    COALESCE(l.likeCnt, 0) AS likeCnt,
    COALESCE(r.reviewCnt, 0) AS bookReviewCnt,
    COALESCE(s.scrapCnt, 0) AS bookScrapCnt,
    b.bookReportCnt,
    b.userSeq,
    b.parentBookSeq,
    b.rcmAgeSeq,
    u.userNick
FROM
    tblBook b
    inner join tblUser u on b.userSeq = u.userSeq
    LEFT JOIN (SELECT bookSeq, COUNT(*) AS likeCnt FROM tblLike GROUP BY bookSeq) l ON b.bookSeq = l.bookSeq
    LEFT JOIN (SELECT bookSeq, COUNT(*) AS reviewCnt FROM tblReview GROUP BY bookSeq) r ON b.bookSeq = r.bookSeq
    LEFT JOIN (SELECT bookSeq, COUNT(*) AS scrapCnt FROM tblScrap GROUP BY bookSeq) s ON b.bookSeq = s.bookSeq;
    
    
-- 동화책 화이트리스트
CREATE OR REPLACE VIEW vwBookWhite
AS
SELECT 
    b.bookSeq,
    b.bookTitle,
    b.bookInfo,
    b.bookCover,
    b.bookRegdate,
    b.bookModDate,
    b.likeCnt,
    b.bookReviewCnt,
    b.bookScrapCnt,
    b.bookReportCnt,
    b.userSeq,
    b.parentBookSeq,
    b.rcmAgeSeq,
    b.userNick
FROM VWBOOK B
    INNER JOIN tblBookWhiteList bw 
    ON b.bookSeq = bw.bookSeq;



-- 동화책 블랙리스트
CREATE OR REPLACE VIEW vwBookBlack 
AS
SELECT 
    b.bookSeq,
    b.bookTitle,
    b.bookInfo, 
    b.bookCover,
    b.bookRegdate,
    b.bookModDate,
    b.bookReportCnt,
    b.userSeq,
    b.parentBookSeq,
    b.rcmAgeSeq
FROM 
    tblBook b
    LEFT JOIN tblBookWhiteList bw ON b.bookSeq = bw.bookSeq
WHERE
    bw.bookSeq IS NULL;


create or replace view vwSuggestion
as
select
    s.sgstSeq,
    s.sgstTitle,
    s.sgstContents,
    s.sgstRegdate,
    s.sgstSecretYN,
    s.userSeq,
    s.sgstCnt,
    u.userNick
from tblSuggestion s
    inner join tblUser u
    on s.userSeq = u.userSeq;

-- 자유게시판 차단 리스트
CREATE OR REPLACE VIEW  vwBoardBlack
AS
SELECT
    b.boardSeq,
    b.boardTitle,
    b.boardContents,
    b.boardRegdate,
    b.boardReportCnt,
    b.boardCnt,
    b.userSeq
FROM
    tblBoard b
WHERE
    b.boardSeq NOT IN (
        SELECT
            boardSeq
        FROM
            tblBoardWhiteList
    );

-- 블라인드제외 자유게시판 글
CREATE OR REPLACE VIEW vwBoard
AS
SELECT
    b.boardSeq,
    b.boardTitle,
    b.boardContents,
    b.boardRegdate,
    b.boardReportCnt,
    b.boardCnt,
    b.userSeq
FROM tblBoard b
    inner JOIN tblBoardWhiteList w
    on b.boardSeq = w.boardSeq
order by b.boardRegdate desc;


-- 블라인드제외 자유게시판 댓글
create or replace view vwBoardComments
as
select
    c.cmntSeq,
    c.userSeq,
    c.boardSeq,
    c.cmntContents,
    c.cmntReportCnt,
    c.cmntRegdate
from tblBoardComments c
    inner join tblBoardCommentWhiteList w
    on c.cmntSeq = w.cmntSeq
order by c.cmntRegdate;

-- 자유게시판 댓글 블랙리스트
CREATE OR REPLACE VIEW vwBoardCommentsBlack 
AS
SELECT
    c.cmntSeq,
    c.userSeq,
    c.boardSeq,
    c.cmntContents,
    c.cmntReportCnt,
    c.cmntRegdate
FROM
    tblBoardComments c
WHERE
    c.cmntSeq NOT IN (
        SELECT
            cmntSeq
        FROM
            tblBoardCommentsWhiteList
    );

--화이트리스트 스크랩 내역
CREATE OR REPLACE VIEW vwScrap
AS
SELECT 
    b.bookSeq, 
    b.bookTitle, 
    b.bookInfo, 
    b.bookCover,
    b.bookRegdate,
    b.bookModDate, 
    b.likeCnt,
    b.bookReviewCnt, 
    b.bookScrapCnt,
    b.bookReportCnt,
    b.userSeq,
    b.parentBookSeq,
    b.rcmAgeSeq,
    b.userNick,
    s.userSeq as mySeq
FROM 
    vwBookWhite b
    INNER JOIN tblScrap s ON b.bookSeq = s.bookSeq;

-- 화이트리스트 좋아요 내역
CREATE OR REPLACE VIEW vwLike 
AS  
SELECT
    b.bookSeq,
    b.bookTitle,
    b.bookInfo, 
    b.bookCover,
    b.bookRegdate,
    b.bookModDate,
    b.likeCnt,
    b.bookReviewCnt,
    b.bookScrapCnt,
    b.bookReportCnt,
    b.userSeq,
    b.parentBookSeq,
    b.rcmAgeSeq,
    b.userNick,
    l.userSeq as mySeq
FROM
    vwBookWhite b
    INNER JOIN tblLike l ON b.bookSeq = l.bookSeq;


-- 화이트 리뷰
CREATE OR REPLACE VIEW vwReviewWhite 
AS
SELECT
    r.reviewSeq,
    r.reviewContents,
    r.reviewLikeCnt,
    r.reviewReportCnt,
    r.userSeq,
    r.bookSeq,
    r.reviewRegdate
FROM
    tblReview r
    INNER JOIN tblReviewWhiteList rw ON r.reviewSeq = rw.reviewSeq;


-- 블랙 리뷰
CREATE OR REPLACE VIEW vwReviewBlack 
AS
SELECT
    r.reviewSeq,
    r.reviewContents,
    r.reviewLikeCnt,
    r.reviewReportCnt,
    r.userSeq,
    r.bookSeq,
    r.reviewRegdate
FROM
    tblReview r
    LEFT JOIN tblReviewWhiteList rw ON r.reviewSeq = rw.reviewSeq
WHERE
    rw.reviewSeq IS NULL;









