## User guide

### Building the app

The app can be built with:

    ./gradlew build

### Executing the app

The app can be run with:

    java -jar ./app/build/libs/app.jar

Txt files must be in the ./app directory.

### Usage

Upon executing the application you have the choice to go straight to sentence generation with the default file, run the performance tests, or change the data file. The data file can be changed to any .txt containing text that is located in the ./app directory.

After choosing sentence generation the app asks for the desired chain length.
This determines how similar the generated sentences are to the original text. Smaller chain lengths result in more randomized sentences.

After choosing the chain length the app enters the generation state where you can decide the length of the generated sentence by typing a number. 0 leads to a random length sentence.

Typing exit at any time shuts down the app.