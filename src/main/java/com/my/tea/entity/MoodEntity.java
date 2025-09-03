package com.my.tea.entity;

import com.my.tea.dto.MoodDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mood")
public class MoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mood;

}
