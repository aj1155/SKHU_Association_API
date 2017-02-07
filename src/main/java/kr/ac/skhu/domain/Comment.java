package kr.ac.skhu.domain;

import kr.ac.skhu.controller.model.request.CommentRequest;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "comment")
@Entity
public class Comment extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@NotNull
	private int id;

	@Column(name = "content")
	@NotNull
	private String content;

	@NotNull
	@Column(name = "writer_id")
	private int writer_id;

	@NotNull
	@Column(name = "writer_name")
	private String writer_name;

	@NotNull
	@Column(name = "board_post_id")
	private int boardPostId;

	public static Comment ofCreate(CommentRequest commentRequest){
		return Comment.builder()
				.content(commentRequest.getContent())
				.writer_id(commentRequest.getWriter_id())
				.writer_name(commentRequest.getWriter_name())
				.boardPostId(commentRequest.getBoadPostId())
				.build();
	}

	public static Comment ofUpdate(CommentRequest commentRequest){
		return Comment.builder()
				.id(commentRequest.getId())
				.content(commentRequest.getContent())
				.writer_id(commentRequest.getWriter_id())
				.writer_name(commentRequest.getWriter_name())
				.boardPostId(commentRequest.getBoadPostId())
				.build();
	}
}
