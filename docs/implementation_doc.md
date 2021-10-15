## Implementation doc

Argumentor is a simple random sentence generator that reads text files and uses the data to build a trie structure that can then be utilized to generate sentences.
The main parts of the app are the processdata and generate functions.

The processdata function reads the text file given as an parameter and builds a trie from the separate words. Currently there are two different versions determined by the parameter boolean alt.
The default version builds a trie with only one node for each word and a hashmap containing every node to help keep track. This version is slower but it allows for significantly more random word combinations even with smaller source material. The alternative version builds a more standard trie with several nodes containing the same word scattered throughout the tree. This version is faster but the generated sentences are copies of those found in the default source material meaning that a very large dataset would be required.
The nodes in the trie contain hashmaps with node-int pairings representing edges. The int values represent the amount of tickets the node has that is then used to calculate the weight of the edge. The function starts from the root and adds new nodes to the previous node's hashmap. Every time it comes across a punctuation mark it resets the previous node back to being the root.

Graphs showing how the two versions react to the input "beans are awesome. I enjoy beans immensely. beans are horrible."

------------------------------------------------------------------------------------

Default

![](/docs/images/default.png)

------------------------------------------------------------------------------------

Alternative

![](/docs/images/alt.png)

------------------------------------------------------------------------------------

The generate functions starts from the root and chooses the next node by utilizing the root node's lottery method. It chooses a random number between 0 and the ticketSum variable and chooses the matching node by going through the edges hashmap. The winning node is then placed as the previous node and the loop continues until the sentence stops randomly or the given maximum word count is reached. After that the function returns the string containing all the words collected from the nodes.

### Time and space complexities

Default data processing
* Time: n loops consisting of two hashmap operations
complexity: O(n)
* Space: size n hashmap and up to n nodes each with their own hashmaps
complexity: O(nm)

Alternatve data processing
* Time: n loops with a single hashmap operation
complexity: O(n)
* Space: n nodes each with their own hashmaps
complexity: O(nm)

Sentence generation
* Time: n loops that loop through node hashmaps.
Note that the hashmaps are smaller in the alternative version.
complexity: O(nm)
* Space: only uses a predetermined set of variables
complexity: O(1)

### Issues and possible improvements

Default version runs into issues if sentences containing the same word twice are present in the source data. In these cases the generator may end up looping through the same sentence indefinitely. This is only notable on very large sentence length requests so for now it mainly affects performance testing.