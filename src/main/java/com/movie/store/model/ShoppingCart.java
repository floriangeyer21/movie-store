package com.movie.store.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinTable(name = "shopping_carts_tickets",
            joinColumns =
                    { @JoinColumn(name = "shopping_cart_id",
                            referencedColumnName = "shopping_cart_id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "ticket_id", referencedColumnName = "id") })
    private Set<Ticket> tickets = new HashSet<>();

    @OneToOne
    @MapsId
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    private User user;
}
