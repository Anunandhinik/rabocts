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
 * This validator give preference to valid transactions first (i.e mutations)
 * If there are multiple transactions having the same reference the first one which is valid will be
 * taken into account and any other ones (even those occurring before the first valid one) will be
 * deemed invalid due to mutation errors.
 * The reference of the first transaction is taken as the first occurrence of the reference.
 * @author ManojKumar_K
 *
 */
@Service(Constants.VALID_FIRST_VALIDATOR)
public class StatementRecordValidFirstValidator implements StatementRecordValidator
{

  @Override
  public List<StatementErrorRecord> validateStatements(List<StatementRecord> statementOfRecords)
  {
    List<StatementRecord> validStatements = new ArrayList<>();
    List<StatementErrorRecord> errorRecords = new ArrayList<>();

    //parallel() seems inconsistent as while validating against existing set.
    statementOfRecords.stream().forEach((recordStatement) -> {
      if (validStatements.contains(recordStatement))
      {
        errorRecords.add(new StatementErrorRecord(recordStatement, ErrorReason.DUPLICATE_REF));
      }
      else
      {
        if (recordStatement.getStartBalance().add(recordStatement.getMutation()).compareTo(recordStatement.getEndBalance()) == 0)
          validStatements.add(recordStatement);
        else
          errorRecords.add(new StatementErrorRecord(recordStatement, ErrorReason.MUTATION_ERROR));
      }
    });
    return errorRecords;
  }

}
