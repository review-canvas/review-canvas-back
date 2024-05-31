package com.romanticpipe.reviewcanvas.storage;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileExtensionUtils {

	public static final Map<String, String> IMAGE_EXTENSIONS = Map.of(
		"jpg", "image/jpg",
		"jpeg", "image/jpeg",
		"png", "image/png",
		"gif", "image/gif",
		"bmp", "image/bmp",
		"webp", "image/webp",
		"tiff", "image/tiff",
		"svg", "image/svg+xml"
	);

	public static final Map<String, String> VIDEO_EXTENSIONS = Map.of(
		"mp4", "video/mp4",
		"avi", "video/x-msvideo",
		"mov", "video/quicktime",
		"wmv", "video/x-ms-wmv",
		"flv", "video/x-flv",
		"mkv", "video/x-matroska",
		"webm", "video/webm",
		"mpeg", "video/mpeg",
		"3gp", "video/3gpp"
	);

	public static boolean isImage(String filename) {
		String extension = getExtension(filename);
		return IMAGE_EXTENSIONS.containsKey(extension);
	}

	public static boolean isVideo(String filename) {
		String extension = getExtension(filename);
		return VIDEO_EXTENSIONS.containsKey(extension);
	}

	public static String getContentType(String filename) {
		String extension = getExtension(filename);
		if (IMAGE_EXTENSIONS.containsKey(extension)) {
			return IMAGE_EXTENSIONS.get(extension);
		} else if (VIDEO_EXTENSIONS.containsKey(extension)) {
			return VIDEO_EXTENSIONS.get(extension);
		} else {
			throw new IllegalArgumentException("지원하지 않는 파일 형식입니다.");
		}
	}

	private static String getExtension(String filename) {
		return Optional.ofNullable(StringUtils.getFilenameExtension(filename))
			.orElseThrow(() -> new IllegalArgumentException("확장자가 없는 파일은 업로드할 수 없습니다."));
	}
}
