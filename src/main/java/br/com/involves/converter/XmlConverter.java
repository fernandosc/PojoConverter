/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.involves.converter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class XmlConverter extends Converter{

    private static final Logger logger = Logger.getLogger("br.com.involves.converter.XmlConverter");
    
    @Override
    public void process(List<?> pojo, File outputFile) throws IllegalArgumentException, IllegalAccessException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected StringBuilder parser(Field[] fields, Object pojo) throws IllegalArgumentException, IllegalAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void arrayParser(StringBuilder result, Field field, Object pojo) throws IllegalArgumentException, IllegalAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void listParser(StringBuilder result, Field field, Object pojo) throws IllegalArgumentException, IllegalAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
