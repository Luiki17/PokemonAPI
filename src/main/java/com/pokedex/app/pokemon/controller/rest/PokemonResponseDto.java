package com.pokedex.app.pokemon.controller.rest;

import com.pokedex.app.pokemon.PokemonEntity;
import lombok.Builder;

import java.util.List;


/**
 * Esta clase más que nada es para separar las respuestas de la bd y de la api externa, y que puedas modelarla a lo que te convenga mas a nivel de definiciones con el equipo de front (en caso de que tengas), sin que te afecte en la bd o cualquier otro componente. En caso de que tenga exactamente los mismos campos que el entity puede que no sea necesario, pero como es un ejemplo debería estar ok.
 */
@Builder
record PokemonResponseDto(int id, String name, int height, int weight, List<String> types) {

    /**
     * al ser entidades pequeñas tal vez no es necesario dejar el mapeo externalizado, además de que tienes la ventaja de ver las entrañas del objeto que mapeas, sin obligarte a dejar todos los campos públicos
     */
    static PokemonResponseDto fromEntity(PokemonEntity entity) {
        return builder()
                .id(entity.getId())
                .name(entity.getName())
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .types(entity.getTypes())
                .build();
    }
}
