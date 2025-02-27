package com.ctw.workstation.team.entity;

import java.time.LocalDateTime;

public record TeamReturnDTO(Long id, String name, String product, String defaultLocation, LocalDateTime createdAt, LocalDateTime modifiedAt) {
}
