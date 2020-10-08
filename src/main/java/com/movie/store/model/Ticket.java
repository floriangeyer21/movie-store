package com.movie.store.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "movie_session_id", referencedColumnName = "id")
    private MovieSession movieSession;

    @ManyToOne
    private User user;
}
