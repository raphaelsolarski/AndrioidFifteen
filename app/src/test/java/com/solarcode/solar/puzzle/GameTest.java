package com.solarcode.solar.puzzle;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void testRandomAndCorrectDirection() throws Exception {
        assertTrue(game.randomAndCorrectDirection(5) >= 0);
        assertTrue(game.randomAndCorrectDirection(5) <= 3);
    }

    @Test
    public void testIsDirectionCorrect() throws Exception {
        assertFalse(game.isDirectionCorrect(0, 0));
        assertFalse(game.isDirectionCorrect(3, 1));
        assertFalse(game.isDirectionCorrect(14, 2));
        assertFalse(game.isDirectionCorrect(0, 3));
    }

    @Test
    public void testSwapBlocks() throws Exception {
        int field1 = game.getField(4);
        int field2 = game.getField(5);
        game.swapBlocks(4, 5);
        assertEquals(field1, game.getField(5));
        assertEquals(field2, game.getField(4));
    }
}