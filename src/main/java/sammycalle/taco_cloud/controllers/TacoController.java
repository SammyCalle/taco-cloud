package sammycalle.taco_cloud.controllers;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sammycalle.taco_cloud.data.repository.TacoRepository;
import sammycalle.taco_cloud.domain.model.Taco;





@RestController
@RequestMapping(path="/api/tacos", produces={"application/json", "text/xml"})
@CrossOrigin(origins="http://tacocloud:8080")
public class TacoController {

    private final TacoRepository tacoRepository;

    public TacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

     @GetMapping(params="recent")
     public Iterable<Taco> recentTacos() {
         PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
         return tacoRepository.findAll(page).getContent();
     }

     @GetMapping("/{id}")
     public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);

        return optTaco
        .map(taco -> ResponseEntity.ok(taco))
        .orElseGet(() -> ResponseEntity.notFound().build());
     }


     @PostMapping(consumes="application/json")
     @ResponseStatus(HttpStatus.CREATED)
     public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
     }

     
     
     

}
