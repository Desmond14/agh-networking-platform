package com.io.traderbook.repository;

import com.io.traderbook.model.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Long> {
	
	@Query(value = "select r from Rating r where r.rater.id = ?1")
    List<Rating> findByRaterId(Integer id);

    @Query(value = "select r from Rating r where r.ratee.id = ?1")
    List<Rating> findByRateeId(Integer id);
}
