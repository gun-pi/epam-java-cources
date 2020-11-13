package com.epam.university.java.core.task062;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Objects;

public class PersonExternalizableImpl implements PersonExternalizable {
    private String fullName;
    private int age;
    private boolean male;
    private PersonExternalizable spouse;
    private Collection<PersonExternalizable> children;

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setMale(boolean male) {
        this.male = male;
    }

    @Override
    public void setSpouse(PersonExternalizable spouse) {
        this.spouse = spouse;
    }

    @Override
    public void setChildren(Collection<PersonExternalizable> children) {
        this.children = children;
    }

    /**
     * The object implements the writeExternal method to save its contents
     * by calling the methods of DataOutput for its primitive values or
     * calling the writeObject method of ObjectOutput for objects, strings,
     * and arrays.
     *
     * @param out the stream to write the object to
     * @throws IOException Includes any I/O exceptions that may occur
     * @serialData Overriding methods should use this tag to describe the data
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.write(this.age);
        out.writeObject(this.children);
        out.writeObject(this.fullName);
        out.writeBoolean(male);
        out.writeObject(spouse);
    }

    /**
     * The object implements the readExternal method to restore its
     * contents by calling the methods of DataInput for primitive
     * types and readObject for objects, strings and arrays.  The
     * readExternal method must read the values in the same sequence
     * and with the same types as were written by writeExternal.
     *
     * @param in the stream to read data from in order to restore the object
     * @throws IOException            if I/O errors occur
     * @throws ClassNotFoundException If the class for an object being
     *                                restored cannot be found.
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        age = in.read();
        children = (Collection<PersonExternalizable>)in.readObject();
        fullName = (String)in.readObject();
        male = in.readBoolean();
        spouse = (PersonExternalizable)in.readObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonExternalizableImpl)) {
            return false;
        }
        PersonExternalizableImpl that = (PersonExternalizableImpl) o;
        return age == that.age && male == that.male
                && Objects.equals(fullName, that.fullName)
                && Objects.equals(spouse, that.spouse)
                && Objects.equals(children, that.children);
    }
}
