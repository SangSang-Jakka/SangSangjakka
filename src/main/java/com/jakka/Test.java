package com.jakka;

import java.io.File;

public class Test {
	
	private static final String BASE_DIRECTORY = "src/main/webapp/generated/";
	
	public static void main(String[] args) {
	
		createUserFolder("test");
		
	}
	
	public static void createUserFolder(String userId) {
        String projectDirectory = System.getProperty("user.dir");
        String folderPath = projectDirectory + File.separator + BASE_DIRECTORY + userId;
        File userFolder = new File(folderPath);

        if (!userFolder.exists()) {
            boolean created = userFolder.mkdirs();
            if (created) {
                System.out.println("User folder created: " + folderPath);
            } else {
                System.err.println("Failed to create user folder: " + folderPath);
            }
        } else {
            System.out.println("User folder already exists: " + folderPath);
        }
    }
}

