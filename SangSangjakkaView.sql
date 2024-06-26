-- 동화책 테이블 + 좋아요, 리뷰수, 저장수, 신고횟수
-- 동화책 목록
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
    COALESCE(re.bookReportCnt, 0) AS bookReportCnt,
    b.shareCnt AS bookCnt,
    b.userSeq,
    b.parentBookSeq,
    b.rcmAgeSeq,
    u.userNick
FROM tblBook b
    INNER JOIN tblUser u 
    ON b.userSeq = u.userSeq
        LEFT JOIN (SELECT bookSeq, COUNT(*) AS likeCnt FROM tblLike GROUP BY bookSeq) l
        ON b.bookSeq = l.bookSeq
            LEFT JOIN (SELECT bookSeq, COUNT(*) AS reviewCnt FROM tblReview GROUP BY bookSeq) r
            ON b.bookSeq = r.bookSeq
                LEFT JOIN (SELECT bookSeq, COUNT(*) AS scrapCnt FROM tblScrap GROUP BY bookSeq) s
                ON b.bookSeq = s.bookSeq
                    LEFT JOIN (SELECT bookSeq, COUNT(*) AS bookReportCnt FROM tblBookShareReport GROUP BY bookSeq) re
                    ON b.bookSeq = re.bookSeq;
    
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
    b.userNick,
    b.bookCnt
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
    b.likeCnt,
    b.bookReviewCnt,
    b.bookScrapCnt,
    b.bookReportCnt,
    b.userSeq,
    b.parentBookSeq,
    b.rcmAgeSeq,
    b.userNick,
    b.bookCnt
FROM vwBook b
WHERE b.bookSeq NOT IN(SELECT bookSeq FROM tblBookWhiteList);


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

--  게시판 테이블 + 신고횟수
CREATE OR REPLACE VIEW vwBoard
AS
SELECT
    b.boardSeq,
    b.boardTitle,
    b.boardContents,
    b.boardRegdate,
    COALESCE(re.boardReportCnt, 0) AS boardReportCnt,
    COALESCE(cm.cmntCnt, 0) AS cmntCnt,
    b.boardCnt,
    b.userSeq,
    u.userNick
FROM tblBoard b
    inner join tblUser u
    on u.userSeq = b.userSeq
    LEFT JOIN (SELECT boardSeq, COUNT(*) AS boardReportCnt FROM tblBoardReport GROUP BY boardSeq) re
    ON b.boardSeq = re.boardSeq
        left join (select boardSeq, COUNT(*) AS cmntCnt from vwBoardCommentsWhite GROUP BY boardSeq) cm
                on b.boardSeq = cm.boardSeq;


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
    b.userSeq,
    b.userNick
FROM vwBoard b
WHERE b.boardSeq NOT IN (SELECT boardSeq FROM tblBoardWhiteList);

-- 블라인드제외 자유게시판 글
CREATE OR REPLACE VIEW vwBoardWhite
AS
    SELECT
        b.boardSeq,
        b.boardTitle,
        b.boardContents,
        b.boardRegdate,
        b.boardReportCnt,
        b.boardCnt,
        b.userSeq,
        b.userNick,
        COALESCE(cm.cmntCnt, 0) AS cmntCnt
    FROM vwBoard b
        inner JOIN tblBoardWhiteList w
        on b.boardSeq = w.boardSeq
            left join (select boardSeq, COUNT(*) AS cmntCnt from vwBoardCommentsWhite GROUP BY boardSeq) cm
            on b.boardSeq = cm.boardSeq;

-- 자유게시판 댓글 + 신고횟수
create or replace view vwBoardComments
as
    select
        c.cmntSeq,
        c.userSeq,
        c.boardSeq,
        c.cmntContents,
        COALESCE(re.cmntReportCnt, 0) AS cmntReportCnt,
        c.cmntRegdate,
        u.userNick
    from tblBoardComments c
        inner join tblUser u
        on u.userSeq = c.userSeq
            LEFT JOIN (SELECT cmntSeq, COUNT(*) AS cmntReportCnt FROM tblBoardCommentsReport GROUP BY cmntSeq) re
            ON c.cmntSeq = re.cmntSeq;

-- 자유게시판 댓글 화이트리스트
create or replace view vwBoardCommentsWhite
AS
    SELECT
        c.cmntSeq,
        c.userSeq,
        c.boardSeq,
        c.cmntContents,
        c.cmntReportCnt,
        c.cmntRegdate,
        c.userNick
    FROM vwBoardComments c
        inner join tblBoardCommentsWhiteList wc
        on wc.cmntSeq = c.cmntSeq;

-- 자유게시판 댓글 블랙리스트
CREATE OR REPLACE VIEW vwBoardCommentsBlack 
AS
SELECT
    c.cmntSeq,
    c.userSeq,
    c.boardSeq,
    c.cmntContents,
    c.cmntReportCnt,
    c.cmntRegdate,
    c.userNick
FROM vwBoardComments c
WHERE c.cmntSeq NOT IN (SELECT cmntSeq FROM tblBoardCommentsWhiteList);

-- 리뷰 테이블 + 신고횟수
CREATE OR REPLACE VIEW vwReview
AS
SELECT
    r.reviewSeq,
    r.reviewContents,
    COALESCE(li.reviewLikeCnt, 0) AS reviewLikeCnt,
    COALESCE(re.reviewReportCnt, 0) AS reviewReportCnt,
    r.userSeq,
    r.bookSeq,
    r.reviewRegdate,
    u.userNick
FROM tblReview r
    inner join tblUser u
    on u.userSeq = r.userSeq    
        LEFT JOIN (SELECT reviewSeq, COUNT(*) AS reviewReportCnt FROM tblReviewReport GROUP BY reviewSeq) re
        ON r.reviewSeq = re.reviewSeq
            LEFT JOIN (SELECT reviewSeq, COUNT(*) AS reviewLikeCnt FROM tblReviewLike GROUP BY reviewSeq) li
            ON r.reviewSeq = li.reviewSeq;
            
        

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
    r.reviewRegdate,
    r.userNick
 FROM vwReview r
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
    r.reviewRegdate,
    r.userNick
FROM vwReview r
    LEFT JOIN tblReviewWhiteList rw 
    ON r.reviewSeq = rw.reviewSeq
WHERE rw.reviewSeq NOT IN(SELECT reviewSeq FROM tblReviewWhiteList);

-- 좋아요 기록
CREATE OR REPLACE VIEW vwLike
as
select 
    u.userSeq as likeUserSeq,
    u.userNick as likeUserNick,
    u.userId as likeUserId,
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
from tblLike l
    inner join vwBook b
    on l.bookSeq = b.bookSeq
        inner join tblUser u
        on l.userSeq = u.userSeq;

-- 스크랩 기록
CREATE OR REPLACE VIEW vwScrap
as
select 
    u.userSeq as scrapUserSeq,
    u.userNick as scrapUserNick,
    u.userId as scrapUserId,
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
from tblScrap s
    inner join vwBook b
    on s.bookSeq = b.bookSeq
        inner join tblUser u
        on s.userSeq = u.userSeq;

--자녀 연령대 범위
CREATE or replace VIEW vwAgeRangeCount
AS
SELECT
  ac.agerange,
  COALESCE(ct.count, 0) AS count
FROM
  tblAgeCat ac
  LEFT JOIN (
    SELECT
      ta.agerange,
      COUNT(*) AS count
    FROM
      tblChildAge tc
      JOIN tblAgeCat ta ON tc.agecatseq = ta.agecatseq
    GROUP BY
      ta.agerange
  ) ct ON ac.agerange = ct.agerange
ORDER BY
  ac.agecatseq;
  
  
-- 사용자 연령대

CREATE OR REPLACE VIEW vwUserAge AS
SELECT
    age_range,
    COUNT(*) AS count
FROM
    (
        SELECT
            CASE
                WHEN age BETWEEN 0 AND 10 THEN '10세이하'
                WHEN age BETWEEN 11 AND 20 THEN '10세이상'
                WHEN age BETWEEN 21 AND 30 THEN '20세이상'
                WHEN age BETWEEN 31 AND 40 THEN '30세이상'
                WHEN age BETWEEN 41 AND 50 THEN '40세이상'
                ELSE '50세이상'
            END AS age_range,
            age
        FROM
            (
                SELECT
                    CASE
                        WHEN SUBSTR(userleftssn, 1, 2) > TO_CHAR(SYSDATE, 'YY') THEN
                            EXTRACT(YEAR FROM SYSDATE) - (1900 + SUBSTR(userleftssn, 1, 2)) + 1
                        ELSE
                            EXTRACT(YEAR FROM SYSDATE) - (2000 + SUBSTR(userleftssn, 1, 2)) + 1
                    END AS age
                FROM
                    tblUser
                WHERE
                    userleftssn IS NOT NULL
            )
    )
GROUP BY
    age_range
ORDER BY
    age_range;

   

-- 수상한 동화책
CREATE or replace VIEW vwAward
AS
select
    b.*,
    a.awardRegdate,
    a.awardRank
from vwBookWhite b
    inner join tblAward a
    on b.bookSeq = a.bookSeq;
   
 CREATE OR REPLACE VIEW vwInflowcount AS
SELECT TO_CHAR(u.userregdate, 'YYYY/MM') AS registration_month,
       c.inflowname,
       COUNT(i.inflowcatseq) AS inflow_count
FROM tbluserinflow i
INNER JOIN tblUser u ON i.userseq = u.userseq
INNER JOIN tblinflowcat c ON i.inflowcatseq = c.inflowcatseq
WHERE TO_CHAR(u.userregdate, 'YYYY/MM') = '2023/07'
GROUP BY TO_CHAR(u.userregdate, 'YYYY/MM'), c.inflowname
ORDER BY inflow_count DESC;  

-- 해당 동화책 신고자 조회
CREATE OR REPLACE VIEW vwReportUser AS
SELECT
    vw.bookSeq AS bookSeq,
    bsr.userSeq AS reportUserSeq,
    ul.userLogDate AS reportDate,
    ul.userCatSeq,
    u.userId,
    u.userNick AS userNick
FROM
    vwBook vw
    INNER JOIN tblBookShareReport bsr ON vw.bookSeq = bsr.bookSeq
    INNER JOIN tblUserLog ul ON bsr.userSeq = ul.userSeq
    INNER JOIN tbluser u ON ul.userSeq = u.userSeq;
   
-- 사용자의 10개의 동화책 장르에 대한 가중치 계산 뷰
CREATE OR REPLACE VIEW vwUserGenreScore AS
SELECT 
    u.userSeq AS userSeq,
    u.userId AS userId,
    u.userNick AS userNick,
    g.genreName AS genreName,
    g.genreSeq AS genreSeq,
    COALESCE(SUM(
        CASE 
            WHEN ac.actionName = '조회' AND gi.genreSeq = g.genreSeq THEN 1.2
            WHEN ac.actionName = '좋아요' AND gi.genreSeq = g.genreSeq THEN 1.8
            WHEN ac.actionName = '스크랩' AND gi.genreSeq = g.genreSeq THEN 2.4
            WHEN ac.actionName = '나의이야기로만들기' AND gi.genreSeq = g.genreSeq THEN 3.0
            WHEN ac.actionName = '동화책제작' AND gi.genreSeq = g.genreSeq THEN 4.8
            WHEN ac.actionName = '리뷰' AND gi.genreSeq = g.genreSeq THEN 3.6
            ELSE 0
        END
    ), 0) AS score
FROM 
    tblUser u
    CROSS JOIN tblGenre g
    LEFT JOIN tblUserBookAction uba ON u.userSeq = uba.userSeq
    LEFT JOIN tblActionCat ac ON uba.actionCatSeq = ac.actionCatSeq
    LEFT JOIN tblBook b ON uba.bookSeq = b.bookSeq
    LEFT JOIN tblGenreInfo gi ON b.bookSeq = gi.bookSeq
GROUP BY 
    u.userSeq, 
    u.userId, 
    u.userNick, 
    g.genreName, 
    g.genreSeq
ORDER BY
    u.userSeq,
    g.genreSeq;
    

-- 마이페이지에서 보여질 사용자의 성향 점수
CREATE OR REPLACE VIEW vwUserTendencyScore AS
SELECT
    u.userSeq,
    u.userId,
    u.userNick,
    t.tendencySeq,
    t.tendencyName,
    COALESCE(
        ROUND(
            COALESCE(SUM(COALESCE(ugs.score, 0)), 0) /
            NULLIF(
                (SELECT SUM(COALESCE(score, 0)) FROM vwUserGenreScore WHERE userSeq = u.userSeq),
                0
            ) * 5.0,
            1
        ),
        0
    ) AS tendencyScore
FROM
    tblUser u
    CROSS JOIN tblTendency t
    LEFT JOIN tblTendencyGenre tg ON t.tendencySeq = tg.tendencySeq
    LEFT JOIN vwUserGenreScore ugs ON tg.genreSeq = ugs.genreSeq AND u.userSeq = ugs.userSeq
GROUP BY
    u.userSeq,
    u.userId,
    u.userNick,
    t.tendencySeq,
    t.tendencyName
ORDER BY
    u.userSeq,
    t.tendencySeq;
   
   
commit;