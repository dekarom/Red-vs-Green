import java.util.*;

/**
 * Game.java 
 * Class maintaining the game states.
 * 
 * @author Dobromir
 */
public class Game {
	private int maxX; // Maximum values for X and Y for the Grid.
	private int maxY;
	private int counter; // Used to count total green state of the targeted cell in setGame()
	private int greenNum; // Number of green neighbors used in setGame()
	private ArrayList<Cell> gridList = new ArrayList<Cell>(); // Used to stores all cells in the grid
	private Map<Cell, ArrayList<Cell>> adjList = new LinkedHashMap<Cell, ArrayList<Cell>>(); // Adjacency list used to
																							// store each cell and its
																							// its neighbors.
	/**
	 * Method that initiates different stages of the game.
	 */
	public void startGame() {

		setGridSize();
		setGrid();
		createAdjacencyList(gridList);
		setGame();
	}

	/**
	 * Method that sets grid size based on user input.
	 */
	public void setGridSize() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the grid size!");
		String userGridSize = sc.nextLine();
		Scanner lineScanner = new Scanner(userGridSize).useDelimiter(",\\s*");
		maxX = lineScanner.nextInt();
		maxY = lineScanner.nextInt();
	}

	/**
	 * Method that takes the initial layout from the user and fills an array
	 *  with the necessary cells.
	 */
	public void setGrid() {
		Scanner sc = new Scanner(System.in);
		int x; // used to automatically assign coordinate to the new cells.
		ArrayList<String> wordContainer = new ArrayList<String>(); // Stores each line of the initial game state in a separate string.
		System.out.println("Please insert create intial state line by line !");

		for (int j = 0; j < maxY; j++) {
			wordContainer.add(sc.nextLine());
		}
		for (int i = 0; i < wordContainer.size(); i++) {
			x = 0;
			String line = wordContainer.get(i); // Represents one of the y lines.
			for (int r = 0; r < line.length(); r++) { // Creates a cell from the input.
				int cellValue = Character.getNumericValue(line.charAt(r));
				gridList.add(new Cell(cellValue, x, i));
				x++;
			}
		}
	}

	/**
	 * This method changes the state of the grid at each iteration
	 *  and counts the generation where the targeted cell is green.
	 */
	public void setGame() {
		
				Scanner sc = new Scanner(System.in); // Used to read the entire line.
				System.out.println("Please enter the desired cell and a number of turns!");
				String userInput = sc.nextLine();
				Scanner lineReader = new Scanner(userInput).useDelimiter(",\\s*"); // Used to read different elements of the line.
				int x = lineReader.nextInt(); // User input for the x and y coordinates of the monitored cell.
				int y = lineReader.nextInt();
				int turns = lineReader.nextInt(); // Number of iterations set by the user.

				for (int i = 0; i < turns + 1; i++) { // Each pass represents a new generation, formed by the branches.
					ArrayList<Cell> adjList1 = new ArrayList<Cell>(); // Used to temporary store the state of each cell 
																	 // for the next iteration.
				//	System.out.println("Gen" + " " + (i) + " " + counter);
					adjList.forEach((k, v) -> { // Iterates through each pair of elements from the HashMap.
						greenNum = 0;
						Cell temp = new Cell(k.getCellValue(), k.getxCor(), k.getyCor());
						if (temp.getCellValue() == 1 && temp.getxCor() == x && temp.getyCor() == y) {
							counter++;
						}
						for (int j = 0; j < v.size(); j++) {
							Cell temp1 = v.get(j);
							if (temp1.getCellValue() == 1) {
								greenNum++;
							}
						}
						if (temp.getCellValue() == 0) { // This branch deals with rules 1&2
							if (greenNum == 3 || greenNum == 6) {
								temp.changeCellValue();
							}
						}
						// This branch deals with rules 3&4.
						if (temp.getCellValue() == 1) {
							if (greenNum != 2 && greenNum !=3 && greenNum != 6) {
								temp.changeCellValue();
							}
						}
						adjList1.add(temp);
					});
					adjList.clear();
					createAdjacencyList(adjList1);
					adjList1.clear();
				}
				sc.close();
				lineReader.close();
				System.out.println("The desired cell will be green: " + counter + " generation(s)");
	}
	
	/**
	 * This method creates an adjacency list based on the neighboring cells, using a
	 * hashMap.
	 * 
	 * @param list - The list containing all the cells.
	 */
	public void createAdjacencyList(ArrayList<Cell> list) {
		Cell tempCell;

		for (int i = 0; i < list.size(); i++) {
			ArrayList<Cell> adjCell = new ArrayList<Cell>(); // used to temporary store neighbors.
			tempCell = list.get(i);
			Cell neignhbour;
			// The following branches are used to allocate neighboring cells if present.
			if (findCell(tempCell.getxCor(), tempCell.getyCor() - 1, list) != null) { // beneath
				neignhbour = findCell(tempCell.getxCor(), tempCell.getyCor() - 1, list);
				adjCell.add(neignhbour);
			}

			if (findCell(tempCell.getxCor() - 1, tempCell.getyCor(), list) != null) { // on the left
				neignhbour = findCell(tempCell.getxCor() - 1, tempCell.getyCor(), list);
				adjCell.add(neignhbour);
			}
			if (findCell(tempCell.getxCor() + 1, tempCell.getyCor(), list) != null) { // on the right
				neignhbour = findCell(tempCell.getxCor() + 1, tempCell.getyCor(), list);
				adjCell.add(neignhbour);
			}
			if (findCell(tempCell.getxCor(), tempCell.getyCor() + 1, list) != null) { // above
				neignhbour = findCell(tempCell.getxCor(), tempCell.getyCor() + 1, list);
				adjCell.add(neignhbour);
			}
			if (findCell(tempCell.getxCor() - 1, tempCell.getyCor() + 1, list) != null) { // upper left
				neignhbour = findCell(tempCell.getxCor() - 1, tempCell.getyCor() + 1, list);
				adjCell.add(neignhbour);
			}
			if (findCell(tempCell.getxCor() + 1, tempCell.getyCor() + 1, list) != null) { // upper right
				neignhbour = findCell(tempCell.getxCor() + 1, tempCell.getyCor() + 1, list);
				adjCell.add(neignhbour);
			}
			if (findCell(tempCell.getxCor() - 1, tempCell.getyCor() - 1, list) != null) { // lower left
				neignhbour = findCell(tempCell.getxCor() - 1, tempCell.getyCor() - 1, list);
				adjCell.add(neignhbour);
			}
			if (findCell(tempCell.getxCor() + 1, tempCell.getyCor() - 1, list) != null) { // lower right
				neignhbour = findCell(tempCell.getxCor() + 1, tempCell.getyCor() - 1, list);
				adjCell.add(neignhbour);
			}
			adjList.put(tempCell, adjCell); // Stores in HashMap of pair of keys and values.
		}
	}

	/**
	 * Helper method used to find cell from a list.
	 * 
	 * @param x    - Coordinates the search is based on.
	 * @param y
	 * @param list - List to be searched from.
	 * @return cell - Targeted  cell
	 */
	public Cell findCell(int x, int y, ArrayList<Cell> list) {
		Cell cell = null;
		for (int i = 0; i < list.size(); i++) {
			Cell tempCell = list.get(i);
			if (tempCell.getxCor() == x && tempCell.getyCor() == y) {
				cell = tempCell;
				return cell;
			}
		}
		return cell;
	}
}
