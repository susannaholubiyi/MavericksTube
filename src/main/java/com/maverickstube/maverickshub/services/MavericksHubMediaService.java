package com.maverickstube.maverickshub.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maverickstube.maverickshub.dtos.request.UploadMediaRequest;
import com.maverickstube.maverickshub.dtos.response.MediaResponse;
import com.maverickstube.maverickshub.dtos.response.UpdateMediaResponse;
import com.maverickstube.maverickshub.dtos.response.UploadMediaResponse;
import com.maverickstube.maverickshub.exceptions.MediaNotFoundException;
import com.maverickstube.maverickshub.exceptions.MediaUploadFailedException;
import com.maverickstube.maverickshub.models.Media;
import com.maverickstube.maverickshub.models.User;
import com.maverickstube.maverickshub.repositories.MediaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class MavericksHubMediaService implements MediaService{
    private final MediaRepository mediaRepository;
    private final Cloudinary cloudinary;
    private final ModelMapper modelMapper;
    private final UserService userService;
    @Override
    public UploadMediaResponse upload(UploadMediaRequest request) {
        User user = userService.getById(request.getUserId());
        try {
            Uploader uploader = cloudinary.uploader();
           Map<?,?> response =  uploader.uploadLarge(request.getMediaFile().getBytes(),ObjectUtils
                   .asMap("resource_type", "auto"));
           log.info("cloudinary upload response:: {}", response);
           String url = response.get("url").toString();
            Media media = modelMapper.map(request, Media.class);
            media.setUrl(url);
            media.setUploader(user);
            media = mediaRepository.save(media);
            return modelMapper.map(media, UploadMediaResponse.class);
        } catch (IOException e) {
            throw new MediaUploadFailedException("media upload failed");
        }

    }

    @Override
    public Media getMediaBy(long id) {
        return mediaRepository.findById(id)
                .orElseThrow(()-> new MediaNotFoundException("Media  not found"));
    }

    @Override
    public UpdateMediaResponse updateMedia(Long mediaId, JsonPatch updateRequest) {
        try {
            Media media = getMediaBy(mediaId);//get target media
            ObjectMapper objectMapper = new ObjectMapper();//convert media to JsonNode(using objectMapper
            JsonNode jsonMedia = objectMapper.convertValue(media, JsonNode.class);

            jsonMedia = updateRequest.apply(jsonMedia);
            //convert jsonNode to JsonMedia(using objectMapper)
            media= objectMapper.convertValue(jsonMedia, Media.class);
            media = mediaRepository.save(media);
            return modelMapper.map(media, UpdateMediaResponse.class);
        } catch (JsonPatchException e) {
            throw new MediaUploadFailedException(e.getMessage());
        }
    }

    @Override
    public List<MediaResponse> getMediaFor(Long userId) {
        List<Media> media = mediaRepository.findAllMediaFor(userId);
        return media.stream()
                .map(mediaItem->modelMapper.map(mediaItem, MediaResponse.class))
                        .toList();
    }


}