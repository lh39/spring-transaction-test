package de.lh39.springtransactiontest.service.impl;

import de.lh39.springtransactiontest.entity.Pet;
import de.lh39.springtransactiontest.repository.PetRepository;
import de.lh39.springtransactiontest.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Wrapper around {@link PetRepository}.
 *
 * @author lh39
 */
@Service
public class PetServiceImpl implements PetService {

  private final PetRepository petRepository;

  @Autowired
  public PetServiceImpl(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @Override
  public Pet savePet(Pet pet) {
    return this.petRepository.save(pet);
  }
}
