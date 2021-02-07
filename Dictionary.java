import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {

	public static void main(String[] args) {
		Map<String, Word> m = new HashMap<String, Word>();
		BufferedReader dictionaryReader = null;
		File fileName = new File("Dictionary.txt");
		int currIndex = -1;
		Word newWord;


		String first4 = "";
		String word = "";
		String currentLine = "";
		Word.Gtype currType = null;

		try {

			dictionaryReader = new BufferedReader(new FileReader(fileName));

			// to remove the first line saying "Dictionary:"
			dictionaryReader.readLine();

			System.out.println("\nReading data from " + fileName + ": ");
			while ((currentLine = dictionaryReader.readLine()) != null) {
				if (currentLine.length() >= 4) {

					if (currentLine.contains(";")) {

						first4 = currentLine.substring(0, 4);
						currentLine = currentLine.substring(4);

						switch (first4) {
						case "verb":
							currType = Word.Gtype.verb;
							break;
						case "noun":
							currType = Word.Gtype.noun;
							break;
						case "adje":
							currType = Word.Gtype.adjective;
							break;
						}

						currIndex = currentLine.indexOf(";");
						word = currentLine.substring(0, currIndex);
						currentLine = currentLine.substring(currIndex + 1);

						word = word.trim();
						word = word.substring(0, 1).toUpperCase() + word.substring(1);
						currentLine.trim();

						newWord = new Word(word, currentLine, currType);
						// System.out.println(newWord);
						m.put(word, newWord);
						
						word = "";
						currentLine = "";
						currType = null;
					}

				}
			}
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				if (dictionaryReader != null)
					dictionaryReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		// System.out.println(m.size() + " distinct words:");
		// System.out.println(m);
		Scanner keyboard = new Scanner(System.in);
		String wordToSearch = "Integer";
		System.out.println("Enter a Word to Search For.");
		wordToSearch = keyboard.nextLine();
		wordToSearch=wordToSearch.trim();
		wordToSearch = wordToSearch.substring(0, 1).toUpperCase() + wordToSearch.substring(1);
		

		System.out.println(m.get(wordToSearch) );
		keyboard.close();
	}
}
