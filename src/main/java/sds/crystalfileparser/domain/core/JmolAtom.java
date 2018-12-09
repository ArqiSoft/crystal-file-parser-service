/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sds.crystalfileparser.domain.core;

/**
 *
 * @author Александр
 */
class JmolAtom {
    
    public String Symbol;
    public String AtomName;
    public float X;
    public float Y;
    public float Z;

    public JmolAtom(String Symbol, String AtomName, float X, float Y, float Z) {
        this.Symbol = Symbol;
        this.AtomName = AtomName;
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }
    
    
}
