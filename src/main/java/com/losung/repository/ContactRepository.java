package com.losung.repository;


import com.losung.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ContactRepository  extends JpaRepository<Contact, Integer> {
    @Query("SELECT c FROM Contact c WHERE (:firstName IS NULL OR c.firstName = :firstName) " +
            "AND (:lastName IS NULL OR c.lastName = :lastName) " +
            "AND (:email IS NULL OR c.email = :email) " +
            "AND (:id IS NULL OR c.id = :id AND c.active = true)")
    List<Contact> findByFirstNameAndLastNameAndEmailAndId(@Param("firstName") String firstName,
                                                          @Param("lastName") String lastName,
                                                          @Param("email") String email,
                                                          @Param("id") Integer id);
    List<Contact> findAllByActive(boolean b);

    Optional<Contact> findByIdAndActive(Integer id, boolean b);
}
