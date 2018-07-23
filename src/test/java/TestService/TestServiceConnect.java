/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestService;

import com.noname.bookstore.properties.ApplicationProperties;
import com.noname.bookstore.services.ServiceConnection;
import java.io.IOException;
import java.sql.Connection;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author YBolshakova
 */
public class TestServiceConnect {

    private ServiceConnection serviceConnection;
    ApplicationProperties prop;
    public static String pathForCreateTable = "D:/tmp/conectPropBooks.properties";

    @Before
    public  void newServiceTest() throws IOException {        
         prop = new ApplicationProperties();
         serviceConnection = new ServiceConnection();      
        
    }
    
    @Test
    public void testreadProperties() throws IOException{
    prop.readProperties(pathForCreateTable);   
}
    @Test
    public void testgetProperties(){
    prop.getProperties();
    }
    
    @Test
    public void testsetProperties(){
    serviceConnection.setProperties(prop);    
    }
    
    @Test
    public void testConnectService(){
     serviceConnection.getConnect();       
    }
    

}
