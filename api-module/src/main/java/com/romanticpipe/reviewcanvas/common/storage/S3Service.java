package com.romanticpipe.reviewcanvas.common.storage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.CommonErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Service {

	private final S3Client s3Client;

	@Value("${aws.s3.bucket}")
	private String bucketName;

	public List<String> uploadFiles(List<MultipartFile> multipartFiles, String dirPath) {
		return multipartFiles.stream().map(multipartFile -> {
			try {
				String fileName = multipartFile.getOriginalFilename();
				String saveFileName = createRandomFileName(fileName);

				PutObjectRequest putObjectRequest = PutObjectRequest.builder()
					.bucket(bucketName)
					.key(dirPath + "/" + saveFileName)
					.contentType(multipartFile.getContentType())
					.contentLength(multipartFile.getSize())
					.build();
				s3Client.putObject(putObjectRequest,
					RequestBody.fromInputStream(multipartFile.getInputStream(), multipartFile.getSize()));
				return saveFileName;
			} catch (IOException | S3Exception e) {
				throw new BusinessException(CommonErrorCode.FILE_UPLOAD_FAILED, e);
			}
		}).toList();
	}

	private String createRandomFileName(String fileName) {
		String ext = Optional.ofNullable(StringUtils.getFilenameExtension(fileName))
			.orElseThrow(() -> new IllegalArgumentException("확장자가 없는 파일은 업로드할 수 없습니다."));
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}
}
