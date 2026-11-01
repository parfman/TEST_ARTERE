package test.com.code;

import java.math.BigDecimal;
import java.util.UUID;

public class Product implements CatalogNode {
	private final UUID id;
    private String name;
    private BigDecimal price;
    private int stockQuantity;

    public Product(String name,
                   BigDecimal price,
                   int stockQuantity) {

        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int quantity) {
        this.stockQuantity = quantity;
    }

}
