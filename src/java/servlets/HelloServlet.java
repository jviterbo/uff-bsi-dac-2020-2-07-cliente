/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import bean_interfaces.HelloBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author viter
 */
@WebServlet(name = "HelloServlet", urlPatterns = {"/HelloServlet"})
public class HelloServlet extends javax.servlet.http.HttpServlet {

    private static final long serialVersionUID = 1L;

//    @EJB (lookup="java:global/HelloWorldEE/HelloWorldEE-ejb/HelloBean!beans.HelloBeanRemote")
//    private HelloBeanRemote hello;
    /**
     * Default constructor.
     */
    public HelloServlet() {
        // TODO Auto-generated constructor stub
    }

    WebTarget wt;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int status = 0;
        String hora = "?", minutos = "?", turno = "?", dia = "?", mes = "?", ano = "?", diasemana = "?";

        //Context ctx;
        PrintWriter out = response.getWriter();
        String nome = request.getParameter("nome");

        Client client = ClientBuilder.newClient();
        URI uri;
        String base = "http://localhost:8080/HelloWorldEE-war/resources/hello/";

        try {
            String link = base + "hour";
            uri = new URI(link);
            this.wt = client.target(uri);
            wt.request().accept("text/html");
            Invocation call = wt.request().buildGet();
            Response resposta = call.invoke();
            status = resposta.getStatus();
            hora = resposta.readEntity(String.class);

            link = base + "minutes";
            uri = new URI(link);
            wt = client.target(uri);
            wt.request().accept("text/plain");
            call = wt.request().buildGet();
            resposta = call.invoke();
            status = resposta.getStatus();
            minutos = resposta.readEntity(String.class);

            link = base + "shift";
            uri = new URI(link);
            wt = client.target(uri);
            wt.request().accept("text/plain");
            call = wt.request().buildGet();
            resposta = call.invoke();
            status = resposta.getStatus();
            turno = resposta.readEntity(String.class);

            link = base + "day";
            uri = new URI(link);
            wt = client.target(uri);
            wt.request().accept("text/plain");
            call = wt.request().buildGet();
            resposta = call.invoke();
            status = resposta.getStatus();
            dia = resposta.readEntity(String.class);

            link = base + "month";
            uri = new URI(link);
            wt = client.target(uri);
            wt.request().accept("text/plain");
            call = wt.request().buildGet();
            resposta = call.invoke();
            status = resposta.getStatus();
            mes = resposta.readEntity(String.class);

            link = base + "year";
            uri = new URI(link);
            wt = client.target(uri);
            wt.request().accept("text/plain");
            call = wt.request().buildGet();
            resposta = call.invoke();
            status = resposta.getStatus();
            ano = resposta.readEntity(String.class);

            link = base + "weekday";
            uri = new URI(link);
            wt = client.target(uri);
            wt.request().accept("text/plain");
            call = wt.request().buildGet();
            resposta = call.invoke();
            status = resposta.getStatus();
            diasemana = resposta.readEntity(String.class);

            String msg = "Olá";

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello World EE</title>");
            out.println("<meta charset=\"ISO-8859-1\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Exemplo de uso de EJBs com diferentes interfaces</h1>");
            out.println("<h2>Interface REST</h2>");
            out.println("<p>" + msg + ", " + nome + "!</p>");
            out.println("<p> Hoje é " + dia + " de " + mes + " de " + ano + ", uma " + diasemana + "...</p>");
            out.println("<p> São " + hora + ":" + minutos + "h da " + turno + "...</p>");
            out.println("<p></p>");
            out.println("<p>Tente novamente: [<a href=\"./hello.html\">REST</a>]</p>");
            out.println("</body>");
            out.println("</html>");

        } catch (URISyntaxException ex) {
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello World EE</title>");
            out.println("<meta charset=\"ISO-8859-1\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Exemplo de uso de EJBs com diferentes interfaces</h1>");
            out.println("<h2>Servlet: Hello World EE</h2>");
            out.println("<p>" + nome + ", algo deu errado... :-(</p>");
            out.println("<p>Status: " + status + "</p>");
            out.println("<p>" + ex.getLocalizedMessage() + "(</p>");
            out.println("<p></p>");
            out.println("<p>Tente novamente: [<a href=\"./hello.html\">REST</a>]</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
