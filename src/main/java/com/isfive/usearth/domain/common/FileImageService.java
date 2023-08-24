package com.isfive.usearth.domain.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileImageService {

    public FileImage createFileImage(MultipartFile file) {
        String profileDir = String.format("/src/main/resources/projectImg/");
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String originalName = file.getOriginalFilename();
        String[] fileNameSplit = originalName.split("\\.");
        String extension = fileNameSplit[fileNameSplit.length - 1]; // 확장자

        UUID uuid = UUID.randomUUID();
        String storedName = uuid + extension;
        String profilePath = profileDir + storedName;

        try {
            file.transferTo(Path.of(profilePath));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        FileImage fileImage = FileImage.builder()
                .originalName(originalName)
                .storedName(storedName)
                .build();

        return fileImage;
    }

    public List<FileImage> createFileImage(List<MultipartFile> fileList) {
        return fileList.stream().map(this::createFileImage).toList();
    }


}
