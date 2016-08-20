/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.involves.converter;

import br.com.involves.converter.pojo.Address;
import br.com.involves.converter.pojo.Person;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Fernando
 */
public class ConverterTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    @Test
    public void testConverterToJson() throws IllegalArgumentException, IllegalAccessException, IOException {
        JsonConverter converter = new JsonConverter();
        File expected = new File("src/test/resources/personExpected.json");
        File output = folder.newFile("personOutput.json");

        Address address1 = new Address("Rua José", "39", "São José");
        List<Address> listAddress = new ArrayList<>();
        listAddress.add(address1);
        Person person = new Person("Fernando", 28, 'M', listAddress, new double[]{8.0, 6.0, 10.0});

        converter.convert(person, output);
        Assert.assertTrue(output.exists());
        Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
    }

    @Test
    public void testListConverterToJson() throws IllegalArgumentException, IllegalAccessException, IOException {
        JsonConverter converter = new JsonConverter();
        File expected = new File("src/test/resources/listPersonExpected.json");
        File output = folder.newFile("listPersonOutput.json");

        Address address1 = new Address("Rua José", "39", "São José");
        Address address2 = new Address("Rua João", "100", "Florianópolis");
        List<Address> listAddress = new ArrayList<>();
        listAddress.add(address1);
        listAddress.add(address2);

        Person person1 = new Person("Fernando", 28, 'M', listAddress, new double[]{8.0, 6.0, 10.0});
        Person person2 = new Person("Maria", 30, 'F', listAddress, new double[]{0.0, 2.0, 5.0});
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        converter.convert(personList, output);
        
        Assert.assertTrue(output.exists());
        Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
    }
    
    @Test
    public void testConverterToJsonNull() throws IllegalArgumentException, IllegalAccessException, IOException {
        JsonConverter converter = new JsonConverter();
        File expected = new File("src/test/resources/personOutputNullExpected.json");
        File output = folder.newFile("personOutputNullOutput.json");

        Person person = new Person(null, 0, ' ', null, null);

        converter.convert(person, output);
        Assert.assertTrue(output.exists());
        Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
    }
}
