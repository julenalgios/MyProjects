package web;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

	@Path("getFirstSong")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JavaRoundBean getFirstSong() throws MalformedURLException, IOException, JSONException {
		HttpSession session = req.getSession(true);
		if (!session.equals(null)) {
			QuizListCreator qc = new QuizListCreator();
			JavaRoundBean round = qc.RoundCreator(qc.artistRandomChooser());
			round.setRoundScore(50);
			session.setAttribute("round", round);
			session.setAttribute("songsCounter", "0");
			JavaRoundBean returnedRound = new JavaRoundBean();
			returnedRound.setArtistName(round.getArtistName());
			returnedRound.setFirstSong(round.getFirstSong());
			return returnedRound;
		} else {
			res.sendError(505);
		}
		return null;
	}

	@Path("getOneMoreSong/{inputValue}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JavaRoundBean getSecondSong(@PathParam("inputValue") String inputValue)
			throws MalformedURLException, IOException, JSONException {
		HttpSession session = req.getSession(false);
		JavaRoundBean answer = (JavaRoundBean) session.getAttribute("round");
		if (!answer.getArtistName().equals(inputValue)) {
			if (!session.equals(null)) {
				String counter = (String) session.getAttribute("songsCounter");
				if (counter.equals("0")) {
					JavaRoundBean round = (JavaRoundBean) session.getAttribute("round");
					round.setRoundScore(30);
					JavaRoundBean returnedRound = new JavaRoundBean();
					returnedRound.setArtistName(round.getArtistName());
					returnedRound.setFirstSong(round.getFirstSong());
					returnedRound.setSecondSong(round.getSecondSong());
					session.removeAttribute("songsCounter");
					session.setAttribute("songsCounter", "1");
					return returnedRound;
				} else {
					JavaRoundBean round = (JavaRoundBean) session.getAttribute("round");
					round.setRoundScore(20);
					JavaRoundBean returnedRound = new JavaRoundBean();
					returnedRound.setArtistName(round.getArtistName());
					returnedRound.setFirstSong(round.getFirstSong());
					returnedRound.setSecondSong(round.getSecondSong());
					returnedRound.setThirdSong(round.getThirdSong());
					return returnedRound;
				}

			} else {
				res.sendError(505);
			}
		} else {
			JavaRoundBean round = (JavaRoundBean) session.getAttribute("round");
			JavaRoundBean returnedRound = new JavaRoundBean();
			returnedRound.setRoundScore(round.getRoundScore());
			returnedRound.setArtistName(round.getArtistName());
			returnedRound.setFirstSong(null);
			returnedRound.setSecondSong(null);
			returnedRound.setThirdSong(null);
			returnedRound.setTotalScore(round.getRoundScore() + round.getTotalScore());
			return returnedRound;
		}
		return null;
	}

}
