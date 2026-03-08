package com.smartagrocare.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.servlet.http.Part;
import org.json.JSONObject;

@WebServlet("/UploadImageServlet")
@MultipartConfig
public class UploadImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("leafImage");

        String originalFileName = filePart.getSubmittedFileName();
        String fileName = System.currentTimeMillis() + "_" + originalFileName;

        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String filePath = uploadPath + File.separator + fileName;

        filePart.write(filePath);

        // Send image to AI server
        URL url = new URL("http://127.0.0.1:5000/predict");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";

        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        OutputStream outputStream = conn.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);

        File uploadFile = new File(filePath);

        writer.append("--" + boundary).append("\r\n");
        writer.append("Content-Disposition: form-data; name=\"image\"; filename=\"" + uploadFile.getName() + "\"")
                .append("\r\n");
        writer.append("Content-Type: image/jpeg").append("\r\n");
        writer.append("\r\n").flush();

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.flush();
        inputStream.close();

        writer.append("\r\n").flush();
        writer.append("--" + boundary + "--").append("\r\n");
        writer.close();

        int responseCode = conn.getResponseCode();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder responseStr = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            responseStr.append(line);
        }

        reader.close();
        System.out.println("AI Response: " + responseStr.toString());

        JSONObject json = new JSONObject(responseStr.toString());

        String disease = json.getString("disease");
        double confidence = json.getDouble("confidence");

        String description = json.getString("description");
        String treatment = json.getString("treatment");

        String fertilizerType = json.getString("fertilizer_type");
        String fertilizerName = json.getString("fertilizer_name");
        String fertilizerApplication = json.getString("fertilizer_application");

        request.setAttribute("imageName", fileName);
        request.setAttribute("disease", disease);
        request.setAttribute("confidence", confidence);

        request.setAttribute("description", description);
        request.setAttribute("treatment", treatment);

        request.setAttribute("fertilizerType", fertilizerType);
        request.setAttribute("fertilizerName", fertilizerName);
        request.setAttribute("fertilizerApplication", fertilizerApplication);

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}