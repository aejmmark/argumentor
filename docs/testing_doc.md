### Testing document

![](/docs/images/jacoco.png)

At the time most of the testing has been done using automatic JUnit tests.

The tests can be run with:

    ./gradlew test

The generated hmtl file can be viewed at:

    /app/build/jacocoHtml/index.html

In addition to that I have done several manual tests to make sure that the generated sentences are notably different than those found in the data.
The user interface will also be tested manually when it is complete.