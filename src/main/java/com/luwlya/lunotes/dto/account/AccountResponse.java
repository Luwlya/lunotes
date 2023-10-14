package com.luwlya.lunotes.dto.account;

import com.luwlya.lunotes.model.AccountStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public record AccountResponse(
        UUID id,
        String name,
        String email,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        AccountStatus status) {
}
