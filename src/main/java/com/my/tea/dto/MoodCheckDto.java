package com.my.tea.dto;

import com.my.tea.entity.MoodCheckEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoodCheckDto {
    private Long id;
    private String moodCheck;

    public static MoodCheckDto fromEntity(MoodCheckEntity entity) {
        return new MoodCheckDto(entity.getId(), entity.getMoodCheck());
    }

    // 저장하고 수정하려고 만들었어용
    public MoodCheckEntity toEntity() {
        MoodCheckEntity entity = new MoodCheckEntity();
        entity.setId(this.id);
        entity.setMoodCheck(this.moodCheck);
        return entity;
    }
}
