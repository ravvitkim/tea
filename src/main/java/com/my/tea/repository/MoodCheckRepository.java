package com.my.tea.repository;

import com.my.tea.entity.MoodCheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodCheckRepository extends JpaRepository<MoodCheckEntity, Long> {
}

