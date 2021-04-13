### Description
This is a simple text analyzer program, which <br>
a. loads a string given by the user <br>
b. counts the number of times the last character is repeated in this text and displays along with information about the percentage of these letters in given string <br>
c. calculates and prints out information about number of words in given text as well as displaying information on how many words of a certain length (1, 2, 3, ...) are present in the text with a percentage value <br>
d. counts and displays information about the number of palindromes and provide occurrence statistics for all palindromes <br>
e. counts diacritical marks and displays with percentage <br>

It was realized for Java on the Internet and Mobile Devices course on Silesian Univeristy of Technology.

#### 1 - Base 
Console application implementing functionality as described above.
#### 2 - Tests 
The second app is an improved version of previous one (e.g. collections and streams were added). In addition parameterized tests were written. 
#### 3 - JavaFX 
Application was improved and refactored by using JavaFX. After RUN button was clicked, results of text analyzis are being printed out in adequate tabs mostly in TableView. 
Furthermore if there is no palindromes nor diacritics those tabs are being disabled and proper message is added as tooltip. 
As an improvment lamba expressions and interface were added.
#### 4 - HTML 
The 4th one is an WebApp. Changes include adding servlets (with doPost and doGet methods), filter and cookies. 
Results are being displayed only if there was no timeout of a session and it is still runnig. 
#### 5 - JDBC
Database support based on Apache Derby was included with the previous application.
Entities (i.e. Diacritics, Palindromes, Words ...) and CRUD for each of them have been added.
The application connects to the database via JDBC.
