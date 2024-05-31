package com.romanticpipe.reviewcanvas.storage;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.CommonErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class S3Service {

	private final S3Client s3Client;
	private final S3Presigner s3Presigner;
	private final String bucketName;
	private final String resizeBucketName;

	public S3Service(S3Client s3Client, S3Presigner s3Presigner, @Value("${aws.s3.bucket}") String bucketName) {
		this.s3Client = s3Client;
		this.s3Presigner = s3Presigner;
		this.bucketName = bucketName;
		this.resizeBucketName = bucketName + "-resize";
	}

	public List<String> uploadFiles(List<MultipartFile> multipartFiles, String dirPath) {
		return multipartFiles.stream().map(multipartFile -> {
			try {
				String key = dirPath + "/" + createRandomFileName(multipartFile.getOriginalFilename());

				PutObjectRequest putObjectRequest = PutObjectRequest.builder()
					.bucket(bucketName)
					.key(key)
					.contentType(FileExtensionUtils.getContentType(multipartFile.getOriginalFilename()))
					.contentLength(multipartFile.getSize())
					.build();
				s3Client.putObject(putObjectRequest,
					RequestBody.fromInputStream(multipartFile.getInputStream(), multipartFile.getSize()));
				return key;
			} catch (IOException | S3Exception e) {
				throw new BusinessException(CommonErrorCode.FILE_UPLOAD_FAILED, e);
			}
		}).toList();
	}

	public void fileDeletes(List<String> fileNames) {
		fileNames.forEach(fileName -> {
			try {
				DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
					.bucket(bucketName)
					.key(fileName)
					.build();
				s3Client.deleteObject(deleteObjectRequest);
			} catch (S3Exception e) {
				log.info("S3 파일 삭제 시도 중 에러가 발생했습니다.", e);
			}
		});
	}

	public String getReviewDirPath(Integer shopAdminId, Long productId) {
		return "shop/" + shopAdminId + "/product/" + productId;
	}

	private String createRandomFileName(String fileName) {
		String ext = Optional.ofNullable(StringUtils.getFilenameExtension(fileName))
			.orElseThrow(() -> new IllegalArgumentException("확장자가 없는 파일은 업로드할 수 없습니다."));
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}

	public List<String> getReviewFileUrls(List<String> objectKeys) {
		return getPresignedUrls(objectKeys, bucketName);
	}

	public List<String> getReviewResizeImageUrls(List<String> objectKeys) {
		objectKeys = objectKeys.stream()
			.map(objectKey -> "resized-" + objectKey)
			.toList();
		return getPresignedUrls(objectKeys, resizeBucketName);
	}

	private List<String> getPresignedUrls(List<String> objectKeys, String bucketName) {
		return objectKeys.stream().map(objectKey -> {
			GetObjectRequest getObjectRequest = GetObjectRequest.builder()
				.bucket(bucketName)
				.key(objectKey)
				.build();

			GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
				.signatureDuration(Duration.ofMinutes(15))
				.getObjectRequest(getObjectRequest)
				.build();

			return s3Presigner.presignGetObject(getObjectPresignRequest)
				.url()
				.toString();
		}).toList();
	}
}
