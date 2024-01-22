package com.example.examen;

public class LugarModel {
    private long id;
    private String name;
    private int visitOrder;
    private String imageUrl;
    private double latitude;
    private double longitude;
    private double accommodationCost;
    private double transportCost;
    private String additionalComments;

    // Constructor
    public LugarModel() {
    }

    public LugarModel(long id, String name, int visitOrder, String imageUrl, double latitude, double longitude,
                      double accommodationCost, double transportCost, String additionalComments) {
        this.id = id;
        this.name = name;
        this.visitOrder = visitOrder;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.accommodationCost = accommodationCost;
        this.transportCost = transportCost;
        this.additionalComments = additionalComments;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitOrder() {
        return visitOrder;
    }

    public void setVisitOrder(int visitOrder) {
        this.visitOrder = visitOrder;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAccommodationCost() {
        return accommodationCost;
    }

    public void setAccommodationCost(double accommodationCost) {
        this.accommodationCost = accommodationCost;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }

    public void setAdditionalComments(String additionalComments) {
        this.additionalComments = additionalComments;
    }
}

