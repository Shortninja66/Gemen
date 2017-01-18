package us.theaura.gemen.util.lib.json;

import java.lang.reflect.Type;

import net.minecraft.util.com.google.gson.Gson;
import net.minecraft.util.com.google.gson.JsonDeserializationContext;
import net.minecraft.util.com.google.gson.JsonDeserializer;
import net.minecraft.util.com.google.gson.JsonElement;
import net.minecraft.util.com.google.gson.JsonObject;
import net.minecraft.util.com.google.gson.JsonParseException;
import net.minecraft.util.com.google.gson.JsonSerializationContext;
import net.minecraft.util.com.google.gson.JsonSerializer;

/**
 * Hierarchy adapter for Gson.
 * 
 * @since 27 December 2016 9:44:17 PM
 * @author Shortninja
 */

public final class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
	
	@Override
	public JsonElement serialize(T object, Type interfaceType, JsonSerializationContext context) {
		final JsonObject wrapper = new JsonObject();
		wrapper.addProperty("type", object.getClass().getName());
		wrapper.add("data", new Gson().toJsonTree(object));

		return wrapper;
	}

	@Override
	public T deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject wrapper = (JsonObject) elem;
		final JsonElement typeName = get(wrapper, "type");
		final JsonElement data = get(wrapper, "data");
		final Type actualType = typeForName(typeName);

		return new Gson().fromJson(data, actualType);
	}

	private Type typeForName(final JsonElement typeElem) {
		try {
			return Class.forName(typeElem.getAsString());
		}catch (ClassNotFoundException e) {
			throw new JsonParseException(e);
		}
	}

	private JsonElement get(final JsonObject wrapper, String memberName) {
		final JsonElement elem = wrapper.get(memberName);

		if(elem == null) {
			throw new JsonParseException("No '" + memberName + "' member found in what was expected to be an interface wrapper");
		}

		return elem;
	}
	
}