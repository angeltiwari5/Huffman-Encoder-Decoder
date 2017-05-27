#
# 
#

# define a makefile variable for the java compiler
#
JCC = javac

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JFLAGS = -g

# typing 'make' will invoke the first target entry in the makefile 
# (the default one in this case)
#
default: Node.class BinaryHeap.class HuffmanTree.class encoder.class IterateBit.class decoder.class

# this target entry builds the Average class
# the Average.class file is dependent on the Average.java file
# and the rule associated with this entry gives the command to create it
#
Node.class: Node.java
	$(JCC) $(JFLAGS) Node.java	
	
BinaryHeap.class: BinaryHeap.java
	$(JCC) $(JFLAGS) BinaryHeap.java	
	
HuffmanTree.class: HuffmanTree.java
	$(JCC) $(JFLAGS) HuffmanTree.java

encoder.class: encoder.java
	$(JCC) $(JFLAGS) encoder.java
IterateBit.class: IterateBit.java
	$(JCC) $(JFLAGS) IterateBit.java
decoder.class: decoder.java
	$(JCC) $(JFLAGS) decoder.java
		
# To start over from scratch, type 'make clean'.  
# Removes all .class files, so that the next make rebuilds them
#
clean: 
	$(RM) *.class