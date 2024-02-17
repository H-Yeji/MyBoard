package com.yjstudy.myboard.file;

import com.yjstudy.myboard.domain.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Slf4j
@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        String uploadFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(uploadFileName);

        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(uploadFileName, storeFileName);
    }

    private String createStoreFileName(String originalFileName) {

        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();

        return uuid + "." + ext; //uuid.ext를 저장소 파일명으로 설정

    }

    private String extractExt(String originalFileName) {

        int pos = originalFileName.lastIndexOf("."); //.의 위치 찾기
        return originalFileName.substring(pos+1); //.이후부터 끝까지 추출
    }

}
