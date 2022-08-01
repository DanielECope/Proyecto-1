package com.afp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.afp.Controller.AfpController;
import com.afp.Controller.ClientController;
import com.afp.Model.AFP;
import com.afp.Model.Client;
import com.afp.Service.IClientService;

@RunWith(MockitoJUnitRunner.class)
public class ControladorTest {

	@InjectMocks
	ClientController clientController;
	@InjectMocks
	AfpController afpController;
	@Mock
	IClientService clientService;
	
	private Client client;
	private AFP afp;
	private String id;
	private float retiro;
	
	@Before
	public void setup()throws Exception{
		//afp=AFP.builder().numberAccount("numberaccount").cash(0).dateNow(null).retire(0).build();
		//client = Client.builder().dni("dni").name("name").lastName("lastname").phone("phone").email("email").afp(afp).build();
		id="D87654321";
		retiro=1600;
		afp=AFP.builder().numberAccount("numberaccount").cash(3000).dateNow(null).retire(0).typeAccount("PRIMA").build();
		client = Client.builder().dni("87654321").name("Daniele").lastName("Colchaoperez").phone("987654322").email("daniele@gmail.com").afp(afp).build();
	}
	@Test
	public void testCaseOk() throws Exception{
		Client client=new Client();		
		client=clientController.create(client);
		assertNull(client);
	}
	@Test
	public void testCaseUpdateOk() throws Exception{
		id="87654321";
		Client client=new Client();		
		client=clientController.update(id,client);
		assertNull(client);
	}	
	@Test
	public void testCasedeleteOk() throws Exception{
		id="87654321";
		Client client=new Client();		
		client=clientController.delete(id);
		assertNull(client);
	}
	@Test
	public void testCaseFindAllOk() throws Exception{
		Client client=new Client();		
		client=clientController.findAll().get(0);
		assertNotNull(client);
	}
	@Test
	public void testCaseError() throws Exception{
		Client client=new Client();
		client=null;
		client=clientController.create(client);
		assertNull(client);
	}
	


	@Test
	public void testCaseAFPSaveOk() throws Exception{
		AFP obj=new AFP();
		id="D87654321";
		obj=afpController.create(retiro, id);
		assertNotNull(obj);
	}
	@Test
	public void testCaseAfoOk() throws Exception{
		AFP obj=new AFP();
		id="D87654321";
		obj=afpController.getById(id);		
		assertNotNull(obj);
	}
	@Test
	public void testCaseAfoError() throws Exception{
		AFP afp=new AFP();
		afp=afpController.getById(null);		
		assertNull(afp);
	}
}
