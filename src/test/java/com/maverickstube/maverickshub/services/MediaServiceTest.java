package com.maverickstube.maverickshub.services;

import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.maverickstube.maverickshub.dtos.request.UploadMediaRequest;
import com.maverickstube.maverickshub.dtos.response.MediaResponse;
import com.maverickstube.maverickshub.dtos.response.UpdateMediaResponse;
import com.maverickstube.maverickshub.dtos.response.UploadMediaResponse;
import com.maverickstube.maverickshub.models.Category;
import com.maverickstube.maverickshub.models.Media;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.maverickstube.maverickshub.models.Category.*;
import static com.maverickstube.maverickshub.utils.TestUtils.TEST_IMAGE_LOCATION;
import static com.maverickstube.maverickshub.utils.TestUtils.TEST_VIDEO_LOCATION;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/data.sql"})
public class MediaServiceTest {
    @Autowired
    private MediaService mediaService;
    @Test
    public void uploadMediaTest() throws IOException {
        Path path = Paths.get(TEST_IMAGE_LOCATION);
        try(var inputStream = Files.newInputStream(path)) {
            UploadMediaRequest request = buildUploadMediaRequest(inputStream);
            UploadMediaResponse response = mediaService.upload(request);
            assertThat(response).isNotNull();
            assertThat(response.getMediaUrl()).isNotNull();
        }

    }
    @Test
    public void uploadVideoTest() throws IOException {
        Path path = Paths.get(TEST_VIDEO_LOCATION);
        try( var inputStream = Files.newInputStream(path)) {
           UploadMediaRequest request = buildUploadMediaRequest(inputStream);
            UploadMediaResponse response = mediaService.upload(request);
            log.info("response -> {}", response);
            assertThat(response).isNotNull();
            assertThat(response.getMediaUrl()).isNotNull();
        }
    }
    @Test
    public void getMediaByIdTest(){
        Media media = mediaService.getMediaBy(100L);
        log.info("found content ->{}", media);
        assertThat(media).isNotNull();
    }
    @Test
    public void updateMediaTest() throws JsonPointerException {
        Category category = mediaService.getMediaBy(103L).getCategory();
        assertThat(category).isNotEqualTo(SCI_FI);
        List<JsonPatchOperation> operations = List.of(
                new ReplaceOperation(new JsonPointer("/category"),
                        new TextNode(SCI_FI.name()))
        );
        JsonPatch updateMediaRequest = new JsonPatch(operations);
        UpdateMediaResponse response = mediaService.updateMedia(103L, updateMediaRequest);
        category = mediaService.getMediaBy(103L).getCategory();
        assertThat(category).isEqualTo(SCI_FI);

    }
    @Test
    public void getMediaForUserTest(){
        Long userId = 200L;
        List<MediaResponse> media = mediaService.getMediaFor(userId);
        log.info("items->{}", media);
        assertThat(media).hasSize(3);
    }
    private  static  UploadMediaRequest buildUploadMediaRequest(InputStream inputStream) throws IOException {
        UploadMediaRequest request = new UploadMediaRequest();
        MultipartFile file = new MockMultipartFile("Media", inputStream);
        request.setMediaFile(file);
        request.setUserId(201L);
        request.setCategory(COMEDY);

        return request;
    }

}
