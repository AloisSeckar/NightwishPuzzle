/*
 * //TODO javadoc etc.
 */
package nightwishpuzzle.gui.stats;

import javax.swing.table.AbstractTableModel;
import nightwishpuzzle.logic.ELRHScoresRecord;


/**
 *
 * Special thanks to: Jakub Bartyzal (xbarj82 [at] vse.cz) for inspiration
 */
public class ELRHScoresTableModel extends AbstractTableModel {
    
    private int rowCount;
    private ELRHScoresRecord[] topScores;
    private String [] columnHeadlines = {       
        "#","User","Date","Time"
    };
    
    /**
     * Konstruktor
     * @param evid 
     */
    public ELRHScoresTableModel(ELRHScoresRecord[] topScores) {
        this.rowCount = 10;
        this.topScores = topScores;
    }
    
    /**
     * Vrací jméno sloupce
     * @param column
     * @return jmeno sloupce
     */
    @Override
    public String getColumnName(int column) {
        return columnHeadlines[column];
    }
    
    

    /**
     * Vrací počet řádků, resp záznamů v tabulce
     * @return počet řádků
     */
    @Override
    public int getRowCount() {
         return this.rowCount;
        
    }
    
    /**
     * Vrací počet sloupců
     * @return pocet sloupců
     */
    @Override
    public int getColumnCount() {
        return 4;
    }
    
    /**
     * Plní tabulku správnými daty
     * @param rowIndex
     * @param columnIndex
     * @return objekt tabulky
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {
            case 0:
                // #
                return rowIndex + 1;
            case 1:
                // name
                return this.topScores[rowIndex].getPlayerName();
            case 2:
                // date
                return this.topScores[rowIndex].getRecordDate();
            case 3:
                // time
                return this.topScores[rowIndex].getElapsedTime() + " s";
            default: 
                // should never happen...
                return null;
        }
    } 
}