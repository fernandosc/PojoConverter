/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.involves.converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class JsonConverter extends Converter{
    
    private static final Logger logger = Logger.getLogger("br.com.involves.converter.JsonConverter");
    
    /**
     * 
     * @param pojo
     * @param outputFile
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws IOException 
     */
    @Override
    public void process(List<?> pojo, File outputFile) throws IllegalArgumentException, IllegalAccessException, IOException{
        logger.log(Level.INFO, "Initializing the process to {0}", outputFile.getName());
        StringBuilder sb = new StringBuilder();
        OutputStream outputStream = new FileOutputStream(outputFile);        
        
        sb.append(pojo.size() > 1 ? "{\"list\":[" : "" );
        for(Object obj : pojo){
            sb.append(parser(getFields(pojo.get(0)), obj));
            sb.append(",");
        }
        
        removeLastCharFromStringBuilder(sb);
        sb.append(pojo.size() > 1 ? "]}" : "" );
        outputStream.write(sb.toString().getBytes());
    }
    /**
     * 
     * @param fields
     * @param pojo
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    @Override
    protected StringBuilder parser(Field[] fields, Object pojo) throws IllegalArgumentException, IllegalAccessException{
        StringBuilder result = new StringBuilder("{");
        for (Field field : fields) {
            field.setAccessible(true);
            result.append("\"").append(field.getName()).append("\":");
            
            if(field.get(pojo) != null){                
                if(field.getType().isArray()){                    
                    arrayParser(result, field, pojo);
                }else if(field.getType().isInterface() && field.getType().isAssignableFrom(List.class)){
                    listParser(result, field, pojo);
                }else if(belongsJavaLang(field.getType().getName()) || field.getType().isPrimitive()){
                    logger.log(Level.INFO, "Parsing {0} field {1}", new Object[]{field.getType().getName(), field.getType()});
                    result.append("\"").append(String.valueOf(field.get(pojo))).append("\",");
                }else{
                    logger.log(Level.INFO, "Parsing the field {0}", field.getType().getName());
                    result.append(parser(field.getType().getDeclaredFields(), field.get(pojo)));
                }
            }else{
                logger.log(Level.WARNING, "The field {0} is null", field.getName());
                result.append("\"\",");
            }
        }
        removeLastCharFromStringBuilder(result);
        result.append("}");
        return result;
    }
    
    @Override
    protected void arrayParser(StringBuilder result, Field field, Object pojo) throws IllegalArgumentException, IllegalAccessException{
        logger.log(Level.INFO, "Parsing a array field {0}", field.getName());
        result.append("[");
        int length = Array.getLength(field.get(pojo));
        for(int i = 0; i < length ; i++){
            Object element = Array.get(field.get(pojo), i);                    
            result.append("\"").append(String.valueOf(element)).append("\",");
        }
        removeLastCharFromStringBuilder(result);
        result.append("],");
    }
    
    @Override
    protected void listParser(StringBuilder result, Field field, Object pojo) throws IllegalArgumentException, IllegalAccessException{
        logger.log(Level.INFO, "Parsing a list field {0}", field.getName());
        result.append("[");
        for(Object obj : (List)field.get(pojo)){
            result.append(parser(obj.getClass().getDeclaredFields(), obj));
            result.append(",");
        }
        removeLastCharFromStringBuilder(result);
        result.append("],");
    }
    
}
