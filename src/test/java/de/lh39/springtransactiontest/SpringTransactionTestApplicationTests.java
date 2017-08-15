package de.lh39.springtransactiontest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import de.lh39.springtransactiontest.component.TransactionTestComponent;
import de.lh39.springtransactiontest.entity.Pet;
import de.lh39.springtransactiontest.repository.PetRepository;
import de.lh39.springtransactiontest.type.AnimalType;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lh39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTransactionTestApplicationTests {

  @Autowired
  TransactionTestComponent transactionTestComponent;
  @Autowired
  PetRepository petRepository;

  @Test
  @DirtiesContext
  public void testPersistence() {
    // persist some pets for testing
    this.petRepository.save(new Pet(AnimalType.CAT, "Pinky", 10));
    this.petRepository.save(new Pet(AnimalType.DOG, "Brain", 11));
    this.petRepository.save(new Pet(AnimalType.MOUSE, "Jelly", 12));

    // retrieve all pets
    assertEquals(3, Lists.newArrayList(this.petRepository.findAll()).size());
    // fetch pet by id
    Pet pet1 = this.petRepository.findOne(1L);
    assertEquals(pet1.getName(), "Pinky");

    // fetch by name
    List<Pet> pinkyCats = this.petRepository.findByName("Pinky");
    assertEquals(pinkyCats.size(), 1);
    assertEquals(pinkyCats.get(0).getAge().intValue(), 10);
  }

  /**
   * Perform a test to examine spring transaction behavior. <br>
   * Therefore we are going to first save octocat via service layer, which is annotated as
   * transactional. Hereafter we are going to call a method to update the age of octocat via
   * repository layer. This method however will thrown a runtime exception. Afterwards we will
   * examine if the transaction rolled back accordingly.
   */
  @Test
  @DirtiesContext
  public void testTransactionBehaviorWithRepositoryDoesRollBack() {
    try {
      this.transactionTestComponent.doBothServiceAndRepository();
    } catch (Exception e) {
      // swallow
    }
    List<Pet> octocats = this.petRepository.findByName("Octocat");
    assertEquals(0, octocats.size());
  }

  /**
   * Perform a test to examine spring transaction behavior. <br>
   * Therefore we are going to first save octocat via service layer, which is annotated as
   * transactional. Hereafter we are going to call a method to update the age of octocat via a
   * custom dao layer which uses entitymanager. This method however will thrown a runtime exception.
   * Afterwards we will examine if the transaction rolled back accordingly.
   */
  @Test
  @DirtiesContext
  public void testTransactionBehaviorWithRepositoryAndDaoMixedDoesRollBack() {
    try {
      this.transactionTestComponent.doBothMixedServiceAndDAO();
    } catch (Exception e) {
      // swallow
    }
    List<Pet> octocats = this.petRepository.findByName("Octocat");
    assertEquals(0, octocats.size());
  }
}
