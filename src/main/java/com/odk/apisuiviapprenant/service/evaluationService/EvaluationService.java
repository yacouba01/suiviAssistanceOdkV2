package com.odk.apisuiviapprenant.service.evaluationService;

import com.odk.apisuiviapprenant.models.evaluationModel.Evaluation;

import java.util.List;

public interface EvaluationService {
    Evaluation addEvaluation(Evaluation evaluation);
    List<Evaluation> allEvaluation();
    Evaluation evaluationById(Long id);
    Evaluation updateEvaluation(Evaluation evaluation, Long id);
}
