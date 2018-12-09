
package sds.crystalfileparser.domain.core;

import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.util.StringUtils;

public class CifRecordsIterator implements Iterator<Record> {
    
    public String Alpha = "Alpha";
    public String Beta = "Beta";
    public String Gamma = "Gamma";
    public String ChemicalFormula = "ChemicalFormula";
    public String ChemicalName = "ChemicalName";
    public String LengthA = "LengthA";
    public String LengthB = "LengthB";
    public String LengthC = "LengthC";

    private ArrayList<String> pathes = new ArrayList<String>();

    private Iterator<String> enumerator = null;
    
    public CifRecordsIterator(String path) throws Exception
    {
        pathes.add(path);

        enumerator = pathes.iterator();
    }

    @Override
    public boolean hasNext() {
        return enumerator.hasNext();
    }

    @Override
    public Record next()
    {
        JmolCrystal crystal;
        try {
            crystal = CifReader.Read(enumerator.next());
            
            ArrayList<PropertyValue> properties = new ArrayList<>();
            properties.add(new PropertyValue(ChemicalFormula, crystal.ChemicalFormula.toString()));
            if (!StringUtils.isEmpty(crystal.ChemicalName))
                properties.add(new PropertyValue(ChemicalName, crystal.ChemicalName));
            properties.add(new PropertyValue(Alpha, Double.toString(crystal.Alpha)));
            properties.add(new PropertyValue(Beta, Double.toString(crystal.Beta)));
            properties.add(new PropertyValue(Gamma, Double.toString(crystal.Gamma)));
            properties.add(new PropertyValue(LengthA, Double.toString(crystal.LengthA)));
            properties.add(new PropertyValue(LengthB, Double.toString(crystal.LengthB)));
            properties.add(new PropertyValue(LengthC, Double.toString(crystal.LengthC)));

            for(Map.Entry<String, String> aux : crystal.AuxInfo.entrySet())
            {
                properties.add(new PropertyValue(aux.getKey(), aux.getValue()));
            }              

            return new Record(crystal.Cif, 0, RecordType.Crystal, properties);
            
        } catch (IOException ex) {
            return null;
        }
        
    }
}
