package kr.ac.skhu.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "board_post")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardPost extends BaseEntity implements Serializable{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@NotNull
	@Column(name = "title")
	private String title;

	@Lob
	@NotNull
	@Column(name = "content")
	private String content;

	@NotNull
	@Column(name = "own_board_id")
	private int ownBoardId;

	@NotNull
	@Column(name = "writer_id")
	private int writer_id;

	@NotNull
	@Column(name = "writer_name")
	private String writer_name;

	public static BoardPost ofCreate(String title, String content, int boardId, int writer_id ,String writer_name){
		return BoardPost.builder()
				.title(title)
				.content(content)
				.ownBoardId(boardId)
				.writer_id(writer_id)
				.writer_name(writer_name)
				.build();
	}

	public static BoardPost ofUpdate(int id,String title, String content, int boardId, int writer_id ,String writer_name){
		return BoardPost.builder()
				.id(id)
				.title(title)
				.content(content)
				.ownBoardId(boardId)
				.writer_id(writer_id)
				.writer_name(writer_name)
				.build();
	}

}
