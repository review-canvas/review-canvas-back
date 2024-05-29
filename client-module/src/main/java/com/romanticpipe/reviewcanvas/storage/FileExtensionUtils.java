package com.romanticpipe.reviewcanvas.storage;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileExtensionUtils {

	public static final List<String> IMAGE_EXTENSIONS = List.of(
		"jpg", "jpeg", "png", "gif", "bmp", "webp", "tiff", "svg"
	);

	public static final List<String> VIDEO_EXTENSIONS = List.of(
		"mp4", "avi", "mov", "wmv", "flv", "mkv", "webm", "mpeg", "3gp"
	);

	public static boolean isImage(String filename) {
		String extension = Optional.ofNullable(StringUtils.getFilenameExtension(filename))
			.orElseThrow(() -> new IllegalArgumentException("확장자가 없는 파일은 업로드할 수 없습니다."));
		return IMAGE_EXTENSIONS.contains(extension);
	}

	public static boolean isVideo(String originalFilename) {
		String extension = Optional.ofNullable(StringUtils.getFilenameExtension(originalFilename))
			.orElseThrow(() -> new IllegalArgumentException("확장자가 없는 파일은 업로드할 수 없습니다."));
		return VIDEO_EXTENSIONS.contains(extension);
	}
}
