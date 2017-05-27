# Huffman-Encoder-Decoder
 1) Developed a Huffman encoder and decoder to reduce the size of data to be transferred from MyTube to Toggle Server.
 2) Encoder read a file of size up-to 70 MB and encoded the message in binary format file of size 24.6MB 
3)  Encoded file was used by decoder to obtain original message.
4) Implemented priority queue with binary heap(also Four Way Cache Heap,Pairing Heap were compared for performance) for Huffman tree construction took 19 milliseconds with worst case complexity of O(nlogn).
5)To build the project use the following command at terminal:
$ make
$ java encoder <input_file_name>

for Decoder:
$ java decoder <encoded_file_name> <code_tabe_file_name>
