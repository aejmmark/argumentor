## Testing document

### Automatic tests

![](/docs/images/jacoco.png)

All classes aside from App and UserInterface have automated JUnit tests.

The tests can be run with:

    ./gradlew test

The generated hmtl file can be viewed at:

    ./app/build/jacocoHtml/index.html

### Manual tests

UI has been tested for correct and incorrect inputs.

Similarity of the generated sentences.
* The default version produces notably different sentences.
* The alternative version replicates existing sentences.

Speed of data processing and sentence generation can be tested from the user interface. These tests use the data.txt file.

![](/docs/images/performance.png)