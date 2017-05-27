
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;



public class decoder {
	
	public static void decode(String encodeFile,String codeTable) {
		try {
			Node node = new Node(-1, -1);
			String file = codeTable;
			String str = null;
			
			FileReader fileRead = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileRead);
			while ((str = reader.readLine()) != null) {
				String[] splited = str.split("\\s+");
				constructTree(splited[0], splited[1], node);
			}
			reader.close();

			File encodedFile = new File(encodeFile);
			byte[] encodedBytes = new byte[(int) encodedFile.length()];
			int totalBytes = 0;
			InputStream inputstrm = new BufferedInputStream(new FileInputStream(encodedFile));
			
			while (totalBytes < encodedBytes.length) {
				int RemBytes = encodedBytes.length - totalBytes;
				int readBytes = inputstrm.read(encodedBytes, totalBytes, RemBytes);
				if (readBytes > 0) {
					totalBytes = totalBytes + readBytes;
				}
			}
			
			inputstrm.close();
			

			StringBuilder sb = new StringBuilder("");
			FileWriter fileWriter = new FileWriter("decoded.txt");
			BufferedWriter outputfile = new BufferedWriter(fileWriter);
			IterateBit bitIterator = new IterateBit(encodedBytes);
			Node tree = node;

			while (bitIterator.hasNext()) {
				if (bitIterator.getNextBit()) {
					tree = tree.rightChild;
				} else {
					tree = tree.leftChild;
				}
				if (tree.key != -1) {
					sb.append(tree.key + "\n");
					tree = node;
				}
			}
			outputfile.write(sb.toString());
			outputfile.close();

		} 
		
		catch (IOException e) {

			e.printStackTrace();
		}
		catch (Exception ex) {
			System.out.println("File Not Found ");
		}
	}

	private static void constructTree(String str1, String str2, Node root) {
		StringBuilder treepath = new StringBuilder(str2);
		int counter = 0;
		while (counter < treepath.length() - 1) {
			if (treepath.charAt(counter) == '0' && root.leftChild == null) {
				Node n = new Node(-1, -1);
				root.leftChild = n;
				root = n;
			} else if (treepath.charAt(counter) == '0' && root.leftChild != null) {
				root = root.leftChild;
			} else if (treepath.charAt(counter) == '1' && root.rightChild == null) {
				Node temp = new Node(-1, -1);
				root.rightChild = temp;
				root = temp;
			} else if (treepath.charAt(counter) == '1' && root.rightChild != null) {
				root = root.rightChild;
			}
			counter++;
		}

		if (counter == treepath.length() - 1) {
			Node n = new Node(-1, Integer.parseInt(str1));
			if (treepath.charAt(counter) == '0') {
				root.leftChild = n;
			} else {
				root.rightChild = n;
			}
		}

	}
	public static void main(String[] args) {
		String encodedFile=args[0];
		String codeTable=args[1];
		decoder.decode(encodedFile,codeTable);
	}
}

