package com.jakka.model.dao.user;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.user.UserDTO;

public class UserDAOImpl implements UserDAO{

	private final static UserDAOImpl DAO = new UserDAOImpl();
	
	private UserDAOImpl() {
		//외부 생성 방지
	}
	@Override
	public UserDTO getUser(String userSeq) {
		// TODO Auto-generated method stub
		return null;
	}
	public static UserDAOImpl getInstance() {
		return DAO;
	}//getInstance()
	
	

	public boolean unRegister(UserDTO dto) {
		String SQL = "delete from tblUser where userSeq = ? and userPw = ?";
		
		try {
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			pstat.setString(1, dto.getUserSeq());
			pstat.setString(2, dto.getUserPw());
			
			int result = pstat.executeUpdate();
			
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
		String SQL = "select userId from tblUser where userNick = ? and userEmail = ?";
		
		try {
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
			// nick과 email을 ?에 할당
			pstat.setString(1, dto.getUserNick());
			pstat.setString(2, dto.getUserEmail());
			
			// 결과 반환
			ResultSet rs = pstat.executeQuery();
			
			// 결과값이 있으면
			if(rs.next()) {
				// DTO타입의 참조변수 result
				UserDTO result = new UserDTO();
				// 쿼리를 실행한 결과를 가져와서 result에 쓰기
				result.setUserId(rs.getString("userId"));
				result.setUserRegdate(rs.getString("userRegdate"));
				rs.close();
				
				// result 반환
				return result;
			}
			
		} catch (Exception e) {
			System.out.println("UserDAOImpl.findId");
			e.printStackTrace();
		}
		return null;
	}
 	
	// 로그인
	public UserDTO login(UserDTO dto) {
		
		String SQL = "select * from tblUser where userId = ? and userPw = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL); 
		){
			pstat.setString(1, dto.getUserId());
			pstat.setString(2, dto.getUserPw());
			
			System.out.println(dto.getUserId());
			System.out.println(dto.getUserPw());
			
			ResultSet rs = pstat.executeQuery();
			
			UserDTO result = new UserDTO();

			if(rs.next()) {
				
				result.setUserId(rs.getString("userId"));
				result.setUserNick(rs.getString("userNick"));
				result.setUserLV(rs.getString("userLV"));
				
				System.out.println(rs.getString("userNick"));
				
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
		        pstat.setString(4, dto.getUserSeq());

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
	
}//End of class
