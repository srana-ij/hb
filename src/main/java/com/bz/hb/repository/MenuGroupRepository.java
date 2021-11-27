package com.bz.hb.repository;

import com.bz.hb.model.security.MenuGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: sohel
 * Date: 30/12/20
 * Time: 11:24 AM
 */
@Repository
public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long> {
    List<MenuGroup> findAllByActiveStatusOrderBySequenceNo(Integer activeStatus);

    @Query("select a from MenuGroup a order by a.sequenceNo")
    Page<MenuGroup> findAllMenuGroups(Pageable pageable);

    @Query("select distinct a.sequenceNo from MenuGroup a")
    List<Long>findAllSequence();

    Long countBySequenceNo(Long sequenceNo);
}
