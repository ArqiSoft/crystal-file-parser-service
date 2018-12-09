/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sds.crystalfileparser.domain.core;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.jmol.adapter.smarter.AtomSetCollection;
import org.jmol.adapter.smarter.AtomSetCollectionReader;
import org.jmol.adapter.smarter.SmarterJmolAdapter;
import org.springframework.util.StringUtils;

public class CifReader {
    
    public static JmolCrystal Read(String path) throws FileNotFoundException, IOException
    {
        JmolCrystal crystal = new JmolCrystal();

        try (Scanner input = new Scanner(new FileInputStream(path))) {
            String lines = "";
            while (input.hasNextLine()) {
                lines += input.nextLine() + '\n';
            }
            crystal.Cif = lines;
        }

        BufferedReader buffReader = new BufferedReader(new FileReader(path));

        Map parameters = new Hashtable();

        Object ascr = SmarterJmolAdapter.staticGetAtomSetCollectionReader(path, null, buffReader, parameters);
        if (ascr instanceof AtomSetCollectionReader)
        {
            AtomSetCollectionReader ascrTipazed = as(AtomSetCollectionReader.class, ascr);
           
            AtomSetCollection asc = as(AtomSetCollection.class, SmarterJmolAdapter.staticGetAtomSetCollection(ascrTipazed));

            if(asc != null)
            {
                Map auxInfo = new HashMap<>();

                if (asc.atoms != null)
                {
                    crystal.Atoms = Arrays.asList(asc.atoms).stream().filter(p-> p != null)
                            .map(atom -> new JmolAtom(atom.elementSymbol, atom.atomName, atom.x, atom.y, atom.z))
                            .collect(Collectors.toList());
                }

                if (asc.bonds != null)
                {        
                    crystal.Bonds =  Arrays.asList(asc.bonds).stream().filter(p-> p != null)
                            .map(bond -> new JmolBond(bond.atomIndex1, bond.atomIndex2))
                            .collect(Collectors.toList());
                }

                for (Map<String, Object> atomSetAuxiliaryInfo : asc.atomSetAuxiliaryInfo) {
                    Map info = atomSetAuxiliaryInfo;
                    if (info != null)
                    {
                        Object[] keys = info.keySet().toArray();

                        for (Object key : keys) {
                            Object val = info.get(key);

                            if (val == null)
                                continue;

                            if (key.equals("chemicalName"))
                            {
                                crystal.ChemicalName = val.toString();
                            }
                            else if (key.equals("formula"))
                            {
                                crystal.ChemicalFormula = val.toString();
                            }
                            else if (key.equals("unitCellParams"))
                            {
                                float[] array = (float[])val ;

                                if(Array.getLength(array) >= 6)
                                {
                                    crystal.LengthA = array[0];
                                    crystal.LengthB = array[1];
                                    crystal.LengthC = array[2];
                                    crystal.Alpha = array[3];
                                    crystal.Beta = array[4];
                                    crystal.Gamma = array[5];
                                }
                            }
                            
                            else if (val instanceof Integer || val instanceof Boolean || val instanceof String) {
                                if (StringUtils.isEmpty(val.toString()) || val.toString().equals("null") || val.toString().equals("?"))
                                    continue;
                                crystal.AuxInfo.put(key.toString(), val.toString());
                            }
                        }
                    }
                }
            }
        }
        return crystal;
    }
    
    private static <T> T as(Class<T> tClass, Object o){
        if(tClass.isInstance(o)){
            return tClass.cast(o);
        }
        return null;
    }
}
