package model.pojo;

public class Order {

    private Integer orderId;
    private Integer userId;
    private Tour tour;

    public Order(Tour tour) {

        orderId = null;
        userId = null;
        this.tour = tour;
    }

    public Order(int orderId, int userId, Tour tour) {

        this.orderId = orderId;
        this.userId = userId;
        this.tour = tour;
    }

    public int getUserId() {
        return userId;
    }

    public Tour getTour() {
        return tour;
    }

}