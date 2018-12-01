package com.iprogrammerr.algorithms_data_structures;

import com.iprogrammerr.algorithms_data_structures.search.BoyerMooreSearch;
import com.iprogrammerr.algorithms_data_structures.search.StringSearch;

public class App {

	public static void main(String[] args) throws Exception {
		StringSearch search = new BoyerMooreSearch("Olek poszedł");
		System.out.println(search.index("du"));
	}
}
