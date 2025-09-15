package com.springboot.blogger.articles;

import java.util.Date;
import java.util.List;

import com.springboot.blogger.common.BaseEntity;
import com.springboot.blogger.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="articles")
public class ArticleEntity extends BaseEntity {

	@Column(nullable = false)
	@NonNull
	private String heading;

	@Column(nullable = false)
	@NonNull
	private String slug;

	private String content;

	@Column(nullable = false)
	@NonNull
	private String subheading;

	@ManyToOne
	@JoinColumn(name="authorId")
	private UserEntity authorId;


//	private List<String> tags;
}