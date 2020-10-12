package com.movie.store.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    private Long id;

    @OneToMany
    @JoinTable(name = "shopping_carts_tickets",
            joinColumns =
                    { @JoinColumn(name = "shopping_cart_id",
                            referencedColumnName = "shopping_Cart_id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "ticket_id", referencedColumnName = "id") })
    private List<Ticket> tickets;

    @OneToOne
    @MapsId
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    private User user;
}
