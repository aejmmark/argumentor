## Testing document

### Automated tests

![](/docs/images/jacoco.png)

All classes aside from App and UserInterface have automated JUnit tests. This includes testing if the generated sentences appear in the source file. The few parts missed by the automated tests have been tested manually.

The tests can be run with:

    ./gradlew test

The generated hmtl file can be viewed at:

    ./app/build/jacocoHtml/index.html

### Manual tests

UI has been tested for correct and incorrect inputs.

Similarity of the generated sentences has also been tested manually by comparing text from the data file to the generated sentences. This has been done several times and with varying sentence lengths and chain lengths.

The list clears missed by the JUnit tests have been tested manually by applying output notifications each time they happen.

Speed of the data processing and sentence generation functios can be tested from the user interface. These tests use the data.txt file.

![](/docs/images/performance.png)
