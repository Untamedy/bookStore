/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestService;

import com.noname.bookstore.Business.SaleMan;
import com.noname.bookstore.domains.DomainAutor;
import com.noname.bookstore.domains.DomainBook;
import com.noname.bookstore.properties.ApplicationProperties;
import com.noname.bookstore.services.AutorService;
import com.noname.bookstore.services.BookService;
import com.noname.bookstore.services.DocumentReader;
import com.noname.bookstore.services.DocumentWriter;
import com.noname.bookstore.services.GeneratorsService;
import com.noname.bookstore.services.SQLScriptsReader;
import com.noname.bookstore.services.SaleService;
import com.noname.bookstore.services.ServiceConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.*;

/**
 *
 * @author YBolshakova
 */
public class BookServiceTest extends Assert {

    public static ApplicationProperties prop;
    public static ServiceConnection connection;
    public static GeneratorsService generatorsService;
    public static BookService bookService;
    public static AutorService autorService;
    public static SaleService saleService;
    public static Connection connect;
    public static DocumentReader reader = new DocumentReader();
    public static DocumentWriter writer = new DocumentWriter();
    public static SaleMan man;
    public static String path = "D:/tmp/inputListOfBooks.txt";
    public String autorName = "K.B. Kusin";
    public String bookName = "Story about cat";
    public int articul = 23;
    public static File f = new File("D:/tmp/cheks");
    public static Statement deleteFromDB;
    public DomainBook book = new DomainBook(23, bookName, "story", 15.4, 3, 23);
    public static List<DomainBook> afterRead;
    public static String deleteSQL = "D:/tmp/delete_db_sql.txt";
    public static String createSQL = "D:/tmp/create_db_sql.txt";
    public static String createTableSQL = "D:/tmp/create_tables_sql.txt";
    public static String pathForDrop = "D:/tmp/conectPropBooksForDrop.properties";
    public static String pathForCreateTable = "D:/tmp/conectPropBooks.properties";

    @BeforeClass
    public static void createDB() throws SQLException, FileNotFoundException, IOException {

        prop = new ApplicationProperties();
        prop.readProperties(pathForDrop);
        prop.getProperties();
        connection = new ServiceConnection();
        connection.setProperties(prop);
        connect = connection.getConnect();
        SQLScriptsReader.importSQL(connect, deleteSQL);
        SQLScriptsReader.importSQL(connect, createSQL);
        connect.close();
        prop.readProperties(pathForCreateTable);
        prop.getProperties();
        connection = new ServiceConnection();
        connection.setProperties(prop);
        connect = connection.getConnect();
        SQLScriptsReader.importSQL(connect, createTableSQL);
        bookService = new BookService(generatorsService, connection);
        autorService = new AutorService(generatorsService, connection);
        saleService = new SaleService(connection);
        generatorsService = new GeneratorsService(connection);
        man = new SaleMan(saleService, bookService, autorService);

        man.addBooks(path);
        afterRead = reader.readFile(path);
    }

    @Before
    public void createConnectToDB() throws IOException, SQLException {
        prop = new ApplicationProperties();
        prop.readProperties(pathForCreateTable);
        prop.getProperties();
        connection = new ServiceConnection();
        connection.setProperties(prop);
        connect = connection.getConnect();

    }

    @Test
    public void readFileTest() throws SQLException {
        assertNotNull(afterRead);
        assertFalse(afterRead.isEmpty());
        assertEquals(4, afterRead.size());
    }

    @Test
    public void addBooksToDB() throws SQLException {
        List<DomainBook> bookAfterAdd = bookService.addBooks(afterRead);
        List<DomainBook> selectFromDB = man.selectbookByInStore();
        boolean result = (bookAfterAdd.size()) == (selectFromDB.size());
        assertNotNull(bookAfterAdd);
        assertFalse(bookAfterAdd.isEmpty());
        assertTrue(result);
        assertNotNull(generatorsService.getGeneratorID("book"));
    }

    @Test
    public void addAutorsToDB() throws SQLException {
        List<DomainBook> autorAdd = bookService.addBooks(afterRead);
        assertFalse(autorAdd.isEmpty());
        assertFalse(generatorsService.getGeneratorID("autor") == 0);
        for (DomainBook b : autorAdd) {
            List<DomainAutor> autorsList = b.getAutors();
            assertFalse(autorsList.isEmpty());
        }
    }

    @Test
    public void selectByAutorTest() throws SQLException {
        List<DomainBook> booksList = bookService.selectFromAutor(autorName);
        for (DomainBook b : booksList) {
            assertEquals(book.getName(), b.getName());
        }
    }

    @Test
    public void selectByInStore() throws SQLException {
        List<DomainBook> bookInStore = man.selectbookByInStore();
        assertNotNull(bookInStore);
        assertFalse(man.selectbookByInStore().isEmpty());
    }

    @Test
    public void selectByName() throws SQLException {
        List<DomainBook> bookListByName = man.selectBooksByName(bookName);
        for (DomainBook b : bookListByName) {
            assertEquals(book.getArticul(), b.getArticul());
        }
    }

    @Test
    public void selectByArticul() throws SQLException {
        DomainBook bookListwithBooks = man.selectbooksByArticul(articul);
        assertNotNull(bookListwithBooks);

    }

    @Test
    public void saleBook() throws SQLException, IOException {
        int qauntity_befor = bookService.selectByArticul(articul).getQuantity();
        saleService.sale();
        int qantity_after = bookService.selectByArticul(articul).getQuantity();

        File[] pathList = f.listFiles();
        assertFalse(pathList.length == 0);
        assertNotNull(saleService.getListForSale());
        assertFalse(qauntity_befor == qantity_after);

    }

    @AfterClass
    public static void deletFromDB() throws SQLException {
        f.deleteOnExit();
        deleteFromDB = connect.createStatement();
        deleteFromDB.addBatch("Delete from \"booklist\".autor_of_books");
        deleteFromDB.addBatch("Delete from \"booklist\".books");
        deleteFromDB.addBatch("Delete from \"booklist\".autor");
        deleteFromDB.executeBatch();
    }

}
