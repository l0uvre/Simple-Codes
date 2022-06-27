/** LC 631 --- Design, HashTable */

import java.util.*;
public class Excel {

    private class Cell {
        /** the single value the cell holds */
        int val;

        /** all the cells the current cell maps to and their occurrences */
        Map<Cell, Integer> cellsToTimes;

        Cell() {
            val = 0;
            cellsToTimes = new HashMap<>();
        }

        void set(int val) {
            this.val = val;
            cellsToTimes.clear();
        }

        void add(Cell cell) {
            cellsToTimes.put(cell, cellsToTimes.getOrDefault(cell, 0) + 1);
        }

        int get() {
            if (cellsToTimes.isEmpty()) {
                return this.val;
            } else {
                int sum = 0;
                for (Cell cell : cellsToTimes.keySet()) {
                    sum += cellsToTimes.get(cell) * cell.get();
                }
                return sum;
            }
        }
    }

    Cell[][] cells;

    public Excel(int height, char width) {
        cells = new Cell[height + 1][width - 'A' + 1];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }

    }
    
    public void set(int row, char column, int val) {
        cells[row][column - 'A'].set(val);
    }
    
    public int get(int row, char column) {
        return cells[row][column - 'A'].get();
    }
    
    public int sum(int row, char column, String[] numbers) {
        Cell cell = cells[row][column - 'A'];
        cell.set(0);
        for (String number : numbers) {
            if (!number.contains(":")) {
                int j = number.charAt(0) - 'A';
                int i = Integer.valueOf(number.substring(1));
                cell.add(cells[i][j]);
            } else {
                String[] splits = number.split(":");
                int rowStart = Integer.valueOf(splits[0].substring(1));
                int rowEnd = Integer.valueOf(splits[1].substring(1));

                int colStart = splits[0].charAt(0) - 'A';
                int colEnd = splits[1].charAt(0) - 'A';

                for (int i = rowStart; i <= rowEnd; i++) {
                    for (int j = colStart; j <= colEnd; j++) {
                        cell.add(cells[i][j]);
                    }
                }
            }
        }
        return cell.get(); 
    }
}
