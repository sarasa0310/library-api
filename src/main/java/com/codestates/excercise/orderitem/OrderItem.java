package com.codestates.excercise.orderitem;

import com.codestates.excercise.item.Item;
import com.codestates.excercise.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice;

    private int count;

    public OrderItem(Order order, Item item, int count) {
        this.order = order;
        this.item = item;
        this.orderPrice = item.getPrice();
        this.count = count;
    }

}
