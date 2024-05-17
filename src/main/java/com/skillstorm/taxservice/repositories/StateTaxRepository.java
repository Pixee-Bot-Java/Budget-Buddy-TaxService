package com.skillstorm.taxservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skillstorm.taxservice.models.StateTax;

import java.util.List;

public interface StateTaxRepository extends JpaRepository<StateTax, Integer> {

    @Query("SELECT st FROM StateTax st JOIN st.state s WHERE s.stateName = :stateName")
    List<StateTax> findByStateName(@Param("stateName") String stateName);

    @Query("SELECT st FROM StateTax st JOIN st.state s WHERE s.stateCode = :stateCode")
    List<StateTax> findByStateCode(@Param("stateCode") String stateCode);
}
