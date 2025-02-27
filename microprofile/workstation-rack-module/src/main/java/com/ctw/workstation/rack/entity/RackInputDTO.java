package com.ctw.workstation.rack.entity;

import java.time.LocalDateTime;

public record RackInputDTO(String serialNumber, Long teamId, String status, String defaultLocation) {

}
