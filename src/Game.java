public class Game {
    private OneToOneMap<Character, Character> currentMapping;
    private OneToOneMap<Character, Character> solutionMapping;
    private String crytogram;

    public Game(Player player, MappingType type) {
        currentMapping = new OneToOneMap<>();
        solutionMapping = new OneToOneMap<>();
        generateCrypto();
    }

    public void mapLetter(char cipher, char mapping) {
        if(!Character.isAlphabetic(cipher)) {
            System.out.println("Error! " + cipher + " is not an alphabetic character!");
            return;
        }

        if(!Character.isAlphabetic(mapping)) {
            System.out.println("Error! " + mapping + " is not an alphabetic character!");
            return;
        }

        if(currentMapping.containsKey(cipher)) {
            System.out.println(cipher + " already has a mapping! You mapped " + currentMapping.get(cipher) + " to it!");
            return;
        }

        if(currentMapping.containsValue(mapping)) {
            System.out.println("You already mapped " + currentMapping.getReverse(mapping) + " to " + mapping + "!");
            return;
        }

        currentMapping.put(Character.toUpperCase(cipher), Character.toUpperCase(mapping));
    }

    public void mapRandomLetter() {

    }

    public void removeLetter() {

    }

    public void saveToDisk() {

    }

    public void autocomplete() {

    }

    public void getFrequencies(){

    }

    private void generateCrypto() {

    }

    enum MappingType {
        LETTERS,
        NUMBERS;
    }
}
