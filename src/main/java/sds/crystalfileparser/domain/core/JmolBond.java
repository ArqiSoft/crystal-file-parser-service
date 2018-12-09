/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sds.crystalfileparser.domain.core;


class JmolBond {
    
    public int AtomIndex1;
    public int AtomIndex2;

    public JmolBond(int AtomIndex1, int AtomIndex2) {
        this.AtomIndex1 = AtomIndex1;
        this.AtomIndex2 = AtomIndex2;
    }
}
