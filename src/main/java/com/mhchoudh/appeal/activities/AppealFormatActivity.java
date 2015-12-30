package com.mhchoudh.appeal.activities;

import com.mhchoudh.appeal.model.Identifier;
import com.mhchoudh.appeal.model.AppealStatus;
import com.mhchoudh.appeal.model.AppealFormat;
import com.mhchoudh.appeal.repositories.AppealRepository;
import com.mhchoudh.appeal.repositories.AppealFormatterRepository;
import com.mhchoudh.appeal.representations.Link;
import com.mhchoudh.appeal.representations.FormatAppealRepresentation;
import com.mhchoudh.appeal.representations.Representation;
import com.mhchoudh.appeal.representations.CSE564AppealsUri;

public class AppealFormatActivity {
    public FormatAppealRepresentation pay(AppealFormat appealFormat, CSE564AppealsUri appealFormatUri) throws InterruptedException {
        Identifier identifier = appealFormatUri.getId();
        
        // Don't know the appeal!
        if(!AppealRepository.current().has(identifier)) {
            throw new NoSuchAppealException();
        }
        
        // Already paid
        if(AppealFormatterRepository.current().has(identifier)) {
            throw new UpdateException();
        }
        
        // Business rules - if the appealFormat amount doesn't match the amount outstanding, then reject
        
        
        // If we get here, let's create the appealFormat and update the appeal status
        AppealRepository.current().get(identifier).setStatus(AppealStatus.Reviewing);
        AppealFormatterRepository.current().store(identifier, appealFormat);
        
        return new FormatAppealRepresentation(appealFormat, new Link(Representation.RELATIONS_URI + "appeal", UriExchange.appealForFormat(appealFormatUri)),
                new Link(Representation.RELATIONS_URI + "reply", UriExchange.replyForAppeal(appealFormatUri)));
    }
}
