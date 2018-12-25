package com.cogniz.interv.rabobank.manoj.validators.impl;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cogniz.interv.rabobank.manoj.model.StatementErrorRecord;
import com.cogniz.interv.rabobank.manoj.model.StatementRecord;
import com.cogniz.interv.rabobank.manoj.model.utils.ErrorReason;

public class StatementRecordReferenceFirstValidatorTest
{

  @Test
  public void testValidateStatements() throws Exception
  {
    List<StatementRecord> records = new ArrayList<>();
    Constructor<StatementRecord> strRec = StatementRecord.class.getDeclaredConstructor();
    strRec.setAccessible(true);
    StatementRecord str1 = strRec.newInstance();
    str1.setAccountNumber("acc1");
    str1.setReference(111);
    str1.setStartBalance(new BigDecimal("1"));
    str1.setMutation(new BigDecimal("1"));
    str1.setEndBalance(new BigDecimal("2"));
    
    StatementRecord str2 = strRec.newInstance();
    str2.setAccountNumber("acc2");
    str2.setReference(112);
    str2.setStartBalance(new BigDecimal("1"));
    str2.setMutation(new BigDecimal("1"));
    str2.setEndBalance(new BigDecimal("1"));
    
    StatementRecord str3 = strRec.newInstance();
    str3.setAccountNumber("acc3");
    str3.setReference(112);
    str3.setStartBalance(new BigDecimal("1"));
    str3.setMutation(new BigDecimal("1"));
    str3.setEndBalance(new BigDecimal("2"));
    
    records.add(str1);
    records.add(str2);
    records.add(str3);
    
    StatementRecordReferenceFirstValidator refVal = new StatementRecordReferenceFirstValidator();
    List<StatementErrorRecord> errors = refVal.validateStatements(records);
    Assert.assertTrue(errors.size()==2);
    Assert.assertEquals("acc2", errors.get(0).getStatementRecord().getAccountNumber());
    Assert.assertEquals(ErrorReason.MUTATION_ERROR, errors.get(0).getErrorReason());
    Assert.assertEquals("acc3", errors.get(1).getStatementRecord().getAccountNumber());
    Assert.assertEquals(ErrorReason.DUPLICATE_REF, errors.get(1).getErrorReason());
    
  }
}
