package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class BookParser {

    public static void main(String[] args) {
        // Sample XML
        String xmlData = "<BookShelf>" +
                "  <Book>" +
                "    <title>Object-Oriented Design Patterns</title>" +
                "    <publishedYear>2022</publishedYear>" +
                "    <numberOfPages>400</numberOfPages>" +
                "    <authors>" +
                "      <author>Gamma</author>" +
                "      <author>Helm</author>" +
                "      <author>Johnson</author>" +
                "      <author>Vlissides</author>" +
                "    </authors>" +
                "  </Book>" +
                "  <Book>" +
                "    <title>Artificial Intelligence Fundamentals</title>" +
                "    <publishedYear>2020</publishedYear>" +
                "    <numberOfPages>450</numberOfPages>" +
                "    <authors>" +
                "      <author>Russell</author>" +
                "      <author>Norvig</author>" +
                "    </authors>" +
                "  </Book>" +
                "  <Book>" +
                "    <title>Software Engineering Principles</title>" +
                "    <publishedYear>2021</publishedYear>" +
                "    <numberOfPages>350</numberOfPages>" +
                "    <authors>" +
                "      <author>Sommerville</author>" +
                "    </authors>" +
                "  </Book>" +
                "</BookShelf>";

        // Parse XML
        JSONObject jsonFromXml = XML.toJSONObject(xmlData);
        System.out.println("JSON from XML:\n" + jsonFromXml.toString(2));

        // Parse JSON
        String jsonData = "{ \"BookShelf\": { \"Book\": [" +
                " { \"title\": \"Deep Learning Essentials\", \"publishedYear\": 2019, \"numberOfPages\": 550, \"authors\": [\"Goodfellow\", \"Bengio\", \"Courville\"] }" +
                "] } }";

        JSONObject jsonObject = new JSONObject(jsonData);
        System.out.println("\nParsed JSON:\n" + jsonObject.toString(2));

        // Add an additional entry programmatically
        JSONObject newBook = createBookObject("Java Fundamentals", 2023, 300, new String[]{"Duke", "Java Guru"});

        jsonObject.getJSONObject("BookShelf").getJSONArray("Book").put(newBook);

        // Print updated JSON
        System.out.println("\nUpdated JSON:\n" + jsonObject.toString(2));

        // Convert JSON back to XML
        String updatedXmlData = XML.toString(jsonFromXml);
        System.out.println("\nXML from updated JSON:\n" + updatedXmlData);
        String formattedXmlData = updatedXmlData.replaceAll("><", ">\n<");

        // Print the formatted XML data for better readability
        System.out.println("\nFormatted XML from updated JSON:\n" + formattedXmlData);
    }

    private static JSONObject createBookObject(String title, int publishedYear, int numberOfPages, String[] authors) {
        return new JSONObject()
                .put("title", title)
                .put("publishedYear", publishedYear)
                .put("numberOfPages", numberOfPages)
                .put("authors", new JSONArray(authors));
    }
}
