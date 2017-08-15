package de.lh39.springtransactiontest.repository;

import java.util.List;

import de.lh39.springtransactiontest.entity.Pet;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implicitly marked with {@link Transactional}.
 * 
 * @see SimpleJpaRepository
 * @author lh39
 */
public interface PetRepository extends CrudRepository<Pet, Long> {
  List<Pet> findByName(String name);
}
