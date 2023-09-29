import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NjanTool {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: njan <command> [query]");
            System.exit(1);
        }

        String command = args[0];

        if ("validate".equals(command)) {
            if (isValidNjan(System.in)) {
                System.exit(0);
            } else {
                System.exit(1);
            }
        } else if ("query".equals(command)) {
            if (args.length < 2) {
                System.err.println("Usage: njan query <QUERY_STRING>");
                System.exit(1);
            }
            String queryString = args[1];
            try {
                String result = queryNjan(System.in, queryString);
                System.out.println(result);
                System.exit(0);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        } else {
            System.err.println("Invalid command: " + command);
            System.exit(1);
        }
    }

    private static boolean isValidNjan(java.io.InputStream input) {
        Scanner scanner = new Scanner(input);
        String jsonString = scanner.nextLine().trim();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return isValidNjan(jsonNode);
        } catch (IOException e) {
            return false;
        }
    }

    private static boolean isValidNjan(JsonNode jsonNode) {
        if (jsonNode.isTextual()) {
            // Valid NJAN: JSON string
            return true;
        } else if (jsonNode.isArray()) {
            // Valid NJAN: JSON array
            for (JsonNode element : jsonNode) {
                if (!isValidNjan(element)) {
                    return false;
                }
            }
            return true;
        } else {
            // Invalid NJAN: JSON object or other types
            return false;
        }
    }

    private static String queryNjan(java.io.InputStream input, String queryString) {
        Scanner scanner = new Scanner(input);
        String jsonString = scanner.nextLine().trim();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return queryNjan(jsonNode, queryString);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid input format.");
        }
    }

    private static String queryNjan(JsonNode jsonNode, String queryString) {
        if (queryString.isEmpty()) {
            // Blank query returns the whole JSON
            return jsonNode.toString();
        }

        String[] indices = queryString.split("\\.");
        List<Integer> parsedIndices = new ArrayList<>();

        for (String index : indices) {
            try {
                int i = Integer.parseInt(index);
                parsedIndices.add(i);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid query format.");
            }
        }

        return query(jsonNode, parsedIndices, 0).toString();
    }

    private static JsonNode query(JsonNode node, List<Integer> indices, int depth) {
        if (depth == indices.size()) {
            return node;
        }

        int index = indices.get(depth);

        if (node.isArray()) {
            if (index >= 0 && index < node.size()) {
                return query(node.get(index), indices, depth + 1);
            } else {
                throw new IllegalArgumentException("Index out of bounds.");
            }
        } else {
            throw new IllegalArgumentException("Not an array element.");
        }
    }
}
