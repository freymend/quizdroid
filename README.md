# quizdroid
An application that will allow users to take multiple-choice quizers

## Stories

- As a user, when I start the app, I should see a list of different topics on which to take a quiz. (For now, these should be hard-coded to read `Math`, "Physics", and `Marvel Super Heros`, as well as any additional topics you feel like adding into the the mix.)
- As a user, when I select a topic from the list, it should take me on the `topic overview` page, which displays a brief description of this topic, the total number of questions in the topic, and a `Begin` button taking me to the first question.
- As a user, when I press the `Begin` button from the topic oerview page, it shuold take me to the first question page, which will consist of a question (`TextView`), four radio buttons each of which consist of one answer, and a `Submit` button.
- As a user, when I am on a question page and I press the `Submit` button, if no radio button is selected, it should do nothing. If a radio button is checked, it should take me to the `answer` page. (Ideally, the submit button should not be visible until a radio button is selected.)
- As a user, when I am on the `answer` page, it should display the answer I gave, the correct answer, and tell me the total number of correct vs incorrect answers so far(`You have 5 out of 9 correct`), and display a `Next` button taking me to the next question page, or else display a `Finish` button if that is the last question in the topic.
- As a user, when I am on the `answer` page, and it is the last question of the topic, the `Finish` button should take me back to the first topic-list page, so that I can take a new quiz.

## Grading 

- repo should be called `quizdroid` on branch `part1`
- repo should contain all necessary build artifacts

### Grading (5pts)

- We will cone and build it from the GH repo
- 1 pt for each story

### Extra Credit (2pts)

- As a user on the question page, if I hit the `back` button it should NOT take me to the answer summary page of the previous question, but to the previous question page directly. (1 pt)
- As a user on the first question page of a topic, hitting `back` should take me back to the topic list page, not the topic overview page. (1 pt)
