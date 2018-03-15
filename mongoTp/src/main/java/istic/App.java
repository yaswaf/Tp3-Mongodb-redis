package istic;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws UnknownHostException
    {
        Morphia morphia = new Morphia();
        MongoClient mongo = new MongoClient();
        morphia.map(Person.class).map(Address.class);
        Datastore ds = morphia.createDatastore(mongo, "my_database");

        Person p = new Person();
        p.setName("Tintin");

        Address address = new Address();
        address.setStreet("123 Some street");
        address.setCity("Some city");
        address.setPostCode("123 456");
        address.setCountry("Some country");
        //set address
        p.setAddress(address);
        // Save the POJO
        ds.save(p);
        ds.save(address);
        for (Person e : ds.find(Person.class))
            System.out.println(e.getName()+ " "+e.getAddress().toString());

    }

}
