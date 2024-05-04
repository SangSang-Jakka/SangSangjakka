-- 자유 게시판 whiteList 트리거
create or replace trigger trgTblBoardWhiteList
AFTER INSERT ON tblBoard
FOR EACH ROW
BEGIN
    INSERT INTO tblBoardWhiteList (boardSeq) VALUES (:NEW.boardSeq);
END;
/

-- 자유 게시판 댓글 whiteList 트리거
create or replace trigger trgTblBoardCmntWhiteList
AFTER INSERT ON tblBoardComments
FOR EACH ROW
BEGIN
    INSERT INTO tblBoardCommentsWhiteList (cmntSeq) VALUES (:NEW.cmntSeq);
END;
/

-- 동화책 whiteList 트리거
create or replace trigger trgBookWhiteList
AFTER INSERT ON tblBook
FOR EACH ROW
BEGIN
    INSERT INTO tblBookWhiteList (bookSeq) VALUES (:NEW.bookSeq);
END;
/

-- 동화책 리뷰 whiteList 트리거
create or replace trigger trgReviewWhiteList
AFTER INSERT ON tblReview
FOR EACH ROW
BEGIN
    INSERT INTO tblReviewWhiteList (reviewSeq) VALUES (:NEW.reviewSeq);
END;
/

-- 자녀 나이를 계산해서 insert될때마다 자녀나이 카테고리 갱신
CREATE OR REPLACE TRIGGER trg_tblChildAge_insert
BEFORE INSERT ON tblChildAge
FOR EACH ROW
DECLARE
    v_birthYear NUMBER;
    v_age NUMBER;
BEGIN
    -- 생년월일에서 연도 추출
    v_birthYear := TO_NUMBER(SUBSTR(:NEW.childBirth, 1, 2));
    
    -- 현재 연도의 끝 두 자리 추출
    v_age := TO_NUMBER(TO_CHAR(SYSDATE, 'YY'));
    
    -- 주민번호 앞자리의 연도가 현재 연도보다 크면 1900년대생, 작으면 2000년대생
    IF v_birthYear > v_age THEN
        v_birthYear := v_birthYear + 1900;
    ELSE
        v_birthYear := v_birthYear + 2000;
    END IF;
    
    -- 현재 연도에서 출생 연도를 뺀 나이 계산
    v_age := EXTRACT(YEAR FROM SYSDATE) - v_birthYear;
    
    -- 나이에 따른 ageCatSeq 할당
    IF v_age < 3 THEN
        :NEW.ageCatSeq := 1;
    ELSIF v_age < 5 THEN
        :NEW.ageCatSeq := 2;
    ELSIF v_age < 7 THEN
        :NEW.ageCatSeq := 3;
    ELSIF v_age < 10 THEN
        :NEW.ageCatSeq := 4;
    ELSE
        :NEW.ageCatSeq := 5;
    END IF;
    
END;
/

-- 자유게시판 신고횟수가 5이면 최고관리자가 자동으로 화이트리스트에서 제거
-- +로그
CREATE OR REPLACE TRIGGER trg_tblBoard_report
AFTER UPDATE ON vwBoard
FOR EACH ROW
WHEN (NEW.boardReportCnt = 5)
DECLARE
    v_userSeq NUMBER;
    v_boardTitle VARCHAR2(100);
    v_boardContents VARCHAR2(2000);
    v_boardSeq NUMBER;
BEGIN
    v_userSeq := :OLD.userSeq;
    v_boardTitle := :OLD.boardTitle;
    v_boardContents := :OLD.boardContents;
    v_boardSeq := :OLD.boardSeq;

    -- 화이트 리스트 제거
    DELETE FROM tblBoardWhiteList
    WHERE boardSeq = :OLD.boardSeq;

    -- 관리자 로그 추가
    INSERT INTO tblAdLog (adLogSeq, adLogDate, adId, adLogContents, adCatSeq)
    VALUES (
        (SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog),
        DEFAULT,
        'super',
        '시스템이 사용자번호''' || v_userSeq || ''' 글번호''' || v_boardSeq || ''' 글제목''' || v_boardTitle || ''' 자유게시판글을 신고횟수 누적으로 ''비활성화''했습니다.',
        5
    );

    -- 사용자 로그 추가
    INSERT INTO tblUserLog (userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq)
    VALUES (
        (SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog),
        SYSDATE,
        v_userSeq,
        '사용자번호''' || v_userSeq || '''이 글번호''' || v_boardSeq || ''' 글제목''' || v_boardTitle || ''' 글내용''' || v_boardContents || ''' 자유게시판글이 신고횟수 누적으로 ''비활성화''됬습니다.',
        6
    );
END;
/

-- 자유게시판 댓글이 신고횟수가 5이면 최고관리자가 자동으로 화이트리스트에서 제거
-- +로그
CREATE OR REPLACE TRIGGER trg_tblBoardComments_report
AFTER UPDATE ON vwBoardComments
FOR EACH ROW
WHEN (NEW.cmntReportCnt = 5)
DECLARE
    v_userSeq NUMBER;
    v_cmntContents VARCHAR2(2000);
    v_cmntSeq NUMBER;
BEGIN
    v_userSeq := :OLD.userSeq;
    v_cmntContents := :OLD.cmntContents;
    v_cmntSeq := :OLD.cmntSeq;

    DELETE FROM tblBoardCommentsWhiteList
    WHERE cmntSeq = :OLD.cmntSeq;

    -- 관리자 로그 추가
    INSERT INTO tblAdLog (adLogSeq, adLogDate, adId, adLogContents, adCatSeq)
    VALUES (
        (SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog),
        DEFAULT,
        'super',
        '시스템이 사용자번호''' || v_userSeq || ''' 글번호''' || v_cmntSeq || ''' 자유게시판 댓글을 신고횟수 누적으로 ''비활성화''했습니다.',
        16
    );

    -- 사용자 로그 추가
    INSERT INTO tblUserLog (userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq)
    VALUES (
        (SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog),
        SYSDATE,
        v_userSeq,
        '사용자번호''' || v_userSeq || '''이 글번호''' || v_cmntSeq || ''' 글내용''' || v_cmntContents || ''' 자유게시판 댓글이 신고횟수 누적으로 ''비활성화''됬습니다.',
        10
    );
END;
/


-- 동화책공유글이 신고횟수가 5이면 최고관리자가 자동으로 화이트리스트에서 제거
-- +로그
CREATE OR REPLACE TRIGGER trg_tblBook_report
AFTER UPDATE ON vwBook
FOR EACH ROW
WHEN (NEW.bookReportCnt = 5)
DECLARE
    v_userSeq NUMBER;
    v_bookTitle VARCHAR2(100);
    v_bookSeq NUMBER;
BEGIN
    v_userSeq := :OLD.userSeq;
    v_bookTitle := :OLD.bookTitle;
    v_bookSeq := :OLD.bookSeq;

    DELETE FROM tblBookWhiteList
    WHERE bookSeq = :OLD.bookSeq;

    -- 관리자 로그 추가
    INSERT INTO tblAdLog (adLogSeq, adLogDate, adId, adLogContents, adCatSeq)
    VALUES (
        (SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog),
        DEFAULT,
        'super',
        '시스템이 사용자번호''' || v_userSeq || ''' 글번호''' || v_bookSeq || ''' 동화책을 신고횟수 누적으로 ''비활성화''했습니다.',
        11
    );

    -- 사용자 로그 추가
    INSERT INTO tblUserLog (userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq)
    VALUES (
        (SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog),
        SYSDATE,
        v_userSeq,
        '사용자번호''' || v_userSeq || '''이 글번호''' || v_bookSeq || ''' 글제목''' || v_bookTitle || ''' 동화책이 신고횟수 누적으로 ''비활성화''됬습니다.',
        17
    );
END;
/


-- 동화책리뷰가 신고횟수가 5이면 최고관리자가 자동으로 화이트리스트에서 제거
-- +로그
CREATE OR REPLACE TRIGGER trg_tblReview_report
AFTER UPDATE ON vwReview
FOR EACH ROW
WHEN (NEW.reviewReportCnt = 5)
DECLARE
    v_userSeq NUMBER;
    v_reviewContents VARCHAR2(2000);
    v_reviewSeq NUMBER;
BEGIN
    v_userSeq := :OLD.userSeq;
    v_reviewContents := :OLD.reviewContents;
    v_reviewSeq := :OLD.reviewSeq;

    DELETE FROM tblReviewWhiteList
    WHERE reviewSeq = :OLD.reviewSeq;

    -- 관리자 로그 추가
    INSERT INTO tblAdLog (adLogSeq, adLogDate, adId, adLogContents, adCatSeq)
    VALUES (
        (SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog),
        DEFAULT,
        'super',
        '시스템이 사용자번호''' || v_userSeq || ''' 글번호''' || v_reviewSeq || ''' 동화책 감상을 신고횟수 누적으로 ''비활성화''했습니다.',
        13
    );

    -- 사용자 로그 추가
    INSERT INTO tblUserLog (userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq)
    VALUES (
        (SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog),
        SYSDATE,
        v_userSeq,
        '사용자번호''' || v_userSeq || '''이 글번호''' || v_reviewSeq || ''' 글내용''' || v_reviewContents || ''' 동화책 감상이 신고횟수 누적으로 ''비활성화''됬습니다.',
        21
    );
END;
/


commit;