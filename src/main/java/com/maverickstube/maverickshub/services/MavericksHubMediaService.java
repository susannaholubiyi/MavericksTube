package com.maverickstube.maverickshub.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import com.maverickstube.maverickshub.dtos.request.UploadMediaRequest;
import com.maverickstube.maverickshub.dtos.response.UploadMediaResponse;
import com.maverickstube.maverickshub.exceptions.MediaUploadFailedException;
import com.maverickstube.maverickshub.models.Media;
import com.maverickstube.maverickshub.repositories.MediaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class MavericksHubMediaService implements MediaService{
    private final MediaRepository mediaRepository;
    private final Cloudinary cloudinary;
    @Override
    public UploadMediaResponse upload(UploadMediaRequest request) {
        try {
            Uploader uploader = cloudinary.uploader();
           Map<?,?> response =  uploader.upload(request.getMediaFile().getBytes(),null);
           log.info("cloudinary upload response:: {}", response);
           UploadMediaResponse mediaResponse = new UploadMediaResponse();
           String url = response.get("url").toString();
            Media media = new Media();
            media.setUrl(url);
            media.setDescription(request.getDescription());
            media = mediaRepository.save(media);
            mediaResponse.setMediaUrl(url);
            mediaResponse.setMediaId(media.getId());
            mediaResponse.setDescription(media.getDescription());
            return mediaResponse;
        } catch (IOException e) {
            throw new MediaUploadFailedException("media upload failed");
        }

    }
}