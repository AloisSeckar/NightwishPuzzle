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
 * // *  File:           *  ELRHScoresRecord.java                     * //
 * // *  Purpose:        *  Class ELRHScoresRecord definition         * //
 * // *  System Version: *  1.0                                       * //
 * // *  Last Modified:  *  2012-02-11 1830 GMT+1                     * //
 * // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * //
 * ///////////////////////////////////////////////////////////////////////
 */
package nightwishpuzzle.logic;

import java.io.Serializable;

/**
 *  ELRHScoresRecord - Record keeping one of the best puzzle solution times.
 *
 * @author Ellrohir
 * @version 1.0
 */
public class ELRHScoresRecord implements Serializable {
    
    // ************************** \\
    // *        CONSTANTS       * \\
    // ************************** \\
    
    /**
     * Stored format for date output.
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // ************************** \\
    // *       PROPERTIES       * \\
    // ************************** \\
    
    /**
     * Number of seconds, that successful solution took.
     */
    private final int elapsedTime;
    
    /**
     * Name given by the record holder.
     */
    private final String playerName;
    
    /**
     * Date when the record was set.
     */
    private final String recordDate;

    // ************************** \\
    // *      CONSTRUCTORS      * \\
    // ************************** \\
    
    /**
     * Standard constructor.
     * 
     * @param time How long it took
     * @param name Who got the record
     * @param date When the record happened
     */
    public ELRHScoresRecord(int time, String name, String date) {
        this.elapsedTime = time;
        this.playerName = name;
        this.recordDate = date;
    }

    // ************************** \\
    // *     ACCESS METHODS     * \\
    // ************************** \\

    /**
     * Return number of elapsed seconds.
     */
    public int getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Return record holder's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Return record date.
     */
    public String getRecordDate() {
        return recordDate;
    }

    // ************************** \\
    // *     PUBLIC METHODS     * \\
    // ************************** \\

    // ************************** \\
    // *    PRIVATE METHODS     * \\
    // ************************** \\

}
