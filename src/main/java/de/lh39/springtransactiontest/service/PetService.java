package de.lh39.springtransactiontest.service;

import de.lh39.springtransactiontest.entity.Pet;

/**
 * @author lh39
 */
public interface PetService {
  Pet savePet(Pet pet);
}
