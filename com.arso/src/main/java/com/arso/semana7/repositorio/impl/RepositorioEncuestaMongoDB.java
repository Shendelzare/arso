package com.arso.semana7.repositorio.impl;

import java.util.LinkedList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.arso.semana4.repositorio.EntidadNoEncontrada;
import com.arso.semana4.repositorio.RepositorioException;
import com.arso.semana7.model.Encuesta;
import com.arso.semana7.repositorio.RepositorioEncuesta;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class RepositorioEncuestaMongoDB implements RepositorioEncuesta {

	private MongoCollection<Encuesta> encuestas;

	public RepositorioEncuestaMongoDB() {
		// TODO: la cadena de conexión debería obtenerse de una propiedad

		String uriString = "mongodb+srv://arso:arso-21@cluster0.ah8af.mongodb.net/bdarso?retryWrites=true&w=majority";

		ConnectionString connectionString = new ConnectionString(uriString);

		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		MongoClient mongoClient = MongoClients.create(clientSettings);

		MongoDatabase db = mongoClient.getDatabase("test");

		this.encuestas = db.getCollection("enlaces", Encuesta.class);
	}

	/** Métodos de apoyo **/

	protected boolean checkDocument(ObjectId id) {

		return encuestas.find(Filters.eq("_id", id)).first() != null;
	}

	/** fin métodos de apoyo **/
	@Override
	public String add(Encuesta entity) throws RepositorioException {
		try {
			entity.setId(new ObjectId());

			encuestas.insertOne(entity);

			return entity.getIdentificador();
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido insertar la entidad", e);
		}
	}

	@Override
	public void update(Encuesta entity) throws RepositorioException, EntidadNoEncontrada {
		if (!checkDocument(entity.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + entity.getId());

		try {

			encuestas.replaceOne(Filters.eq("_id", entity.getId()), entity);

		} catch (Exception e) {
			throw new RepositorioException("No se ha podido actualizar la entidad, id:" + entity.getId(), e);
		}

	}

	@Override
	public void delete(Encuesta entity) throws RepositorioException, EntidadNoEncontrada {
		if (!checkDocument(entity.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + entity.getId());

		try {
			encuestas.deleteOne(Filters.eq("_id", entity.getId()));
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido borrar la entidad, id:" + entity.getId(), e);
		}

	}

	@Override
	public Encuesta getById(String id) throws RepositorioException, EntidadNoEncontrada {

		Encuesta encuesta = encuestas.find(Filters.eq("_id", new ObjectId(id))).first();

		if (encuesta == null)
			throw new EntidadNoEncontrada("No existe la entidad con id:" + id);

		return encuesta;
	}

	@Override
	public List<Encuesta> getAll() throws RepositorioException {
		LinkedList<Encuesta> allEncuestas = new LinkedList<>();

		encuestas.find().into(allEncuestas);

		return allEncuestas;
	}

	@Override
	public List<String> getIds() {

		LinkedList<String> allIds = new LinkedList<String>();

		encuestas.find().map(e -> e.getId().toString()).into(allIds);

		return allIds;
	}

}
