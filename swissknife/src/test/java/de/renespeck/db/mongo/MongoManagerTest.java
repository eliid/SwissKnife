package de.renespeck.db.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.bson.BasicBSONObject;
import org.bson.BsonArray;
import org.bson.BsonObjectId;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import de.renespeck.swissknife.db.mongo.MongoManager;

public class MongoManagerTest extends TestCase {

    String collection = "test213456Collection".concat(MongoManagerTest.class.getName());
    String dbname     = "test213456";

    public static Test suite() {
        return new TestSuite(MongoManagerTest.class);
    }

    public void testMongoManager() {

        MongoManager mm = MongoManager
                .getMongoManager("localhost", 27017)
                .setConfig(dbname, collection);

        String id = mm.insert("123");
        assertEquals(id, "");

        id = mm.insert(new JSONObject().put("test", "123").toString());

        BasicBSONObject dbo = new BasicBSONObject();
        dbo.put(MongoManager.idKey, id);
        id = mm.insert(dbo.toString());

        assertFalse(id.equals(""));

        List<String> list = new ArrayList<>();
        mm.findOperation("_id", "$in", new BsonArray(Arrays.asList(new BsonObjectId(new ObjectId(id)))))
                .forEachRemaining(json -> {
                    list.add(json.toString());
                });

        assertTrue(list.size() == 1);

        mm.deleteCollection();
        mm.deleteDB();
    }
}
