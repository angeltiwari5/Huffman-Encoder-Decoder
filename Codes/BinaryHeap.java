
import java.util.ArrayList;

public class BinaryHeap {
	
	
	
	public static ArrayList<Node> buildHeap(ArrayList<Node> nodeArr) {
		for (int i = nodeArr.size() / 2 - 1; i >= 0; i--) {
			minHeapify(nodeArr, i);
		}
		return nodeArr;
	}

	public static void minHeapify(ArrayList<Node> nodeArr, int index) {
		int left = Leftchild(index);
		int right = Rightchild(index);
		int smallest;
		if (left <= nodeArr.size() - 1 && nodeArr.get(left).compareTo(nodeArr.get(index)) == -1) {
			smallest = left;
		} else {
			smallest = index;
		}
		if (right <= nodeArr.size() - 1 && nodeArr.get(right).compareTo(nodeArr.get(smallest)) == -1) {
			smallest = right;
		}
		if (smallest != index) {
			Node n = nodeArr.get(index);
			nodeArr.set(index, nodeArr.get(smallest));
			nodeArr.set(smallest, n);
			minHeapify(nodeArr, smallest);
		}
	}

	public static Node Deletemin(ArrayList<Node> nodeArr) {
		if (nodeArr.size() < 0)
			return null;

		Node min = nodeArr.get(0);
		nodeArr.set(0, nodeArr.get(nodeArr.size() - 1));
		nodeArr.remove(nodeArr.size() - 1);

		minHeapify(nodeArr, 0);
		return min;
	}

	public static void Insert(ArrayList<Node> nodeArr, Node newNode) {
		nodeArr.add(nodeArr.size(), newNode);
		decreaseKey(nodeArr, newNode);
	}

	public static void decreaseKey(ArrayList<Node> nodeArr, Node newNode) {

		for(int i=nodeArr.size() - 1;i > 0 && nodeArr.get(Parent(i)).compareTo(nodeArr.get(i)) == 1;i=Parent(i)){
			Node temp = nodeArr.get(i);
			nodeArr.set(i, nodeArr.get(Parent(i)));
			nodeArr.set(Parent(i), temp);
		}
	}

	public static int Parent(int i) {
		return (i - 1) / 2;
	}

	public static int Leftchild(int i) {
		return 2 * i + 1;
	}

	public static int Rightchild(int i) {
		return 2 * i + 2;
	}

}