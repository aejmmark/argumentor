###Design document

The goal is to create a app that can produce new sentences on demand based on previously provided data in the form of written text. The words, their frequency together and their positions in sentences are stored in one or several digital trees. This data is then utilized by an algorithm based on a Markov chain. It picks words at random and adjusts the odds based on how often the words are used together in the original text. Similarly it randomizes the end of the sentence based on how often sentences end in the previously selected word. This should result in somewhat coherent sentences. The algorithm goes through the tree once so the complexity should be O(n). The space required for the data depends on the desired result. More data means more unique sentences and the other way round.

The project will be done using Java, but I am also familiar with Python.

I am a Bachelor of Computer Science