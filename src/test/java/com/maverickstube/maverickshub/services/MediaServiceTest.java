package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dtos.request.UploadMediaRequest;
import com.maverickstube.maverickshub.dtos.response.UploadMediaResponse;
import com.maverickstube.maverickshub.models.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.maverickstube.maverickshub.models.Category.COMEDY;
import static com.maverickstube.maverickshub.utils.TestUtils.TEST_IMAGE_LOCATION;
import static com.maverickstube.maverickshub.utils.TestUtils.TEST_VIDEO_LOCATION;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
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
    private  static  UploadMediaRequest buildUploadMediaRequest(InputStream inputStream) throws IOException {
        UploadMediaRequest request = new UploadMediaRequest();
        MultipartFile file = new MockMultipartFile("Media", inputStream);
        request.setMediaFile(file);
        request.setCategory(COMEDY);

        return request;
    }
}
