package com.sds.osdr.crystalparser.tests;

import sds.crystalfileparser.domain.core.CifParser;
import sds.crystalfileparser.domain.core.Record;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.junit.Test;
import org.springframework.util.Assert;

public class CrystalParserTests {
    
    
    @Test
    public void testMethodForCif() throws FileNotFoundException, IOException {
        CifParser parser = new CifParser(new FileInputStream("1100119.cif"));
        Iterator<Record> i = parser.iterator();
        int count = 0;
        while(i.hasNext()){
            Record record = i.next();
            Assert.isInstanceOf(Record.class, record);
            count++;
        }
        Assert.isTrue(count == 1);
    }
}
