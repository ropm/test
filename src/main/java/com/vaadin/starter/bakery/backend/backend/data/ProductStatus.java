package com.vaadin.starter.bakery.backend.data;

public enum ProductStatus {
    IN_STOCK("In Stock"), OUT_OF_STOCK("Out of Stock");

    private String description;

    ProductStatus(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
