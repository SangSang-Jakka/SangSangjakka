package com.jakka.model.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.jakka.model.DBUtil;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Cnt;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.enums.AdminLog;
import com.jakka.model.enums.UserLog;

public class BoardDAOImpl implements BoardDAO {

	private static final BoardDAOImpl DAO = new BoardDAOImpl();

	private BoardDAOImpl() {
		// 외부 생성 방지
	}

	public static BoardDAOImpl getInstance() {

		return DAO;

	}// getInstance()

	// 자유게시판 전체글
	@Override
	public ArrayList<BoardDTO> findAll() {

		final String SQL = "select * from vwBoard order by boardRegdate desc";

		try (

				Connection conn = DBUtil.open();
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(SQL);

		) {

			ArrayList<BoardDTO> list = new ArrayList<>();

			while (rs.next()) {

				BoardDTO dto = new BoardDTO();

				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserNick(rs.getString("userNick"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println("BoardDAO.| list");
			e.printStackTrace();
		}

		return null;

	}// list()

	// 블라인드 제외 전체글
	public ArrayList<BoardDTO> findAllWhite() {

		final String SQL = "select * from vwBoardWhite order by boardRegdate desc";

		try (

				Connection conn = DBUtil.open();
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(SQL);

		) {

			ArrayList<BoardDTO> list = new ArrayList<>();

			while (rs.next()) {

				BoardDTO dto = new BoardDTO();

				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserNick(rs.getString("userNick"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println("BoardDAO.| list");
			e.printStackTrace();
		}

		return null;

	}// list()
	
	@Override
    public ArrayList<BoardDTO> findAllWhite(HashMap<String, String> map) {

        String where = "where rnum BETWEEN ? AND ?";
        String col = "";

        if (map.get("search").equals("y")) {
            col = col + String.format(" where %s like '%%%s%%'", map.get("column"), map.get("word"));
        }

        String sql = String.format("SELECT boardSeq, boardTitle, boardContents, boardRegdate, boardReportCnt, boardCnt, userSeq, userNick, cmntCnt " +
                "FROM (SELECT ROWNUM RNUM, f.boardSeq, f.boardTitle, f.boardContents, f.boardRegdate, f.boardReportCnt, f.boardCnt, f.userSeq, f.userNick, f.cmntCnt " +
                "FROM (SELECT * FROM vwBoardWhite %s ORDER BY boardRegdate desc) f) " +
                "%s", col, where);

        try (
                Connection conn = DBUtil.open(); 
                PreparedStatement pstat = conn.prepareStatement(sql);
        ) {
            pstat.setString(1, map.get("begin"));
            pstat.setString(2, map.get("end"));

            ResultSet rs = pstat.executeQuery();

            ArrayList<BoardDTO> list = new ArrayList<>();

            while (rs.next()) {

                BoardDTO dto = new BoardDTO();

                dto.setBoardCnt(rs.getString("boardCnt"));
                dto.setBoardContents(rs.getString("boardContents"));
                dto.setBoardRegdate(rs.getString("boardRegdate"));
                dto.setBoardReportCnt(rs.getString("boardReportCnt"));
                dto.setBoardSeq(rs.getString("boardSeq"));
                dto.setBoardTitle(rs.getString("boardTitle"));
                dto.setUserSeq(rs.getString("userSeq"));
                dto.setUserNick(rs.getString("userNick"));
                dto.setCmntCnt(rs.getString("cmntCnt"));

                list.add(dto);

            }

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
	
	
	
	public ArrayList<BoardDTO> findAllWhite(HashMap<String, String> map, String orderBy) {
		
		//System.out.println(orderBy);
	    String SQL = "SELECT boardSeq, boardTitle, boardContents, boardRegdate, boardReportCnt, boardCnt, userSeq, userNick, cmntCnt " +
	                 "FROM (SELECT ROWNUM RNUM, f.boardSeq, f.boardTitle, f.boardContents, f.boardRegdate, f.boardReportCnt, f.boardCnt, f.userSeq, f.userNick, f.cmntCnt " +
	                 "FROM (SELECT * FROM vwBoardWhite ";

	    if (map.get("search").equals("y")) {
	        SQL += String.format("WHERE %s like '%%%s%%' ", map.get("column"), map.get("word"));
	    }

	    SQL += "ORDER BY ";

	    switch (orderBy) {
	        case "newest":
	            SQL += "boardRegdate DESC";
	            break;
	        case "view_count":
	            SQL += "boardCnt DESC";
	            break;
	        case "comment_count":
	            SQL += "CMNTCNT DESC";
	            break;
	        default:
	            SQL += "boardRegdate DESC";
	    }

	    SQL += ") f) " +
	           "WHERE RNUM BETWEEN ? AND ?";

	    try (
	        Connection conn = DBUtil.open();
	        PreparedStatement pstmt = conn.prepareStatement(SQL);
	    ) {
	        pstmt.setString(1, map.get("begin"));
	        pstmt.setString(2, map.get("end"));

	        ResultSet rs = pstmt.executeQuery();

	        ArrayList<BoardDTO> list = new ArrayList<>();
	        
	        System.out.println(SQL);

	        while (rs.next()) {
	            BoardDTO dto = new BoardDTO();
	            dto.setBoardCnt(rs.getString("boardCnt"));
	            dto.setBoardContents(rs.getString("boardContents"));
	            dto.setBoardRegdate(rs.getString("boardRegdate"));
	            dto.setBoardReportCnt(rs.getString("boardReportCnt"));
	            dto.setBoardSeq(rs.getString("boardSeq"));
	            dto.setBoardTitle(rs.getString("boardTitle"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            dto.setUserNick(rs.getString("userNick"));
	            dto.setCmntCnt(rs.getString("cmntCnt"));
	            list.add(dto);
	        }

	        System.out.println(list);
	        return list;

	    } catch (Exception e) {
	        System.out.println("BoardDAO.| list");
	        e.printStackTrace();
	    }

	    return null;
	}



	// 블라인드된 자유게시판 글
	@Override
	public ArrayList<BoardDTO> findAllBlack() {

		final String SQL = "SELECT * FROM vwBoardBlack order by boardRegdate desc";

		try (Connection conn = DBUtil.open();
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(SQL)) {

			ArrayList<BoardDTO> list = new ArrayList<>();

			while (rs.next()) {

				BoardDTO dto = new BoardDTO();

				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setUserSeq(rs.getString("userSeq"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("BoardDAO.| findAllBlack");
			e.printStackTrace();
		}
		return null;
	}

	// 게시판 글 추가
	// 제목, 내용, 작성자seq
	@Override
	public int add(BoardDTO dto) {

		final String SQL = "INSERT INTO tblBoard (boardSeq, boardTitle, boardContents, boardRegdate, boardCnt, userSeq) VALUES ((SELECT NVL(MAX(boardSeq), 0) + 1 FROM tblBoard), ?, ?, DEFAULT, DEFAULT, ?)";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";

		try (

				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				PreparedStatement log = conn.prepareStatement(LOGSQL);) {

			conn.setAutoCommit(false);

			pstat.setString(1, dto.getBoardTitle());
			pstat.setString(2, dto.getBoardContents());
			pstat.setString(3, dto.getUserSeq());

			int result = pstat.executeUpdate();

			if (result > 0) {
				log.setString(1, dto.getUserSeq());
				log.setString(2, "사용자번호'" + dto.getUserSeq() + "'이 글제목'" + dto.getBoardTitle() + "' 글내용'"
						+ dto.getBoardContents() + "' 자유게시판글을 '작성'했습니다.");
				log.setString(3, UserLog.BoardCreated.getValue());
				log.executeUpdate();
			}

			conn.commit();

			return result;

		} catch (Exception e) {
			System.out.println("BoardDAO.| add");
			e.printStackTrace();
		}

		return 0;

	}// add()

	// 게시글 수정(이름, 내용)
	@Override
	public int save(BoardDTO dto) {

		final String SQL = "update tblBoard set boardTitle = ?, boardContents = ? where boardSeq = ?";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";

		try (

				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				PreparedStatement log = conn.prepareStatement(LOGSQL);

		) {

			conn.setAutoCommit(false);

			pstat.setString(1, dto.getBoardTitle());
			pstat.setString(2, dto.getBoardContents());
			pstat.setString(3, dto.getBoardSeq());
			

			int result = pstat.executeUpdate();

			if (result > 0) {
				log.setString(1, dto.getUserSeq());
				log.setString(2, "사용자번호'" + dto.getUserSeq() + "'이 글번호'" + dto.getBoardSeq() + "' 글제목'"
						+ dto.getBoardTitle() + "' 글내용'" + dto.getBoardContents() + "' 자유게시판글을 '수정'했습니다.");
				log.setString(3, UserLog.BoardEdited.getValue());
				log.executeUpdate();
			}

			conn.commit();

			return result;

		} catch (Exception e) {
			System.out.println("BoardDAO.| set");
			e.printStackTrace();
		}

		return 0;

	}// set()

	// 게시글 조회수 증가
	public int addCnt(String boardSeq) {

		final String SQL = "update tblBoard set boardCnt = boardCnt + 1 where boardSeq = ?";

		try (

				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);

		) {

			pstat.setString(1, boardSeq);

			int result = pstat.executeUpdate();

			return result;

		} catch (Exception e) {
			System.out.println("BoardDAO.| addCnt");
			e.printStackTrace();
		}

		return 0;
	}// addCnt()

	// 게시글 신고횟수 증가
	public int addReportCnt(String boardSeq, String userSeq) {

		final String SQL = "insert into tblBoardReport(boardSeq, userSeq) values(?, ?)";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";

		try (

				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				PreparedStatement log = conn.prepareStatement(LOGSQL);

		) {

			conn.setAutoCommit(false);

			pstat.setString(1, boardSeq);
			pstat.setString(2, userSeq);

			int result = pstat.executeUpdate();

			if (result > 0) {
				log.setString(1, userSeq);
				log.setString(2, "사용자번호'" + userSeq + "'이 글번호'" + boardSeq + "' 자유게시판글을 '신고'했습니다.");
				log.setString(3, UserLog.BoardReported.getValue());
				log.executeUpdate();
			}

			conn.commit();

			return result;

		} catch (Exception e) {
			System.out.println("BoardDAO.| addReportCnt");
			e.printStackTrace();
		}

		return 0;

	}// addReportCnt()

	// 특정 게시글 정보 가져오기
	@Override
	public BoardDTO findById(String boardSeq) {

		final String SQL = "select * from vwBoard where boardSeq = ?";

		try (

				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);

		) {

			pstat.setString(1, boardSeq);

			ResultSet rs = pstat.executeQuery();

			BoardDTO dto = new BoardDTO();

			if (rs.next()) {

				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserNick(rs.getString("userNick"));
				dto.setCmntCnt(rs.getString("cmntCnt"));

			}

			rs.close();
			return dto;

		} catch (Exception e) {
			System.out.println("BoardDAO.| get");
			e.printStackTrace();
		}

		return null;

	}// get()

	// 비활성화
	public int disable(String boardSeq, String adId) {

		final String SQL = "delete from tblBoardWhiteList where boardSeq = ?";
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, ?)";

		try (Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				PreparedStatement log = conn.prepareStatement(LOGSQL);) {
			conn.setAutoCommit(false);

			pstat.setString(1, boardSeq);

			int result = pstat.executeUpdate();

			if (result > 0) {
				log.setString(1, adId);
				log.setString(2, "'" + adId + "'이 글번호'" + boardSeq + "'자유게시판글을 '비활성화'했습니다.");
				log.setString(3, AdminLog.BoardDisabled.getValue());
				log.executeUpdate();
			}

			conn.commit();

			return result;

		} catch (Exception e) {
			System.out.println("BoardDAO.| disable");
			e.printStackTrace();
		}

		return 0;
	}

	// 활성화
	public int activation(String boardSeq, String adId) {

		final String SQL = "insert into tblBoardWhiteList(boardSeq) values(?)";
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, ?)";

		try (Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				PreparedStatement log = conn.prepareStatement(LOGSQL);) {
			conn.setAutoCommit(false);

			pstat.setString(1, boardSeq);

			int result = pstat.executeUpdate();

			if (result > 0) {
				log.setString(1, adId);
				log.setString(2, "'" + adId + "'이 글번호'" + boardSeq + "'자유게시판글을' 활성화'했습니다.");
				log.setString(3, AdminLog.BoardEnabled.getValue());
				log.executeUpdate();
			}

			conn.commit();

			return result;

		} catch (Exception e) {
			System.out.println("BoardDAO.| activation");
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public ArrayList<BoardDTO> findByContentsContains(String word) {

		final String SQL = "SELECT * FROM vwBoard WHERE boardContents LIKE ? order by boardRegdate desc";

		try (Connection conn = DBUtil.open(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, "%" + word + "%");

			ResultSet rs = pstmt.executeQuery();

			ArrayList<BoardDTO> list = new ArrayList<>();

			while (rs.next()) {

				BoardDTO dto = new BoardDTO();

				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setUserSeq(rs.getString("userSeq"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("BoardDAO.| findByContentsContains");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<BoardDTO> findByNick(String Nick) {

		final String SQL = "SELECT * FROM vwBoard WHERE userNick = ? order by boardRegdate desc";

		try (Connection conn = DBUtil.open(); PreparedStatement pstat = conn.prepareStatement(SQL);) {
			pstat.setString(1, Nick);

			ResultSet rs = pstat.executeQuery();

			ArrayList<BoardDTO> list = new ArrayList<>();

			while (rs.next()) {

				BoardDTO dto = new BoardDTO();

				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setUserSeq(rs.getString("userSeq"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("BoardDAOImpl.| findByNick");
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<BoardDTO> findByRegdateAfter(String date) {

		final String SQL = "SELECT * FROM vwBoard WHERE boardRegdate > TO_DATE(?, 'YYYY-MM-DD') ORDER BY boardRegdate DESC";

		try (Connection conn = DBUtil.open(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, date);

			ResultSet rs = pstmt.executeQuery();

			ArrayList<BoardDTO> list = new ArrayList<>();

			while (rs.next()) {

				BoardDTO dto = new BoardDTO();

				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setUserSeq(rs.getString("userSeq"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("BoardDAO.| findByRegdateAfter");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<BoardDTO> findByRegdateBefore(String date) {

		final String SQL = "SELECT * FROM vwBoard WHERE boardRegdate < TO_DATE(?, 'YYYY-MM-DD') ORDER BY boardRegdate DESC";

		try (Connection conn = DBUtil.open(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, date);

			ResultSet rs = pstmt.executeQuery();

			ArrayList<BoardDTO> list = new ArrayList<>();

			while (rs.next()) {

				BoardDTO dto = new BoardDTO();

				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setUserSeq(rs.getString("userSeq"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("BoardDAO.| findByRegdateBefore");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<BoardDTO> findByRegdateBetween(String startDate, String endDate) {

		final String SQL = "SELECT * FROM vwBoard WHERE boardRegdate BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') ORDER BY boardRegdate DESC";

		try (Connection conn = DBUtil.open(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);

			ResultSet rs = pstmt.executeQuery();

			ArrayList<BoardDTO> list = new ArrayList<>();

			while (rs.next()) {

				BoardDTO dto = new BoardDTO();

				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setUserSeq(rs.getString("userSeq"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("BoardDAO.| findByRegdateBetween");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<BoardDTO> findByTitleContains(String word) {

		final String SQL = "SELECT * FROM vwBoard WHERE boardTitle LIKE ? order by boardRegdate desc";

		try (Connection conn = DBUtil.open(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, "%" + word + "%");

			ResultSet rs = pstmt.executeQuery();

			ArrayList<BoardDTO> list = new ArrayList<>();

			while (rs.next()) {

				BoardDTO dto = new BoardDTO();

				dto.setBoardSeq(rs.getString("boardSeq"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setBoardContents(rs.getString("boardContents"));
				dto.setBoardRegdate(rs.getString("boardRegdate"));
				dto.setBoardReportCnt(rs.getString("boardReportCnt"));
				dto.setBoardCnt(rs.getString("boardCnt"));
				dto.setUserSeq(rs.getString("userSeq"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("BoardDAO.| findByTitleContains");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isReport(String boardSeq, String userSeq) {

		final String SQL = "select count(*) from tblBoardReport where boardSeq = ? and userSeq = ?";

		try (Connection conn = DBUtil.open(); PreparedStatement pstat = conn.prepareStatement(SQL);) {

			pstat.setString(1, boardSeq);
			pstat.setString(2, userSeq);

			ResultSet rs = pstat.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}

		} catch (Exception e) {
			System.out.println("BoardDAO.| isReport");
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public int whiteTotalCnt(HashMap<String, String> map) {

		String where = "";

		if (map.get("search").equals("y")) {

			where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
		}

		String sql = String.format("select count(*) as cnt from vwBoardWhite %s", where);

		try (Connection conn = DBUtil.open(); PreparedStatement pstat = conn.prepareStatement(sql);) {

			ResultSet rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}


	//  임시  > 외래키로 삭제 불가 작동X
	// 게시물 삭제 
	@Override
	public int remove(String boardSeq) {

		final String deleteCommentsSQL = "delete from tblBoardComments where boardSeq = ?";
		final String deleteBoardSQL = "delete from tblBoard where boardSeq = ?";

		try (Connection conn = DBUtil.open()) {
			conn.setAutoCommit(false);

			// 댓글 삭제
			try (PreparedStatement deleteCommentsStmt = conn.prepareStatement(deleteCommentsSQL)) {
				deleteCommentsStmt.setString(1, boardSeq);
				int commentsResult = deleteCommentsStmt.executeUpdate();

				// 댓글 삭제가 실패한 경우 롤백
				if (commentsResult <= 0) {
					conn.rollback();
					return 0;
				}
			}

			// 게시물 삭제
			try (PreparedStatement deleteBoardStmt = conn.prepareStatement(deleteBoardSQL)) {
				deleteBoardStmt.setString(1, boardSeq);
				int boardResult = deleteBoardStmt.executeUpdate();

				// 게시물 삭제가 실패한 경우 롤백
				if (boardResult <= 0) {
					conn.rollback();
					return 0;
				}
			}

			// 댓글과 게시물이 모두 정상적으로 삭제되었을 경우에만 커밋
			conn.commit();
			return 1;

		} catch (Exception e) {
			System.out.println("BoardDAO.| remove");
			e.printStackTrace();
			return 0;
		}
		
		
		
	}
	
	
	@Override
	public int findSeq(String userSeq) {
	    String SQL = "SELECT MAX(boardSeq) FROM tblBoard WHERE userSeq = ?";

	    try (
	        Connection conn = DBUtil.open();
	        PreparedStatement pstat = conn.prepareStatement(SQL);
	    ) {
	        pstat.setString(1, userSeq);
	        ResultSet rs = pstat.executeQuery();

	        if (rs.next()) {
	            return rs.getInt(1); // 1번째 열의 값을 정수형으로 가져옴
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return 0; // 값을 찾지 못한 경우 0 반환
	}
	
	@Override
	public BoardDTO findInfo(String freeSeq) {
	    String SQL = "SELECT * FROM tblBoard WHERE boardSeq = ?";
	    BoardDTO dto = null;

	    try (
	        Connection conn = DBUtil.open();
	        PreparedStatement pstat = conn.prepareStatement(SQL);
	    ) {
	        pstat.setString(1, freeSeq); // 매개변수 값 설정
	        ResultSet rs = pstat.executeQuery(); // executeQuery() 실행

	        if (rs.next()) { // 결과가 있을 경우
	            dto = new BoardDTO();
	            dto.setBoardSeq(rs.getString("boardSeq"));
	            dto.setBoardTitle(rs.getString("boardTitle"));
	            dto.setBoardContents(rs.getString("boardContents"));
	            dto.setBoardRegdate(rs.getString("boardRegdate"));
	            dto.setBoardCnt(rs.getString("boardCnt"));
	            dto.setUserSeq(rs.getString("userSeq"));
	            
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return dto;
	}
	
	 
	
	@Override
	public int saveEdit(BoardDTO dto) {
		
		final String SQL = "update tblBoard set boardTitle = ?, boardContents = ? where boardSeq = ?";
		

		try (

				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				

		) {

			conn.setAutoCommit(false);

			pstat.setString(1, dto.getBoardTitle());
			pstat.setString(2, dto.getBoardContents());
			pstat.setString(3, dto.getBoardSeq());
			

			int result = pstat.executeUpdate();

			if (result > 0) {
				
				return result;
			}

		} catch (Exception e) {
			System.out.println("BoardDAO.| set");
			e.printStackTrace();
		}

		return 0;
	}
	
	
	@Override
	public int freeDel(String boardSeq) {
		

		final String SQL = "delete from tblBoardWhiteList where boardSeq = ?";
	

		try (Connection conn = DBUtil.open();
			 PreparedStatement pstat = conn.prepareStatement(SQL);)
		{

			pstat.setString(1, boardSeq);

			int result = pstat.executeUpdate();


			return result;

		} catch (Exception e) {
			System.out.println("BoardDAO.| disable");
			e.printStackTrace();
		}

		return 0;
	}
}// End of class
