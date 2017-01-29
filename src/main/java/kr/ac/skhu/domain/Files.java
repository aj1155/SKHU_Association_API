package kr.ac.skhu.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Table(name = "file")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Files {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@NotNull
	private int id;

	@Column(name = "board_id")
	@NotNull
	private int boardId;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "size")
	@NotNull
	private Long size;

	@Column(name = "path")
	@NotNull
	private String path;

	public static Files of(int id, int boardId, String name, Long size, String path){
		return Files.builder()
				.id(id)
				.boardId(boardId)
				.name(name)
				.size(size)
				.path(path).build();
	}

	public Files of(int boardId, String name, Long size, String path){
		return Files.builder()
				.boardId(boardId)
				.name(name)
				.size(size)
				.path(path).build();
	}

	public Files(int boardId, String name, Long size, String path){
		this.boardId=boardId;
		this.name=name;
		this.size=size;
		this.path=path;
	}
}
