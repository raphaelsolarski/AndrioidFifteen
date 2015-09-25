package com.solarcode.solar.puzzle;

import android.util.Log;

import java.util.Random;

public class Game {
    public final int fieldsInRow = 4;
    public final int fieldsInColumn = 4;
    static final int numberOfMapDraws = 150;
    int indexOfInvisibleBlock;
    int fieldsMap[];
    Random randomMachine;

    public Game() {
        fieldsMap = new int[fieldsInColumn * fieldsInRow];
        for(int i = 0; i < fieldsMap.length; i++) {
            fieldsMap[i] = i+1;
        }

        randomMachine = new Random();

        do {
            randomizeMap();
        }while(checkIfYouWon());
    }

    private void randomizeMap(){
        indexOfInvisibleBlock = Math.abs(randomMachine.nextInt()) % (fieldsInRow * fieldsInColumn);
        for(int i = 0; i < numberOfMapDraws; i++) {
            moveInvisibleField(randomAndCorrectDirection(indexOfInvisibleBlock));
        }
    }

    public int randomAndCorrectDirection(int fieldNumber){
        int direction = Math.abs(randomMachine.nextInt()) % 4;

        while(!isDirectionCorrect(fieldNumber, direction)){
            direction = Math.abs(randomMachine.nextInt()) % 4;
        }

        return direction;
    }

    public void buttonWasClicked(int fieldNumber) {
        if(isFieldAvailable(fieldNumber)) {
            swapBlocks(fieldNumber, indexOfInvisibleBlock);
            indexOfInvisibleBlock = fieldNumber;
        } else {
            Log.d("button clicked", "field unavailable");
        }
    }

    public boolean isFieldAvailable(int fieldNumber) {
        if((fieldNumber == indexOfInvisibleBlock - fieldsInRow)
                || (fieldNumber == indexOfInvisibleBlock + 1)
                || (fieldNumber == indexOfInvisibleBlock + fieldsInRow)
                || (fieldNumber == indexOfInvisibleBlock - 1))
            return true;
        else
            return false;
    }

    public void moveInvisibleField(int direction) {
        int fieldIndexToSwap = getFieldIndexToSwap(direction);
        swapBlocks(indexOfInvisibleBlock, fieldIndexToSwap);
    }

    public void swapBlocks(int field1, int field2) {
        int tmp = fieldsMap[field1];
        fieldsMap[field1] = fieldsMap[field2];
        fieldsMap[field2] = tmp;
    }

    public int getFieldIndexToSwap(int direction) {
        switch (direction) {
            case 0:
                return indexOfInvisibleBlock - fieldsInRow;

            case 1:
                return indexOfInvisibleBlock + 1;

            case 2:
                return indexOfInvisibleBlock + fieldsInRow;

            case 3:
                return indexOfInvisibleBlock - 1;
        }
        return 0;
    }

    public boolean isDirectionCorrect(int fieldNumber, int direction){
        switch (direction){
            case 0: //north
                if(fieldNumber - fieldsInRow >= 0) {
                    return true;
                } else {
                    return false;
                }

            case 1: //east
                if(fieldNumber + 1 < fieldsInRow) {
                    return true;
                } else {
                    return false;
                }

            case 2: //south
                if(fieldNumber + fieldsInRow < fieldsInRow * fieldsInColumn) {
                    return true;
                } else {
                    return false;
                }

            case 3: //west
                if(fieldNumber - 1 >= 0) {
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }

    public int getField(int fieldNumber) {
        return fieldsMap[fieldNumber];
    }

    public boolean checkIfYouWon() {
        for (int i = 0; i < fieldsMap.length; i++) {
            if(fieldsMap[i] != i+1) {
                return false;
            }
        }
        return true;
    }

    public int getInvisibleField() {
        return indexOfInvisibleBlock;
    }
}
