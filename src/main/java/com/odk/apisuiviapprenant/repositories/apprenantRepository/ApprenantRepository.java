package com.odk.apisuiviapprenant.repositories.apprenantRepository;

import com.odk.apisuiviapprenant.models.apprenantModel.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
}
