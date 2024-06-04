package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dtos.request.UploadMediaRequest;
import com.maverickstube.maverickshub.dtos.response.UploadMediaResponse;

public interface MediaService {
    UploadMediaResponse upload(UploadMediaRequest request);
}
