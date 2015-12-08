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
 * // *  File:           *  ELRHNightwishPuzzle.java                  * //
 * // *  Purpose:        *  Class ELRHNightwishPuzzle definition      * //
 * // *  System Version: *  1.0                                       * //
 * // *  Last Modified:  *  2014-10-19 1613 GMT+1                     * //
 * // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * //
 * ///////////////////////////////////////////////////////////////////////
 */

package nightwishpuzzle;

//
import nightwishpuzzle.logic.ELRHSegmentTable;
import nightwishpuzzle.logic.ELRHSegment;
import nightwishpuzzle.logic.ELRHScores;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import nightwishpuzzle.gui.ELRHTopScores;
import nightwishpuzzle.logic.ELRHGameSave;

/**
 *  ELRHSegment - //TODO class description
 *
 * @author Ellrohir
 * @version 1.0
 */
public class ELRHNightwishPuzzle {
    
    // TODO translation
    // TODO separate GUI panels
    // TODO prune code
    // TODO english javadoc - whole file
    // TODO panel background image
    // TODO variable for game type (dpp3, img6, etc.)
    // TODO keep game settings
    
    /**
     * Random value for the direction "up".
     */
    public static final int MOVEMENT_UP = 11111;
    
    /**
     * Random value for the direction "down".
     */
    public static final int MOVEMENT_DOWN = 22222;
    
    /**
     * Random value for the direction "left".
     */
    public static final int MOVEMENT_LEFT = 33333;
    
    /**
     * Random value for the direction "right".
     */
    public static final int MOVEMENT_RIGHT = 44444;
    
    /**
     * Path to top scores data file.
     */
    public static final String STATS_FILE = "scores.sco";
    
    /**
     * Path to saved game data file.
     */
    public static final String GAME_FILE = "game.sav";

    
    private JFrame mainWin = new JFrame();
    
    private final JMenuBar menuBar = new JMenuBar();
    private final JPanel contentPanel = new JPanel();
    private final JPanel statusPanel = new JPanel();
    
//    // FOR TESTING PURPOSES
//    private JButton testB = new JButton("AI move");
//    private JTextArea testText = new JTextArea();
//    private Timer testTimer = new Timer(200, new ELRHAISegmentMover());
    
    // menu
    private final JMenu mainMenu = new JMenu("Main");
    private final JMenu optionsMenu = new JMenu("Options");
    private final JMenu helpMenu = new JMenu("Help");
    // 
    private final JMenuItem mmStart = new JMenuItem("Start game");
    private final JMenuItem mmPause = new JMenuItem("Pause game");
    private final JMenuItem mmLoad = new JMenuItem("Load last");
    private final JMenuItem mmSave = new JMenuItem("Save current");
    private final JMenuItem mmScores = new JMenuItem("High scores");
    private final JMenuItem mmReset = new JMenuItem("Reset scores");
    private final JMenuItem mmExit = new JMenuItem("Exit");
    private final JMenu omImg = new JMenu("Select image");
    private final JMenu omDim = new JMenu("Select dimension");
    private final JMenuItem hmAbout = new JMenuItem("About");
    //
    private final ButtonGroup optImg = new ButtonGroup();
    private final ButtonGroup optDim = new ButtonGroup();
    private final JRadioButtonMenuItem img1 = new JRadioButtonMenuItem("Dark Passion Play", true);
    private final JRadioButtonMenuItem img2 = new JRadioButtonMenuItem("Imaginaerum", false);
    private final JRadioButtonMenuItem dim1 = new JRadioButtonMenuItem("3x3", false);
    private final JRadioButtonMenuItem dim2 = new JRadioButtonMenuItem("4x4", true);
    private final JRadioButtonMenuItem dim3 = new JRadioButtonMenuItem("6x6", false);
    // menu
    
    // status
    private final JLabel stImage = new JLabel("Dark Passion Play (4x4)");
    private final JLabel stPause = new JLabel("PAUSED");
    private final JLabel stTime = new JLabel("00:00");
    // status
    
    private final GridBagLayout layout = new GridBagLayout();
    private final GridBagConstraints coords = new GridBagConstraints();
    
    private boolean gameRuns = false;
    private boolean gameProgress = false;
    
    private Timer timeRuns = new Timer(1000, new ELRHGameWatch());
    
    
    private int elapsedTime = 0;
    
    private int gridSize = 4;
    
    private String imgSource = "dpp";
    
    private int segmentCount = 15;
    
    private int segmentSize = 100;
    
    private ELRHSegment[] segments = new ELRHSegment[segmentCount];
    
    private ELRHSegmentTable segmentTable;
    
    private ELRHScores topScores = new ELRHScores();
    
    
    /**
     * X-souradnice volné pozice, na které není ELRHSegment.
     */
    //JButton volna = new JButton();
    
    /**
     * X-souradnice volné pozice, na které není ELRHSegment.
     */
    private int freeX = 0;
    
    /**
     * Y-souradnice volné pozice, na které není ELRHSegment.
     */
    private int freeY = 0;
    
    /**
     * Index segments, na kterou bylo kliknuto.
     */
    // private int selected = 0;
    
    
    /**
     * Standard constructor.
     */
    public ELRHNightwishPuzzle() {
        
        // game top scores initialization
        initializeTopScores();
        
        // main win initialization
        mainWin.setTitle("Nightwish Puzzle");
        mainWin.setLayout(new BoxLayout(mainWin.getContentPane(), BoxLayout.Y_AXIS));
        mainWin.setResizable(false);
       
        // closing action
        // code taken from:
        // http://mindprod.com/jgloss/close.html
        mainWin.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        // what happens when user closes the JFrame.
        WindowListener windowListener = new WindowAdapter() {
            // anonymous WindowAdapter class
            @Override
            public void windowClosing (WindowEvent w) {
                // Whatever code you want to actually close the JFrame, e.g.
                writeTopScores();
                timeRuns.stop();
                mainWin.dispose();
            } // end windowClosing
        };// end anonymous class
        mainWin.addWindowListener(windowListener);
        
        // menu initialization
        generateMenu();
        // content panel initialization
        contentPanel.setLayout(layout);
        // status panel initialization
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusPanel.add(Box.createRigidArea(new Dimension(5,5)));
        statusPanel.add(stImage);
        statusPanel.add(Box.createHorizontalGlue());
//        // FOR TESTING PURPOSES
//        statusPanel.add(testText);
//        statusPanel.add(testB);
//        testB.addActionListener(new ELRHTestButtonListener());
        stPause.setVisible(false);
        statusPanel.add(stPause);
        statusPanel.add(Box.createRigidArea(new Dimension(5,5)));
        statusPanel.add(stTime);
        statusPanel.add(Box.createRigidArea(new Dimension(5,5)));
        
        // add components to main win
        mainWin.add(menuBar);
        mainWin.add(contentPanel);
        mainWin.add(new JSeparator());
        mainWin.add(statusPanel);
        
        // set main win size and pos
        // TODO better window position
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        screen.setSize(screen.getWidth()/2, screen.getHeight()/2);
        mainWin.setLocation(200,100);
        mainWin.setSize(new Dimension(800,600));
    }
    
    private void generateGamePlan(boolean newGame) {
        // creating and adding compnents
        int x = 0;
        int y = 0;
        URL[] img = prepareURLs();
        //
        for (int i=0;i<=segmentCount;i++) {
            //
            if ((newGame)&&(i==segmentCount)) {
                continue; // need to break when in bottom down corner
            }
            if ((!newGame)&&(segmentTable.getSegmentTable()[x][y]==0)) {
                continue; // need to break when reaching free space
            }
            // create component
            segments[i] = new ELRHSegment(x,y);
            // set image
                // scale image
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image image;
                if (newGame) {
                    // load images one by one
                    image = toolkit.getImage(img[i]);
                } else {
                    // load image, that was saved on current position
                    image = toolkit.getImage(img[segmentTable.getSegmentTable()[x][y]]);
                }
                Image scaledImage = image.getScaledInstance(segmentSize, segmentSize, Image.SCALE_DEFAULT);   
                ImageIcon icon = new ImageIcon(scaledImage);
            segments[i].setIcon(icon);
            segments[i].setPreferredSize(new Dimension(segmentSize,segmentSize));
            segments[i].addActionListener(new ClickOnSegment());
            // add to form
            coords.gridx = x;
            coords.gridy = y;
            contentPanel.add(segments[i],coords);
            // coord move
            x++;
            if (x>=gridSize) {
                x = 0;
                y++;
            }
        }
        
        if (newGame) {
            // set initial free space in right bottom corner
            freeX = (int) (gridSize - 1);
            freeY = (int) (gridSize - 1);
            // prepare segment table - for checking victory conditions
            segmentTable = new ELRHSegmentTable(gridSize);
            // shuffle all pieces
            // using random moves
            for (int i=0;i<segmentCount*15;i++) {
                randomAIMove();
            }
    //        // FOR TESTING PURPOSES
    //        testText.setText(segmentTable.testEchoTable());
        }
    }
    
    private void generateMenu() {
        //
        menuBar.add(mainMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);
        menuBar.add(Box.createGlue());
        //
        mainMenu.add(mmStart);
        mmStart.addActionListener(new ELRHMenuStart());
        mainMenu.add(mmPause);
        mmPause.addActionListener(new ELRHMenuPause());
        mainMenu.add(mmLoad);
        mmLoad.addActionListener(new ELRHMenuLoad());
        mainMenu.add(mmSave);
        mmSave.addActionListener(new ELRHMenuSave());
        mainMenu.addSeparator();
        mainMenu.add(mmScores);
        mmScores.addActionListener(new ELRHMenuScores());
        mainMenu.add(mmReset);
        mmReset.addActionListener(new ELRHMenuResetScores());
        mainMenu.addSeparator();
        mainMenu.add(mmExit);
        mmExit.addActionListener(new ELRHMenuExit());
        //
        optionsMenu.add(omImg);
        optionsMenu.add(omDim);
        optImg.add(img1);
        optImg.add(img2);
        omImg.add(img1);
        omImg.add(img2);
        optDim.add(dim1);
        optDim.add(dim2);
        optDim.add(dim3);
        omDim.add(dim1);
        omDim.add(dim2);
        omDim.add(dim3);
        img1.setName("img1opt");
        img1.addItemListener(new ELRHMenuImage());
        img2.setName("img2opt");
        img2.addItemListener(new ELRHMenuImage());
        dim1.setName("dim1opt");
        dim1.addItemListener(new ELRHMenuDimension());
        dim2.setName("dim2opt");
        dim2.addItemListener(new ELRHMenuDimension());
        dim3.setName("dim3opt");
        dim3.addItemListener(new ELRHMenuDimension());
        //
        helpMenu.add(hmAbout);
        hmAbout.addActionListener(new ELRHMenuAbout());
    }
    
    /**
     * Tries to load game top scores from save file.
     * It file not found or corrupted, sets default scores.
     * 
     * Code inspired in:
     * http://www.javadb.com/reading-objects-from-file-using-objectinputstream
     */
    private void initializeTopScores() {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(STATS_FILE));
            Object obj = inputStream.readObject();
            if (obj instanceof ELRHScores) {
                topScores = (ELRHScores)obj;
            } else { 
                JOptionPane.showMessageDialog(mainWin.getContentPane(), "Failed to read top scores! Reseting to default.");
                // reset top scores to default values
                topScores.clearStats();
            }
        } catch (IOException ex) { 
            JOptionPane.showMessageDialog(mainWin.getContentPane(), "Failed to read top scores! Reseting to default.");
            // reset top scores to default values
            topScores.clearStats();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(mainWin.getContentPane(), "Failed to read top scores! Reseting to default.");
            // reset top scores to default values
            topScores.clearStats();
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(mainWin.getContentPane(), "Failed to read top scores! Reseting to default.");
            // reset top scores to default values
            topScores.clearStats();
        } finally {
            // close the ObjectInputStream
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception ex) {
                // do nothing, just prevent error message
            }
        }
    }
    
    /**
     * Writes current game top scores into file.
     * 
     * Code inspired in:
     * http://www.javadb.com/writing-objects-to-file-with-objectoutputstream
     */
    private void writeTopScores() {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(STATS_FILE));
            outputStream.writeObject(topScores);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainWin.getContentPane(), "Failed to write down top scores!");
        } finally {
            // close the ObjectOutputStream
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (Exception ex) {
                // do nothing, just prevent error message
            }
        }
    }
    
    
    /**
     * Prepares the image pieces.
     * Prepares an array with valid URLs leading to resource images. If invalid
     * URL encoutered, the program will end with an error message.
     * 
     * @return ordered array of valid image URLs
     */
    private URL[] prepareURLs() {
        // inicialization
        URL[] src = new URL[segmentCount];
        // filling the ELRHSegmentURLs array
        int x = 1;
        int y = 1;
        for (int i=0;i<segmentCount;i++) {
            // build ELRHSegmentURL
            String path = "/images/" + imgSource + Integer.toString(gridSize) +
                "/" +Integer.toString(x) + Integer.toString(y) + ".png";
            URL test = ELRHNightwishPuzzle.class.getResource(path);
            // check if exist
            if (test== null) {
                JOptionPane.showMessageDialog(mainWin, "Fatal error - resource image not found! Program will terminate.", "Nightwish Puzzle - General error!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            // build segment url record
            src[i] = test;
            // coord move
            x++;
            if (x>gridSize) {
                x = 1;
                y++;
            }
        }
        //
        return src;
    }
    
    private void setImageDimension(int newGridSize) {
        gridSize = newGridSize;
        //
        switch (gridSize) {
            case 3:
                segmentCount = 8;
                segmentSize = 150;
                break;
            case 4:
                segmentCount = 15;
                segmentSize = 100;
                break;
            case 6:
                segmentCount = 35;
                segmentSize = 75;
                break;
            default:
                segmentCount = 15;
                segmentSize = 100;
        }
    }
    
    private void setImageSource(String newSource) {
        imgSource = newSource;
    }
    
    private void setGame(boolean newGame) {
        // cleanup from previous
        contentPanel.removeAll();
        contentPanel.revalidate();
        segments = new ELRHSegment[segmentCount];
        // gameProgress
        gameProgress = false;
        // game plan
        // if loading game, pieces are not shuffled randomly
        generateGamePlan(newGame);
        // set image info text
        updateImageInfoText();
        // reset time counter
        if (newGame) {
            // only when starting new game
            elapsedTime = 0;
        }
        updateElapsedTimeText();
        timeRuns.start();
        // game started
        gameRuns = true;
    }
    
    private void updateImageInfoText() {
        // set current image name
        String imgName = "";
        if (imgSource.equals("dpp")) {
            imgName = "Dark Passion Play";        
        } else if (imgSource.equals("img")) {
            imgName = "Imaginaerum";        
        }
        // update status bar text
        stImage.setText(imgName+" ("+Integer.toString(gridSize)+"x"+Integer.toString(gridSize)+")");
    }
    
    private void updateElapsedTimeText() {
        // extract minutes and seconds from elapsed seconds
        Integer m = elapsedTime/60;
        Integer s = elapsedTime%60;
        // quarantee MM and SS format (it could be M:S otheriwse)
        String mins;
        String secs;
        if (m<10) {
            mins = "0" + m.toString();
        } else {
            mins = m.toString();
        }
        if (s<10) {
            secs = "0" + s.toString();
        } else {
            secs = s.toString();
        } 
        // update text
        stTime.setText(mins+":"+secs);
    }
    
    /** 
     * Spousteci trida.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ELRHNightwishPuzzle run = new ELRHNightwishPuzzle();
        run.setVisible(true);
    }
    
    
    
    /** 
     * Zjisti, jestli se kliklo na kostku sousedni z mezerou.
     */
    private boolean isAllowedToMove(int posX, int posY) {
        // kdy se muze hybat?
        // budto je posX + nebo - jedna od volne_X
        // nebo je posY + nebo - jedna od volne_Y
        // zaroven musi platit ze rozdil druhych souradnic je 0 (stejny radek/sloupec)
        return ((Math.abs(posX-freeX)==1)&&(Math.abs(posY-freeY)==0))||((Math.abs(posY-freeY)==1)&&(Math.abs(posX-freeX)==0));
    }
    
    /**
     * Evaluates which direction the segment will be moved.
     * 
     * @param event - segment to be moved
     * 
     * @return integer value for actual direction corresponding with constant from ELRHSegmentTable
     */
    private int getMovementDirection(ELRHSegment event) {
        // possibilities - up, down, left, right
        // thanks to isAllowedToMove() method, there aren't any other options than following:
        // NOTICE - the directions are vice-versed (the "free space" is moving)
        // up
        if (event.getPosY()==freeY+1) {
            return MOVEMENT_DOWN;
        }
        // down
        else if (event.getPosY()==freeY-1) {
            return MOVEMENT_UP;
        }
        // left
        else if (event.getPosX()==freeX+1) {
            return MOVEMENT_RIGHT;
        }
        // right
        else if (event.getPosX()==freeX-1) {
            return MOVEMENT_LEFT;
        }
        // default (should never really happen)
        else {
            return -1;
        }
    }
    
    /** 
     * Moves the selected segment.
     * It simply places the segment into current "free space" and sets new 
     * "free space" location to place, where the segment originally was.
     */
    private void segmentMove(ELRHSegment event, int direction) {
        // security
        // (should never be null)
        // but to prevent all possible errors...
        if (event!=null) {
            // first update table of segments
            segmentTable.updateTable(freeX, freeY, direction);
//            // FOR TESTING PURPOSES
//            testText.setText(segmentTable.testEchoTable());
            // remove component
            contentPanel.remove(event);
            contentPanel.repaint();
            // change coordinates
            coords.gridx = freeX;
            coords.gridy = freeY;
            freeX = event.getPosX();
            freeY = event.getPosY();
            event.setPosX((int) coords.gridx);
            event.setPosY((int) coords.gridy);
            // add component to new location
            contentPanel.add(event, coords);
            contentPanel.revalidate();
        }
    }

    public void setVisible(boolean b) {
        mainWin.setVisible(b);
    }
    
    private void randomAIMove() {
        // get possible directions of movement according to the current free position
        ArrayList<Integer> directions = new ArrayList<Integer>();
        if (freeX>0) {
            directions.add(MOVEMENT_LEFT);
        }
        if (freeX<gridSize-1) {
            directions.add(MOVEMENT_RIGHT);
        }
        if (freeY>0) {
            directions.add(MOVEMENT_UP);
        }
        if (freeY<gridSize-1) {
            directions.add(MOVEMENT_DOWN);
        }
        // randomly select one of the possibilities
        // and perform the move
        Random rand = new Random();
        aiSegmentMove(directions.get(rand.nextInt(directions.size())));
    }
    
    private void aiSegmentMove(int direction) {
        // get segment, which should move
        ELRHSegment current = null;
        switch(direction) {
            case MOVEMENT_LEFT:
                // left
                current = findSegment(freeX-1, freeY);
                break;
            case MOVEMENT_RIGHT:
                // right
                current = findSegment(freeX+1, freeY);
                break;
            case MOVEMENT_UP:
                // up
                current = findSegment(freeX, freeY-1);
                break;
            case MOVEMENT_DOWN:
                // down
                current = findSegment(freeX, freeY+1);
                break;
        }
        // move it
        segmentMove(current, direction);
    }
    
    private ELRHSegment findSegment(int posX, int posY) {
        // go through segments array until coords match
        // or end of array, but this should never happen...
        int index = 0;
        while (index<segments.length) {
            if ((segments[index].getPosX()==posX)&&(segments[index].getPosY()==posY)) {
                return segments[index];
            }
            index++;
        }
        return null;
    }
    
    private boolean isAllowedToReset() {
        // default - true
        int choice = JOptionPane.YES_OPTION;
        // if user made any gameProgress - ask him if he allows
        if (gameProgress) {
            choice = JOptionPane.showConfirmDialog(mainWin, "Unsaved game progress will be lost! Continue?", "Nightwish Puzzle - Notification", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }
        // if there wasn't choice or if user chose yes
        return choice == JOptionPane.YES_OPTION;
    }
    
    
    /**
     * Tests victory conditions.
     * 
     * @return True if puzzle finished, false if not
     */
    private boolean testGameEnd() {
        return segmentTable.isOrdered();
    }
    
    /**
     * Starts or stops the game.
     * 
     * @param run - True if game should be started, false when it should be stopped
     */
    private void changeGameState(boolean run) {
        if (run) {
            // run the game
            if (!gameRuns) {
                gameRuns = true;
                timeRuns.start();
                stPause.setVisible(false);
                // TODO what else
            }
        } else {
            // pause the game
            if (gameRuns) {
                gameRuns = false;
                timeRuns.stop();
                if (!testGameEnd()) {
                    stPause.setVisible(true);
                }
                // TODO what else
            }
        }
    }
    
    /**
     * Saves current game progress.
     * 
     * Code inspired in:
     * http://www.javadb.com/writing-objects-to-file-with-objectoutputstream
     */
    private void saveGame() {
        // create save record
        ELRHGameSave savedGame = new ELRHGameSave(imgSource, gridSize, elapsedTime, freeX, freeY, segmentTable.getSegmentTable());
        // save it
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(GAME_FILE));
            outputStream.writeObject(savedGame);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainWin.getContentPane(), "Failed to save game progress!");
        } finally {
            // close the ObjectOutputStream
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (Exception ex) {
                // do nothing, just prevent error message
            }
        }
    }
    
    /**
     * Load saved game progress.
     * 
     * Code inspired in:
     * http://www.javadb.com/reading-objects-from-file-using-objectinputstream
     */
    private void loadGame() {
        // load data from file
        ObjectInputStream inputStream = null;
        ELRHGameSave savedGame;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(GAME_FILE));
            Object obj = inputStream.readObject();
            if (obj instanceof ELRHGameSave) {
                // load game data
                savedGame = (ELRHGameSave)obj; 
                // adjust game according to loaded values
                elapsedTime = savedGame.elapsedTime;
                freeX = savedGame.freeX;
                freeY = savedGame.freeY;
                gridSize = savedGame.gridSize;
                imgSource = savedGame.imgSource;
                segmentTable.loadSegmentTable(savedGame.segmentTable);
                //
                setGame(false);
            } else {
                JOptionPane.showMessageDialog(mainWin.getContentPane(), "Failed to load game! Saved file corrupted.");
            }
        } catch (IOException ex) { 
            JOptionPane.showMessageDialog(mainWin.getContentPane(), "Failed to load game! Error during loading process.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(mainWin.getContentPane(), "Failed to load game! Error during loading process.");
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(mainWin.getContentPane(), "Failed to load game! Error during loading process.");
        } finally {
            // close the ObjectInputStream
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception ex) {
                // do nothing, just prevent error message
            }
        }
    }
 
    
    /**
     * Returns code for current game type
     * 
     * @return 0-6 according to current game type
     */
    private int getGameTypeCode() {
        String gameType = imgSource+gridSize;
        //
        if (gameType.equals("img3")) {
            return 1;
        }
        else if (gameType.equals("img4")) {
            return 2;
        }
        else if (gameType.equals("img6")) {
            return 3;
        }
        else if (gameType.equals("dpp3")) {
            return 4;
        }
        else if (gameType.equals("dpp4")) {
            return 5;
        }
        else if (gameType.equals("dpp6")) {
            return 6;
        } else {
            return 0;
        }
    }
    
    /** 
     * Action listener for counting elapsed time.
     */
    public class ELRHGameWatch implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime++;
            updateElapsedTimeText();
        }
    }
    
    // TODO translate
    /** 
     * ActionListener na ovladani akce po kliknuti.
     */
    private class ClickOnSegment implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // un-pause if paused
            if (!gameRuns) {
                changeGameState(true);
            }
            // zjisteni segments, na kterou se kliklo
            ELRHSegment event = (ELRHSegment) e.getSource();
            // zjisteni, zda se muze hybat
            if (isAllowedToMove(event.getPosX(), event.getPosY()))
            {  
                // gameProgress in game
                gameProgress = true;
                // pokud ano, vykonat presun na urcene misto
                segmentMove(event, getMovementDirection(event));
                // testovat konec hry
                if (testGameEnd()) {
                    // stop timer
                    timeRuns.stop();
                    // no more game progress
                    gameProgress = false;
                    // check and possibly keep top scores
                    int gameType = getGameTypeCode();
                    int pos = topScores.compareStats(elapsedTime, gameType);
                    if (pos>-1) {
                        // if new top10 value achieved
                        String plrName = JOptionPane.showInputDialog(contentPanel, "Congratulations, you achieved a top score! Please, enter your name:", "Player 1");
                        if (plrName.equals("")) {
                            plrName = "Player 1";
                        }
                        // add into stats
                        topScores.addStatsRecord(elapsedTime, plrName, gameType, pos);
                        // save stats to file
                        writeTopScores();
                    }
                    // reset game
                    int choice = JOptionPane.showConfirmDialog(contentPanel, "You won! Do you want to start a new game?", "Victory", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        // znovu rozdat
                        setGame(true);
                    }
                }
            }
        }
    }
    
    /** 
     * ActionListener - menu button "About"
     */
    private class ELRHMenuAbout implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(mainWin, "Nightwish Puzzle - Simple puzzle game in Java Ver 1.0 © Ellrohir 2011-2014", "Nightwish Puzzle - About", JOptionPane.INFORMATION_MESSAGE);
        }   
    }
    
    /** 
     * ActionListener - menu button "Start"
     */
    private class ELRHMenuStart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // start new game
            if (isAllowedToReset()) {
                setGame(true);
            }
        }   
    }
    
    /** 
     * ActionListener - menu button "Exit"
     */
    private class ELRHMenuExit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // exit game
            if (isAllowedToReset()) {
                writeTopScores();
                timeRuns.stop();
                mainWin.dispose();
            }
        }   
    }
    
    
    
    /** 
     * ActionListener - menu button "Stats"
     */
    private class ELRHMenuScores implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // view top scores window
            ELRHTopScores scores = new ELRHTopScores(mainWin, topScores);
            scores.setVisible(true);
        }   
    }
    
    /** 
     * ActionListener - menu button "Stats"
     */
    private class ELRHMenuResetScores implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(contentPanel, "Chcete opravdu resetovat dosažené časy? Nelze vzít zpět!", "Reset scores", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                topScores.clearStats();
            }     
        }   
    }
    
    
    /** 
     * ActionListener - menu button "Load"
     */
    private class ELRHMenuLoad implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadGame();
        }
    }

    /** 
     * ActionListener - menu button "Pause"
     */
    private class ELRHMenuPause implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameRuns) {
                changeGameState(false);
                JOptionPane.showMessageDialog(mainWin.getContentPane(), "Game paused. Click on any image piece to resume.");
            }
        }
    }

    /** 
     * ActionListener - menu button "Save"
     */
    private class ELRHMenuSave implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // perform pause action
            changeGameState(false);
            // save data
            saveGame();
        }
    }
    
    /** 
     * ItemListener - menu option "Select Dimension"
     */
    private class ELRHMenuDimension implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            // check for game gameProgress
            if (isAllowedToReset()) {
                // get selected item
                JMenuItem item = (JMenuItem) e.getItem();
                //
                if (item.getName().equals("dim1opt")) {
                    setImageDimension((int)3);
                } else if (item.getName().equals("dim2opt")) {
                    setImageDimension((int)4);
                } else {
                    setImageDimension((int)6);
                }
                if (gameRuns) {
                    // reset game (only when game already active)
                    setGame(true);
                }
                // TODO menubar change
            }
        }
    }
    
    /** 
     * ItemListener - menu option "Select Dimension"
     */
    private class ELRHMenuImage implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            // check for game gameProgress
            if (isAllowedToReset()) {
                // get selected item
                JMenuItem item = (JMenuItem) e.getItem();
                //
                if (item.getName().equals("img1opt")) {
                    setImageSource("dpp");
                } else {
                    setImageSource("img");
                }
                if (gameRuns) {
                    // reset game (only when game already active)
                    setGame(true);
                }
                // TODO menubar change
            }
        }
    }
    
//    // FOR TESTING PURPOSES
//    private class ELRHTestButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (testB.getText().equals("AI move")) {
//                testB.setText("AI stop");
//                testTimer.start();
//            } else {
//                testB.setText("AI move");
//                testTimer.stop();
//            }
//        }
//        
//    }
//    
//    private class ELRHAISegmentMover implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            randomAIMove();
//        }
//
//    }
}

