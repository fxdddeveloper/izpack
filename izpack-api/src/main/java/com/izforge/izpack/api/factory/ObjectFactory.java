package com.izforge.izpack.api.factory;


import com.izforge.izpack.api.exception.IzPackClassNotFoundException;

/**
 * An object factory that supports dependency injection.
 *
 * @author Tim Anderson
 */
public interface ObjectFactory
{

    /**
     * Creates a new instance of the specified type.
     * <p/>
     * Constructor arguments may be specified as parameters, or injected by the factory.
     * When specified as parameters, order is unimportant, but must be unambiguous.
     *
     * @param type       the object type
     * @param parameters additional constructor parameters
     * @return a new instance
     */
    <T> T create(Class<T> type, Object... parameters);

    /**
     * Creates a new instance of the specified class name.
     * <p/>
     * Constructor arguments may be specified as parameters, or injected by the factory.
     * When specified as parameters, order is unimportant, but must be unambiguous.
     *
     * @param className  the class name
     * @param superType  the super type
     * @param parameters additional constructor parameters
     * @return a new instance
     * @throws ClassCastException           if <tt>className</tt> does not implement or extend <tt>superType</tt>
     * @throws IzPackClassNotFoundException if the class cannot be found
     */
    <T> T create(String className, Class<T> superType, Object... parameters);

}