package de.lh39.springtransactiontest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.lh39.springtransactiontest.type.AnimalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author lh39
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NonNull
  private AnimalType animal;
  @NonNull
  private String name;
  @NonNull
  private Integer age;
}
