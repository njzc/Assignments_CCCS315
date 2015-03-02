public abstract class DataElement {
	
	// Method to determine whether two objects contain the same data.
	// Postcondition: Returns true if this object contains the
	// same data as the object otherElement;
	// otherwise, it returns false.
	public abstract boolean equals(DataElement otherElement);
	
	// Method to compare two objects.
	// Postcondition: Returns a value < 0 if this object is
	// less than the object otherElement;
	// Returns 0 if this object is the same as
	// the object otherElement.
	// Returns a value > 0 if this object is
	// greater than the object otherElement.
	public abstract int compareTo(DataElement otherElement);

	// Method to copy otherElement into this object.
	// Postcondition: The data of otherElement is copied into
	// this object.
	public abstract void makeCopy(DataElement otherElement);

	// Method to return a copy of this object.
	// Postcondition: A copy of this object is created and
	// a reference of the copy is returned.
	public abstract DataElement getCopy();
}