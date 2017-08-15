package de.lh39.springtransactiontest.component;

import de.lh39.springtransactiontest.entity.Pet;
import de.lh39.springtransactiontest.repository.PetDao;
import de.lh39.springtransactiontest.repository.PetRepository;
import de.lh39.springtransactiontest.service.PetService;
import de.lh39.springtransactiontest.type.AnimalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Component to help examine the spring transaction behavior. Wraps different ways to call the
 * persistence layer in separate methods. Offers a way to perform transaction operations.
 *
 * @author lh39
 */
@Component
public class TransactionTestComponent {

  private final PetService petService;
  private final PetRepository petRepository;
  private final PetDao petDao;

  @Autowired
  public TransactionTestComponent(PetService petService, PetRepository petRepository,
      PetDao petDao) {
    this.petService = petService;
    this.petRepository = petRepository;
    this.petDao = petDao;
  }

  @Transactional
  public void doBothServiceAndRepository() {
    this.saveOctocatWithAge7ViaService();
    this.updateOctocatToAge8ViaRepository();
  }

  @Transactional
  public void doBothMixedServiceAndDAO() {
    this.saveOctocatWithAge7ViaService();
    this.updateOctocatToAge8ViaDAO();
  }

  /**
   * Save octocat via service layer (wraps spring data).
   */
  public void saveOctocatWithAge7ViaService() {
    Pet octocat = new Pet(AnimalType.CAT, "Octocat", 7);
    this.petService.savePet(octocat);
  }

  /**
   * Update octocat via repository layer (spring data).
   */
  public void updateOctocatToAge8ViaRepository() {
    Pet octocat = this.petRepository.findByName("Octocat").get(0);
    octocat.setAge(8);
    this.petRepository.save(octocat);
    throw new RuntimeException("octocat sad...");
  }

  /**
   * Update octocat via dao layer (entitymanager).
   */
  public void updateOctocatToAge8ViaDAO() {
    Pet octocat = this.petRepository.findByName("Octocat").get(0);
    octocat.setAge(9);
    this.petDao.save(octocat);
    throw new RuntimeException("octocat still sad...");
  }

}
