package com.app.bdt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicallSkillDto {
    @NotBlank(message = "The skill is required")
    private String skill;

    @NotBlank(message = "The years is required")
    private Integer years;
}
