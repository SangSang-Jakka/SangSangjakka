package com.jakka.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jakka.model.DBUtil;
import com.jakka.model.dto.GenreDTO;
import com.jakka.model.dto.board.BoardDTO;
import com.jakka.model.dto.book.BookDTO;

public class GenreDAO {
	public ArrayList<GenreDTO> findAll() {

		final String SQL = "select * from tblgenre order by genreseq";

		try (

				Connection conn = DBUtil.open();
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(SQL);

		) {

			ArrayList<GenreDTO> list = new ArrayList<>();

			while (rs.next()) {

				GenreDTO dto = new GenreDTO();

				//dto.setGenreSeq(rs.getString("GenreSeq"));
				//dto.setGenreName(rs.getString("GenreName"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println("BoardDAO.| list");
			e.printStackTrace();
		}

		return null;

	}
	
	public GenreDTO findByName(String genreName) {
		
		final String SQL = "select * from tblgenre where genreName = ?";
		
		try (
			Connection conn = DBUtil.open();
			PreparedStatement pstat = conn.prepareStatement(SQL);
				
		){
			pstat.setString(1, genreName);
			
			ResultSet rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				GenreDTO dto = new GenreDTO();
				
				//dto.setGenreSeq(rs.getString("genreSeq"));
				//dto.setGenreName(rs.getString("genreName"));
				
	            return dto;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("BookDAO.| get");
			e.printStackTrace();
		}
		
		
		return null;
	}

	public int infoAdd(ArrayList<GenreDTO> dto, String bookSeq) {
		{
			final String SQL = "INSERT INTO tblgenreinfo (bookSeq, genreSeq) VALUES (?, ?)";

			try (Connection conn = DBUtil.open(); PreparedStatement pstat = conn.prepareStatement(SQL);) {
				
				int result = 0;
				
				pstat.setInt(1, Integer.parseInt(bookSeq));
				
				for(GenreDTO item : dto) {
					//pstat.setInt(2, Integer.parseInt(item.getGenreSeq()));
					result = pstat.executeUpdate();
					if (result == 0) {
						return result;
					}
				}
				
				return result;
				
			} catch (SQLException e) {
				System.out.println("Error adding book: " + e.getMessage());
				throw new RuntimeException(e); // Rethrow or handle as necessary
			}
		}
	}

}
