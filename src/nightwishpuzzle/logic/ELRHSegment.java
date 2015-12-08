/* ///////////////////////////////////////////////////////////////////////
 * //                                                                   //
 * //                          NIGHTWISH PUZZLE                         //
 * //                     Simple puzzle game in Java                    //
 * //                                                                   //
 * //                      Copyright © Ellrohir 2011                    //
 * //                                                                   //
 * //                                                                   //
 * //    File Info                                                      //
 * // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * //
 * // *  Author:         *  Ellrohir [ ellrohir@seznam.cz ]           * //
 * // *  Homepage:       *  http://ellrohir.mzf.cz                    * //
 * // *  File:           *  ELRHSegment.java                          * //
 * // *  Purpose:        *  Class ELRHSegment definition              * //
 * // *  System Version: *  1.0                                       * //
 * // *  Last Modified:  *  2011-12-11 2215 GMT+1                     * //
 * // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * //
 * ///////////////////////////////////////////////////////////////////////
 */

package nightwishpuzzle.logic;

import javax.swing.JButton;

/**
 * ELRHSegment - Represents the piece of image to be put together in the puzzle.
 * It is practically a JButton, only with two extra "position" parameters which 
 * determine the segment's position in the puzzle grid.
 * 
 * @see JButton
 *
 * @author Ellrohir
 * @version 1.0
 */
public class ELRHSegment extends JButton {

    // ************************** \\
    // *        CONSTANTS       * \\
    // ************************** \\

    // ************************** \\
    // *       PROPERTIES       * \\
    // ************************** \\
    
    /**
     * The x-coord of the segment.
     */
    private int posX = 0;
    
    /**
     * The y-coord of the segment.
     */
    private int posY = 0;

    // ************************** \\
    // *      CONSTRUCTORS      * \\
    // ************************** \\
    
    /**
     * Standard constructor.
     * 
     * @param posX - initial x-coord
     * @param posY - initial y-coord
     */
    public ELRHSegment(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
    }

    // ************************** \\
    // *     ACCESS METHODS     * \\
    // ************************** \\

    /**
     * Getter for posX property.
     * 
     * @return current x-coord of the segment
     */
    public int getPosX() {
        return posX;
    }
    
    /**
     * Getter for posY property.
     * 
     * @return current y-coord of the segment
     */
    public int getPosY() {
        return posY;
    }
    
    /**
     * Setter for posX propery.
     * 
     * @param posX - new x-coord of the segment
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Setter for posY propery.
     * 
     * @param posY - new y-coord of the segment
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    // ************************** \\
    // *     PUBLIC METHODS     * \\
    // ************************** \\

    // ************************** \\
    // *    PRIVATE METHODS     * \\
    // ************************** \\

}
