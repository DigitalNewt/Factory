package com.ravetree.model.dao;


import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

public class RavetreeDB {


    public MongoCollection<Document> connectToMongo(String collectionName) {

        RavetreeCredentials credentials = RavetreeCredentials.getInstance();
        MongoCredential credential = MongoCredential.createMongoCRCredential(credentials.getMdbUser(), credentials.getMdbName(), credentials.getMdbPass().toCharArray());
        MongoClient mongo = new MongoClient(new ServerAddress(credentials.getMdbAddress(), credentials.getMdbPort()), Arrays.asList(credential));
        MongoDatabase db = mongo.getDatabase(credentials.getMdbName());
        return db.getCollection(collectionName);
    }
}