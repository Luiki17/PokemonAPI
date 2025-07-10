package com.pokedex.pokemon.repository;

import com.pokedex.pokemon.entity.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<PokemonEntity, Integer> {
    boolean existsByName(String name);
    List<PokemonEntity> findByTypesContaining(String type);

}
