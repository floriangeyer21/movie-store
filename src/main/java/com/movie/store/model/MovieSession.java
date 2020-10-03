package com.movie.store.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"movie", "cinemaHall", "showTime"})
@ToString(of = {"movie", "cinemaHall", "showTime"})
@Table(name = "movie_session")
public class MovieSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_session_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "id")
    private CinemaHall cinemaHall;

    @Column(name = "show_time")
    private LocalDateTime showTime;
}
