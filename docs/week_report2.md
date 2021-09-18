### Week 2

This week I mostly focused on creating a basic working version of the app. This involved creating the relevant algorithms and datastructures. The main algorithms are the data processor and the sentence generator. The data processor goes through the given text file and stores all the words and their connections in the datastructures. The sentence generator uses a lottery ticket system to determine each word and builds a sentence by combining them.
At the time all sentences are 10 words long but I am planning on implementing a more detailed sentence ending system. I have been using a file containing text from the university site to test the app and it seems to be working in most cases. Sometimes it may encounter an error, so there must be an issue with the lottery system.

The algorithms utilize a tree-like datastructure using Word classes as nodes and Edge classes as the edges connecting them. The edges are weighted by assigning them lottery tickets based on how frequently they are used. The current version still relies on existing Java ArrayLists so further development is still needed.

A fairly significant amount of time was used for troubleshooting various issues caused by unfamiliarity with Gradle/Maven, javadocs, tests, Java syntax etc. So I learned quite a bit about developing with Java wich was the main reason for choosing the language. I ran into several errors with checkstyle so I will probably leave that to next week.

Questions:
Should I store sample data used by the data processing algorithm on github?

My next goal is to refactor the main functions into smaller separate functions and create tests for the separate parts. This will hopefully also help in finding the issues causing the crashes. I will also work on developing the sentence ending system and creating new classes to replace the ArrayLists currently used.

Time spent 11h