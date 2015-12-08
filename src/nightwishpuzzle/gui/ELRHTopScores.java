/* ///////////////////////////////////////////////////////////////////////
 * //                                                                   //
 * //                              SOFTMAN                              //
 * //                   Softball team manager PC game                   //
 * //                                                                   //
 * //                   Copyright © Ellrohir 2011-2012                  //
 * //                                                                   //
 * //                                                                   //
 * //    File Info                                                      //
 * // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * //
 * // *  Author:         *  Ellrohir [ ellrohir@seznam.cz ]           * //
 * // *  Homepage:       *  http://ellrohir.xf.cz                     * //
 * // *  File:           *  ELRHTopScores.java                              * //
 * // *  Purpose:        *  Class ELRHTopScores definition                  * //
 * // *  System Version: *  1.0                                       * //
 * // *  Last Modified:  *  2011-MM-DD HHMM GMT+1                     * //
 * // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * //
 * ///////////////////////////////////////////////////////////////////////
 */
package nightwishpuzzle.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import nightwishpuzzle.logic.ELRHScores;
import nightwishpuzzle.gui.stats.ELRHScoresTableModel;

/**
 *  ELRHTopScores - //TODO class description
 *
 * @author Ellrohir
 * @version 1.0
 */
public class ELRHTopScores {

    // ************************** \\
    // *        CONSTANTS       * \\
    // ************************** \\

    // ************************** \\
    // *       PROPERTIES       * \\
    // ************************** \\
    
    private JDialog mainWin;
    
    private ELRHScores topScores;

    // ************************** \\
    // *      CONSTRUCTORS      * \\
    // ************************** \\
    
    public ELRHTopScores(JFrame parent, ELRHScores scores) {
        this.mainWin = new JDialog(parent, "Nightwish Puzzle - Top scores");
        this.topScores = scores;
        
        JTable scoresIMG3 = prepareScoresTable(1);
        JTable scoresIMG4 = prepareScoresTable(2); 
        JTable scoresIMG6 = prepareScoresTable(3); 
        JTable scoresDPP3 = prepareScoresTable(4); 
        JTable scoresDPP4 = prepareScoresTable(5); 
        JTable scoresDPP6 = prepareScoresTable(6); 

        JPanel dppPanel = new JPanel();
        dppPanel.setLayout(new BoxLayout(dppPanel, BoxLayout.Y_AXIS));
        dppPanel.add(new JLabel("Dark Passion Play 3x3"));
        dppPanel.add(scoresDPP3);
        dppPanel.add(new JLabel("Dark Passion Play 4x4"));
        dppPanel.add(scoresDPP4);
        dppPanel.add(new JLabel("Dark Passion Play 6x6"));
        dppPanel.add(scoresDPP6);
        
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new BoxLayout(imgPanel, BoxLayout.Y_AXIS));
        imgPanel.add(new JLabel("Imaginaerum 3x3"));
        imgPanel.add(scoresIMG3);
        imgPanel.add(new JLabel("Imaginaerum 4x4"));
        imgPanel.add(scoresIMG4);
        imgPanel.add(new JLabel("Imaginaerum 6x6"));
        imgPanel.add(scoresIMG6);
        
        mainWin.setModal(true);
        mainWin.setLayout(new FlowLayout());
        mainWin.add(dppPanel);
        mainWin.add(Box.createRigidArea(new Dimension(20, 5)));
        mainWin.add(imgPanel);
        mainWin.pack();
        // TODO better window pos
        mainWin.setLocation(200,100);
        mainWin.setResizable(false);
    }

    // ************************** \\
    // *     ACCESS METHODS     * \\
    // ************************** \\

    // ************************** \\
    // *     PUBLIC METHODS     * \\
    // ************************** \\
    
    public void setVisible(boolean visible) {
        this.mainWin.setVisible(visible);
    }

    // ************************** \\
    // *    PRIVATE METHODS     * \\
    // ************************** \\
    
    private JTable prepareScoresTable(int index) {
        
        // get table model for current table
        // values depend on the index
        ELRHScoresTableModel model = new ELRHScoresTableModel(topScores.returnTopScores(index));     
        
        // prepare table
        JTable table = new JTable(model); 
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(125);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
           
        // cell alignment
        // this code inspired at:
        // http://stackoverflow.com/questions/2408541/align-the-values-of-the-cells-in-jtable
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        // return generated table
        return table;
    }

}
