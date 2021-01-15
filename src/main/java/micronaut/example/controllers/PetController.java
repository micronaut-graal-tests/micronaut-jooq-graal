package micronaut.example.controllers;

import micronaut.example.domain.NameDTO;
import micronaut.example.domain.PetWithOwner;
import micronaut.example.repositories.PetRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller("/pets")
class PetController {

    private final PetRepository petRepository;

    PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Get("/")
    List<NameDTO> all() {
        return petRepository.list();
    }

    @Get("/{name}")
    Optional<PetWithOwner> byName(String name) throws SQLException {
        return petRepository.findByName(name);
    }
}
