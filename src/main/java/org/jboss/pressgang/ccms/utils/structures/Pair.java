package org.jboss.pressgang.ccms.utils.structures;

import org.jboss.pressgang.ccms.utils.common.CollectionUtilities;


/**
 * A simple class to hold a pair of objects.
 */
public class Pair<X, Y>
{
	private X first;
	private Y second;

	public static <T, U> Pair<T, U> newPair(final T first, final U second)
	{
		return new Pair<T, U>(first, second);
	}
	
	public Pair(final X a1, final Y a2)
	{
		first = a1;
		second = a2;
	}

	public X getFirst()
	{
		return first;
	}

	public Y getSecond()
	{
		return second;
	}

	public void setFirst(final X arg)
	{
		first = arg;
	}

	public void setSecond(final Y arg)
	{
		second = arg;
	}

	@Override
	public boolean equals(final Object other)
	{
		if (other == null)
			return false;
		
		if (!(other instanceof Pair<?, ?>))
			return false;

		final Pair<?, ?> otherPair = (Pair<?, ?>) other;

		if (!CollectionUtilities.isEqual(otherPair.getFirst(), this.getFirst()))
			return false;
		if (!CollectionUtilities.isEqual(otherPair.getSecond(), this.getSecond()))
			return false;

		return true;
	}
	
	@Override
	public int hashCode()
	{
		int hash =  1;
		hash = 31 * hash + (this.first == null ? 0 : this.first.hashCode() * 31);
		hash = 31 * hash + (this.second == null ? 0 : this.second.hashCode()); 
		return hash;
	}
}