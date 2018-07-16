package ru.job4j.domain;

/**
 * SaleOrder.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sale_order")
public class SaleOrder {
    private int id;
    private String title;
    private String description;
    private boolean sale;
    private User author;
    private Timestamp created;
    private City city;
    private long price;
    private Car car;
    private Photo photo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "sale")
    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    @OneToOne
    @JoinColumn(name = "author_id", nullable = false)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @OneToOne
    @JoinColumn(name = "city_id", nullable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Column(name = "price")
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @OneToOne
    @JoinColumn(name = "car_id", nullable = false)
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @OneToOne
    @JoinColumn(name = "photo_id")
    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}