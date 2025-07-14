package com.pokedex.app.pokemon.client.rest;

import com.pokedex.app.pokemon.PokemonEntity;

import java.util.List;

/**
 *No es necesario que este record sea público porque solo es relevante para el contexto de la restapi, si usaras la version de Graphql incluso puede que necesites otro dto.
 */
record PokeApiDto(int id, String name, int height, int weight, List<TypeSlot> types) {

    PokemonEntity toEntity() {
        return PokemonEntity
                .builder()
                .id(id)
                .name(name)
                .height(height)
                .weight(weight)
                .types(mapTypes())
                .build();
    }

    private List<String> mapTypes() {
        return types
                .stream()
                .map(TypeSlot::typeName)
                .toList();
    }

    record TypeSlot(Type type) {

        /**
         * Pequeño ejemplo de la <a href="https://devexpert.io/blog/ley-de-demeter">ley de demeter</a>
         */
        private String typeName() {
            return type.name;
        }
    }

    record Type(String name) {
    }
}
