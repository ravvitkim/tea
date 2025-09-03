package com.my.tea.dto;

import com.my.tea.entity.MoodEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoodDto {
    private Long id;
    private String mood;

    // Entity -> DTO
    public static MoodDto fromEntity(MoodEntity entity) {
        return new MoodDto(entity.getId(), entity.getMood());
    }

    // DTO -> Entity
    public MoodEntity toEntity() {
        MoodEntity mood = new MoodEntity();
        mood.setId(this.id);
        mood.setMood(this.mood);
        return mood;
    }




}
