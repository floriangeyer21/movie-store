package com.movie.store.model;

import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shoppingCarts_tickets",
            joinColumns =
                    { @JoinColumn(name = "shopping_Cart_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "ticket_id", referencedColumnName = "id") })
    private List<Ticket> tickets;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
