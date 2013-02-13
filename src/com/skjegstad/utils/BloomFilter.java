package com.skjegstad.utils;

import java.util.BitSet;

public class BloomFilter<E> extends AbstractBloomFilter<E> {

    /**
     * Constructs an empty Bloom filter. The total length of the Bloom filter will be
     * c*n.
     *
     * @param bitsPerElement is the number of bits used per element.
     * @param expectedSize is the expected number of elements the filter will contain.
     * @param hashFunctionCount is the number of hash functions used.
     */
   public BloomFilter(double bitsPerElement, int expectedSize, int hashFunctionCount) {
	   super(expectedSize, hashFunctionCount, bitsPerElement, (int)Math.ceil(bitsPerElement * expectedSize));
     this.bloomData = new StandardBloomData(bitSetSize);
   }
	
    /**
     * Constructs an empty Bloom filter. The optimal number of hash functions (k) is estimated from the total size of the Bloom
     * and the number of expected elements.
     *
     * @param bitSetSize defines how many bits should be used in total for the filter.
     * @param expectedNumberOElements defines the maximum number of elements the filter is expected to contain.
     */
    public BloomFilter(int bitSetSize, int expectedNumberOElements) {
        this(bitSetSize / (double)expectedNumberOElements,
             expectedNumberOElements,
             (int) Math.round((bitSetSize / (double)expectedNumberOElements) * Math.log(2.0)));
    }
	
    /**
     * Constructs an empty Bloom filter with a given false positive probability. The number of bits per
     * element and the number of hash functions is estimated
     * to match the false positive probability.
     *
     * @param falsePositiveProbability is the desired false positive probability.
     * @param expectedNumberOfElements is the expected number of elements in the Bloom filter.
     */
    public BloomFilter(double falsePositiveProbability, int expectedNumberOfElements) {
        this(Math.ceil(-(Math.log(falsePositiveProbability) / Math.log(2))) / Math.log(2), // c = k / ln(2)
             expectedNumberOfElements,
             (int)Math.ceil(-(Math.log(falsePositiveProbability) / Math.log(2)))); // k = ceil(-log_2(false prob.))
    }
	
    /**
     * Construct a new Bloom filter based on existing Bloom filter data.
     *
     * @param bitSetSize defines how many bits should be used for the filter.
     * @param expectedNumberOfFilterElements defines the maximum number of elements the filter is expected to contain.
     * @param actualNumberOfFilterElements specifies how many elements have been inserted into the <code>filterData</code> BitSet.
     * @param filterData a BitSet representing an existing Bloom filter.
     */
    public BloomFilter(int bitSetSize, int expectedNumberOfFilterElements, int actualNumberOfFilterElements, BitSet filterData) {
        this(bitSetSize, expectedNumberOfFilterElements);
        final StandardBloomData standardBloomData = new StandardBloomData(bitSetSize);
        this.bloomData = standardBloomData;
        standardBloomData.setBitSet(filterData);
        this.numberOfAddedElements = actualNumberOfFilterElements;
    }

	/**
	 * Compares the contents of two instances to see if they are equal.
	 *
	 * @param obj is the object to compare to.
	 * @return True if the contents of the objects are equal.
	 */
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (getClass() != obj.getClass()) {
	        return false;
	    }
	    final BloomFilter<E> other = (BloomFilter<E>) obj;        
	    if (this.expectedNumberOfFilterElements != other.expectedNumberOfFilterElements) {
	        return false;
	    }
	    if (this.hashFunctionCount != other.hashFunctionCount) {
	        return false;
	    }
	    if (this.bitSetSize != other.bitSetSize) {
	        return false;
	    }
	    if (this.bloomData != other.bloomData && (this.bloomData == null || !this.bloomData.equals(other.bloomData))) {
	        return false;
	    }
	    return true;
	}

	/**
	 * Calculates a hash code for this class.
	 * @return hash code representing the contents of an instance of this class.
	 */
	@Override
	public int hashCode() {
	    int hash = 7;
	    hash = 61 * hash + (this.bloomData != null ? this.bloomData.hashCode() : 0);
	    hash = 61 * hash + this.expectedNumberOfFilterElements;
	    hash = 61 * hash + this.bitSetSize;
	    hash = 61 * hash + this.hashFunctionCount;
	    return hash;
	}
	
}
