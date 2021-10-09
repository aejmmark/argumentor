### Week 5

This week ended up differently than planned as I started reworking many aspects of the project as a result of the peer review. My reviewer had some helpful advice and the project I reviewed contained some good solutions I could apply to my own project. Ideas such as extending sentences by juming back into the root and running performance test from the user interface. Overall it was very interesting to see a similar project that was implemented in a vastly different way.

I removed all the ui elements from the App class and moved them to their own UserInterface class. The new performance tests were also added to said class. Other than that I made various small changes across the project such as changing how random sentence length is determined and how ending sentences are handled among other things. This involved making some new tests and changing or removing existing ones. I also removed the App and UserInterface classes from the test reports as I have no plans for automated ui tests for now.

I updated the testing document with the new performance test data. I could probably add some tests checking if the generated sentences are present in the sample data or not depending on wich version is being run. Irregularities in the sample data may cause issues but maybe it is worth trying.
The implementation document was updated with space and time complexities for the main functions as well as some graphs to better help understand the differences between the two versions.

Time spent 10h