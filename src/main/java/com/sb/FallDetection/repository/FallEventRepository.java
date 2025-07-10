package com.sb.FallDetection.repository;


import com.sb.FallDetection.model.FallEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FallEventRepository extends JpaRepository<FallEvent, Long> {

}
