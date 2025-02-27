package com.ctw.workstation.booking.entity;

import java.time.LocalDateTime;

public record BookingInputDTO(Long rackId, Long requesterId, LocalDateTime bookFrom, LocalDateTime bookTo) {
}
