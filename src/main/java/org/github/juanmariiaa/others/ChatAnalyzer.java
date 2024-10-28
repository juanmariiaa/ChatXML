package org.github.juanmariiaa.others;

import org.github.juanmariiaa.model.domain.Message;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The ChatAnalyzer class provides functionality to analyze a chat represented by a list of messages.
 * It generates a summary report containing various statistics about the chat and writes the report to a specified file.
 */
public class ChatAnalyzer {

    /**
     * Generates a chat summary report based on the provided list of messages and writes it to the specified file path.
     *
     * @param messages The list of messages to analyze.
     * @param filePath The path of the file where the summary report will be written.
     */
    public void chatAnalyzer(List<Message> messages, String filePath) {
        if (messages.isEmpty()) {
            System.out.println("No messages to analyze.");
            return;
        }

        long totalMessages = messages.size();
        long totalWords = messages.stream()
                .mapToInt(msg -> msg.getContent().split("\\s+").length)
                .sum();

        // Count messages by user
        Map<String, Long> messagesPerUser = messages.stream()
                .collect(Collectors.groupingBy(Message::getSender, Collectors.counting()));

        // Calculate word frequency
        Map<String, Long> wordFrequency = messages.stream()
                .flatMap(msg -> Arrays.stream(msg.getContent().toLowerCase().split("\\s+")))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        // Find the most common word
        Optional<Map.Entry<String, Long>> mostCommonWord = wordFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        // Calculate average message length
        double averageMessageLength = messages.stream()
                .mapToInt(msg -> msg.getContent().length())
                .average()
                .orElse(0);

        // Write the summary to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Chat Analysis Report:\n");
            writer.write("Total Messages: " + totalMessages + "\n");
            writer.write("Total Words: " + totalWords + "\n");
            writer.write("Average Message Length: " + averageMessageLength + " characters\n");

            messagesPerUser.forEach((user, count) -> {
                try {
                    writer.write("Messages by " + user + ": " + count + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            mostCommonWord.ifPresent(entry -> {
                try {
                    writer.write("Most Common Word: '" + entry.getKey() + "' (" + entry.getValue() + " times)\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("Chat summary generated at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
