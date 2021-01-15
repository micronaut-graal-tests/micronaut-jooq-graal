package micronaut.example.converters;

import micronaut.example.domain.Pet;
import org.jooq.impl.EnumConverter;

public class PetTypeConverter extends EnumConverter<String, Pet.PetType> {
    public PetTypeConverter() {
        super(String.class, Pet.PetType.class);
    }
}
