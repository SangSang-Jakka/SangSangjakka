package com.jakka.model.dao.user;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.user.UserDTO;
import com.jakka.model.enums.UserLog;
import com.jakka.model.enums.UserState;

public class UserDAOImpl implements UserDAO{

	private final static UserDAOImpl DAO = new UserDAOImpl();
	
	private UserDAOImpl() {
		//외부 생성 방지
	}

	public static UserDAOImpl getInstance() {
		return DAO;
	}//getInstance()
	

	public boolean unRegister(UserDTO dto) {
		
		final String SQL = "update tblUser set userState = 'n' where userSeq = ?";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
		){
			conn.setAutoCommit(false);
			
			
			pstat.setString(1, dto.getUserSeq());
			pstat.setString(2, dto.getUserPw());
			
			int result = pstat.executeUpdate();
			
			if (result > 0) {
				log.setString(1, dto.getUserSeq());
				log.setString(2, "사용자번호'" + dto.getUserSeq() + "' 아이디'" + dto.getUserId() + "'" + "이름'" + dto.getUserName() + "'님이 '회원탈퇴'했습니다.");
				log.setString(3, UserLog.Withdrawal.getValue());
				log.executeUpdate();
			}
			
			conn.commit();
			
			return result > 0;
			
			
		} catch (Exception e) {
			System.out.println("UserDAOImpl.unRegister");
			e.printStackTrace();
			return false;
		}
		
	}
	// 비밀번호찾기
	public UserDTO findPw(UserDTO dto) {
		// 아이디와 주민번호 앞자리의 조건이 맞는 사용자 찾기, 그리고 userSeq를 반환해서
		// 비밀번호를 변경할 때 해당 사용자의 비밀번호를 변경하기 위해 userSeq를 추출하는 로직
		String SQL = "select userSeq from tblUser where userId = ? and userleftSsn = ?";
		
		try {

			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			pstat.setString(1, dto.getUserId());
			pstat.setString(2, dto.getUserLeftSsn());
			
			// 쿼리 작업 후 결과를 rs에 반환
			ResultSet rs = pstat.executeQuery();
			// 일치하는 유저가 저장된 rs의 값이 존재하면
			if(rs.next()) {
				UserDTO result = new UserDTO();
				result.setUserSeq(rs.getString("userSeq"));
				rs.close();
				
				return result;
			} 
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 아이디 찾기
	public UserDTO findId(UserDTO dto) {
		String SQL = "select userId, userRegdate from tblUser where userNick = ? and userEmail = ?";
		
		try {
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			// nick과 email을 ?에 할당
			pstat.setString(1, dto.getUserNick());
			pstat.setString(2, dto.getUserEmail());
			
			// 결과 반환
			ResultSet rs = pstat.executeQuery();
			UserDTO result = new UserDTO();
			
			// 결과값이 있으면
			if(rs.next()) {
				// DTO타입의 참조변수 result
				// 쿼리를 실행한 결과를 가져와서 result에 쓰기
				result.setUserId(rs.getString("userId"));
				result.setUserRegdate(rs.getString("userRegdate"));
				System.out.println(result);

				}
				rs.close();
				
				
			// result에 아이디를 찾은 결과를 보여줄 아이디와 가입일을 쓰기, 쓴걸 들고 result에 들고감
			// result 반환
			return result;
			
		} catch (Exception e) {
			System.out.println("UserDAOImpl.findId");
			e.printStackTrace();
		}
		return null;
	}
	
	//로그인 로그 작성
	@Override
	public void loginLog(String userSeq) {
		
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) "
				+ "values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(LOGSQL);
		){
			
			pstat.setString(1, userSeq);
			pstat.setString(2, "사용자번호'" + userSeq + "'님이 '로그인'했습니다.");
			pstat.setString(3, UserLog.Login.getValue());
			pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("UserDAOImpl.| loginLog");
			e.printStackTrace();
		}
		
	}
	
	// 로그인
	public UserDTO login(UserDTO dto) {
		
		String SQL = "select * from tblUser where userId = ? and userPw = ? and userState = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL); 
		){
			pstat.setString(1, dto.getUserId());
			pstat.setString(2, dto.getUserPw());
			pstat.setString(3, UserState.ACTIVE.getValue());
			
			System.out.println(dto.getUserId());
			System.out.println(dto.getUserPw());
			
			ResultSet rs = pstat.executeQuery();
			
			UserDTO result = new UserDTO();

			if(rs.next()) {
				
				result.setUserId(rs.getString("userId"));
				result.setUserNick(rs.getString("userNick"));
				result.setUserLV(rs.getString("userLV"));
				result.setUserSeq(rs.getString("userSeq"));
				
			}

			
			rs.close();
			return result;

			
		} catch (Exception e) {
			System.out.println("UserDAOImpl.login");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int add(UserDTO dto) {
		
		final String SQL = "INSERT INTO tblUser (userSeq, userId, userPw, userName, userNick, userTel, userAddress, userEmail, userLeftSsn, userRightSsn, userState, userLv, userRegdate, LimitStorage) VALUES ((SELECT NVL(MAX(userSeq), 0) + 1 FROM tblUser), ?, ?, ?, ?, ?, ?, ?, ?, ?, default, default, SYSDATE, default)";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, dto.getUserId());
			pstat.setString(2, dto.getUserPw());
			pstat.setString(3, dto.getUserName());
			pstat.setString(4, dto.getUserNick());
			pstat.setString(5, dto.getUserTel());
			pstat.setString(6, dto.getUserAddress());
			pstat.setString(7, dto.getUserEmail());
			pstat.setString(8, dto.getUserLeftSsn());
			pstat.setString(9, dto.getUserRightSsn());
			
			int result = pstat.executeUpdate();
			
			createUserFolder(dto.getUserId());
			
			return result;
			
		} catch (Exception e) {
			System.out.println("UserDAO.| get");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public UserDTO findById(String id) {
		
		final String SQL = "select * from tblUser where userId = ?";
		
		try (
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
			){
			
			pstat.setString(1, id);
			
			ResultSet rs = pstat.executeQuery();
			
			
			if (rs.next()) {
				
				UserDTO dto = new UserDTO();
				
				dto.setLimitStorage(rs.getString("limitStorage"));
				dto.setUserAddress(rs.getString("userAddress"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserLeftSsn(rs.getString("userLeftSsn"));
				dto.setUserLV(rs.getString("userLv"));
				dto.setUserNick(rs.getString("userNick"));
				dto.setUserRegdate(rs.getString("userRegdate"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserState(rs.getString("userState"));
				dto.setUserTel(rs.getString("userTel"));
				dto.setUserName(rs.getString("userName")); 
				
				rs.close();
				
				return dto;
			}
			
			
		} catch (Exception e) {
			System.out.println("UserDAO.| get");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ArrayList<UserDTO> findAll() {
		
		final String SQL = "select * from tblUser order by userRegdate desc";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<UserDTO> list = new ArrayList<>();
			
			 for (ResultSet row = rs; row.next(); ) {
				
				UserDTO dto = new UserDTO();
				
				dto.setUserAddress(rs.getString("userAddress"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserLeftSsn(rs.getString("userLeftSsn"));
				dto.setLimitStorage(rs.getString("limitStorage"));
				dto.setUserLV(rs.getString("userLv"));
				dto.setUserNick(rs.getString("userNick"));
				dto.setUserRegdate(rs.getString("userRegdate"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserState(rs.getString("userState"));
				dto.setUserTel(rs.getString("userTel"));
				dto.setUserName(rs.getString("userName"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| listAll");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public ArrayList<UserDTO> findAllBlack() {
		
		final String SQL = "select * from tblUser where userState = 'n'";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<UserDTO> list = new ArrayList<>();
			
			 for (ResultSet row = rs; row.next(); ) {
				
				UserDTO dto = new UserDTO();
				
				dto.setUserAddress(rs.getString("userAddress"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserLeftSsn(rs.getString("userLeftSsn"));
				dto.setLimitStorage(rs.getString("limitStorage"));
				dto.setUserLV(rs.getString("userLv"));
				dto.setUserNick(rs.getString("userNick"));
				dto.setUserRegdate(rs.getString("userRegdate"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserState(rs.getString("userState"));
				dto.setUserTel(rs.getString("userTel"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| listDisabled");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public ArrayList<UserDTO> findAllWhite() {
		
		final String SQL = "select * from tblUser where userState = 'y'";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<UserDTO> list = new ArrayList<>();
			
			 for (ResultSet row = rs; row.next(); ) {
				
				UserDTO dto = new UserDTO();
				
				dto.setUserAddress(rs.getString("userAddress"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserLeftSsn(rs.getString("userLeftSsn"));
				dto.setLimitStorage(rs.getString("limitStorage"));
				dto.setUserLV(rs.getString("userLv"));
				dto.setUserNick(rs.getString("userNick"));
				dto.setUserRegdate(rs.getString("userRegdate"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserState(rs.getString("userState"));
				dto.setUserTel(rs.getString("userTel"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| listDisabled");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	//닉네임, 전화번호, 주소, 이메일
		@Override
		public int save(UserDTO dto) {
			
			final String SQL = "UPDATE tblUser SET userNick = ?, userTel = ?, userAddress = ?, userEmail = ? WHERE userId = ?";
			
			try (
				Connection conn = DBUtil.open();
			    PreparedStatement pstat = conn.prepareStatement(SQL)
			) {

			        pstat.setString(1, dto.getUserNick());
			        pstat.setString(2, dto.getUserTel());
			        pstat.setString(3, dto.getUserAddress());
			        pstat.setString(4, dto.getUserEmail());
			        pstat.setString(5, dto.getUserId());

			        int result = pstat.executeUpdate();
			        
			        return result;
			        
			    } catch (Exception e) {
			        System.out.println("UserDAO.| save");
			        e.printStackTrace();
			        
			    }
			    
			    return 0;
		}

	
	//용량 변경
	@Override
	public int saveStorage(UserDTO dto, String adId) {
		
		final String SQL = "UPDATE tblUser SET limitStorage = ? WHERE userId = ?";
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, 3)";
		
		try (
			Connection conn = DBUtil.open();
		    PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
		) {

				conn.setAutoCommit(false);
			
				pstat.setString(1, dto.getLimitStorage());
		        pstat.setString(2, dto.getUserId());
		        
		        int result = pstat.executeUpdate();
		        
		        if (result > 0) {
		            log.setString(1, adId);
		            log.setString(2, "'" + adId + "'이 사용자번호 '" + dto.getUserSeq() + "' 의 저장소 공간을 '" + dto.getLimitStorage() + "byte'로 변경했습니다.");
		            log.executeUpdate();
		        }

		        
		        
		        conn.commit();
		        
		        return result;
		        
		    } catch (Exception e) {
		        System.out.println("UserDAO.| saveStorage");
		        e.printStackTrace();
		        
		    }
		    
		    return 0;
	}
	
	//비밀번호 변경
	public int savePw(UserDTO dto) {
		
		final String SQL = "update tblUser set userPw = ? where userSeq = ?";
		
		try(
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
			) {
			
			pstat.setString(1, dto.getUserPw());
			pstat.setString(2, dto.getUserSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("UserDAO.| setPw");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int disable(String userSeq, String adId) {
		
		final String SQL = "insert into tblBlackList(userSeq) values(?)";
		
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, 1)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);	
		){
			conn.setAutoCommit(false);
			
			pstat.setString(1, userSeq);
			
			int result = pstat.executeUpdate();
			
			if(result > 0) {
				
				log.setString(1, adId);
				log.setString(2, "'" + adId + "'이 사용자번호 '" + userSeq + "' 을(를) '비활성화' 했습니다.");
				log.executeUpdate();
			}
			
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("UserDAO.| disable");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int activation(String userSeq, String adId) {
		
		final String SQL = "delete from tblBlackList where userSeq = ?";
		final String LOGSQL = "insert into tblAdLog(adLogSeq, adLogDate, adId, adLogContents, adCatSeq) values((SELECT NVL(MAX(adLogSeq), 0) + 1 FROM tblAdLog), default, ?, ?, 2)";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			PreparedStatement log = conn.prepareStatement(LOGSQL);
				
		){
			conn.setAutoCommit(false);
			
			pstat.setString(1, userSeq);
			
			int result = pstat.executeUpdate();
			
			if(result > 0) {
				
				log.setString(1, adId);
				log.setString(2, "'" + adId + "'이 사용자번호 '" + userSeq + "' 을(를) '활성화' 했습니다.");
				log.executeUpdate();
			}
			
			
			
			conn.commit();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("UserDAO.| disable");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public String findSeq(String id) {
		
		final String SQL = "select userSeq from tblUser where userId = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			pstat.setString(1, id);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getString("userSeq");
						
			}
			
			
		} catch (Exception e) {
			System.out.println("UserDAOImpl.| findSeq");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void createUserFolder(String userId) {
		
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


	@Override
	public int signUp(UserDTO dto) {
		
		
		
		final String SQL = "INSERT INTO tblUser (userSeq, userId, userPw, userNick, userTel, userAddress, userEmail, userLeftSsn, userRightSsn, userState, userLv, userRegdate, limitStorage,userName) VALUES ((SELECT NVL(MAX(userSeq), 0) + 1 FROM tblUser),?, ?, ?, ?, ?, ?, ?, ?, 'y', 1, SYSDATE, 10737418240,?)";
		final String LOGSQL = "insert into tblUserLog(userLogSeq, userLogDate, userSeq, userLogContents, userCatSeq) values((SELECT NVL(MAX(userLogSeq), 0) + 1 FROM tblUserLog), default, ?, ?, ?)";
		
		try (
				Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				//PreparedStatement log = conn.prepareStatement(LOGSQL);
				
					
			){
				//conn.setAutoCommit(false);

				pstat.setString(1, dto.getUserId());
				pstat.setString(2, dto.getUserPw());
				pstat.setString(3, dto.getUserNick());
				pstat.setString(4, dto.getUserTel());
				pstat.setString(5, dto.getUserAddress());
				pstat.setString(6, dto.getUserEmail());
				pstat.setString(7, dto.getUserLeftSsn());
				pstat.setString(8, dto.getUserRightSsn());
				pstat.setString(9, dto.getUserName());
				
				int newUserId = pstat.executeUpdate();
				
				System.out.println(newUserId);
				
//		        if (newUserId > 0) {
//		            // 새로 생성된 사용자의 아이디 반환
//		        	System.out.println("Aaaaaaaa");
//		        	
//		        	log.setString(1, dto.getUserSeq());
//					log.setString(2, "닉네임'" + dto.getUserNick() + "' 이름'" + dto.getUserName() + "'님이 '회원가입'했습니다.");
//					log.setString(3, UserLog.SignUp.getValue());
//					log.executeUpdate();
//		        	
//		        	
//		        	conn.commit();
		        	

		            return newUserId;
		       // }
				
			} catch (Exception e) {
				System.out.println("UserDAO.| singUp");
				e.printStackTrace();
			}
			
			return 0;
		}
	
	@Override
	public int checkId(UserDTO dto) {
		
		final String SQL = "SELECT COUNT(*) FROM tblUser where userId = ?";
		
		try (
		        Connection conn = DBUtil.open();
		        PreparedStatement pstat = conn.prepareStatement(SQL);
		    ){
		        pstat.setString(1, dto.getUserId());
		        
		        ResultSet rs = pstat.executeQuery();
		        if (rs.next()) {
		            int count = rs.getInt(1);
		            System.out.println(count); // 디버깅을 위해 출력
		            return count;
		        }
		    } catch (Exception e) {
		        System.out.println("UserDAO.| disable");
		        e.printStackTrace();
		    }
		    
		    System.out.println(0);
		    return 0;
		}
	
	@Override
	public int checkNick(UserDTO dto) {
		final String SQL = "SELECT COUNT(*) FROM tblUser where userNick = ?";
		
		try (
		        Connection conn = DBUtil.open();
		        PreparedStatement pstat = conn.prepareStatement(SQL);
		    ){
		        pstat.setString(1, dto.getUserNick());
		        
		        ResultSet rs = pstat.executeQuery();
		        if (rs.next()) {
		        	System.out.println("Aaaaa");
		            int count = rs.getInt(1);
		            System.out.println(count); // 디버깅을 위해 출력
		            return count;
		        }
		    } catch (Exception e) {
		        System.out.println("UserDAO.| disable");
		        e.printStackTrace();
		    }
		    
		    return 0;
	}
	
	@Override
	public int checkEmail(UserDTO dto) {
		final String SQL = "SELECT COUNT(*) FROM tblUser where userEmail = ?";
		
		try (
		        Connection conn = DBUtil.open();
		        PreparedStatement pstat = conn.prepareStatement(SQL);
		    ){
		        pstat.setString(1, dto.getUserEmail());
		        
		        ResultSet rs = pstat.executeQuery();
		        if (rs.next()) {
		            int count = rs.getInt(1);
		            System.out.println(count); // 디버깅을 위해 출력
		            return count;
		        }
		    } catch (Exception e) {
		        System.out.println("UserDAO.| disable");
		        e.printStackTrace();
		    }
		    
		    System.out.println(0);
		    return 0;
	}


	
		public int userCnt(String userRegdate) {
		
		int userCount = 0;  
		
		
		String SQL = "SELECT COUNT(*) AS user_count FROM tblUser WHERE TO_CHAR(userregdate, 'YY/MM/DD') = ?";

		try {
			
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
			pstat.setString(1, userRegdate);
			
			ResultSet rs = pstat.executeQuery();
			
			 if (rs.next()) {
		            // ResultSet에서 'user_count' 열의 값을 가져와서 변수에 할당
		            userCount = rs.getInt("user_count");
		        }
			
			
		} catch (Exception e) {
			System.out.println("UserDAOImpl.userCnt");
			e.printStackTrace();
		}
		 return userCount;
		
		
	}
	
	@Override
	public Map<String, Integer> userGender() {
		
		Map<String, Integer> userCounts = new HashMap<>();
		
		 String SQL = "SELECT SUM(CASE WHEN SUBSTR(USERRIGHTSSN, 1, 1) = '1' OR SUBSTR(USERRIGHTSSN, 1, 1) = '3' THEN 1 ELSE 0 END) AS man, SUM(CASE WHEN SUBSTR(USERRIGHTSSN, 1, 1) = '2' OR SUBSTR(USERRIGHTSSN, 1, 1) = '4' THEN 1 ELSE 0 END) AS woman FROM tblUser";
		
		 try {
			 	Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				ResultSet rs = pstat.executeQuery();
				
				if (rs.next()) {
	                int manCount = rs.getInt("man");
	                int womanCount = rs.getInt("woman");

	                // 결과를 Map에 저장
	                userCounts.put("man", manCount);
	                userCounts.put("woman", womanCount);
	            }
				
		} catch (Exception e) {
			System.out.println("UserDAOImpl.userGender");
			e.printStackTrace();
		}
		 return userCounts;
	}

	


	
	@Override
	 public Map<String, Map<String, Integer>> newCnt(String formattedNum1, String formattedNum2) {
		 Map<String, Map<String, Integer>> countsMap = new HashMap<>();

		 String sql = "SELECT \r\n"
		 		+ "    TO_CHAR(userregdate, 'YY/MM') AS month,\r\n"
		 		+ "    COUNT(CASE WHEN USERSTATE = 'y' THEN 1 END) AS user_count,\r\n"
		 		+ "    COUNT(CASE WHEN USERSTATE = 'n' THEN 1 END) AS user_left\r\n"
		 		+ "FROM\r\n"
		 		+ "    tblUser\r\n"
		 		+ "WHERE\r\n"
		 		+ "    TO_CHAR(userregdate, 'YY/MM') >= ? AND\r\n"
		 		+ "    TO_CHAR(userregdate, 'YY/MM') <= ?\r\n"
		 		+ "GROUP BY\r\n"
		 		+ "    TO_CHAR(userregdate, 'YY/MM')\r\n"
		 		+ "ORDER BY\r\n"
		 		+ "    TO_NUMBER(REPLACE(TO_CHAR(userregdate, 'YY/MM'), '/', ''))";
       try {
           Connection conn = DBUtil.open();
           PreparedStatement pstat = conn.prepareStatement(sql);
           
           // 매개변수 설정
           pstat.setString(1, formattedNum1);
           pstat.setString(2, formattedNum2);

           
          
           try (ResultSet rs = pstat.executeQuery()) {
               while (rs.next()) {
                   String month = rs.getString("month");
                   int userCount = rs.getInt("user_count");
                   int userLeft = rs.getInt("user_left");
                   
                   Map<String, Integer> countMap = new HashMap<>();
                   countMap.put("user_count", userCount);
                   countMap.put("user_left", userLeft);

                   countsMap.put(month, countMap);
               }
           }
       } catch (Exception e) {
           System.out.println("UserDAOImpl.newCnt");
           e.printStackTrace();
       }

       return countsMap;
	}
	
	@Override
	public int findPK(UserDTO dto) {
	    final String SQL = "SELECT USERSEQ FROM tblUser where USERID = ?";
	    int primaryKey = 0;
	    
	    try (
	        Connection conn = DBUtil.open();
	        PreparedStatement pstat = conn.prepareStatement(SQL);
	    ) {
	        pstat.setString(1, dto.getUserId());
	        try (ResultSet rs = pstat.executeQuery()) {
	            if (rs.next()) {
	                primaryKey = rs.getInt("USERSEQ");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return primaryKey;
	}

	@Override
	public Map<String, Integer> childAge() {
		
		Map<String, Integer> ageRange = new HashMap<>();
		
		 String SQL = "SELECT\r\n"
		 		+ "    ac.agerange,\r\n"
		 		+ "    COALESCE(ct.count, 0) AS count\r\n"
		 		+ "FROM\r\n"
		 		+ "    tblAgeCat ac\r\n"
		 		+ "    LEFT JOIN (\r\n"
		 		+ "        SELECT\r\n"
		 		+ "            ta.agerange,\r\n"
		 		+ "            COUNT(*) AS count\r\n"
		 		+ "        FROM\r\n"
		 		+ "            tblChildAge tc\r\n"
		 		+ "            JOIN tblAgeCat ta ON tc.agecatseq = ta.agecatseq\r\n"
		 		+ "        GROUP BY\r\n"
		 		+ "            ta.agerange\r\n"
		 		+ "    ) ct ON ac.agerange = ct.agerange\r\n"
		 		+ "ORDER BY\r\n"
		 		+ "    ac.agecatseq";
		
		 try {
			 	Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				ResultSet rs = pstat.executeQuery();
				
				while (rs.next()) {
		            String range = rs.getString("agerange");
		            int count = rs.getInt("count");
		            ageRange.put(range, count);
		        }
				
		} catch (Exception e) {
			System.out.println("UserDAOImpl.childAge");
			e.printStackTrace();
		}
		 return ageRange;
	}
	
	
	
	
	@Override
	public Map<String, Integer> userAge() {
		
		Map<String, Integer> userAgeRange = new HashMap<>();
		
		 String SQL = "select * from vwUserAge";
		
		 try {
			 	Connection conn = DBUtil.open();
				PreparedStatement pstat = conn.prepareStatement(SQL);
				ResultSet rs = pstat.executeQuery();
				
				while (rs.next()) {
		            String userRange = rs.getString("age_range");
		            int userAgeCount = rs.getInt("count");
		            userAgeRange.put(userRange, userAgeCount);
		        }
				
		} catch (Exception e) {
			System.out.println("UserDAOImpl.userAge");
			e.printStackTrace();
		}
		 return userAgeRange;
	}
	
	
	@Override
	public ArrayList<UserDTO> findAllBlackList() {
		
		final String SQL = "select * from tblBlackList b join tblUser u  on b.userSeq = u.userSeq";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<UserDTO> list = new ArrayList<>();
			
			 for (ResultSet row = rs; row.next(); ) {
				
				UserDTO dto = new UserDTO();
				
				dto.setUserAddress(rs.getString("userAddress"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserLeftSsn(rs.getString("userLeftSsn"));
				dto.setLimitStorage(rs.getString("limitStorage"));
				dto.setUserLV(rs.getString("userLv"));
				dto.setUserNick(rs.getString("userNick"));
				dto.setUserRegdate(rs.getString("userRegdate"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserState(rs.getString("userState"));
				dto.setUserTel(rs.getString("userTel"));
				dto.setUserName(rs.getString("userName"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| listAll");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	@Override
	public int blaklistReStore(String userSeq) {
		
		final String SQL = "delete from tblBlacklist where userseq = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
				
		){
			
			
			pstat.setString(1, userSeq);
			
			return pstat.executeUpdate();
			

			
			
		} catch (Exception e) {
			System.out.println("UserDAO.| disable");
			e.printStackTrace();
		}
		
		return 0;
	
	}
	
	@Override
	public int findBlacklistSeq(String userSeq) {
		

		final String SQL = "SELECT * FROM tblBlackList b JOIN tblUser u ON b.userSeq = u.userSeq WHERE u.userId = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
		){
			pstat.setString(1, userSeq);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				//return rs.getInt("userSeq");
				return 1;
						
			}
			
			
		} catch (Exception e) {
			System.out.println("UserDAOImpl.| findSeq");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	@Override
	public int nUserCount() {
	    int nUserCount = 0;
	    String SQL = "SELECT COUNT(*) AS nuser FROM tblUser WHERE userstate = 'n'";

	    try (
	        Connection conn = DBUtil.open();
	        PreparedStatement pstat = conn.prepareStatement(SQL);
	    ) {
	        ResultSet rs = pstat.executeQuery();

	        if (rs.next()) {
	            nUserCount = rs.getInt("nuser");
	        }

	    } catch (Exception e) {
	        System.out.println("UserDAOImpl.nUserCount");
	        e.printStackTrace();
	    }

	    return nUserCount;
	}

	
@Override
	public Map<String, Integer[]> member(String year) {
	 Map<String, Integer[]> result = new HashMap<>();

	    String SQL = "SELECT\r\n"
	            + "    LPAD(SUBSTR(USERREGDATE, 4, 2), 2, '0') AS 월,\r\n"
	            + "    SUM(CASE WHEN USERSTATE = 'y' THEN 1 ELSE 0 END) AS 가입자수,\r\n"
	            + "    SUM(CASE WHEN USERSTATE = 'n' THEN 1 ELSE 0 END) AS 탈퇴자수\r\n"
	            + "FROM\r\n"
	            + "    tblUser\r\n"
	            + "WHERE\r\n"
	            + "    USERREGDATE BETWEEN TO_DATE(?, 'YY/MM/DD') AND TO_DATE(?, 'YY/MM/DD')\r\n"
	            + "GROUP BY\r\n"
	            + "    SUBSTR(USERREGDATE, 4, 2)\r\n"
	            + "ORDER BY\r\n"
	            + "    SUBSTR(USERREGDATE, 4, 2)";

	    try (Connection conn = DBUtil.open();
	         PreparedStatement pstat = conn.prepareStatement(SQL)) {

	        String startDate = year + "/01/01";
	        String endDate = year + "/12/31";

	        pstat.setString(1, startDate);
	        pstat.setString(2, endDate);

	        try (ResultSet rs = pstat.executeQuery()) {
	            while (rs.next()) {
	                String month = rs.getString("월");
	                int joinCount = rs.getInt("가입자수");
	                int withdrawCount = rs.getInt("탈퇴자수");

	                result.put(month, new Integer[]{joinCount, withdrawCount});
	            }
	        }

	    } catch (Exception e) {
	        System.out.println("UserDAOImpl.getUserStatsByMonth");
	        e.printStackTrace();
	    }

	    return result;
	}
	
	// 사용자가 만든 동화책 수 조회
	
	@Override
	public ArrayList<UserDTO> findAllBook() {
		
		final String SQL = "select u.*, count(b.bookSeq) AS numBooks from tblUser u left join tblBook b ON u.userSeq = b.userSeq group by u.userSeq, u.userId, u.userPw, u.userName, u.userNick, u.userTel, u.userAddress, u.userEmail, u.userLeftSsn, u.userRightSsn, u.userState, u.userLv, u.userRegdate, u.limitStorage";
		
		try (
			
			Connection conn = DBUtil.open();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(SQL);
				
			){
			
			ArrayList<UserDTO> list = new ArrayList<>();
			
			 for (ResultSet row = rs; row.next(); ) {
				
				UserDTO dto = new UserDTO();
				
				dto.setUserAddress(rs.getString("userAddress"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserLeftSsn(rs.getString("userLeftSsn"));
				dto.setLimitStorage(rs.getString("limitStorage"));
				dto.setUserLV(rs.getString("userLv"));
				dto.setUserNick(rs.getString("userNick"));
				dto.setUserRegdate(rs.getString("userRegdate"));
				dto.setUserSeq(rs.getString("userSeq"));
				dto.setUserState(rs.getString("userState"));
				dto.setUserTel(rs.getString("userTel"));
				dto.setUserName(rs.getString("userName"));
				dto.setNumBooks(rs.getString("numBooks"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.| listAll");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	
}//End of class
