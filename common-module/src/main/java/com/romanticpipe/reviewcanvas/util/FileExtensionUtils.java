package com.romanticpipe.reviewcanvas.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileExtensionUtils {

	public static final List<String> IMAGE_EXTENSIONS = List.of(
		"jpg", "jpeg", "png", "gif", "bmp", "webp", "tiff", "svg"
	);

	public static final List<String> VIDEO_EXTENSIONS = List.of(
		"mp4", "avi", "mov", "wmv", "flv", "mkv", "webm", "mpeg", "3gp"
	);
}
