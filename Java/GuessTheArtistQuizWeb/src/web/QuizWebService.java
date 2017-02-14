package web;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import core.QuizListCreator;

@WebService
@Path("quizWebService")
public class QuizWebService {
	
	@Context
	private HttpServletRequest req;
	
	@Context
	private HttpServletResponse res;
	
	@Path("getSongs")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getSongs() throws MalformedURLException, IOException, JSONException{
		HttpSession session = req.getSession();
		if (session!=null){
		QuizListCreator qc = new QuizListCreator();
		JSONObject songs = qc.JSONsongsCreator(qc.artistRandomChooser());
		return songs;
		} else {
			res.sendError(505);
			return null;
		}
	}

}