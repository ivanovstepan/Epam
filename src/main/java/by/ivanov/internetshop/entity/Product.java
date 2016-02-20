package by.ivanov.internetshop.entity;


public class Product {
    private Long id_product;
    private String description;
    private int price;
    private int amount;

    public Long getId_product() {
        return id_product;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }


    public Product() {
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product(int amount, int price, String description, Long id_product) {
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.id_product = id_product;
    }
}
