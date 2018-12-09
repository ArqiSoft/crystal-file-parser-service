/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sds.crystalfileparser.domain.core;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JmolCrystal {
    
    public JmolCrystal()
    {
        AuxInfo =  new HashMap<String, String>();
    }

    public String Cif = "";
    public String ChemicalName = "";
    public String ChemicalFormula = "";
    public double Alpha;
    public double Beta;
    public double Gamma;
    public double LengthA;
    public double LengthB;
    public double LengthC;
    public Iterable<JmolAtom> Atoms;
    public Iterable<JmolBond> Bonds;
    public Map<String, String> AuxInfo;
}
