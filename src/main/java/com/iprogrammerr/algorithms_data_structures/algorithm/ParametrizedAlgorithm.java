package com.iprogrammerr.algorithms_data_structures.algorithm;

public interface ParametrizedAlgorithm<T, R> {
	T solution(R param) throws Exception;
}
