package com.ctw.workstation.rackasset.entity;

import java.time.LocalDateTime;

public record RackAssetReturnDTO(Long id, String asset_tag, Long rackID, LocalDateTime createdAt, LocalDateTime modifiedAt) {
}
