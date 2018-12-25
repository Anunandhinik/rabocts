package com.cogniz.interv.rabobank.manoj.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cogniz.interv.rabobank.manoj.model.StatementErrorRecord;
import com.cogniz.interv.rabobank.manoj.model.utils.Constants;
import com.cogniz.interv.rabobank.manoj.protocol.StatementErrorRecordResponse;
import com.cogniz.interv.rabobank.manoj.protocol.StatementRecordResponse;

@Component
public class StatementResponseConverter implements Converter<List<StatementErrorRecord>, StatementRecordResponse>
{

  @Override
  public StatementRecordResponse convert(List<StatementErrorRecord> source)
  {
    StatementRecordResponse result = new StatementRecordResponse();
    if(source == null || source.isEmpty())
    {
      result.setResult(Constants.RESULTS_OK);
    }
    else
    {
      result.setResult(Constants.RESULTS_ERROR);
      List<StatementErrorRecordResponse> errorReference = new ArrayList<>();
      source.stream().forEach((record) -> {
        StatementErrorRecordResponse error = new StatementErrorRecordResponse();
        error.setReferenceNumber(record.getStatementRecord().getReference());
        error.setAccountNumber(record.getStatementRecord().getAccountNumber());
        error.setReason(record.getErrorReason().getReasonDescription());
        errorReference.add(error);
      });
      result.setErrorRecords(errorReference);
    }
    return result;
  }

}
