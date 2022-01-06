package com.openclassrooms.safetynets.alerts.model;

public class Child {

	/**
	 * The first name of the child.
	 */
	private String firstName;

	/**
	 * The first name of the child.
	 */
	private String lastName;

	/**
	 * Age of the child.
	 */
	private int age;

	/**
	 * Constructor of class Child. Initialize child and homeMembers.
	 *
	 * @param firstName The child's first name
	 * @param lastName  The child's last name
	 * @param age       The child's age
	 */
	public Child(final String firstName, final String lastName, final int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	/**
	 * Getter of Child.firstName.
	 *
	 * @return The first name of the child.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter of Child.firstName.
	 *
	 * @param firstName The firstName of the child to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter of Child.lastName.
	 *
	 * @return The last name of the child.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter of Child.lastName.
	 *
	 * @param lastName The lastName of the child to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter of Child.age.
	 *
	 * @return The age of the child.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Setter of Child.age.
	 *
	 * @param age The age of the child to set
	 */
	public void setAge(final int age) {
		this.age = age;
	}
}
