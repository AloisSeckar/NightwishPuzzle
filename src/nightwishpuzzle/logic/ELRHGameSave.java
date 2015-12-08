/* ///////////////////////////////////////////////////////////////////////
 * //                                                                   //
 * //                          NIGHTWISH PUZZLE                         //
 * //                     Simple puzzle game in Java                    //
 * //                                                                   //
 * //                   Copyright © Ellrohir 2011-2012                  //
 * //                                                                   //
 * //                                                                   //
 * //    File Info                                                      //
 * // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * //
 * // *  Author:         *  Ellrohir [ ellrohir@seznam.cz ]           * //
 * // *  Homepage:       *  http://ellrohir.mzf.cz                    * //
 * // *  File:           *  ELRHGameSave.java                         * //
 * // *  Purpose:        *  Class ELRHGameSave definition             * //
 * // *  System Version: *  1.0                                       * //
 * // *  Last Modified:  *  2012-02-12 2145 GMT+1                     * //
 * // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * //
 * ///////////////////////////////////////////////////////////////////////
 */

package nightwishpuzzle.logic;

import java.io.Serializable;

/**
 *  ELRHGameSave - Just a container for saving info about current game progress.
 * Contains only public unmodifiable properties that hold game info.
 *
 * @author Ellrohir
 * @version 1.0
 */
public class ELRHGameSave implements Serializable {
    
    // TODO javadoc

    // ************************** \\
    // *        CONSTANTS       * \\
    // ************************** \\

    // ************************** \\
    // *       PROPERTIES       * \\
    // ************************** \\
    
    public final String imgSource;
    
    public final int gridSize;
    
    public final int elapsedTime;
    
    public final int freeX;
    
    public final int freeY;
        
    public final int[][] segmentTable;

    // ************************** \\
    // *      CONSTRUCTORS      * \\
    // ************************** \\
    
    public ELRHGameSave(String img, int grid, int time, int x, int y, int[][] table) {
        this.imgSource = img;
        this.gridSize = grid;
        this.elapsedTime = time;
        this.freeX = x;
        this.freeY = y;
        this.segmentTable = table;
    }

    // ************************** \\
    // *     ACCESS METHODS     * \\
    // ************************** \\

    // ************************** \\
    // *     PUBLIC METHODS     * \\
    // ************************** \\

    // ************************** \\
    // *    PRIVATE METHODS     * \\
    // ************************** \\

}
