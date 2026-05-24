package com.example.myorders;

public class OrderModel {
    private String vehicleType;
    private String price;
    private String dateTime;
    private String orderId;
    private String shortAddress;
    private String fullAddress;
    private String status;

    public OrderModel(String vehicleType, String price, String dateTime, String orderId,
                      String shortAddress, String fullAddress, String status) {
        this.vehicleType = vehicleType;
        this.price = price;
        this.dateTime = dateTime;
        this.orderId = orderId;
        this.shortAddress = shortAddress;
        this.fullAddress = fullAddress;
        this.status = status;
    }


    public String getVehicleType() { return vehicleType; }
    public String getPrice() { return price; }
    public String getDateTime() { return dateTime; }
    public String getOrderId() { return orderId; }
    public String getShortAddress() { return shortAddress; }
    public String getFullAddress() { return fullAddress; }
    public String getStatus() { return status; }
}