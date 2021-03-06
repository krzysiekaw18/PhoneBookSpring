package pl.krzysiekstuglik.PhoneBook.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import pl.krzysiekstuglik.PhoneBook.models.NumberEntity;
import pl.krzysiekstuglik.PhoneBook.models.forms.NumberForm;
import pl.krzysiekstuglik.PhoneBook.models.repositories.NumberRepository;

import java.util.List;

@Service
public class MainServices {

    final NumberRepository numberRepository;

    @Autowired
    public MainServices(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    public boolean tryToAddNumber(NumberForm numberForm){
        if (numberRepository.existsByNumber(numberForm.getNumber())){
            return false;
        }
        NumberEntity numberEntity = createEntityFromForm(numberForm);
        numberRepository.save(numberEntity);
        return true;
    }

    private NumberEntity createEntityFromForm(NumberForm numberForm) {
        NumberEntity numberEntity = new NumberEntity();
        numberEntity.setName(numberForm.getName());
        numberEntity.setSurname(numberForm.getSurname());
        numberEntity.setNumber(numberForm.getNumber());
        return numberEntity;
    }

   public List<NumberEntity> getAll(){
        return numberRepository.findAll();
   }

   public NumberEntity getAllDetails(int id){
        return numberRepository.findById(id).get();
   }
}
