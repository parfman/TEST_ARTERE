package test.com.code2;

import java.math.BigDecimal;
import java.util.UUID;

import test.com.code.Product;

public class CartItem {

    private final UUID id;
    private final Product product;

    // Prix mémorisé au moment de l'ajout
    private final BigDecimal unitPrice;

    private int quantity;

    public CartItem(Product product, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "La quantité doit être supérieure à zéro"
            );
        }

        this.id = UUID.randomUUID();
        this.product = product;
        this.unitPrice = product.getPrice();
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "La quantité doit être supérieure à zéro"
            );
        }

        this.quantity = quantity;
    }

    // Calcul du sous-total de la ligne
    public BigDecimal getSubTotal() {

        return unitPrice.multiply(
            BigDecimal.valueOf(quantity)
        );
    }
}
