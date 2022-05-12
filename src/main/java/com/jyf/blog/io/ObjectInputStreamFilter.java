/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2019/7/29
 * Time: 11:19
 **/
package com.jyf.blog.io;

import javax.management.BadAttributeValueExpException;
import java.io.*;

public class ObjectInputStreamFilter extends ObjectInputStream {
    
    public ObjectInputStreamFilter(InputStream inputStream) throws IOException {
        super(inputStream);
    }
    
    
    @Override
    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        if(objectStreamClass.getName().equals(BadAttributeValueExpException.class.getName())){
            throw new InvalidClassException("不合法的反序列化",objectStreamClass.getName());
        }
        return super.resolveClass(objectStreamClass);
    }
}
