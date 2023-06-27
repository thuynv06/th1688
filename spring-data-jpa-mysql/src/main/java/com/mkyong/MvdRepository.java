package com.mkyong;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MvdRepository extends CrudRepository<Mvd, Long> {

    @Query(value = "SELECT k FROM Mvd k where k.mvd=?1 order by k.id DESC")
    List<Mvd> findByMaVanDon(String mvd);



}
