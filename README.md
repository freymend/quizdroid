# quizdroid

An application that will allow users to take multiple-choice quizzers
- now we will write the code to check the questions periodically, store the data, and allow for preferences

## Tasks

Refactor the `TopicRepository` to read a `JSON` file (data/questions.json) to use as the source of the `Topics` and `Questions`. Use a hard-coded file (available at <http://tednewardsandbox.site44.com/questions.json>) pushed to the device with adb for now; do **NOT** include it as part of the application's "assets", as a future version will be replacing the file after the application has been deployed.

The application should provide a `Preferences` action bar item that brings up a `Preferences` activity containing the application's configurable settings: `URL` to use for question data, and how often to check for new downloads measured in minutes. If a download is currently underway, these settings should not take effect until the next download starts.

## Grading

repo should be called `quizdroid` on branch `storage`

repo should contain all necessary build artifacts

### Grading (5pts)

- 3 pts: `TopicRepostiroy` pulls all data from a JSON file
- 2 pts: Preferences displays configuration

### Extra Credit (2pts):

Use a custom JSON file of your own questions; if you do this, submit screenshots using your questions. Include the URL at which your JSON file can be found (it must be internet-accessible) so we can verify it. It must match the structure/format of the example

