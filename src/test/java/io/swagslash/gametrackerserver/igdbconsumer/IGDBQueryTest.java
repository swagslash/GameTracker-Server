package io.swagslash.gametrackerserver.igdbconsumer;

import org.junit.Assert;
import org.junit.Test;

public class IGDBQueryTest {

    @Test
    public void queryFieldTest() {
        IGDBQuery query = new IGDBQuery();
        query.addField("field1");
        query.addField("field2");

        Assert.assertEquals("fields field1,field2;", query.toString());
    }

    @Test
    public void querySearchTest() {
        IGDBQuery query = new IGDBQuery();
        query.setSearch("searchstring");

        Assert.assertEquals("fields *;search \"searchstring\";", query.toString());
    }

    @Test
    public void queryFieldSearchCombination() {
        IGDBQuery query = new IGDBQuery();
        query.addField("field1");
        query.setSearch("searchstring");

        Assert.assertEquals("fields field1;search \"searchstring\";", query.toString());
    }

}