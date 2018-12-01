package com.iprogrammerr.algorithms_data_structures;

import com.iprogrammerr.algorithms_data_structures.search.BoyerMooreBytesSearch;
import com.iprogrammerr.algorithms_data_structures.search.BytesSearch;

public class App {

	public static void main(String[] args) throws Exception {
		BytesSearch search = new BoyerMooreBytesSearch("Olek poszedł".getBytes());
		System.out.println(search.index("posz".getBytes()));
	}
}
