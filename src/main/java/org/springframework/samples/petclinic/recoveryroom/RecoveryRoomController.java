package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/recoveryroom")
public class RecoveryRoomController {
	 private final String  RECOVERY_ROOM_LISTING_VIEW="recoveryroom/recoveryroomList";
	    private final String RECOVERY_ROOM_FORM="recoveryroom/createOrUpdateRecoveryRoomForm";
	    
	    private final RecoveryRoomService recoveryroomService;
	    @Autowired
	    public RecoveryRoomController(RecoveryRoomService service){
	        this.recoveryroomService=service;
	    }
	    
	    
	    @Transactional(readOnly = true)
	    @GetMapping("/")
	    public ModelAndView showRecoveryRooms(){
	        ModelAndView result=new ModelAndView(RECOVERY_ROOM_LISTING_VIEW);
	        result.addObject("recoveryrooms", recoveryroomService.getAll());
	        return result;
	    }
	    
	    @GetMapping("/create")
	    public ModelAndView createRecoveryRoom() {
	        ModelAndView mav = new ModelAndView(RECOVERY_ROOM_FORM);
	        mav.addObject("recoveryRoom", new RecoveryRoom());
	        return mav;
	    }
	    
	    
	    @PostMapping("/create")
	    public ModelAndView processCreationForm(@Valid RecoveryRoom recoveryroom, BindingResult result) {
	        ModelAndView mav;
	        if (result.hasErrors()) {
	            mav = new ModelAndView(RECOVERY_ROOM_FORM);
	            mav.addObject("recoveryRoom", recoveryroom);
	            mav.addObject("types", recoveryroomService.getAllRecoveryRoomTypes());
	        } else {
	            this.recoveryroomService.save(recoveryroom);
	            mav = new ModelAndView("welcome");
	        }
	        return mav;
	    }
	    
	    @ModelAttribute("recoveryRoomTypes")
	    public List<RecoveryRoomType> populateRecoveryRoomTypes() {
	        return this.recoveryroomService.getAllRecoveryRoomTypes();
	    }
}
