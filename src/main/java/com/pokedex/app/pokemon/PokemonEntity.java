package com.pokedex.app.pokemon;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;


/**
 * @@Data y jpa puede que no sean buenos amigos <a href="https://stackoverflow.com/questions/75181366/why-jpa-buddy-complains-about-data-annotation-over-jpa-entity">mira aquí</a>
 */
@Entity
@Getter
@Setter
@ToString
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                                   ((HibernateProxy) o)
                                           .getHibernateLazyInitializer()
                                           .getPersistentClass() :
                                   o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                                      ((HibernateProxy) this)
                                              .getHibernateLazyInitializer()
                                              .getPersistentClass() :
                                      this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PokemonEntity that = (PokemonEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
               ((HibernateProxy) this)
                       .getHibernateLazyInitializer()
                       .getPersistentClass()
                       .hashCode() :
               getClass().hashCode();
    }
}
