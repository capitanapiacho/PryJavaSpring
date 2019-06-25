
package com.marman.controller;

import com.marman.modelos.Conectar;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


public class DeleteController {
    private JdbcTemplate jdbcTemplate;

    public DeleteController() {
        Conectar con = new Conectar();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());        
    }
    
    @RequestMapping("/delete.htm")
     public ModelAndView form(HttpServletRequest request)
     {
        ModelAndView mav = new ModelAndView();
        int id= Integer.parseInt(request.getParameter("id"));
        this.jdbcTemplate.update("delete from usuarios where id=?", id);
        return new ModelAndView("redirect:/home.htm");      
     }
    
}
