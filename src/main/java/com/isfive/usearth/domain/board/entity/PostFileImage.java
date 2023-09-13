package com.isfive.usearth.domain.board.entity;

import com.isfive.usearth.domain.common.FileImage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostFileImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private FileImage fileImage;

	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	public PostFileImage(FileImage fileImage) {
		this.fileImage = fileImage;
	}

	public FileImage getFileImage() {
		return fileImage;
	}

	public String getStoredName() {
		return fileImage.getStoredName();
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
