package com.cogniz.interv.rabobank.manoj.validators.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cogniz.interv.rabobank.manoj.model.StatementErrorRecord;
import com.cogniz.interv.rabobank.manoj.model.StatementRecord;
import com.cogniz.interv.rabobank.manoj.model.utils.Constants;
import com.cogniz.interv.rabobank.manoj.model.utils.ErrorReason;
import com.cogniz.interv.rabobank.manoj.validators.StatementRecordValidator;

/**
 * This validator gives preference to the reference number.
 * Even if the record is invalid (i.e: the mutation is wrong) the reference is taken into account
 * and any further transactions referring to the same reference will be deemed as in invalid.
 * @author ManojKumar_K
 *
 */
@Service(Constants.REFERENCE_FIRST_VALIDATOR)
public class StatementRecordReferenceFirstValidator implements StatementRecordValidator
{

  @Override
  public List<StatementErrorRecord> validateStatements(List<StatementRecord> statementOfRecords)
  {
    List<StatementRecord> previousreferences = new ArrayList<>();
    List<StatementErrorRecord> errors = new ArrayList<>();
    statementOfRecords.stream().forEach((record) -> {
      if(previousreferences.contains(record))
        errors.add(new StatementErrorRecord(record, ErrorReason.DUPLICATE_REF));
      else
      {
        previousreferences.add(record);
        if(record.getStartBalance().add(record.getMutation()).compareTo(record.getEndBalance()) != 0)
          errors.add(new StatementErrorRecord(record, ErrorReason.MUTATION_ERROR));
      }
    });
    return errors;
  }

}
