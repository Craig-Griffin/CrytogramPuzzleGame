import misc.FileHandler;
import misc.MappingType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


class GameTest {
	private static GameModel game;


	@BeforeAll
	static void init() {
		game = new GameModel(MappingType.LETTERS, new FileHandler());
	}

	@AfterEach
    void cleanup() {
        File testplayer = new File("testplayer_save.txt");
        testplayer.delete();
    }

	/**
	 * This will test 
	 */
	@Test
	void makeSureAnEmptyUserStringOnlyContainsHashTagsForLetters() {
		char[] userInput = game.getUserInput().toCharArray();

		for(char c: userInput) {
			assertTrue(c=='#' || c== ' ' || c=='.' || c==',');
		}	
	}


	/**
	 * Make sure that the random letter mapping is working.
	 */
	@Test
	void makeSureSolutionAndCrytogramAreNotTheSame() {
		assertTrue(!game.getCrytogram().equals(game.getSolution()) );
	}

	@Test
	void removeLetter() {
		Player john = new Player("testplayer");
		game.mapLetter(Character.toLowerCase(game.getCrytogram().charAt(2)), Character.toLowerCase(game.getSolution().charAt(2)), john);
		assertTrue(Character.toLowerCase(game.getUserInput().charAt(2)) == Character.toLowerCase(game.getSolution().charAt(2)));
		game.removeLetter(Character.toLowerCase(game.getCrytogram().charAt(2)));
		assertTrue(Character.toLowerCase(game.getUserInput().charAt(2)) == '#');
	}

	@Test
	void addLetter() {
		Player john = new Player("testplayer");
		game.mapLetter(Character.toLowerCase(game.getCrytogram().charAt(2)), 'a', john);
		assertTrue(Character.toLowerCase(game.getUserInput().charAt(2)) == 'a');
		//checking for overwriting mapping (prints message in console)
		game.mapLetter(Character.toLowerCase(game.getCrytogram().charAt(2)), 'b', john);
		assertTrue(Character.toLowerCase(game.getUserInput().charAt(2)) == 'a');
	}

	@Test
	void autocomplete() {
		Player john = new Player("testplayer");
		assertNotEquals(game.getUserInput(), game.getSolution());
		game.autocomplete(john);
		assertEquals(game.getUserInput(), game.getSolution());

	}


}