/* ///////////////////////////////////////////////////////////////////////
 * //                                                                   //
 * //                          NIGHTWISH PUZZLE                         //
 * //                     Simple puzzle game in Java                    //
 * //                                                                   //
 * //                   Copyright © Ellrohir 2011-2014                  //
 * //                                                                   //
 * //                                                                   //
 * //    File Info                                                      //
 * // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * //
 * // *  Author:         *  Ellrohir [ ellrohir@seznam.cz ]           * //
 * // *  Homepage:       *  http://ellrohir.mzf.cz                    * //
 * // *  File:           *  ELRHSegmentTable.java                     * //
 * // *  Purpose:        *  Class ELRHSegmentTable definition         * //
 * // *  System Version: *  1.0                                       * //
 * // *  Last Modified:  *  2014-10-19 1610 GMT+1                     * //
 * // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * //
 * ///////////////////////////////////////////////////////////////////////
 */

package nightwishpuzzle.logic;

import nightwishpuzzle.ELRHNightwishPuzzle;

/**
 * ELRHSegmentTable - This class represents the puzzle for evaluating purposes.
 * Through simple integer representation in NxN matrix it can be easy determined
 * whether the puzzle is solved or not.
 *
 * @author Ellrohir
 * @version 1.0
 */
public class ELRHSegmentTable {

    // ************************** \\
    // *        CONSTANTS       * \\
    // ************************** \\

    // ************************** \\
    // *       PROPERTIES       * \\
    // ************************** \\
    
    /**
     * Stored dimension for the table of segments.
     */
    private final int gridSize;
    
    /**
     * The table of segments itself.
     */
    private final int[][] segmentTable;

    // ************************** \\
    // *      CONSTRUCTORS      * \\
    // ************************** \\
    
    /**
     * Standard constructor.
     * 
     * @param gridSize - the dimension for the table of segments
     */
    public ELRHSegmentTable(int gridSize) {
        // initialization
        this.gridSize = gridSize;
        segmentTable = new int[gridSize][gridSize];
        // fill values for default situation - "ordered" picture
        int value = 1;
        for (int row=0;row<gridSize;row++) {
            for (int col=0;col<gridSize;col++) {
                segmentTable[col][row] = value;
                value++;
            }
        }
        // "0" stands for empty place (by default right bottom corner)
        segmentTable[gridSize-1][gridSize-1] = 0;
    }

    // ************************** \\
    // *     ACCESS METHODS     * \\
    // ************************** \\
    
    /** 
     * Returns current segment table.
     * Used for saving the progress.
     * 
     * @return int[][] array with current segment possitions.
     */
    public int[][] getSegmentTable() {
        return this.segmentTable;
    }

    // ************************** \\
    // *     PUBLIC METHODS     * \\
    // ************************** \\
    
    /** 
     * Performs swapping of two nearby segments.
     * 
     * @param gridX - x-coord of segment to be swapped
     * @param gridY - y-coord of segment to be swapped
     * @param direction - the direction in which the swap should be preformed
     */
    public void updateTable(int gridX, int gridY, int direction) {
        // check input values
        // must be within bounds
        if ((gridX>=0)&&(gridX<gridSize)&&(gridY>=0)&&(gridY<gridSize)) {
            // decide which action to take
            switch (direction) {
                case ELRHNightwishPuzzle.MOVEMENT_UP:
                    swapUp(gridX, gridY);
                    break;
                case ELRHNightwishPuzzle.MOVEMENT_DOWN:
                    swapDown(gridX, gridY);
                    break;
                case ELRHNightwishPuzzle.MOVEMENT_LEFT:
                    swapLeft(gridX, gridY);
                    break;
                case ELRHNightwishPuzzle.MOVEMENT_RIGHT:
                    swapRight(gridX, gridY);
                    break;
            }
        }
    }
    
    /** 
     * Checks whether the table of segments is ordered by rows.
     * 
     * @return true if segments are ordered, false if not
     */
    public boolean isOrdered() {
        //
        int gridX = 0;
        int gridY = 0;
        int value = 1;
        boolean check = true;
        while ((check)&&(value<gridSize*gridSize)) {
            // go through array by rows
            // select segment at [x,y]
            // check its value
            // it should correspond with expected value
            if (segmentTable[gridX][gridY]!=value) {
                check = false;
            }
            // move indexes
            value++;
            //
            gridX++;
            if (gridX>gridSize-1) {
                gridX = 0;
                gridY++;
            }
        }
        // true - we went through whole array and no problem was encountered - victory
        // false - during evaluating a mistake was found - no victory
        return check;
    }
    
    // testing purposes only
    public String testEchoTable() {
        String result = "";
        for (int row=0;row<gridSize;row++) {
            for (int col=0;col<gridSize;col++) {
                result = result + Integer.toString(segmentTable[col][row]) + " ";
            }
            result = result + "\n";
        }
        return result;
    }

    /** 
     * Completely changes segment table to values loaded from save file.
     * 
     * @param newTable - loaded segment table
     */
    public void loadSegmentTable(int[][] newTable) {
        for (int row=0;row<gridSize;row++) {
            for (int col=0;col<gridSize;col++) {
                this.segmentTable[col][row] = newTable[col][row];
            }
        }
    }

    // ************************** \\
    // *    PRIVATE METHODS     * \\
    // ************************** \\

    /** 
     * Swaps selected segment with segment one row above.
     * 
     * @param gridX - x-coord of segment to be swapped
     * @param gridY - y-coord of segment to be swapped
     */
    private void swapUp(int gridX, int gridY) {
        // to perform there must be one free pos above current
        if (gridY>0) {
            int container = segmentTable[gridX][gridY];
            segmentTable[gridX][gridY] = segmentTable[gridX][gridY-1];
            segmentTable[gridX][gridY-1] = container;
        }
    }
    
    /** 
     * Swaps selected segment with segment one row below.
     * 
     * @param gridX - x-coord of segment to be swapped
     * @param gridY - y-coord of segment to be swapped
     */
    private void swapDown(int gridX, int gridY) {
        // to perform there must be one free pos below current
        if (gridY<gridSize-1) {
            int container = segmentTable[gridX][gridY];
            segmentTable[gridX][gridY] = segmentTable[gridX][gridY+1];
            segmentTable[gridX][gridY+1] = container;
        }
    }

   /** 
     * Swaps selected segment with segment to the left.
     * 
     * @param gridX - x-coord of segment to be swapped
     * @param gridY - y-coord of segment to be swapped
     */
    private void swapLeft(int gridX, int gridY) {
        // to perform there must be one free position left from current
        if (gridX>0) {
            int container = segmentTable[gridX][gridY];
            segmentTable[gridX][gridY] = segmentTable[gridX-1][gridY];
            segmentTable[gridX-1][gridY] = container;
        }
    }

   /** 
     * Swaps selected segment with segment to the right.
     * 
     * @param gridX - x-coord of segment to be swapped
     * @param gridY - y-coord of segment to be swapped
     */
    private void swapRight(int gridX, int gridY) {
        // to perform there must be one free position right from current
        if (gridX<gridSize-1) {
            int container = segmentTable[gridX][gridY];
            segmentTable[gridX][gridY] = segmentTable[gridX+1][gridY];
            segmentTable[gridX+1][gridY] = container;
        }
    }
}