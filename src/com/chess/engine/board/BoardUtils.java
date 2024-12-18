package com.chess.engine.board;

public class BoardUtils {
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final boolean[] SECOND_ROW = initRow(8);
    public static final boolean[] SEVENTH_ROW = initRow(48);

    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;

    private BoardUtils(){
        throw new RuntimeException("You cannot instantiate this!");
    }

    private static boolean[] initColumn(int i) {
        final boolean[] column = new boolean[NUM_TILES];
        while(i<NUM_TILES){
            column[i] = true;
            i += NUM_TILES_PER_ROW;
        }
        return column;
    }

    private static boolean[] initRow(int i){
        final boolean[] row = new boolean[NUM_TILES];
        while(i%NUM_TILES_PER_ROW != 0){
            row[i] = true;
            i++;
        }
        return row;
    }

    public static boolean isValidCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < NUM_TILES;
    }
}
