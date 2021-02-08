/**
 * @author GERARDO R PADILLA JR.
 * 
 *         This code is a basic
 * 
 */

public class Word {

	private String word;
	private String definition;
	private Gtype type;

	public enum Gtype {
		noun, verb, adjective
	};

	/**
	 * 
	 * @param new     Word word to add
	 * @param Def     definition of that word
	 * @param newType type of the new word
	 */
	Word(String newWord, String Def, Gtype newType) {
		setWord(newWord);
		setDefinition(Def);
		setType(newType);
	}

	Word(Word NewWord) {

		setWord(NewWord.getWord());
		setDefinition(NewWord.getDefinition());
		setType(NewWord.getType());
	}

	/**
	 * Returns the word that is here
	 * 
	 * @return word that is stored in this class
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Sets the word member to the parameter
	 * 
	 * @param word to be defined
	 *
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Returns the definition of the word
	 * 
	 * @return definition of the word
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * Sets the definition member to the parameter
	 * 
	 * @param definition to be added
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public Gtype getType() {
		return type;
	}

	public void setType(Gtype gType) {

		this.type = gType;
	}

	/**
	 * Will print the word, type of word, and a definition
	 * 
	 * @return the entire word and its information on a single line
	 */
	@Override
	public String toString() {
		
		return (word + ": (" + type + ") " + definition);

	}

}
