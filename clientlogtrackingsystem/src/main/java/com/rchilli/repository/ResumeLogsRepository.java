package com.rchilli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rchilli.entities.ResumeLogs;

public interface ResumeLogsRepository extends CrudRepository<ResumeLogs, Integer> {

	public List<ResumeLogs> findByUserId(int i);

	@Query("select r from ResumeLogs r where r.userId IN:userIds")
	public List<ResumeLogs> findByUserIds(@Param("userIds") List<Integer> userIds);
	
	@Query(value="select * from ResumeLogs  where DATE(parseDate)=:date && userId=:userId",nativeQuery=true)
	public List<ResumeLogs> findByDateAndUserId(@Param("userId") int userId, @Param("date") String date);
	
	@Query(value="select * from ResumeLogs where YEAR(DATE(parseDate))=:year && MONTH(DATE(parseDate))=:month && userId=:userId ",nativeQuery=true)
	public List<ResumeLogs> findByMonthAndYear(@Param("userId") int userId,@Param("year") String year,@Param("month") String month);



}
