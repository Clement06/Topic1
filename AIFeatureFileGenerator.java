
package day19;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AIFeatureFileGenerator {

    public static void main(String[] args) {
        // Example user stories data
        List<String> userStories = List.of(
                "As a user, I want to log in to the system. Successful Login: Given I am on the login page, When I enter valid credentials and click the login button, Then I should be logged in and directed to the home page.",
                "As a user, I want to log in to the system. Invalid Login: Given I am on the login page, When I enter invalid credentials and click the login button, Then I should see an error message."
                // Add more user stories as needed
        );

        // Generate feature files
        for (String userStory : userStories) {
            String featureFileContent = generateFeatureFile(userStory);

            // Print or save the generated feature file content
            System.out.println(featureFileContent);
            // Alternatively, save the content to a file
            // saveToFile("generated_feature.feature", featureFileContent);
        }
    }

    private static String generateFeatureFile(String userStory) {
        Document doc = new Document(userStory);
        Sentence sentence = doc.sentences().get(0);

        StringBuilder featureFileContent = new StringBuilder();
        featureFileContent.append(String.format("Feature: %s%n" , sentence.text()));

        for (int i = 1; i < sentence.length(); i++) {
            featureFileContent.append(generateScenario(sentence.word(i)));
        }

        return featureFileContent.toString();
    }

    private static String generateScenario(String sentencePart) {
        return String.format("  Scenario: %s%n    Given no preconditions%n    When %s%n    Then no expected results%n", sentencePart);
    }

    private static void saveToFile(String fileName, String content) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}