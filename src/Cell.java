
/**
 * Cell.java
 * This Class models a single cell.
 *
 * @author Dobromir
 */
public class Cell implements Cloneable {
	private int cellValue; // 1 or 0
	private int xCor; // Coordinates of the cell
	private int yCor;

/**
 *  Constructor
 * @param cellValue
 * @param xCor
 * @param yCor
 */
	public Cell(int cellValue, int xCor, int yCor) {
		this.cellValue = cellValue;
		this.xCor = xCor;
		this.yCor = yCor;
	}

	/**
	 * This method changes the value of the cell from red(0) to green(1) 
	 * and vice versa.
	 */
	public void changeCellValue() {
		if (cellValue == 1) {
			cellValue = 0;
		} else if (cellValue == 0) {
			cellValue = 1;
		}
	}

	/**
	 * Returns the current value of a cell.
	 * 
	 * @return cellValue
	 */
	public int getCellValue() {
		return cellValue;

	}

	public void setCellValue(int value) {
		this.cellValue = value;
	}

	// Getters and setters for the coordinates
	public int getxCor() {
		return xCor;
	}

	public void setxCor(int xCor) {
		this.xCor = xCor;
	}

	public int getyCor() {
		return yCor;
	}

	public void setyCor(int yCor) {
		this.yCor = yCor;
	}

	public String toString() {
		return ("Cell with coords: " + " " + xCor + " " + yCor
				+" " + cellValue);
	}
}
