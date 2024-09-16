package no.hvl.dat250.jpa.tutorial.creditcards.driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat250.jpa.tutorial.creditcards.*;

import java.sql.SQLException;

public class CreditCardsMain {

  static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";

  public static void main(String[] args) {
      try {
          org.h2.tools.Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }

      try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(
        PERSISTENCE_UNIT_NAME); EntityManager em = factory.createEntityManager()) {
      em.getTransaction().begin();
      createObjects(em);
      em.getTransaction().commit();

    }

  }

  private static void createObjects(EntityManager em) {
    Customer customer = new Customer();
    customer.setName("Max Mustermann");
    Address address = new Address();
    address.setStreet("Inndalsveien");
    address.setNumber(28);
    address.addOwner(customer);
    customer.addAddress(address);
    Bank bank = new Bank();
    bank.setName("Pengebank");
    CreditCard cardOne = new CreditCard();
    CreditCard cardTwo = new CreditCard();
    bank.addCreditCard(cardOne);
    bank.addCreditCard(cardTwo);
    customer.addCreditCard(cardOne);
    customer.addCreditCard(cardTwo);
    cardOne.setBank(bank);
    cardTwo.setBank(bank);
    cardOne.setNumber(12345);
    cardTwo.setNumber(123);
    cardOne.setBalance(-5000);
    cardTwo.setBalance(1);
    cardOne.setCreditLimit(-10000);
    cardTwo.setCreditLimit(2000);
    Pincode pin = new Pincode();
    pin.setCode("123");
    pin.setCount(1);
    cardOne.setPincode(pin);
    cardTwo.setPincode(pin);
    em.persist(customer);
    em.persist(address);
    em.persist(bank);
    em.persist(cardOne);
    em.persist(cardTwo);
    em.persist(pin);
  }
}
