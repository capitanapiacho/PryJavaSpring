
package com.marman.controller;

import com.marman.modelos.Conectar;
import com.marman.modelos.Usuarios;
import com.marman.modelos.UsuariosValidar;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import java.sql.ResultSet;

@Controller
@RequestMapping("/edit.htm")
public class EditController {
    UsuariosValidar usuariosValidar;
    private JdbcTemplate jdbcTemplate;
    
        public EditController() {
        this.usuariosValidar = new UsuariosValidar();
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
        
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request )
    {
        ModelAndView mav = new ModelAndView();
        int id= Integer.parseInt(request.getParameter("id"));
        Usuarios d = this.selectUsuario(id);        
        mav.setViewName("edit");
        mav.addObject("usuarios", new Usuarios(id,d.getNombre(),d.getCorreo(), d.getTelefono()));
        return mav;
    } 
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form 
    (
            @ModelAttribute("usuarios") Usuarios u,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    )    
    {
         this.usuariosValidar.validate(u, result);
         if(result.hasErrors())
         {
            ModelAndView mav = new ModelAndView();
            int id= Integer.parseInt(request.getParameter("id"));
            Usuarios d = this.selectUsuario(id); 
            mav.setViewName("edit");
            mav.addObject("usuarios", new Usuarios(id,d.getNombre(),d.getCorreo(), d.getTelefono()));
            return mav;
         }else
         {
         int id= Integer.parseInt(request.getParameter("id"));
         this.jdbcTemplate.update(
         "update usuarios set nombre = ?,correo = ?, telefono = ? where id = ?",
          u.getNombre(),u.getCorreo(),u.getTelefono(),id);
          return new ModelAndView("redirect:/home.htm");        
         }
    
    }

    private Usuarios selectUsuario(int id) 
    {
        final Usuarios usuar = new Usuarios();
       
        String qry="select * from usuarios where id = "+id+"";
        
        return (Usuarios) jdbcTemplate.query
        (
                qry, new ResultSetExtractor<Usuarios>()
                
            {
                
            public Usuarios extractData(ResultSet rs)throws SQLException, DataAccessException{
                    if(rs.next())
                    {
                        usuar.setNombre(rs.getString("nombre"));
                        usuar.setCorreo(rs.getString("correo"));
                        usuar.setTelefono(rs.getString("telefono"));
                    }
                
                    return usuar;
                }
    
            }
        );
    }
}
