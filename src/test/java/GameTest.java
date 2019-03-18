import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {


	/**
	 * This will test 
	 */
	@Test
	void makeSureAnEmptyUserStringOnlyContainsHashTagsForLetters() {
		GameModel game = new GameModel(MappingType.LETTERS);
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
		GameModel game = new GameModel(MappingType.LETTERS);
		assertTrue(!game.getCrytogram().equals(game.getSolution()) );
	}

	@Test
	void removeLetter() {
		GameModel game = new GameModel(MappingType.LETTERS);
		Player john = new Player("john");
		assertTrue(Character.toLowerCase(game.getUserInput().charAt(2)) == '#');
		game.mapLetter(Character.toLowerCase(game.getCrytogram().charAt(2)), Character.toLowerCase(game.getSolution().charAt(2)), john);
		assertTrue(Character.toLowerCase(game.getUserInput().charAt(2)) == Character.toLowerCase(game.getSolution().charAt(2)));
		game.removeLetter(Character.toLowerCase(game.getCrytogram().charAt(2)));
		assertTrue(Character.toLowerCase(game.getUserInput().charAt(2)) == '#');
	}

	@Test
	void addLetter() {
		GameModel game = new GameModel(MappingType.LETTERS);
		Player john = new Player("john");
		assertTrue(Character.toLowerCase(game.getUserInput().charAt(2)) == '#');
		game.mapLetter(Character.toLowerCase(game.getCrytogram().charAt(2)), 'a', john);
		assertTrue(Character.toLowerCase(game.getUserInput().charAt(2)) == 'a');
		//checking for overwriting mapping (prints message in console)
		game.mapLetter(Character.toLowerCase(game.getCrytogram().charAt(2)), 'b', john);
		assertTrue(Character.toLowerCase(game.getUserInput().charAt(2)) == 'a');
	}

	@Test
	void autocomplete() {
		GameModel game = new GameModel(MappingType.LETTERS);
		Player john = new Player("john");
		assertNotEquals(game.getUserInput(), game.getSolution());
		game.autocomplete(john);
		assertEquals(game.getUserInput(), game.getSolution());

	}


}