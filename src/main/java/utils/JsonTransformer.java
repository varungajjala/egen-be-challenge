package utils;

import com.google.gson.Gson;

import spark.ResponseTransformer;

/**
 * ResponseTransformer to transform the response to JSON
 * @author Varun
 *
 */
public class JsonTransformer implements ResponseTransformer{

	private Gson gson = new Gson();
	
	@Override
	public String render(Object model) throws Exception {
		// TODO Auto-generated method stub
		return gson.toJson(model);
	}
}

