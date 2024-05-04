package com.jakka;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.jakka.model.DBUtil;
import com.jakka.model.enums.AdminLog;
import com.jakka.model.enums.Inflow;
import com.jakka.model.enums.RecommendAge;
import com.jakka.model.enums.UserLog;
import com.jakka.model.enums.UserState;

public class Dummy {

	private static Random rnd = new Random();
	private static final List<String> USED_PHONE_NUMBERS = new ArrayList<>();
	
	public static void main(String[] args) {
		
		//더미 제작
		//1. 일반 관리자 5명 생성
		//addAdmin5();
		
		//2. 공지사항 생성
		// 10개의 공지사항 고정글3개
		//addNotice();
		
		//3. 사용자 계정 생성
		//addUser();
		
		//4. 건의사항 생성
		//addSuggestion();
		
		//5. 자유게시판 게시글 + 답변 생성
		addFreeboard();
		
	}
	
	
	
	private static void addFreeboard() {
		
		final String userlist = "select * from tblUser";
		
		//자유게시판 글
		final String boardSql = "";
		final String boardLogSql = "";
		final String boardReportSql = "";
		final String boardReportLogSql = "";
		final String boardWhiteSql = "";
		final String boardWhiteLogSql = "";
		
		//신고당한글중에 무고글 풀어주기
		final String boardactiveSql = "";
		final String boardactiveLogSql = "";
		
		//자유게시판 댓글
		final String cmmtSql = "";
		final String cmmtLogSql = "";
		final String cmmtReportSql = "";
		final String cmmtReportLogSql = "";
		final String cmmtWhiteSql = "";
		final String cmmtWhiteLogSql = "";
		
		//신고당한댓글중에 무고글 풀어주기
		final String cmmtactiveSql = "";
		final String cmmtactiveLogSql = "";
		
		try (
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(userlist);
			
			PreparedStatement board = conn.prepareStatement(userlist);
				
		){
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}




	private static void addSuggestion() {
		
		int sugCount = 0;
		
		final String[] ADMIN_RESPONSES = {
			    "귀하의 건의사항을 검토하였습니다. 제안해 주신 내용을 반영하도록 노력하겠습니다.",
			    "말씀해 주신 내용을 개발팀에 전달하여 향후 업데이트에 반영할 수 있도록 하겠습니다.",
			    "건의해 주신 기능 개선 및 추가 사항을 확인하였습니다. 개발 계획에 포함시켜 논의하겠습니다.",
			    "제안해 주신 디자인 변경 사항을 검토하였으며, 긍정적으로 고려하고 있습니다.",
			    "보안 강화를 위한 귀하의 제안 사항을 주의 깊게 살펴보았습니다. 적극 반영하도록 하겠습니다.",
			    "성능 개선을 위해 제시해 주신 의견을 참고하여 시스템 최적화 작업을 진행하겠습니다.",
			    "접근성 향상을 위한 귀하의 건의사항을 검토하였습니다. 향후 개선 계획에 포함시키겠습니다.",
			    "고객 경험 향상을 위해 제안해 주신 아이디어를 적극적으로 고려하고 있습니다.",
			    "새로운 서비스 제안에 대해 감사드립니다. 시장 조사 및 타당성 검토 후 결과를 알려드리겠습니다.",
			    "기존 서비스 변경 건의사항을 확인하였습니다. 개선 방안을 마련하여 적용하도록 하겠습니다.",
			    "커뮤니티 활성화를 위한 귀하의 의견을 귀담아 듣겠습니다. 실제 반영될 수 있도록 노력하겠습니다.",
			    "고객 지원 강화를 위해 제안해 주신 사항을 면밀히 검토하여 개선책을 마련하겠습니다.",
			    "결제 시스템 개선 제안에 대해 감사드립니다. 보안성과 편의성을 고려하여 반영하도록 하겠습니다.",
			    "귀하의 건의사항을 받아들여 모바일 앱 최적화 작업을 진행하겠습니다.",
			    "웹사이트 개편 제안을 적극 수용하여, 사용자 경험 향상을 위해 노력하겠습니다.",
			    "UI/UX 디자인 변경 요청을 검토하였으며, 전문가 의견을 수렴하여 반영하도록 하겠습니다.",
			    "데이터 관리 개선 방안에 대해 감사드립니다. 보안성과 효율성을 고려하여 적용하겠습니다.",
			    "개인정보 보호 강화 제안을 주의 깊게 살펴보았습니다. 관련 정책 및 시스템을 점검하겠습니다.",
			    "고객 의견 반영을 위해 노력하고 있습니다. 제안해 주신 사항을 충분히 고려하겠습니다.",
			    "광고 정책 변경 건의에 대해 관심 있게 검토하겠습니다. 사용자 경험 향상을 최우선으로 하겠습니다."
			};
		
		final String[] SUGGESTION_TITLES = {
			    "기능 개선 요청", "새로운 기능 추가 건의", "디자인 변경 제안", "사용성 향상 방안", "버그 수정 요청",
			    "보안 강화 제안", "성능 최적화 필요", "접근성 개선 방안", "사용자 경험 향상 방법", "서비스 품질 개선 아이디어",
			    "새로운 서비스 제안", "기존 서비스 변경 건의", "커뮤니티 활성화 방안", "고객 지원 강화 요청", "결제 시스템 개선 제안",
			    "모바일 앱 최적화 필요", "웹사이트 개편 제안", "UI/UX 디자인 변경 요청", "데이터 관리 개선 방안", "개인정보 보호 강화 제안",
			    "고객 의견 반영 요청", "광고 정책 변경 건의", "회원 등급 제도 개선 아이디어", "검색 기능 최적화 필요", "알림 시스템 개선 방안",
			    "SNS 연동 기능 추가 제안", "콘텐츠 관리 시스템 개선 요청", "분석 및 통계 기능 강화 필요", "사용자 피드백 반영 방안", "다국어 지원 확대 제안",
			    "친구 초대 기능 추가 요청", "게시판 기능 개선 아이디어", "계정 보안 강화 필요", "알고리즘 최적화 요청", "데이터 백업 시스템 구축 제안"
			};
		
		final String[] SUBJECTS = {"회원", "게시판", "서비스", "앱", "웹사이트", "기능", "디자인", "보안", "성능", "접근성"};
		final String[] OBJECTS = {"개선", "추가", "변경", "수정", "삭제", "강화", "확대", "축소", "통합", "분리"};
		final String[] VERBS = {"되었으면", "했으면", "할 수 있었으면", "좋겠다", "바랍니다", "필요합니다", "요청합니다", "건의합니다", "제안합니다", "권장합니다"};
		
		final String[] ADMIN = {"admin1", "admin2", "admin3", "admin4", "admin5"};
		
		final boolean[] SECRET = { true, true, false, false, false, false, false, false, false, false };
		
		final String USER = "select * from tblUser";
		
		final String sugg = "INSERT INTO tblSuggestion (sgstSeq, sgstTitle, sgstContents, sgstRegdate, sgstSecretYN, userSeq, sgstCnt) "
				+ "VALUES ((SELECT NVL(MAX(sgstSeq), 0) + 1 FROM tblSuggestion), ?, ?, ?, ?, ?, ?)";
		
		final String userlog = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) "
				+ "values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), ?, ?, ?, ?)";
		
		final String answ = "insert into tblSuggestionAnswer(answSeq, adId, sgstSeq, sgstAnsw, sgstRegdate) "
				+ "values((SELECT NVL(MAX(answSeq), 0) + 1 FROM tblSuggestionAnswer), ?, ?, ?, ?)";
		
		final String adminlog = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) "
				+ "values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), ?, ?, ?, ?)";
		
		try (
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
				
			PreparedStatement pstat = conn.prepareStatement(sugg);
			PreparedStatement userpstat = conn.prepareStatement(userlog);
				
			PreparedStatement answpstat = conn.prepareStatement(answ);
			PreparedStatement adminpsat = conn.prepareStatement(adminlog);
				
			ResultSet rs = stat.executeQuery(USER);
		){
			
			while(rs.next()) {
				
				String userSeq = rs.getString("userSeq");
                Timestamp userRegdate = rs.getTimestamp("userRegdate");
				
                //건의사항 0 ~ 5개 사이 작성
                for(int i = 0; i < rnd.nextInt(6); i++) {
                	
                	String title = SUGGESTION_TITLES[rnd.nextInt(SUGGESTION_TITLES.length)];
                	String contents = SUBJECTS[rnd.nextInt(SUBJECTS.length)] + OBJECTS[rnd.nextInt(OBJECTS.length)] + VERBS[rnd.nextInt(VERBS.length)];
                	
                	LocalDateTime randomDateTime = generateRandomDateTimeAfter(userRegdate.toLocalDateTime());
                	Timestamp randomTimestamp = Timestamp.valueOf(randomDateTime);
                	
                	pstat.setString(1, title);
                	pstat.setString(2, contents);
                	pstat.setTimestamp(3, randomTimestamp);
                	pstat.setString(4, SECRET[rnd.nextInt(SECRET.length)] ? "y" : "n");	//일반글 : 비밀글 = 8 : 2
                	pstat.setString(5, userSeq);
                	pstat.setString(6, rnd.nextInt(6) + "");
                	
                	pstat.executeUpdate();
                	
                	//로그 추가
                	//날짜 사용자번호, 내용, 카테고리
                	userpstat.setTimestamp(1, randomTimestamp);
                	userpstat.setString(2, userSeq);
                	userpstat.setString(3, "사용자번호'" + userSeq + "'이 글제목'" + title + "' 글내용'" + contents + "' 건의사항을 '작성'했습니다.");
                	userpstat.setString(4, UserLog.SuggestionCreated.getValue());
                	
                	userpstat.executeUpdate();
                	
                	sugCount += 1;
                	
                	if(rnd.nextBoolean()) {
                		
                		LocalDateTime answerDateTime = generateRandomDateTimeAfter(randomTimestamp.toLocalDateTime());
                    	Timestamp anwserTimestamp = Timestamp.valueOf(answerDateTime);
                		
                		String answer = ADMIN_RESPONSES[rnd.nextInt(ADMIN_RESPONSES.length)];
                		
                		String adId = ADMIN[rnd.nextInt(ADMIN.length)];
                		
                		answpstat.setString(1, adId);
                		answpstat.setString(2, sugCount + "");
                		answpstat.setString(3, answer);
                		answpstat.setTimestamp(4, anwserTimestamp);
                		
                		answpstat.executeUpdate();
                		
                		adminpsat.setTimestamp(1, anwserTimestamp);
                		adminpsat.setString(2, adId);
                		adminpsat.setString(3, "관리자'" + adId + "'이 글번호'" + sugCount + "'에 '" + answer + "'건의사항 답변을 '작성'했습니다.");
                		adminpsat.setString(4, AdminLog.SuggestionAnswered.getValue());

                		adminpsat.executeUpdate();
                	}
                	
                	
                }
				
				
			}//유저 검색 종료
			
			System.out.println("건의사항 + 답변 + 로그완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static LocalDateTime generateRandomDateTimeAfter(LocalDateTime dateTime) {
	    long startEpochSecond = dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
	    long endEpochSecond = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
	    long randomEpochSecond = startEpochSecond + rnd.nextInt((int) (endEpochSecond - startEpochSecond));
	    return LocalDateTime.ofEpochSecond(randomEpochSecond, 0, ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
	}

	private static void addUser() {
		
		final String[] LASTEMAIL = {"@naver.com", "@daum.net", "@gmail.com", "@nate.com"};
		
		final String[] INFLOW = {Inflow.ACQUAINTANCE.getValue(), Inflow.BLOG.getValue(), Inflow.CAFFE.getValue(), Inflow.ECT.getValue()
								, Inflow.FLYER.getValue(), Inflow.Internet_Search.getValue(), Inflow.SOCIAL_MEDIA_PLATFORM.getValue()};
		
		final String[] LASTNAMES = {
			    "김", "이", "박", "최", "정", "강", "조", "윤", "장", "임",
			    "한", "오", "서", "신", "권", "황", "안", "송", "류", "전",
			    "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유",
			    "남", "심", "노", "정", "하", "곽", "성", "차", "주", "구",
			    "우", "신", "임", "나", "전", "민", "유", "진", "지", "엄",
			    "채", "원", "천", "방", "공", "강", "현", "함", "추", "노",
			    "소", "식", "재", "위", "석", "근", "렬", "운", "모", "배",
			    "신", "왕", "제", "연", "방", "옥", "육", "인", "마", "초",
			    "류", "잔", "우", "무", "류", "육", "최", "서", "아", "권"
			};
		
		final String[] NAMES = {
			    "민수", "지훈", "수진", "지은", "서연", "서윤", "하늘", "예진", "서현", "유나",
			    "준서", "하은", "지우", "세진", "재민", "민정", "소율", "지아", "지선", "윤서",
			    "서진", "서현", "채원", "예은", "예림", "유진", "지후", "은서", "은주", "소연",
			    "지원", "지안", "규민", "지호", "민서", "서윤", "도현", "수빈", "수연", "민재",
			    "주안", "지율", "서현", "하영", "윤재", "지유", "지민", "서준", "지환", "수아",
			    "수민", "예은", "지아", "서연", "하은", "지원", "준우", "지우", "지민", "서윤",
			    "민규", "지현", "하윤", "지혜", "서진", "서현", "주원", "예진", "지후", "유진",
			    "준호", "서윤", "하윤", "예진", "지우", "유나", "지윤", "준혁", "지호", "예은",
			    "민재", "예빈", "서현", "주원", "지윤", "지민", "예린", "지아", "하은", "예은",
			    "수아", "주원", "지안", "민준", "지호", "민서", "예진", "수민", "주안", "하윤"
			};
		
		 final String[] ADDRESSES = {"서울시 강남구 역삼동", "부산시 해운대구 우동", "인천시 계양구 작전동", "대구시 수성구 범물동", "광주시 서구 치평동",
			       "대전시 유성구 봉명동", "울산시 남구 무거동", "경기도 성남시 분당구 정자동", "강원도 원주시 무실동", "충청북도 청주시 흥덕구 가경동"
		 };
		
		String sql = "INSERT INTO tblUser (userSeq, userId, userPw, userName, userNick, userTel, userAddress, userEmail, userLeftSsn, userRightSsn, userState, userLv, userRegdate, LimitStorage) "
				+ "VALUES ((SELECT NVL(MAX(userSeq), 0) + 1 FROM tblUser), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, default, ?, default)";
		
		String log = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq)"
				+ "values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), ?, ?, ?, ?)";
		
		String inflow = "insert into tblUserInflow(userSeq, inflowCatSeq)"
				+ "values(?, ?)";
		
		String child = "insert into tblChildAge(childAgeSeq, childBirth, userSeq, ageCatSeq)"
				+ "values((SELECT NVL(MAX(childAgeSeq), 0) + 1 FROM tblChildAge), ?, ?, ?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(sql);
			PreparedStatement plog = conn.prepareStatement(log);
			PreparedStatement pinflow = conn.prepareStatement(inflow);
			PreparedStatement pchild = conn.prepareStatement(child);
		){
			//100명의 계정 생성
			for(int i = 0; i < 100; i++) {
				
				// 랜덤 날짜 및 시간 생성
				long minDay = LocalDate.of(2023, 1, 1).toEpochDay();
				long maxDay = LocalDate.of(2024, 1, 1).toEpochDay();
				long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
				LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
			    LocalTime randomTime = LocalTime.of(rnd.nextInt(24), rnd.nextInt(60), rnd.nextInt(60));
			    LocalDateTime regDateTime = LocalDateTime.of(randomDate, randomTime);
			    
			    // Timestamp로 변환
			    Timestamp regdatetimestamp = Timestamp.valueOf(regDateTime);
				
			    String id = String.format("user%04d", i);
			    
			    // 랜덤 생년월일 생성
		        int year = rnd.nextInt(56) + 1965; // 1965 ~ 2020년 사이
		        int month = rnd.nextInt(12) + 1;
		        int day = rnd.nextInt(28) + 1; // 일수는 28일까지만 고려

		        boolean isMale = rnd.nextBoolean(); // 성별 (true: 남자, false: 여자)

		        String ssn = generateRandomSsn(year, month, day, isMale);
		        String ssnFront = ssn.substring(0, 6);
		        String ssnBack = ssn.substring(6);
			    String name = LASTNAMES[rnd.nextInt(LASTNAMES.length)] + NAMES[rnd.nextInt(NAMES.length)];
		        
		        
				pstat.setString(1, id);
				pstat.setString(2, "1111");
				pstat.setString(3, name);
				pstat.setString(4, id + "NICK");
				pstat.setString(5, generateUniquePhoneNumber());
				pstat.setString(6, ADDRESSES[rnd.nextInt(ADDRESSES.length)]);
				pstat.setString(7, id + LASTEMAIL[rnd.nextInt(LASTEMAIL.length)]);
				pstat.setString(8, ssnFront);
		        pstat.setString(9, ssnBack);
		        pstat.setString(10, UserState.ACTIVE.getValue());
				pstat.setTimestamp(11, regdatetimestamp);
			    
				pstat.executeUpdate();
				
				createUserFolder(id);
				
				//로그 추가
				plog.setTimestamp(1, regdatetimestamp);
				plog.setString(2, (i + 1) + "");
				plog.setString(3, "사용자'" + name + "'님이 아이디'" + id +"' 닉네임'" + id + "NICK" + "'으로  '회원가입'했습니다.");
				plog.setString(4, UserLog.SignUp.getValue());
				
				plog.executeUpdate();
				
				//유입경로 추가
				pinflow.setString(1, (i + 1) + "");
				pinflow.setString(2, INFLOW[rnd.nextInt(INFLOW.length)]);
				pinflow.executeUpdate();
				
				//자녀 추가
				if(Integer.parseInt(ssnFront.substring(0, 2)) >= 65 && Integer.parseInt(ssnFront.substring(0, 2)) <= 98 && rnd.nextBoolean()) {
					
					int childYear = rnd.nextInt(20) + 2004; // 2004년부터 2023년 사이의 랜덤 출생년도
				    int childMonth = rnd.nextInt(12) + 1;
				    int childDay = rnd.nextInt(28) + 1;
				    
				    String birthDate = String.format("%04d%02d%02d", childYear, childMonth, childDay);
				    
				    pchild.setString(1, birthDate);
				    pchild.setString(2, (i + 1) + "");
				    pchild.setString(3, RecommendAge.ODER_5YEARS.getValue());
				    pchild.executeUpdate();
				}
				
			}
			

		    
			System.out.println("사용자 생성 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void addNotice() {
		
		String[] adminId = {"admin1", "admin2", "admin3", "admin4", "admin5", "super"};
		
		String[] titles = {
				"생성형 AI를 활용한 동화책 제작 가이드",
				"동화책 공유 이벤트 안내",
				"홈페이지 업데이트 안내",
				"저작권 및 윤리 규정 안내",
				"실물 동화책 제작 서비스 오픈",
				"신규 회원 가입 혜택 안내",
				"우수 동화책 작가 인터뷰",
				"동화책 검색 기능 개선",
				"어린이날 기념 이벤트",
				"명예의 전당 동화책 발표"
				};
		
		
		String[] contents = {
				"안녕하세요 회원 여러분! 이번 공지사항에서는 저희 사이트에서 제공하는 생성형 AI를 활용하여 동화책을 쉽고 재미있게 제작하는 방법에 대해 안내 드리겠습니다. 자세한 내용은 사이트의 동화책 제작 메뉴에서 확인하실 수 있습니다.",
				"창의력 넘치는 회원 여러분들이 만든 멋진 동화책을 다른 회원들과 공유해 보세요! 이번 달 말일까지 공유된 동화책 중 좋아요를 가장 많이 받은 분들께는 특별한 선물을 드립니다. 많은 참여 부탁드립니다.",
				"더 나은 서비스 제공을 위해 홈페이지 업데이트를 진행합니다. 업데이트 기간 동안에는 일부 서비스 이용에 제한이 있을 수 있습니다. 회원 여러분의 양해 부탁드리며, 업데이트 이후 더욱 편리한 동화책 제작 및 공유 기능을 만나보실 수 있을 것입니다.",
				"저희 사이트는 창작자의 저작권을 존중합니다. 동화책 제작 시 타인의 저작권을 침해하거나 부적절한 내용을 포함하지 않도록 주의해 주시기 바랍니다. 관련 규정을 위반한 경우 경고 및 제재 조치가 취해질 수 있습니다. 건전한 창작 활동을 위한 회원 여러분의 협조 부탁드립니다.",
				"많은 분들이 기다려 주신 실물 동화책 제작 서비스가 드디어 오픈합니다! 이제 내가 만든 동화책을 실제 책으로 만나볼 수 있습니다. 자세한 내용은 마이페이지의 실물 동화책 제작 메뉴에서 확인해 주세요.",
				"저희 사이트에 새롭게 가입한 회원분들을 위한 특별 혜택을 준비했습니다. 가입 후 첫 동화책 제작 시 사용할 수 있는 무료 이미지 생성 쿠폰과 포인트를 드립니다. 지금 바로 가입하고 혜택을 누려보세요!",
				"이번 달의 우수 동화책 작가로 선정된 OOO 님을 만나봤습니다. 창의적인 동화책 제작 노하우와 생성형 AI 활용 팁 등 알찬 내용으로 가득한 인터뷰를 공지사항을 통해 만나보실 수 있습니다.",
				"원하는 동화책을 더욱 쉽고 빠르게 찾을 수 있도록 검색 기능을 개선했습니다. 이제 키워드, 작가, 장르 등 다양한 조건으로 검색할 수 있으며, 검색 결과의 정확도도 높아졌습니다. 회원 여러분의 많은 이용 바랍니다.",
				"어린이날을 맞이하여 특별한 이벤트를 준비했습니다. 어린이날 기념 동화책 제작 챌린지에 참여하신 모든 분께 푸짐한 상품을 드립니다. 자세한 내용은 이벤트 페이지에서 확인하실 수 있습니다. 많은 참여 부탁드립니다!",
				"지난 한 달 동안 회원 여러분의 사랑을 받은 최고의 동화책을 발표합니다. 명예의 전당에 오른 동화책 작가분들께 진심으로 축하의 말씀을 전합니다. 앞으로도 많은 관심과 참여 부탁드리며, 다음 달에도 멋진 동화책으로 찾아뵙겠습니다."
				};
		
		String sql = "insert into tblNotice(noticeSeq, noticeTitle, noticeContents, noticeRegdate, noticeCnt, adId)"
				+ "values(?, ?, ?, ?, ?, ?)";
		
		String fix = "insert into tblNoticeFix(noticeSeq) values(?)";
		
		String log = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) "
				+ "values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), ?, ?, ?, ?)";
		
		try (
				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(sql);
				PreparedStatement fixsql = conn.prepareStatement(fix);
				PreparedStatement logsql = conn.prepareStatement(log);
		){
			conn.setAutoCommit(false);
			
			for(int i = 0; i < 10; i++) {
				
				String adId = adminId[rnd.nextInt(6)];
				
				// 랜덤 날짜 및 시간 생성
				long minDay = LocalDate.of(2023, 1, 1).toEpochDay();
				long maxDay = LocalDate.of(2024, 1, 1).toEpochDay();
				long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
				LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
			    LocalTime randomTime = LocalTime.of(rnd.nextInt(24), rnd.nextInt(60), rnd.nextInt(60));
			    LocalDateTime noticeDateTime = LocalDateTime.of(randomDate, randomTime);

			    // Timestamp로 변환
			    Timestamp noticeTimestamp = Timestamp.valueOf(noticeDateTime.toLocalDate().toString() + " " + noticeDateTime.toLocalTime().toString());
			    System.out.println(noticeTimestamp);
			    
				pstat.setString(1, i + 1 + "");
				pstat.setString(2, titles[i]);
				pstat.setString(3, contents[i]);
				pstat.setTimestamp(4, noticeTimestamp);
				pstat.setString(5, (rnd.nextInt(91) + 10) + "");
				pstat.setString(6, adId);
				pstat.executeUpdate();
				
				logsql.setTimestamp(1, noticeTimestamp);
				logsql.setString(2, adId);
				logsql.setString(3, "관리자'" + adId + "'이 글제목'" + titles[i]  + "' 글내용'" + contents[i] + "' 공지사항을 '작성'했습니다.");
				logsql.setString(4, AdminLog.NoticeCreated.getValue());
				logsql.executeUpdate();
				
				if(rnd.nextBoolean()) {
					
					fixsql.setString(1, i + 1 + "");
					fixsql.executeUpdate();
					
					LocalDateTime fixedDateTime = noticeDateTime.plusMinutes(rnd.nextInt(60));
					Timestamp fixedTimestamp = Timestamp.valueOf(fixedDateTime.toLocalDate().toString() + " " + fixedDateTime.toLocalTime().toString());
				    
					logsql.setTimestamp(1, fixedTimestamp);
					logsql.setString(2, adId);
					logsql.setString(3, "관리자'" + adId + "'이 글번호'" + (i + 1) + "' 공지사항을 '고정'했습니다.");
					logsql.setString(4, AdminLog.NoticeFixed.getValue());
					logsql.executeUpdate();
					
				}
				
			}
			
			System.out.println("랜덤 날짜 공지사항 + 공지사항 고정 + 로그");
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void addAdmin5() {
		
		String[] names = {"김철수", "이영희", "박민수", "최지웅", "황진이"};
		String[] addresses = {"서울특별시 강남구 역삼동", "경기도 수원시 권선구", "인천광역시 부평구", "대전광역시 서구", "부산광역시 해운대구"};
		String[] phones = {"010-1234-5678", "010-9876-5432", "010-5555-1234", "010-4321-8765", "010-7890-2345"};
		
		String sql = "INSERT INTO tblAdmin (adId, adPw, adName, adNick, adAddress, adTel, adLv) " +
                "VALUES (?, default, ?, ?, ?, ?, default)";
				
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(sql);
		){
			
			for(int i = 0; i < 5; i++) {
				
				pstat.setString(1, "admin" + (i + 1));
				pstat.setString(2, names[i]);
				pstat.setString(3, "관리자" + (i + 1));
				pstat.setString(4, addresses[i]);
				pstat.setString(5, phones[i]);
				
				pstat.executeUpdate();
				
			}
			
			System.out.println("관리자 5명 추가");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private static void createUserFolder(String userId) {
		
		final String BASE_DIRECTORY = "src/main/webapp/generated/";
		
		String projectDirectory = System.getProperty("user.dir");
        String folderPath = projectDirectory + File.separator + BASE_DIRECTORY + userId;
        File userFolder = new File(folderPath);

        if (!userFolder.exists()) {
            boolean created = userFolder.mkdirs();
            if (created) {
                System.out.println("User folder created: " + folderPath);
            } else {
                System.err.println("Failed to create user folder: " + folderPath);
            }
        } else {
            System.out.println("User folder already exists: " + folderPath);
        }
		
	}
	
	private static String generateUniquePhoneNumber() {
	       String phoneNumber;
	       do {
	           phoneNumber = "010-" + String.format("%04d-%04d", rnd.nextInt(10000), rnd.nextInt(10000));
	       } while (USED_PHONE_NUMBERS.contains(phoneNumber));

	       USED_PHONE_NUMBERS.add(phoneNumber);
	       return phoneNumber;
	}
	
	private static String generateRandomSsn(int year, int month, int day, boolean isMale) {
	    StringBuilder ssn = new StringBuilder();

	    // 생년월일 6자리
	    ssn.append(String.format("%02d", year % 100));
	    ssn.append(String.format("%02d", month));
	    ssn.append(String.format("%02d", day));

	    // 성별 코드 (1: 1900년대 남자, 2: 1900년대 여자, 3: 2000년대 남자, 4: 2000년대 여자)
	    int genderCode = isMale ? (year < 2000 ? 1 : 3) : (year < 2000 ? 2 : 4);
	    ssn.append(genderCode);

	    // 뒷자리 6자리 (000001 ~ 999999)
	    int lastDigits = rnd.nextInt(1000000);
	    ssn.append(String.format("%06d", lastDigits));

	    return ssn.toString();
	}
	
}


