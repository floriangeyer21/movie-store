package com.movie.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"capacity", "movieSessions"})
@ToString(of = {"id", "capacity"})
@Table(name = "cinema_halls")
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cin_hall_id")
    private Long id;
    private int capacity;
    private String description;
}
