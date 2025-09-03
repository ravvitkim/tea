package com.my.tea.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "state")
public class StateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state;
}
