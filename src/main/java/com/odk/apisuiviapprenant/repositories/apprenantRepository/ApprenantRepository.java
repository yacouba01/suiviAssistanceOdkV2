package com.odk.apisuiviapprenant.repositories.apprenantRepository;

import com.odk.apisuiviapprenant.models.apprenantModel.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {

    Optional<Apprenant> findApprenantByLoginAndMotDePass(String login, String motDePasse);
}
