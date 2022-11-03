package org.springframework.samples.petclinic.recoveryroom;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RecoveryRoomTypeFormatter implements Formatter<RecoveryRoomType>{

	RecoveryRoomService producService;
	
    @Override
    public String print(RecoveryRoomType object, Locale locale) {
        return object.getName();
    }

    @Override
    public RecoveryRoomType parse(String text, Locale locale) throws ParseException {
    	 RecoveryRoomType recoveryroomType = producService.getRecoveryRoomType(text);
         if (recoveryroomType == null) {
             throw new ParseException("Product type not found: " + text, 0);
         }
         return recoveryroomType;
     }
    
    
}
