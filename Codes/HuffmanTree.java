
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HuffmanTree {
	
	static HashMap<Integer, String> Table = new HashMap<Integer, String>();
	

	public static ArrayList<Node> generateHuffmanFromBinaryHeap(ArrayList<Node> binaryHeap) {
		while (binaryHeap.size() > 1) {
			Node node1 = BinaryHeap.Deletemin(binaryHeap);
			Node node2 = BinaryHeap.Deletemin(binaryHeap);
			Node mergeNode = new Node(node1.frequency + node2.frequency, -1);
			mergeNode.leftChild = node1;
			node1.parent = mergeNode;
			mergeNode.rightChild = node2;
			node2.parent = mergeNode;

			BinaryHeap.Insert(binaryHeap, mergeNode);
		}
		return binaryHeap;
	}

	public static void codeAssignment(Node node) {
		if (node.parent != null && node == node.parent.leftChild) {
			node.path.append(node.parent.path);
			node.path.append("0");
		}
		if (node.parent != null && node == node.parent.rightChild) {
			node.path.append(node.parent.path);
			node.path.append("1");
		}
		if (node.leftChild != null)
			codeAssignment(node.leftChild);
		if (node.rightChild != null)
			codeAssignment(node.rightChild);
	}

	public static HashMap<Integer, String> generateCodeTable(Node node) {
		if (node.leftChild != null)
			generateCodeTable(node.leftChild);
		if (node.key != -1) {
			Table.put(node.key, node.path.toString());
		}
		if (node.rightChild != null)
			generateCodeTable(node.rightChild);

		return Table;
		
		
	}

	public static void outputCodeTable(HashMap<Integer, String> map) {
		StringBuilder sb = new StringBuilder("");
		Set s = map.entrySet();
		Iterator it = s.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			sb.append((int) entry.getKey() + " " + map.get((int) entry.getKey()) + "\n");
		}


		String file = "code_table.txt";
		try {
			FileWriter outputFile = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(outputFile);
			writer.write(sb.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	
	

	

	
	

	
}




