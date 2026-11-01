package test.com.code;

import java.math.BigDecimal;
import java.util.UUID;

public class CatalogService {
	private final Category root;

    public CatalogService(Category root) {
        this.root = root;
    }

    // Ajouter une sous-catégorie
    public void addCategory(
            UUID parentId,
            Category category) {

        Category parent = findCategory(parentId);

        if (parent == null) {
            throw new IllegalArgumentException(
                "Catégorie parente introuvable"
            );
        }

        parent.add(category);
    }

    // Ajouter un produit
    public void addProduct(
            UUID categoryId,
            Product product) {

        Category category = findCategory(categoryId);

        if (category == null) {
            throw new IllegalArgumentException(
                "Catégorie introuvable"
            );
        }

        category.add(product);
    }

    // Rechercher une catégorie par son identifiant
    public Category findCategory(UUID id) {

        if (root.getId().equals(id)) {
            return root;
        }

        for (CatalogNode node : root.getChildren()) {

            if (node instanceof Category category) {

                Category result = findCategoryInTree(
                    category, id
                );

                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    private Category findCategoryInTree(
            Category current, UUID id) {

        if (current.getId().equals(id)) {
            return current;
        }

        for (CatalogNode child : current.getChildren()) {

            if (child instanceof Category category) {

                Category result =
                    findCategoryInTree(category, id);

                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    // Rechercher un produit dans tout le catalogue
    public Product findProduct(UUID id) {

        return findProductInTree(root, id);
    }

    private Product findProductInTree(
            Category category, UUID id) {

        for (CatalogNode child : category.getChildren()) {

            if (child instanceof Product product
                    && product.getId().equals(id)) {
                return product;
            }

            if (child instanceof Category subCategory) {

                Product result =
                    findProductInTree(subCategory, id);

                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    // Modifier une catégorie
    public void updateCategory(
            UUID id,
            String name,
            String description) {

        Category category = findCategory(id);

        if (category == null) {
            throw new IllegalArgumentException(
                "Catégorie introuvable"
            );
        }

        category.setName(name);
        category.setDescription(description);
    }

    // Modifier un produit
    public void updateProduct(
            UUID id,
            String name,
            BigDecimal price,
            int stockQuantity) {

        Product product = findProduct(id);

        if (product == null) {
            throw new IllegalArgumentException(
                "Produit introuvable"
            );
        }

        product.setName(name);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
    }

    // Supprimer une catégorie ou un produit
    public boolean deleteNode(UUID id) {

        if (root.getId().equals(id)) {
            throw new IllegalArgumentException(
                "Impossible de supprimer la racine du catalogue"
            );
        }

        return deleteFromTree(root, id);
    }

    private boolean deleteFromTree(
            Category category, UUID id) {

        // Suppression directe d'un enfant
        if (category.remove(id)) {
            return true;
        }

        // Recherche récursive dans les sous-catégories
        for (CatalogNode child : category.getChildren()) {

            if (child instanceof Category subCategory) {

                if (deleteFromTree(subCategory, id)) {
                    return true;
                }
            }
        }

        return false;
    }

}
