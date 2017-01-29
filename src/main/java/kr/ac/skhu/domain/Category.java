package kr.ac.skhu.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Table(name = "category")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@NotNull
	private int id;

	@Column(name = "name")
	@NotNull
	private String name;

	public static Category of(int id, String name){
		return Category.builder()
				.id(id)
				.name(name).build();
	}
}
