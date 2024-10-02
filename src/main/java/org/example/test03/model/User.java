package org.example.test03.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name ="test03",schema="test03",uniqueConstraints = @UniqueConstraint(columnNames ="name"))

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String Name;

    @Column(name = "money", columnDefinition = "BIGINT default 0")
    private Long Money;
}
