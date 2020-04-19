package com.group.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Arrays;

@FacesValidator
public class ActorValidator implements Validator {
    private String[] actorNames = {"Courteney", "David", "James", "Jennifer", "Lisa",
            "Maggie", "Matthew", "Matt", "Paul", "Reese"};


    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object name) {
        if (!Arrays.asList(actorNames).contains(name)) {
            FacesMessage msg =
                    new FacesMessage("Actor name should be one of these: " + Arrays.toString(actorNames));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

}
