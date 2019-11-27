package com.todo1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.todo1.model.Product;
import com.todo1.service.impl.Todo1ServiceImpl;

import org.junit.AfterClass;
import org.junit.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration
@TestPropertySource(properties = { "spring.datasource.driverClassName=org.apache.derby.jdbc.EmbeddedDriver",
        "spring.datasource.urljdbc:jdbc:derby:memory:testdb;create=true" })
@RunWith(SpringRunner.class)
@SpringBootTest
public class Todo1ApplicationTests {

    @Autowired
    Todo1ServiceImpl todo1;
	
	static EmbeddedDatabase db;

    @BeforeClass
    public static void initialInMemoryDatabase() throws IOException, FileNotFoundException, SQLException {
        db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.DERBY).setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true).addScript("schemaTest.sql").addScripts("dataTest.sql").build();
    }

    @Test
    public void getAllProductsTest() throws Exception {

        List<Product> products = todo1.getAllProducts();
        Assert.assertEquals(1, products.get(0).getId());
        Assert.assertEquals("DC COMIC", products.get(0).getCompany());
        Assert.assertEquals("Sudadera con capucha", products.get(0).getName());
        Assert.assertEquals("Sudadera con capucha para niño", products.get(0).getDescription());
        Assert.assertEquals("Ropa", products.get(0).getType());
        Assert.assertEquals(19,0, products.get(0).getPrice());
        Assert.assertEquals(5, products.get(0).getQuantity());
	}

	@Test
	public void addProduct(){
		Map<String, Object> newProduct = new HashMap<>();
		newProduct.put("company","MARVEL");
		newProduct.put("name","Lapiz IRON-MAN");
		newProduct.put("price",14);
		newProduct.put("type","Papelería");
		newProduct.put("quantity",5);
		newProduct.put("description","Lapiz escolar de IRON-MAN");
		List<Product> products = todo1.getAllProducts();
		Integer listLenght = products.size();
		String resultAddProd = todo1.addProduct(newProduct);
		List<Product> productsAfter = todo1.getAllProducts();
		Assert.assertEquals("Producto guardado", resultAddProd);
		Assert.assertEquals(listLenght + 1, productsAfter.size());
		Assert.assertEquals("Lapiz IRON-MAN", productsAfter.get(5).getName().toString());
	}

	@Test
	public void updateProduct(){
		Product productBefore = todo1.getAllProducts().get(1);
		Map<String, Object> newProduct = new HashMap<>();
		newProduct.put("company","DC COMIC");
		newProduct.put("name","Camiseta con manga");
		newProduct.put("price",16);
		newProduct.put("type","Ropa");
		newProduct.put("quantity",1);
		newProduct.put("description","Camiseta con manga para niño");
		newProduct.put("id",productBefore.getId());
		String resultUptProd = todo1.updateProduct(newProduct);
		Product productAfter = todo1.getAllProducts().get(1);
		Assert.assertEquals("Producto actualizado", resultUptProd);
		Assert.assertEquals(1, productAfter.getQuantity());
	}
	
	@AfterClass
	public static void shuttingDown(){
		db.shutdown();
	}

}

