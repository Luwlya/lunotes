package com.luwlya.lunotes.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateAccountRequest(
        String name,
        @Email
        String email,
        @Size(min = 8)
        String password) {
}
