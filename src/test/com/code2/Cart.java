package test.com.code2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import test.com.code.Product;

public class Cart {

    private final UUID id;

    private final List<CartItem> items =
        new ArrayList<>();

    public Cart() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public List<CartItem> getItems() {
        return List.copyOf(items);
    }

    // Ajouter un produit au panier
    public void addProduct(Product product, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "La quantité doit être positive"
            );
        }

        // Vérifier si le produit existe déjà
        for (CartItem item : items) {

            if (item.getProduct().getId()
                    .equals(product.getId())) {

                item.setQuantity(
                    item.getQuantity() + quantity
                );

                return;
            }
        }

        // Le produit n'existe pas encore dans le panier
        CartItem item = new CartItem(product, quantity);

        items.add(item);
    }

    // Supprimer un produit du panier
    public void removeProduct(UUID productId) {

        items.removeIf(item ->
            item.getProduct().getId().equals(productId)
        );
    }

    // Modifier la quantité d'un produit
    public void updateQuantity(
            UUID productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "La quantité doit être positive"
            );
        }

        for (CartItem item : items) {

            if (item.getProduct().getId()
                    .equals(productId)) {

                item.setQuantity(quantity);
                return;
            }
        }

        throw new IllegalArgumentException(
            "Produit introuvable dans le panier"
        );
    }

    // Calculer le montant total du panier
    public BigDecimal getTotal() {

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : items) {
            total = total.add(item.getSubTotal());
        }

        return total;
    }

    // Vider le panier
    public void clear() {
        items.clear();
    }
}
