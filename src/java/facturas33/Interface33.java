/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturas33;

import hl.facturas33.funciones.ProcesarFactura;
import hl.util.HLFilesFunctions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ruben
 */
@WebServlet(name = "Interface33", urlPatterns = {"/Interface33"})
public class Interface33 extends HttpServlet {

    private static final long serialVersionUID = 1L;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        
        
        
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Interface33</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Interface33 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/text");
        response.setHeader("Cache-Control", "no-cache");
        
        System.out.println("Iniciando prueba 1");
        //ProcesarFactura hlc = new ProcesarFactura("c:\\fuentes\\HLCore\\hl.properties");
        ProcesarFactura hlc = new ProcesarFactura(HLFilesFunctions.WindowsPath("/home/hlcore/hl.properties"));
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        String line = "";
        
        while((line = br.readLine()) != null){
            json += line;
            System.out.println("line:"+line);
        }
        System.out.println("Preiniciando prueba 1");
        try
        {
            hlc.EjecutarFactura(json, false);
        }
        catch(Exception ex)
        {
            response.setStatus( HttpServletResponse.SC_FORBIDDEN  );
        }
        
        if(hlc.getSf().getErrorCode()>1)
        {
            out.println(hlc.getSf().getError());
            response.setStatus( HttpServletResponse.SC_FORBIDDEN  );
        }
        else
        {
            out.println(hlc.getSolicitudFacturaJson());
            response.setStatus(HttpServletResponse.SC_OK);
        }
        
       
      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
