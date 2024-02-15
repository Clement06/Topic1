
package day19;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FeatureFileGenerator {

    public static void main(String[] args) {
        // Example user stories data
        List<UserStory> userStories = List.of(
                new UserStory("Login", "As a user, I want to log in to the system.", List.of(
                        new Scenario("Successful Login", "I am on the login page", "I enter valid credentials and click the login button", "I should be logged in and directed to the home page"),
                        new Scenario("Invalid Login", "I am on the login page", "I enter invalid credentials and click the login button", "I should see an error message")
                ))
                // Add more user stories as needed
        );

        // Generate feature files
        for (UserStory userStory : userStories) {
            String featureFileContent = generateFeatureFile(userStory);

            // Print or save the generated feature file content
            System.out.println(featureFileContent);
            // Alternatively, save the content to a file
            // saveToFile(userStory.getFeatureName() + "_feature.feature", featureFileContent);
        }
    }

    private static String generateFeatureFile(UserStory userStory) {
        StringBuilder featureFileContent = new StringBuilder();
        featureFileContent.append(String.format("Feature: %s%n  %s%n", userStory.getFeatureName(), userStory.getFeatureDescription()));

        for (Scenario scenario : userStory.getScenarios()) {
            featureFileContent.append(generateScenario(scenario));
        }

        return featureFileContent.toString();
    }

    private static String generateScenario(Scenario scenario) {
        return String.format("  Scenario: %s%n    Given %s%n    When %s%n    Then %s%n",
                scenario.getName(), scenario.getPreconditions(), scenario.getActions(), scenario.getExpectedResults());
    }

    private static void saveToFile(String fileName, String content) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class UserStory {
    private final String featureName;
    private final String featureDescription;
    private final List<Scenario> scenarios;

    public UserStory(String featureName, String featureDescription, List<Scenario> scenarios) {
        this.featureName = featureName;
        this.featureDescription = featureDescription;
        this.scenarios = scenarios;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }
}

class Scenario {
    private final String name;
    private final String preconditions;
    private final String actions;
    private final String expectedResults;

    public Scenario(String name, String preconditions, String actions, String expectedResults) {
        this.name = name;
        this.preconditions = preconditions;
        this.actions = actions;
        this.expectedResults = expectedResults;
    }

    public String getName() {
        return name;
    }

    public String getPreconditions() {
        return preconditions;
    }

    public String getActions() {
        return actions;
    }

    public String getExpectedResults() {
        return expectedResults;
    }
}
