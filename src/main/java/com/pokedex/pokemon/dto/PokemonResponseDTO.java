package com.pokedex.pokemon.dto;

import lombok.Data;
import java.util.List;

@Data
public class PokemonResponseDTO {
    private int id;
    private String name;
    private int height;
    private int weight;
    private List<TypeSlot> types;

    @Data
    public static class TypeSlot {
        private Type type;
    }

    @Data
    public static class Type {
        private String name;
    }
}
