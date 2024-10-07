//package com.coherentsolutions.advanced.java.section09;
//
//import org.neo4j.driver.*;
//import org.testcontainers.containers.Neo4jContainer;
//
//import java.lang.Record;
//
///**
// * Ex02Neo4jTest demonstrates using the Testcontainers Neo4j module.
// */
//public class Ex02Neo4jTest {
//
//    public static void main(String[] args) {
//        // Create a Neo4j container
//        try (Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>("neo4j:4.4")) {
//
//            // Start the container
//            neo4jContainer.start();
//
//            // Create a driver to interact with Neo4j
//            String boltUrl = neo4jContainer.getBoltUrl();
//            String password = neo4jContainer.getAdminPassword();
//            try (Driver driver = GraphDatabase.driver(boltUrl, AuthTokens.basic("neo4j", password));
//                 Session session = driver.session()) {
//
//                // Create nodes and relationships
//                String cypherQuery = "CREATE (a:Person {name: 'Bob'})-[:KNOWS]->(b:Person {name: 'Carol'})";
//                session.run(cypherQuery);
//
//                // Query data
//                Result result = session.run("MATCH (n:Person) RETURN n.name AS name");
//                while (result.hasNext()) {
//                    Record record = result.next();
//                    System.out.println("Person name: " + record.get("name").asString());
//                }
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
///*
// * In this example:
// * - We use the Neo4jContainer to start a Neo4j database.
// * - We interact with the database using the Neo4j Java Driver.
// * - This demonstrates how Testcontainers supports modern databases through community modules.
// */
