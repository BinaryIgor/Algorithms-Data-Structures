package com.iprogrammerr.algorithms_data_structures;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.iprogrammerr.algorithms_data_structures.initialization.NextPrimeNumber;

public final class NextPrimeNumberTest {

	@Test
	public void canReturnProperNextValue() {
		NextPrimeNumber prime = new NextPrimeNumber(1);
		assertThat(prime.value(), Matchers.equalTo(2));
		assertThat(prime.value(), Matchers.equalTo(3));
		prime = new NextPrimeNumber(42);
		assertThat(prime.value(), Matchers.equalTo(43));
	}
}
