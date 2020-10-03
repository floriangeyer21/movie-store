package com.movie.store.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"capacity", "movieSession"})
@ToString(of = {"capacity", "movieSession"})
@Table(name = "cinema_hall")
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_hall_id")
    private Long id;

    @OneToMany(mappedBy = "id")
    List<MovieSession> movieSessions;

    private int capacity;
}
