package io.swagslash.gametrackerserver.igdbconsumer;

import org.junit.Assert;
import org.junit.Test;

public class IGDBQueryTest {

    @Test
    public void queryFieldTest() {
        IGDBQuery query = new IGDBQuery();
        query.addField("field1");
        query.addField("field2");

        Assert.assertEquals(2, query.getFields().size());
        Assert.assertEquals("fields field1,field2;", query.toString());
    }

    @Test
    public void querySearchTest() {
        IGDBQuery query = new IGDBQuery();
        query.setSearch("searchstring");

        Assert.assertEquals("fields *;search \"searchstring\";", query.toString());
    }

    @Test
    public void queryWhereTest() {
        IGDBQuery query = new IGDBQuery();
        query.setWhere("id=1");

        Assert.assertEquals("id=1", query.getWhere());
        Assert.assertEquals("fields *;where id=1;", query.toString());
    }

    @Test
    public void querySortTest() {
        IGDBQuery query = new IGDBQuery();
        query.setSort("sortfield", true);

        Assert.assertEquals("sortfield", query.getSort());
        Assert.assertEquals("fields *;sort sortfield desc;", query.toString());

        query.setSort("sortfield2", false);

        Assert.assertEquals("sortfield2", query.getSort());
        Assert.assertEquals("fields *;sort sortfield2 asc;", query.toString());
    }

    @Test
    public void queryFieldSearchCombination() {
        IGDBQuery query = new IGDBQuery();
        query.addField("field1");
        query.setSearch("searchstring");

        Assert.assertEquals(1, query.getFields().size());
        Assert.assertEquals("searchstring", query.getSearch());
        Assert.assertEquals("fields field1;search \"searchstring\";", query.toString());
    }

}