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
CREATE OR REPLACE TRIGGER trg_update_ageCatSeq
BEFORE INSERT OR UPDATE ON tblChildAge
FOR EACH ROW
DECLARE
  v_age NUMBER;
BEGIN
  v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(:NEW.childBirth, 'YYMMDD')) / 12);
  CASE
    WHEN v_age BETWEEN 1 AND 2 THEN :NEW.ageCatSeq := 1;
    WHEN v_age BETWEEN 3 AND 4 THEN :NEW.ageCatSeq := 2;
    WHEN v_age BETWEEN 5 AND 6 THEN :NEW.ageCatSeq := 3;
    WHEN v_age BETWEEN 7 AND 9 THEN :NEW.ageCatSeq := 4;
    WHEN v_age >= 10 THEN :NEW.ageCatSeq := 5;
  END CASE;
END;
/

commit;