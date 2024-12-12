
# Quiz App - Spring Boot

This is a simple Spring Boot application that implements a quiz system where a user can:
1. Start a new quiz session.
2. Get a random multiple choice question from a set of questions in the database.
3. Submit an answer.
4. Get the total number of questions answered with the details on correct and incorrect submissions.

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- Java 11 or later
- Maven
- MySQL
- Postman (for testing the API)

## Setup

1. **Clone the repository**

   Clone this repository to your local machine:

   ```bash
   git clone https://github.com/yourusername/quiz-app.git
   cd quiz-app
Set up the database

You need to create a MySQL database. You can execute the following SQL commands to set up your database:

sql
Copy code
CREATE DATABASE quiz_app;

USE quiz_app;

-- Table for storing questions
CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(255),
    option_a VARCHAR(255),
    option_b VARCHAR(255),
    option_c VARCHAR(255),
    option_d VARCHAR(255),
    correct_option CHAR(1)
);

-- Table for storing user answers
CREATE TABLE user_answers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    question_id INT,
    selected_option CHAR(1),
    is_correct BOOLEAN,
    FOREIGN KEY (question_id) REFERENCES questions(id)
);
Seed the database with a few questions using this SQL:

sql
Copy code
INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_option)
VALUES 
('What is the capital of France?', 'Paris', 'London', 'Rome', 'Berlin', 'A'),
('Which planet is known as the Red Planet?', 'Earth', 'Mars', 'Jupiter', 'Saturn', 'B');
Configure application properties

Update the application.properties file in src/main/resources to connect to your MySQL database:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/quiz_app
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
Replace yourpassword with your actual MySQL password.

Build and run the application

You can run the application using the following Maven commands:

bash
Copy code
mvn clean install
mvn spring-boot:run
This will start the application on http://localhost:8080.

API Endpoints
1. Start a new quiz session
URL: POST /api/quiz/start
Response:
{
    "message": "Quiz session started!",
    "userId": 1
}

2.. Get a random question
URL: GET /api/quiz/question?userId=1
Response:
{
    "id": 1,
    "questionText": "What is the capital of France?",
    "optionA": "Paris",
    "optionB": "London",
    "optionC": "Rome",
    "optionD": "Berlin"
}
3. Submit an answer
URL: POST /api/quiz/answer?userId=1&questionId=1&selectedOption=A     (make sure u change the question id while submiiting the anaswer)
Response:
{
    "message": "Answer submitted successfully!",
    "isCorrect": true,
    "correctOption": "A"
}
4. Get the user's score
URL: GET /api/quiz/score?userId=1
Response:

{
    "totalAnswered": 1,
    "correctAnswers": 1,
    "incorrectAnswers": 0
}
