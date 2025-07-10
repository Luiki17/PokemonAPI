package com.pokedex.pokemon.mapper;

import com.pokedex.pokemon.dto.PokemonResponseDTO;
import com.pokedex.pokemon.entity.PokemonEntity;

import java.util.List;

public class PokemonMapper {

    public static PokemonResponseDTO fromEntity(PokemonEntity entity) {
        PokemonResponseDTO dto = new PokemonResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setHeight(entity.getHeight());
        dto.setWeight(entity.getWeight());

        List<PokemonResponseDTO.TypeSlot> types = entity.getTypes().stream().map(typeName -> {
            PokemonResponseDTO.TypeSlot typeSlot = new PokemonResponseDTO.TypeSlot();
            PokemonResponseDTO.Type type = new PokemonResponseDTO.Type();
            type.setName(typeName);
            typeSlot.setType(type);
            return typeSlot;
        }).toList();

        dto.setTypes(types);
        return dto;
    }

}
