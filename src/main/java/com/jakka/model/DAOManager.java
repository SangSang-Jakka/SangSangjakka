package com.jakka.model;

import com.jakka.model.dao.admin.AdminDAO;
import com.jakka.model.dao.admin.AdminDAOImpl;
import com.jakka.model.dao.board.BoardCommentsDAO;
import com.jakka.model.dao.board.BoardCommentsDAOImpl;
import com.jakka.model.dao.board.BoardDAO;
import com.jakka.model.dao.board.BoardDAOImpl;
import com.jakka.model.dao.board.NoticeDAO;
import com.jakka.model.dao.board.NoticeDAOImpl;
import com.jakka.model.dao.board.SuggestionAnswerDAO;
import com.jakka.model.dao.board.SuggestionAnswerDAOImpl;
import com.jakka.model.dao.board.SuggestionDAO;
import com.jakka.model.dao.board.SuggestionDAOImpl;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.book.BookDAOImpl;
import com.jakka.model.dao.book.PageDAO;
import com.jakka.model.dao.book.PageDAOImpl;
import com.jakka.model.dao.book.ReviewDAO;
import com.jakka.model.dao.book.ReviewDAOImpl;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dao.user.UserDAOImpl;

public class DAOManager {

	
	// 관리자 DAO 객체 반환
    public static AdminDAO getAdminDAO() {
        return AdminDAOImpl.getInstance();
    }

    // 게시판 DAO 객체 반환
    public static BoardDAO getBoardDAO() {
        return BoardDAOImpl.getInstance();
    }

    // 공지사항 DAO 객체 반환
    public static NoticeDAO getNoticeDAO() {
        return NoticeDAOImpl.getInstance();
    }

    // 건의사항 DAO 객체 반환
    public static SuggestionDAO getSuggestionDAO() {
        return SuggestionDAOImpl.getInstance();
    }

    // 게시판 댓글 DAO 객체 반환
    public static BoardCommentsDAO getBoardCommentDAO() {
        return BoardCommentsDAOImpl.getInstance();
    }

    // 건의사항 답변 DAO 객체 반환
    public static SuggestionAnswerDAO getSuggestionAnswerDAO() {
        return SuggestionAnswerDAOImpl.getInstance();
    }

    // 사용자 DAO 객체 반환
    public static UserDAO getUserDAO() {
        return UserDAOImpl.getInstance();
    }

    // 동화책 DAO 객체 반환
    public static BookDAO getBookDAO() {
        return BookDAOImpl.getInstace();
    }

    // 페이지 DAO 객체 반환
    public static PageDAO getPageDAO() {
        return PageDAOImpl.getInstance();
    }

    // 리뷰 DAO 객체 반환
    public static ReviewDAO getReviewDAO() {
        return ReviewDAOImpl.getInstance();
    }
	
	
}//End of class
