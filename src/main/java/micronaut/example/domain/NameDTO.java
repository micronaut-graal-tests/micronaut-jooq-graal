package micronaut.example.domain;

import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class NameDTO {

    private String name;

    public NameDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
