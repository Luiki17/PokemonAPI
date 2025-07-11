package com.pokedex.app.pokemon;

import com.pokedex.app.pokemon.client.PokemonClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {


    //Con todos los cambios que se hicieron es super simple testear lo que hace el servicio
    @InjectMocks
    private PokemonService service;

    @Mock
    PokemonClient client;

    @Mock
    PokemonRepository repository;


    @Test
    @DisplayName("When it looks for a pokemon that does not exist in the database it should look for it on the API and save it on the database")
    void findInTheApi() {
        when(repository.findByName("bulbasaur")).thenReturn(Optional.empty());

        PokemonEntity bulbasaur = bulbasaurEntity();
        when(client.getPokemonByName("bulbasaur")).thenReturn(bulbasaur);
        when(repository.save(bulbasaur)).thenReturn(bulbasaur);

        PokemonEntity response = service.savePokemon("bulbasaur");

        assertEquals(bulbasaur, response);
        verify(repository).save(bulbasaur);
        verify(client).getPokemonByName("bulbasaur");
    }

    @Test
    @DisplayName("When it looks for a pokemon that exists in the database it should not look for it on the API and return the value from the database")
    void findInTheDb() {
        PokemonEntity bulbasaur = bulbasaurEntity();
        when(repository.findByName("bulbasaur")).thenReturn(Optional.of(bulbasaur));


        PokemonEntity response = service.savePokemon("bulbasaur");

        assertEquals(bulbasaur, response);
        verify(repository,never()).save(bulbasaur);
        verify(client,never()).getPokemonByName("bulbasaur");
    }

    private static PokemonEntity bulbasaurEntity() {
        return PokemonEntity
                .builder()
                .id(1)
                .name("bulbasaur")
                .height(7)
                .weight(69)
                .build();
    }
}