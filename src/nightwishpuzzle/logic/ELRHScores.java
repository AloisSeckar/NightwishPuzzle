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
 * // *  File:           *  ELRHScores.java                           * //
 * // *  Purpose:        *  Class ELRHScores definition               * //
 * // *  System Version: *  1.0                                       * //
 * // *  Last Modified:  *  2012-02-11 1710 GMT+1                     * //
 * // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * //
 * ///////////////////////////////////////////////////////////////////////
 */

package nightwishpuzzle.logic;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *  ELRHScores - Class for keeping game top scores.
 *
 * @author Ellrohir
 * @version 1.0
 */
public class ELRHScores implements Serializable {

    // ************************** \\
    // *        CONSTANTS       * \\
    // ************************** \\


    // ************************** \\
    // *       PROPERTIES       * \\
    // ************************** \\
    
    private ELRHScoresRecord[] stats_img3 = new ELRHScoresRecord[10];
    
    private ELRHScoresRecord[] stats_img4 = new ELRHScoresRecord[10];
    
    private ELRHScoresRecord[] stats_img6 = new ELRHScoresRecord[10];
    
    private ELRHScoresRecord[] stats_dpp3 = new ELRHScoresRecord[10];
    
    private ELRHScoresRecord[] stats_dpp4 = new ELRHScoresRecord[10];
    
    private ELRHScoresRecord[] stats_dpp6 = new ELRHScoresRecord[10];
        
    // ************************** \\
    // *      CONSTRUCTORS      * \\
    // ************************** \\

    // ************************** \\
    // *     ACCESS METHODS     * \\
    // ************************** \\

    // ************************** \\
    // *     PUBLIC METHODS     * \\
    // ************************** \\
    
    /**
     * Clears all stored stats and replaces them with default values.
     */
    public void clearStats() {
        // set current time
        String time = getCurrentTime();
        // reset all stats values
        for (int i=0; i<10; i++) {
            stats_img3[i] = new ELRHScoresRecord(999, "NWPuzzle", time);
            stats_img4[i] = new ELRHScoresRecord(999, "NWPuzzle", time);
            stats_img6[i] = new ELRHScoresRecord(999, "NWPuzzle", time);
            stats_dpp3[i] = new ELRHScoresRecord(999, "NWPuzzle", time);
            stats_dpp4[i] = new ELRHScoresRecord(999, "NWPuzzle", time);
            stats_dpp6[i] = new ELRHScoresRecord(999, "NWPuzzle", time);
        }
    }
    
    /**
     * Compares new time with stored times and evaluates whether this time
     * should be in top10.
     * 
     * @param time - time in secs to be compared
     * @param section - code for type of game that was played
     * 
     * @return Either the position in top10 or -1 when time too bad for top10.
     */
    public int compareStats(int time, int section) {
        switch (section) {
            case 1: 
                // img3
                return compareParticularStats(time, stats_img3);
            case 2: 
                // img4
                return compareParticularStats(time, stats_img4);
            case 3: 
                // img6
                return compareParticularStats(time, stats_img6);
            case 4: 
                // dpp3
                return compareParticularStats(time, stats_dpp3);
            case 5: 
                // dpp4
                return compareParticularStats(time, stats_dpp4);
            case 6: 
                // dpp6
                return compareParticularStats(time, stats_dpp6);
            default:
                // should never really happen
                return -1;
        }
    }
    
    /**
     * Adds new record into top10 times for given game type.
     * 
     * @param time - time achieved
     * @param name - given player name
     * @param section - code for type of game that was played
     * @param pos - position in top10 where to add
     */
    public void addStatsRecord(int time, String name, int section, int pos) {
        switch (section) {
            case 1: 
                // img3
                stats_img3 = addParticularStatsRecord(time, name, stats_img3, pos);
                break;
            case 2: 
                // img4
                stats_img4 = addParticularStatsRecord(time, name, stats_img4, pos);
                break;
            case 3: 
                // img6
                stats_img6 = addParticularStatsRecord(time, name, stats_img6, pos);
                break;
            case 4: 
                // dpp3
                stats_dpp3 = addParticularStatsRecord(time, name, stats_dpp3, pos);
                break;
            case 5: 
                // dpp4
                stats_dpp4 = addParticularStatsRecord(time, name, stats_dpp4, pos);
                break;
            case 6: 
                // dpp6
                stats_dpp6 = addParticularStatsRecord(time, name, stats_dpp6, pos);
                break;
        }
    }
    
    /**
     * Returns current top scores for given game type.
     * 
     * @param section - code for type of game that was played
     * 
     * @return Current Top 10 scores in specified section.
     */
    public ELRHScoresRecord[] returnTopScores(int section) {
        switch (section) {
            case 1: 
                // img3
                return stats_img3;
            case 2: 
                // img4
                return stats_img4;
            case 3: 
                // img6
                return stats_img6;
            case 4: 
                // dpp3
                return stats_dpp3;
            case 5: 
                // dpp4
                return stats_dpp4;
            case 6: 
                // dpp6
                return stats_dpp6;
            default:
                // should never really happen
                return null;
        }
    }

    // ************************** \\
    // *    PRIVATE METHODS     * \\
    // ************************** \\
    
    /**
     * Returns current date and time.
     * 
     * @return Formated string with current date and time data.
     */
    private String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(ELRHScoresRecord.DATE_FORMAT);
        return sdf.format(cal.getTime());
    }
    
    /**
     * Compares new time with stored times for specific game type and evaluates 
     * whether this time should be in top10.
     * 
     * @param time - time in secs to be compared
     * @param src - particular stats array to be evaluated
     * 
     * @return Either the position in top10 or -1 when time too bad for top10.
     */
    private int compareParticularStats(int time, ELRHScoresRecord[] src) {
        // go through array
        // if time better than value at current pos, return current pos
        // if end of array occured, return -1
        for (int i=0;i<10;i++) {
            if (time<src[i].getElapsedTime()) {
                return i; // not +1, as it is array index!
            }
        }
        // end of array occured
        return -1;
    }
    
    /**
     * Adds new record into top10 times into specific array.
     * 
     * @param time - time achieved
     * @param name - given player name
     * @param src - particular stats array where to add
     * @param pos - position in top10 where to add
     * 
     * @return Top scores array with new value.
     */
    private ELRHScoresRecord[] addParticularStatsRecord(int time, String name, ELRHScoresRecord[] src, int pos) {
        // new value will be inserted at "pos"
        // copy first part of array (until "pos-1") - no change here
        ELRHScoresRecord[] updated = new ELRHScoresRecord[10];
        System.arraycopy(src, 0, updated, 0, pos);
        // insert new value at "pos"
        updated[pos] = new ELRHScoresRecord(time, name, getCurrentTime());
        // move rest of array (from "pos+1") one backwards
        for (int i=pos+1; i<10; i++) {
            updated[i] = src[i-1];
        }
        // return new array
        return updated;
    }
}
