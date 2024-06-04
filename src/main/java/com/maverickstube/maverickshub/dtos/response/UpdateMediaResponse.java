package com.maverickstube.maverickshub.dtos.response;

import com.maverickstube.maverickshub.models.Category;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateMediaResponse {
    private String description;
    private Category category;
}
