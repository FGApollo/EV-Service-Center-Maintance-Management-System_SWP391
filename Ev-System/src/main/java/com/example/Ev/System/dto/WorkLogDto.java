package com.example.Ev.System.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class WorkLogDto {
    private List<Integer> staffId;
    private Integer appointmentId;
    private BigDecimal hoursSpent;
    private String tasksDone;
}
