<%
String imageName = (String) request.getAttribute("imageName");
String disease = (String) request.getAttribute("disease");
Double confidence = (Double) request.getAttribute("confidence");

String description = (String) request.getAttribute("description");
String treatment = (String) request.getAttribute("treatment");

String fertilizerType = (String) request.getAttribute("fertilizerType");
String fertilizerName = (String) request.getAttribute("fertilizerName");
String fertilizerApplication = (String) request.getAttribute("fertilizerApplication");

if(imageName == null) imageName = "No image";
if(disease == null) disease = "No disease received";
if(confidence == null) confidence = 0.0;

if(description == null) description = "Information not available.";
if(treatment == null) treatment = "Treatment information not available.";

if(fertilizerType == null) fertilizerType = "N/A";
if(fertilizerName == null) fertilizerName = "N/A";
if(fertilizerApplication == null) fertilizerApplication = "No fertilizer recommendation available.";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Disease Detection Result</title>

<style>
body{
    font-family: Arial;
    margin:40px;
}

.result-box{
    border:1px solid #ccc;
    padding:20px;
    margin-top:20px;
    border-radius:8px;
}

h3{
    margin-top:25px;
}

img{
    border-radius:8px;
}
</style>

</head>

<body>

<h2>Disease Detection Result</h2>

<p><b>Uploaded Leaf Image:</b></p>

<img src="uploads/<%= imageName %>" width="300">

<div class="result-box">

<h3>Disease Detected:</h3>
<p style="color:red;font-size:20px;"><%= disease %></p>

<h3>Confidence:</h3>
<p style="font-size:20px;"><%= confidence %>%</p>

<h3>Description:</h3>
<p><%= description %></p>

<h3>Treatment Recommendation:</h3>
<p><%= treatment %></p>

<h3>Fertilizer Recommendation:</h3>

<p><b>Type:</b> <%= fertilizerType %></p>
<p><b>Name:</b> <%= fertilizerName %></p>
<p><b>How to Apply:</b> <%= fertilizerApplication %></p>

</div>

<br><br>

<a href="dashboard.jsp">Back to Dashboard</a>

</body>
</html>