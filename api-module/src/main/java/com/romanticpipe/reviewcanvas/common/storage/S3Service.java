package com.romanticpipe.reviewcanvas.common.storage;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Service {

	private final S3Client s3Client;

	@Value("${aws.s3.bucket}")
	private String bucketName;

	public String extractExt(String originalFileName) {
		int pos = originalFileName.lastIndexOf(".");
		return originalFileName.substring(pos + 1);
	}

	public String createSaveFileName(String originalFileName) {
		String ext = extractExt(originalFileName);
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}

	public String uploadFile(MultipartFile multipartFile) {
		// List<String> fileNameList = new ArrayList<>();
		// multipartFileList.forEach(file -> {
		// TODO 파일의 이름을 난수화 하는 과정이 필요할지?
		try {
			String fileName = multipartFile.getOriginalFilename();
			String extension = StringUtils.getFilenameExtension(fileName);
			String saveFileName = createSaveFileName(fileName);

			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.bucket(bucketName)
				.key(saveFileName)
				.contentType(extension)
				.build();

			s3Client.putObject(putObjectRequest, RequestBody.fromBytes(multipartFile.getBytes()));

			// fileNameList.add(fileName);
			return fileName;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// });
	}
}
