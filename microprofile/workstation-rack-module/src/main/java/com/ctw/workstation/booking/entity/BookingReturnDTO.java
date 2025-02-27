package com.ctw.workstation.booking.entity;

import java.time.LocalDateTime;

public record BookingReturnDTO(Long id, Long rackId, Long requesterId, LocalDateTime bookFrom, LocalDateTime bookTo, LocalDateTime createdAt, LocalDateTime modifiedAt) {
}
