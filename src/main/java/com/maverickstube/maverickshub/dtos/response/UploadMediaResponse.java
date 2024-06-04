package com.maverickstube.maverickshub.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadMediaResponse {
    private Long mediaId;
    private String mediaUrl;
    private String description;
}
