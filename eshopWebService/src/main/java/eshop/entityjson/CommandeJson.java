package eshop.entityjson;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;

import formationAlten.entity.Client;
import formationAlten.jsonview.Views;

@Entity
@SequenceGenerator(name = "seqCommandeJson", sequenceName = "order_json_seq_id", initialValue = 1, allocationSize = 1)
public class CommandeJson {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCommandeJson")
	@JsonView(Views.Common.class)
	private Long numero;
	@JsonView(Views.Common.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	@JsonView(Views.Common.class)
	private Client client;
	
	@JsonView(Views.CommandeWithAchats.class)
	@OneToMany(mappedBy = "id.commande")
	private List<AchatJson> achatsJson;
	
	
	
	public CommandeJson() {
		super();
	}

	public CommandeJson(LocalDate date, Client client, List<AchatJson> achatsJson) {
		super();
		this.date = date;
		this.client = client;
		this.achatsJson = achatsJson;
	}



	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<AchatJson> getAchatsJson() {
		return achatsJson;
	}

	public void setAchatsJson(List<AchatJson> achatsJson) {
		this.achatsJson = achatsJson;
	} 
	
	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandeJson other = (CommandeJson) obj;
		return Objects.equals(numero, other.numero);
	}
	
	
}
