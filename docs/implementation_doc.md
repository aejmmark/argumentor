## Implementation doc

Argumentor is a simple random sentence generator that reads text files and uses the data to build a trie structure that can then be utilized to generate sentences.
The main parts of the app are the processdata and generate functions.

The processdata function reads the chosen text file and builds a trie from the separate words based on the "chain" length given as an input. The function maintains a list that contains the previous words of the source data. At every loop it adds a new word and removes the oldest if the list is at chain length. Then it builds a trie branch with the addNodes function using the words on the list. Previously encountered nodes have their frequency value increased. Once the function runs into an sentence ending character (./!/?) it clears the list and starts building it up again.

------------------------------------------------------------------------------------

Demonstration of how the trie is built:

![](/docs/images/trie.png)

------------------------------------------------------------------------------------

The generate function maintains a list of the size chain length - 1. The function starts from the root and chooses the next word by utilizing the node's lottery function. This chooses a random number between 0 and the ticketSum variable and chooses the matching word by going through the edges hashmap and reducing the generated number with the nodes frequency values. The winning word is then added to the list and used to determine the next node. Once the list is full it begins to search for a matching node by using the nodeSearch function. This starts from the root and compares the words on the list to the edges of the current node. It repeats this until it reaches the last node of the branch and then determines next word using the lottery function. If the generator runs in to an incomplete branch or a node that has a sentence ending word as its key it clears the list. This whole process is repeated until the the given maximum word count is reached or sentence randomly stops if the chosen word count is 0.
After that the function returns the string containing all the collected words.

### Time and space complexities

Data processing
* Time: N loops consisting of 2 - 8 hashmap operations.

Complexity: O(N)
* Space: Up to N nodes each with their own size M hashmaps.

Complexity: O(NM)

Sentence generation
* Time: N loops consisting of 1 - 7 hashmaps operations and looping through a size M hashmap.

Complexity: O(NM)
* Space: Uses a list of size 1 - 7.

Complexity: O(1)

Note that the maximum chain length 8 was chosen arbitrarily and could be increased to any number. Doing so would only affect the results to a certain point as sentences naturally tend to be below a certain length.

### Issues and possible improvements

The data processing does not differentiate between sentence ending dots and ones in the middle of sentences such as in the case of mr. or dr.