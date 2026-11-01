package test.com.code;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Category implements CatalogNode{
	    private final UUID id;
	    private String name;
	    private String description;

	    private final List<CatalogNode> children =
	            new ArrayList<>();

	    public Category(String name, String description) {
	        this.id = UUID.randomUUID();
	        this.name = name;
	        this.description = description;
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

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public List<CatalogNode> getChildren() {
	        return List.copyOf(children);
	    }

	    public void add(CatalogNode node) {
	        children.add(node);
	    }

	    public boolean remove(UUID id) {
	        return children.removeIf(
	            child -> child.getId().equals(id)
	        );
	    }
}
