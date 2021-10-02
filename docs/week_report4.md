### Week 4

This weeks focus was on improving the app and writing the required documents. Major changes included adding an user interface and removing the Edge class and the Node class's edgeSum method. The ui currently allows the user to select the source text file and choose the desired length of the sentences. The ticket variable that was previously kept in the Edge class was moved to the Node class's edges hashmap containing Node-Integer pairings. The edgeSum method that previously counted the sum of all tickets in all edges connected to the node was replaced by a int ticketSum variable. This change should make the lottery method faster since it now has to go through the map only once.

I ended up creating an alternative tree structure using the existing classes and a new node adding function I added under the Node class. This version is more akin to a standard trie structure and is more efficient than the original. The generated sentences are always found within the source text, so to achieve randomness the generator would have to produce a series of sentences.

The new features required rewriting many of the automated tests. I also performed manual tests on the ui to make sure incorrect inputs would not break it. Additionally I made several tests comparing the efficiency and reliability of the two versions. Tests indicated that the alternative version functions faster but the original produced more desirable results.

Next week I will focus on developing a way to link more than two words together at a time. I will also keep working on the documents.