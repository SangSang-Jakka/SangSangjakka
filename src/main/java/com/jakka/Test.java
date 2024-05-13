package com.jakka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.jakka.model.DAOManager;
import com.jakka.model.DBUtil;
import com.jakka.model.dao.book.BookDAO;
import com.jakka.model.dao.user.UserDAO;
import com.jakka.model.dto.book.BookDTO;
import com.jakka.model.dto.user.UserDTO;
import com.jakka.model.enums.UserLog;

public class Test {
	
	private static Random rnd = new Random();
	
	public static void main(String[] args) {
		
		
		//동화책 리뷰 더미 생성
		//createReview();
		
		UserDAO userDAO = DAOManager.getUserDAO();
		
		String[] arr = userDAO.findGenreScore("6");
		
		for(int i =0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		
		
    }
	

	private static void createReview() {
		
		
		// 주어 배열
		final String[] SUB = {"책 속 주인공", "주인공의 모습", "주인공의 행동", "이야기 전개", "작가의 표현력", "캐릭터 설정", "배경 묘사", "메시지 전달", "상상력", "교훈", "스토리라인", "인물 성격", "대사", "분위기", "클라이맥스", "반전", "해피엔딩", "슬픈 결말", "일러스트레이션", "주제"};

		// 목적어 배열
		final String[] OBJ = {"마음에 들어요", "인상적이에요", "재미있어요", "흥미로워요", "멋져요", "감동적이에요", "새로워요", "풍부해요", "현실감 있어요", "탄탄해요", "귀여워요", "환상적이에요", "기발해요", "독창적이에요", "매력적이에요", "상세해요", "유머러스해요", "섬세해요", "그림같아요", "생동감 있어요"};

		// 서술어 배열
		final String[] VER = {"만족스러워요", "좋아해요", "와닿아요", "공감가요", "즐겁게 읽었어요", "계속 생각나요", "또 보고 싶어요", "시간 가는 줄 몰랐어요", "빠져들었어요", "인상 깊었어요", "대단해요", "놀라웠어요", "울컥했어요", "웃음 나왔어요", "잊지 못할 것 같아요", "꿈꾸게 해요", "배우게 되었어요", "감탄했어요", "마음이 따뜻해졌어요", "여운이 남아요"};
		
		final String SQL = "INSERT INTO tblReview (reviewSeq, reviewContents, userSeq, bookSeq, reviewRegdate) "
				+ "VALUES ((SELECT NVL(MAX(reviewSeq), 0) + 1 FROM tblReview), ?, ?, ?, ?)";
		
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) "
				+ "values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), ?, ?, ?, ?)";
		
		
		UserDAO userDAO = DAOManager.getUserDAO();
		BookDAO bookDAO = DAOManager.getBookDAO();
		
		ArrayList<UserDTO> userList = userDAO.findAllWhite();
		ArrayList<BookDTO> bookList = bookDAO.findAllWhite();
		
		for(BookDTO book : bookList) {
			
			String bookSeq = book.getBookSeq();
			Timestamp bookRegdate = Timestamp.valueOf(book.getBookRegdate()); 
			
			//랜덤으로 27명을 골라서 랜덤리뷰 작성
			for(int i = 0; i < 27; i++) {
				
				LocalDateTime randomDateTime = generateRandomDateTimeAfter(bookRegdate.toLocalDateTime());
            	Timestamp randomTimestamp = Timestamp.valueOf(randomDateTime);
				
				String review = SUB[rnd.nextInt(SUB.length)] + OBJ[rnd.nextInt(OBJ.length)] + VER[rnd.nextInt(VER.length)];
				String userSeq = userList.get(rnd.nextInt(userList.size())).getUserSeq();
				
				try (
						Connection conn = DBUtil.open();
						PreparedStatement pstat = conn.prepareStatement(SQL);
						PreparedStatement log = conn.prepareStatement(LOGSQL);	
					){
						conn.setAutoCommit(false);
						
						pstat.setString(1, review);
						pstat.setString(2, userSeq);
						pstat.setString(3, bookSeq);
						pstat.setTimestamp(4, randomTimestamp);

						int result = pstat.executeUpdate();
						
						if (result > 0) {
							log.setTimestamp(1, randomTimestamp);
							log.setString(2, userSeq);
							log.setString(3, "사용자번호'" + userSeq + "'이 부모글번호'" + bookSeq +"' 글내용'" + review  + "' 동화책 리뷰를 '작성'했습니다.");
							log.setString(4, UserLog.BookReviewCreated.getValue());
							log.executeUpdate();
						}
						
						conn.commit();
						
						System.out.println("랜덤 리뷰 작성완료");
						
					} catch (Exception e) {
						System.out.println("ReviewDAO.| add");
						e.printStackTrace();
					}
				
				
				
			}
			
			
		}//리뷰 종료
		
		
	}//createReview()
	
	private static LocalDateTime generateRandomDateTimeAfter(LocalDateTime dateTime) {
	    long startEpochSecond = dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
	    long endEpochSecond = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
	    long randomEpochSecond = startEpochSecond + rnd.nextInt((int) (endEpochSecond - startEpochSecond));
	    return LocalDateTime.ofEpochSecond(randomEpochSecond, 0, ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
	}
	
}//End of class

