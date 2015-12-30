package com.mhchoudh.appeal.client.activities;

import java.net.URI;

import com.mhchoudh.appeal.client.ClientAppeal;
import com.mhchoudh.appeal.model.Appeal;
import com.mhchoudh.appeal.representations.AppealRepresentation;

public class UpdateAppealActivity extends Activity {

    private final URI updateUri;
    private AppealRepresentation updatedAppealRepresentation;

    public UpdateAppealActivity(URI updateUri) {
        this.updateUri = updateUri;
    }

    public void updateAppeal(Appeal appeal) {
        try {
            updatedAppealRepresentation = binding.updateAppeal(appeal, updateUri);
            actions = new RepresentationHypermediaProcessor().extractNextActionsFromAppealRepresentation(updatedAppealRepresentation);
        } catch (MalformedAppealException e) {
            actions = retryCurrentActivity();
        } catch (ServiceFailureException e) {
            actions = retryCurrentActivity();
        } catch (NotFoundException e) {
            actions = noFurtherActivities();
        } catch (CannotUpdateAppealException e) {
            actions = noFurtherActivities();
        }
    }
    
    public ClientAppeal getAppeal() throws InterruptedException {
        return new ClientAppeal(updatedAppealRepresentation.getAppeal());
    }
}
