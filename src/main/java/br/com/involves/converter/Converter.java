/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.involves.converter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public abstract class Converter {
    
    private static final Logger logger = Logger.getLogger("br.com.involves.converter.Converter");
    
    /**
     * 
     * @param pojo
     * @param outputFile
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws IOException 
     */
    public abstract void process(List<?> pojo, File outputFile) throws IllegalArgumentException, IllegalAccessException, IOException;
    
    /**
     * 
     * @param fields
     * @param pojo
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    protected abstract StringBuilder parser(Field[] fields, Object pojo) throws IllegalArgumentException, IllegalAccessException;
    
    /**
     * 
     * @param result
     * @param field
     * @param pojo
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    protected abstract void arrayParser(StringBuilder result, Field field, Object pojo) throws IllegalArgumentException, IllegalAccessException;
    
    /**
     * 
     * @param result
     * @param field
     * @param pojo
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    protected abstract void listParser(StringBuilder result, Field field, Object pojo) throws IllegalArgumentException, IllegalAccessException;
    
    /**
     * 
     * @param object
     * @return 
     */
    public Field[] getFields(Object object){
        logger.log(Level.INFO, "Getting declared fields from object - {0}", object);
        return object.getClass().getDeclaredFields();
    }
    /**
     * 
     * @param objectName
     * @return 
     */
    public boolean belongsJavaLang(String objectName) {
        logger.log(Level.INFO, "Checking if object name belongs to java lang - {0}", objectName);
        return objectName.startsWith("java.lang");
    }
    /**
     * 
     * @param stringBuilder 
     */
    public void removeLastCharFromStringBuilder(StringBuilder stringBuilder){
        logger.log(Level.INFO, "Remove the last char from string");
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());        
    }
    /**
     * 
     * @param listPojo
     * @param file
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws IOException 
     */
    public void convert(List<?> listPojo, File file) throws IllegalArgumentException, IllegalAccessException, IOException{        
        logger.log(Level.INFO, "Converting the pojo");
        process(listPojo, file);
    }
    /**
     * 
     * @param pojo
     * @param file
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws IOException 
     */
    public void convert(Object pojo, File file) throws IllegalArgumentException, IllegalAccessException, IOException{
        List<Object> listPojo = new ArrayList<>();
        listPojo.add(pojo);
        convert(listPojo, file);
    }
    
}
