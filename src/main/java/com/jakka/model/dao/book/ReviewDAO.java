package com.jakka.model.dao.book;

import com.jakka.model.dao.ActiveStatus;
import com.jakka.model.dao.BasicDAO;
import com.jakka.model.dao.Comments;
import com.jakka.model.dao.ReportCnt;
import com.jakka.model.dto.book.ReviewDTO;

public interface ReviewDAO extends BasicDAO<ReviewDTO>, ReportCnt
									, ActiveStatus<ReviewDTO>, Comments<ReviewDTO>{

}