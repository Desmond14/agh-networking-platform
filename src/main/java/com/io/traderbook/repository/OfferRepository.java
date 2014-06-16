package com.io.traderbook.repository;

import com.io.traderbook.model.Offer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.io.traderbook.model.User;

import java.util.Set;

public interface OfferRepository extends CrudRepository<Offer, Long>{

    @Query(value = "select o from Offer o where o.seller = ?1")
    Iterable<Offer> findBySeller(User seller);

}
