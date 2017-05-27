
class IterateBit {
	byte[] Array;
	int index = 0;
	int indexBit = 0;
	final static char[] masks = { 0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01 };

	public IterateBit(byte[] Array) {
		this.Array = Array;
	}
	boolean hasNext() {
		return !(indexBit == 8 && index == (Array.length - 1));
	}

	boolean getNextBit() {
		if (indexBit == 8) {
			index++;
			indexBit = 0;
		}
		if((Array[index] & masks[indexBit++]) > 0)
		return true ;
		else return false;
	}

	
}
