// backend/src/main/java/org/example/entities/Point.java
package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "points")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private double x;


    @Column(nullable = false)
    private double y;


    @Column(nullable = false)
    private double r;


    @Column(nullable = false)
    private boolean hit;

    @Column(nullable = false)
    private long time;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
