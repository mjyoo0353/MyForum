# MyForum: Basic Community Bulletin Board
Developed to gain familiarity with Spring Boot and JPA

</br>

## 🗂️ Table of Contents
-  [Project Introduction](#0)
-  [Technologies, Libraries, and tools Used](#1)
-  [Key Features](#2)
-  [Database and API Design](#3)
-  [Screenshots](#4)
</br>

----
<h2 id="0">
    <b>📌 Project Introduction</b>
</h2>

- Duration: 2024.10.7 - 2024.10.22
- Personal Side Project

</br>

----
<h2 id="1">
    <b>📌 Technologies, Libraries, and tools Used</b>
</h2>

- `Java 21`, `Spring Boot 3.3.4`, `Gradle`
- `Spring Data JPA`, `MySQL`
- `HTML`, `CSS`, `JavaScript`, `Thymeleaf`, `BootStrap`
- `IntelliJ`, `SourceTree`, `Gihub`
</br>

----
<h2 id="2">
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

- ### Main Page
<img width="850" alt="image" src="https://github.com/user-attachments/assets/08156995-dab8-4ffb-a606-58ee1c601de6">
<img width="860" alt="image" src="https://github.com/user-attachments/assets/c0f3ce91-d9be-4832-86ce-8219690b0839">

<details>
	<summary>Redirect unauthenticated users to the Login Page</summary>
  	<div markdown="1">
      <img width="770" alt="image" src="https://github.com/user-attachments/assets/25d068c7-cc18-4d55-8f7d-ebd108bc4fca">
  	</div>
</details>


<details>
	<summary>Signup Page - Validation check</summary>
  	<div markdown="1">
        <img width="770" alt="image" src="https://github.com/user-attachments/assets/2c073c27-0838-4cb6-be9e-f50876825f23">
  	</div>
</details>

<details>
	<summary>Searching</summary>
  	<div markdown="1">
        <img width="851" alt="image" src="https://github.com/user-attachments/assets/5333a51a-1fda-44aa-8998-03b78b31ad88">
  	</div>
</details>

<details>
	<summary>Post Detail Page</summary>
  	<div markdown="1">
        <img width="850" alt="image" src="https://github.com/user-attachments/assets/e0e698fb-be4d-4135-a03d-11220ec3b4dd">
  	</div>
</details>

<details>
	<summary>Profile Page</summary>
  	<div markdown="1">
        <img width="828" alt="image" src="https://github.com/user-attachments/assets/99063467-40b7-4cd2-b11a-b5d0243a7c4a">
        <img width="812" alt="image" src="https://github.com/user-attachments/assets/8e27c310-2160-4a1f-a903-76c600131d4e">
        <img width="821" alt="image" src="https://github.com/user-attachments/assets/9da6f67d-8160-42b0-8ce6-d378d504ed64">  	</div>
</details>

