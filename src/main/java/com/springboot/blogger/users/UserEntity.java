package com.springboot.blogger.users;

import com.springboot.blogger.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="users")
public class UserEntity extends BaseEntity {

	@Column(nullable=false , unique=true)
	@NonNull
	private String email;

	@Column(nullable=false, unique = true)
	@NonNull
	private String username;

	@Column(nullable=false)
	@NonNull
	private String password;

	private String bio;
	private String avatar;

}