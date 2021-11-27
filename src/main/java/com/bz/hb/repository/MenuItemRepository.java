package com.bz.hb.repository;

import com.bz.hb.model.security.MenuItem;
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
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findAllByActiveStatusOrderBySequenceNo(Integer activeStatus);
    List<MenuItem> findMenusByMenuGroupIdOrderBySequenceNo(Long menuGroupId);

    @Query("select distinct a.sequenceNo from MenuItem a")
    List<Long>findAllSequence();

}
