import org.junit.Assert;
import org.junit.Test;

class GameTest {


	/**
	 * This will test 
	 */
	@Test
	void makeSureAnEmptyUserStringOnlyContainsHashTagsForLetters() {
		Game game = new Game();
		char[] userInput = game.getUserInput().toCharArray();

		for(char c: userInput) {
			Assert.assertTrue(c=='#' || c== ' ' || c=='.' || c==',');
		}	
	}


	/**
	 * Make sure that the random letter mapping is working.
	 */
	@Test
	void makeSureSolutionAndCrytogramAreNotTheSame() {
		Game game = new Game();
		Assert.assertTrue(!game.getCrytogram().equals(game.getSolution()) );
	}

	@Test
	void removeLetter() {
	}

	@Test
	void saveToDisk() {
	}

	@Test
	void autocomplete() {
	}

	@Test
	void getFrequencies() {
	}
}