package com.movie.store.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@EqualsAndHashCode(of = {"capacity", "movieSessions"})
@ToString(of = {"id", "capacity"})
@Table(name = "cinema_halls")
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cin_hall_id")
    private Long id;
    private int capacity;

    @OneToMany(mappedBy = "cinemaHall")
    private List<MovieSession> movieSessions;
}
