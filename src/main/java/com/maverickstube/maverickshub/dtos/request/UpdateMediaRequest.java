package com.maverickstube.maverickshub.dtos.request;

import com.maverickstube.maverickshub.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMediaRequest {
    private Long id;
    private String description;
    private Category category;
}
