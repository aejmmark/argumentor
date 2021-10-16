### Week 6

Week started with another peer review. I still find giving reviews extremely stressful so it is probably a good that I get some practise. Also always interesting to see how other people approach coding.
After that I spent some time developing a way to link more words together in the default tree, but I ended scrapping it due to time constraints and the fact that it probably would not affect the results significantly enough.

I refactored the whole Node adding process by replacing the existing Node and edge adding methods with a single one. This new method uses a boolean parameter similarly to how the dataProcess function works. These changes made the code more coherent and at the same time faster as I was able to remove an extra hashmap search.
I remade some of the tests to fit the new methods and at the same time fixed all the checkstyle errors that I had left. I ended up removing the docstring errors from the checkstyle config as I found that the test names contained enough information.

I made a very basic version of the user guide that informs how the app can be built and run. The way the jar version of the app handles txt files is currently problematic as the code uses the current directory to locate the file. I will probably have to find another way of doing this for the final version.

Time spent 8h