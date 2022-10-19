package hiber.model;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Car(String model, int series) {   //КОНСТРУКТОР БЕЗ ЮЗЕРА
        this.model = model;
        this.series = series;
    }

    public Car(User user, String model, int series) {   //КОНСТРУКТОР С ЮЗЕРОМ
        this.user = user;
        this.model = model;
        this.series = series;
    }

    public Car() { }

    private String model;
    private int series;

    public User getUser() {
        return user;
    }

    public void setUser(User user) { this.user = user; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id/* +
                ", user=" + user */ +
                ", model='" + model + '\'' +
                ", series=" + series +
                '}';
    }
}
