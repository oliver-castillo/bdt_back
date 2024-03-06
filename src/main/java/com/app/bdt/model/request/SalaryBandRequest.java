package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryBandRequest {

  @NotNull
  private Double initialAmount;
  @NotNull
  private Double finalAmount;
  @NotNull
  private Integer currencyId;

}
