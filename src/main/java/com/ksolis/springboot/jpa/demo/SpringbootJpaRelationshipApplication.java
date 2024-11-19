package com.ksolis.springboot.jpa.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ksolis.springboot.jpa.demo.entities.Address;
import com.ksolis.springboot.jpa.demo.entities.Client;
import com.ksolis.springboot.jpa.demo.entities.ClientDetails;
import com.ksolis.springboot.jpa.demo.entities.Invoice;
import com.ksolis.springboot.jpa.demo.repositories.ClientRepository;
import com.ksolis.springboot.jpa.demo.repositories.ClientsDetailsRepository;
import com.ksolis.springboot.jpa.demo.repositories.InvoiceRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements  CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;


	@Autowired
	private ClientsDetailsRepository clientsDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args)throws Exception{
		oneToOneFindById();
	}
	@Transactional
	public void oneToOneFindById(){
		ClientDetails clientDetails = new ClientDetails(4000, true);
		clientsDetailsRepository.save(clientDetails);
		
		Optional<Client> clientOptional = clientRepository.findById(2L);
		clientOptional.ifPresent(client->{
			client.setClientDetails(clientDetails);
			clientRepository.save(client);
			System.out.println(client);
		});
		
	}
	@Transactional
	public void oneToOne(){
		Client client = new Client("Frank", "Moras");
        clientRepository.save(client);

		ClientDetails clientDetails = new ClientDetails(4000, true);
		clientsDetailsRepository.save(clientDetails);
	}

	@Transactional
	public void removeInvoiceBidireccionalFindById(){
		
		Optional<Client> optionalClient = clientRepository.findOne(2L);

        optionalClient.ifPresent(client-> {
			Invoice invoice1 = new Invoice("Compras de la oficina",5000L);
			Invoice invoice2 = new Invoice("Compras de la casa",5043L);

			client.addInvoice(invoice1).addInvoice(invoice2);
	
			clientRepository.save(client);
			
			System.out.println(client);
		});
		
		Optional<Client> optionalClientDd = clientRepository.findOne(2L);

		optionalClientDd.ifPresent(client ->{
			Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				client.getInvoices().remove(invoice);
				invoice.setClient(null);
				clientRepository.save(client);
				System.out.println(client);

			});
		});
	};



	@Transactional
	public void OneToManyInvoiceBidireccionalFindById(){
		Optional<Client> optionalClient = clientRepository.findOneW(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de la oficina",5000L);
			Invoice invoice2 = new Invoice("Compras de la casa",5043L);
			List<Invoice> invoices = new ArrayList<>();
			client.addInvoice(invoice1).addInvoice(invoice2);
	
			clientRepository.save(client);
			System.out.println(client);
		});
	}


	@Transactional
	public void OneToManyInvoiceBidireccional(){
		Client client = new Client("Frank", "Moras");

		Invoice invoice1 = new Invoice("Compras de la oficina",5000L);
		Invoice invoice2 = new Invoice("Compras de la casa",5043L);
		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);
	}

	@Transactional
	public void removeAddressFindById(){
		
		Optional<Client> optionalClient = clientRepository.findOne(2L);

        optionalClient.ifPresent(client-> {
			Address address1= new Address(1234,"El verjel");
			Address address2= new Address(1289,"Puebla");
			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			clientRepository.save(client);
			System.out.println(client);	

			Optional<Client>  optionalClient2 = clientRepository.findById(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println(c);
			});
	

		});
		
	};

	@Transactional
	public void removeAddress(){
		Client client = new Client("Frank", "Moras");
		Address address1= new Address(1234,"El verjel");
		Address address2= new Address(1289,"Puebla");
		
		Set<Address> addresses = new HashSet<>();
		addresses.add(address1);
		addresses.add(address2);
		client.setAddresses(addresses);
		
		clientRepository.save(client);
		System.out.println(client);

		Optional<Client>  optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});

	};

	@Transactional
	public void OneToMany(){
		Client client = new Client("Frank", "Moras");
		Address address1= new Address(1234,"El verjel");
		Address address2= new Address(1289,"Puebla");
		
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);
		clientRepository.save(client);
		System.out.println(client);
	};

	@Transactional
	public void OneToManyFindById(){
		
		Optional<Client> optionalClient = clientRepository.findById(2L);

        optionalClient.ifPresent(client-> {
			Address address1= new Address(1234,"El verjel");
			Address address2= new Address(1289,"Puebla");

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			clientRepository.save(client);
			System.out.println(client);	
		});
		
	};

    @Transactional
	public void manyToOne(){
		Client client = new Client("Panchita", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras de oficina", 3000L);
		invoice.setClient(client);
		
		
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}
    
	@Transactional
	public void manyToOneFindByIdClient(){
		Optional<Client> optionalClient = clientRepository.findById(1L);
		if(optionalClient.isPresent()){
			Client client = optionalClient.orElseThrow() ;  
			Invoice invoice = new Invoice("Compras de oficina", 3000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);                         
		}
	}
}
