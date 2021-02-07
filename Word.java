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

	enum Gtype {
		noun, verb, adjective
	};

	/**
	 * 
	 * @param new Word    word to add
	 * @param Def 	definition of that word
	 * @param newType	type of the new word
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

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public Gtype getType() {
		return type;
	}

	public void setType(Gtype gType) {
		this.type = gType;
	}

}
