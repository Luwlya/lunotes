package com.luwlya.lunotes.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAccountRequest(
        @NotNull
        String name,
        @NotNull
        @Email
        String email,
        @NotNull
        @Size(min = 8)
        String password) {
}
