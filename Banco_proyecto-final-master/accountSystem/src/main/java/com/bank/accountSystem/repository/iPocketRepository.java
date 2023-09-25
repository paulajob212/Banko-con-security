package com.bank.accountSystem.repository;

import com.bank.accountSystem.model.Pocket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iPocketRepository extends JpaRepository<Pocket,Integer> {
    Pocket findByPocketNumber(String pocketNumber);
}
