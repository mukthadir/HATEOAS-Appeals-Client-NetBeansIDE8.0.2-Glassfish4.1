package com.mhchoudh.appeal.client.activities;

import java.net.URI;

import com.mhchoudh.appeal.client.ClientAppeal;
import com.mhchoudh.appeal.model.Appeal;
import com.mhchoudh.appeal.representations.AppealRepresentation;

public class PlaceAppealActivity extends Activity {

    private Appeal appeal;

    public void placeAppeal(Appeal appeal, URI appealUri) throws InterruptedException {
        
        try {
            AppealRepresentation createdAppealRepresentation = binding.createAppeal(appeal, appealUri);
            this.actions = new RepresentationHypermediaProcessor().extractNextActionsFromAppealRepresentation(createdAppealRepresentation);
            this.appeal = createdAppealRepresentation.getAppeal();
        } catch (MalformedAppealException e) {
            this.actions = retryCurrentActivity();
        } catch (ServiceFailureException e) {
            this.actions = retryCurrentActivity();
        }
    }
    
    public ClientAppeal getAppeal() throws InterruptedException {
        return new ClientAppeal(appeal);
    }
}
