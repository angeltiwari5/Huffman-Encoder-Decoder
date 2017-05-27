
import java.io.*;
import java.util.*;

public class encoder {

	public static void HuffmanEncoder(ArrayList<Integer> input, HashMap<Integer, String> codeTable) {
		Iterator itr = input.iterator();
		StringBuilder encodedString = new StringBuilder("");
		

		while (itr.hasNext()) {
			int nxt = (int) itr.next();
			encodedString.append(codeTable.get(nxt));
		}

		byte[] bytearray = new byte[encodedString.length() / 8];
		for (int i = 0; i < (encodedString.length() / 8); i++) {
			bytearray[i] = (byte) Short.parseShort(encodedString.substring(8 * i, 8 * (i + 1)), 2);
		}

		String outFile = "encoded.bin";
		try {
			OutputStream outputFile = new BufferedOutputStream(new FileOutputStream(outFile));
			outputFile.write(bytearray);
			outputFile.close();

		} catch (IOException ex) {
			System.out.println("Error writing file '" + outFile + "'");
		}
	}
	
	public static void main(String[] args) {
			HashMap<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
			ArrayList<Integer> inputList = new ArrayList<Integer>();

			String fileInput = args[0];
			String str = null;
			try {
				FileReader reader = new FileReader(fileInput);
				BufferedReader br = new BufferedReader(reader);
				while ((str = br.readLine()) != null) {
					inputList.add(Integer.parseInt(str));
					if (frequencyMap.containsKey(Integer.parseInt(str))) {
						int valCount = frequencyMap.get(Integer.parseInt(str));
						frequencyMap.put(Integer.parseInt(str), ++valCount);
					} else {
						frequencyMap.put(Integer.parseInt(str), 1);
					}
				}

				br.close();
			} catch (FileNotFoundException ex) {
				System.out.println("Error opening file " + fileInput );
			} catch (IOException ex) {
				System.out.println("Error reading file " + fileInput );

			}
			
			Set keys = frequencyMap.entrySet();
			ArrayList<Node> frequencyBinary = new ArrayList<Node>();
			
			Iterator itr = keys.iterator();
			while (itr.hasNext()) {
				Map.Entry mp = (Map.Entry) itr.next();
				Node node = new Node((int) mp.getValue(), (int) mp.getKey());
				frequencyBinary.add(node);
			
			}
			
			  //long startbinheap = System.currentTimeMillis(); 
			  ArrayList<Node> huffmanTree = new ArrayList<Node>(); 
			  ArrayList<Node> binaryNodeArray = BinaryHeap.buildHeap(frequencyBinary); 
			  huffmanTree = HuffmanTree.generateHuffmanFromBinaryHeap(binaryNodeArray);
			  //System.out.println("Binary Heap CodeTable Created: " + (System.currentTimeMillis() - start));
			  HuffmanTree.codeAssignment(huffmanTree.get(0));
			  
			  HashMap<Integer, String> codeTable = new HashMap<Integer, String>();
			  codeTable = HuffmanTree.generateCodeTable(huffmanTree.get(0));
			  //System.out.println("Binary Heap CodeTable Created: " + (System.currentTimeMillis() - start)); 
			  
			  HuffmanTree.outputCodeTable(codeTable);
			  //System.out.println("Binary Heap Code Table Written in: " +
			  //(System.currentTimeMillis() - start));
			  
			  encoder.HuffmanEncoder(inputList, codeTable);
			  
			  //System.out.println("Binary Heap Encoding completed in: " +
			  //(System.currentTimeMillis() - start)); // End of Encoding

	}

}
