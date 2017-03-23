package kr.ac.skhu.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Manki Kim on 2017-03-22.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "user_key")
public class UserKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_key")
    private String userKey;
}
