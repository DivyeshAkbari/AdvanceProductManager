package co.pragra.productmanager.newproductmanager.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeRestContoller {

    @GetMapping("/api")
    public Map<String,String> home(Model model) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("title","My First Rest API");
        map.put("heroTItle","My First API Title");
        return map;
    }
}
