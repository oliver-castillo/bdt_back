package com.app.bdt.model.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MasterDto {

    private Map<Long, String> paises;

    /*
     * @JsonProperty(access = Access.WRITE_ONLY)
     * 
     * @NotBlank(message = "The password name is required")
     * 
     * @Length(min = 8, message = "The password name must be at least 8 characters")
     * private String password;
     */
}
