package hw3;

import java.util.ArrayList;
import java.util.Random;


import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Tile;

/**
 * Class that models a game.
 */

public class ConnectGame {
	/**
	@NicholasKirschbaum
	*/
	private ShowDialogListener dialogListener;
	private ScoreUpdateListener scoreListener;
	/*
	 * holds the height width min max random and score
	 */
	private int width = 0;
	private int height = 0;
	private int min = 0;
	private int max = 0;
	private int started = 0;
	private long score = 0;
	private Random rand;
	/*
	 * connectGrid is the grid used for this file
	 */
	
	private Grid connectGrid;
	private String dialog;
	/*
	 * genius is the first tiles level
	 */
	
	private int genius;
	/*
	 * first is the first tile added to the array
	 */
	
	private Tile first;
	/*
	 * helps hold selected values
	 */
	private ArrayList<Tile> list = new ArrayList<Tile>();
	
	
	
	
	
	/**
	 * Constructs a new ConnectGame object with given grid dimensions and minimum
	 * and maximum tile levels.
	 * 
	 * @param width  grid width
	 * @param height grid height
	 * @param min    minimum tile level
	 * @param max    maximum tile level
	 * @param rand   random number generator
	 */
	public ConnectGame(int width2, int height2, int min2, int max2, Random rand2) {
		// TODO
		this.width = width2;
		this.height = height2;
		this.min = min2;
		this.max = max2;
		this.rand = rand2;
		
		this.connectGrid = new Grid(width2, height2);
		
		//this.dialogListener = new ShowDialogListener(dialog);
		
	}

	/**
	 * Gets a random tile with level between minimum tile level inclusive and
	 * maximum tile level exclusive. For example, if minimum is 1 and maximum is 4,
	 * the random tile can be either 1, 2, or 3.
	 * <p>
	 * DO NOT RETURN TILES WITH MAXIMUM LEVEL
	 * 
	 * @return a tile with random level between minimum inclusive and maximum
	 *         exclusive
	 */
	public Tile getRandomTile() {
		// TODO
		
		int number = (rand.nextInt(max - min) + min);
		Tile tile = new Tile(number);
		return tile;
	}

	/**
	 * Regenerates the grid with all random tiles produced by getRandomTile().
	 */
	
	public void radomizeTiles() {
		// TODO
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				connectGrid.setTile(getRandomTile(),i,j);
				
			}
		}
	}

	/**
	 * Determines if two tiles are adjacent to each other. The may be next to each
	 * other horizontally, vertically, or diagonally.
	 * 
	 * @param t1 one of the two tiles
	 * @param t2 one of the two tiles
	 * @return true if they are next to each other horizontally, vertically, or
	 *         diagonally on the grid, false otherwise
	 */
	public boolean isAdjacent(Tile t1, Tile t2) {
		/**
		 * Takes the X and y values and checks to see if the two tiles 
		 * are bordering eachother
		 */
		int t1x = t1.getX();
		int t1y = t1.getY();
		int t2x = t2.getX();
		int t2y = t2.getY();
		if ((t1x == t2x) && (t1y == (t2y + 1))) {
			return true;
		}
		else if ((t1x == t2x) && ((t1y + 1) == t2y)) {
			return true;
		}
		else if (((t1x + 1) == t2x) && (t1y == t2y)) {
			
			return true;
		}
		else if ((t1x == (t2x + 1)) && (t1y== t2y)) {
			return true;
		}
		else if (((t1x + 1) == t2x) && ((t1y + 1)== t2y)) {
			//repeat
			return true;
		}
		else if ((t1x == (t2x + 1)) && ((t1y + 1) == t2y)) {
			
			return true;
		}
		else if (((t1x + 1) == t2x) && (t1y == (t2y + 1))) {
			return true;
		}
		else if ((t1x == (t2x + 1)) && (t1y == (t2y + 1))) {
			return true;
		}
		else {
			return false;
		}
		// TODO
		
	}

	/**
	 * Indicates the user is trying to select (clicked on) a tile to start a new
	 * selection of tiles.
	 * <p>
	 * If a selection of tiles is already in progress, the method should do nothing
	 * and return false.
	 * <p>
	 * If a selection is not already in progress (this is the first tile selected),
	 * then start a new selection of tiles and return true.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return true if this is the first tile selected, otherwise false
	 */
	
	
	public boolean tryFirstSelect(int x, int y) {
		if(started == 0) {
			
			
			
			first = connectGrid.getTile(x, y);
			first.setSelect(true);
			
			list.add(connectGrid.getTile(x,y));
			//list.add(connectGrid.getTile(x,y));
			//list.add(first);
			genius = first.getLevel();
			
			started = 1;
			return true;
		}
		else {
			return false;
		}
		// TODO
	}

	/**
	 * Indicates the user is trying to select (mouse over) a tile to add to the
	 * selected sequence of tiles. The rules of a sequence of tiles are:
	 * 
	 * <pre>
	 * 1. The first two tiles must have the same level.
	 * 2. After the first two, each tile must have the same level or one greater than the level of the previous tile.
	 * </pre>
	 * 
	 * For example, given the sequence: 1, 1, 2, 2, 2, 3. The next selected tile
	 * could be a 3 or a 4. If the use tries to select an invalid tile, the method
	 * should do nothing. If the user selects a valid tile, the tile should be added
	 * to the list of selected tiles.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 */
	
	public void tryContinueSelect(int x, int y) {
		// TODO
		Tile followup = connectGrid.getTile(x, y);
		int smartness = followup.getLevel();
		Tile secondlast = list.get(list.size() - 1);
		
		
		if(secondlast == followup) {
			int X = secondlast.getX();
			int Y = secondlast.getY();
			secondlast.setSelect(false);
			list.remove(connectGrid.getTile(X,Y));
		}
		else {
			if(list.size() == 1) {
				if (genius == smartness) {
					list.add(connectGrid.getTile(x,y));
					followup.setSelect(true);
					genius = smartness;
					
				}
				
			}
			else if(list.size() > 1) {
				if ((genius == smartness) || (genius + 1 == smartness)) {
					list.add(connectGrid.getTile(x,y));
					genius = smartness;
					followup.setSelect(true);
					
				}
				
			}
		}
	}

	/**
	 * Indicates the user is trying to finish selecting (click on) a sequence of
	 * tiles. If the method is not called for the last selected tile, it should do
	 * nothing and return false. Otherwise it should do the following:
	 * 
	 * <pre>
	 * 1. When the selection contains only 1 tile reset the selection and make sure all tiles selected is set to false.
	 * 2. When the selection contains more than one block:
	 *     a. Upgrade the last selected tiles with upgradeLastSelectedTile().
	 *     b. Drop all other selected tiles with dropSelected().
	 *     c. Reset the selection and make sure all tiles selected is set to false.
	 * </pre>
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return return false if the tile was not selected, otherwise return true
	 */
	public boolean tryFinishSelection(int x, int y) {
		// TODO
		/**
		 * dup checks to see if this value is already in the list
		 */
		Boolean dup = false;
		Tile finalt = connectGrid.getTile(x, y);
		
		
		for (int i = 0; i < list.size() - 1; i++) {
			Tile lastBraincell = list.get(i);
			if (lastBraincell == finalt) {
				dup = true;
				
			}
			
		}
		if (dup == false) {
			
			for (int j = 0 ; j < list.size(); j++) {
				Tile gettingScore = list.get(j);
				// long = int?
				score += gettingScore.getValue();
				
				
				gettingScore.setSelect(false);
			}
			if (list.size() == 0) {
				Tile gettingScore = list.get(0);
				score += gettingScore.getValue();
			}
			
			dropSelected();
			list.clear();
			started = 0;
			return true;
		}
		return false;
	}

	/**
	 * Increases the level of the last selected tile by 1 and removes that tile from
	 * the list of selected tiles. The tile itself should be set to unselected.
	 * <p>
	 * If the upgrade results in a tile that is greater than the current maximum
	 * tile level, both the minimum and maximum tile level are increased by 1. A
	 * message dialog should also be displayed with the message "New block 32,
	 * removing blocks 2". Not that the message shows tile values and not levels.
	 * Display a message is performed with dialogListener.showDialog("Hello,
	 * World!");
	 */
	public void upgradeLastSelectedTile() {
		// TODO
		Tile upgrade = list.get(list.size() - 1);
		upgrade.setLevel(upgrade.getLevel() + 1);
		min += 1;
		max += 1;
		dialogListener.showDialog(dialog);
		//dialogListener += 1;
		//connectGrid.setListeners(dialogListener, ScoreUpdateListener scoreListener)
	}

	/**
	 * Gets the selected tiles in the form of an array. This does not mean selected
	 * tiles must be stored in this class as a array.
	 * 
	 * @return the selected tiles in the form of an array
	 */
	
	public Tile[] getSelectedAsArray() {
		//for (int i = 0; i < array2.length; i++) {
			//array2[i] = list.get(i);
		//}
		return list.toArray(new Tile[0]);
	}

	/**
	 * Removes all tiles of a particular level from the grid. When a tile is
	 * removed, the tiles above it drop down one spot and a new random tile is
	 * placed at the top of the grid.
	 * 
	 * @param level the level of tile to remove
	 */
	public void dropLevel(int level) {
		// TODO
		// make an array list with all the values and then run dropSelected();
		ArrayList<Tile> dropList = new ArrayList<Tile>();
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				Tile remove = connectGrid.getTile(i, j);
				if(remove.getLevel() == level) {
					dropList.add(remove);
					remove.setSelect(true);
					
				}
				
			}
		}
		
		for(int i = 0; i < width; i++) {
			Boolean pleasework = true;
			for(int j = 0; j < height - 1; j++) {
				Tile remove = connectGrid.getTile(i, j);
				Tile removeBellow = connectGrid.getTile(i, j + 1);
				if ((remove.isSelected() == false) && (removeBellow.isSelected() == true)) {
					//connectGrid.setTile(Tile tile, int x, int y);
					
					// we want to set removebellow(level) = remove(level)
					removeBellow.setLevel(remove.getLevel());
					//we want to randomize remove(level)
					remove.setLevel(rand.nextInt(max - 1) + min);
					remove.setSelect(false);
					removeBellow.setSelect(false);
					pleasework = false;
				}

				
				
			}
			if (pleasework == true) {
				for(int j = 0; j < height; j++) {
					Tile remove = connectGrid.getTile(i, j);
					
					
					if (remove.isSelected() == true) {
						
						remove.setLevel(rand.nextInt(max - 1) + min);
						remove.setSelect(false);
					}
					
					
				}
			}
			pleasework = false;
		}
		
	}

	/**
	 * Removes all selected tiles from the grid. When a tile is removed, the tiles
	 * above it drop down one spot and a new random tile is placed at the top of the
	 * grid.
	 */
	
	public void dropSelected() {
		// TODO
		
		Tile finalcompare = list.get(list.size() - 1);
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height - 1; j++) {
				Tile remove = connectGrid.getTile(i, j);
				Tile removeBellow = connectGrid.getTile(i, j + 1);
				if ((remove.isSelected() == false) && (removeBellow.isSelected() == true)) {
					//connectGrid.setTile(Tile tile, int x, int y);
					
					// we want to set removebellow(level) = remove(level)
					removeBellow.setLevel(remove.getLevel());
					//we want to randomize remove(level)
					remove.setLevel(rand.nextInt(max - 1) + min);
					removeBellow.setSelect(false);
				}

				
				
			}
			for(int j = 0; j < height; j++) {
				Tile remove = connectGrid.getTile(i, j);
				
				if ((remove.isSelected() == true) && (remove.getX() == finalcompare.getX()) && (remove.getY() == finalcompare.getY())) {
					upgradeLastSelectedTile();
					remove.setSelect(false);
				}
				else if (remove.isSelected() == true) {
					
					remove.setLevel(rand.nextInt(max - 1) + min);
					remove.setSelect(false);
				}
				
				
			}
		}
	}

	/**
	 * Remove the tile from the selected tiles.
	 * 
	 * @param x column of the tile
	 * @param y row of the tile
	 */
	public void unselect(int x, int y) {
		// TODO
		connectGrid.getTile(x,y).setSelect(false);
		list.remove(connectGrid.getTile(x,y));
		
	}

	/**
	 * Gets the player's score.
	 * 
	 * @return the score
	 */
	public long getScore() {
		// TODO
		return score;
	}

	/**
	 * Gets the game grid.
	 * 
	 * @return the grid
	 */
	public Grid getGrid() {
		// TODO
		return connectGrid;
	}

	/**
	 * Gets the minimum tile level.
	 * 
	 * @return the minimum tile level
	 */
	public int getMinTileLevel() {
		// TODO
		return min;
	}

	/**
	 * Gets the maximum tile level.
	 * 
	 * @return the maximum tile level
	 */
	public int getMaxTileLevel() {
		// TODO
		return max;
	}

	/**
	 * Sets the player's score.
	 * 
	 * @param score number of points
	 */
	public void setScore(long scored) {
		// TODO
		score = scored;
	}

	/**
	 * Sets the game's grid.
	 * 
	 * @param grid game's grid
	 */
	
	public void setGrid(Grid grid) {
		// TODO
		//for (int i = 0; i < width; i++) {
			//for (int j = 0; j < height; j++) {
			//	position[i][j] = grid;
			//}
			
		//}
		//position = ;
		connectGrid = grid;
	}

	/**
	 * Sets the minimum tile level.
	 * 
	 * @param minTileLevel the lowest level tile
	 */
	public void setMinTileLevel(int minTileLevel) {
		// TODO
		min = minTileLevel;
	}

	/**
	 * Sets the maximum tile level.
	 * 
	 * @param maxTileLevel the highest level tile
	 */
	public void setMaxTileLevel(int maxTileLevel) {
		// TODO
		max = maxTileLevel;
	}

	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Save the game to the given file path.
	 * 
	 * @param filePath location of file to save
	 */
	public void save(String filePath) {
		
		
		
		GameFileUtil.save(filePath, this);
		
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 */
	public void load(String filePath) {
		GameFileUtil.load(filePath, this);
		
	}
}
