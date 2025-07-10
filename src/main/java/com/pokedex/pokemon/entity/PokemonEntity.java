package com.pokedex.pokemon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonEntity {

    @Id
    private int id;

    private String name;
    private int height;
    private int weight;

    @ElementCollection
    private List<String> types;
}
