package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dtos.request.UploadMediaRequest;
import com.maverickstube.maverickshub.dtos.response.UploadMediaResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MediaServiceTest {
    @Autowired
    private MediaService mediaService;
    @Test
    public void uploadMediaTest() throws IOException {

        String fileLocation = "/home/user/Desktop/maverickshub/src/main/resources/static/thanos.jpeg";
        Path path = Paths.get(fileLocation);
        try(var inputStream = Files.newInputStream(path)) {
            UploadMediaRequest request = new UploadMediaRequest();
            MultipartFile file = new MockMultipartFile("thanos profile pic",inputStream );
            request.setMediaFile(file);
            UploadMediaResponse response = mediaService.upload(request);

            assertThat(response).isNotNull();
            assertThat(response.getMediaUrl()).isNotNull();
        }

    }
}
