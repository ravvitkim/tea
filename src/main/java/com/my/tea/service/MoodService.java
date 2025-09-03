package com.my.tea.service;

import com.my.tea.dto.MoodCheckDto;
import com.my.tea.dto.MoodDto;
import com.my.tea.entity.MoodCheckEntity;
import com.my.tea.entity.MoodEntity;
import com.my.tea.repository.MoodCheckRepository;
import com.my.tea.repository.MoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoodService {

    private final MoodRepository moodRepository;
    private final MoodCheckRepository moodCheckRepository;

    public MoodService(MoodRepository moodRepository, MoodCheckRepository moodCheckRepository) {
        this.moodRepository = moodRepository;
        this.moodCheckRepository = moodCheckRepository;
    }

    // 체크리스트 항목 가져오기
    public List<MoodCheckDto> getMoodCheckList() {
        return moodCheckRepository.findAll()
                .stream()
                .map(MoodCheckDto::fromEntity)
                .toList();
    }
    // 체크된 항목으로 기분 계산 (예시)




    public MoodDto saveMood(MoodDto dto) {
        // DTO -> Entity
        MoodEntity entity = dto.toEntity();

        // DB 저장
        MoodEntity saved = moodRepository.save(entity);

        // Entity -> DTO
        return MoodDto.fromEntity(saved);
    }
    public MoodCheckDto saveMoodCheck(MoodCheckDto dto) {
        // DTO -> Entity
        MoodCheckEntity entity = dto.toEntity();

        // DB 저장
        MoodCheckEntity saved = moodCheckRepository.save(entity);

        // Entity -> DTO
        return MoodCheckDto.fromEntity(saved);
    }

}

