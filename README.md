# MyForum: Basic Community Bulletin Board

</br>

## 🗂️ Table of Contents
-  [Project Introduction](#0)
-  [Key Features](#1)
-  [Database and API Design](#2)
-  [Technologies, Libraries, and tools Used](#3)
-  [Screenshots](#4)
</br>

----
<h2 id="0">
    <b>📌 Project Introduction</b>
</h2>

- Developed to gain familiarity with Spring Boot and JPA

</br>

----
<h2 id="1">
    <b>📌 Key Features</b>
</h2>

- ### User
    - Only registered users can access detailed posts on the bulletin board
    - Login and logout functionality is implemented using an interceptor for session management
    - Users can view their own posts and comments in their profiles
    - Input validation and duplicate checks ensure data integrity and improve user experience
 
- ### Post
  - Displays posts with pagination, allowing users to navigate one page at a time or jump directly to the first or last page
  - Users can search for posts by keywords

- ### Comment
  - Users can leave comments on posts
  - Comments are automatically deleted when the associated post is removed
 
- ### Like
  - Users can like posts
  - Prevent users from liking the same post multiple times
    
</br>

----
<h2 id="2">
    <b>📌 Technologies, Libraries, and tools Used</b>
</h2>

- `Java 21`, `Spring Boot 3.3.4`
- `JPA`, `MySQL`
- `HTML`, `CSS`, `JavaScript`, `Thymeleaf`, `BootStrap`
- `IntelliJ`, `MySQL Workbench`, `SourceTree`, `Gihub`
</br>

----
<h2 id="3">
    <b>📌 Database and API Design</b>
</h2>

- ### ER Diagram
<img width="800" alt="image" src="https://github.com/user-attachments/assets/b71a9522-f9cb-4410-a256-e19c8e947f3f"/>

<br/>

- ### API Design
<img width="800" alt="image" src="https://github.com/user-attachments/assets/73d46637-344f-46db-977b-0727ae3baf48">
<img width="800" alt="image" src="https://github.com/user-attachments/assets/826d7839-2d20-4fff-b45a-5e9436042948">
<img width="800" alt="image" src="https://github.com/user-attachments/assets/b67206b6-aed1-453f-8131-43c621be0d69">


 
----
<h2 id="4">
    <b>📌 Screenshots</b>
</h2>
