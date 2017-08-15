package de.lh39.springtransactiontest.repository;

import de.lh39.springtransactiontest.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author lh39
 */
public interface PetDao {
    List<Pet> findByName(String name);

    Pet save(Pet pet);

}
