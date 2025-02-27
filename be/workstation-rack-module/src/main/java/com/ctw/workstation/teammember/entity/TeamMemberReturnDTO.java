package com.ctw.workstation.teammember.entity;

import java.time.LocalDateTime;

public record TeamMemberReturnDTO(Long id, Long teamID, String ctwID, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
}
