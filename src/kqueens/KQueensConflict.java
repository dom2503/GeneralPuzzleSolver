/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kqueens;

import generalpuzzlesolver.puzzle.Conflict;

/**
 *
 * @author pablolistegarcia
 */
public class KQueensConflict implements Conflict{
    private int row;
  private int column;

  
  public KQueensConflict(int row, int column){
    this.row = row;
    this.column = column;

  }
  
  public int getRow(){
    return this.row;
  }
  
  public int getColumn(){
    return this.column;
  }
}
