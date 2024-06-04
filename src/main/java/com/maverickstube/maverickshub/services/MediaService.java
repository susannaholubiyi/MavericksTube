package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dtos.request.UpdateMediaRequest;
import com.maverickstube.maverickshub.dtos.request.UploadMediaRequest;
import com.maverickstube.maverickshub.dtos.response.UpdateMediaResponse;
import com.maverickstube.maverickshub.dtos.response.UploadMediaResponse;
import com.maverickstube.maverickshub.models.Media;

public interface MediaService {
    UploadMediaResponse upload(UploadMediaRequest request);

    Media getMediaBy(long id);

    UpdateMediaResponse updateMedia(UpdateMediaRequest updateMediaRequest);
}
