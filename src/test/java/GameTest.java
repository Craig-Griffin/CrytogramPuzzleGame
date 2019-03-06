import org.junit.Assert;
import org.junit.Test;

class GameTest {


	/**
	 * This will test 
	 */
	@Test
	void makeSureAnEmptyUserStringOnlyContainsHashTagsForLetters() {
		GameModel game = new GameModel(MappingType.LETTERS);
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
		GameModel game = new GameModel(MappingType.LETTERS);
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