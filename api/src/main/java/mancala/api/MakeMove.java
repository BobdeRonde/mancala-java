package mancala.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.MancalaException;
import mancala.domain.MancalaImpl;

@Path("/play")
public class MakeMove {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response initialize(
            @Context HttpServletRequest request,
            IndexInput index) {
        HttpSession session = request.getSession(true);
        MancalaImpl mancala = (MancalaImpl) session.getAttribute("mancala");
        String namePlayer1 = (String) session.getAttribute("player1");
        String namePlayer2 = (String) session.getAttribute("player2");

        mancala.playPit(index.getIndex());
        var output = new Mancala(mancala, namePlayer1, namePlayer2);
        return Response.status(200).entity(output).build();
    }
}
