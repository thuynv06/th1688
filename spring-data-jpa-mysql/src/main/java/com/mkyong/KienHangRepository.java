package com.mkyong;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KienHangRepository extends CrudRepository<KienhangEntity, Long> {

    @Query(value = "SELECT k FROM KienhangEntity k where k.ladingcode=?1 order by k.id DESC")
    List<KienhangEntity> findByMVD(String mavandon);



}
