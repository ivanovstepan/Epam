package by.ivanov.internetshop.entity;


public class Product {
    private Long id_product;
    private String description;
    private int price;
    private Long amount;

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    private int userCount;

    public Long getId_product() {
        return id_product;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public Long getAmount() {
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

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Product(Long amount, int price, String description, Long id_product) {
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.id_product = id_product;
    }
    public Product(int userCount, int price, String description, Long id_product) {
        this.userCount = userCount;
        this.price = price;
        this.description = description;
        this.id_product = id_product;
    }
}
