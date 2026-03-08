# Smart AgroCare 🌱

AI Crop Disease Detection and Farm Management System.

## Tech Stack
- Java (JSP + Servlet)
- Apache Tomcat 9
- MySQL
- Python Flask AI Server
- TensorFlow CNN

## System Architecture
User uploads leaf image from dashboard.jsp

JSP → Servlet → Flask AI Server → TensorFlow Model → JSON Response → Result Page

## Features
- User Registration & Login
- Leaf Image Upload
- AI Disease Detection
- Confidence Score
- Disease Description
- Treatment Recommendation
- Fertilizer Suggestion
- Invalid Image Detection

## AI Server
The AI server runs separately using Python Flask.

Repository:
https://github.com/VanguardVedant/Smart-AgroCare--AI

## How to Run
1 Start Flask AI server
2 Start Apache Tomcat
3 Open dashboard.jsp
4 Upload leaf image
