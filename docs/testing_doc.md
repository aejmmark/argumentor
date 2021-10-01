## Testing document

### Automatic tests

![](/docs/images/jacoco.png)

All files aside from the App.java file have automated JUnit tests.

The tests can be run with:

    ./gradlew test

The generated hmtl file can be viewed at:

    ./app/build/jacocoHtml/index.html

### Manual tests

UI has been tested for correct and incorrect inputs.

Similarity of the generated sentences.
* The original version produces notably different sentences.
* The alternative version produces copies of existing sentences.

Speed of data processing and sentence generation was tested using a very large text file. Both versions had similar results with the alternative version being slightly faster in both cases.