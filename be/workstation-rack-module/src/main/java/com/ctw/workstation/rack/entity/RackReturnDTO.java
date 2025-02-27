package com.ctw.workstation.rack.entity;

import java.time.LocalDateTime;

public record RackReturnDTO(Long id, String serialNumber, Long teamId, String status, String defaultLocation, LocalDateTime createdAt, LocalDateTime modifiedAt) {

}