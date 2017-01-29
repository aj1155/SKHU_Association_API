package kr.ac.skhu.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="board")
@Entity
public class Board implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "board_type")
	@NotNull
	@Enumerated(EnumType.STRING)
	private BoardType boardType;

	@Column(name = "category_id")
	@NotNull
	private int categoryId;

	public static Board ofCreate(BoardType boardType,int categoryId){
		return Board.builder()
				.boardType(boardType)
				.categoryId(categoryId)
				.build();
	}

}
