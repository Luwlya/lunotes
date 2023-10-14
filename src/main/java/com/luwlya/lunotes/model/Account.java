package com.luwlya.lunotes.model;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

@RecordBuilder
public record Account(
        UUID id,
        String name,
        String email,
        String passwordHash,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        AccountStatus status
) implements AccountBuilder.With {
}
