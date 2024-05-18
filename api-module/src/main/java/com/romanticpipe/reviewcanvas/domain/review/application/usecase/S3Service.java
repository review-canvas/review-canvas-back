package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Service {

	private final S3Client s3Client;

	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucketName;

	public void uploadFile(List<MultipartFile> multipartFileList) {

		multipartFileList.forEach(file -> {
			// TODO 파일의 이름을 난수화 하는 과정이 필요할지?
			try {
				String fileName = file.getOriginalFilename();
				Path tempFile = Files.createTempFile(fileName, null);
				file.transferTo(tempFile);

				s3Client.putObject(PutObjectRequest.builder()
					.bucket(bucketName)
					.key(fileName)
					.build(), tempFile);

				Files.delete(tempFile);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
}
