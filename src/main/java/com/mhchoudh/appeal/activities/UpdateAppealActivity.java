package com.mhchoudh.appeal.activities;

import com.mhchoudh.appeal.model.Identifier;
import com.mhchoudh.appeal.model.Appeal;
import com.mhchoudh.appeal.model.AppealStatus;
import com.mhchoudh.appeal.repositories.AppealRepository;
import com.mhchoudh.appeal.representations.AppealRepresentation;
import com.mhchoudh.appeal.representations.CSE564AppealsUri;

public class UpdateAppealActivity {
    public AppealRepresentation update(Appeal order, CSE564AppealsUri orderUri) throws InterruptedException {
        Identifier orderIdentifier = orderUri.getId();

        AppealRepository repository = AppealRepository.current();
        if (AppealRepository.current().appealNotPlaced(orderIdentifier)) { // Defensive check to see if we have the order
            throw new NoSuchAppealException();
        }

        if (!orderCanBeChanged(orderIdentifier)) {
            throw new UpdateException();
        }

        Appeal storedOrder = repository.get(orderIdentifier);
        
        storedOrder.setStatus(storedOrder.getStatus());


        return AppealRepresentation.createResponseAppealRepresentation(storedOrder, orderUri); 
    }
    
    private boolean orderCanBeChanged(Identifier identifier) throws InterruptedException {
        return AppealRepository.current().get(identifier).getStatus() == AppealStatus.NotSent;
    }
}
