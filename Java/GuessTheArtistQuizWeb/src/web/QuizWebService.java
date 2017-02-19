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
import core.JavaRoundBean;
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
	public JavaRoundBean getSongs() throws MalformedURLException, IOException, JSONException{
		HttpSession session = req.getSession(false);
		if (true){
			QuizListCreator qc = new QuizListCreator();
			JavaRoundBean round = qc.RoundCreator(qc.artistRandomChooser());
			return round;
		} else {
			res.sendError(505);
		}
		return null;
	}

}
