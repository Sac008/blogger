package com.springboot.blogger.comments;

import com.springboot.blogger.articles.ArticleEntity;
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
@Entity(name="comments")
public class CommentEntity extends BaseEntity {

	@Column(nullable=false)
	@NonNull
	private String body;

	@ManyToOne
	@JoinColumn(name="article_id")
	private ArticleEntity articleId;

	@ManyToOne
	@JoinColumn(name="author_id")
	private UserEntity authorId;

	private String title;

}