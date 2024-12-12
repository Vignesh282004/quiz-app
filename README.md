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

 ********** Mysql *********
2. CREATE DATABASE quiz_app;

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
**   insert some dummy data into ur db
3. INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_option)
VALUES 
('What is the capital of France?', 'Paris', 'London', 'Rome', 'Berlin', 'A'),
('Which planet is known as the Red Planet?', 'Earth', 'Mars', 'Jupiter', 'Saturn', 'B');

4.spring.datasource.url=jdbc:mysql://localhost:3306/quiz_app
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect

5. mvn clean install
mvn spring-boot:run


To help you push your Spring Boot project to GitHub and explain how to use it in the README.md file, I will guide you step-by-step through the entire process.

Part 1: Pushing the Project to GitHub
1. Initialize Git in Your Project Directory
If you haven't already, open your terminal and navigate to your project directory. Then, initialize a new Git repository in your project folder:

bash
Copy code
cd /path/to/your/project
git init
2. Create a .gitignore File
It's important to ignore files that don't need to be tracked by Git (e.g., target/ build folder, IDE settings, etc.).

Create a .gitignore file in your project root directory and add common patterns for a Java/Spring Boot project:

plaintext
Copy code
# Java
*.class
*.jar
*.war
*.ear

# IntelliJ IDEA
.idea/
*.iml
*.iws

# Maven
target/

# Eclipse
.project
.classpath
.settings/

# Logs
*.log
You can also use a pre-configured .gitignore for Java from GitHub's gitignore templates.

3. Stage and Commit Your Code
Now, stage all files and make the initial commit:

bash
Copy code
git add .
git commit -m "Initial commit"
4. Create a New Repository on GitHub
Go to GitHub and create a new repository. Follow these steps:

Click on the New button at the top right of the GitHub dashboard.
Enter a repository name (e.g., quiz-app).
Select whether to make it public or private.
Do not initialize with a README or a .gitignore since we've already done that locally.
5. Push the Local Repository to GitHub
After creating the repository on GitHub, GitHub will provide you with the commands to push your local repository to the GitHub remote repository. Use these commands:

bash
Copy code
git remote add origin https://github.com/yourusername/quiz-app.git
git branch -M main
git push -u origin main
Replace yourusername with your actual GitHub username.

Part 2: Writing the README.md File
Now that the project is pushed to GitHub, you'll want to explain how to run and test the API using Postman. Below is the markdown content for your README.md file.

Example README.md File
markdown
Copy code
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
