package hw3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import api.Tile;
import ui.GameConsole;

/**
 * Utility class with static methods for saving and loading game files.
 */

public class GameFileUtil {
	/**
	@NicholasKirschbaum
	*/
	 
	/**
	 * 
	 * Saves the current game state to a file at the given file path.
	 * <p>
	 * The format of the file is one line of game data followed by multiple lines of
	 * game grid. The first line contains the: width, height, minimum tile level,
	 * maximum tile level, and score. The grid is represented by tile levels. The
	 * conversion to tile values is 2^level, for example, 1 is 2, 2 is 4, 3 is 8, 4
	 * is 16, etc. The following is an example:
	 * 
	 * <pre>
	 * 5 8 1 4 100
	 * 1 1 2 3 1
	 * 2 3 3 1 3
	 * 3 3 1 2 2
	 * 3 1 1 3 1
	 * 2 1 3 1 2
	 * 2 1 1 3 1
	 * 4 1 3 1 1
	 * 1 3 3 3 3
	 * </pre>
	 * 
	 * @param filePath the path of the file to save
	 * @param game     the game to save
	 */
	public static void save(String filePath, ConnectGame game) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			// TODO: write to file, can use writer.write()
			
			
			int min = game.getMinTileLevel();
			int max = game.getMaxTileLevel();
			int score = (int)game.getScore();
			Grid grid = game.getGrid();
			
			int hei = grid.getHeight();
			int width = grid.getWidth();
			
				writer.write("" + width);
				writer.write(" ");
				writer.write("" + hei);
				writer.write(" ");
				writer.write("" + min);
				writer.write(" ");
				writer.write("" + max);
				writer.write(" ");
				writer.write("" + score);
				writer.write(width);
				for(int i = 0; i < (hei); i++) {
					for(int j = 0; j < (width); j++) {
						Tile tile = grid.getTile(j,i);
						writer.write("" + tile.getLevel());
						if (j + 1 != width) {
							writer.write(" ");
						}
						
					}
					if (i + 1 != hei) {
						writer.write(width);
					}
				}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the file at the given file path into the given game object. When the
	 * method returns the game object has been modified to represent the loaded
	 * game.
	 * <p>
	 * See the save() method for the specification of the file format.
	 * 
	 * @param filePath the path of the file to load
	 * @param game     the game to modify
	 */
	public static void load(String filePath, ConnectGame game) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String[] restart = reader.readLine().split(" ");
			int width = Integer.parseInt(restart[0]);
			int height = Integer.parseInt(restart[1]);
			int min = Integer.parseInt(restart[2]);
			int max = Integer.parseInt(restart[3]);
			long score = Integer.parseInt(restart[4]);
			
			
			
			
			Grid grid = new Grid(width, height);
			game.setMaxTileLevel(max);
			game.setMinTileLevel(min);
			game.setScore(score);
			grid = new Grid(width, height);
			for (int i = 0; i < height; i++) {
				String[] resetup = reader.readLine().split(" ");
				for (int j = 0; j < width; j++) {
					
					
					
					int brain = Integer.parseInt(resetup[j]);
					Tile work = new Tile(brain);
					grid.setTile(work, j, i);
					
					
					
				}
				
			}
			game.setGrid(grid);
			
			
		}
		catch (IOException ex) {
			  	// no error handling required for this project - do nothing
		}
		
		
		
		
	
			
			
			//game.setMaxTileLevel(max);
			//game.setMinTileLevel(min);
			//game.setScore(score);
					int max = 6;
					int width = 2;
					int height = 2;
					int min = 2;
					long score = 212;
					//Grid grid = new Grid();
					Scanner scan = new Scanner(filePath);
					//while(scan.hasNext()) {
						if(scan.hasNextInt()) {
							width = scan.nextInt();
						}
						if(scan.hasNextInt()) {
							 height = scan.nextInt();
						}
						if(scan.hasNextInt()) {
							min = scan.nextInt();
						}
						if(scan.hasNextInt()) {
							max = scan.nextInt();
						}
						if(scan.hasNextInt()) {
							 score = scan.nextInt();
						}
						

					//}
					game.setScore(score);
					game.setMaxTileLevel(max);
					game.setMinTileLevel(min);
					Grid grid = new Grid(width, height);
					for(int i = 0; i < (height); i++) {
						for(int j = 0; j < (width); j++) {
							Tile tie = new Tile(1);
							
							grid.setTile(tie, i, j);
						}
					}
					
					
					
					/**
					int width = scan.nextInt();
					scan.next();
					int height = scan.nextInt();
					scan.nextLine();
					int min = scan.nextInt();
					scan.nextLine();
					int max = scan.nextInt();
					scan.nextLine();
					long score = scan.nextInt();
					scan.nextLine();
					game.setMaxTileLevel(max);
					game.setMinTileLevel(min);
					
					game.setScore(score);
					for(int i = 0; i < (height); i++) {
						for(int j = 0; j < (width); j++) {
							//grid.setTile(//nextline, i, j);
						}
					}

				//} catch (FileNotFoundException ex) {
				  // no error handling required for this project - do nothing
				//}
				 * **
				 */
		
		
		
		
	}
}
