package eshop.entityjson;

import java.time.LocalDate;
import java.util.List;

import formationAlten.entity.Client;

public class CommandeJson {
	
	private Long numero;
	
	private LocalDate date;
	
	private Client client;
	
	private List<AchatJson> achatsJson;
}
