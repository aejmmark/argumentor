### Week 2

This week I updated the datastructure by adding a Tree class and moving most of of the data processing function components under it. Similarly I moved many components of the generator function under the Node class into separate functions. This involved removing the sentence ending system I had previously planned. Chainging how the function ended sentences also fixed the random crashing issue. Additionally I replaced the previously used ArrayLists with HashMaps to speed up the searching process. In its current form the app seems to be more efficient than in the pervious version. I should probably try an alternative version without the allNodes HashMap and instead rely on a search algorithm to see if there are notable differences.

I developed new tests for all the changed parts of the app and implemented the jacoco library to generate coverage reports. I also finally managed to get the checkstyle plugin to work properly and made changes to my code based on the reports received. I found the reports helpful in improving my programming habits so that my code is more readable in the future. Interesting to see that checkstyle had several issues with the code provided by my IDE.

Questions:
Was the comment about Tries referring to a new structure where generated sentences would be stored or changing the data structure that is currently in use?

Next goal is to develop the user interface and improve the functionality further.

Time spent 11h

