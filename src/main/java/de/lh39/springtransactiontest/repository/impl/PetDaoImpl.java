package de.lh39.springtransactiontest.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.lh39.springtransactiontest.entity.Pet;
import de.lh39.springtransactiontest.repository.PetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author lh39
 */
@Repository
public class PetDaoImpl implements PetDao {

  private final EntityManager entityManager;

  @Autowired
  public PetDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Pet> findByName(String name) {
    CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
    CriteriaQuery<Pet> criteriaQuery = criteriaBuilder.createQuery(Pet.class);
    Root<Pet> root = criteriaQuery.from(Pet.class);
    criteriaQuery.where(criteriaBuilder.equal(root.get("name"), "Pinky"));
    TypedQuery<Pet> query = this.entityManager.createQuery(criteriaQuery);
    return query.getResultList();
  }

  @Override
  public Pet save(Pet pet) {
    this.entityManager.persist(pet);
    return pet;
  }

}
