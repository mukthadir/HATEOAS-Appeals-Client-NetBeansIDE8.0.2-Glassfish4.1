package com.mhchoudh.appeal.client.activities;

import java.net.URI;

import com.mhchoudh.appeal.model.AppealFormat;
import com.mhchoudh.appeal.representations.FormatAppealRepresentation;

public class FormatActivity extends Activity {

    private final URI appealFormatUri;
    private AppealFormat appealFormat;

    public FormatActivity(URI paymentUri) {
        this.appealFormatUri = paymentUri;
    }

    public void formatAppeal(AppealFormat format) throws InterruptedException {        
        try {
            FormatAppealRepresentation formatAppealRepresentation = binding.formatAp(format, appealFormatUri);
            actions = new RepresentationHypermediaProcessor().extractNextActionsFromPaymentRepresentation(formatAppealRepresentation);
            format = formatAppealRepresentation.getFormat();
        } catch (InvalidFormatException e) {
            actions = retryCurrentActivity();
        } catch (NotFoundException e) {
            actions = noFurtherActivities();
        } catch (DuplicateFormatException e) {
            actions = noFurtherActivities();
        } catch (ServiceFailureException e) {
            actions = retryCurrentActivity();            
        }
    }
    
    public AppealFormat getPayment() {
        return appealFormat;
    }
}
