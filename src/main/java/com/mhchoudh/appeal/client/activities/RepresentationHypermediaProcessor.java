package com.mhchoudh.appeal.client.activities;

import com.mhchoudh.appeal.representations.AppealRepresentation;
import com.mhchoudh.appeal.representations.FormatAppealRepresentation;

class RepresentationHypermediaProcessor {

    Actions extractNextActionsFromAppealRepresentation(AppealRepresentation representation) {
        Actions actions = new Actions();

        if (representation != null) {

            if (representation.getFormatAppealLink() != null) {
                actions.add(new FormatActivity(representation.getFormatAppealLink().getUri()));
            }

            if (representation.getUpdateLink() != null) {
                actions.add(new UpdateAppealActivity(representation.getUpdateLink().getUri()));
            }

            if (representation.getSelfLink() != null) {
                actions.add(new ReadAppealActivity(representation.getSelfLink().getUri()));
            }

            if (representation.getCancelLink() != null) {
                actions.add(new CancelAppealActivity(representation.getCancelLink().getUri()));
            }
        }

        return actions;
    }

    public Actions extractNextActionsFromPaymentRepresentation(FormatAppealRepresentation representation) {
        Actions actions = new Actions();
        
        if(representation.getAppealLink() != null) {
            actions.add(new ReadAppealActivity(representation.getAppealLink().getUri()));
        }
        
        if(representation.getReplyLink() != null) {
            actions.add(new GetReplyActivity(representation.getReplyLink().getUri()));
        }
        
        return actions;
    }

}
