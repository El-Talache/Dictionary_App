
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Dictionary {
	static Map<String, Word> m = new HashMap<String, Word>();

	public static void main(String[] args) {
		BufferedReader dictionaryReader = null;
		File fileName = new File("Dictionary.txt");
		int currIndex = -1;
		Word newWord;
		DictionaryGUI display = new DictionaryGUI();
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
					System.out.println("Dictionary Has been initialized with all the words");
				dictionaryReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}

		// System.out.println(m.size() + " distinct words:");
		// System.out.println(m);

	}

	public static Word getDicWord(String word) {
		Word thisWord = m.get(word);
		return thisWord;
	}

	public static boolean isWord(String word) {
		return m.containsKey(word);
	}

}

class DictionaryGUI implements ActionListener {

	JFrame frame = new JFrame("The Best Java Dictionary");
	JLabel label = new JLabel("Enter a Word to get its definition: 	");
	JLabel definition = new JLabel("");
	JButton defineButton = new JButton("Define");
	JTextField textBox = new JTextField(20);
	Dictionary myDic = new Dictionary();
	// Creating the panel at bottom and adding components
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();

	public DictionaryGUI() {

		defineButton.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		panel.setLayout(new GridLayout(3, 2));
		panel2.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
//		panel2.setLayout(new GridLayout(0, 1));
		panel.add(label);
		panel.add(textBox);
		panel.add(defineButton);

		panel2.add(definition);
		frame.add(panel, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.CENTER);
		frame.pack();
		frame.setSize(1000, 400);
		frame.setVisible(true);

	}

	public String getWord() {

		String word = "";
		if (textBox.getText() != null) {

			word = textBox.getText();
		}
		return word;

	}

	/**
	 * @Override
	 * 
	 *           This method will execute when the "Define" button is pressed
	 * 
	 * 
	 */
	public void actionPerformed(ActionEvent e) {

		if (!textBox.getText().isEmpty()) {
			String word = textBox.getText();

			word = word.trim();
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			if (Dictionary.isWord(word))
				show(Dictionary.getDicWord(word));
			else
				definition.setText("Word Not in Dictionary.\n Try Again");

		} else {
			definition.setText("Please, enter a Word before pressing Button!");
		}

	}

	public void show(Word word) {
		definition.setText(word.toString());
	}
}