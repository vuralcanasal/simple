package com.va.simple.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class CreateAccountRequest(
        @field:NotBlank(message = "The customer id must NOT be empty")
        val customerId: String,
        @field:Min(0)
        val initialCredit: BigDecimal
)
