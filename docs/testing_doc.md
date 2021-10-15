## Testing document

### Automated tests

![](/docs/images/jacoco.png)

All classes aside from App and UserInterface have automated JUnit tests. The few parts missed by the automated tests have been tested with manual tests.

The tests can be run with:

    ./gradlew test

The generated hmtl file can be viewed at:

    ./app/build/jacocoHtml/index.html

### Manual tests

UI has been tested for correct and incorrect inputs.

Extending sentences by returning the the root node has been tested manually by generating lengthy sentences. Errors would cause the sentence to be shorter than intended or the word "ROOT" to appear in the text.

Similarity of the generated sentences has been tested by comparing text from the data file to the generated sentences. This has been done several times and with varying sentence lengths.
* The default version produces sentences that are notably different than those found in the source material.
* The alternative version mainly replicates existing sentences.

Speed of data processing and sentence generation can be tested from the user interface. These tests use the data.txt file.

![](/docs/images/performance.png)