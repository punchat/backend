package com.github.punchat.messaging.addressee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddresseeRepository extends JpaRepository<Addressee, Long> {

}
