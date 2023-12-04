package poo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Plateau extends Pane {

    private static final int CELL_SIZE = 20;
    private static final int GRID_WIDTH = 20;
    private static final int GRID_HEIGHT = 15;

    private CellType[][] cells;

    public Plateau() {
        cells = new CellType[GRID_WIDTH][GRID_HEIGHT];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                Rectangle cell = new Rectangle(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                cell.setFill(Color.WHITE);
                cell.setStroke(Color.BLACK);
                getChildren().add(cell);

                cells[x][y] = CellType.EMPTY;
            }
        }
    }

    public void setCellType(int x, int y, CellType type) {
        cells[x][y] = type;
        updateCellAppearance(x, y);
    }

    private void updateCellAppearance(int x, int y) {
        Rectangle cell = (Rectangle) getChildren().get(y * GRID_WIDTH + x);

        switch (cells[x][y]) {
            case EMPTY:
                cell.setFill(Color.WHITE);
                break;
            case SNAKE:
                cell.setFill(Color.GREEN);
                break;
            case FOOD:
                cell.setFill(Color.RED);
                break;
            // Ajoutez d'autres types de cellules au besoin
        }
    }

    public enum CellType {
        EMPTY, SNAKE, FOOD
        // Ajoutez d'autres types de cellules au besoin
    }
}
