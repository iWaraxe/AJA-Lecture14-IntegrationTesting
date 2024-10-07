package com.coherentsolutions.advanced.java.section05;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import java.io.IOException;

/**
 * Ex03ElasticsearchContainerExample demonstrates how to use an Elasticsearch container for integration testing.
 */
public class Ex03ElasticsearchContainerExample {

    public static void main(String[] args) {
        // Create an Elasticsearch container
        ElasticsearchContainer elasticsearchContainer = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:7.17.21");

        // Start the container
        elasticsearchContainer.start();

        // Create a RestHighLevelClient using RestClient.builder() (RestClientBuilder)
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create(elasticsearchContainer.getHttpHostAddress())));

        try {
            // Index a document
            IndexRequest indexRequest = new IndexRequest("books").id("1")
                    .source("title", "Elasticsearch Basics", "author", "John Doe");
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            System.out.println("Document indexed with ID: " + indexResponse.getId());

            // Search for the document
            SearchRequest searchRequest = new SearchRequest("books");
            searchRequest.source().query(QueryBuilders.matchQuery("title", "Elasticsearch"));
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            // Print the search results
            for (SearchHit hit : searchResponse.getHits()) {
                System.out.println("Found document: " + hit.getSourceAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the client and stop the container
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            elasticsearchContainer.stop();
        }
    }
}
