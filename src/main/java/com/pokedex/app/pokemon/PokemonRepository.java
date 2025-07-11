package com.pokedex.app.pokemon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface PokemonRepository extends JpaRepository<PokemonEntity, Integer> {
    List<PokemonEntity> findByTypesContaining(String type);

    Optional<PokemonEntity> findByName(String name);
}
