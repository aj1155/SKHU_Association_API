package kr.ac.skhu.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "origin_user")
@Entity
public class OriginUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@NotNull
	private int id;

	@Column(name = "login_id")
	@NotNull
	private String loginId;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "birth")
	@NotNull
	private String birth;

	@Column(name = "phone_number")
	@NotNull
	private String phoneNumber;

	@Column(name = "company_number")
	private String companyNumber;

	@Column(name = "grade")
	@NotNull
	private int grade;

	@Column(name = "status")
	@NotNull
	private String status;

	@JoinColumn(name = "category_id")
	@NotNull
	@ManyToOne
	private Category category;

	public static OriginUser of(int id, String loginId, String name, String birth, String phoneNumber, String companyNumber, int grade, String status, Category category){
		return OriginUser.builder()
				.id(id)
				.loginId(loginId)
				.name(name)
				.phoneNumber(phoneNumber)
				.companyNumber(companyNumber)
				.grade(grade)
				.status(status)
				.category(category).build();
	}
}
