package kr.ac.skhu.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Manki Kim on 2017-01-18.
 */
@Entity
@Data
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

}
