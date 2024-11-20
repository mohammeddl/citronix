package com.citronix.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaleDTO(

                Long id,
                @Positive(message = "Unit price must be greater than 0") double unitPrice,
                double quantity,
                double revenue,
                @NotNull(message = "Date is required") @FutureOrPresent(message = "Date cannot be in the past") LocalDate date,
                @NotBlank(message = "Client name is required") String client,
                 Long harvestId) {
}