package com.iprogrammerr.algorithms_data_structures;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.iprogrammerr.algorithms_data_structures.data.Columns;
import com.iprogrammerr.algorithms_data_structures.data.TypedMap;

public final class ColumnsTest {

    @Test
    public void canAddAndRetrieve() throws Exception {
	long id = 1;
	String name = "name";
	byte[] image = new byte[] { 3, 4, 5, -10, 55, 102 };
	double rate = 4.5;
	float scale = 9.5f;
	TypedMap columns = new Columns().put("id", id).put("name", name).put("image", image).put("rate", rate)
		.put("scale", scale);
	assertTrue(columns.longValue("id") == id);
	assertTrue(columns.stringValue("name").equals(name));
	assertTrue(Arrays.equals(columns.binaryValue("image"), image));
	assertTrue(rate == columns.doubleValue("rate"));
	assertTrue(scale == columns.floatValue("scale"));
    }

    @Test
    public void canOverrideValues() throws Exception {
	long id = 1;
	long secondId = 2;
	TypedMap columns = new Columns().put("id", id).put("id", secondId);
	assertFalse(columns.longValue("id") == id);
	assertTrue(columns.longValue("id") == secondId);
    }

}
