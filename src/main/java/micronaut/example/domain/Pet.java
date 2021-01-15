package micronaut.example.domain;

import io.micronaut.core.annotation.Introspected;

import java.util.UUID;

@Introspected
public class Pet {

    private UUID id;
    private String name;
    private PetType type = PetType.DOG;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public enum PetType {
        DOG,
        CAT
    }
}
